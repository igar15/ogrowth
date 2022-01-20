package ru.javaprojects.organizationservice.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";

    private final ThreadLocal<String> correlationId= new ThreadLocal<>();
    private final ThreadLocal<String> authToken= new ThreadLocal<>();
    private final ThreadLocal<String> userId = new ThreadLocal<>();
    private final ThreadLocal<String> orgId = new ThreadLocal<>();

    public String getCorrelationId() { return correlationId.get();}
    public void setCorrelationId(String correlationId) {
        this.correlationId.set(correlationId);
    }

    public String getAuthToken() {
        return authToken.get();
    }

    public void setAuthToken(String authToken) {
        this.authToken.set(authToken);
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public String getOrgId() {
        return orgId.get();
    }

    public void setOrgId(String orgId) {
        this.orgId.set(orgId);
    }

    public HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CORRELATION_ID, getCorrelationId());
        return httpHeaders;
    }

}