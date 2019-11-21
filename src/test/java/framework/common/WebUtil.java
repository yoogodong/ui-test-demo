package framework.common;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class WebUtil {

    private static Logger log = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 如何想对元素调用有返回值的方法
     */
    public static Object pull(WebDriver driver, WebElement webElement, Function<WebElement, Object> action) {
        implicitWait0(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Object[] result = new Object[1];
        final int[] times = new int[1];
        wait.ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class).until(webDriver -> {
            log.info("尝试执行{}次", times[0] = times[0] + 1);
            result[0] = action.apply(webElement);
            times[0] = 0;
            return true;
        });
        implicitWait(driver);
        return result[0];
    }

    /**
     * 调用元素没有返回值的方法
     */
    public static void push(WebDriver driver, WebElement webElement, Consumer<WebElement> action) {
        implicitWait0(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        final int[] times = new int[1];
        wait.ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class).until(webDriver -> {
            log.info("尝试执行{}次", times[0] = times[0] + 1);
            action.accept(webElement);
            times[0] = 0;
            return true;
        });
        implicitWait(driver);
    }

    /**
     * 设置隐式等待
     */
    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * 取消隐式等待
     */
    public static void implicitWait0(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
}
