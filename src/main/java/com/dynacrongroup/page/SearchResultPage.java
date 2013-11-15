package com.dynacrongroup.page;

import com.dynacrongroup.util.TestConf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: yurodivuie
 * Date: 11/15/13
 * Time: 8:19 AM
 */
public class SearchResultPage {

    private static final TestConf TEST_CONF = TestConf.get();
    private static final Logger LOG = LoggerFactory.getLogger(SearchResultPage.class);

    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TEST_CONF.getAjaxWaitSeconds()), this);
        new WebDriverWait(driver, TEST_CONF.getAjaxWaitSeconds())
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search li")));
    }

    public String getTermFromTitle() {
        String title = driver.getTitle();
        LOG.info(title);
        return title.substring(0, title.indexOf(" - "));
    }





}
