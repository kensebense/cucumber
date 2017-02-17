package org.kense.myproject.stepdefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.kense.myproject.support.GoogleSeleniumHelper;
import org.kense.myproject.support.GoogleTestSession;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Singleton
public class GoogleSteps {

    @Inject
    private GoogleSeleniumHelper googleSeleniumHelper;

    @Inject
    private GoogleTestSession testSession;

    @Given("^I have started a Chrome browser$")
    public void iHaveStartedAChromeBrowser() throws Throwable {
        // nothing needs to be done, Selenium will kick one off for us;
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchArgument) throws Throwable {

        String searchTitle = googleSeleniumHelper.searchFor(searchArgument);
        testSession.setTitleContent(searchTitle);
    }

    @Then("^the title bar should say \"([^\"]*)\"$")
    public void theTitleBarShouldSay(String expectedTitle) throws Throwable {

        assertThat(testSession.getTitleContent(), is(expectedTitle));
    }
}
