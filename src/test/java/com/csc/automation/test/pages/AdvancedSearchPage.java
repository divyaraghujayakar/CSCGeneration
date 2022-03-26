package com.csc.automation.test.pages;

import com.csc.automation.test.runners.Hooks;
import com.csc.automation.test.config.ApplicationProperties;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ScenarioScope
@Slf4j
public class AdvancedSearchPage implements BasePage {

  private final Hooks hooks;
  private final ApplicationProperties applicationProperties;
  private final CommonPage commonPage;

  public AdvancedSearchPage(Hooks hooks, ApplicationProperties applicationProperties, CommonPage commonPage) {
    this.hooks = hooks;
    this.applicationProperties = applicationProperties;
    this.commonPage = commonPage;
    PageFactory.initElements(this.hooks.getDriver(), this);
  }

  public boolean isAdvancedSearchPage() {
    return ((hooks
        .getDriver()
        .getCurrentUrl()
        .contains(applicationProperties.getAdvancedSearchUrl())));
  }

  @FindBy(how = How.ID, using = "search_language")
  private WebElement writtenInLanguageDropdown;

  @FindBy(how = How.ID, using = "search_stars")
  private WebElement withThisManyStarsTextBox;

  @FindBy(how = How.ID, using = "search_state")
  private WebElement inTheStateDropdown;

  @FindBy(how = How.ID, using = "search_license")
  private WebElement withThisLicenseDropdown;

  @FindBy(how = How.ID, using = "search_followers")
  private WebElement withThisManyFollowersTextBox;

  @FindBy(
      how = How.CSS,
      using = "div.container-lg.p-responsive.advanced-search-form  button[type='submit']")
  private WebElement submitButton;


  public void enterAdvancedSearchCriteria(List<Map<String,String>> searchCriteria) {
    Map<String,String> searchParameters = searchCriteria.get(0);
    if (null != searchParameters) {
      commonPage.selectByVisibleText(searchParameters.get("Language"), writtenInLanguageDropdown);
      commonPage.enterData(searchParameters.get("Stars"), withThisManyStarsTextBox);
      commonPage.selectByVisibleText(searchParameters.get("State"), inTheStateDropdown);
      commonPage.selectByVisibleText(searchParameters.get("License"), withThisLicenseDropdown);
      commonPage.enterData(searchParameters.get("Followers"), withThisManyFollowersTextBox);
      submitButton.click();
    } else {
      log.debug("Search Parameters is empty");
    }
  }
}
