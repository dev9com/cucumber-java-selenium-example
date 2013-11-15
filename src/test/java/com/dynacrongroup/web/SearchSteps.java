package com.dynacrongroup.web;

import com.dynacrongroup.page.SearchQueryPage;
import com.dynacrongroup.page.SearchResultPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Fail.fail;

/**
 * User: yurodivuie
 * Date: 11/15/13
 * Time: 9:01 AM
 */
public class SearchSteps {

    private WebDriver driver;
    private Object currentPage;
    private String termEntered = "";

    @Before({"@requires_browser"})
    public void buildDriver() {
        driver = new FirefoxDriver();
    }

    @After({"@requires_browser"})
    public void destroyDriver() {
        driver.quit();
    }

    @Given("^A Google search page$")
    public void A_Google_search_page() throws Throwable {
        currentPage = SearchQueryPage.loadUsing(driver);
    }

    @When("^I enter the search term \"([^\"]*)\"$")
    public void I_enter_the_search_term(String term) throws Throwable {
        verifyCurrentPage(SearchQueryPage.class);
        ((SearchQueryPage) currentPage).setQuery(term);
        termEntered = term;
    }

    @And("^I submit the search by pressing \"([^\"]*)\"$")
    public void I_submit_the_search_by_pressing(String submitType) throws Throwable {
        verifyCurrentPage(SearchQueryPage.class);
        switch (submitType.toLowerCase()) {
            case "enter":
            case "enter key":
                currentPage = ((SearchQueryPage) currentPage).pressEnterInQuery();
                break;
            case "search":
            case "google search":
            case "google search button":
            case "search button":
                currentPage = ((SearchQueryPage) currentPage).clickSearchButton();
                break;
            case "i'm feeling lucky button":
            case "i'm feeling lucky":
            case "lucky":
            case "lucky button":
                currentPage = ((SearchQueryPage) currentPage).clickLuckyButton();
                break;
        }
    }

    @Then("^The search result page title should contain the search term")
    public void The_search_result_page_title_should_contain_the_word() throws Throwable {
        verifyCurrentPage(SearchResultPage.class);
        String termInTitle = ((SearchResultPage) currentPage).getTermFromTitle();
        assertThat(termInTitle).contains(termEntered);
    }

    private void verifyCurrentPage(Class pageClass) {
        if (!currentPage.getClass().equals(pageClass)) {
            fail(
                    String.format("Expected current page to have type %s - actual type is %s",
                            pageClass.getSimpleName(),
                            currentPage.getClass().getSimpleName())
            );
        }
    }
}
