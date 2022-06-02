package Mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import java.io.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import DataDriven.ExcelDriven;
import Mobile.Utils.SessionUtils;
import MobileObjectMapper.BuyPassMapper;
import MobileObjectMapper.CorporateProfileMapper;
import MobileObjectMapper.FilterCheck;
import MobileObjectMapper.FreeParkingMapper;
import MobileObjectMapper.LoginMapper;
import MobileObjectMapper.OperationalHrsMapper;
import MobileObjectMapper.ParkingMapper;

import MobileObjectMapper.VehicleMapper;
import Pages.Mobile.ActiveVehicleCheck;
import Pages.Mobile.EVcharging;
import Pages.Mobile.LaunchQrReader;
import Pages.Mobile.NewUserRegistration;
import Pages.Mobile.OperationalHrs;
import Pages.Mobile.PageAddVehicle;
import Pages.Mobile.PageBuyPass;
import Pages.Mobile.PageHomeApcoa;
import Pages.Mobile.PageLogin;
import Pages.Mobile.PageMaxTimeRework;
import Pages.Mobile.PageProfileAutoSwitch;
import Pages.Mobile.PageSelectCountry;
import Pages.Mobile.SessionCreationPage;
import Pages.Mobile.referralPromo;
import TestNGListeners.ApcoaListeners;
import freemarker.core.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestLogin {


	public  String ActiveSessionId;
	public static String AppliedPromo;

	WebDriver driver;

	@Parameters({ "Environment", "Country","Tenant","Platform","Profile" })
	@BeforeSuite
	public void initializeDriver(String ennv, String country,String tenant, String platform,String Profile) throws IOException{
		AutomationConfiguration.Tenant = System.getProperty("tenant");
		AutomationConfiguration.Environment = System.getProperty("environment");
		AutomationConfiguration.Country = System.getProperty("country");
		AutomationConfiguration.Platform = System.getProperty("platform");
		AutomationConfiguration.Profile=System.getProperty("profile");
		CreateSession.readConfigFile("/src/test/java/resources/"+AutomationConfiguration.Platform+AutomationConfiguration.Tenant+".properties");

	}
	@AfterSuite
	public void Teardown()
	{
		AutomationConfiguration.AppiumDriver.quit();
	}

	@DataProvider
	public LoginMapper[] getLoginData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Login");	
		ObjectMapper mapper = new ObjectMapper();
		LoginMapper []login = new LoginMapper[1];
		login[0] = mapper.readValue(data, LoginMapper.class);

		return login;	
	}

	@DataProvider
	public VehicleMapper[] getVehicleData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Add Vehicle");
		ObjectMapper mapper = new ObjectMapper();
		VehicleMapper []vehicle = new VehicleMapper[1];
		vehicle[0] = mapper.readValue(data, VehicleMapper.class);

		return vehicle;	
	}

	@DataProvider
	public ParkingMapper[] getParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"Session");		
		ObjectMapper mapper = new ObjectMapper();
		ParkingMapper []parking = new ParkingMapper[1];
		parking[0] = mapper.readValue(data, ParkingMapper.class);

		return parking;	
	}

	@DataProvider
	public ParkingMapper[] getReverseQRParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"ReverseQR");		
		ObjectMapper mapper = new ObjectMapper();
		ParkingMapper []parking = new ParkingMapper[1];
		parking[0] = mapper.readValue(data, ParkingMapper.class);

		return parking;	
	}

	@DataProvider
	public BuyPassMapper[] getBuyPassData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"BuyPass");		
		ObjectMapper mapper = new ObjectMapper();
		BuyPassMapper []pass = new BuyPassMapper[1];
		pass[0] = mapper.readValue(data, BuyPassMapper.class);

		return pass;	
	}

	@DataProvider
	public FreeParkingMapper[] getFreeParkingData() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"FreeParking");		
		ObjectMapper mapper = new ObjectMapper();
		FreeParkingMapper []promo = new FreeParkingMapper[1];
		promo[0] = mapper.readValue(data, FreeParkingMapper.class);

		return promo;	
	}

	@DataProvider
	public FilterCheck[] getFilterCheck() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"FilterCheck");		
		ObjectMapper mapper = new ObjectMapper();
		FilterCheck []promo = new FilterCheck[1];
		promo[0] = mapper.readValue(data, FilterCheck.class);

		return promo;	
	}

	@DataProvider
	public OperationalHrsMapper[] getOperationalHrsMapper() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"operationalHrs");		
		ObjectMapper mapper = new ObjectMapper();
		OperationalHrsMapper []hrs = new OperationalHrsMapper[1];
		hrs[0] = mapper.readValue(data, OperationalHrsMapper.class);

		return hrs;	
	}
	
	@DataProvider
	public CorporateProfileMapper[] getCorporateProfileMapper() throws Exception{
		String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"corporateprofile");		
		ObjectMapper mapper = new ObjectMapper();
		CorporateProfileMapper []hrs = new CorporateProfileMapper[1];
		hrs[0] = mapper.readValue(data, CorporateProfileMapper.class);

		return hrs;	
	}



	@BeforeMethod
	public void initializeAssertions(){
		AutomationConfiguration.SoftAsserts = new SoftAssert();
	}

	@AfterMethod
	public void AssertAll(){
		AutomationConfiguration.SoftAsserts.assertAll();;
	}


	/*
	 * Implementing the select country from list .
	 */

	@Test(priority=1)
	public void selectCountry() throws Exception{
		Thread.sleep(2000);
		PageSelectCountry selectcountry = new PageSelectCountry(AutomationConfiguration.AppiumDriver);
		SoftAssert softAssert = new SoftAssert();
		if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")||AutomationConfiguration.Tenant.equalsIgnoreCase("GMP")) {
			//Thread.sleep(5000);   //10000
			selectcountry.selectCountryClick();
			Thread.sleep(2000);
			selectcountry.selectCountry(AutomationConfiguration.Country);
			softAssert.assertEquals(AutomationConfiguration.Country.toUpperCase(), selectcountry.CountrySelected.toUpperCase(),"Country not selected" );		
		}
		Thread.sleep(3000);//5000
		selectcountry.btnLoginClick();
		try {
			softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}


	/*
	 * Implementing the login of user,enterning login credentials
	 * Environment:Staging/Production
	 */
	@Test(priority=2,dataProvider="getLoginData")
	public void loginAppcoa(LoginMapper loginMapper) throws InterruptedException{
		//Thread.sleep(4000);
		PageLogin login = new PageLogin(AutomationConfiguration.AppiumDriver);
		Thread.sleep(3000);
		login.enterCredentials(loginMapper.getEmail(), loginMapper.getPassword());
		Thread.sleep(2000);//8000
		login.clickContinue();

		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.acceptPushNotification();
		home.checkUserName();
		home.cancelActivatePopUp();
		home.cancelQuestionPopUp();


		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(loginMapper.getUsername().toUpperCase(),home.Username.toUpperCase() );
		softAssert.assertAll();	
	}//app = /Users/karankumaragarwal/Downloads/staging_1.0.60_100_apcoaflow_2021120212.apk


	/*
	 * Implementing add a vehicle.
	 * Environment:Staging/Production
	 */
	@Test(priority=3,dataProvider="getVehicleData")
	public void addVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle addvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
		if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa"))
			addvehicle.addVehicle2(vehicleno);
		else
			addvehicle.addVehicle2(vehicleno);
		Thread.sleep(2000);
		String vno = addvehicle.getfirstvehiclelpr();
		softAssert.assertEquals(vehicleno, vno,"Vehicle LPR not added");
		PageAddVehicle.goBack();
		softAssert.assertAll();

	}


	/*
	 * Implementing deletion of vehicle.
	 * Environment:Staging/Production
	 */
	@Test(priority=6,dataProvider="getVehicleData")
	public void deleteVehicle(VehicleMapper vehicleMapper) throws InterruptedException{
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		Thread.sleep(2000);
		String vehicleno = vehicleMapper.getLpr();
		SoftAssert softAssert = new SoftAssert();
		PageAddVehicle delvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
		delvehicle.deletelpr();
		Thread.sleep(2000);
		String vno = delvehicle.getfirstvehiclelpr();
		softAssert.assertNotEquals(vehicleno, vno,"Vehicle LPR not deleted");
		PageAddVehicle.goBack();
		softAssert.assertAll();
	}

	/*
	 * Implementing Start Session,Extend Session,Stop session
	 * Environment:Staging/Production
	 */
	@Test(priority = 5, dataProvider = "getParkingData")
	public void startExtendStopSession(ParkingMapper parkingMapper) throws InterruptedException {
		//Thread.sleep(2000);
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String country = AutomationConfiguration.Country;
		SoftAssert softAssert = new SoftAssert();

		SessionUtils sessionUtils = new SessionUtils(SC, country, softAssert);
		sessionUtils.startSession(parkingMapper);

		float finalPrice = sessionUtils.extendSession(parkingMapper);
		sessionUtils.stopSession(finalPrice,parkingMapper);

		softAssert.assertAll();
		PageAddVehicle.goBack();
	}


	/*
	 * Implementing Buy Pass in Austria , Sweden and poland
	 * Environment:Production
	 */
	@Test(priority=7,dataProvider = "getBuyPassData")
	public void BuyPass(BuyPassMapper passMapper)throws InterruptedException{
		Thread.sleep(4000);
		PageBuyPass PB= new PageBuyPass(AutomationConfiguration.AppiumDriver);
		String Parking =passMapper.getParkingidentifier();
		String PassPromo=passMapper.getpromo();

		PB.searchAreaAndBuyPass(Parking,PassPromo);
	}


	/*
	 * Implementing the check of Start Button in EPMP carparks.
	 * Environment:Staging/Production
	 */
	@Test(priority = 12)
	public void EpmpStartButtonCheck() throws InterruptedException {

		Thread.sleep(2000);
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String country = AutomationConfiguration.Country;
		SoftAssert softAssert = new SoftAssert();

		SessionUtils sessionUtils = new SessionUtils(SC, country, softAssert);
		sessionUtils.EpmpCheck();
		softAssert.assertAll();

	}

	/*  public static void main(String[] args) throws Exception
    {    AutomationConfiguration.Tenant="Apcoa";
         AutomationConfiguration.Country="Austria";
         AutomationConfiguration.Environment="Production";
    	String excelfilepath = System.getProperty("user.dir") + "/src/test/java/resources/"+AutomationConfiguration.Tenant+"Dataset.xlsx";
		ExcelDriven.readExcelFile(excelfilepath, AutomationConfiguration.Environment);
		String data = ExcelDriven.readDataRowandColumn(AutomationConfiguration.Environment,AutomationConfiguration.Country,"BuyPass");		
		ObjectMapper mapper = new ObjectMapper();
		BuyPassMapper []pass = new BuyPassMapper[1];
		pass[0] = mapper.readValue(data, BuyPassMapper.class);
        System.out.println(pass[0].getParkingidentifier());
		//return pass;
    }*/


	/*
	 * Implementing the Changing the Tarrif During Start Session in Staging Environment.
	 * Environment:Staging
	 */
	// @Test(priority = 4, dataProvider = "getParkingData")
	public void multipleTarrifCheck(ParkingMapper parkingMapper) throws InterruptedException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		//    String fullParkingName = parkingMapper.getParkingname();

		SC.GettheParking(parkingName);
		SC.check_multiple_tarrif(parkingMapper);

	}


	/*
	 * Implementing the Check of Intial price in extend session after starting the session in free parking in the poland.
	 * Environment:Staging/Production
	 */
	@Test(priority=11,dataProvider = "getParkingData")
	public void TarrifAfterFreeParkingHrsInExtendSession(ParkingMapper parkingMapper) throws InterruptedException
	{
		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.dismissFoodAlert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String country = AutomationConfiguration.Country;

		String parkingName = parkingMapper.getParkingidentifier();
		SC.GettheParking(parkingName);
		SC.PolandTarrif(country, parkingMapper);
		SC. StoptheSession();

	}
	
	

	/*
	 * Implementing the Change of profile in Start Session.
	 * Environment:Staging/Production.
	 */
	// @Test(priority = 4, dataProvider = "getParkingData")
	public void profileChange(ParkingMapper parkingMapper) throws InterruptedException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		SC.GettheParking(parkingName);
		SC.changeProfile();
		String country = AutomationConfiguration.Country;
		SC.dialerMovement(country,parkingMapper);
		SC.PaymentConfirmation();
	}


	// @Test(priority=4)
	public void CheckInvoice() throws InterruptedException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.CheckInvoiceAndResend();
	}


	//@Test(priority=4,dataProvider = "getParkingData")
	public void CheckConvenienceFee(ParkingMapper parkingMapper) throws InterruptedException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		SC.GettheParking(parkingName);
		SC.ConvenienceFee();
	}

	/*
	 * Checking the Start of session  without payments details in a Municipality CarPark in Denmark.
	 * Environment:Production
	 */
	@Test(priority=12)
	public void NoSessionWithoutPaymentMethod() throws InterruptedException
	{
		Thread.sleep(4000);
		PageLogin login = new PageLogin(AutomationConfiguration.AppiumDriver);
		Thread.sleep(3000);
		login.enterCredentials("automation_newuser112333@yopmail.com"," testing");
		Thread.sleep(2000);//8000
		login.clickContinue();

		PageHomeApcoa home = new PageHomeApcoa(AutomationConfiguration.AppiumDriver);
		home.acceptPushNotification();
		home.checkUserName();
		home.cancelActivatePopUp();
		home.cancelQuestionPopUp();


		//SoftAssert softAssert = new SoftAssert();
		//softAssert.assertEquals(loginMapper.getUsername().toUpperCase(),home.Username.toUpperCase() );
		//softAssert.assertAll();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.GettheParking("kommune");
		SC.SessionCheck();
	}

	/*
	 * Implementing the Creation of Buisness Profile after deleting the old Buisness Profile.
	 * Environment:Staging
	 */
	@Test(priority=8)
	public void BuisnessProfileCreation() throws InterruptedException
	{   
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.DeleteBuisnessProfile();
		SC.AddBuisnessprofile();

	}


	/*
	 * Check user should not be charged any fee for free parking promo codes.
	 * Environment:Production
	 */
	@Test(priority=9,dataProvider = "getFreeParkingData")
	public void freeParkingCheck(FreeParkingMapper parkingMapper) throws InterruptedException
	{   
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		SC.GettheParking(parkingName);
		SC.CheckPriceInFreeParking(parkingMapper);
		SC. StoptheSession();

	}
	
//	@Test(priority=10)
	public void ChangingDefaultProfile() throws InterruptedException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		// SC.changingDefaultProfile("Corporate");
		SC.changingDefaultProfile(AutomationConfiguration.Profile);
	}



	@Test(priority=12,dataProvider = "getReverseQRParkingData")
	public void StartPostpay(ParkingMapper parkingMapper) throws InterruptedException, IOException 
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		String parkingName = parkingMapper.getParkingidentifier();
		SC.changingDefaultProfile(AutomationConfiguration.Profile);
		Thread.sleep(3000);
		SC.GettheParking(parkingName);
		ActiveSessionId= SC.StartPostpaySession();


	}
	
	@Test(priority=13)
	public void DiscountByQRScan() throws InterruptedException, IOException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		//SC.AddDiscountByQRScan(2);
		SC.AddDiscountByQRScan(2);
	}

	@Test(priority=14)
	public void StopPostpay() throws InterruptedException, IOException{

		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.StopPostpaySession(ActiveSessionId,AppliedPromo);

	}


	// @Test(priority=4)
	public void HundredPerDiscount()
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	}

    
	@Test(priority=4,dataProvider = "getParkingData")
	public void deepLink(ParkingMapper parkingMapper) throws IOException, InterruptedException
	{   SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	LaunchQrReader LR = new LaunchQrReader(AutomationConfiguration.AppiumDriver);

	SC.removeQR();
	SC.excCommand("DirectLinkQr");
	LR.launchQrReader();
	Thread.sleep(5000);
	SC.dialerMovement(AutomationConfiguration.Country,parkingMapper);
	LR.KillQrScanner();

	}


	@Test(priority=4,dataProvider = "getFilterCheck")
	public void OperationalHrsFilterCheck(FilterCheck parkingMapper) throws InterruptedException
	{
	SoftAssert softAssert = new SoftAssert();
	SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	OperationalHrs op= new OperationalHrs(AutomationConfiguration.AppiumDriver);
	String  parkingName=parkingMapper.getParkingidentifier();
	ApcoaListeners.logInfo("Searching the parking"+parkingMapper.getParkingname());
	SC.GettheParking(parkingName);
	Thread.sleep(5000);
	ApcoaListeners.logInfo("Checking the Parking");
	softAssert.assertEquals(op.checkTheParking(), "True");
	ApcoaListeners.logInfo("Applying the filter");
	op.changeFilter();
	Thread.sleep(4000);
	ApcoaListeners.logInfo("Checking The Parking");
	SC.GettheParking(parkingName);
	softAssert.assertEquals(op.checkTheParking(), "False");
	softAssert.assertAll();
	
	}


	@Test(priority=12,dataProvider = "getOperationalHrsMapper")
	public void SessionAfterOperationalHrs(OperationalHrsMapper parkingMapper) throws InterruptedException, ParseException, java.text.ParseException
	{   
		SoftAssert softAssert = new SoftAssert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		OperationalHrs op= new OperationalHrs(AutomationConfiguration.AppiumDriver);
		String  parkingName=parkingMapper.getParkingidentifier();
		ApcoaListeners.logInfo("Searching the parking");
		SC.GettheParking(parkingName);
		Thread.sleep(5000);
		SC.StartsessionforParkingwithPass();
		op.CheckOperationalHrs2(parkingMapper);
	}
	
	@Test(priority=12)
	public void ProfileAutoSwitch() throws InterruptedException
	{
		/*
		 *  FOR SWEDEN -----------------------------------------------------
		 */
		// Case 1: Both Personal and Corporate , Profile Selected-Personal , Tarrif-Regular , Expected Behavior-Able to start the parking ,Profile_switch-- Personal
		Map<String,String> myMap1 = new HashMap<String, String>();
		Map<String,String> myMap2 = new HashMap<String, String>();
		Map<String,String> myMap3 = new HashMap<String, String>();
		Map<String,String> myMap4 = new HashMap<String, String>();
		Map<String,String> myMap5 = new HashMap<String, String>();
		Map<String,String> myMap6 = new HashMap<String, String>();
		Map<String,String> myMap7 = new HashMap<String, String>();
		Map<String,String> myMap8 = new HashMap<String, String>();

		List<Map<String , String>> myMap  = new ArrayList<Map<String,String>>();


		myMap.add(0,myMap1);
		myMap.add(1,myMap2);
		myMap.add(2,myMap3);
		myMap.add(3,myMap4);
		myMap.add(4,myMap5);
		myMap.add(5,myMap6);
		myMap.add(6,myMap7);
		myMap.add(7,myMap8);
		 

		myMap1.put("DefaultProfile", "Personal Profile");
		myMap1.put("parking", "3450");                       // Case1
		myMap1.put("SwitchedProfile", "Personal");
		myMap1.put("startsession", "true");

		myMap2.put("DefaultProfile", "Personal Profile");
		myMap2.put("parking", "50224");                     // Case2
		myMap2.put("SwitchedProfile", "Region Stockholm Profile");
		myMap2.put("startsession", "true");

		myMap3.put("DefaultProfile", "Personal Profile");
		myMap3.put("parking", "50220");                      //Case3
		myMap3.put("SwitchedProfile", "Region Stockholm Profile");
		myMap3.put("startsession", "true");

		myMap4.put("DefaultProfile", "Personal Profile");
		myMap4.put("parking", "7385");                       //Case4
		myMap4.put("SwitchedProfile", "Personal");
		myMap4.put("startsession", "true");

		myMap5.put("DefaultProfile", "Region Stockholm Profile");
		myMap5.put("parking", "3450");                    //Case5
		myMap5.put("SwitchedProfile", "Personal");
		myMap5.put("startsession", "true");

		myMap6.put("DefaultProfile", "Region Stockholm Profile");
		myMap6.put("parking", "50224");
		myMap6.put("SwitchedProfile", "Region Stockholm Profile");
		myMap6.put("startsession", "true");

		myMap7.put("DefaultProfile", "Region Stockholm Profile");
		myMap7.put("parking", "50220");
		myMap7.put("SwitchedProfile", "Region Stockholm Profile");
		myMap7.put("startsession", "true");

		myMap8.put("DefaultProfile", "Region Stockholm Profile");
		myMap8.put("parking", "7385");
		myMap8.put("SwitchedProfile", "Personal");
		myMap8.put("startsession", "true");


        SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		PageProfileAutoSwitch PA =new PageProfileAutoSwitch(AutomationConfiguration.AppiumDriver);



		for (Map<String, String> map : myMap)
		{   ApcoaListeners.logInfo("Searching the parking"+map);
			ApcoaListeners.logInfo("Default profile ");
			PA.ProfileAutoSwitchCheck(map.get("DefaultProfile"),map.get("parking"),map.get("SwitchedProfile"),map.get("startsession"));
			PA.checkProfileInExtend(map.get("SwitchedProfile"));
			SC. StoptheSession();
		}

	}
	

	@Test(priority=16)
	public void EVchargingButtonCheck() throws InterruptedException
	{   String ActualParkingName;
	SoftAssert SA=new SoftAssert();

	EVcharging ev= new EVcharging(AutomationConfiguration.AppiumDriver);
	//ev.EVCheck();

	String[] ParkingIdentifier= new String[] {"7002","3299","8391","6605"}; 
	String[] ParkingName = new String[] {"P-Hus Avenyn","Torsplan 2","P-Hus Kanikenäsbanken","Doktor Butlers gata 3-5"};

	for(int i=0;i<ParkingIdentifier.length;i++)
	{ 
		
	ApcoaListeners.logInfo("Parking Area Code ------> "+ParkingIdentifier[i]);
	ApcoaListeners.logInfo("Parking Name          ------> "+ParkingName[i]);
	ActualParkingName= ev.GettheParking2(ParkingIdentifier[i]);

	SA.assertEquals(ParkingName[i], ActualParkingName, "Parking Not Found");

	SA.assertEquals(ev.checkEVChargingButton(ParkingName[i]),"True" ,"Test-FAILED");

	}
	
	SA.assertAll();

	}
	
	@Test(priority=8,dataProvider = "getCorporateProfileMapper")
	public void CorporateProfileCreation(CorporateProfileMapper mapper) throws InterruptedException
	{   SoftAssert SA=new SoftAssert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		boolean Temp1=SC.DeleteCorporateProfile(mapper.getcorporate()+" Profile");//Region Värmland Profile
		SA.assertEquals(Temp1, true);
		boolean Temp2=SC.AddCorporateprofile(mapper.getcorporate(),mapper.getcorporateid(),mapper.getemail());
		SA.assertEquals(Temp2,true);
		SA.assertAll();

	}
	
	
	@Test(priority=15)
	public void EditPersonalDetails() throws InterruptedException
	{   SoftAssert SA=new SoftAssert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.editpersonalDetails("ABC","CDEF");
		SA.assertAll();

	}
	
	
	
	
	
	
	@Test(priority=11,dataProvider = "getParkingData")
	public void CheckActivevehicle(ParkingMapper parkingMapper) throws InterruptedException
	{    SoftAssert SA=new SoftAssert();
		String parkingName=parkingMapper.getParkingidentifier();
		
		ActiveVehicleCheck addvehicle = new ActiveVehicleCheck(AutomationConfiguration.AppiumDriver);
		ArrayList<ArrayList<String>> TotalVehicleList =  addvehicle.getVehicleList();
	  //  addvehicle.TakeScreenShotAndCompare();
	    SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	    SC.GettheParking(parkingName);
	    SC.StartsessionforParkingwithPass();
	    ArrayList<String> Vehicle= addvehicle.getVehicleListInSession();
	    
	    for(int i=0;i<TotalVehicleList.get(1).size();i++)
	    {   ApcoaListeners.logInfo("Checking InActive_Vehicle  "+TotalVehicleList.get(1).get(i));
	    	SA.assertEquals(Vehicle.contains(TotalVehicleList.get(1).get(i)), false);
	    }
	    SA.assertAll();
	    
	    
	}
	
	
	
	public static String   vnum;
	@Test(priority=11 ,dataProvider = "getParkingData")
	public void CheckReferralPromo(ParkingMapper parkingMapper) throws InterruptedException
	{  
		String ReferralCode;
	    boolean result;
	    SoftAssert SA=new SoftAssert();
	    String ParkingName=parkingMapper.getParkingidentifier();
		referralPromo RP=new referralPromo(AutomationConfiguration.AppiumDriver);
		ReferralCode=RP.getReferralCode();
		
		NewUserRegistration  NewUsr= new NewUserRegistration(AutomationConfiguration.AppiumDriver);
		NewUsr.FillDetails(ReferralCode);
	    NewUsr.AddVehicle();
	    NewUsr.AddCard();
	//    NewUsr.VerifyRegistration(vnum);
	   result= RP.CheckFreePromo(ParkingName,"FLAT1TIME");
	   SA.assertEquals(result, true);
	   SA.assertAll();
	}
	
	
	@Test(priority=11 ,dataProvider = "getParkingData")
	public void MaxHrsParkig(ParkingMapper parkingMapper) throws InterruptedException
	{
	 SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
	 String ParkingName=parkingMapper.getParkingidentifier();
	 SC.GettheParking(ParkingName);
	 SC.StartsessionforParkingwithPass();
	 Thread.sleep(5000);
	 OperationalHrs OP=new OperationalHrs(AutomationConfiguration.AppiumDriver);
	 OP.MaxPrkingHrs();
	 
	}
	
	
//	@Test(priority=14)
//	public void EVDeepLinkQR() throws IOException, InterruptedException
//	{
//		
//		 SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
//			LaunchQrReader LR = new LaunchQrReader(AutomationConfiguration.AppiumDriver);
//
//			SC.removeQR();
//			SC.excCommand("EVChargingQR");
//			LR.launchQrReader();
//			EVcharging EV=new EVcharging(AutomationConfiguration.AppiumDriver);
//			EV.CheckEvChargingQrFlow();
//	
//	}
	
	@Test(priority=15)
	
	public void CheckSessionPriceinDK() throws InterruptedException
	{   SoftAssert SA=new SoftAssert();
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.GettheParking("kommune");
		String res=SC.checkthesessionTarrif();
		SC. StoptheSession();
		SA.assertEquals(res,"0");
		SA.assertAll();
	}
	
	
	
	@Test(priority=16)
	public void TimeBasedMaxRework() throws InterruptedException, ParseException, java.text.ParseException
	{  
		PageMaxTimeRework PW=new PageMaxTimeRework(AutomationConfiguration.AppiumDriver);
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.GettheParking("maxima");
		//SC.StartsessionforParkingwithPass();
		//PW.SelectTarrif("TIME_BASED");
//		
		//PW.CheckMaxima(20.00,11.00,50.00,"05:00:00");
		
		PW.SelectTarrif("NOTTIME_BASED");
		PW.CheckMaxima(20.00,11.00,40.00,"none");
		
	}
	
	
	@Test(priority=10,dataProvider = "getParkingData")
	public void verifyInvoicePDF(ParkingMapper parkingMapper) throws InterruptedException, IOException
	{
		SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		SC.navigateToDownloadPDF(parkingMapper);
		//VerifyPDF VP=new VerifyPDF();
		//VP.SendPDF("AT46303718006");
	}
	
	
	
	
@Test(priority=14)
	
	public void EVTest() throws IOException, InterruptedException
	{   SoftAssert SA=new SoftAssert();
	    SessionCreationPage SC = new SessionCreationPage(AutomationConfiguration.AppiumDriver);
		LaunchQrReader LR = new LaunchQrReader(AutomationConfiguration.AppiumDriver);
		EVcharging EV=new EVcharging(AutomationConfiguration.AppiumDriver);
		
		
		String []ParkingName= new String[]{"P-Hus_Avenyn","P-hus_Duvan","Ystadvgen13"};//,"P-Hus_Avenyn","P-hus_Duvan","Ystadvgen13"};//
		String []ParkingAreaCode= new String[]{"7002","8001","2893"};//,"7002","8001","2893"};//,"8001"
		String [][] NameOfChargingStation = new String[][] {};
		
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		  map.put("P-Hus_Avenyn",new String[]{"P01-02","P03-04","P05-06","P07-08","P09-10","P11-12"});//
		  map.put("P-hus_Duvan", new String[] {"P01-02","P03-04","P05-06","P07-08","P09-10","P11-12"});
		  map.put("TurningTorso",new String[] {"P01-02","P03-04","P05-06"} );
		  map.put("Ystadvgen13", new String[] {"P106-107","P108-109","P110-111"});
	 
		for(int i=0;i<ParkingName.length;i++)
		{   
			List<Boolean> SelectFromList=new ArrayList<Boolean>();
			List<Boolean> ScanQrfromAppCamera=new ArrayList<Boolean>();
			List<Boolean> ScanQrfromNativeCamera=new ArrayList<Boolean>();
			
			ApcoaListeners.logInfo("Checking The EV QR FLOW for the Parking -------->"+ParkingName[i]);
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println();
			ApcoaListeners.logInfo("getting the parking with Area Code"+ParkingAreaCode[i]+":"+ParkingName[i]);
			EV.GettheParking2(ParkingAreaCode[i]);
			Thread.sleep(2000);
			SC.removeQR();
			Thread.sleep(2000);
			SC.excCommand("EVChargingQR/Stop");
			Thread.sleep(2000);
			SelectFromList=EV.GetEvDataFromSelectList();
			ApcoaListeners.logInfo("Plug Avalability       :"+SelectFromList);
			//System.out.println(SelectFromList);
			
			  
			int length= map.get(ParkingName[i]).length;
			for(int j=0;j<length;j++)
			{   String QRPath="EVChargingQR/"+AutomationConfiguration.Country+"/"+ParkingName[i]+"/"+map.get(ParkingName[i])[j];
			    Thread.sleep(7000);
				SC.removeQR();
				ApcoaListeners.logInfo("QR Folder path  :    "+QRPath);
				SC.excCommand(QRPath);
                EV.getbackparking(ParkingAreaCode[i]);
				List<Boolean> Res=EV.CheckEvChargingQrFlowAppCamera();
				System.out.println(Res.get(0)+":"+Res.get(1));
				ScanQrfromAppCamera.add(Res.get(0));
				boolean temp=Res.get(1);
				
				SA.assertEquals(temp,true);
				SA.assertAll();
			}
			
			
			
			ApcoaListeners.logInfo("EVCharging Flow through Native Camera");
			for(int j=0;j<length;j++)
			{
				 String QRPath="EVChargingQR/"+AutomationConfiguration.Country+"/"+ParkingName[i]+"/"+map.get(ParkingName[i])[j];
				    
					SC.removeQR();
					ApcoaListeners.logInfo("QR Folder path  :    "+QRPath);
					SC.excCommand(QRPath);
					Thread.sleep(5000);
					LR.launchQrReader();
					SC.removeQR();
					SC.excCommand("EVChargingQR/Stop");
					List<Boolean> Res=EV.CheckEvChargingQrFlowNativeCamera();
					ScanQrfromNativeCamera.add(Res.get(0));
					boolean temp=Res.get(1);
					LR.KillQrScanner();
					SA.assertEquals(temp,true);
					
					Thread.sleep(15000);
				    SA.assertAll();
					
			}
			
			System.out.println(SelectFromList);
			System.out.println(ScanQrfromAppCamera);
			System.out.println(ScanQrfromNativeCamera);
			
			
			
			
			
			
			
			
			
			
		}
		
	}
	
	
	
	
}
