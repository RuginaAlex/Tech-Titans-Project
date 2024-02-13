package com.techtitans.smartbudget.security.dto;

import java.util.List;

public class Cors {
    private List<String> hosts;

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}