package Pages.Mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class LaunchQrReader {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;


	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/button_perform_action')]")
	WebElement btnSearchLink;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/permission_allow_button')]")
	WebElement acceptPushNotificationBtn;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[1]")
	private WebElement gmptarrifSelection;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_time_title')]")
	WebElement ParkingTimeTitle;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_title')]")
	WebElement ParkingPriceTitle;



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LaunchQrReader (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}

	public void launchQrReader() throws InterruptedException
	{
		try {
			try {

				Activity activity = new Activity("qr.code.scanner.app","qr.code.scanner.ui.onboarding.OnboardingActivity");
				activity.setStopApp(false);
				activity.setAppWaitActivity("qr.code.scanner.ui.onboarding.OnboardingActivity");
				activity.setAppWaitPackage("qr.code.scanner.app");

				((AndroidDriver<MobileElement>)(WebDriver) AutomationConfiguration.AppiumDriver).startActivity(activity);

			}catch(Exception e) {}
			
			try {
				ApcoaListeners.logInfo("Clicking  on Accept button");
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,acceptPushNotificationBtn,20);
				acceptPushNotificationBtn.click();

			}
			catch (Exception e) {}
			
			ApcoaListeners.logInfo("Scanning the QR ");
			Thread.sleep(10000);
			try {
				ApcoaListeners.logInfo("Clicking  on Search  button");
				
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnSearchLink,10);
				btnSearchLink.click();
				ApcoaListeners.logInfo("Clicked  on Search button");
			} catch (Exception e) {}

		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}


	}

	public void KillQrScanner()
	{  
		String command ="adb shell pm clear qr.code.scanner.app";

		try{Process p = Runtime.getRuntime().exec(command);}catch(Exception e) {System.out.println(e.toString());}


	}

	public void  checkStartSessionPage() throws InterruptedException
	{
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,gmptarrifSelection,25);
			gmptarrifSelection.click();
			ApcoaListeners.logInfo("Tarrif Selected");
		}
		catch(Exception e) {}

		ApcoaListeners.logInfo("Checking validation checks for Start Session Page");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingTimeTitle,10);
		SA.assertEquals(ParkingTimeTitle.getText(),"Set Parking Time");

		

		ApcoaListeners.logInfo("Start session page Verified");
		SA.assertAll();
		
		Thread.sleep(6000);




	}

}
