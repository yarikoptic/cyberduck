package ch.cyberduck.core.spectra;

/*
 * Copyright (c) 2002-2016 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.Local;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.http.HttpUploadFeature;
import ch.cyberduck.core.io.BandwidthThrottle;
import ch.cyberduck.core.io.CRC32ChecksumCompute;
import ch.cyberduck.core.io.MD5ChecksumCompute;
import ch.cyberduck.core.io.StreamCancelation;
import ch.cyberduck.core.io.StreamListener;
import ch.cyberduck.core.io.StreamProgress;
import ch.cyberduck.core.preferences.PreferencesFactory;
import ch.cyberduck.core.transfer.Transfer;
import ch.cyberduck.core.transfer.TransferStatus;

import org.apache.log4j.Logger;
import org.jets3t.service.model.StorageObject;

import java.security.MessageDigest;
import java.util.List;

public class SpectraUploadFeature extends HttpUploadFeature<StorageObject, MessageDigest> {
    private static final Logger log = Logger.getLogger(SpectraWriteFeature.class);

    private final SpectraSession session;

    public SpectraUploadFeature(final SpectraSession session, final SpectraWriteFeature write) {
        super(write);
        this.session = session;
    }

    @Override
    public StorageObject upload(final Path file, final Local local, final BandwidthThrottle throttle, final StreamListener listener,
                                final TransferStatus status, final StreamCancelation cancel, final StreamProgress progress) throws BackgroundException {
        // The client-side checksum is passed to the BlackPearl gateway by supplying the applicable CRC HTTP header.
        // If this is done, the BlackPearl gateway verifies that the data received matches the checksum provided.
        // End-to-end data protection requires that the client provide the CRC when uploading the object and then
        // verify the CRC after downloading the object at a later time (see Get Object). The BlackPearl gateway also
        // verifies the CRC when reading from physical data stores so the gateway can identify problems before
        // transmitting data to the client.
        if(PreferencesFactory.get().getBoolean("spectra.upload.crc32")) {
            status.setChecksum(new CRC32ChecksumCompute().compute(local.getInputStream()));
        }
        if(PreferencesFactory.get().getBoolean("spectra.upload.md5")) {
            status.setChecksum(new MD5ChecksumCompute().compute(local.getInputStream()));
        }
        final SpectraBulkService bulk = new SpectraBulkService(session);
        // Make sure file is available in cache
        final List<TransferStatus> chunks = bulk.query(Transfer.Type.upload, file, status);
        if(chunks.isEmpty()) {
            log.error(String.format("Empty chunk array for upload %s", file));
            return super.upload(file, local, throttle, listener, status, cancel, progress);
        }
        StorageObject stored = null;
        for(TransferStatus chunk : chunks) {
            stored = super.upload(file, local, throttle, listener, chunk, cancel, progress);
        }
        return stored;
    }
}
