package Pages.Mobile;
import Mobile.TestLogin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import CommonUtility.AppiumGenericMethods;
import CommonUtility.AutomationConfiguration;
import MobileObjectMapper.FreeParkingMapper;
import MobileObjectMapper.ParkingMapper;

import TestNGListeners.ApcoaListeners;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class SessionCreationPage {

	AppiumDriver<WebElement> driver;
	SoftAssert SA=new SoftAssert();
	WebDriverWait wait;
	@SuppressWarnings("rawtypes")
	TouchAction action;
	public String Parkingprice;
	public String ActualInitialParkingPrice;
	public String ActualParkingHour;
	public String ActualParkingMin;
	public String ActualPaymentConfirmMsg;
	public String ActualPaymentSuccess;
	public String ActualSessionEndMsg;
	public String ActualSessionEndSuccessmsg;
	public String ActualExtendedParkingPrice;
	public String ActualParkingName;
	public String ExpectedPaymentConfirmMsg="You will be charged " +Parkingprice+ " before the session starts";
	public String ActiveSessionID;
	public String ActiveSessionCost;
	public String ExpiredSessionID;
	public String ExpiredSessionCost;
	public String AppliedPromoByQR;
	public String AppliedPromoByQRInExpire;

	By ProfileList = By.className("android.widget.TextView");
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.ImageView[1]")
	private WebElement btnMenu;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement MenuMySessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_session_id')]")
	private WebElement MySessionsSessionID;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_cost')]")
	private WebElement MySessionsTotalCost;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Past\"]/android.widget.TextView")
	private WebElement MySessionsGotoExpiredSessions;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc='Expired']")
	private WebElement MySessionsGotoExpiredSessionsApcoa;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Past\"]/android.widget.TextView")//here
	private WebElement GMPMySessionsGotoExpiredSessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbartv;

	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[7]")
	private WebElement ParkingSearchbar;  

	@AndroidFindBy(xpath=".//android.widget.EditText[contains(@resource-id,':id/edt_Search')]")
	private WebElement searchParking;

	@AndroidFindBy(xpath="(//android.widget.LinearLayout)[4]")
	private WebElement selectParking;

	@AndroidFindBy(xpath="(//android.view.ViewGroup)[2]")
	private WebElement selectParking2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_setting_parent')]")
	public WebElement scrollStartPointInMap;

	@AndroidFindBy(xpath="(//android.view.View)[5]") 
	public WebElement clickOnParking;

	@AndroidFindBy(xpath="(//android.view.View)[10]")
	public WebElement clickOnParkingGermany;

	@AndroidFindBy(xpath="(//android.view.View)[4]") 
	public WebElement clickOnParkingAustria_Poland;    //zamek ujazdowski

	@AndroidFindBy(xpath="(//android.view.View)[18]")
	public WebElement clickOnParkingItaly; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_display_name')]")
	private WebElement parkingDetailScreen;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_flow_benefits_header')]")
	private WebElement scrollStartPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/vehicle_type_header')]")
	private WebElement scrollEndPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_favourite')]")
	private WebElement SetfavParking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session_with_buy_pass')]")
	private WebElement startSessionwithpass;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_price_with_units')]")
	private WebElement ParkingPrice;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_hours')]")
	private WebElement ParkingHour;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_parking_minutes')]")
	private WebElement ParkingMinute;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/csb_time_dialer')]")
	private WebElement clickOnDialer;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	private WebElement paymentConfirmMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm_pay')]")
	private WebElement payAmount; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_heading')]")
	private WebElement paySuccessMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close')]")
	private WebElement closePayment;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_close') or contains(@resource-id,':id/tv_negative_action_button') or contains(@resource-id,':id/iv_back')]")
	private WebElement close;
	
	

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_login')]")
	private WebElement Login;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	private WebElement email;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_password')]")
	private WebElement password;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_continue')]")
	private WebElement continuelogin;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_stop_parking_session')]")
	private WebElement stopSession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_desc')]")
	private WebElement SessionStopMsg;  

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_confirm')]")
	private WebElement Confirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_success_sub_heading')]")
	private WebElement SessionEndSuccessMsg; 

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_extend_parking_session')]")
	private WebElement ExtendSession;

	@AndroidFindBy(xpath=".//android.widget.TextView[contains(@resource-id,':id/tv_garage_access_door')]")
	private WebElement opengaragedoor;

	@AndroidFindBy(xpath="(.//android.widget.RelativeLayout[contains(@resource-id,':id/rl_garage_door_item')])[1]")
	private WebElement selectFirstdoor;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_action_button')]")
	private WebElement openaccessdoor;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_action_button')]")
	private WebElement confirmpaymentforextendsession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_negative_action_button')]")
	private WebElement goback;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_start_session')]")
	private WebElement StartSession;

	@AndroidFindBy(xpath="(.//android.widget.ImageView[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement SelectSecondTariffMode;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ub_page_button_cancel')]")
	private WebElement feedbackPopUp;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_area_code_search')]")
	private WebElement SearchAreaCode;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_search')]")
	private WebElement AreaCodeSearchBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cl_parent')]")
	private WebElement Selectparking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_select_parking_lot')]")
	private WebElement SelectparkingLot;

	@AndroidFindBy(xpath="(//android.widget.ImageView[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement SelectSecondLot;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement StartSessionafterSelectinglot;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_header')]")
	public WebElement DetailScreenscrollStartPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/service_header')]")
	public WebElement DetailScreenscrollEndPoint;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_menu')]")
	private WebElement ClickOnsideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_sessions')]")
	private WebElement My_Sessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement ActiveParking;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement GoBacktoSideBar;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_view_parking_session')]")
	private WebElement emptyActiveSession;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_extend_session_option')]")
	private WebElement btnselectfirsttariff;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_start_session')]")
	private WebElement gmpstartsession;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement tarrifSelection;
	//	(//*[contains(@resource-id,':id/cl_nearby_parking')]//android.widget.LinearLayout)[3]

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/cl_nearby_parking')]//android.widget.LinearLayout)[3]")
	private WebElement openstartsession;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[2]")
	private WebElement bactarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[4]")
	private WebElement oneParkingtarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[1]")
	private WebElement gmptarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[1]")
	private WebElement gmpAustriatarrifSelection;

	@AndroidFindBy(xpath="(//*[contains(@resource-id,':id/iv_selected_mode')])[3]")
	private WebElement elitetarrifSelection;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_park_now')]")
	private WebElement btnParkNow;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Past\"]/android.widget.TextView")//here
	private WebElement OneParkingMySessionsGotoExpiredSessions;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_discount')]")
	private WebElement btnAddPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	private WebElement btnclikAddPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/textinput_placeholder')]")
	private WebElement btnPutCode;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_apply_promo_code')]")
	private WebElement btnApplyPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_back')]")
	private WebElement btnBack;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirmPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_discount')]")
	private WebElement btnEditPromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_remove_promotion')]")
	private WebElement btnRemovePromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnProceedRemovePromo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement btnConfirm2Promo;

	@AndroidFindBy(id="com.apcoaflow.consumer:id/tv_positive_action_button]")
	private WebElement btnConfirm3Promo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/half_interstitial_button1')]")
	private WebElement btnCancelNotification;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cover_button2')]")
	private WebElement btnCancelNotification2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_start_session_with_buy_pass')]")
	private WebElement btnstartsession2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_search')]")
	private WebElement ParkingSearchbar2;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
	private WebElement TarrifText1;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView")
	private WebElement btnTarrif2;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ivTariffInfo')]")
	private WebElement tariffInfo;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtTariffSelection')]")
	private WebElement btnTariffSelection;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/txtTotalCost')]")
	private WebElement UpdatedTariff;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tvBottomPriceBreakdownLabel')]")
	private WebElement btnOkGotIt;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_tariff_type')]")
	private WebElement tariffText1;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_name')]")
	private WebElement isDiscountApplied;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payment_heading')]")
	private WebElement PaymentProfile;


	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_card')]")
	private WebElement editCard;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[1]")
	private WebElement Profile1;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.TextView[1]")
	private WebElement Profile2;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup/android.widget.TextView[1]")
	private WebElement Profile3;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_passes')]")
	private WebElement PassMenu;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Expired\"]/android.widget.TextView")
	private WebElement ExpiredSession;

	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Image\"])[1]")
	private WebElement InvoiceMenu;

	@AndroidFindBy(xpath="/hierarchy/android.widget.Toast")
	private WebElement ToastMessage;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
	private WebElement btnDownloadInvoice;

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[1]")
	private WebElement TextConvenience;


	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[2]")
	private WebElement ConvenienceFee;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/actv_positive_button')]")
	private WebElement confirm;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_error_desc')]")
	private WebElement errorMsg;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payments_nav')]")
	private WebElement paymentNav;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Personal Profile')]")
	private WebElement SelectPersonalProfile;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Corporate Profile')]")
	private WebElement SelectCorporateProfile;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Business Profile')]")
	private WebElement SelectBusinessProfile;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Add a parking profile')]")
	private WebElement SelectAddProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_delete')]")
	private WebElement deleteProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement ProceedBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Add a parking profile') or contains(@text,'Add a Parking Profile')]")
	private WebElement AddBuisnessProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_email')]")
	private WebElement enterEmail;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_action')]")
	private WebElement confirmProfile;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@resource-id,'id/tv_parking_type')]")
	private WebElement SelectBuisnessProfile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/sc_make_default')]")
	private WebElement makingDefault;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_tap_to_access')]")
	private WebElement TaptoExit;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_title')]")
	private WebElement MySessionTitile;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/clQrScan')]")
	private WebElement ScanQRButton;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_message')]")
	private WebElement PromoName;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_discount_title')]")
	private WebElement AppliedPromoInExpire;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_validation_title')]")
	private WebElement PromoCheck;

	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_add_discount') or contains(@resource-id,':id/tv_discount_name')]")
	private WebElement addPromoDiscount;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/sp_subcorporates')]")
	private WebElement SelectSubCorporate;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_corporate_id')]")
	private WebElement CorporateId;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/cb_terms')]")
	private WebElement AcceptTerms;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_positive_action_button')]")
	private WebElement Proceed;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement ProfileInfo;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/iv_edit_info')]")
	private WebElement editProfileInfo;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_first_name')]")
	private WebElement FirstName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/edt_last_name')]")
	private WebElement LastName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/email')]")
	private WebElement Email;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_name')]")
	private WebElement UserName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/btn_save')]")
	private WebElement ConfirmChange;
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=\"Image\"])[1]")
	private WebElement DownloadPDF;
	
	@AndroidFindBy(xpath="(//android.widget.TextView)[5]")
	private WebElement PDFName;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/rl_personal_details')]")
	private WebElement Profile;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/dismiss_text')]")
	private WebElement closeallnotification;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_access_identifier')]")
	private WebElement VehicleNumber;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/tv_payment_card_number')]")
	private WebElement PaymentCard;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/ivNotification')]")
	private WebElement BtnNotification;
	

	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'DOWNLOAD INVOICE')]")
	private WebElement DownloadInvoice;
	

	By selectTariff = By.className("android.widget.TextView");
	
	By ProfileName = By.xpath("//android.widget.TextView[contains(@text,'Profile')]");
	By SubCorporate = By.className("android.widget.TextView");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SessionCreationPage(AppiumDriver driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		wait=new WebDriverWait(driver, 100);
		action=new TouchAction((PerformsTouchActions) driver);
	}


	/*
	 * 
	 * Implementation:- Going to My Active Session to check Active Session Id and Total tarrif
	 */
	public void GotoMyActiveSessions() {
		try{
			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,100);
			btnMenu.click();

			Thread.sleep(2000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,100);
			MenuMySessions.click();
			Thread.sleep(2000);

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,100);
			ActiveSessionID = MySessionsSessionID.getText();
			System.out.println("Active Session ID: "+ ActiveSessionID);

			if(!(AutomationConfiguration.Country.equals("Denmark")||AutomationConfiguration.Country.equals("Sweden")||AutomationConfiguration.Country.equals("Testing"))) {
				action.press(PointOption.point(505, 1870)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(528, 784)).release().perform();
				//	Thread.sleep(3000);
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsTotalCost,100);
				ActiveSessionCost = MySessionsTotalCost.getText();
				System.out.println("Active Session cost : "+ ActiveSessionCost);
			}
			else
			{
				Thread.sleep(3000);
			}
		}catch(Exception e){
			System.out.println("PaymentConfirmation end error: "+e.toString());

		}
	}

	/*
	 *Implementation:-Applying PromoCode in the Start Session.
	 */
	public void addAnotherPromoCode(String code) throws InterruptedException{
		
        try {
		if(isDiscountApplied.getText().equals(code))
		{   ApcoaListeners.logInfo("Same Promo already Applied");
		   return;
		}
        }catch(Exception e) {}
        {
			Thread.sleep(3000);
			try {
				btnEditPromo.click();
			}
			catch(Exception e)
			{
				addPromoDiscount.click();
			}


			Thread.sleep(2000);

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnclikAddPromo,200);

			btnclikAddPromo.sendKeys(code);
			Thread.sleep(2000);
			btnApplyPromo.click();
			 Thread.sleep(3000);
		    try {
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnConfirmPromo,200);	
			btnConfirmPromo.click();

			try {
				btnConfirmPromo.click();
			}
			catch(Exception e) {}
			
		    }
		    catch(Exception e) {}
		}

	}


	/*
	 * Implementation:-Used to Remove the applied Promo Code.
	 */
	public void removeDiscount() throws InterruptedException
	{
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnEditPromo,200);
		btnEditPromo.click();
		Thread.sleep(2000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnRemovePromo,200);
		btnRemovePromo.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnProceedRemovePromo,200);
		btnProceedRemovePromo.click();
		Thread.sleep(2000);
		GoBacktoSideBar.click();

	}


	/*
	 * Implementation:-Go to expired session --->check previous session id & total tarrif
	 */
	public void GotoMyExpiredSessions() {
		try{
			Thread.sleep(2000);//5000
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,100);
			btnMenu.click();
			Thread.sleep(3000);

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,100);
			MenuMySessions.click();
			Thread.sleep(3000);//8000
			if(AutomationConfiguration.Tenant.equalsIgnoreCase("GMP")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GMPMySessionsGotoExpiredSessions,30);
				GMPMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				if(AutomationConfiguration.Country.equals("Austria"))
				{
					CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsGotoExpiredSessionsApcoa,30);
					MySessionsGotoExpiredSessionsApcoa.click();
				}
				else {
					CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsGotoExpiredSessions,30);
					MySessionsGotoExpiredSessions.click();}

				Thread.sleep(3000);
			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("OneParking")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("BAC")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GarageInn")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);
			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Reef")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);

			}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GreenParking")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);}

			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);}
			else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Legacy")) {
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,OneParkingMySessionsGotoExpiredSessions,30);
				OneParkingMySessionsGotoExpiredSessions.click();
				Thread.sleep(3000);}
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,100);
			ExpiredSessionID = MySessionsSessionID.getText();
			System.out.println("Expired Session ID: "+ ExpiredSessionID);

			Thread.sleep(3000);
			ExpiredSessionCost = MySessionsTotalCost.getText();
			System.out.println("Expired Session Cost: "+ ExpiredSessionCost);
		}catch(Exception e){
			System.out.println("PaymentConfirmation end error: "+e.toString());
		}
	}

	/*
	 * Implementation:-Used to search the parking.
	 */
	public void GettheParking(String ParkingName){
		try{
			ApcoaListeners.logInfo("GettheParking start");

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar2,30);
			ParkingSearchbar2.click();

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,searchParking,30);
			searchParking.sendKeys(ParkingName);

			Thread.sleep(8000);
			boolean temp = selectParking2.isDisplayed();
			if(temp){
				selectParking2.click();
			}else{
				selectParking.click(); 
			}

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingSearchbar,30);
			ApcoaListeners.logInfo("parking name: "+ParkingSearchbartv.getText());
			ActualParkingName = ParkingSearchbartv.getText();
			ApcoaListeners.logInfo("GettheParking  end");
		}catch(Exception e){
			ApcoaListeners.logInfo("GettheParking start: error:"+ e.toString());
		}
	}


	/*
	 * Implementation:-Check the ConvenienceFee in price breakdown in start session.
	 */
	public void ConvenienceFee() throws InterruptedException
	{
		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		Thread.sleep(5000);
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
		Thread.sleep(4000);
		tariffInfo.click();
		ApcoaListeners.logInfo("Checking Tarrif Breakdown");
		Thread.sleep(5000);

		SA.assertEquals(TextConvenience.getText(),"Convenience Fee");
		ApcoaListeners.logInfo("Convenience Fee present------->" +ConvenienceFee.getText());
		SA.assertAll();

	}


	/*
	 * Implementation:-Check the start of sesion without payment methods
	 */
	public void SessionCheck() throws InterruptedException
	{ 
		Thread.sleep(3000);

		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		Thread.sleep(5000);
		confirm.click();
		Thread.sleep(2000);
		ApcoaListeners.logInfo("Rotating the dailer");
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
		Thread.sleep(3000);

		ApcoaListeners.logInfo("error message----->"+errorMsg.getText());
		SA.assertEquals(errorMsg.getText(),"Please enter payment details");

		Thread.sleep(2000);
		clickOnDialer.click();
		try
		{   ApcoaListeners.logInfo("Checking the confirm pay button");
		SA.assertFalse(payAmount.isDisplayed());

		}
		catch(Exception e)
		{

		}
		SA.assertAll();
		ApcoaListeners.logInfo("Unable to start the session without Payment Method");



	}
	
	
	public String checkthesessionTarrif() throws InterruptedException
	{
		String Promo="REGTESTDK";

		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		Thread.sleep(5000);

		ApcoaListeners.logInfo("Applying the Free Parking Promo Code:   "+Promo);
		addAnotherPromoCode(Promo);
		
		
		if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
			action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
		}
		else {
			dailerRotationControl(25);
		}


		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
		Parkingprice= ParkingPrice.getText();

		String currency="DKK";
		int len=currency.length();
		ActualInitialParkingPrice = Parkingprice.substring(len);
		ApcoaListeners.logInfo("Free Parking Price     "+ActualInitialParkingPrice);
		//SA.assertEquals(ActualInitialParkingPrice,"0.00");
		//SA.assertAll();
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
		PaymentConfirmation();
		
		return ActualInitialParkingPrice;
		

	}

	/*
	 * Implementation:-Scrolling down the page
	 */
	public void scrolldown(WebElement pageStart, WebElement PageEnd){
		try{
			pageStart.isDisplayed();

			Thread.sleep(3);
			Dimension dim= driver.manage().window().getSize();
			int x=dim.getWidth()/2;
			int startY=(int) (dim.getHeight()*0.2);
			int endY=(int) (dim.getHeight()*0.8);
			action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			boolean scrolled=PageEnd.isDisplayed();
			Thread.sleep(4000);
			System.out.println(scrolled);
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}
	/*
	 * Implementation:-Scrolling up the page
	 */
	public void scrollUp(WebElement pageStart, WebElement PageEnd){
		try{
			pageStart.isDisplayed();
			Thread.sleep(3);
			Dimension dim= driver.manage().window().getSize();
			int x=dim.getWidth()/2;
			int startY=(int) (dim.getHeight()*0.8);
			int endY=(int) (dim.getHeight()*0.2);
			action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endY)).release().perform();
			boolean scrolled=PageEnd.isDisplayed();
			Thread.sleep(4000);
			System.out.println(scrolled);
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	public void favPark(){
		try{
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParkingGermany,100);	
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,parkingDetailScreen,100);	  
			SetfavParking.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}

	}

	/* 
	 * Implementation:- Used for Rotating the Daier in start session.
	 */
	public void dialerMovement(String Country,ParkingMapper parkingMapper) {
		try{
			ApcoaListeners.logInfo("Dialer Movement for start Session: start");
			ApcoaListeners.logInfo("Country: "+Country);
			Thread.sleep(5000);//15000

			/*	if(AutomationConfiguration.Environment.equalsIgnoreCase("Staging")&&AutomationConfiguration.Country.equalsIgnoreCase("Poland"))
			{PageBuyPass pbp = new PageBuyPass(driver);
			pbp.applyPromoCode("PLOFF30");
			Thread.sleep(5000);
			}*/

			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				PageBuyPass pbp = new PageBuyPass(driver);
				if(AutomationConfiguration.Country.equalsIgnoreCase("Austria"))
					addAnotherPromoCode("REGTESTAT");
				else if(AutomationConfiguration.Country.equalsIgnoreCase("Italy"))
				{ btnCancelNotification.click();
				addAnotherPromoCode("REGTESTIT");}
				else if(AutomationConfiguration.Country.equalsIgnoreCase("Denmark"))
				{//pbp.applyPromoCode("REGTESTDK");
					addAnotherPromoCode("REGTESTDK");
				}
				else if(AutomationConfiguration.Country.equalsIgnoreCase("Sweden"))
				{//pbp.applyPromoCode("REGTESTSE");
					addAnotherPromoCode("REGTESTSE");
				}
				else if(AutomationConfiguration.Country.equalsIgnoreCase("Poland"))
				{ 
					addAnotherPromoCode("REGTESTPL");	

				}
				
			}
			

			//	Thread.sleep(5000);
			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
			}
			else {
				dailerRotationControl(25);
			}

			ApcoaListeners.logInfo("Dialer Movement for start Session: ends");

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
			Parkingprice= ParkingPrice.getText();
			String currency=parkingMapper.getcurrency();
			int len=currency.length();
			ActualInitialParkingPrice = Parkingprice.substring(len);

			Thread.sleep(3000);
			ActualParkingHour=ParkingHour.getText();
			ActualParkingMin=ParkingMinute.getText();

			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,20);
			ApcoaListeners.logInfo("dialerMovement end");
		}catch(Exception e){
			ApcoaListeners.logInfo("dialerMovement end error: "+e.toString());
		}
	}


	/*
	 * Implementation:- Confirm the Payment Suceessful after the extend session.
	 */
	public void ExtendPaymentConfirmation(){
		try{
			ApcoaListeners.logInfo("ExtendPaymentConfirmation start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,20);//100
			closePayment.click();
			ApcoaListeners.logInfo("ExtendPaymentConfirmation end: ");
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendPaymentConfirmation end error: "+e.toString());
		}
	}


	public void PaymentConfirmation() {
		try{
			ApcoaListeners.logInfo("PaymentConfirmation start");

			//Thread.sleep(5000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,payAmount,30);
			ApcoaListeners.logInfo("payAmount.getText(): "+payAmount.getText());

			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,payAmount,15);
			if(AutomationConfiguration.Country.equalsIgnoreCase("Italy")) 
			{   try {
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,btnCancelNotification2,15);
			        }
			catch(Exception e) {}
			//btnCancelNotification2.click();
			}
			if(!(AutomationConfiguration.Country.equals("Denmark")||AutomationConfiguration.Country.equals("Sweden")||AutomationConfiguration.Country.equals("Testing"))) {
				Thread.sleep(5000);
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paySuccessMsg,30);
				ActualPaymentSuccess=paySuccessMsg.getText();
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,10);
				closePayment.click();
				ApcoaListeners.logInfo("PaymentConfirmation end: ActualPaymentSuccess " + ActualPaymentSuccess);
			}

		}catch(Exception e) {
			ApcoaListeners.logInfo("PaymentConfirmation end error: "+e.toString());
		}
	}


	/*
	 * Implementation:- Stopping the session in the MyActive Session menu
	 */
	public void StopSession() {
		try{
			ApcoaListeners.logInfo("StopSession start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,stopSession,30);
			stopSession.click();
			try {
				if(!(AutomationConfiguration.Country.equals("Sweden")||AutomationConfiguration.Country.equals("Italy")))
					ActualSessionEndMsg=SessionStopMsg.getText();
			}
			catch(Exception e)
			{

			}

			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Confirm,30);
			Confirm.click();
			Thread.sleep(10000);
			((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			try {
				ActualSessionEndSuccessmsg=SessionEndSuccessMsg.getText();
				closePayment.click();}
			catch(Exception e)
			{

			}

			ApcoaListeners.logInfo("StopSession end");
		}catch(Exception e){
			ApcoaListeners.logInfo("StopSession end error: "+e.toString());
		}
	}

	/*
	 * Implementing the Check of Intial price in extend session after starting the session in free parking in the poland.
	 */
	public void PolandTarrif(String Country,ParkingMapper parkingMapper) throws InterruptedException
	{ 
		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		try {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpAustriatarrifSelection,5);
			
		}
		catch(Exception e)
		{}
		Thread.sleep(5000);


		try{

			ApcoaListeners.logInfo("Country: "+Country);
			Thread.sleep(10000);//15000
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
			Parkingprice= ParkingPrice.getText();
			String currency=parkingMapper.getcurrency();
			int len=currency.length();
			ActualInitialParkingPrice = Parkingprice.substring(len);

			Thread.sleep(3000);
			ActualParkingHour=ParkingHour.getText();
			ActualParkingMin=ParkingMinute.getText();

			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
			ApcoaListeners.logInfo("dialerMovement end");
		}catch(Exception e){
			ApcoaListeners.logInfo("dialerMovement end error: "+e.toString());
		}

		PaymentConfirmation();

		ApcoaListeners.logInfo("Extend Session start");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ExtendSession,100);
		ExtendSession.click();
		Thread.sleep(4000);//8000
		ApcoaListeners.logInfo("Extend Session Dialer Movement start:");
		ApcoaListeners.logInfo("Country: " +Country);
		Thread.sleep(4000);

		String TarrifName=isDiscountApplied.getText();
		ApcoaListeners.logInfo("Tarrif Name in Extend Session  --------->"+TarrifName);
		String ParkingHour1=ParkingHour.getText();
		ApcoaListeners.logInfo("Intial ParkingHour in Extend Session  --------->"+ParkingHour1);
		String ParkingMin=ParkingMinute.getText();
		ApcoaListeners.logInfo("Intial ParkingMin in Extend Session  --------->"+ParkingMin);
		String ParkingPrice1=ParkingPrice.getText();
		ApcoaListeners.logInfo("Intial Parking price in Extend Session  --------->"+ParkingPrice1);
		if(AutomationConfiguration.Environment.equals("Production"))
			SA.assertEquals(TarrifName,"QAPOLANDFREETIME");
		else
			SA.assertEquals(TarrifName,"FREE2HRS");
		
		SA.assertEquals(ParkingHour1,"00");
		SA.assertEquals(ParkingMin,"00");
		SA.assertEquals(ParkingPrice1,"PLN0.00");
		SA.assertAll();

		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

	}

	public void StoptheSession() throws InterruptedException
	{   
		ApcoaListeners.logInfo("Stopping the session");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,stopSession,100);
		stopSession.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Confirm,100);
		Confirm.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closePayment,100);
		closePayment.click();
		ApcoaListeners.logInfo("Session Stopped");

	}


	/*
	 * Implementation:-Rotating the Dailer in the Extend Session.
	 */
	public void ExtendSession(String Country,ParkingMapper parkingMapper){
		try{
			ApcoaListeners.logInfo("Extend Session start");
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ExtendSession,30);
			ExtendSession.click();
			Thread.sleep(4000);//8000
			ApcoaListeners.logInfo("Extend Session Dialer Movement start:");
			ApcoaListeners.logInfo("Country: " +Country);
			Thread.sleep(4000);
			if(AutomationConfiguration.Environment.equalsIgnoreCase("Production") && AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {
				action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
			}else {
				if(Country.equalsIgnoreCase("Austria")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Denmark")){
					dailerRotationControl(25);
				}
				else if(Country.equalsIgnoreCase("Sweden")){
					dailerRotationControl(25);
				}
				else if(Country.equalsIgnoreCase("Poland")){
					Thread.sleep(5000);
					action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(860, 1416)).release().perform();

					Thread.sleep(5000);
				}
				else if(Country.equalsIgnoreCase("India")){
					dailerRotationControl(25);
				}
				else if(Country.equalsIgnoreCase("New India")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Italy")){
					dailerRotationControl(25);
				}else if(Country.equalsIgnoreCase("Brazil")){
					dailerRotationControl(100);
				}else if(Country.equalsIgnoreCase("Australia")){
					action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(847, 1640)).release().perform();

				}else if(Country.equalsIgnoreCase("United States")){
					dailerRotationControl(100);		
				}else if(Country.equalsIgnoreCase("United States Elite")){
					dailerRotationControl(50);		
				}else if(Country.equalsIgnoreCase("Dubai")){
					dailerRotationControl(100);
				}else {
					dailerRotationControl(25);
				}
			}
			//	Thread.sleep(8000);
			ApcoaListeners.logInfo("Extend Session Dialer Movement ends:");
			Thread.sleep(4000);
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,20);
			Parkingprice= ParkingPrice.getText();
			String currency=parkingMapper.getcurrency();
			int len=currency.length();
			//	System.out.println(len);
			ActualExtendedParkingPrice=Parkingprice.substring(len);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,20);
			ApcoaListeners.logInfo("ExtendSession end:Parkingprice " + Parkingprice );

			ApcoaListeners.logInfo("ExtendSession end: ActualExtendedParkingPrice" + ActualExtendedParkingPrice );
			if(!(AutomationConfiguration.Country.equals("Denmark")||AutomationConfiguration.Country.equals("Sweden")))
			{
				CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,confirmpaymentforextendsession,30);
				confirmpaymentforextendsession.click();
			}
		}catch(Exception e){
			ApcoaListeners.logInfo("ExtendSession end error: "+e.toString());
		}
	}

	public void OpenGarageDoor() {
		try{
			opengaragedoor.click();
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,selectFirstdoor,100);
			openaccessdoor.click();
			goback.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public void Startsession(String Country){
		try{
			ApcoaListeners.logInfo("Startsession start");
			if(Country.equalsIgnoreCase("Italy")){
				Thread.sleep(4000);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParkingItaly,100);	
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}else if(Country.equalsIgnoreCase("Austria")) {
				ApcoaListeners.logInfo("Startsession start Austria");
				Thread.sleep(8000);
				//CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				// GenericMethods.explicitWait1(startSessionwithpass);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}else if(Country.equalsIgnoreCase("Poland")){
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver, StartSession,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,SelectSecondTariffMode,100);
			}else{
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnParking,100);
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,StartSession,100);
			}
			ApcoaListeners.logInfo("Startsession end");
		}catch(Exception e){
			ApcoaListeners.logInfo("Startsession end error: "+e.toString());
		}
	}


	/*
	 * Implementation:-Clicking the startsession button and selecting the tarrif
	 */
	public void StartsessionforParkingwithPass() throws InterruptedException{
		ApcoaListeners.logInfo("StartsessionforParkingwithPass start");
		if(AutomationConfiguration.Environment.equalsIgnoreCase("Production")) {
			ApcoaListeners.logInfo("Scroll page starts");
			Thread.sleep(5000);	
			PageBuyPass.scrollPage();
			ApcoaListeners.logInfo("Scroll page ends");
			Thread.sleep(2000);
		}
		if(AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {		
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,20);
		}

		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GMP")) {	
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			try {
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmptarrifSelection,10);
			}
			catch(Exception e) {}
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Apcoa")) {	
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);

			try {
				CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpAustriatarrifSelection,7);
			}
			catch(Exception e) {}


		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("OneParking")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,oneParkingtarrifSelection,30);
		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("BAC")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,bactarrifSelection,30);

		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GarageInn")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			//	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,100);

		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Reef")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			//	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,tarrifSelection,100);

		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("GreenParking")) {

			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,btnParkNow,30);

		}
		else if(AutomationConfiguration.Tenant.equalsIgnoreCase("Elite")) {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,30);
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,elitetarrifSelection,30);

		}
		ApcoaListeners.logInfo("StartsessionforParkingwithPass end");
	}

	public void StartSessionforParkingwithTariff(){
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,SelectSecondTariffMode,100);
	}

	public void SelectParkinglot_startSession() {
		try{
			SelectparkingLot.click();
			SelectSecondLot.click();
			int attempts = 0;
			while(attempts < 2) {
				try {
					SelectSecondLot.click();
					break;
				} 
				catch(Exception e) 
				{}
				attempts++;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}

	public void SearchParkingUsingAreaCode(String areacode) {
		try{
			SearchAreaCode.click();
			AreaCodeSearchBar.sendKeys(areacode);
			Selectparking.click();

		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}


	public void DailerMovement_AutoCheckout(int x,int y,int a,int b) {
		try {
			Thread.sleep(10000);
			action.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(a, b)).release().perform();
			//action.press(PointOption.point(538, 1169)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(612, 1181)).release().perform();
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
			payAmount.click();
			ActualPaymentSuccess=paySuccessMsg.getText();
			closePayment.click();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}

	public void ActiveSessions_List() {
		try {
			ClickOnsideBar.click();
			My_Sessions.click();
			Thread.sleep(7000);
			boolean SessionGoingOn=ActiveParking.isDisplayed();
			while(SessionGoingOn) {
				GoBacktoSideBar.click();
				ClickOnsideBar.click();
				My_Sessions.click();
				Thread.sleep(5000);
				SessionGoingOn=ActiveParking.isDisplayed();
				//    	if(ActiveParking.isDisplayed()){
				//    	
				//    	System.out.println(SessionGoingOn);
				//    }
			}
		}catch(Exception e){
			e.printStackTrace(); 
			if(emptyActiveSession.isDisplayed()) {
				System.out.println("Auto checkout is successfull");
			}
		}
	}

	public void dailerRotationControl(int perc) throws InterruptedException
	{
		if(perc==25)
		{    System.out.println("Inside the dailer function");
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(849, 1646)).release().perform();
		// Thread.sleep(2000);
		}else if(perc==50)
		{
			dailerRotationControl(25);
			action.press(PointOption.point(849, 1646)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(545, 1968)).release().perform();
			// Thread.sleep(2000);
		}
		else if(perc==75)
		{
			dailerRotationControl(50);
			action.press(PointOption.point(545, 1968)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(206, 1643)).release().perform();
			//  Thread.sleep(2000);
		}
		else if(perc==100)
		{
			dailerRotationControl(75);
			action.press(PointOption.point(206, 1643)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(553, 1427)).release().perform();
			//  Thread.sleep(2000);
		}
	}

	public String checkEpmpStartButton(String Parking)throws InterruptedException
	{  Thread.sleep(5000);
	ApcoaListeners.logInfo("Checking for Start Button in EPMP");
	try {
		if (gmpstartsession.isDisplayed()){
			ApcoaListeners.logInfo("Start Session Button Found -->"+Parking);
			return "False";
		}
		else 
		{ApcoaListeners.logInfo("Start Session Button Not Found -->"+Parking);
		return "True";}
	}
	catch (Exception e)
	{   System.out.println("hello");
	ApcoaListeners.logInfo("Start Session Button Not Found -->"+Parking);
	return "True";
	}
	}

	public void changeProfile() throws InterruptedException
	{
		ApcoaListeners.logInfo("going to click Start Session button");
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
		Thread.sleep(5000);
		// CommonUtility.GenericMethods.explicitWaitForWebElement(driver,PaymentProfile,100);
		String ProfileName= PaymentProfile.getText();
		System.out.println(ProfileName);
		Thread.sleep(3000);
		// CommonUtility.GenericMethods.explicitWaitForWebElement(driver,editCard,100);
		editCard.click();
		Thread.sleep(3000);
		System.out.println(Profile1.getText());
		System.out.println(Profile2.getText());
		// System.out.println(Profile3.getText());


		if(!Profile1.getText().equals(ProfileName))
		{
			Profile1.click();
		}
		else if(!Profile2.getText().equals(ProfileName))
		{
			Profile2.click();
		}

		Thread.sleep(4000);
		Confirm.click();
		Thread.sleep(5000);

	}
	public void CheckInvoiceAndResend() throws InterruptedException
	{
		ClickOnsideBar.click();
		Thread.sleep(3000);
		PassMenu.click();
		Thread.sleep(4000);
		ExpiredSession.click();
		Thread.sleep(2000);
		InvoiceMenu.click();
		Thread.sleep(2000);
		btnDownloadInvoice.click();

		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,ToastMessage,100);
		//String Toast_Message=ToastMessage.getText();
		//System.out.println(Toast_Message);


	}


	/*
	 * Implementing the Changing the Tarrif During Start Session in Staging Environment.
	 * Environment:Staging
	 */
	public void check_multiple_tarrif(ParkingMapper parkingMapper) throws InterruptedException
	{   ApcoaListeners.logInfo("going to click Start Session button");
	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
	Thread.sleep(5000);
	ApcoaListeners.logInfo("Selected Tariff   "+TarrifText1.getText());
	TarrifText1.click();

	Thread.sleep(2000);
	if(isDiscountApplied.isDisplayed())
	{
		removeDiscount();
	}
	Thread.sleep(3000);
	ApcoaListeners.logInfo("Rotating the Dialer");
	dailerRotationControl(25);
	String oldtarrif,updatedtarrif,TariffText1,TarrifText2,iTarrifText2;
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
	Parkingprice= ParkingPrice.getText();
	String currency=parkingMapper.getcurrency();
	int len=currency.length();
	oldtarrif = Parkingprice.substring(len);
	TariffText1=tariffText1.getText();
	ApcoaListeners.logInfo(TariffText1+"----->"+Parkingprice);


	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,tariffInfo,30);
	ApcoaListeners.logInfo("Going to click on Tariff info ");
	tariffInfo.click();
	Thread.sleep(2000);
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnTariffSelection,30);
	ApcoaListeners.logInfo("Going to Select Different Tariff");
	btnTariffSelection.click();
	Thread.sleep(3000);
	iTarrifText2=btnTarrif2.getText();
	ApcoaListeners.logInfo("Second Tariff Selected"+" "+iTarrifText2);
	btnTarrif2.click();

	Thread.sleep(2000);
	btnOkGotIt.click();
	Thread.sleep(2000);
	updatedtarrif=ParkingPrice.getText().substring(len);
	TarrifText2=tariffText1.getText();
	ApcoaListeners.logInfo(TarrifText2+"----> "+updatedtarrif);
	SA.assertEquals(TarrifText2,iTarrifText2);
	SA.assertNotEquals(TarrifText2,TariffText1);
	SA.assertNotEquals(oldtarrif,updatedtarrif);
	SA.assertAll();
	}


	/*
	 * Implementation:-Deleting the Buisness Profile 
	 */
	public void DeleteBuisnessProfile() throws InterruptedException
	{ 
		
	ApcoaListeners.logInfo("Going to Delete buisness profile");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ClickOnsideBar,30);
	ClickOnsideBar.click();
	Thread.sleep(2000);
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paymentNav,30);
	paymentNav.click();
	Thread.sleep(2000);
	SelectBusinessProfile.click();
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,makingDefault,10);
	ApcoaListeners.logInfo("Making Default profile ");
	makingDefault.click();
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,deleteProfile,10);
	deleteProfile.click();
	Thread.sleep(2000);
	ProceedBtn.click();
	ApcoaListeners.logInfo("Profile Deleted");
	
	}
	
	public boolean DeleteCorporateProfile(String CorporateName) throws InterruptedException
	{ 
	ApcoaListeners.logInfo("Going to Delete Corporate profile");
	ApcoaListeners.logInfo("Corporate Profile to be Deleted   :"+CorporateName);
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ClickOnsideBar,30);
	ClickOnsideBar.click();
	Thread.sleep(2000);
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paymentNav,30);
	paymentNav.click();
	Thread.sleep(2000);
	List<WebElement> profile = this.driver.findElements(ProfileName);
	for(int i=0;i<profile.size();i++)
	{
		if(profile.get(i).getText().equalsIgnoreCase(CorporateName))
		{   ApcoaListeners.logInfo("Got the Profile  :"+CorporateName);
			profile.get(i).click();
			break;
		}
		
	}
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,deleteProfile,10);
	deleteProfile.click();
	
	Thread.sleep(2000);
	ProceedBtn.click();
	ApcoaListeners.logInfo("Profile Deleted");
	
	Thread.sleep(6000);
	List<WebElement> profile1 = this.driver.findElements(ProfileName);
	for(int i=0;i<profile1.size();i++)
	{
		if(profile1.get(i).getText().equalsIgnoreCase(CorporateName))
		{
			return false;
			
		}
	}
	
	return true;
	
}
	
	


	/*
	 * Implementation :- Adding the Buisness Profile
	 */
	
	
	public void AddBuisnessprofile() throws InterruptedException
	{ApcoaListeners.logInfo("Going Add Buisness Profile");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,AddBuisnessProfile,30);
	AddBuisnessProfile.click();

	  try {
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SelectBuisnessProfile,30);
		SelectBuisnessProfile.click();
	} catch(Exception e) {}
	
	Thread.sleep(2000);
	ApcoaListeners.logInfo("Entering the Email id :  spoorthi@getmyparking.com");
	enterEmail.sendKeys("spoorthi@getmyparking.com");
	Thread.sleep(2000);
	ApcoaListeners.logInfo("Confirm the Profile Creation");
	confirmProfile.click();
	Thread.sleep(2000);

	SA.assertEquals(SelectBusinessProfile.getText(),"Business Profile");
	SA.assertAll();
	ApcoaListeners.logInfo("Buisness profile created Successfully!!");

	Thread.sleep(4000);
	((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));

	}
	
	public boolean AddCorporateprofile(String CorporateProfile,String corporateId,String EmailId) throws InterruptedException
	{
		
	ApcoaListeners.logInfo("Going Add Corporate Profile");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SelectAddProfile,30);
	SelectAddProfile.click();
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,SelectSubCorporate,30);
	SelectSubCorporate.click();
	Thread.sleep(4000);
	ApcoaListeners.logInfo("Going To Select SubCorporate   :"+CorporateProfile);
	List<WebElement> SP = this.driver.findElements(SubCorporate);
	for(int i=0;i<SP.size();i++)
	{
		if(SP.get(i).getText().equalsIgnoreCase(CorporateProfile))
		{   System.out.println(SP.get(i).getText());
			SP.get(i).click();
			break;
		}
	}
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,CorporateId,30);
	CorporateId.sendKeys(corporateId);
	ApcoaListeners.logInfo("Corporate Id  :"+corporateId);
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,email,30);
	email.sendKeys(EmailId);
	ApcoaListeners.logInfo("Email Id  :"+EmailId);
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,AcceptTerms,30);
	AcceptTerms.click();
	ApcoaListeners.logInfo("Terms Accepted");
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,confirmProfile,30);
	confirmProfile.click();
	
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Proceed,30);
	Proceed.click();
	
	
	Thread.sleep(8000);
	ApcoaListeners.logInfo("Checking Corporate Profile Added");
	List<WebElement> profile1 = this.driver.findElements(ProfileName);
	for(int i=0;i<profile1.size();i++)
	{
		if(profile1.get(i).getText().equalsIgnoreCase(CorporateProfile+" Profile"))
		{
			return true;
			
		}
	}
	return false;
	
}


	/*
	 * Checking the Start of session  without payments details in a Municipality CarPark in Denmark.
	 * Environment:Production
	 */
	public void CheckPriceInFreeParking(FreeParkingMapper parkingMapper ) throws InterruptedException
	{   String Promo=parkingMapper.getpromo();

	ApcoaListeners.logInfo("going to click Start Session button");
	
	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpstartsession,100);
	try {
		CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmpAustriatarrifSelection,5);
		
	}
	catch(Exception e)
	{}
	Thread.sleep(5000);

	ApcoaListeners.logInfo("Applying the Free Parking Promo Code:   "+Promo);
	addAnotherPromoCode(Promo);

	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ParkingPrice,30);
	Parkingprice= ParkingPrice.getText();

	String currency=parkingMapper.getcurrency();
	int len=currency.length();
	ActualInitialParkingPrice = Parkingprice.substring(len);
	ApcoaListeners.logInfo("Free Parking Price     "+ActualInitialParkingPrice);
	SA.assertEquals(ActualInitialParkingPrice,"0.00");
	SA.assertAll();
	CommonUtility.GenericMethods.explicitWaitForWebElement(driver,clickOnDialer,100);
	PaymentConfirmation();


	}

	public void editpersonalDetails(String first,String last) throws InterruptedException
	{   ApcoaListeners.logInfo("Going to Edit the User Name");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		
		String ActualFirstName,ActualLastName,ActualEmail;
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ProfileInfo,30);
		ProfileInfo.click();
		try {
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,Profile,10);
		Profile.click();
		}
		catch(Exception e) {}
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,editProfileInfo,30);
	     editProfileInfo.click();
	     
		ActualFirstName=FirstName.getText();
		
		ActualLastName=LastName.getText();
		ApcoaListeners.logInfo("ActualFirstName  :"+ActualFirstName);
		
		ApcoaListeners.logInfo("ActualLastName  :"+ActualLastName);
		
		changeNameandEmail(first,last);
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,editProfileInfo,30);
	     editProfileInfo.click();
		changeNameandEmail(ActualFirstName,ActualLastName);;
		
		
}
	
	public void changeNameandEmail(String firstname,String lastname) throws InterruptedException
	{    ApcoaListeners.logInfo("Changing First Name to   :"+firstname);
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,FirstName,30);
		FirstName.sendKeys(firstname);
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Changing Last Name to   :"+lastname);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,LastName,30);
		LastName.sendKeys(lastname);
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Confirm the Change ");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ConfirmChange,30);
		ConfirmChange.click();
		
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		SA.assertEquals(UserName.getText(),firstname+" "+lastname);
		SA.assertAll();
	}
	
	public void changingDefaultProfile(String Profile) throws InterruptedException {

		ApcoaListeners.logInfo("Profile to be set Default  ------>"+Profile);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ClickOnsideBar,30);
		ClickOnsideBar.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,paymentNav,30);
		paymentNav.click();
		Thread.sleep(4000);

		List<WebElement> profile = this.driver.findElements(ProfileList);
		for(WebElement singleProfile : profile){

			if(singleProfile.getText().equalsIgnoreCase(Profile))
			{	
				ApcoaListeners.logInfo("Profile Selected :"+singleProfile.getText());
				singleProfile.click();
				break;
			}
		}

		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,makingDefault,30);
		makingDefault.click();
		ApcoaListeners.logInfo("Set to default profile");


		Thread.sleep(2000);


		GoBacktoSideBar.click();
		Thread.sleep(2000);
		GoBacktoSideBar.click();
		ApcoaListeners.logInfo("Setting up  Default profile  done");


	}

	public void excCommand(String check) throws IOException{


		String command ="cp "+System.getProperty("user.dir")+"/"+check+"/poster.png /home/ritik/Android/Sdk/emulator/resources";

		try{Process p = Runtime.getRuntime().exec(command);}catch(Exception e) {System.out.println(e.toString());}

	}

	public void removeQR()
	{ 
		String command ="rm /home/ritik/Android/Sdk/emulator/resources/poster.png";
        
		try{Process p = Runtime.getRuntime().exec(command);}catch(Exception e) {System.out.println(e.toString());}

	}

	public String StartPostpaySession() throws IOException, InterruptedException
	{   ApcoaListeners.logInfo("Going to click on TAP TO ACCESS");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,startSessionwithpass,30);
	ApcoaListeners.logInfo("Clicked on TAP TO ACCESS");

	removeQR();
	Thread.sleep(4000);
	String EntryQRFolder=AutomationConfiguration.Tenant+AutomationConfiguration.Environment+"EntryQR";
	excCommand(EntryQRFolder);
	Thread.sleep(4000);
	startSessionwithpass.click();
	ApcoaListeners.logInfo("Going to Scan Entry QR");
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ClickOnsideBar,30);
	ClickOnsideBar.click();
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,30);
	MenuMySessions.click();
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,30);
	ApcoaListeners.logInfo("Postpay Session Started");
	ActiveSessionID=MySessionsSessionID.getText();
	ApcoaListeners.logInfo("Active Session Id :    "+ActiveSessionID);
	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GoBacktoSideBar,30);
	GoBacktoSideBar.click();

	return ActiveSessionID;
	}


	public void  AddDiscountByQRScan(int promoNum) throws InterruptedException, IOException
	{   
		if (promoNum==1)
			ApcoaListeners.logInfo("Going to Add Discount by Scanning QR");
		else if(promoNum==2)
		{
			ApcoaListeners.logInfo("Going to Change the Promo by Scanning QR");
		}
		removeQR();
		System.out.println("Remove the QR");

		if(promoNum==1)
			{
			String DiscountQRFolder=AutomationConfiguration.Tenant+AutomationConfiguration.Environment+"DiscountQR";
			excCommand(DiscountQRFolder);
			}
		else 
			{
			String DiscountQR2Folder=AutomationConfiguration.Tenant+AutomationConfiguration.Environment+"DiscountQR2";
			excCommand(DiscountQR2Folder);
			}
		Thread.sleep(4000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ScanQRButton,30);
		ScanQRButton.click();
		if(promoNum==2)
		{
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnConfirmPromo,50);
			SA.assertEquals(PromoName.getText(),"Are you sure you want to remove the discount applied ?");
			btnConfirmPromo.click();
		}
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnConfirmPromo,50);
		
		
		
		
		
		AppliedPromoByQR=PromoName.getText();
		int idx = AppliedPromoByQR.indexOf(":");
		AppliedPromoByQR = AppliedPromoByQR.substring(idx+2);
        
		SA.assertEquals("OKAY, GOT IT!",btnConfirmPromo.getText());
		btnConfirmPromo.click();
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,ClickOnsideBar,30);
		ClickOnsideBar.click();
		 
		Thread.sleep(3000);
		 ((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		 
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PromoCheck,50);
		SA.assertEquals(PromoCheck.getText(),AppliedPromoByQR);
		ApcoaListeners.logInfo("Promo Applied Successfully     "+AppliedPromoByQR);
		SA.assertAll();

		TestLogin.AppliedPromo=AppliedPromoByQR;
        

	}

	public void StopPostpaySession(String SessionId,String AppliedPromo) throws IOException, InterruptedException
	{   ActiveSessionID=SessionId;
	ApcoaListeners.logInfo("Going to Exit the Session");
	removeQR();
	Thread.sleep(4000);
	String ExitQRFolder=AutomationConfiguration.Tenant+AutomationConfiguration.Environment+"ExitQR";
	excCommand(ExitQRFolder);
	TaptoExit.click();
	ApcoaListeners.logInfo("Scanning The EXIT QR");

	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsSessionID,30);
	ExpiredSessionID=MySessionsSessionID.getText();
	AppliedPromoByQRInExpire=AppliedPromoInExpire.getText();

	ApcoaListeners.logInfo("Past Session Id :    "+ExpiredSessionID);
	System.out.println(ExpiredSessionID);
	System.out.println(ActiveSessionID);
	SA.assertEquals(AppliedPromoByQRInExpire,AppliedPromo);
	SA.assertEquals(ActiveSessionID,ExpiredSessionID);
	SA.assertAll();
	ApcoaListeners.logInfo("Exit Session Complete");

	
	Thread.sleep(4000);
	((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}
	
	
	public void navigateToDownloadPDF() throws InterruptedException, IOException
	{   
		String SessionID,TotalCost,PDFNumber;
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,30);
		MenuMySessions.click();
		Thread.sleep(3000);
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsGotoExpiredSessions,30);
		MySessionsGotoExpiredSessions.click();
		
		SessionID=MySessionsSessionID.getText();
		TotalCost=MySessionsTotalCost.getText();
		
		System.out.println(SessionID);
		System.out.println(TotalCost);
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,DownloadPDF,30);
		DownloadPDF.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,DownloadInvoice,30);
		DownloadInvoice.click();
		
		
		action.press(PointOption.point(553, 1427)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(602, 1444)).release().perform();
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PDFName,30);
		PDFNumber=PDFName.getText();
		System.out.println(PDFNumber);
		Thread.sleep(4000);
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		VerifyPDF VP=new VerifyPDF();
		String PDF=VP.SendPDF(PDFNumber);
		
		System.out.println(PDF);
		
		
		
	}
	
	
	public void navigateToDownloadPDF(ParkingMapper parkingMapper) throws InterruptedException, IOException
	{   
		SoftAssert SA=new SoftAssert();
		String SessionID,TotalCost,PDFNumber,ParkingName,VehicleNum,Paymentcard,Currency,Username;
	    
		ApcoaListeners.logInfo("Going to click on hamburger menu");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,btnMenu,30);
		btnMenu.click();
		ApcoaListeners.logInfo("Going to click on MySession menu");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MenuMySessions,30);
		MenuMySessions.click();
		Thread.sleep(3000);
		ApcoaListeners.logInfo("Going to click on Expired Session menu");
		try {
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,MySessionsGotoExpiredSessionsApcoa,10);
		MySessionsGotoExpiredSessionsApcoa.click();
		}
		catch(Exception e)
		{
			CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,GMPMySessionsGotoExpiredSessions,10);
			GMPMySessionsGotoExpiredSessions.click();
		}
		
		SessionID=MySessionsSessionID.getText();
		TotalCost=MySessionsTotalCost.getText().split(parkingMapper.getcurrency())[1];
		ParkingName=ActiveParking.getText();
		VehicleNum=VehicleNumber.getText();
	    Paymentcard=PaymentCard.getText();
		
		
		
		
		if(parkingMapper.getcurrency().equals("PLN"))
			Currency="zł";
		else if(parkingMapper.getcurrency().equals("SEK"))
			Currency="kr";
		else
			Currency=parkingMapper.getcurrency();
		
		ApcoaListeners.logInfo("Session ID in Expired session                      :"+SessionID);
		ApcoaListeners.logInfo("Total Cost in Expired session                      :"+TotalCost);
		ApcoaListeners.logInfo("Parking Name in Expired session                    :"+ParkingName);
		ApcoaListeners.logInfo("Vehicle number in Expired session                  :"+VehicleNum);
		ApcoaListeners.logInfo("Last digits of Payment Card  in Expired session    :"+Paymentcard);
		ApcoaListeners.logInfo("Currency Symbol to be check in invoice             :"+Currency);
		
		System.out.println("");
		ApcoaListeners.logInfo("Going to download the Invoice PDF");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,DownloadPDF,30);
		
		DownloadPDF.click();
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,DownloadInvoice,30);
		DownloadInvoice.click();
		Thread.sleep(4000);
		ApcoaListeners.logInfo("Scrolling Down the Notification");
		action.press(PointOption.point(362, 100)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(362, 502)).release().perform();
		
		ApcoaListeners.logInfo("Getting the PDF number");
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,PDFName,30);
		PDFNumber=PDFName.getText();
		ApcoaListeners.logInfo("Invoice Number"+PDFNumber);
		Thread.sleep(4000);
		
		CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,closeallnotification,30);
		closeallnotification.click();
		
		((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		VerifyPDF VP=new VerifyPDF();
		String PDF=VP.SendPDF(PDFNumber);
		
		System.out.println(PDF);
		ApcoaListeners.logInfo("Checking the Data in the Invoice PDF..............");
		SA.assertTrue(PDF.contains(TotalCost),"Total Cost is different or Not found");
		SA.assertTrue(PDF.contains(Paymentcard),"Payment Card Number Not Found ");
		SA.assertTrue(PDF.contains(VehicleNum),"Vehicle Number is different or Not found");
		SA.assertTrue(PDF.contains(ParkingName),"Parking Name is different or Not found");
		SA.assertTrue(PDF.contains(Currency),"Currency Symbol is different or Not found");
		
		
		SA.assertAll();
		
	}
	
	
	
	public void GetBackToHomeScreen(int i) throws InterruptedException
	{  
		
		try {
	    	CommonUtility.GenericMethods.explicitWaitForWebElementOnly(driver,close,5);
		  close.click();
	       }
	catch(Exception e)
	{}
		
	   if(checknotificationbtn()||i==10)
	   {   ApcoaListeners.logInfo("Already at home screen");
		   return;
	   }
	   else
	   {  i++;
		   ApcoaListeners.logInfo("Not At home Screen");
	      ApcoaListeners.logInfo("pressing the back button");
		   ((AndroidDriver<WebElement>) AutomationConfiguration.AppiumDriver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	        Thread.sleep(3000);
		   GetBackToHomeScreen(i);
	   }
	
	
	}
	
	public boolean checknotificationbtn()
	{
		try {
			 if(BtnNotification.isDisplayed())
			   {   ApcoaListeners.logInfo("Already at home screen");
				   return true;
			   }
			 return false;
		}
		catch(Exception e) {
			return false;
		}
	}
	
   public void checkDeepLink()
   {
	   try {
			CommonUtility.GenericMethods.explicitWaitForWebElement(driver,gmptarrifSelection,10);
		}
		catch(Exception e) {}
	   CommonUtility.GenericMethods.explicitWaitForWebElement(driver,ParkingHour,10);
	 //  CommonUtility.GenericMethods.explicitWaitForWebElement(driver,ParkingMinute,10);
	 
   }


}






