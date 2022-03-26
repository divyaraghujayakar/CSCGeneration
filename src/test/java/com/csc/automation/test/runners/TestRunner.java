package com.csc.automation.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/",
    plugin = {
      "pretty",
      "html:results/cucumber-reports.html",
      "json:results/cucumber.json",
      "junit:results/cucumber.xml",
      "timeline:results/timeline"
    },
    glue = {
      "com/csc/automation/test",  "src/test/java/com/csc/automation/test/runners"

    })

@CucumberContextConfiguration
@SpringBootTest
public class TestRunner {}
