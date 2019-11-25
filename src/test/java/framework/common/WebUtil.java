package framework.common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 用来将webdriver 绑定到线程 scope, 以能使 测试基类和页面对象基类共享 webdriver
 */
public class WebUtil {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static Logger log = LoggerFactory.getLogger(WebUtil.class);


    /**
     * 将 webdriver 绑定到线程，如果测试以单线程方式运行，则需要检测 webdriver 是否已经 quit
     */
    public static WebDriver getWebDriver() {
        if (threadDriver.get() == null || threadDriver.get().toString().contains("null")) {
            threadDriver.set(chrome());//切换浏览器
        }
        return threadDriver.get();
    }


    private static WebDriver chrome() {
        System.setProperty("webdriver.chrome.driver", "/Users/ygdong/CommandAlias/chromedriver");
        return new ChromeDriver();
    }

    private static WebDriver ie() {
        System.setProperty("webdriver.ie.driver", "C:/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    private static WebDriver firfox() {
        System.setProperty("webdriver.firfox.driver", "");
        return new ChromeDriver();
    }


    /**
     * 获取当前的屏幕快照， 保存在项目目录下的 screenshot 目录下
     */
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
