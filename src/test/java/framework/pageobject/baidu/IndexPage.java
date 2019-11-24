package framework.pageobject.baidu;


import framework.common.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class IndexPage extends PageBase {

    @FindBy(how = How.ID, using = "kw")
    private WebElement kw;
    private String url = "http://www.baidu.com";

    @FindBy(id = "su")
    private WebElement su;


    public void search(String what) {
        kw.sendKeys(what);
        su.submit();
    }

    public void open() {
        driver.get(url);
    }
}
