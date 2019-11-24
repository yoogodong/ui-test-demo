package framework.pageobject.javsdoc8;

import framework.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Index extends PageBase {


    @FindBy(xpath = "//li[@class='navBarCell1Rev']")
    private WebElement currentNavItem;



    public void leftTopFrame() {
        driver.switchTo().frame("packageListFrame");
    }

    public void leftBottomFrame() {
        driver.switchTo().frame("packageFrame");
    }

    public void rightFrame() {
        driver.switchTo().frame("classFrame");
    }

    public void choosePackage(String s) {
        driver.switchTo().parentFrame();
        leftTopFrame();
        push(By.linkText(s), WebElement::click);
    }

    public void chooseClass(String cls) {
        driver.switchTo().parentFrame();
        leftBottomFrame();
        push(By.linkText(cls), WebElement::click);
    }

    public String getCurrentNavItem() {
        driver.switchTo().parentFrame();
        rightFrame();
        return (String) pull(currentNavItem, WebElement::getText);
    }
}
