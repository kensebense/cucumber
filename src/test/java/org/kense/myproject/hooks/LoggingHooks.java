package org.kense.myproject.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.kense.myproject.support.LoggerHelper;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoggingHooks {

    @Inject
    LoggerHelper loggerHelper;

    private Handler fileHandler;
    private String scenarioName;

    @Before
    public void setupScenarioLogger(Scenario scenario) {

        setScenarioName(scenario);

        Logger logger = loggerHelper.getLogger();

        final File logDirectory = new File("target/cucumber/logs/requests");
        if (logDirectory.mkdirs()) {
            logger.info(String.format("Created log directory %s", logDirectory.getPath()));
        }

        try {
            fileHandler = new FileHandler(String.format("%s/%s.log", logDirectory.getPath(), scenarioName));
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.info(String.format("Scenario started: %s", scenarioName));

        } catch (IOException e) {
            logger.warning(String.format("Could not create log file handler: %s", e.getMessage()));
            logger.warning("Will use default console log instead");
        }
    }

    private void setScenarioName(Scenario scenario) {

        // Set the default scenario name
        scenarioName = scenario.getName();

        /*
            When dealing with Scenario Outlines, we want to distinguish which round of the Examples that is currently
            being run (otherwise the run of the second Example will overwrite the logs of the first Example since they both share the
            same scenario name).

            scenario.getId() gives a semicolon delimited list, and if the entry after the last semicolon is a number,
            then we know it is a Scenario Outline Example. We can use this number as a sequence number to append to the scenarioName

            Example of what scenario.getId() can return if current run is a Scenario Outline Example run:
            attach-shipping-address-to-order;change-an-existing-shipping-address-to-an-incomplete-address;;2
         */

        // Pattern that matches any characters after the last semicolon and puts them in group 1 of matcher
        Pattern pattern = Pattern.compile(".*;\\s*(.*$)");
        Matcher matcher = pattern.matcher(scenario.getId());

        if (matcher.find() && matcher.group(1).matches("\\d+")) {

            String scenarioOutlineSeqNo = matcher.group(1);
            scenarioName = String.format("%s.%s", scenarioName, scenarioOutlineSeqNo);
        }
    }

    @After
    public void tearDownScenarioLogger() {

        Logger logger = loggerHelper.getLogger();

        logger.info(String.format("Scenario ended: %s", scenarioName));

        if (fileHandler != null) {
            fileHandler.flush();
            fileHandler.close();
            logger.removeHandler(fileHandler);
        }
    }
}

