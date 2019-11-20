package framework.pageobject.baidu;

import framework.common.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage {

    @FindBy(partialLinkText = "下一页")
    private WebElement nextPage;

    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void nextPage() {
        Util.push(driver, nextPage, element -> element.click());
    }


}
