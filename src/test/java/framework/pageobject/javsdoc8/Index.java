package framework.pageobject.javsdoc8;

import framework.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 意在演示多个 frame 之间的切换
 */
public class Index extends PageBase {


    private final String URL = "file:///Users/ygdong/Documents/jdk-8u211-docs-all/api/index.html";

    @FindBy(xpath = "//li[@class='navBarCell1Rev']")
    private WebElement currentNavItem;


    private void leftTopFrame() {
        driver.switchTo().frame("packageListFrame");
    }

    private void leftBottomFrame() {
        driver.switchTo().frame("packageFrame");
    }

    private void rightFrame() {
        driver.switchTo().frame("classFrame");
    }

    /**
     * 在兄弟 frame 之间切换，要记得先切换到 parent frame
     */
    private void parentFrame() {
        driver.switchTo().parentFrame();
    }

    public void choosePackage(String s) {
        parentFrame();
        leftTopFrame();
        driver.findElement(By.linkText(s)).click();
    }


    public void chooseClass(String cls) {
        parentFrame();
        leftBottomFrame();
        driver.findElement(By.linkText(cls)).click();
    }

    public String getCurrentNavItem() {
        parentFrame();
        rightFrame();
        return currentNavItem.getText();
    }

    public void open() {
        driver.get(URL);
    }
}
