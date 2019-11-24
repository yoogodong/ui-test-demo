package framework.common;

import org.openqa.selenium.WebDriver;

/**
 * 所有页面对象的基类,目前仅仅是为了便于在页面对象中直接访问 driver
 * 测试方法 scope
 */
public class PageBase {

    protected WebDriver driver;

    public PageBase() {
        driver = WebUtil.getWebDriver();
    }
}
