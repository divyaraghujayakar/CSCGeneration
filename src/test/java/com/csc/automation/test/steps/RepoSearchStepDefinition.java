
package com.csc.automation.test.steps;

        import com.csc.automation.test.config.ApplicationProperties;
        import com.csc.automation.test.runners.Hooks;
        import io.cucumber.datatable.DataTable;
        import io.cucumber.java.en.And;
        import io.cucumber.java.en.Given;
        import io.cucumber.java.en.Then;
        import io.cucumber.java.en.When;
        import io.cucumber.spring.ScenarioScope;
        import lombok.extern.slf4j.Slf4j;
        import org.bouncycastle.tsp.TSPUtil;
        import org.junit.Assert;
        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import com.csc.automation.test.pages.*;

        import java.util.List;
        import java.util.Map;

@ScenarioScope
@Slf4j
@EnableConfigurationProperties(ApplicationProperties.class)


public class RepoSearchStepDefinition {

    public RepoSearchStepDefinition(SearchPage searchPage, ReactSearchResultPage reactSearchResultPage, AdvancedSearchPage advancedSearchPage,
                             AdvancedSearchResultPage advancedSearchResultPage, RepoPage repoPage, Hooks hooks) {
        this.searchPage = searchPage;
        this.reactSearchResultPage = reactSearchResultPage;
        this.advancedSearchPage = advancedSearchPage;
        this.advancedSearchResultPage = advancedSearchResultPage;
        this.repoPage = repoPage;
        this.hooks = hooks;
    }

    SearchPage searchPage;
    ReactSearchResultPage reactSearchResultPage;
    AdvancedSearchPage advancedSearchPage;
    AdvancedSearchResultPage advancedSearchResultPage;
    RepoPage repoPage;
    Hooks hooks;

    @Given("user is on git search Page")
    public void user_is_on_git_search_page() {
        searchPage.goToSearchPage();




        Assert.assertTrue(searchPage.isGitHubSearchPage());
    }
    @When("user enters {string} in search box and hits enter")
    public void user_enters_in_search_box_and_hits_enter(String searchString) {

        searchPage.enterSearchText(searchString);

    }
    @When("user is on results page and clicks on advanced search link")
    public void user_is_on_results_page_and_clicks_on_advanced_search_link() {
        Assert.assertTrue(reactSearchResultPage.isReactSearchResultsPage());
        reactSearchResultPage.clickAdvancedSearch();

    }

    @Then("User should see only {int} repo result that reads {string}")
    public void user_should_see_only_repo_result_that_reads(Integer numberOfResults, String repoName) {
        advancedSearchResultPage.isOnlyOneRepoListed(numberOfResults);
        advancedSearchResultPage.isNameMvolskovDecider(repoName);
        advancedSearchResultPage.clickOnRepo();
    }
    @Then("user should be able to print first {int} characters of the readme.md file of the repository")
    public void user_should_be_able_to_print_first_characters_of_the_readme_md_file_of_the_repository(Integer numberOfCharacters) {
        Assert.assertTrue( "User is not on required repo page", repoPage.isRepoPageRequired());
        repoPage.openReadMeFile();
        Assert.assertTrue("User is not on readme.MD page",repoPage.isReadMeFile());
        Assert.assertTrue("The number of characters printed is not 300",repoPage.printCharacters(numberOfCharacters));
    }



    @When("user searches for repo in advanced search page")
    public void user_searches_for_repo_in_advanced_search_page(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> searchCriteria = dataTable.asMaps(String.class, String.class);
            advancedSearchPage.enterAdvancedSearchCriteria(searchCriteria);
        }
    }



