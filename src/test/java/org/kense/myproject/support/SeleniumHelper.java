package org.kense.myproject.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

public class SeleniumHelper {

    private ChromeDriverService service;
    private WebDriver driver;

    @PostConstruct
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/chromedriver/chromedriver");

        service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();

        service.start();

        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
    }

    @PreDestroy
    public void createAndStopService() {
        driver.quit();
        service.stop();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
