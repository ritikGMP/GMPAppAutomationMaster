package Pages.Mobile;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AutomationConfiguration;
import Mobile.NewUserReg;
import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NewUserRegistration {
	
	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
     
	
	String country=AutomationConfiguration.Country;
	String tenant=AutomationConfiguration.Tenant;
	String vnum;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_get_started')]")
	private WebElement btnletGetStart;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_next')]")
	private WebElement btnNext;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btnNext')]")
	private WebElement btnNext2;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action')]")
	private WebElement btnAllow;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sign_up_with_email')]")
	private WebElement btnSignUp;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/etEmail')]")
	private WebElement btnEnterEmail;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/etFirstName')]")
	private WebElement btnEnterFirstName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/etLastName')]")
	private WebElement btnEnterLastName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btnNext')]")
	private WebElement btnNextafterEmail;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	private WebElement btnEnterPassword;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_terms_conditions')]")
	private WebElement btnCond1;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_privacy_policy')]")
	private WebElement btnCond2;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_vehicle_number')]")
	private WebElement btnEditVehcileNumber;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,'cardNumber')]")
	private WebElement btnAddCardNumber;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,'cardholderName')]")
	private WebElement btnAddCardName;     //cardholderName
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,'expiryDate')]")
	private WebElement btnAddExpiryDate;  //expiryDate
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,'cvv')]")
	private WebElement btnAddCVV;  //cvv
	
	@AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'primaryButton')]")
	private WebElement btnAddCard;//primaryButton
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement btnPaymentTitle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtPreferredPass')]")
	private WebElement emailconfirm;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_country_name')]")
	private WebElement passwordconfirm;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	private WebElement btnmenu;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_customer_id')]")
	private WebElement custId;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement btnskip;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement addvehcnfrm;
	
	@AndroidFindBy(xpath="(//android.widget.Button)[1]")
	private WebElement btnVisa;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payment_mode')]")
	private WebElement addCard;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[contains(@resource-id,'bt_payment_method_type')])[2]")
	private WebElement SelectCreditCard;
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	private WebElement EnterCardNumber;
	
	@AndroidFindBy(xpath="(//android.widget.EditText)[2]")
	private WebElement EnterExpiry;
	
	@AndroidFindBy(xpath="(//android.widget.EditText)[3]")
	private WebElement EnterCVV;
	
	@AndroidFindBy(xpath="//android.widget.Button")
	private WebElement ConfirmAddCard;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_mark_electric_vehicle')]")
	private WebElement ValidationForVehicle;
	
	@AndroidFindBy(xpath="(//android.widget.ImageView)[2]")
	private WebElement skipPayment;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	WebElement btnconfirmadd;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_vehicle_nav')]")
	WebElement btnNavVehicle;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/etReferral')]")
	WebElement EnterReferral;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_action')]")
	WebElement OkGotIT;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewUserRegistration(AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}
	
	public void FillDetails(String Referral) throws InterruptedException
	{ApcoaListeners.logInfo("Email Registration Started");
	   
	    try {
	    	System.out.println("inside");
	   
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnletGetStart,20);
    	btnletGetStart.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,18);
		btnNext.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAllow,8);
		btnAllow.click();
	    }
	    catch(Exception e) {}
	    
	    try {
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnSignUp,20);
		btnSignUp.click();
	    }
	    catch(Exception e) {}
		String emailid=EmailGenerator();
		ApcoaListeners.logInfo("Email id to be enter-->"+emailid);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEnterEmail,20);
		btnEnterEmail.sendKeys(emailid);
		
		Thread.sleep(4000);
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNextafterEmail,20);
		btnNextafterEmail.click();
		Thread.sleep(6000);
		SA.assertEquals(emailconfirm.getText(),"Your preferred password");
		ApcoaListeners.logInfo("Email entered Successful");
		ApcoaListeners.logInfo("Going to enter Password");
		ApcoaListeners.logInfo("Password to be enter-->"+"testing");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEnterPassword,20);
		btnEnterPassword.sendKeys("testing");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnCond1,20);
		btnCond1.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnCond2,20);
		btnCond2.click();
		Thread.sleep(4000);
		//CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,200);
		
		btnNext2.click();
		
		if (country.matches("(?i)Italy")||tenant.matches("(?i)Reef|BAC|OneParking|Legacy"))
		{  if(!Referral.equals("none"))
		{   CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,EnterReferral,10);
			EnterReferral.sendKeys(Referral);
			ApcoaListeners.logInfo("Referral code Entered   :"+Referral);
		}
			try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext2,5);
			btnNext2.click();
			
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OkGotIT,10);
			OkGotIT.click();
			Thread.sleep(4000);
		}
		catch(Exception e) {}
	}
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,passwordconfirm,10);
		SA.assertEquals(passwordconfirm.getText(),AutomationConfiguration.Country);
		 ApcoaListeners.logInfo("Email Registered Successfully");
		SA.assertAll();
	}
	
	public void AddVehicle() throws InterruptedException
	{   ApcoaListeners.logInfo("AddVehicle Started:");
	
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext2,20);
		btnNext2.click();
		vnum=VehicleNumberGenerator();
		NewUserReg.vnum=vnum;
		ApcoaListeners.logInfo("Going to add vehicle-->"+vnum);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEditVehcileNumber,20);
		btnEditVehcileNumber.sendKeys(vnum);
		Thread.sleep(2000);
		SA.assertEquals(btnNext2.isEnabled(), true);
		ApcoaListeners.logInfo("Vehicle Added successfully");
        SA.assertAll();
		btnNext2.click();
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnconfirmadd,5);
			btnconfirmadd.click();
		    }
		catch(Exception e)
		{}
	}
	
	
	
		public void VerifyRegistration(String vnum) throws InterruptedException
	    { 
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnmenu,30);
		btnmenu.click();
		Thread.sleep(5000);
		ApcoaListeners.logInfo("Checking CustomerID....."+custId.getText());
		SA.assertTrue(!(custId.getText().equals(null)));
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNavVehicle,15);
		btnNavVehicle.click();
		PageAddVehicle addvehicle = new PageAddVehicle(AutomationConfiguration.AppiumDriver);
	    String firstVehicle=addvehicle.getfirstvehiclelpr();
	    SA.assertEquals(firstVehicle,vnum);
	    ApcoaListeners.logInfo("Checked Vehicle Number....."+firstVehicle);
		SA.assertAll();
		}
	
	
     public void SkipPayment()
     {   ApcoaListeners.logInfo("Skiping the Add Payment");
     
    	 if (country.matches("(?i)Sweden")) {
    		
    		 skipPayment.click();
    		 CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver, btnskip,20);
    		 btnskip.click();
    	}
    	 else {
    		 btnskip.click();
    	 }
    		 
     }
		
	
	
	
	 public void AddCard() throws InterruptedException
	{   ApcoaListeners.logInfo("Going to Payment_Card");
	   
    	if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa"))
    	{  if(!(AutomationConfiguration.Country.equalsIgnoreCase("Sweden")||AutomationConfiguration.Country.equalsIgnoreCase("Denmark")))
	   {Thread.sleep(4000);
	    btnVisa.click();
	    Thread.sleep(3000);
	    ApcoaListeners.logInfo("Payment_Card---->"+"4525425464350336");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddCardNumber,20);
		btnAddCardNumber.sendKeys("4525425464350336");
		 Thread.sleep(3000);
		ApcoaListeners.logInfo("Username---->"+"Automation Test");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddCardName,20);
		btnAddCardName.sendKeys("Automation Test");
		Thread.sleep(3000);
		ApcoaListeners.logInfo("ExpiryDate---->"+"11/27");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddExpiryDate,20);
		btnAddExpiryDate.sendKeys("1127");
		Thread.sleep(3000);
		ApcoaListeners.logInfo("CVV---->"+"123");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddCVV,20);
		btnAddCVV.sendKeys("123");
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAddCard,20);
		btnAddCard.click();
		Thread.sleep(20000);
		//System.out.println(btnPaymentTitle.getText());
		
	//	SA.assertNotEquals(btnPaymentTitle.getText(),"Payment failed");
		
		btnmenu.click();
		Thread.sleep(5000);
		System.out.println(custId.getText());
		SA.assertTrue(!(custId.getText().equals(null)));
	    
     	SA.assertAll();
	   }
    	
	   }else 
	   {   
		   CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,addCard,20);
		   addCard.click();
		   CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SelectCreditCard,20);
		   SelectCreditCard.click();
		   Thread.sleep(5000);
		   EnterCardNumber.sendKeys("4111111111111111");
		   Thread.sleep(4000);
		   EnterExpiry.sendKeys("1127");
		   Thread.sleep(2000);
		   if(AutomationConfiguration.Tenant.equals("Reef"))
		   EnterCVV.sendKeys("123");
		   Thread.sleep(2000);
		   ConfirmAddCard.click();
		   CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnmenu,20);
		   btnmenu.click();
			Thread.sleep(5000);
			System.out.println(custId.getText());
			SA.assertTrue(!(custId.getText().equals(null)));
		    
	     	SA.assertAll();
		  
	   }
    	
    	
	}
	
	public String EmailGenerator()
	{
		String email;
		Random rd = new Random();
		
		String s=String.valueOf(rd.nextInt(100000));     //creating random number string
		email="Automation_NewUser_"+s+"@yopmail.com";
		return email;
		
	}
	
	public String VehicleNumberGenerator()
	{
		String vehnum;
		Random rd= new Random();
		String s=String.valueOf(rd.nextInt(100000));
		if(AutomationConfiguration.Country.equalsIgnoreCase("Austria"))
		{
			vehnum="W A "+s;
		}
		else if(AutomationConfiguration.Country.equalsIgnoreCase("Sweden")||AutomationConfiguration.Country.equalsIgnoreCase("Denmark"))
		{
			vehnum="WA"+s;
		}
		else
		{
			vehnum="W A"+s;
		}
			
		
		return vehnum;
		
	}
	
	
	public void checkCharaterInTheName() throws InterruptedException
	{    
		String temp="12233";
		ApcoaListeners.logInfo("Email Registration Started");
	     System.out.println(AutomationConfiguration.Country);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnletGetStart,15);
		btnletGetStart.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnNext,20);
		btnNext.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnAllow,20);
		btnAllow.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnSignUp,20);
		btnSignUp.click();
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEnterFirstName,20);
		ApcoaListeners.logInfo("Try to Enter the Non-Alphabetic Value  ----->12@*%");
		btnEnterFirstName.sendKeys(temp);
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Text in the First_Name_Field"+btnEnterFirstName.getText());
		
		SA.assertNotEquals(btnEnterFirstName.getText(),"First Name");
		
		btnEnterLastName.sendKeys("12@*%");
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Text in the Last_Name_Field"+btnEnterLastName.getText());
		SA.assertNotEquals(btnEnterLastName.getText(),"Last Name");
		
		SA.assertAll();
		
		
	}
	
}
