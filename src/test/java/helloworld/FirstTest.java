package helloworld;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;


/**
 * 通过这个示例演示：UI 测试的核心构成元素
 * 1. 启动浏览器
 * 2. 定位元素
 * 3. 与元素交互
 * 4. 什么时候要刷新 dom
 * 5. 验证什么
 */
public class FirstTest {
    @Test
    public void search_by_baidu() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://baidu.com");
        driver.findElement(By.id("kw")).sendKeys("香港");

        driver.findElement(By.id("su")).click();

        driver.navigate().refresh();
        String title = driver.getTitle();
        assertEquals("香港_百度搜索", title);
        driver.quit();
    }

}
