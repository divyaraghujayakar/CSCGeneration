package com.csc.automation.test.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("target.application")

@Component
public class ApplicationProperties {
  @Setter @Getter private String baseUrl;

  @Setter @Getter private String githubSearchUrl;

  @Setter @Getter private String reactSearchResultUrl;

  @Setter @Getter private String advancedSearchUrl;

  @Setter @Getter private String repoPageUrl;

  @Setter @Getter private String readmePageUrl;
}
