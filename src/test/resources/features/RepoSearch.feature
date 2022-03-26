Feature: Git Search

  Scenario: Search git for react app and print the readme file's first 300 characters
    Given user is on git search Page
    When  user enters 'react' in search box and hits enter
    And user is on results page and clicks on advanced search link
    And user searches for repo in advanced search page
    |Language|State|Stars|Followers|License|
    |JavaScript|closed|>45|>50|Boost Software License 1.0|
    Then User should see only 1 repo result that reads 'mvoloskov/decider'
    And user should be able to print first 300 characters of the readme.md file of the repository

