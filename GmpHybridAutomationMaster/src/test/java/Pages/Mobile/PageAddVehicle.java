package Pages.Mobile;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import Utility.ElementScreenShot;
import Utility.ImageComparison;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageAddVehicle {
	
	WebDriver driver;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_vehicle_nav')]")
	WebElement btnNavVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_activate_vehicle')]")
	WebElement btnActiveVehicle;
	
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_add')]")
	WebElement btnAddVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_vehicle_number')]")
	WebElement edtbxAddVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_save')]")
	WebElement btnSaveVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_save')]")
	WebElement btnbacktohome;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	WebElement btnsearchparking;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_Search')]")
	WebElement edtxsearchparking;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/tv_vehicle_number')])[1]")
	WebElement txtfirstlpr;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_delete_vehicle')])[1]")
	WebElement btnfirstlprdel;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	WebElement btnconfirmdel;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button') or contains(@resource-id,':id/btn_button_bottom')]")
	WebElement btnconfirmadd;   
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btnNext')]")
	WebElement btnNext;
	
		
	public PageAddVehicle(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}
	
	public String getfirstvehiclelpr(){
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,txtfirstlpr,30);
		return txtfirstlpr.getText();
	}
	
	public void deletelpr() throws InterruptedException
	{
		ApcoaListeners.logInfo("Going to delete Vehicle");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		//Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,15);
		btnNavVehicle.click();
	//	Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnfirstlprdel,15);
		btnfirstlprdel.click();
	//	Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnconfirmdel,15);
		btnconfirmdel.click();
		ApcoaListeners.logInfo("Delete Vehicle End");
	}
	
	public void addVehicle(String vehiclenumber) throws InterruptedException{
	
		ApcoaListeners.logInfo("Add Vehicle Start");
	//	Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		//Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,15);
		btnNavVehicle.click();
	//	Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddVehicle,15);
		btnAddVehicle.click();
	//	Thread.sleep(1000);
		if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa"))
		{	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,15);
			btnNext.click();
			
		}
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtbxAddVehicle,15);
		edtbxAddVehicle.sendKeys(vehiclenumber);
	//	Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnSaveVehicle,15);
		btnSaveVehicle.click();
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnconfirmadd,10);
			btnconfirmadd.click();
			
		}
		catch(Exception e)
		{
			
		}
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Add Vehicle End");
	}
	
	public void addVehicle2(String vehiclenumber) throws InterruptedException{
		
		ApcoaListeners.logInfo("Add Vehicle Start");
	//	Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		//Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,15);
		btnNavVehicle.click();
	//	Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddVehicle,15);
		btnAddVehicle.click();
	//	Thread.sleep(1000);
		
		    CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,15);
			btnNext.click();
			
		
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,edtbxAddVehicle,15);
		edtbxAddVehicle.sendKeys(vehiclenumber);
	//	Thread.sleep(1000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,15);
		btnNext.click();
		
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnconfirmadd,5);
			btnconfirmadd.click();
			
		}
		catch(Exception e)
		{
			
		}
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Add Vehicle End");
	}
	
	
	
	
	public static void goBack(){
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}
}