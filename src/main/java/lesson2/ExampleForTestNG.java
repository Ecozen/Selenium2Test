package lesson2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExampleForTestNG {
	
	static WebDriver driver;
	@BeforeClass
	public static void setup(){
		System.out.println("init...");
//		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void test(){
    	// 让浏览器访问 zTree Demo
    	driver.get("http://www.ztree.me/v3/demo/cn/core/standardData.html");
    	
    	// 等待 zTree 初始化完毕，Timeout 设置10秒
    	(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver d) {
    			WebElement element = driver.findElement(By.id("treeDemo"));
    			return element.findElement(By.tagName("a")) != null;
    		}
    	});
    	
    	WebElement element = driver.findElement(By.id("treeDemo"));
    	List<WebElement> elements = element.findElements(By.tagName("li"));
    	// 显示生成的节点DOM 数量
    	System.out.println("treeNode DOM length = " + elements.size());
	}
	
	@AfterClass
	public static void destory(){
		System.out.println("destory...");
		driver.quit();
	}

}
