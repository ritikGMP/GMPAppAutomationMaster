package Pages.Mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class referralPromo {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
	
	String Referral;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_get_free_parking')]")
	WebElement btnGetPromo;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_logout')]")
	WebElement logout;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	WebElement ProceedLogout;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_register')]")
	WebElement Register;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_referral_code')]")
	WebElement ReferralCode;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_heading')]")
	WebElement PromoName1;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_promotion_name')]")
	WebElement Promolist;
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout[2]/android.widget.ImageView")
	WebElement btncontinue;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public referralPromo (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}
	
	
	public String getReferralCode() throws InterruptedException
	{    
		 ApcoaListeners.logInfo("Going to click on Hammberger menu");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,15);
			btnMenu.click();
			ApcoaListeners.logInfo("Going to get Referral Code menu");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnGetPromo,15);
			btnGetPromo.click();
			Referral=ReferralCode.getText();
			ApcoaListeners.logInfo("Referral Code :    "+Referral);
			Thread.sleep(4000);
			((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,15);
			btnMenu.click();
			
			ApcoaListeners.logInfo("Going To logout");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,logout,15);
			logout.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ProceedLogout,15);
			
			ProceedLogout.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btncontinue,15);
			btncontinue.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,15);
			btnMenu.click();
			ApcoaListeners.logInfo("Starting New User registration with PromoCode");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Register,15);
			Register.click();
			return Referral;
	}
	
	By Promo = By.xpath("//*[contains(@resource-id,':id/tv_promotion_name')]");
	public boolean CheckFreePromo(String parkingName,String PromoName) throws InterruptedException
	{
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	    SC.GettheParking(parkingName);
	    SC.StartsessionforParkingwithPass();
	    ApcoaListeners.logInfo("Checking Free parking Promo in the Promo List      :"+PromoName);
	    CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PromoName1,15);
	    PromoName1.click();
	    Thread.sleep(5000);
	    List<WebElement> ListOfPromo = this.driver.findElements(Promo);
	    for(int i=0;i<ListOfPromo.size();i++)
	    {    ApcoaListeners.logInfo("Promo Found :     "+ListOfPromo.get(i).getText());
	    	if(ListOfPromo.get(i).getText().equals(PromoName))
	    	{  
	    		return true;
	    	}
	    }
	    return false;
	    	
	
	}
	
   
	
	
	
	
}
