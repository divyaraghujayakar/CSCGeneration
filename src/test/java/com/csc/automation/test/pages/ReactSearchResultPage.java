package com.csc.automation.test.pages;

import com.csc.automation.test.runners.Hooks;
import com.csc.automation.test.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@Slf4j
public class ReactSearchResultPage implements BasePage {
  private final Hooks hooks;
  private final ApplicationProperties applicationProperties;

  public ReactSearchResultPage(Hooks hooks, ApplicationProperties applicationProperties) {
    this.hooks = hooks;
    this.applicationProperties = applicationProperties;
    PageFactory.initElements(this.hooks.getDriver(), this);
  }

  @FindBy(how = How.CSS, using = "a[href='/search/advanced?q=react&type=Repositories']")
  private WebElement advancedSearch;

  public void clickAdvancedSearch() {
    advancedSearch.click();
  }

  public boolean isReactSearchResultsPage() {
    return ((hooks
        .getDriver()
        .getCurrentUrl()
        .contains(applicationProperties.getReactSearchResultUrl())));
  }
}
