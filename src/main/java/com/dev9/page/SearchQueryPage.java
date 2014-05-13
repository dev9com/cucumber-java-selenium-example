package com.dev9.page;

import com.dev9.util.TestConf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * User: yurodivuie
 * Date: 11/15/13
 * Time: 8:19 AM
 */
public class SearchQueryPage {

    private static final TestConf TEST_CONF = TestConf.get();

    private final WebDriver driver;

    @FindBy(css = "input[name=q]")
    WebElement query;

    @FindBy(css = "input[value=\"Google Search\"]")
    WebElement searchButton;

    @FindBy(css = "input[value=\"I'm Feeling Lucky\"]")
    WebElement luckyButton;

    public SearchQueryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TEST_CONF.getAjaxWaitSeconds()), this);
    }

    public static SearchQueryPage loadUsing( WebDriver driver) {
        driver.get(TEST_CONF.getSearchUrl());
        return new SearchQueryPage(driver);
    }

    public SearchQueryPage setQuery(String term) {
        query.clear();
        query.sendKeys(term);
        return this;
    }

    public SearchResultPage pressEnterInQuery() {
        query.sendKeys("\n");
        return new SearchResultPage(driver);
    }

    public SearchResultPage clickSearchButton() {
        searchButton.click();
        return new SearchResultPage(driver);
    }

    public SearchResultPage clickLuckyButton() {
        luckyButton.click();
        return new SearchResultPage(driver);
    }


}
