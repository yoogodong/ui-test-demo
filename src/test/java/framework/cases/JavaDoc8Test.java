package framework.cases;

import framework.common.TestBase;
import framework.pageobject.javsdoc8.Index;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class JavaDoc8Test extends TestBase {

    private Index index;

    @Before
    public void setUp() throws Exception {
        index = PageFactory.initElements(driver, Index.class);
        driver.get("file:///Users/ygdong/Documents/jdk-8u211-docs-all/api/index.html");
    }

    @Test
    public void could_find_Applet_in_applet_package() {
        index.choosePackage("java.applet");
        index.chooseClass("Applet");
        String navItem = index.getCurrentNavItem();
        assertEquals("CLASS", navItem);
    }
}
