package lesson2;

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

public class ExampleJS {
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
    	(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver d) {
    			WebElement element = driver.findElement(By.id("treeDemo"));
    			return element.findElement(By.tagName("a")) != null;
    		}
    	});
    	
    	System.out.println("start...javascript");

//    	String name =(String) ((JavascriptExecutor)driver).executeScript("return $.fn.zTree.getZTreeObj('treeDemo').getNodes()[0].name;");
//    	Long rootCount = (Long) ((JavascriptExecutor)driver).executeScript("return $.fn.zTree.getZTreeObj('treeDemo').getNodes().length;");
    	
    	//直接 var 定义得到的对象并不是全局对象，这么执行会出错的
//    	((JavascriptExecutor)driver).executeScript("var zTreeObj = $.fn.zTree.getZTreeObj('treeDemo');");
//    	String name =(String) ((JavascriptExecutor)driver).executeScript("return zTreeObj.getNodes()[0].name;");
//    	Long rootCount = (Long) ((JavascriptExecutor)driver).executeScript("return zTreeObj.getNodes().length;");

    	//利用 window 实现全局对象
    	((JavascriptExecutor)driver).executeScript("window.zTreeObj = $.fn.zTree.getZTreeObj('treeDemo');");
    	String name =(String) ((JavascriptExecutor)driver).executeScript("return window.zTreeObj.getNodes()[0].name;");
    	Long rootCount = (Long) ((JavascriptExecutor)driver).executeScript("return window.zTreeObj.getNodes().length;");
    	
    	// 显示js 的结果
    	System.out.println("treeObj[0].name = " + name);
    	System.out.println("root nodes count = " + rootCount);
    	

    }
    
    @AfterClass
    public static void destory() {
    	System.out.println("destory...");
    	//关闭浏览器
    	driver.close();
    }
}
