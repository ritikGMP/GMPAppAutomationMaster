package Pages.Mobile;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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

public class ActiveVehicleCheck {

	
	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	WebElement btnMenu;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_vehicle_nav')]")
	WebElement btnNavVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_activate_vehicle')]")
	WebElement btnActiveVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_supported_access_type_heading')]")
	WebElement VehicleInSession;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActiveVehicleCheck (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
		
		
		
	}
	
	By Selectvehicle = By.xpath("(//*[contains(@resource-id,':id/tv_vehicle_number')])");
	By VehicleStatus = By.xpath("//*[contains(@resource-id,':id/iv_activate_vehicle')]");
	
	
	public ArrayList<ArrayList<String>> getVehicleList() throws InterruptedException
	{  
		ApcoaListeners.logInfo("Getting Active and InActive List");
		ArrayList<ArrayList<String>> TotalVehicleList = new ArrayList<ArrayList<String>>();
	
	    ArrayList<String> ActiveVehicle = new ArrayList<String>();
	    ArrayList<String> InActiveVehicle = new ArrayList<String>();
	    
	    ApcoaListeners.logInfo("Going to click on Hammberger menu");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,15);
		btnMenu.click();
		ApcoaListeners.logInfo("Going to click on Vehicle menu");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,5);
		btnNavVehicle.click();
		Thread.sleep(6000);
		
		for(int i=0;;)
		{   List<WebElement> VehicleNo = this.driver.findElements(Selectvehicle);
		    List<WebElement> StatusVehicleNo = this.driver.findElements(VehicleStatus);
		    int Inactivelength=InActiveVehicle.size();
			   int temp=VehicleNo.size();
			
			//TakeScreenShotAndCompare(StatusVehicleNo.get(i));
			System.out.println();
			if(TakeScreenShotAndCompare(StatusVehicleNo.get(i))==true)
			{   if(ActiveVehicle.contains(VehicleNo.get(i).getText())==false)
				 {  
				    ApcoaListeners.logInfo("Entering in active vehicle list       " +VehicleNo.get(i).getText());
			     	ActiveVehicle.add(VehicleNo.get(i).getText());
				 }
			}
			else
			{  
				if(InActiveVehicle.contains(VehicleNo.get(i).getText())==false&&ActiveVehicle.contains(VehicleNo.get(i).getText())==false)
				 {
					{
						ApcoaListeners.logInfo("Entering in InActive vehicle list       " +VehicleNo.get(i).getText());
						InActiveVehicle.add(VehicleNo.get(i).getText());
					}
					
				}
			
			}
			if(Inactivelength>0&&InActiveVehicle.get(Inactivelength-1).equals(VehicleNo.get(temp-1).getText()))
			{
				break;
			}
			if(i==VehicleNo.size()-1)
			{	  
			 action.press(PointOption.point(481,1747)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(495,1015)).release().perform();
			 i=0;
			 Thread.sleep(4000);
			}
			else
			{
				i++;
			}
			
			
		}
		System.out.println("active Vehicle List");
		for(int i=0;i<ActiveVehicle.size();i++)
		{   
			
			System.out.println(ActiveVehicle.get(i));
		}
		
		System.out.println("Inactive Vehicle List");
		for(int i=0;i<InActiveVehicle.size();i++)
		{   
			
			System.out.println(InActiveVehicle.get(i));
		}
		
		TotalVehicleList.add(ActiveVehicle);
		TotalVehicleList.add(InActiveVehicle);
		
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		return TotalVehicleList;
	}
	
	public void temp()
	{
		System.out.println("hello");
	}
	
	public boolean TakeScreenShotAndCompare(WebElement element) throws InterruptedException//
	{   
	ImageComparison IC=new ImageComparison();
	ElementScreenShot SH= new ElementScreenShot();
	String p1=SH.elementScreenshot(driver,element);
  //String p1="/home/ritik/eclipse-workspace/GMP_HybridAutomationFramework-masterfinalGRID/screenshots/575e3fdf-18bf-4c8e-b1ab-febe04a11bef.png";
	String p2="/home/ritik/eclipse-workspace/GMP_HybridAutomationFramework-masterfinalGRID/screenshots/dc8b7eca-04ee-4992-bc35-a10ec5bbc3a8.png";
	double diff=IC.comapareImages(p1,p2,true);
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
	
	public  ArrayList<String> getVehicleListInSession() throws InterruptedException
	{   
		ApcoaListeners.logInfo("Going to Get Vehicle Number in the my sesion");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,VehicleInSession,15);
		VehicleInSession.click();
		Thread.sleep(4000);
		ArrayList<String> Vehicle = new ArrayList<String>();
		for(int i=0;;)
		{   int  length=Vehicle.size();
		    
			List<WebElement> VehicleNo = this.driver.findElements(Selectvehicle);
			int temp=VehicleNo.size();
			if(Vehicle.contains(VehicleNo.get(i).getText())==false&&!(VehicleNo.get(i).getText().equals(null)))
			{  ApcoaListeners.logInfo("entering in Vehicle list");
				Vehicle.add(VehicleNo.get(i).getText());
			}
			
			if(length>0&&Vehicle.get(length-1).equals(VehicleNo.get(temp-1).getText()))
			{
				break;
			}
			
			if(i==VehicleNo.size()-1)
			{	  
			 action.press(PointOption.point(481,1747)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(495,1015)).release().perform();
			 i=0;
			 Thread.sleep(4000);
			}
			else
			{
				i++;
			}
		}
		ApcoaListeners.logInfo("Total Vehicle Visible in the Session Creation Page");
		for( int i=0;i<Vehicle.size();i++)
		{
			ApcoaListeners.logInfo(Vehicle.get(i));
		}
		
		return Vehicle;
	}
	
	
}
