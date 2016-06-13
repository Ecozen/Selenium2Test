package seleniumide.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

@SuppressWarnings("deprecation")
public class TestNG2SeleniumRC {
	
	private Selenium selenium;

	@BeforeTest
	@Parameters({"testEnv"})
	public void setUp(String testEnv) throws Exception {
		selenium = new DefaultSelenium(testEnv, 5555, "*firefox", "http://www.baidu.com/");
		selenium.start();
	}

	@Test
	public void testSelenium2Junit_RC() throws Exception {
		selenium.open("/");
		selenium.click("id=kw");
		selenium.type("id=kw", "selenium webdriver");
		selenium.click("css=div.s_form");
		selenium.click("id=su");
		selenium.waitForPageToLoad("30000");
		assertTrue("selenium webdriver_百度搜索".equals(selenium.getTitle()));
		selenium.open("/s?word=selenium%20webdriver&tn=39015028_hao_pg&ie=utf-8");
		selenium.click("link=Selenium Webdriver - Selenium教程");
		assertTrue("Selenium Webdriver - Selenium教程".equals(selenium.getTitle()));
	}

	@AfterTest
	public void tearDown() throws Exception {
//		selenium.stop();
	}


}

