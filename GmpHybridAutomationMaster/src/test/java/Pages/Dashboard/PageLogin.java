package Pages.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;

public class PageLogin {

	WebDriver driver;
	
	@FindBy(id="userName")
	WebElement emailele;
	
	@FindBy(id="password")
	WebElement passwordele;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement continureele;
	
	
	
	public PageLogin(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	
	public void enterCredentials(String email, String password)
	{
		ApcoaListeners.logInfo("Going to enter credentials in Dashboard");
		GenericMethods.explicitWait(driver, By.id("userName"), 100);
		emailele.sendKeys(email);
		passwordele.sendKeys(password);
		ApcoaListeners.logInfo("Sucessfully Entered email: "+email);
		ApcoaListeners.logInfo("Sucessfully Entered password: "+password);
	}
	
	public void clickLoginBtn()
	{
		ApcoaListeners.logInfo("Going to click Continure ");
		GenericMethods.explicitWait(this.driver, By.xpath("//button[@type='submit']"), 10);
		continureele.click();
		ApcoaListeners.logInfo("Clicked on Continue Sucessfully");
	}
	
	
}
