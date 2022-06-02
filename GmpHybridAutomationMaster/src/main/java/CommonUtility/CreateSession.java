/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: This Class for loading config file and
	creating the using of selenium and appium.
-------------------------------------------------------------

*/
package CommonUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSession {
	
	public WebDriver getDriver()	{
		return AutomationConfiguration.Driver;
	}
	
	/**
     * method to read the Config File and launch drivers according to configuration
     *
     */
	public static void readConfigFile(String path) throws IOException{
		AutomationConfiguration.logInfo("Start reading config file");

		String pathforconfig=System.getProperty("user.dir").toString()+path.toString();
		
		FileInputStream fis = new FileInputStream(pathforconfig.toString());
		AutomationConfiguration.PropertyFile = new Properties();
		AutomationConfiguration.PropertyFile.load(fis);
		AutomationConfiguration.DesiredCap = new DesiredCapabilities();
				
		AutomationConfiguration.ScreenshotFor = AutomationConfiguration.PropertyFile.getProperty("screenshotfor").toString();
		AutomationConfiguration.LaunchBrowser = AutomationConfiguration.PropertyFile.getProperty("launchbrowser").toString();
		AutomationConfiguration.LaunchMobileApp = AutomationConfiguration.PropertyFile.getProperty("launchmobileapp").toString();
		AutomationConfiguration.Platform = AutomationConfiguration.PropertyFile.getProperty("platformName").toString();
		AutomationConfiguration.logInfo("Read Complete property file.");
		
		if(AutomationConfiguration.LaunchBrowser.toUpperCase().equals("TRUE")){
			AutomationConfiguration.logInfo("Starting launching web for testing.");
			launchWebDriver();
		}if(AutomationConfiguration.LaunchMobileApp.toUpperCase().equals("TRUE")){
			AutomationConfiguration.logInfo("Starting launching mobile app ");
			if(AutomationConfiguration.Platform.toUpperCase().equals("ANDROID")){
				AutomationConfiguration.logInfo("Platform: Android");
				launchAndroidDriver();
			}else if(AutomationConfiguration.Platform.toString().toUpperCase().equals("IOS")){
				AutomationConfiguration.logInfo("Platform: IOS");
				launchiOSDriver();}
			else{
				AutomationConfiguration.logInfo("Platform name is invalid cannot launch any test.");
			}
		}
	}
	
	
	/**
     * method to launch the android driver
     *
     *@param capabilities to give the desiredcapabilities
     */
	
	public synchronized static void launchAndroidDriver() throws MalformedURLException{		
		try {
			AutomationConfiguration.logInfo("Launching android app");
						
			AutomationConfiguration.DesiredCap.setCapability("deviceName", AutomationConfiguration.PropertyFile.getProperty("deviceName").toString());
			AutomationConfiguration.DesiredCap.setCapability("platformName",AutomationConfiguration.PropertyFile.getProperty("platformName").toString());
			AutomationConfiguration.DesiredCap.setCapability("appActivity", AutomationConfiguration.PropertyFile.getProperty("appActivity").toString());
			AutomationConfiguration.DesiredCap.setCapability("app",new File( AutomationConfiguration.PropertyFile.getProperty("app").toString()).getAbsolutePath());
			AutomationConfiguration.DesiredCap.setCapability(MobileCapabilityType.FULL_RESET, true);
			AutomationConfiguration.DesiredCap.setCapability("automationName", AutomationConfiguration.PropertyFile.getProperty("automationName").toString());
			AutomationConfiguration.DesiredCap.setCapability("uiautomator2ServerLaunchTimeout",Integer.parseInt(AutomationConfiguration.PropertyFile.getProperty("uiautomator2ServerLaunchTimeout")));
			AutomationConfiguration.DesiredCap.setCapability("appWaitDuration",Integer.parseInt(AutomationConfiguration.PropertyFile.getProperty("appWaitDuration")));
			AutomationConfiguration.DesiredCap.setCapability("udid", AutomationConfiguration.PropertyFile.getProperty("udid").toString());
			AutomationConfiguration.DesiredCap.setCapability("adbExecTimeout", 25000);
			AutomationConfiguration.DesiredCap.setCapability("autoGrantPermissions", true);
			
			AutomationConfiguration.AppiumServerURL = AutomationConfiguration.PropertyFile.getProperty("appiumserverurl").toString();
			
			AutomationConfiguration.AppiumDriver = new AndroidDriver<>(new URL(AutomationConfiguration.AppiumServerURL), AutomationConfiguration.DesiredCap);
			AutomationConfiguration.AppiumDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			AutomationConfiguration.logInfo("Successfully launched android app");	
		}catch(Exception e){
			AutomationConfiguration.logInfo("Error in launching android app. Exception: "+e.toString());
		}
	}
	
	/**
     * method to launch the IOS driver
     *
     *@param capabilities to give the desiredcapabilities
     */
	public synchronized static void launchiOSDriver() throws MalformedURLException {		
		//appiumDriver  = new IOSDriver( new URL(appiumServerURL), capabilities);
	}

	/**
     * method to launch the Webdriver for Web App
     *
     */
	public static void launchWebDriver(){
		try {
			AutomationConfiguration.BrowserName = AutomationConfiguration.PropertyFile.getProperty("browser").toString();
			AutomationConfiguration.URL = AutomationConfiguration.PropertyFile.getProperty("url").toString();
			if(AutomationConfiguration.BrowserName.toUpperCase().equals("CHROME")){
					AutomationConfiguration.logInfo("Launching Chrome browser");
					WebDriverManager.chromedriver().setup();
					AutomationConfiguration.Driver = new ChromeDriver();
					AutomationConfiguration.Driver.manage().window().maximize();
					AutomationConfiguration.logInfo("Sucessfully launched Chrome Browser");
					AutomationConfiguration.Driver.get(AutomationConfiguration.URL);
					AutomationConfiguration.logInfo("Successfully navigate to URL: "+ AutomationConfiguration.URL);	
			}else if (AutomationConfiguration.BrowserName.toUpperCase().equals("FIREFOX")){
				AutomationConfiguration.logInfo("Launching Firefox browser");
				WebDriverManager.firefoxdriver().setup();
				AutomationConfiguration.Driver.manage().window().maximize();
				AutomationConfiguration.logInfo("Sucessfully launched Firefox Browser");
				AutomationConfiguration.Driver.get(AutomationConfiguration.URL);
				AutomationConfiguration.logInfo("Successfully navigate to URL: "+ AutomationConfiguration.URL);	
			}else if (AutomationConfiguration.BrowserName.toUpperCase().equals("IE")){
				AutomationConfiguration.logInfo("Launching Internet Explorer browser");
				WebDriverManager.iedriver().setup();
				AutomationConfiguration.Driver.manage().window().maximize();
				AutomationConfiguration.logInfo("Sucessfully launched Internet Explorer Browser");
				AutomationConfiguration.Driver.get(AutomationConfiguration.URL);
				AutomationConfiguration.logInfo("Successfully navigate to URL: "+ AutomationConfiguration.URL);	
			}else{
				AutomationConfiguration.logInfo("Invalid browser type. Cannot launch.");
			}
		}catch(Exception e){
			AutomationConfiguration.logInfo("Exception in launching browser: "+ e.toString());
		}		
	}
}



















