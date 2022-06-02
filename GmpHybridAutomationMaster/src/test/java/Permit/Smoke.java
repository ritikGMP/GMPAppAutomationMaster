package Permit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonUtility.AutomationConfiguration;
import CommonUtility.CreateSession;
import Pages.Permit.ActivePermit;
import Pages.Permit.BuyPermit;
import Pages.Permit.ChooseLocation;
import Pages.Permit.ChoosePermit;
import Pages.Permit.Details;
import Pages.Permit.LoginPage;
import Pages.Permit.MyPermit;
import Pages.Permit.SendForApproval;

public class Smoke {


	String useremail = "testing@yopmail.com";
	String password = "testing";
	String location = "Bangalore";
	String contact = "9999998888";
	String address = "123Street";
	String city = "Nainital";
	String state = "Uttarakhand";
	String country = "India";
	String zipcode = "236002";
	String town ="Bangalore";
	WebDriver driver;
	@Test
	public void loginTestPOM() {

		driver = AutomationConfiguration.Driver;
		LoginPage loginPage = new LoginPage(driver);

		loginPage.setUserCredentials(useremail,password);
		loginPage.clickLogin();

		Assert.assertEquals(loginPage.checklogin(),true,"Something Went Wrong");
		// choose location 
		ChooseLocation chooselocation = new ChooseLocation(driver);
		chooselocation.enterLocation(location);
		// choose permit 
		ChoosePermit choosePermit = new ChoosePermit(driver);
		choosePermit.choosePermit();
		// send for approval
		SendForApproval approval = new SendForApproval(driver);
		approval.selectPermit();
		approval.clickContinue();
		approval.sendForApproval();
		approval.myPermits();
		// buy permits
		BuyPermit buy =new BuyPermit(driver);
		buy.choosepermit();
		buy.entercity(town);
		buy.buypermit();
		buy.clicks();
		// Check the permit
		ActivePermit check=new ActivePermit(driver);
		check.active();
		// my permits
		MyPermit permit = new MyPermit(driver);
		permit.mypermits();
		//Details 
		Details detail =new Details(driver);
		detail.setContactnumber(contact);
		detail.setAddress(address);
		detail.setCity(city);
		detail.setState(state);
		detail.setCountry(country);
		detail.setZipcode(zipcode);
	}

	@BeforeMethod
	public void setup() throws IOException {
		CreateSession.readConfigFile("/src/test/java/resources/configPermit.properties");
		AutomationConfiguration.Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     
		AutomationConfiguration.Driver.findElement(By.cssSelector("button[aria-label='menu']")).click();
		AutomationConfiguration.Driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void close() {}
}
