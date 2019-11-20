package framework.pageobject.baidu;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class IndexPage {

    @FindBy(how = How.ID, using = "kw")
    private WebElement kw;

    @FindBy(id = "su")
    private WebElement su;

    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String what) {
        kw.sendKeys(what);
        su.submit();
    }
}
