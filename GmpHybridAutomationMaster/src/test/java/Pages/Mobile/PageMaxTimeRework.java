package Pages.Mobile;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import TestNGListeners.ApcoaListeners;
import freemarker.core.ParseException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PageMaxTimeRework {
	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement gmpstartsession;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[1]")
	private WebElement gmptarrifSelection1;
	
	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement gmptarrifSelection2;
    
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_expiry_info')]")
	private WebElement ExpiryTime;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_hours')]")
	private WebElement parkingHrs;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_minutes')]")
	private WebElement parkingMins;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_with_units')]")
	private WebElement parkingPrice;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement close;
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageMaxTimeRework (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}
	
	
	public void SelectTarrif(String Tarrif)
	{  
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
		try {
			
			if(Tarrif.equals("TIME_BASED"))
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmptarrifSelection1,10);
			else if(Tarrif.equals("NOTTIME_BASED"))
			{
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmptarrifSelection2,10);	
			}
		}
		catch(Exception e) {}
	}
	
	
	
	public void CheckMaxima(double IntialPrice,double incremental,double maxprice,String maxtime) throws ParseException, java.text.ParseException, InterruptedException {
		 SoftAssert SA=new SoftAssert(); 
		 
		OperationalHrs OP=new OperationalHrs(AutomationConfiguration.AppiumDriver);
		
		boolean res;
		if(!maxtime.equals("none")) {
			ApcoaListeners.logInfo("----------------------------------This is the Time_Based ------------------------------");
		String initialTime=ExpiryTime.getText().split("- ")[1]+":00";
		initialTime=initialTime.replace(" ","");
		String CurrentTime=initialTime;
		
		
		System.out.println(initialTime);
		System.out.println(maxtime);
		String p;
		
		
		double Price;
		
		ApcoaListeners.logInfo("Cuurent Time -------------->"+CurrentTime);
		ApcoaListeners.logInfo("Maximum Time --------------> "+maxtime);
		ApcoaListeners.logInfo("Maximum price-------------->"+maxprice);
		System.out.println();
		System.out.println("------------------------------------------------------------------");
		
		
		ApcoaListeners.logInfo("Rotating the Dailer ");
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(849, 1646)).release().perform();
		boolean temp=OperationalHrs.isTimeBetweenTwoTime(initialTime, maxtime, CurrentTime);   
		    if(temp)
	       	{
		   	  p=parkingPrice.getText().split("₹")[1];
		   	  Price=Double.parseDouble(p); 
		   	  System.out.println(Price);
		   	  SA.assertEquals(Price,IntialPrice,"Initial Price Is Not Same");
		   	  SA.assertAll();
		   	}
		    
		    
		    ApcoaListeners.logInfo("Rotating The Dailer");
		  
//		   int current_Hrs=Integer.parseInt(CurrentTime.split(":")[0]);
//		   int current_Mins=Integer.parseInt(CurrentTime.split(":")[1]);
//		   int max_Hrs=Integer.parseInt(maxtime.split(":")[0]);
//		   int max_Mins=Integer.parseInt(maxtime.split(":")[1]);
//		   System.out.println(current_Hrs);
//		   System.out.println(current_Mins);
		   
		   
		   
		  while(OperationalHrs.isTimeBetweenTwoTime(initialTime, maxtime, CurrentTime))
		{
			  for( int i=25;i<125;i+=25)
			{   
				OP.dailerRotator(i);
				
				Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
				 if(Price<=maxprice)
				 {
					 res=true;			 }
				 else
					 res=false;
				 
				
				CurrentTime=ExpiryTime.getText().split("- ")[1]+":00";
				CurrentTime=CurrentTime.replace(" ","");
				if(OperationalHrs.isTimeBetweenTwoTime(initialTime, maxtime, CurrentTime))
				{ SA.assertEquals(res, true,"Price is greater than the Maximum Price Set");
				 
				}
				else
				{   Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
//					SA.assertEquals(maxprice+incremental,Price);
					SA.assertTrue(Price>maxprice);
				}
				
				SA.assertAll();
			}
			  
//			Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
//			 if(Price<=maxprice)
//			 {
//				 res=true;			 }
//			 else
//				 res=false;
//			 
//			
//			CurrentTime=ExpiryTime.getText().split("- ")[1]+":00";
//			CurrentTime=CurrentTime.replace(" ","");
//			if(OperationalHrs.isTimeBetweenTwoTime(initialTime, maxtime, CurrentTime))
//			{ SA.assertEquals(res, true,"Price is greater than the Maximum Price Set");
//			 
//			}
//			else
//			{   Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
////				SA.assertEquals(maxprice+incremental,Price);
//				SA.assertTrue(Price>maxprice);
//			}
//			SA.assertAll();
	   
		}
		  }
		else
		{
			ApcoaListeners.logInfo("----------------------------------This is the Not_Time_Based ------------------------------");
			int ParkingHours= Integer. parseInt(parkingHrs.getText());
			int  ParkingMins=Integer. parseInt(parkingMins.getText());
			
			int time=ParkingHours*60+ParkingMins;
			
			while(time<1440)
			{
				  for( int i=25;i<125;i+=25)
					{   
						OP.dailerRotator(i);
						
						 Double	Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
						 if(Price<=maxprice)
						 {
							 res=true;			
							 }
						 else
							 res=false;
						 
						 ParkingHours= Integer. parseInt(parkingHrs.getText());
					     ParkingMins=Integer. parseInt(parkingMins.getText());
							
						 time=ParkingHours*60+ParkingMins;
						 
						if(time<1440)
						 SA.assertEquals(res, true,"Price is greater than the Maximum Price Set");
						
					
						SA.assertAll();

				}
				
				for( int i=25;i<125;i+=25)
				{   
					OP.dailerRotator(i);
					
				}
				
				Double	Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
				 if(Price>maxprice)
				 {
					 res=true;			
					 }
				 else
					 res=false;
				 
				 ParkingHours= Integer. parseInt(parkingHrs.getText());
			     ParkingMins=Integer. parseInt(parkingMins.getText());
					
				 time=ParkingHours*60+ParkingMins;
				 
				if(time>1440)
				 SA.assertEquals(res, true,"Price is less or equal to the Maximum Price Set");
				
				SA.assertAll();
						
					}
//				 Double	Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
//					 if(Price<=maxprice)
//					 {
//						 res=true;			
//						 }
//					 else
//						 res=false;
//					 
//					 ParkingHours= Integer. parseInt(parkingHrs.getText());
//				     ParkingMins=Integer. parseInt(parkingMins.getText());
//						
//					 time=ParkingHours*60+ParkingMins;
//					 
//					if(time<1440)
//					 SA.assertEquals(res, true,"Price is greater than the Maximum Price Set");
//					
//				
////					SA.assertAll();
//
//			}
//			
			for( int i=25;i<125;i+=25)
			{   
				OP.dailerRotator(i);
				
				Double	Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
				 if(Price>maxprice)
				 {
					 res=true;			
					 }
				 else
					 res=false;
				 
				 ParkingHours= Integer. parseInt(parkingHrs.getText());
			     ParkingMins=Integer. parseInt(parkingMins.getText());
					
				 time=ParkingHours*60+ParkingMins;
				 
				if(time>1440)
				 SA.assertEquals(res, true,"Price is less or equal to the Maximum Price Set");
				
				SA.assertAll();
				
			}
			
			
			
			
		}
		  try {
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,close,10);
		close.click();
		  }
		  catch(Exception e) {}
		    
	}
	
	public void dailerRotator(int perc) throws InterruptedException
	{
		if(perc==25)
		{    
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(849, 1646)).release().perform();
		// Thread.sleep(2000);
		}else if(perc==50)
		{
			
			action.press(PointOption.point(849, 1646)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(545, 1968)).release().perform();
			// Thread.sleep(2000);
		}
		else if(perc==75)
		{
			
			action.press(PointOption.point(545, 1968)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(206, 1643)).release().perform();
			//  Thread.sleep(2000);
		}
		else if(perc==100)
		{
			
			action.press(PointOption.point(206, 1643)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(553, 1427)).release().perform();
			//  Thread.sleep(2000);
		}
	}
	
	public void checkPrice (String initialTime,String maxtime,String CurrentTime,Double maxprice) throws NumberFormatException, ParseException, java.text.ParseException
	{
		double Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
	   boolean res;
	   SoftAssert SA=new SoftAssert();
	 if(Price<=maxprice)
	 {
		 res=true;			 }
	 else
		 res=false;
		
		CurrentTime=ExpiryTime.getText().split("- ")[1]+":00";
		CurrentTime=CurrentTime.replace(" ","");
		if(OperationalHrs.isTimeBetweenTwoTime(initialTime, maxtime, CurrentTime))
		{ SA.assertEquals(res, true,"Price is greater than the Maximum Price Set");
		 
		}
		else
		{   Price=Double.parseDouble(parkingPrice.getText().split("₹")[1]);
//			SA.assertEquals(maxprice+incremental,Price);
			SA.assertTrue(Price>maxprice);
		}
		SA.assertAll();
		
	}
	

	
	
}
	


