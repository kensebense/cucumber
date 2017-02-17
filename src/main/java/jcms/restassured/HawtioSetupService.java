package jcms.restassured;

import com.jayway.restassured.RestAssured;

public final class HawtioSetupService {

    public static HawtioSetupService setup() {
        return new HawtioSetupService();
    }

    private HawtioSetupService() {
        this.uriFromEnvironmentVariables();
    }

    private void uriFromEnvironmentVariables() {
        RestAssured.baseURI = URIResolver.hawtioBaseUriFromEnvironmentVariables();
    }
}
