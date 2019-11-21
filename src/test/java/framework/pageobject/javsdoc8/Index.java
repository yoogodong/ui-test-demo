package framework.pageobject.javsdoc8;

import framework.common.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Index {

    private WebDriver driver;

    @FindBy(xpath = "//li[@class='navBarCell1Rev']")
    private WebElement currentNavItem;


    public Index(WebDriver driver) {
        this.driver = driver;
    }

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
        WebUtil.push(driver, By.linkText(s), WebElement::click);
    }

    public void chooseClass(String cls) {
        driver.switchTo().parentFrame();
        leftBottomFrame();
        WebUtil.push(driver, By.linkText(cls), WebElement::click);
    }

    public String getCurrentNavItem() {
        driver.switchTo().parentFrame();
        rightFrame();
        return (String) WebUtil.pull(driver, currentNavItem, WebElement::getText);
    }
}
