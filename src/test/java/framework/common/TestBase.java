package framework.common;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 测试失败后重试
     */
    @Rule
    public TestRule rule = new TestRule() {
        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    for (int i = 1; ; i++) {
                        if (i > 1) log.warn("目前执行第{}次", i);
                        try {
                            base.evaluate();
                            break;
                        } catch (Throwable throwable) {
                            //重试限制值
                            final int repeatTimes = 1;
                            if (i == repeatTimes) {
                                throw throwable;
                            }
                            log.warn("测试失败，可以重试{}次", i - 1);
                        }
                    }
                }
            };
        }
    };
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
            WebUtil.implicitWait(driver);
        }

        @Override
        protected void after() {
            log.info("关闭浏览器");
            driver.quit();
        }
    };


}
