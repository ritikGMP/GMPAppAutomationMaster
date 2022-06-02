package Pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageHomeApcoa {
	
	WebDriver driver;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_negative_action')]")
	WebElement denyPushNotificationBtn;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	WebElement usernametxt;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_order_dismiss')]")
	WebElement btnDismissFoodAlert;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	WebElement cancelQuestionPopUpButton;  //to cancel question pop up 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close_activation_reminder')]")
	WebElement cancelActivatePopUpButton;  
	
	
	public String Username="";
	
	public PageHomeApcoa(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}
	
	public void dismissFoodAlert(){	
		try {
		GenericMethods.explicitWaitForWebElementOnly(driver, btnDismissFoodAlert,2);
		ApcoaListeners.logInfo("Going to click Food Alert ");
		btnDismissFoodAlert.click();
		ApcoaListeners.logInfo("Clicked Food alert button sucessfully.");
		}catch(Exception e) {}
	}
	
	public void acceptPushNotification(){	
		GenericMethods.explicitWaitForWebElementOnly(driver, denyPushNotificationBtn,100);
		ApcoaListeners.logInfo("Going to click Accept Push Notification ");
		denyPushNotificationBtn.click();
		ApcoaListeners.logInfo("Clicked Accept Push Notification button sucessfully.");
		dismissFoodAlert();
	}

	public void checkUserName(){
		GenericMethods.explicitWaitForWebElementOnly(driver, btnMenu,75);
		btnMenu.click();
		GenericMethods.explicitWaitForWebElementOnly(driver, usernametxt,75);
		ApcoaListeners.logInfo("username is: "+ usernametxt.getText());
		Username = usernametxt.getText();
		PageAddVehicle.goBack();
	}
	public void cancelQuestionPopUp() {
		try {
			cancelQuestionPopUpButton.click();
		}catch(Exception e) {}
		
	}
	
	public void cancelActivatePopUp() {
		try {
			cancelActivatePopUpButton.click();
		}catch(Exception e) {}
		
	}
}
