package framework.common;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过定义测试基类，可以通过 @Rule 来控制测试，比如失败后重试
 */
public class TestBase {

    private final Logger log = LoggerFactory.getLogger(TestBase.class);
    /**
     * driver 变量是方法 scope ,对于 junit4 来说也意味着测试类实例 scope, 也是 Thread scope
     */
    protected WebDriver driver;
    /**
     * 这里的目的仅仅是为了在测试类中，可以直接访问 driver， 而不必再经过 WebUtil
     */
    private ExternalResource resource = new ExternalResource() {

        @Override
        protected void before() throws Throwable {
            log.info("打开浏览器");
            driver = WebUtil.getWebDriver();
        }

        @Override
        protected void after() {
            log.info("关闭浏览器");
            driver.quit();
        }
    };


    /**
     * 测试失败后重试
     */
    private TestRule retry = (base, description) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            for (int i = 1; ; i++) {
                if (i > 1) {
                    log.warn("{}::{} 运行失败， 进行第{}次尝试", description.getClassName(), description.getMethodName(), i);
                }
                try {
                    base.evaluate();
                    break;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    //重试限制值
                    final int repeatTimes = 1;
                    if (i == repeatTimes) {
                        WebUtil.takeScreenShot();
                        throw throwable;
                    }
                }
            }
        }
    };


    /**
     * 这里来编排若干过 Rule 的顺序
     */
    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(resource).around(retry);
}
