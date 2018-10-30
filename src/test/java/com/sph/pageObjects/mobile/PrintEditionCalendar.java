package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import junit.framework.Assert;

public class PrintEditionCalendar {
	private String methodName = null;
	
	enum PRINT_DAYS 
	{ 
	    TODAY, YESTERDAY; 
	}

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(PrintEditionCalendar.class);
	private WebDriver driver;
    private Capabilities capabilities;
    
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText")
	@AndroidFindBy(id = AndroidElements.TAB_TITLE_ID)
	private List<MobileElement> weekdaysDisplayedInCalendar;

	public PrintEditionCalendar(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
	}
	
	public PrintEditionCalendar verifyCalendarView() {
		methodName = "verifyCalendarView";
		logger.info("Entering Method: " + methodName);
		
		Date date = new Date(); // your date
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMM dd");
		List<String> printDates = new ArrayList<String>();
		String expectedDate = dateFormatter.format(date);
		printDates.add(expectedDate);
		
		//Add next 7 days to the list of expected archived Print Edition list
		int totalArchived = 0;
		
		for(MobileElement weekday: weekdaysDisplayedInCalendar) {
			Assert.assertEquals("Failed to display the correct date", expectedDate, weekday.getText());
			printDates.add(expectedDate);
			System.out.println(expectedDate);
			cal.add(Calendar.DATE, -1);
			totalArchived = totalArchived - 1;
			expectedDate = dateFormatter.format(cal.getTime());
		}
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public WebDriver gotoPrintEdition(String day) {
		methodName = "verifyCalendarView";
		logger.info("Entering Method: " + methodName);
		
		weekdaysDisplayedInCalendar.get(1).click();
		
//		Date date = new Date(); // your date
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMM dd");
//		List<String> printDates = new ArrayList<String>();
//		String expectedDate = dateFormatter.format(date);
//		printDates.add(expectedDate);
//		
//		//Add next 7 days to the list of expected archived Print Edition list
//		int totalArchived = 0;
//		
//		for(MobileElement weekday: weekdaysDisplayedInCalendar) {
//			Assert.assertEquals("Failed to display the correct date", expectedDate, weekday.getText());
//			printDates.add(expectedDate);
//			System.out.println(expectedDate);
//			cal.add(Calendar.DATE, -1);
//			totalArchived = totalArchived - 1;
//			expectedDate = dateFormatter.format(cal.getTime());
//		}
//		logger.info("Exiting Method: " + methodName);
		return driver;
	}
}
