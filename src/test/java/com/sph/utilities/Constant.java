package com.sph.utilities;
import java.io.File;

public class Constant {
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
//	public static final String APK_PATH = new File("").getAbsolutePath() + File.separator + "app" + File.separator
//			+ "stplus-phone-prod-v605-v1-mar6.apk";
	
	public static final String APK_PATH = "/Users/madhu/Documents/Apps/app-smartphone-debug-2.apk";
	
	public static final String IPA_PATH = new File("").getAbsolutePath() + File.separator + "app" + File.separator
			+ "DEV StraitsTimes-Revamp.ipa";
	//public static final String APP_PATH = "/Users/madhu/Documents/Automation Framework/StraitsTimes-Revamp-26thApril.app";
	public static final String APP_PATH = "/Users/madhu/Documents/Apps/StraitsTimes-Revamp.app";
	public static final int LONG_TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 10;
	public static final String LOGIN_PAGE_TITLE = "Login with mySPH";
	public static final String LEFT = "Left";
	public static final String RIGHT = "Right";
	public static final String UP = "Up";
	public static final String DOWN = "Down";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String APP_PACKAGE = "com.buuuk.st";
	public static final String APP_ACTIVITY = "com.sph.straitstimes.views.activities.SplashActivity";
	public static final String AUTOMATION_NAME_IOS = "XCUITEST";
	public static final String AUTOMATION_NAME_Android = "uiautomator2";
	public static final String BUNDLE_ID = "com.sph.stiPhone";
	
	public static final Integer ARTICLE_COUNT_PER_SECTION = 5;
	
	/* Credentials */
	public static final String PREMIUM_USER = "Premium_Smartphone1";
	public static final String PREMIUM_USER_PWD = "Mango123";
	public static final String LITE_USER = "Lite_Smartphone1";
	public static final String LITE_USER_PWD = "Password123";
	public static final String INVALID_USER = "InvalidUserName";
	public static final String INVALID_PWD = "InvalidPassword";
	public static final String BLANK = "";
	public static final String REGISTERED_USER = "Premium_access1";
	public static final String REGISTERED_PWD = "Password123";
	
//	public static DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss-dd/MM/yyyy");
//	public static Calendar cal = Calendar.getInstance();
//	
//	public static final String REPORT_LOCATION = "/Users/madhu/Documents/Automation Framework/SG/backup - 7th May/STPlusAutomationFramework/reports/Report" + dateFormat.format(cal).toString() + ".html";

	public static final String REPORT_LOCATION = "/Users/madhu/Documents/Automation Framework/SG/backup - 7th May/STPlusAutomationFramework/reports/";
	
	/* webview - native view */
	public static final String WEBVIEW = "WEBVIEW";
	public static final String NATIVE = "NATIVE";

	/* path to appium.xcconfig */
	public static final String CONFIGFILE = new File("").getAbsolutePath() + File.separator + "src" + File.separator
			+ "resources" + File.separator + "appium.xcconfig";

	/* Application constant */
	public static final String READFULLARTICLE = "TO READ THE FULL ARTICLE";
	public static final String THANK_YOU_FOR_READING_ST_TEXT = "Thank you for reading The Straits Times";
	public static final String PREMIUM_STORIES_ACCESS_HELP_TEXT = "You have reached one of our Premium stories. To continue reading, get access now or log in if you are a subscriber.";
	public static final String WHAT_IS_PREMIUM_TEXT = "WHAT IS PREMIUM ?";
	public static final String LOG_IN = "Log In";
	public static final String LOG_OUT = "Log Out";
	public static final String ACCOUNT_MANAGE_LABEL = "MANAGE";
	public static final String ACCOUNT_PAGE_TITLE = "ACCOUNT";

	/* Article Type */
	public static final String PREMIUM = "PREMIUM";
	public static final String FREE = "FREE";
	
	public static final String PREMIUM_SECTION_TITLE = "Unique content, exclusive insights";
	public static final String SECTION_TITLE = "section_title";

	/* Section names -On main article page */
	public static final String FROM_AROUND_THE_WEB = "FROM AROUND THE WEB";
	public static final String MORE_FROM_ST = "MORE FROM ST";
	

	public static final String NOTIFICATION_SWITCH_OFF = "Notifications OFF";
	public static final String NOTIFICATION_SWITCH_ON = "Notifications ON";
	
	
	public static final Integer VIEWPOINT_ARTICLE_LAYOUT_SEQUENCE = 4;

	/* Tab names */
	
	public static final String ST_NOW_TAB_LABEL = "ST NOW";
	public static final String HOME_TAB_LABEL = "HOME";
	public static final String LATEST_TAB_LABEL = "LATEST";
	public static final String SINGAPORE_TAB_LABEL = "SINGAPORE";
	public static final String POLITICS_TAB_LABEL = "POLITICS";
	public static final String ASIA_TAB_LABEL = "ASIA";
	public static final String WORLD_TAB_LABEL = "WORLD";
	public static final String LIFESTYLE_TAB_LABEL = "LIFESTYLE";
	public static final String FOOD_TAB_LABEL = "FOOD";
	public static final String FORUM_TAB_LABEL = "FORUM";
	public static final String VIDEOS_TAB_LABEL = "VIDEOS";
	public static final String OPINION_TAB_LABEL = "OPINION";
	public static final String BUSINESS_TAB_LABEL = "BUSINESS";
	public static final String SPORT_TAB_LABEL = "SPORT";
	public static final String TECH_TAB_LABEL = "TECH";
	
	public enum TAB {

		STNOW, LATEST, HOME, SINGAPORE, POLITICS, ASIA, WORLD, LIFESTYLE, FOOD, FORUM, VIDEOS, OPINION, BUSINESS, SPORT, TECH

	}

	public enum INTROCARD {

		FIRST, SECOND, THIRD, FOURTH, FIFTH

	}
	
	public enum SETTINGS_MENU {

		ACCOUNT, EDIT_HOME, NOTIFICATIONS, EDIT_NOTIFICATIONS, SUBSCRIPTION, AUTO_DOWNLOAD, DOWNLOADED_ISSUES, CLEAR_CACHE, TEXT_SIZE, 
		SPEAKING_RATE, SUPPORT, FAQ_AND_HELP, ADVERTISING_ENQUIRY, TERMS_AND_CONDITIONS, PRIVACY_POLICY

	}
	
	public enum MENU {

		SUBSCRIBE, LOGIN, LOGOUT, HOME_PAGE, EPAPER, BOOKMARK, PRINT_EDITION, SETTINGS, MORE_FROM_ST

	}

}
