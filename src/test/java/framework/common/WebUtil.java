package framework.common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class WebUtil {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> threadWait = new ThreadLocal<>();
    private static Logger log = LoggerFactory.getLogger(WebUtil.class);

    public static WebDriver getWebDriver() {
        if (threadDriver.get() == null) {
            threadDriver.set(chrome());
        }
        return threadDriver.get();
    }

    //    todo: 不同浏览器的方法
    private static WebDriver chrome() {
        System.setProperty("webdriver.chrome.driver", "/Users/ygdong/CommandAlias/chromedriver");
        return new ChromeDriver();
    }

    public static WebDriverWait getWait() {
        if (threadWait.get() == null) {
            threadWait.set(new WebDriverWait(getWebDriver(), 20));
        }
        return threadWait.get();
    }


    /**
     * 设置隐式等待
     */
    public static void implicitWait() {
        final int time = 5;
        final TimeUnit unit = TimeUnit.SECONDS;
        getWebDriver().manage().timeouts().implicitlyWait(time, unit);
        log.info("隐式等待设置为 {} {}", time, unit);
    }

    /**
     * 取消隐式等待
     */
    public static void implicitWait0() {
        getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        log.info("隐式等待设置为 0 SECONDS");
    }

    public static void takeScreenShot() {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            in = new FileInputStream(screenShot);
            byte[] buffer = new byte[(int) screenShot.length()];
            in.read(buffer);

            String dir = "./screenshot/";
            new File(dir).mkdirs();
            out = new FileOutputStream(dir + LocalDateTime.now() + ".png");
            out.write(buffer);
        } catch (IOException e) {
            log.warn("截屏出错", e);
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                log.info("不能关闭文件流", e);
            }
        }
    }

}
