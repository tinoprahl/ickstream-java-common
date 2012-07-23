/*
 * Copyright (C) 2012 Erland Isaksson (erland@isaksson.info)
 * All rights reserved.
 */

package com.ickstream.protocol.cloud;

import com.ickstream.common.jsonrpc.MessageLogger;
import com.ickstream.protocol.cloud.core.CoreService;
import com.ickstream.protocol.cloud.core.FindServicesRequest;
import com.ickstream.protocol.cloud.core.FindServicesResponse;
import com.ickstream.protocol.cloud.library.LibraryService;
import com.ickstream.protocol.cloud.scrobble.ScrobbleService;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

public class ServiceFactory {
    private static final String CORESERVICE_ENDPOINT = "http://ickstream.isaksson.info/ickstream-cloud-core/jsonrpc";

    private static String getEndpoint() {
        String url = System.getProperty("ickstream-core-url");
        if (url != null) {
            return url;
        } else {
            return CORESERVICE_ENDPOINT;
        }
    }

    public static CoreService getCoreService(String accessToken, MessageLogger messageLogger) {
        CoreService coreService = new CoreService(createHttpClient(), getEndpoint());
        coreService.setAccessToken(accessToken);
        coreService.setMessageLogger(messageLogger);
        return coreService;
    }

    private static HttpClient createHttpClient() {
        try {
            Class.forName("org.apache.http.impl.conn.PoolingClientConnectionManager");
            return new DefaultHttpClient(new PoolingClientConnectionManager());
        } catch (ClassNotFoundException e) {
            return new DefaultHttpClient(new ThreadSafeClientConnManager());
        }
    }

    public static CoreService getCoreService(String accessToken) {
        return getCoreService(accessToken, null);
    }

    public static ScrobbleService getScrobbleService(String accessToken, MessageLogger messageLogger) {
        try {
            FindServicesResponse response = getCoreService(accessToken, messageLogger).findServices(new FindServicesRequest("scrobble"));
            if (response != null && response.getItems_loop().size() > 0) {
                ScrobbleService scrobbleService = new ScrobbleService(new DefaultHttpClient(), response.getItems_loop().get(0).getUrl());
                scrobbleService.setAccessToken(accessToken);
                scrobbleService.setMessageLogger(messageLogger);
                return scrobbleService;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServiceTimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ScrobbleService getScrobbleService(String accessToken) {
        return getScrobbleService(accessToken, null);
    }

    public static LibraryService getLibraryService(String accessToken, MessageLogger messageLogger) {
        try {
            FindServicesResponse response = getCoreService(accessToken, messageLogger).findServices(new FindServicesRequest("librarymanagement"));
            if (response != null && response.getItems_loop().size() > 0) {
                LibraryService libraryService = new LibraryService(new DefaultHttpClient(), response.getItems_loop().get(0).getUrl());
                libraryService.setAccessToken(accessToken);
                libraryService.setMessageLogger(messageLogger);
                return libraryService;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServiceTimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LibraryService getLibraryService(String accessToken) {
        return getLibraryService(accessToken, null);
    }
}
