package framework.cases;

import framework.common.TestBase;
import framework.pageobject.javsdoc8.Index;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class JavaDoc8Test extends TestBase {

    private Index index;
    private final Logger log = LoggerFactory.getLogger(JavaDoc8Test.class);

    @Before
    public void setUp() throws Exception {
        index = PageFactory.initElements(driver, Index.class);
        index.open();
    }

    @Test
    public void could_find_Applet_in_applet_package() {
        index.choosePackage("java.applet");
        index.chooseClass("Applet");
        assertEquals("CLASS", index.getCurrentNavItem());
    }
}
