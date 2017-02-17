package org.kense.myproject.automation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

public class SeleniumHandler {

    private ChromeDriverService service;
    private WebDriver driver;

    @PostConstruct
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/dev/tools/seleniumdriver/chromedriver.exe");

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
