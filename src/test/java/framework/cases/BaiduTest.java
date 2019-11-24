package framework.cases;

import framework.common.TestBase;
import framework.pageobject.baidu.IndexPage;
import framework.pageobject.baidu.SearchResultPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class BaiduTest extends TestBase {

    private IndexPage indexPage;
    private SearchResultPage searchResultPage;

    @Before
    public void setUp() throws Exception {
        log.info("初始化页面对象");
        indexPage = PageFactory.initElements(driver, IndexPage.class);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
    }

    @Test
    public void search_result_should_has_specific_title() {
        indexPage.open();
        indexPage.search("香港");
        assertEquals("香港_百度搜索", searchResultPage.title());
    }

    @Test
    public void openOtherPage() {
        search_result_should_has_specific_title();
        searchResultPage.openLinkWithText("豆瓣");
    }
}
