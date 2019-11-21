package framework.pageobject.baidu;

import framework.common.WebUtil;
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
        WebUtil.push(driver, nextPage, WebElement::click);
    }


}
