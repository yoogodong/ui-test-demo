package inherited;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * 通过这个示例演示：
 * 1. 采用继承来保证 webdriver 可维护
 * 2. JUnit4 继承的关键问题
 */
public class Fourth extends Base {

    @Test
    public void could_navigate_to_zhihu() throws InterruptedException {
        driver.get("http://baidu.com");
        driver.findElement(By.id("kw")).sendKeys("香港");
        driver.findElement(By.id("su")).click();
        int page = 1;
        while (true) {
            log.info("进入第{}页", page);
            List<WebElement> link = driver.findElements(By.partialLinkText("豆瓣同城"));
            if (link.size() != 0) {
                perform(By.partialLinkText("豆瓣同城"), element -> element.click());
                break;
            } else {
                log.info("点击{}页", ++page);
                perform(By.partialLinkText("下一页"), element -> element.click());
            }
        }
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().toArray(new String[]{})[0]);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
        assertEquals("豆瓣同城_中国香港", driver.getTitle());
    }

    @Test
    public void test1() {
//        driver
    }
}
