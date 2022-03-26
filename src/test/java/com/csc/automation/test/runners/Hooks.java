package com.csc.automation.test.runners;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties("selenium.browser")

public class Hooks {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

  @Setter private String name;
  @Setter private Boolean headless;
  @Setter private String remote;
  @Setter private String baseUrl;
  @Setter private String language;
  @Setter private String timeToWaitForPageToLoad;
  @Setter @Getter private String implicitWaitTime;
  @Setter @Getter private Long fluentWaitInterval;
  @Setter @Getter private Long fluentWaitDuration;

  private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  private ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

  @PostConstruct
  public void initialize() {

    // Shutdown hook
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  if (isDriverLoaded()) {
                    LOGGER.info("Shutdown signal detected: Closing opened drivers");
                    closeDriver();
                    LOGGER.info("Opened drivers closed");
                  }
                }));
    // --
  }

  private boolean isDriverLoaded() {
    return driver != null && driver.get() != null;
  }


  public WebDriver getDriver() {
    if (driver.get() == null) {
      initialiseDriver();
    }
    return driver.get();
  }

  public WebDriverWait getWait() {
    if (null == (wait)) {
      initialiseDriver();
    }
    return wait.get();
  }

  public void tearDown(Scenario scenario) {

    final byte[] screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BYTES);
    scenario.attach(screenshot, "image/png", "screenshot");

  }

  public void closeDriver() {
    if (null == (driver.get())) {
      return;
    } else {
      driver.get().quit();
      driver.set(null);
    }
  }

  private void initialiseDriver() {


    System.setProperty("webdriver.chrome.silentOutput", "true");
      setChromeDriver();

  }

  private void setChromeDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("enable-automation");
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--disable-extensions"); // disabling extensions
    // Headless mode

    WebDriverManager.chromedriver().setup();

    System.out.println("WebDriver initialized");

    driver.set(new ChromeDriver(chromeOptions));




  }

  public String getConfigLang() {
    return language;
  }
}
