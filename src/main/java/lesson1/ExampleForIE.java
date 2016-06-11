package lesson1;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExampleForIE {
	public static void main(String[] args) throws IOException {

    	DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
    	ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    	System.setProperty("webdriver.ie.driver", "F:\\tools\\Selenium\\IEDriverServer_x64_2.41.0\\IEDriverServer.exe");
    	WebDriver driver = new InternetExplorerDriver(ieCapabilities);
        // 让浏览器访问 Baidu
//        driver.get("http://www.baidu.com");
        // 用下面代码也可以实现
         driver.navigate().to("http://www.baidu.com");

        // 获取 网页的 title
        System.out.println("1 Page title is: " + driver.getTitle());

        // 通过 id 找到 input 的 DOM
        WebElement element = driver.findElement(By.id("kw"));

        // 输入关键字
        element.sendKeys("zTree");

        // 提交 input 所在的  form
        element.submit();
        
        // 通过判断 title 内容等待搜索页面加载完毕，间隔10秒
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("ztree");
            }
        });

        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());
        
        //关闭浏览器
        driver.quit();
    }
}
