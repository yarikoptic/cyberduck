package ch.cyberduck.core.ftp;

/*
 * Copyright (c) 2002-2013 David Kocher. All rights reserved.
 * http://cyberduck.ch/
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
 *
 * Bug fixes, suggestions and comments should be sent to feedback@cyberduck.ch
 */

import ch.cyberduck.core.AbstractTestCase;
import ch.cyberduck.core.AttributedList;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.Protocol;
import ch.cyberduck.core.ftp.parser.CompositeFileEntryParser;

import org.apache.commons.net.ftp.FTPFileEntryParser;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @version $Id$
 */
public class FTPListResponseReaderTest extends AbstractTestCase {

    @Test(expected = FTPInvalidListException.class)
    public void test3243() throws Exception {
        FTPFileEntryParser parser = new FTPParserSelector().getParser("UNIX");

        final FTPSession s = new FTPSession(new Host(Protocol.FTP, "localhost"));
        Path path = new Path("/SunnyD", Path.DIRECTORY_TYPE);
        assertEquals("SunnyD", path.getName());
        assertEquals("/SunnyD", path.getAbsolute());

        final AttributedList<Path> list = new AttributedList<Path>();
        new FTPListResponseReader().read(s, path, parser,
                Collections.singletonList(" drwxrwx--x 1 owner group          512 Jun 12 15:40 SunnyD"));
    }

    @Test
    public void testParseSymbolicLink() throws Exception {
        FTPFileEntryParser parser = new FTPParserSelector().getParser("UNIX");

        final FTPSession s = new FTPSession(new Host(Protocol.FTP, "localhost"));
        Path path = new Path("/", Path.DIRECTORY_TYPE);
        assertEquals("/", path.getName());
        assertEquals("/", path.getAbsolute());

        final AttributedList<Path> list = new FTPListResponseReader().read(s, path, parser,
                Collections.singletonList("lrwxrwxrwx    1 mk basicgrp       27 Sep 23  2004 www -> /www/basic/mk"));

        assertFalse(list.isEmpty());
        assertTrue(list.get(0).attributes().isSymbolicLink());
        assertEquals("/www/basic/mk", list.get(0).getSymlinkTarget().getAbsolute());
    }

    @Test(expected = FTPInvalidListException.class)
    public void test3763() throws Exception {
        FTPFileEntryParser parser = new FTPParserSelector().getParser("UNIX");

        final FTPSession s = new FTPSession(new Host(Protocol.FTP, "localhost"));
        Path path = new Path("/www", Path.DIRECTORY_TYPE);
        assertEquals("www", path.getName());
        assertEquals("/www", path.getAbsolute());

        final AttributedList<Path> list = new FTPListResponseReader().read(s, path, parser,
                Collections.singletonList("lrwxrwxrwx    1 mk basicgrp       27 Sep 23  2004 /home/mk/www -> /www/basic/mk"));
    }

    @Test
    @Ignore
    public void testParseHardlinkCountBadFormat() throws Exception {
        final FTPSession s = new FTPSession(new Host(Protocol.FTP, "localhost"));
        Path path = new Path(
                "/store/public/brain", Path.DIRECTORY_TYPE);

        String[] replies = new String[]{
                "drwx------+111 mi       public       198 Dec 17 12:29 unsorted"
        };

        final CompositeFileEntryParser parser = new FTPParserSelector().getParser("UNIX");
        final AttributedList<Path> list = new FTPListResponseReader().read(s, path, parser, Arrays.asList(replies));
        assertEquals(1, list.size());
        assertEquals("unsorted", list.get(0).getName());
        assertEquals("/store/public/brain", list.get(0).getParent().getAbsolute());
    }


    @Test
    public void testParseAbsolutePaths() throws Exception {
        final FTPSession s = new FTPSession(new Host(Protocol.FTP, "localhost"));
        Path path = new Path(
                "/data/FTP_pub", Path.DIRECTORY_TYPE);

        String[] replies = new String[]{
                "- [RWCEAFMS] Petersm                             0 May 05  2004 /data/FTP_pub/WelcomeTo_PeakFTP"
        };

        final AttributedList<Path> list = new FTPListResponseReader().read(s, path, new FTPParserSelector().getParser("NETWARE  Type : L8"),
                Arrays.asList(replies));
        assertEquals(1, list.size());
        assertEquals("WelcomeTo_PeakFTP", list.get(0).getName());
        assertEquals("/data/FTP_pub", list.get(0).getParent().getAbsolute());
    }
}
