package com.csc.automation.test.pages;

import com.csc.automation.test.runners.Hooks;
import com.csc.automation.test.config.ApplicationProperties;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@Slf4j
public class SearchPage implements BasePage{

  private final Hooks hooks;
  private final ApplicationProperties applicationProperties;

  public SearchPage(Hooks hooks, ApplicationProperties applicationProperties) {
    this.hooks = hooks;
    this.applicationProperties = applicationProperties;
    PageFactory.initElements(this.hooks.getDriver(), this);
  }

  @FindBy(how = How.CSS, using = "input[name='q'][data-test-selector='nav-search-input']")
  private WebElement searchOrjumpToTextBox;

  public void enterSearchText(String searchText) {
    searchOrjumpToTextBox.click();
    searchOrjumpToTextBox.sendKeys(searchText);
    searchOrjumpToTextBox.sendKeys(Keys.ENTER);
  }

  public boolean isGitHubSearchPage() {
    return ((hooks.getDriver().getCurrentUrl().contains(applicationProperties.getBaseUrl())));
  }

  public void goToSearchPage(){
    hooks.getDriver().get(applicationProperties.getBaseUrl());
  }
}
