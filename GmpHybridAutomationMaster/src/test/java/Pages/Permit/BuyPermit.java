package Pages.Permit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuyPermit {
	WebDriver driver;
	
	@FindBy(css="button[aria-label='menu']")
	WebElement menu;
	
	@FindBy(xpath="//span[contains(text(),'Home')]")
	WebElement home;
	
	@FindBy(css="input[placeholder='Search for a location or city...']")
	WebElement location;
	
	@FindBy(className="MuiInputBase-input")
	WebElement enter;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[2]/div/div[4]/div/div[2]/div[2]/div/button")
	WebElement permit;
	
	@FindBy(xpath="//p[contains(text(),'Ritik SubCorporate Linking Test')]")
	WebElement directbuy;
	
	@FindBy(xpath="//p[contains(text(),'Continue')]")
	WebElement continuele;
	
	@FindBy(xpath="//span[contains(text(),'Agree')]")
	WebElement agree;
	
	@FindBy(xpath="//span[contains(text(),'Buy Permit')]")
	WebElement permitel;
	
	@FindBy(xpath="//span[contains(text(),'Go to My Permits')]")
	WebElement mypermit;
	
	public BuyPermit(WebDriver driver) {
		this.driver=driver;
	}
	
	public void choosepermit() {
		menu.click();
		home.click();
	}
	
	public void entercity(String town) {
		location.sendKeys(town);	 
	    enter.sendKeys(Keys.RETURN);
	    permit.click();
	}
	
	public void buypermit() {
		directbuy.click();
	}
	
	public void clicks() {
		continuele.click();
		agree.click();
		permitel.click();
		mypermit.click();
	}
}