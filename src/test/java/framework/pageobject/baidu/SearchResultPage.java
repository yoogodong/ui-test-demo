package framework.pageobject.baidu;

import framework.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SearchResultPage extends PageBase {


    @FindBy(partialLinkText = "下一页")
    private WebElement nextPage;
    private Logger log = LoggerFactory.getLogger(SearchResultPage.class);

    /**
     * 点击下一页，然后刷新 dom
     */
    public void nextPage() {
        log.info("点击下一页");
        new Actions(driver).moveToElement(nextPage).click().perform();
        driver.navigate().refresh();

    }

    /**
     * 点击搜索结果页面中的指定关键字的链接
     */
    public void openLinkWithText(String keyWorlds) {
        while (true) {
            List<WebElement> links = driver.findElements(By.partialLinkText(keyWorlds));
            if (links.size() != 0) {
                //注意这里，直接 webElement.click() 会 因为浏览器滚动条没有在合适的区域而抛出 ElementClickInterceptedException
                new Actions(driver).moveToElement(links.get(0)).click().perform();
                break;
            } else {
                nextPage();
            }
        }
    }


    public String title() {
        return driver.getTitle();
    }
}
