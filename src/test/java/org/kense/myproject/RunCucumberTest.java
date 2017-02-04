package org.kense.myproject;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@new",
        features = "classpath:features",
        plugin = {"pretty", "junit:target/cucumber/feature-test-report.xml", "html:target/cucumber/html-report", "usage:target/cucumber/feature-test-usage-report.json"},
        snippets = SnippetType.CAMELCASE
)
public class RunCucumberTest {
}
