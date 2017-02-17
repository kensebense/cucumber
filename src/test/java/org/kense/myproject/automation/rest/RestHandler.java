package org.kense.myproject.automation.rest;

import java.util.Map;

import jcms.restassured.RestAssuredSetupService;
import jcms.restassured.RestConstants;

public class RestHandler<T> {

    public RestHandler() {
        RestAssuredSetupService.setup()
                .basicAuthCredentials(RestConstants.CREDENTIALS_ADMIN_USERNAME, RestConstants.CREDENTIALS_ADMIN_PASSWORD);
    }

    public void setCredentials(String userName, String password) {
        RestAssuredSetupService.setup()
                .basicAuthCredentials(userName, password);
    }

    public T performPostRequest(String resourceURI, String payload, Map<String, Object> pathParams) {
        return null;
    }

    public T performPutRequest(String resourceURI, String payload, Map<String, Object> pathParams) {
        return null;
    }

    public T performGetRequest(String resourceURI, Map<String, Object> pathParams, Map<String, Object> queryParams) {
        return null;
    }
}
