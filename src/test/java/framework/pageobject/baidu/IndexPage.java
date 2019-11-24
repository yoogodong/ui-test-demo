package framework.pageobject.baidu;


import framework.common.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexPage extends PageBase {

    private Logger log = LoggerFactory.getLogger(IndexPage.class);

    @FindBy(how = How.ID, using = "kw")
    private WebElement kw;
    private String url = "http://www.baidu.com";

    @FindBy(id = "su")
    private WebElement su;


    /**
     * 搜索关键字，因为页面被结果页面刷新，所以调用 refresh
     */
    public void search(String what) {
        kw.sendKeys(what);
        su.submit();
        driver.navigate().refresh();
    }

    /**
     * 页面的打开不要放在测试用例中，因为url 也应该是页面对象的属性
     */
    public void open() {
        driver.get(url);
    }
}
