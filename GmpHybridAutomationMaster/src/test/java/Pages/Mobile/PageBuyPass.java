package Pages.Mobile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageBuyPass {

	WebDriver driver;
	SoftAssert SA=new SoftAssert();
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	WebElement allowButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	WebElement cancelPopUpButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	WebElement searchBox;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_Search')]")
	WebElement searcheditBox;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_address')])[1]")
	WebElement carparkName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_buy_pass')]")
	WebElement buyPassButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_positive_action')]")
	WebElement buyPassAgainButton;
	
	@AndroidFindBy(id="android:id/button1")
	WebElement selectDate;
	
	@AndroidFindBy(id="android:id/button1")
	WebElement okButton;
	// 602,1444
		//com.apcoaflow.consumer:id/tv_add_discount
		
		//com.apcoaflow.consumer:id/et_promo_code sendkeys
		
		//com.apcoaflow.consumer:id/tv_apply_promo_code click
	
	//	
	//com.apcoaflow.consumer:id/tv_positive_action_button
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_promo_code')]")
	WebElement addPromoLink;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_discount')]")
	WebElement addPromoDiscount;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement addPromoTextbox;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	WebElement applyButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement okGotItButton;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_pay')])[3]")
	WebElement proceedButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	WebElement confirmPayButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	WebElement paymentSuccessfulButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_okay_got_it')]")
	WebElement promoAppliedSuccssfullyOKButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	WebElement promoNotAppliedText;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/half_interstitial_button2')]")
	WebElement ConfirmPromoPayment;
	
	
	
	By puchasePassbtn= By.xpath("//*[contains(@resource-id,':id/tv_purchase_pass')]");
	
	By passList = By.xpath("//*[contains(@resource-id,':id/tv_pass_name')]");
	
	public PageBuyPass(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}



	@SuppressWarnings("rawtypes")
	public void returnPreviousPage() {
	((AndroidDriver) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	public void applyPromoCode(String PromoCode) throws InterruptedException {
		ApcoaListeners.logInfo("Going to apply promo code");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,addPromoDiscount,30);
		addPromoDiscount.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,addPromoTextbox,30);
		addPromoTextbox.sendKeys(PromoCode);
		Thread.sleep(5000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,applyButton,50);
		applyButton.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,okGotItButton,50);
		okGotItButton.click();
		Thread.sleep(3000);
		
		ApcoaListeners.logInfo("Promo code End");
	}
	
	public static void scrollPage() {
		Dimension dimension = AutomationConfiguration.AppiumDriver.manage().window().getSize();
		int start_x=(int)(dimension.width*0.5);
		int start_y=(int)(dimension.height*0.8);

		int end_x=(int) (dimension.width*0.2);
		int end_y=(int) (dimension.height*0.2);

		@SuppressWarnings("rawtypes")
		TouchAction touch= new TouchAction(AutomationConfiguration.AppiumDriver);
		touch.press(PointOption.point(start_x,start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(end_x,end_y)).release().perform();
	}

	public void clickOnPurchasePassButton() {
		List<WebElement> purchasePassButton = driver.findElements(puchasePassbtn);
		List<WebElement> listOfPass= driver.findElements(passList);
	  int numberofpass=	listOfPass.size();
	for(int i=0;i<listOfPass.size();i++){
			String a=listOfPass.get(i).getText();
			System.out.println(a);
			if(AutomationConfiguration.Country.equalsIgnoreCase("Austria"))
			{	
			if(a.equalsIgnoreCase("Tagespauschale")){//Test_notification
				purchasePassButton.get(i).click();
				System.out.println("Clicked on purchase pass button");
				break;
			}
			}
			else if(AutomationConfiguration.Country.equalsIgnoreCase("Sweden"))
			{
				if(a.equalsIgnoreCase("En dagsperiodbiljett")){//Test_notification
			
					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			else if(AutomationConfiguration.Country.equalsIgnoreCase("Poland"))
			{
				if(a.equalsIgnoreCase("Abonament 12 godzinny")){//Test_notification
			
					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
			else if(AutomationConfiguration.Country.equalsIgnoreCase("Germany"))
			{
				if(a.equalsIgnoreCase("proration allowed testing")){//Test_notification
			
					purchasePassButton.get(i).click();
					System.out.println("Clicked on purchase pass button");
					break;
				}
			}
		if(i==numberofpass-2)	
		 scrollPage();
		}
	}

	public void searchAreaAndBuyPass(String areaCode,String promoName) throws InterruptedException {
		//Thread.sleep(2500);
		//allowButton.click();
		//Thread.sleep(2500);
		//cancelPopUpButton.click();
		Thread.sleep(2500);
		searchBox.click();
		Thread.sleep(2500);
		searcheditBox.sendKeys(areaCode);
		Thread.sleep(6000);
		carparkName.click();
		Thread.sleep(6000);
		//returnPreviousPage(); //return to previous page
		Thread.sleep(6000);
		//scrollPage();
		//Thread.sleep(6000);
		buyPassButton.click();
		Thread.sleep(3000);
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,okGotItButton,5);
			okGotItButton.click();
		}
		catch(Exception e) {}
		
		//buyPassAgainButton.click();
		
		
		Thread.sleep(4000);
		clickOnPurchasePassButton();
		Thread.sleep(4000);
		selectDate.click();
		Thread.sleep(6000);
		okButton.click();
		Thread.sleep(6000);
		System.out.println(promoName);
		if(!promoName.equalsIgnoreCase("none"))
		{ addPromoLink.click();
		  Thread.sleep(6000);
	    addPromoTextbox.sendKeys(promoName);
		applyButton.click();
		Thread.sleep(6000);
		try {
			String text=(promoNotAppliedText.getText());
			if(text.equals("Promo code cannot be applied")){
				System.out.println("Promo not applied");
				okGotItButton.click();
				returnPreviousPage();
				Thread.sleep(4000);
			}
		}
		catch(Exception e) {
			System.out.println("Promo applied");
			promoAppliedSuccssfullyOKButton.click();
			Thread.sleep(4000);
		}
		}
		Thread.sleep(4000);
		proceedButton.click();
		Thread.sleep(4000);
		confirmPayButton.click();
		try {
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ConfirmPromoPayment,5);
		ConfirmPromoPayment.click();
		} catch(Exception e) {}
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,promoNotAppliedText,5);
	    SA.assertEquals(promoNotAppliedText.getText(),"Payment Successful");
	    SA.assertAll();
		paymentSuccessfulButton.click();
		
		Thread.sleep(7000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
	}
	
	
}