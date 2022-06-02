package Pages.Mobile;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageLogin {
	
	WebDriver driver;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	WebElement edtxtEmail;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	WebElement edtxtPassword;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_continue')]")
	WebElement btnLogin;
	
	public PageLogin(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}
		
	public void enterCredentials(String email, String password) throws InterruptedException{
		Thread.sleep(8000);//5000
		ApcoaListeners.logInfo("Going to Login with credentials: "+ email+" -> "+ password);
		//CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtxtEmail,50);
	//	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtxtPassword,10);
		
	//	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtxtEmail,8);
		Thread.sleep(28000);
		edtxtEmail.sendKeys(email);
		edtxtPassword.sendKeys(password);
		ApcoaListeners.logInfo("Credentials entered successfully ");
	}

	public void clickContinue(){
		ApcoaListeners.logInfo("Going to click Login button ");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnLogin,20);
		btnLogin.click();
		ApcoaListeners.logInfo("Clicked Login button sucessfully.");
	}
	
	
	
}
