package org.kense.myproject.support;

import javax.inject.Inject;
import org.kense.myproject.automation.selenium.SeleniumHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSeleniumHelper {

    @Inject
    private LoggerHelper loggerHelper;

    @Inject
    private SeleniumHandler seleniumHandler;

    public String searchFor(final String searchArgument) {

        WebDriver webDriver = seleniumHandler.getDriver();

        // And now use this to visit Google
        webDriver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = webDriver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys(searchArgument);

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        loggerHelper.getLogger().info("Page title is: " + webDriver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<Boolean>) d -> d.getTitle().startsWith(searchArgument));

        // Should see: "Cheese! - Google Search"
        String message = String.format("Page title is: %s", webDriver.getTitle());
        loggerHelper.getLogger().info(message);

        return webDriver.getTitle();

    }
}
