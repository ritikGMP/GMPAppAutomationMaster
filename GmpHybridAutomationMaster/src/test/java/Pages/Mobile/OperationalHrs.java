package Pages.Mobile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import MobileObjectMapper.OperationalHrsMapper;
import TestNGListeners.ApcoaListeners;
import freemarker.core.ParseException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class OperationalHrs {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_filter')]")
	WebElement btnFilter;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_filters')]")
	WebElement btnApplyFilter;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement btnstartsession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_display_name')]")
	private WebElement parkingName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_error_desc')]")
	private WebElement ErrorMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_expiry_info')]")
	private WebElement ExpiryTime;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_hours')]")
	private WebElement parkingHrs;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_minutes')]")
	private WebElement parkingMins;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmount; 
 
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OperationalHrs (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}

	public void changeFilter() throws InterruptedException
	{    ApcoaListeners.logInfo("Clicking the Filter button");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnFilter,10);
	btnFilter.click();
	ApcoaListeners.logInfo("Clicked the Filter Button");
	Thread.sleep(3000);
	action.press(PointOption.point(990, 660)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(86, 660)).release().perform();
	ApcoaListeners.logInfo("Clicking on Apply Filter");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnApplyFilter,10);
	btnApplyFilter.click();

	ApcoaListeners.logInfo("Filter Applied");
	}


	public static boolean isTimeBetweenTwoTime(String argStartTime,String argEndTime, String argCurrentTime) throws ParseException, java.text.ParseException {
		String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		//
		if (argStartTime.matches(reg) && argEndTime.matches(reg)
				&& argCurrentTime.matches(reg)) {
			boolean valid = false;
			// Start Time
			java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
					.parse(argStartTime);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startTime);

			// Current Time
			java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
					.parse(argCurrentTime);
			Calendar currentCalendar = Calendar.getInstance();
			currentCalendar.setTime(currentTime);

			// End Time
			java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
					.parse(argEndTime);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endTime);

			//
			if (currentTime.compareTo(endTime) < 0) {

				currentCalendar.add(Calendar.DATE, 1);
				currentTime = currentCalendar.getTime();

			}

			if (startTime.compareTo(endTime) < 0) {

				startCalendar.add(Calendar.DATE, 1);
				startTime = startCalendar.getTime();

			}
			//
			if (currentTime.before(startTime)) {

				valid = false;
			} else {

				if (currentTime.after(endTime)) {
					endCalendar.add(Calendar.DATE, 1);
					endTime = endCalendar.getTime();

				}



				if (currentTime.before(endTime)) {

					valid = true;
				} else {
					valid = false;

				}

			}
			return valid;

		} else {
			throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
		}

	}


//public static void main(String[] args) throws ParseException, java.text.ParseException
//{
//	System.out.println(isTimeBetweenTwoTime("12:39:00","12:39:00","13:40:00"));
//
////	
////	
//}

	

	
	public boolean  checkExceedingMsg()
	{
		try {
			if(ErrorMsg.isDisplayed())
				if(ErrorMsg.getText().equals("Exceeding operational hours"))
						return true;
				else
					return false;
		}
		catch(Exception e) {}
		return false;
	}
   
	public String findCurrentTime()
	{
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   return dtf.format(now);	
	}
	
	
	public void CheckOperationalHrs(OperationalHrsMapper mapper) throws ParseException, java.text.ParseException, InterruptedException
	{  
		String OpenHrs=mapper.getopeninghrs();
		String closeHrs=mapper.getclosinghrs();
		String  CurrentTime=findCurrentTime();
		String ParkingTime=parkingHrs.getText()+":"+parkingMins.getText()+":"+"00";
		
		ApcoaListeners.logInfo("Opening Time of Parking "+OpenHrs);
		ApcoaListeners.logInfo("Closing Time of Parking "+closeHrs);
	    ApcoaListeners.logInfo("Current Timing :"+CurrentTime);
		
        
        if(!isTimeBetweenTwoTime(OpenHrs,closeHrs,CurrentTime))
		{   
            ApcoaListeners.logInfo("Current timing don't lie between the operational hrs. of Parking");
			ApcoaListeners.logInfo("Checking the Error Msg");
			SA.assertEquals(ErrorMsg.getText(),"Exceeding operational hours");
			ApcoaListeners.logInfo(ErrorMsg.getText());
			
		}
		
		else 
		{ ApcoaListeners.logInfo("Current timing lie between the operational hrs. of Parking");
			for( int i=25;;i+=25)
			{  
			String ExpireTime=ExpiryTime.getText().split("at ")[1];
			ExpireTime+=":00";
			
			if(checkExceedingMsg())
			{     ApcoaListeners.logInfo(ExpireTime);
				  SA.assertEquals(isTimeBetweenTwoTime(OpenHrs,closeHrs,ExpireTime), false);
				 
				  break;
			}
			ApcoaListeners.logInfo("Rotating The Dailer");
			dailerRotator(i);
			if(i==100)
			  i=25;
			
			}
		}
        SA.assertAll();
	}
	
	public void CheckOperationalHrs2(OperationalHrsMapper mapper) throws ParseException, java.text.ParseException, InterruptedException
	{  
		String OpenHrs=mapper.getopeninghrs();
		String closeHrs=mapper.getclosinghrs();
		String  CurrentTime=findCurrentTime();
		String ParkingTime=parkingHrs.getText()+":"+parkingMins.getText()+":"+"00";
		
		ApcoaListeners.logInfo("Opening Time of Parking "+OpenHrs);
		ApcoaListeners.logInfo("Closing Time of Parking "+closeHrs);
	    ApcoaListeners.logInfo("Current Timing :"+CurrentTime);
		
	    if((!parkingHrs.getText().equals("00"))||(!parkingMins.getText().equals("00")))
	    {   System.out.println("moving to Zero");
	    	setToZero();
	    }
        
        if(!isTimeBetweenTwoTime(OpenHrs,closeHrs,CurrentTime))
		{   
            ApcoaListeners.logInfo("Current timing don't lie between the operational hrs. of Parking");
			ApcoaListeners.logInfo("Checking the Error Msg");
			SA.assertEquals(ErrorMsg.getText(),"Exceeding operational hours");
			clickOnDialer.click();
			try
			{   ApcoaListeners.logInfo("Checking the confirm pay button");
			SA.assertFalse(payAmount.isDisplayed());

			}
			catch(Exception e)
			{

			}
			ApcoaListeners.logInfo(ErrorMsg.getText());
			
		}
		
		else 
		{ ApcoaListeners.logInfo("Current timing lie between the operational hrs. of Parking");
			for( int i=25;;i+=25)
			{  
			String ExpireTime=ExpiryTime.getText().split("at ")[1];
			ExpireTime+=":00";
			if(!isTimeBetweenTwoTime(OpenHrs,closeHrs,ExpireTime))
			{     ApcoaListeners.logInfo(ExpireTime);
				  SA.assertEquals(checkExceedingMsg(), true);
				  clickOnDialer.click();
					try
					{  
				   
					 SA.assertFalse(payAmount.isDisplayed());
					
					}
					catch(Exception e)
					{
                        
					}
				  break;
			}
			
			ApcoaListeners.logInfo("Rotating The Dailer");
			dailerRotator(i);
			if(i==100)
			  i=25;
			
			}
		}
        SA.assertAll();
	}
	
	public void RotateToZero(int perc)
	{	
		System.out.println("Entered");
		if(perc==25)
		{    
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(206, 1643)).release().perform();
		// Thread.sleep(2000);
		}else if(perc==50)
		{
			
			action.press(PointOption.point(206, 1643)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(545, 1968)).release().perform();
			// Thread.sleep(2000);
		}
		else if(perc==75)
		{
			
			action.press(PointOption.point(545, 1968)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(849, 1646)).release().perform();
			//  Thread.sleep(2000);
		}
		else if(perc==100)
		{
			
			action.press(PointOption.point(849, 1646)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(481, 1403)).release().perform();
			//  Thread.sleep(2000);
		}
	
	}
	
	public void setToZero() throws InterruptedException
	{      
		for(int i=25;;i+=25)
			{
		   if((parkingHrs.getText().equals("00") && parkingMins.getText().equals("00")))
			{   System.out.println(parkingHrs.getText()+":"+parkingMins.getText()); 
				  break;
			}
		   RotateToZero(i);
			
			 if(i==100)
		      i=25;
	}
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

   
	public String checkTheParking()
	{
		try {
			if(btnstartsession.isDisplayed()==true)
			{  ApcoaListeners.logInfo("Parking Visible");
			return "True";}
		}
		catch(Exception e) {}

		ApcoaListeners.logInfo("Parking Not Visible");
		return "False";
	}
	
	public void MaxPrkingHrs() throws InterruptedException
	{
		ApcoaListeners.logInfo("Rotating The Dailer");
		for( int i=25;;i+=25)
		{   
			
		    int Hours=Integer.parseInt(parkingHrs.getText());
			int  Mins=Integer.parseInt(parkingMins.getText());
			if(Hours>=38 && Mins>00)
			{   
				
				ApcoaListeners.logInfo("Parking Hours    :"+Hours+":"+Mins);
				break;
			}
			
			
			dailerRotator(i);
			if(i==100)
			  i=25;
		}
	}
	
	
	

}
