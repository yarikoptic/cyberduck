package ch.cyberduck.core.dav;

/*
 * Copyright (c) 2002-2018 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

import com.github.sardine.impl.handler.MultiStatusResponseHandler;
import com.github.sardine.model.Collection;
import com.github.sardine.model.Creationdate;
import com.github.sardine.model.Displayname;
import com.github.sardine.model.Getcontentlength;
import com.github.sardine.model.Getcontenttype;
import com.github.sardine.model.Getetag;
import com.github.sardine.model.Getlastmodified;
import com.github.sardine.model.Multistatus;
import com.github.sardine.model.Prop;
import com.github.sardine.model.Propstat;
import com.github.sardine.model.Resourcetype;
import com.github.sardine.model.Response;
import com.github.sardine.util.SardineUtil;

public class SaxPropFindResponseHandler extends MultiStatusResponseHandler {
    private static final Logger log = Logger.getLogger(SaxPropFindResponseHandler.class);

    @Override
    protected Multistatus getMultistatus(final InputStream stream) throws IOException {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        try {
            final SAXParser saxParser = spf.newSAXParser();
            final XMLReader xmlReader = saxParser.getXMLReader();
            final SaxHandler handler = new SaxHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(stream));
            return handler.getMultistatus();
        }
        catch(Exception e) {
            throw new IOException("Not a valid DAV response", e);
        }
    }

    private static final class SaxHandler extends DefaultHandler {

        private Multistatus multistatus;

        private Response response;
        private Propstat propstat;
        private Prop prop;
        private Resourcetype type;

        private StringBuilder data;
        private Element root;

        @Override
        public void startDocument() {
            multistatus = new Multistatus();
            root = SardineUtil.createElement(SardineUtil.createQNameWithCustomNamespace("root"));
        }

        @Override
        public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
            switch(localName) {
                case "multistatus":
                    multistatus = new Multistatus();
                    break;
                case "response":
                    response = new Response();
                    if(multistatus != null) {
                        multistatus.getResponse().add(response);
                    }
                    break;
                case "propstat":
                    propstat = new Propstat();
                    if(response != null) {
                        response.getPropstat().add(propstat);
                    }
                    break;
                case "prop":
                    prop = new Prop();
                    if(propstat != null) {
                        propstat.setProp(prop);
                    }
                    break;
                case "resourcetype":
                    type = new Resourcetype();
                    if(prop != null) {
                        prop.setResourcetype(type);
                    }
                    break;
                case "collection":
                    type.setCollection(new Collection());
                    break;
            }
            data = new StringBuilder();
        }

        @Override
        public void characters(final char[] ch, final int start, final int length) {
            data.append(new String(ch, start, length));
        }

        @Override
        public void endElement(final String uri, final String localName, final String qName) {
            if(StringUtils.isBlank(data.toString())) {
                log.warn(String.format("Missing value for %s", localName));
                return;
            }
            if(response != null) {
                switch(localName) {
                    case "href": {
                        if(response != null) {
                            response.getHref().add(data.toString());
                        }
                        break;
                    }
                }
            }
            if(propstat != null) {
                switch(localName) {
                    case "status": {
                        propstat.setStatus(data.toString());
                        break;
                    }
                }
            }
            if(prop != null) {
                switch(localName) {
                    case "creationdate": {
                        final Creationdate value = new Creationdate();
                        value.getContent().add(data.toString());
                        prop.setCreationdate(value);
                        break;
                    }
                    case "displayname": {
                        final Displayname value = new Displayname();
                        value.getContent().add(data.toString());
                        prop.setDisplayname(value);
                        break;
                    }
                    case "getcontentlength": {
                        final Getcontentlength value = new Getcontentlength();
                        value.getContent().add(data.toString());
                        prop.setGetcontentlength(value);
                        break;
                    }
                    case "getcontenttype": {
                        final Getcontenttype value = new Getcontenttype();
                        value.getContent().add(data.toString());
                        prop.setGetcontenttype(value);
                        break;
                    }
                    case "getlastmodified": {
                        final Getlastmodified value = new Getlastmodified();
                        value.getContent().add(data.toString());
                        prop.setGetlastmodified(value);
                        break;
                    }
                    case "getetag": {
                        final Getetag value = new Getetag();
                        value.getContent().add(data.toString());
                        prop.setGetetag(value);
                        break;
                    }
                    case "lastmodified_server": {
                        final Element element = SardineUtil.createElement(root, DAVTimestampFeature.LAST_MODIFIED_SERVER_CUSTOM_NAMESPACE);
                        element.setTextContent(data.toString());
                        prop.getAny().add(element);
                        break;
                    }
                    case "lastmodified": {
                        final Element element = SardineUtil.createElement(root, DAVTimestampFeature.LAST_MODIFIED_CUSTOM_NAMESPACE);
                        element.setTextContent(data.toString());
                        prop.getAny().add(element);
                        break;
                    }
                }
            }
            switch(localName) {
                case "response": {
                    response = null;
                    break;
                }
                case "propstat": {
                    propstat = null;
                    break;
                }
                case "prop": {
                    prop = null;
                    break;
                }
            }
            if(!SardineUtil.DEFAULT_NAMESPACE_URI.equals(uri)) {
                // Custom property
                final Element element = SardineUtil.createElement(root, new QName(uri, localName, SardineUtil.DEFAULT_NAMESPACE_PREFIX));
                element.setTextContent(data.toString());
                prop.getAny().add(element);
            }
        }

        public Multistatus getMultistatus() {
            return multistatus;
        }
    }
}
