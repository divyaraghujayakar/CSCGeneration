package com.csc.automation.test.pages;

import com.csc.automation.test.config.ApplicationProperties;
import com.csc.automation.test.runners.Hooks;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ScenarioScope
@Slf4j
public class AdvancedSearchResultPage implements BasePage{

    private final Hooks hooks;
    private final ApplicationProperties applicationProperties;
    private final CommonPage commonPage;

    public AdvancedSearchResultPage(Hooks hooks, ApplicationProperties applicationProperties, CommonPage commonPage) {
        this.hooks = hooks;
        this.applicationProperties = applicationProperties;
        this.commonPage = commonPage;
        PageFactory.initElements(this.hooks.getDriver(), this);
    }
    @FindBy(how= How.CSS, using="ul.repo-list")
    private WebElement resultList;

    @FindBy(how= How.CSS, using ="li.repo-list-item a")
    private WebElement repoDetails;




    public boolean isOnlyOneRepoListed(int repoSize){
        List<WebElement> reposListed = resultList.findElements(By.cssSelector("li"));

        return (reposListed.size()==repoSize)?  true: false;
    }

    public boolean isNameMvolskovDecider(String repoName)
    {
        commonPage.highlightElement(repoDetails);
        return ((repoDetails.getText().equalsIgnoreCase(repoName))?true:false);
    }

    public void clickOnRepo(){
        commonPage.clickElement(repoDetails);
    }
}
