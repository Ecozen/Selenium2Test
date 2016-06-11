package lesson3;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExampleForAjax {
    static WebDriver driver;
    
    @BeforeClass
    public static void init() {
    	System.out.println("init...");
    	// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
//    	System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
    	// 创建一个 FireFox 的浏览器实例
    	driver = new FirefoxDriver();
    }
    
    @Test
    public void test() {
    	// 让浏览器访问 zTree Demo
    	driver.get("http://www.ztree.me/v3/demo/cn/core/standardData.html");
    	
    	// 等待 zTree 初始化完毕，Timeout 设置10秒
    	try {
    		(new WebDriverWait(driver, 5, 500)).until(new ExpectedCondition<Boolean>() {
    			public Boolean apply(WebDriver d) {
    				//findElement 会导致程序锁死
    				WebElement element = driver.findElement(By.id("treeDemo111"));
    				
//    				WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('#treeDemo111');");
    				return element != null;
    			}
    		});
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	try {
    		//findElement 会导致程序锁死
//    		WebElement element = driver.findElement(By.id("treeDemo111"));
    		
    		List<WebElement> elementList =(List<WebElement>) driver.findElements(By.id("treeDemo111"));
    		System.out.println(elementList.size());
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	try {
    		//findElement 会导致程序锁死
//    		WebElement element = driver.findElement(By.id("treeDemo111"));
    		
    		WebElement element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('#treeDemo111');");
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    @AfterClass
    public static void destory() {
    	System.out.println("destory...");
    	//关闭浏览器
    	driver.quit();
    }
}
