package jcms.restassured;


import static com.jayway.restassured.RestAssured.given;

import java.io.File;

import javax.ws.rs.core.MediaType;

import com.jayway.restassured.RestAssured;

public final class RestAssuredSetupService {

    public static RestAssuredSetupService setup() {
        return new RestAssuredSetupService();
    }

    private RestAssuredSetupService() {
        this.uriFromEnvironmentVariables();
    }

    /**
     * Creates and sets a Basic Authentication with given credentials, to be used during execution towards API.
     * @param username the username
     * @param password the password
     * @return an instance of class.
     */
    public RestAssuredSetupService basicAuthCredentials(String username, String password) {
        RestAssured.authentication = RestAssured.preemptive().basic(username, password);
        return this;
    }

    /**
     * Posts a multi part form to the given URL.
     * @param file the file to upload
     * @param urlPathName the destination URL path name - i.e. without protocol, hostname and port.
     *            <ul>
     *              <li>Not correct: http://dockerhost:20200/site/upload</li>
     *              <li>Correct: /site/upload</li>
     *            </ul>
     * @return an instance of class.
     */
    public RestAssuredSetupService postMultiPart(File file, String urlPathName) {

        given()
                .accept(MediaType.MULTIPART_FORM_DATA)
                .multiPart(file)
                .when()
                .log().ifValidationFails()
                .post(urlPathName)
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200);

        return this;
    }

    /**
     * Resolves an URI based on environment variables.
     * Will look for the below environment variable keys and use their values.
     * <ul>
     *     <li>jboss_schema</li>
     *     <li>jboss_hostname</li>
     *     <li>jboss_port</li>
     * </ul>
     *
     * If any of the environment variable keys are not found, the process will revert to using defaults based on
     * <ul>
     *     <li>{@link RestConstants#BASE_SCHEMA}</li>
     *     <li>{@link RestConstants#BASE_HOSTNAME}</li>
     *     <li>{@link RestConstants#BASE_PORT}</li>
     * </ul>
     * @return an instance of class.
     */
    private void uriFromEnvironmentVariables() {

        RestAssured.baseURI = URIResolver.baseUriFromEnvironmentVariables();
    }
}
