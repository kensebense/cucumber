package jcms.restassured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public enum ConfigurationHelper {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationHelper.class);

    public Integer valueOf(String environmentVariableName, Integer defaultValue) {

        String value = valueOf(environmentVariableName, String.valueOf(defaultValue));
        return Integer.valueOf(value);
    }

    public String valueOf(String environmentVariableName, String defaultValue) {

        Preconditions.checkArgument(defaultValue != null, "A defaultValue must be given");

        String variableValue;

        variableValue = System.getenv(environmentVariableName);
        if (variableValue == null) {
            variableValue = System.getProperty(environmentVariableName);
        }

        if (variableValue != null && !variableValue.isEmpty()) {
            LOGGER.debug("Found environment variable {} with valueOf {}", environmentVariableName, variableValue);
        } else {
            // Nothing found, make sure to use given defaultValue
            variableValue = defaultValue;
            LOGGER.debug("Did not find environment variable {}, using the default value {}", environmentVariableName, defaultValue);
        }

        return variableValue;
    }
}
