package helloworld;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * 通过这个示例演示最核心的API：
 * 1. 切换到通过链接打开的新窗口
 * 2. findElement/findElements 的区别
 */
public class ThirdTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("关闭浏览器");
        driver.quit();
    }

    @Test
    public void could_navigate_to_zhihu() throws InterruptedException {
        driver.get("http://baidu.com");
        driver.findElement(By.id("kw")).sendKeys("香港");
        driver.findElement(By.id("su")).click();
        while (true) {
            driver.navigate().refresh();
            List<WebElement> link = driver.findElements(By.partialLinkText("香港八一"));
            if (link.size() != 0) {
                new Actions(driver).moveToElement(link.get(0)).click().perform();
                break;
            } else {
                driver.findElement(By.partialLinkText("下一页")).click();
            }
        }

        driver.close();
        driver.switchTo().window(
                driver.getWindowHandles().toArray(new String[]{})[0]);
        driver.navigate().refresh();
        assertEquals("香港八一_百度百科", driver.getTitle());
    }


}
