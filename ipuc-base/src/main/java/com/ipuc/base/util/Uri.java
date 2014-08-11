package com.ipuc.base.util;

import java.net.URI;
import java.net.URISyntaxException;

public class Uri {

    private URI uri;

    /**
     * Constructor. Receives the uri that we are going to parse.
     *
     * @param uri
     *
     * @throws URISyntaxException
     */
    public Uri(String uri) throws URISyntaxException {
        this.uri = new URI(uri);
    }

    public String getUrl() {
        return uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort();
    }

    public String getHost() {
        return uri.getHost();
    }

    public String getScheme() {
        return uri.getScheme();
    }

    public int getPort() {
        return uri.getPort();
    }

    public String getPath() {
        String path = uri.getPath();
        if (uri.getQuery() != null) {
            path += "?" + uri.getQuery();
        }
        return path;
    }

    public String getUsername() {
        if (uri.getUserInfo() != null) {
            return uri.getUserInfo().split(":", 2)[0];
        }
        return null;
    }

    public String getPassword() {
        if (uri.getUserInfo() != null) {
            return uri.getUserInfo().split(":", 2)[1];
        }
        return null;
    }
}
