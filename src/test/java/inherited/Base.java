package inherited;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Base {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * driver 实例的 scope 是方法,对于 junit4 来说也意味着测试类实例 scope
     */
    protected WebDriver driver;
    protected WebDriverWait wait;


    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            log.info("打开浏览器");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
            implicitWait();
        }

        @Override
        protected void after() {
            log.info("关闭浏览器");
            driver.quit();
        }
    };

    /**
     * 设置隐式等待
     */
    protected void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     * 取消隐式等待
     */
    protected void implicitWait0() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * 反复重试，对元素执行某个动作， 直到成功或者超时
     */
    protected void perform(By by, Consumer<WebElement> action) {
        final int[] times = new int[1];
        wait.ignoring(StaleElementReferenceException.class).ignoring(ElementClickInterceptedException.class).until(webDriver -> {
            WebElement element = driver.findElement(by);
            log.info("尝试执行{}次", times[0] = times[0] + 1);
            action.accept(element);
            times[0] = 0;
            return true;
        });

//        wait.until()

    }
}
