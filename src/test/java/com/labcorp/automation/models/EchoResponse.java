package com.labcorp.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class EchoResponse {
    private String method;
    private String protocol;
    private String host;
    private String path;
    private String ip;
    private Map<String, String> headers;
    
    @JsonProperty("parsedQueryParams")
    private Map<String, String> queryParams;

    // Getters and Setters
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    
    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }
    
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    
    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }
    
    public Map<String, String> getQueryParams() { return queryParams; }
    public void setQueryParams(Map<String, String> queryParams) { this.queryParams = queryParams; }
} 