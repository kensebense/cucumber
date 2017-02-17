package jcms.restassured;

import static jcms.restassured.RestConstants.BASE_PORT;
import static jcms.restassured.RestConstants.DEFAULT_URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class URIResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(URIResolver.class);

    /**
     * Resolves an URI based on environment variables.
     * Will look for the below environment variable keys and use their values.
     * <ul>
     *     <li>crewplace.server.baseuri</li>
     *     <li>crewplace.server.port</li>
     * </ul>
     *
     * If the environment variable key is not found, the process will revert to using defaults based on
     * <ul>
     *     <li>{@link RestConstants#BASE_SCHEMA}</li>
     *     <li>{@link RestConstants#BASE_HOSTNAME}</li>
     *     <li>{@link RestConstants#BASE_PORT}</li>
     * </ul>
     * @return the URI.
     */
    public static String baseUriFromEnvironmentVariables() {
        return baseUriFromEnvironmentVariablesInternal("site");
    }

    public static String hawtioBaseUriFromEnvironmentVariables() {
        return baseUriFromEnvironmentVariablesInternal("hawtio");
    }

    private static String baseUriFromEnvironmentVariablesInternal(String contextPath) {
        // If executing environment (GoCD) sets a specific jboss hostname, then use this instead of the default value
        // Example of specifying on command line: mvn test -DargLine="-Dcrewplace.server.baseuri=http://myhostname -Dcrewplace.server.port=8090"

        String baseURI = ConfigurationHelper.INSTANCE.valueOf(ConfigurationConstants.SERVER_BASE_URI, DEFAULT_URI);
        String port = ConfigurationHelper.INSTANCE.valueOf(ConfigurationConstants.SERVER_PORT, BASE_PORT);

        String result = String.format("%s:%s/%s", baseURI, port, contextPath);

        LOGGER.debug(String.format("Setting base URI to %s", result));

        return result;
    }
}
