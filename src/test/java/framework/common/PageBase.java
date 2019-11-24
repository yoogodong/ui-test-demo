package framework.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 所有页面对象的基类
 * 测试方法 scope, 也就是 Tread scope
 */
public class PageBase {

    private final Logger log = LoggerFactory.getLogger(PageBase.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase() {
        driver = WebUtil.getWebDriver();
        wait = WebUtil.getWait();
    }


    /**
     * 如何想对元素调用有返回值的方法
     */
    public Object pull(WebElement webElement, Function<WebElement, Object> action) {
        Object[] result = new Object[1];
        final int[] count = new int[1];
        waitWithIgnoring().until(webDriver -> {
            if ((count[0] = count[0] + 1) > 1)
                log.info("尝试执行{}次", count[0]);
            result[0] = action.apply(webElement);
            count[0] = 0;
            return true;
        });
        return result[0];
    }

    /**
     * 如何想对元素调用有返回值的方法
     */
    public Object pull(By by, Function<WebElement, Object> action) {
        Object[] result = new Object[1];
        final int[] count = new int[1];
        waitWithIgnoring().until(webDriver -> {
            if ((count[0] = count[0] + 1) > 1)
                log.info("尝试执行{}次", count[0]);
            WebElement webElement = driver.findElement(by);
            result[0] = action.apply(webElement);
            count[0] = 0;
            return true;
        });
        return result[0];
    }

    /**
     * 调用元素没有返回值的方法
     */
    public void push(WebElement webElement, Consumer<WebElement> action) {
        final int[] count = new int[1];
        waitWithIgnoring().until(webDriver -> {
            if ((count[0] = count[0] + 1) > 1)
                log.info("尝试执行{}次", count[0]);
            action.accept(webElement);
            count[0] = 0;
            return true;
        });
    }

    /**
     * 调用元素没有返回值的方法
     */
    public void push(By by, Consumer<WebElement> action) {
        final int[] count = new int[1];
        waitWithIgnoring().until(webDriver -> {
            if ((count[0] = count[0] + 1) > 1)
                log.info("尝试执行{}次", count[0]);
            WebElement webElement = driver.findElement(by);
            action.accept(webElement);
            return true;
        });
    }

    //todo : 不需要返回？
    private FluentWait<WebDriver> waitWithIgnoring() {
        return wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
    }
}
