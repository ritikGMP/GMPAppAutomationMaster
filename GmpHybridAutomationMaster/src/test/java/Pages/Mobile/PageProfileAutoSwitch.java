package Pages.Mobile;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import Utility.ElementScreenShot;
import Utility.ImageComparison;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageProfileAutoSwitch {
	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
    
	By DefaultMark = By.xpath("//android.widget.ImageView[contains(@resource-id,':id/iv_default')]");
	By Profile = By.xpath("//android.widget.TextView[contains(@resource-id,':id/tv_profile_title')]");
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payment_heading')]")
	private WebElement PaymentProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmount; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialer;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_heading')]")
	private WebElement paySuccessMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cover_button2')]")
	private WebElement btnCancelNotification2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement closePayment;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_extend_parking_session')]")
	private WebElement ExtendSession;

	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Image\"])[2]")
	private WebElement Screenshot1;

	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Image\"])[3]")
	private WebElement Screenshot2;



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageProfileAutoSwitch (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}

	public void  ProfileAutoSwitchCheck(String DeafultProfile ,String Parking ,String SwitchedProfile ,String startSession ) throws InterruptedException
	{
	String paymentProfile;
	
	SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	SC.changingDefaultProfile(DeafultProfile);
	SC.GettheParking(Parking);
	SC.StartsessionforParkingwithPass();
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PaymentProfile,10);
	paymentProfile=PaymentProfile.getText();
	
	
	
	/*if(paymentProfile.equalsIgnoreCase("Personal"))
		{paymentProfile+=" Profile";}
		*/
	
	ApcoaListeners.logInfo("PaymentProfile in Start Session   ------>"+paymentProfile);
	
	SA.assertEquals(paymentProfile,SwitchedProfile);
	
	Thread.sleep(4000);
	ApcoaListeners.logInfo("Applying The PromoCode");
	SC.addAnotherPromoCode("100PERCENT");
	Thread.sleep(3000);
	ApcoaListeners.logInfo("Rotating The Dailer");
	action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
	
	ApcoaListeners.logInfo("Checking Start Session");
	SA.assertEquals(checkSessionStart(),startSession);

	SA.assertAll();

	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,payAmount,15);
	if(AutomationConfiguration.Country.equalsIgnoreCase("Italy")) 
	{CommonUtility.GenericMethods.explicitWaitForWebElement(driver,btnCancelNotification2,15);
	//btnCancelNotification2.click();
	}
	if(!(AutomationConfiguration.Country.equals("Denmark")||AutomationConfiguration.Country.equals("Sweden"))) {
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paySuccessMsg,30);
		String ActualPaymentSuccess=paySuccessMsg.getText();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,10);
		closePayment.click();
		ApcoaListeners.logInfo("PaymentConfirmation end: ActualPaymentSuccess " + ActualPaymentSuccess);
	}

/*
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));


		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	 */


	}



	
	public void checkProfileInExtend(String SwictedProfile) throws InterruptedException
	{
		ApcoaListeners.logInfo("Extend Session start");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ExtendSession,30);
		ExtendSession.click();
		Thread.sleep(4000);//8000
		ApcoaListeners.logInfo("Going To click on PaymentProfile");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PaymentProfile,30);
		PaymentProfile.click();
		Thread.sleep(3000);
      
		List<WebElement> profile = this.driver.findElements(Profile);
		List<WebElement> defaultmark = this.driver.findElements(DefaultMark);
        
        System.out.println(profile.size());
		for(int i=0;i<profile.size();i++)
		{
			ApcoaListeners.logInfo(profile.get(i).getText());
			
			
			if(profile.get(i).getText().equalsIgnoreCase(SwictedProfile))
			{
			
				SA.assertEquals(TakeScreenShotAndCompare(defaultmark.get(i)),true);
				
				break;
			}
			
		}
		
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
         SA.assertAll();
         ApcoaListeners.logInfo("Payment Profile in Both Start Session and Extend Session Are Same");

	}
	
	

	public boolean TakeScreenShotAndCompare(WebElement element) throws InterruptedException
	{   
	ImageComparison IC=new ImageComparison();
	ElementScreenShot SH= new ElementScreenShot();
	String p1=SH.elementScreenshot(driver,element);
	String p2="/home/ritik/eclipse-workspace/GMP_HybridAutomationFramework-masterfinalProduction2/screenshots/6b467ab2-0af5-4532-ab58-236213185cb3.png";
	double diff=IC.comapareImages(p1,p2,false);
	if(diff>=0.0&&diff<=0.5)
	{ 
	  ApcoaListeners.logInfo("Images Are Same");
	  Thread.sleep(4000);
	  removeScreenshot(p1);
	  return true;
	  
	}
	else
	{
		ApcoaListeners.logInfo("Images Are Different");
		  Thread.sleep(4000);
		removeScreenshot(p1);
		return false;
	}
	
	
	}

  public void removeScreenshot(String path)
  {
	  String command ="rm "+path;
	  
      try{Process p = Runtime.getRuntime().exec(command);}catch(Exception e) {System.out.println(e.toString());}
      ApcoaListeners.logInfo("ScreenShot Deleted");
  }

	public String checkSessionStart() throws InterruptedException
	{     
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,clickOnDialer,10);
		clickOnDialer.click();
		Thread.sleep(4000);
		try
		{  
			if(payAmount.isDisplayed())
			{	return "true";
			}
			else
				return "false";
		}
		catch(Exception e)
		{}
		return "false";

	}







}
