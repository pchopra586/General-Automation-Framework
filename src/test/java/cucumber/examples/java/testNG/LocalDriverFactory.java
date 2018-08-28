package cucumber.examples.java.testNG;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.xml.utils.LocaleUtility.EMPTY_STRING;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalDriverFactory {
    public static WebDriver createInstance(String browserName, String driverHost, String driverPort, String devicename, String appPath, String udID) throws MalformedURLException {
        WebDriver driver;
        DesiredCapabilities capability;

           //URL hubUrl = new URL("http://0.0.0.0:4723/wd/hub");
        String hubUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
        browserName = (browserName != null) ? browserName : "chrome";

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case SAFARI:
                driver = new SafariDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/Applications/Softwares/chromedriver");
                driver = new ChromeDriver();
                break;
            case ANDROID:
                capability = new DesiredCapabilities().android();
                capability.setCapability("platformName",
                        "Android");
                capability.setCapability("deviceName",devicename);
                capability.setCapability("app",appPath);
                capability.setCapability("browserName",
                        "");
                capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE,"com.buuuk.st");
                //capability.setCapability("appPackage", "com.buuuk.st");
                capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,
                        "com.sph.straitstimes.views.activities.TncActivity");
                driver = new AppiumDriver<>(new URL(hubUrl), capability);
                System.out.printf("Android driver is returned");
                break;
            case IOS:

                capability = new DesiredCapabilities().iphone();
                capability.setCapability("platformName", "iOS");
                capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
                capability.setCapability("app",appPath);
                capability.setCapability(MobileCapabilityType.UDID,udID);
                //capability.setCapability(MobileCapabilityType.UDID,"933236321f37acf9f0ee9b83d22892dd5057a942");
                //capability.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone Simulator");
                capability.setCapability(MobileCapabilityType.DEVICE_NAME,
                        "iPad (3)");
                capability.setCapability("showXcodeLog", true);
                capability.setCapability(MobileCapabilityType.NO_RESET, true);
                capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,
                        true);
                capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,
                        true);
                capability.setCapability(MobileCapabilityType.BROWSER_NAME,
                        EMPTY_STRING);
                capability.setCapability("autoAcceptAlerts", true);
                capability.setCapability("xcodeConfigFile","/Users/pchopra/Documents/appium-xcuitest-driver-master/WebDriverAgent/Config.xcconfig");
                capability.setCapability("xcodeOrgId","L6TLAD67NF");
                capability.setCapability("xcodeSigningId","iPhone Developer");
               // driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),capability);
                driver = new IOSDriver<MobileElement>(new URL(hubUrl), capability);
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    private static enum Browser {
        SAFARI,
        CHROME,
        IE,
        ANDROID,
        IOS;
    }
}