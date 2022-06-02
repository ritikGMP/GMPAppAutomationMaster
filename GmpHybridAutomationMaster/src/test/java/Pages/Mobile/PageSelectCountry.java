package Pages.Mobile;
import Mobile.Utils.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import CommonUtility.AutomationConfiguration;
import CommonUtility.GenericMethods;
import TestNGListeners.ApcoaListeners;
import Utility.ElementScreenShot;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageSelectCountry {
	
	WebDriver driver;
	
	By selectCountryName = By.className("android.widget.TextView");
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/text')]")
	WebElement selectCountryButton;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_login')]")
	WebElement btnLog;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_next')]")
	WebElement nextbtn;
	//	
	//com.apcoaflow.consumer:id/footer_button_1
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/footer_button_1')]")
	WebElement btnfooterok;
	
	public String CountrySelected = "";
	
	public PageSelectCountry(WebDriver appiumDriver){
		this.driver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator (appiumDriver), this);
	}	
	
	public void selectCountry(String countryName) throws InterruptedException{
		ApcoaListeners.logInfo("Going to Select Country: "+ countryName);
		GenericMethods.explicitWait(driver, selectCountryName,75);	
		
		List<WebElement> selectCountry = this.driver.findElements(selectCountryName);
		for(WebElement singleCountry : selectCountry){
			ApcoaListeners.logInfo("Country Found: "+singleCountry.getText());
			if(singleCountry.getText().toUpperCase().equals(countryName.toUpperCase())){
				singleCountry.click();
			//	Thread.sleep(4000);//here
				ApcoaListeners.logInfo("Country found and clicked successfully");
				break;
			}
		}
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,selectCountryButton,15);
		 

		CountrySelected = selectCountryButton.getText().toString();
		ApcoaListeners.logInfo("selectCountry ended: country selected: "+ selectCountryButton.getText());	
		try
		{btnfooterok.click();}
		catch(Exception e)
		{
			
		}
	}
		
	public void selectCountryClick() throws InterruptedException{
	//	Thread.sleep(4000);
		ApcoaListeners.logInfo("Going to click select_country button");
		//Thread.sleep(2000); //10000
        
        
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,selectCountryButton,150);
		
		Thread.sleep(2000);
		selectCountryButton.click();
		ApcoaListeners.logInfo("Clicked select_country button successfully");		
	}
	
	public void btnLoginClick() throws InterruptedException{
		ApcoaListeners.logInfo("Going to click Login button");
		try {
			Thread.sleep(2000);
			btnfooterok.click();
		}
		catch(Exception e)
		{
		
		}
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnLog,150);
		btnLog.click();
		try {
			//CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,nextbtn,30);
			//nextbtn.click();
			//PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
			//home.acceptPushNotification();
			ApcoaListeners.logInfo("Clicked Login button successfully");
		}catch(Exception e)
		{}
	}
	
	
}
