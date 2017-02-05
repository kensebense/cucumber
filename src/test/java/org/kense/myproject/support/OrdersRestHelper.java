package org.kense.myproject.support;

import org.kense.dto.OrderDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.inject.Inject;
import java.util.List;

public class OrdersRestHelper {


    @Inject
    private LoggerHelper loggerHelper;

    @Inject
    private SeleniumHelper seleniumHelper;

    private OrderDTO orderDTO;

    public void login(UserCredentials credentials) {

        loggerHelper.getLogger().info(String.format("Logged in with username %s", credentials.getUsername()));
    }

    public OrderDTO getOrder() {
        return this.orderDTO;
    }

    public List<OrderDTO> requestOrders() {

        return null;
    }

    public OrderDTO requestDraftOrder() {

        this.orderDTO = new OrderDTO();
        return getOrder();
    }

    public void addPartToOrder(Long partNumber) {
        addPartToOrder(partNumber, null);
    }

    public void addPartToOrder(Long partNumber, Long quantity) {

        if (quantity == null) {
            quantity = 1L;
        }

        orderDTO.getPartIds().add(partNumber);

        loggerHelper.getLogger().info(String.format("Added part %s to order", partNumber));
    }

    // testing selenium here
    public String searchForCheese() {

        WebDriver webDriver = seleniumHelper.getDriver();

        // And now use this to visit Google
        webDriver.navigate().to("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = webDriver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        loggerHelper.getLogger().info("Page title is: " + webDriver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(webDriver, 10))
                .until((ExpectedCondition<Boolean>) d -> d.getTitle().toLowerCase().startsWith("cheese!"));

        // Should see: "cheese! - Google Search"
        String message = "Page title is: " + webDriver.getTitle();
        loggerHelper.getLogger().info(message);
        return message;

    }
}
