package com.csc.automation.test.pages;

import com.csc.automation.test.config.ApplicationProperties;
import com.csc.automation.test.runners.Hooks;
import io.cucumber.java.Scenario;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@ScenarioScope
@Slf4j
public class CommonPage implements BasePage {
    private final Hooks hooks;
    private final ApplicationProperties applicationProperties;

    public CommonPage(Hooks hooks, ApplicationProperties applicationProperties) {
        this.hooks = hooks;
        this.applicationProperties = applicationProperties;
        PageFactory.initElements(this.hooks.getDriver(), this);
    }

    public void selectByVisibleText(String value, @NotNull WebElement locator) {
        Select select = new Select(locator);
        try {
            if (!value.isEmpty()) highlightElement(locator);
            select.selectByVisibleText(value);
        } catch (Exception exception) {
            log.debug("Value not selected due to exception {}", exception.getMessage());
        }
    }

    public void clickElement(WebElement element){
        highlightElement(element);
        element.click();
    }

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) hooks.getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: #778899 ');", element);
    }

    public void enterData(String data, WebElement element) {
        highlightElement(element);
        element.sendKeys(data);
    }


}
