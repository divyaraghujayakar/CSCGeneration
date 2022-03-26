package com.csc.automation.test.pages;

import com.csc.automation.test.config.ApplicationProperties;
import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import com.csc.automation.test.runners.Hooks;

@Component
@ScenarioScope
@Slf4j
public class RepoPage implements BasePage {

      ApplicationProperties applicationProperties;
      Hooks hooks;


      public RepoPage(ApplicationProperties applicationProperties, Hooks hooks) {
            this.applicationProperties = applicationProperties;
            this.hooks = hooks;
            PageFactory.initElements(this.hooks.getDriver(), this);
      }





      @FindBy(how= How.LINK_TEXT,using="README.md")
       private WebElement readMeFileLink;

      @FindBy(how = How.ID,using = "readme")
      private WebElement readMeFileFrame;

      public boolean isRepoPageRequired(){
            return hooks.getDriver().getCurrentUrl().contains(applicationProperties.getRepoPageUrl());
      }

      public void openReadMeFile(){
            readMeFileLink.click();
      }

      public boolean isReadMeFile(){
            return hooks.getDriver().getCurrentUrl().contains(applicationProperties.getReadmePageUrl());
      }

      public boolean printCharacters(int numberOfCharacters){
            String readMeData = (String) readMeFileFrame.getText().subSequence(0,numberOfCharacters);
            System.out.println("Required number of characters: " +numberOfCharacters);
            System.out.println("Actual number of characters printed: " +readMeData.length());
            System.out.println(readMeData);
            return readMeData.length()==numberOfCharacters;

      }

}
