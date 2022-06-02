package CommonUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;

public class AutomationConfiguration {

	public static HashMap<String, String> MainPackages = new HashMap<>();
	static {
		try {
			Files.createDirectories(Paths.get(System.getProperty("user.dir").toString()+"/Output/Logs/"));
		} catch (IOException e) {	}
		try {
			Files.createDirectories(Paths.get(System.getProperty("user.dir").toString()+"/Output/Reports/"));
		} catch (IOException e) {}
		try {
			Files.createDirectories(Paths.get(System.getProperty("user.dir").toString()+"/Output/Screenshot/"));
		} catch (IOException e) {	}
		MainPackages.put("APCOA STAGING", "com.apcoaflow.consumer.staging");
		MainPackages.put("APCOA QA", "com.apcoaflow.consumer.qa");
		MainPackages.put("APCOA DEV", "com.apcoaflow.consumer.dev");
		
		MainPackages.put("GMP STAGING", "com.gmpuser.app.staging");
		MainPackages.put("GMP QA", "com.gmpuser.app.qa");
		MainPackages.put("GMP DEV", "com.gmpuser.app.dev");	
	} 
 	public static WebDriver Driver;
	public static AppiumDriver<WebElement> AppiumDriver;
	public static Properties PropertyFile;
	public static DesiredCapabilities DesiredCap;
	public static Logger Log = (Logger) LogManager.getLogger(CreateSession.class.getName());
	public static String Tenant;
	public static String Environment;
	public static String Country;
	public static SoftAssert SoftAsserts;
	public static String AppiumServerURL;
	public static String URL;
	public static String BrowserName;
	public static String LaunchBrowser;
	public static String LaunchMobileApp;
	public static String ScreenshotFor;
	public static String Platform;
	public static String Profile;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	public static void logInfo(String info){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  ").format(new Date()).toString() + info);
		AutomationConfiguration.Log.info(info);
	}	
	public static void onFail(WebDriver d,String msg)
	{
		
	}
}
