package Pages.Mobile;

import java.io.IOException;
import java.util.ArrayList;
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

public class EVcharging {

	String ActualParkingName;

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_proceed')]")
	WebElement btnProceed;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_ev_charging_buy_pass') or contains(@resource-id,':id/tv_start_ev_charging_session')]")
	WebElement btnEVcharging;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbar2;

	@AndroidFindBy(xpath=".//android.widget.EditText[contains(@resource-id,':id/edt_Search')]")
	private WebElement searchParking;

	@AndroidFindBy(xpath="(//android.view.ViewGroup)[2]")
	private WebElement selectParking2;

	@AndroidFindBy(xpath="(//android.widget.LinearLayout)[4]")
	private WebElement selectParking;

	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[7]")
	private WebElement ParkingSearchbar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbartv;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_ev_charge_available')]")
	private WebElement EVChargingText1;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_plug_charge')]")
	private WebElement EVChargingText2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_next')]")
	private WebElement Proceed;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_error_scan')]")
	private WebElement ScanError;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_heading')]")
	private WebElement ScannerHeading;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/button_once')]")
	private WebElement btnJustOne;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_proceed')]")
	private WebElement proceed;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement allplugoccupied;

	@AndroidFindBy(xpath="(//android.view.ViewGroup[contains(@resource-id,':id/cl_plug')])[2]")
	private WebElement plug2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement close;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_select_from_list')]")
	private WebElement SelectFromList;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[contains(@resource-id,':id/tv_chargers_availability')])[1]")
	private WebElement pluglist;
	
	
 
	//android.widget.TextView[contains(@resource-id,':id/tv_chargers_availability')]
	






	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EVcharging (AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}

	public void EVCheck()throws InterruptedException
	{ 
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		//String parkingName = parkingMapper.getEpmpParkingIdentifier();Turning Torso
		// String fullParkingName = parkingMapper.getEpmpParkingName();
		String[] ParkingIdentifier= new String[] {"7002","3299","8391","6605"}; 
		String[] ParkingName = new String[] {"P-Hus Avenyn","Torsplan 2","P-Hus Kanikenäsbanken","Doktor Butlers gata 3-5"};

		for(int i=0;i<ParkingIdentifier.length;i++)
		{
			GettheParking2(ParkingIdentifier[i]);

			SA.assertEquals(ParkingName[i], ActualParkingName, "Parking Not Found");

			SA.assertEquals(checkEVChargingButton(ParkingName[i]),"True" ,"Test-FAILED");
			SA.assertAll();
			// System.out.println(this.SC.checkEpmpStartButton());
		}
	}



	public String checkEVChargingButton(String Parking)throws InterruptedException
	{ 

		ApcoaListeners.logInfo("Checking for EV Charging Button in EPMP");
		try {
			if (btnEVcharging.isDisplayed()){
				ApcoaListeners.logInfo("EV Charging Button Found -->"+Parking);
				return "True";
			}
			else 
			{ApcoaListeners.logInfo("EV Charging Button Not Found -->"+Parking);
			return "False";}
		}
		catch (Exception e)
		{   
			ApcoaListeners.logInfo("EV Charging Button Not Found -->"+Parking);
			return "False";
		}

	}

	public String  GettheParking2(String ParkingName){
		try{
			ApcoaListeners.logInfo("GettheParking start");

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar2,30);
			ParkingSearchbar2.click();

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,searchParking,30);
			searchParking.sendKeys(ParkingName);

			Thread.sleep(4000);
			boolean temp = selectParking2.isDisplayed();
			if(temp){
				selectParking2.click();
			}else{
				selectParking.click(); 
			}

			try {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnProceed,7);
				btnProceed.click();
			}
			catch(Exception e) {}

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar,30);
			ApcoaListeners.logInfo("parking name: "+ParkingSearchbartv.getText());
			ActualParkingName = ParkingSearchbartv.getText();
			ApcoaListeners.logInfo("GettheParking  end");
		}catch(Exception e){
			ApcoaListeners.logInfo("GettheParking start: error:"+ e.toString());
		}
		return ActualParkingName;
	}



	public List<Boolean> CheckEvChargingQrFlowNativeCamera() throws InterruptedException, IOException
	{   SoftAssert SA=new SoftAssert();
	List<Boolean> RESULT = new ArrayList<Boolean>();
	SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	ApcoaListeners.logInfo("Checking the EV QR Flow through Native Camera");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnJustOne,60);
	ApcoaListeners.logInfo("Going to click on just once button");
	btnJustOne.click();

	//    	     CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText1,60);
	//    	     ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText1.getText());
	//    	     SA.assertEquals(EVChargingText1.getText(),"EV Charging available here");
	//    	     CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,6);
	//    	     Proceed.click();
	//    	     CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText2,60);
	//    	     ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText2.getText());
	//    	     SA.assertEquals(EVChargingText2.getText(),"Plug the Charger");
	//    	     CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,6);
	//    	     Proceed.click();
	//    	     try{
	//    	     CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ScannerHeading,6);
	//    	     SA.assertEquals(ScannerHeading.getText(),"Scan Charging Station");
	//    	     ApcoaListeners.logInfo("Found the Scanner Heading  :   "+ScannerHeading.getText());
	//    	     }
	//    	     catch(Exception e)
	//    	     {   CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ScanError,6);
	//  	         ApcoaListeners.logInfo("Found the Scan error    :  "+ScanError.getText());
	//    	    	 SA.assertEquals(ScanError.getText(),"EV Charging station selected currently unavailable");
	//    	     }
	//    	     
	//    	     SA.assertAll();

	boolean res;
	try { 
		RESULT= CheckEVplugPage();
	}
	
		catch(Exception e)
		{
			ApcoaListeners.logInfo("Found the Scan error    :  "+ScanError.getText());
			if(ScanError.getText().equals("EV Charging station selected currently unavailable"))
			{
				
				RESULT.add(false);
			    RESULT.add(true);
			    }
			
			
			ApcoaListeners.logInfo("going to click on close btn");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,close,10);
			close.click();
			//CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GoBacktoSideBar,10);
			
			GoBacktoSideBar.click();
			//((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			

		
	}
	return RESULT; 

	}

	public List<Boolean> CheckEvChargingQrFlowAppCamera() throws InterruptedException, IOException
	{  List<Boolean> RESULT=new ArrayList<Boolean>();
	SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	ApcoaListeners.logInfo("Checking the Flow through the App Scanner");
	boolean res;
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEVcharging,30);
	btnEVcharging.click();
	ApcoaListeners.logInfo("Scanning the QR");
	try {
		 RESULT=CheckEVplugPage();
	}
	catch(Exception e)
	{
		ApcoaListeners.logInfo("Found the Scan error    :  "+ScanError.getText());
		if(ScanError.getText().equals("EV Charging station selected currently unavailable"))
			{
			
			RESULT.add(false);
		    RESULT.add(true);
		    System.out.println(RESULT);
		    }
		System.out.println(RESULT);
	
		ApcoaListeners.logInfo("going to click on close btn");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,close,10);
		close.click();
		
	//	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GoBacktoSideBar,10);
		
		GoBacktoSideBar.click();
		
		//((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		System.out.println(RESULT);

	}
	return RESULT;
	}
	
	
	By PlugAvailability = By.xpath("//android.widget.TextView[contains(@resource-id,':id/tv_chargers_availability') or contains(@resource-id,':id/tv_chargers_busy')]");
    
	
	public List<Boolean>  GetEvDataFromSelectList() throws InterruptedException {
    	 
    	 
    	 List<Boolean> listplugavailablity=new ArrayList<Boolean>();
    	 
    	 CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEVcharging,30);
    		btnEVcharging.click();
    		
    		try {
    			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText1,10);
    			ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText1.getText());
    			SA.assertEquals(EVChargingText1.getText(),"EV Charging available here");
    			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,5);
    			Proceed.click();
    			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText2,5);
    			ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText2.getText());
    			SA.assertEquals(EVChargingText2.getText(),"Plug the Charger");
    			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,5);
    			Proceed.click();

    		}
    		catch(Exception e)
    		{

    		}

    		
    		
    	ApcoaListeners.logInfo("Scanning the QR");
    	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SelectFromList,30);
    	SelectFromList.click();
    	Thread.sleep(10000);
//    	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,pluglist,30);
//    	pluglist.click();
    	List<WebElement> PA = this.driver.findElements(PlugAvailability);
    	for(int i=0;i<PA.size();i++)
    	{  System.out.println(PA.get(i).getText());
    	  if(PA.get(i).getText().equals("ALL BUSY"))	
    	  {
    		  listplugavailablity.add(false);
    	  }
    	  else
    	  {
    		  listplugavailablity.add(true);
    	  }
    	}
    	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GoBacktoSideBar,30);
    	GoBacktoSideBar.click();
    	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GoBacktoSideBar,30);
    	GoBacktoSideBar.click();
    	
    	return listplugavailablity;
    	
    	
     }



	By PlugType = By.xpath("//*[contains(@resource-id,':id/tv_plug_type')]");
	By PlugNumber= By.xpath("//*[contains(@resource-id,':id/tv_plug_number')]");

	public List<Boolean> CheckEVplugPage() throws InterruptedException
	{  boolean temp;
	  List<Boolean> list=new ArrayList<Boolean>();
	    
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText1,10);
			ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText1.getText());
			SA.assertEquals(EVChargingText1.getText(),"EV Charging available here");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,5);
			Proceed.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EVChargingText2,5);
			ApcoaListeners.logInfo("Validating the Text    :"+EVChargingText2.getText());
			SA.assertEquals(EVChargingText2.getText(),"Plug the Charger");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,5);
			Proceed.click();

		}
		catch(Exception e)
		{

		}

		Thread.sleep(5000);
		boolean res=true;
		List<WebElement> PT = this.driver.findElements(PlugType);
		List<WebElement> PN = this.driver.findElements(PlugNumber);

		for(int i=0;i<PT.size();i++)
		{   
			ApcoaListeners.logInfo(PT.get(i).getText()+"   "+PN.get(i).getText());
			if(PT.get(i).getText().equals("Type null"))
			{   temp=true;
				res= false;
				break;
			}
			if(PN.get(i).getText().equals("null"))
			{   temp=true;
				res= false;
				break;
			}
			
			
		}
		
		temp=true;
         
		
        

		Thread.sleep(5000);
		ApcoaListeners.logInfo("Going to click on Proceed Button");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,proceed,15);
		proceed.click();
		Thread.sleep(4000);
		try {
			if(allplugoccupied.isDisplayed())
			{  
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,close,15);
			close.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,plug2,15);
			plug2.click();
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,proceed,5);
			proceed.click();
			}
		}
		catch(Exception e) {}
        
		list.add(temp);
		list.add(res);
		
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

		
		return list;

	}
	
	
	public void getbackparking(String Parking)
	{
		try {
			 CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEVcharging,10);
			 System.out.println("found the parking");
	    		//btnEVcharging.click();
		}
		catch(Exception e)
		{
			EVcharging EV=new EVcharging(AutomationConfiguration.AppiumDriver);
			System.out.println("getting back the parking");
			EV.GettheParking2(Parking);
			
		}
	}

}
