package framework.cases;

import framework.pageobject.baidu.IndexPage;
import framework.pageobject.baidu.SearchResultPage;
import inherited.Base;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class WithPageObjectTest extends Base {

    private IndexPage indexPage;
    private SearchResultPage searchResultPage;

    @Before
    public void setUp() throws Exception {
        driver.get("http://www.baidu.com");
        log.info("初始化页面对象");
        indexPage = PageFactory.initElements(driver, IndexPage.class);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
    }

    @Test
    public void name() {
        indexPage.search("香港");
        for (int i = 0; i < 30; i++) {
            searchResultPage.nextPage();
        }
    }
}
