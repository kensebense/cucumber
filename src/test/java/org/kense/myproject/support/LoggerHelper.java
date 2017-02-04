package org.kense.myproject.support;

import java.util.logging.Logger;

/**
 * RestRequestLoggerBean which purpose is to provide a logger that can be shared between classes.
 * The logger instantiated here will be used and changed by {@link com.volvo.postest.feature.hooks.LoggingHooks} once for each scenario,
 * then used in {@link com.volvo.postest.feature.support.OrdersRestHelper#prepare(com.volvo.postest.feature.restclient.RestHandler)}
 */
public class LoggerHelper {

    public static final String LOGGER_NAME = LoggerHelper.class.getName();

    private static Logger LOGGER = Logger.getLogger(LOGGER_NAME);

    public Logger getLogger() {
        return LOGGER;
    }
}
