package framework.pageobject.baidu;

import framework.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SearchResultPage extends PageBase {


    @FindBy(partialLinkText = "下一页")
    private WebElement nextPage;
    private Logger log = LoggerFactory.getLogger(SearchResultPage.class);


    public void nextPage() {
        log.info("点击下一页");
        push(nextPage, WebElement::click);
    }


    public void openLinkWithText(String keyWorlds) {
        while (true) {
            List<WebElement> links = driver.findElements(By.partialLinkText(keyWorlds));
            if (links.size() != 0) {
                push(links.get(0), element -> new Actions(driver).moveToElement(element).click().perform());
                links.get(0);
                break;
            } else {
                nextPage();
                driver.navigate().refresh();
            }
        }
    }


    public String title() {
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("百度一下，你就知道")));
        return driver.getTitle();
    }
}
