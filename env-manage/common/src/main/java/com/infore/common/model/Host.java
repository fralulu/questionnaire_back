package com.infore.common.model;

import java.util.Objects;

public class Host {

    private String host;
    private int port;

    public Host(String host, int port) {
        Objects.requireNonNull(host, "Host is null.");
        if (host.isEmpty()) {
            throw new IllegalArgumentException("Host address is incorrect.");
        }

        if (port < 0) {
            throw new IllegalArgumentException("Host port is incorrect.");
        }

        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
