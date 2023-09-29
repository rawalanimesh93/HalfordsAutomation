package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Base {
	public static Properties prop;
	public static WebDriver driver;
	static long timeout =10;
	static {

		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/resources/env.properties");

			prop = new Properties();
			prop.load(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}}

	@Before
	public void Initialization() {

		String browserName = prop.getProperty("browser");

		switch (browserName) {

		case "chrome":
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--incognito");
			driver = new ChromeDriver(option);
			break;

		case "firefox":
			FirefoxOptions option1 = new FirefoxOptions();
			option1.addArguments("--incognito");
			driver = new FirefoxDriver(option1);
			break;

		}
//		if (browserName.equals("chrome")) {
//			ChromeOptions option = new ChromeOptions();
//			option.addArguments("--incognito");
//			driver = new ChromeDriver(option);
//		} else if (browserName.equals("firefox")) {
//			FirefoxOptions option = new FirefoxOptions();
//			option.addArguments("--incognito");
//			driver = new FirefoxDriver(option);
//
//		}

		driver.get(prop.getProperty("appUrl"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@After
	public void browserClose(Scenario s) throws IOException {

		if (s.isFailed()) { // If failed, Take screenshot

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("Screenshots/" + s.getName() + ".png"));
		}
		driver.quit();
	}
	//Generic methods for code reusability
	public void mouseHover(WebElement ele) {
		Actions act = new Actions (driver);
		act.moveToElement(ele).build().perform();
	}
	
	public void waitForExpectedElement(WebElement ele, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void selectDropdownValue(WebElement ele, String type, String value) {
		Select s = new Select(ele);
		
		switch (type) {
		
		case "index":
			s.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			s.selectByValue(value);
			break;
		case "visibleText":
			s.selectByVisibleText(value);
			break;
		}}
	
	public void clickOnElement(WebElement ele,long timeout) {
		try {
			waitforElementClickable(ele, timeout);
			ele.click();
		}catch(Exception e){
			waitforElementClickable(ele, timeout);
			executorClick(ele);
		}}
	
	public void waitforElementClickable(WebElement ele,long timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void executorClick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void switchFrames(String type, String value) {
		switch (type) {
		
		case "index":
			driver.switchTo().frame(Integer.parseInt(value));
			break;
			
		case "name":
			driver.switchTo().frame(value);
			break;
		}}
	
	public void selectBootStrapDropdown(WebElement e,List<WebElement> list,String expectedValues) {
		clickOnElement(e,10);
		
	for (WebElement ele : list) {
		String actualValue = ele.getText();
		
		if(actualValue.equals(expectedValues)) {
			ele.click();
			break;
		}}}
	
	public void switchWindow(String expTitle) {
	Set<String> allWindowId	= driver.getWindowHandles();
	
	for (String windowID : allWindowId) {
	driver.switchTo().window(windowID);
	
	if(driver.getTitle().contains(expTitle)) {
		break;
		
}}}
	
	public void cleanAndEnterText(WebElement ele, String value) {
		ele.clear();
		ele.sendKeys(value);
	}
	
	public void validateText(WebElement ele, String expectedValue) {
		String acctualvalue = ele.getText();
		Assert.assertEquals(expectedValue, acctualvalue);

	}
	
	public void clickCheckbox(WebElement ele) {
		if (!ele.isSelected()) {
			clickOnElement(ele, timeout);
		}}

} //class close


















