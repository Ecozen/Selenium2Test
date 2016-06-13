package seleniumide.testng;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium2TestNG {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.baidu.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelenium2TestNG() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("kw")).clear();
    driver.findElement(By.id("kw")).sendKeys("selenium webdriver");
    driver.findElement(By.id("su")).click();
    assertEquals(driver.getTitle(), "selenium webdriver_百度搜索");
    driver.findElement(By.linkText("Selenium Webdriver - Selenium教程")).click();
    String currentWindow = driver.getWindowHandle();
    Set<String> windowHandles = driver.getWindowHandles();
    Iterator<String> it = windowHandles.iterator();
    while (it.hasNext()) {
		String handle = it.next();
		if (currentWindow.equals(handle)) {
			continue;
		}
		driver.switchTo().window(handle);
	}
    assertTrue("Selenium Webdriver - Selenium教程".equals(driver.getTitle()));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
