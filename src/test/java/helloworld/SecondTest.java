package helloworld;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


/**
 * 通过这个示例演示最核心的API：
 * 1. 隐式等待和显式等待
 * 2. 切换到通过链接打开的新窗口
 * 3. findElement/findElements 的区别
 */
public class SecondTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("su")));
        driver.findElement(By.id("su")).click();
        while (true) {
            List<WebElement> link = driver.findElements(By.partialLinkText("豆瓣同城"));
            if (link.size() != 0) {
                link.get(0).click();
                break;
            } else {
                driver.findElement(By.partialLinkText("下一页")).click();
            }
        }

        driver.close();
        driver.switchTo().window(
                driver.getWindowHandles().toArray(new String[]{})[0]);
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
        assertEquals("豆瓣同城_中国香港", driver.getTitle());
    }


}
