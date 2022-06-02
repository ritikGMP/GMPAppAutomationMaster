package Pages.Mobile;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class PageDiscount {

	
	// 602,1444
	//com.apcoaflow.consumer:id/tv_add_discount
	
	//com.apcoaflow.consumer:id/et_promo_code sendkeys
	
	//com.apcoaflow.consumer:id/tv_apply_promo_code click
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement btnAddDiscount;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement edtPromoCode;
	
	@AndroidFindBy(xpath="//*[contains(@resource-id,':id/et_promo_code')]")
	WebElement btnApplyPromoCode;
	
	
	
}
