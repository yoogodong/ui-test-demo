package framework.cases;

import framework.common.TestBase;
import framework.pageobject.baidu.IndexPage;
import framework.pageobject.baidu.SearchResultPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class BaiduTest extends TestBase {

    private final Logger log = LoggerFactory.getLogger(BaiduTest.class);
    private IndexPage indexPage;
    private SearchResultPage searchResultPage;

    @Before
    public void setUp() throws Exception {
        log.info("初始化页面对象");
        indexPage = PageFactory.initElements(driver, IndexPage.class);
        searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        indexPage.open();
    }

    @Test
    public void search_result_should_has_specific_title() {
        indexPage.search("香港");
        assertEquals("香港_百度搜索", searchResultPage.title());
    }

    /**
     * 这里演示了测试用例之间如果存在依赖关系，直接采用方法调用的方式处理
     */
    @Test
    public void openOtherPage() {
        search_result_should_has_specific_title();
        searchResultPage.openLinkWithText("豆瓣");
    }

    /**
     * 此方法仅仅为了观察测试的稳定性
     */
    @Test
    public void nextPageIsRobust() {
        search_result_should_has_specific_title();
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
            searchResultPage.nextPage();
        }
    }
}
