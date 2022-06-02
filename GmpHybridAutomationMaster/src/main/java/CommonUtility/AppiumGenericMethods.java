/*
-------------------------------------------------------------
Author Name: Karan Kumar Agarwal

Date:24-Sep-2021

Purpose /Description: Common methods for the Appium, that is 
	to perform the operation on mobile app using appium
-------------------------------------------------------------

*/

package CommonUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;


/**
 * contains basic functionality for the Appium.
 *
 */

public class AppiumGenericMethods extends CreateSession 
{
	WebDriver driver ;

	
	public static void threadWait(AppiumDriver<MobileElement> driver, long time) throws InterruptedException {
		synchronized (driver) {
			driver.wait(time);
		}
	}
	
	
    // common timeout for all tests can be set here
    public final int timeOut = 40;

    public AppiumGenericMethods(WebDriver driver)
    {
        this.driver = driver;
    }
    
    /**
	 * method to check element present or not.
	 * @param targetElement for the targeted Element
	 * 
	 * @return true if element is present and false if not present
	 */
    public Boolean isElementPresent(By targetElement) throws InterruptedException 
    {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }

    /**
     * method to hide keyboard
     */
    @SuppressWarnings("unchecked")
	public void hideKeyboard() 
    {
        ((AppiumDriver<WebElement>) driver).hideKeyboard();
    }

    /**
     * method to go back by Android Native back clickaction.press(PointOption.point(438, 1564)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(477, 2038)).release().perform();
		
     */
    @SuppressWarnings("unchecked")
	public void back() 
    {
        ((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    

    /**
     * method to tap on the screen on provided coordinates
     *
     * @param xPosition x coordinate to be tapped
     * @param yPosition y coordinate to be tapped
     */
    public void tap(double xPosition, double yPosition) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("startX", xPosition);
        tapObject.put("startY", yPosition);
        js.executeScript("mobile: tap", tapObject);
    }


    /**
     * method to find an element
     *
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator)
    {
        try {
            WebElement element = driver.findElement(locator);
            return element;
        } 
        catch (NoSuchElementException e) 
        {
        	AutomationConfiguration.Log.error("Element not found");
        	throw e;
        }
    }

    /**
     * method to find all the elements of specific locator
     *
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator) 
    {
        try 
        {
            List<WebElement> element = driver.findElements(locator);
            return element;
        } 
        catch (NoSuchElementException e) 
        {
            //Log.logError(this.getClass().getName(), "findElements", "element not found" + locator);
        	AutomationConfiguration.Log.error("Element not found");
        	
        	throw e;
        }
    }

    /**
     * method to get message test of alert
     *
     * @return message text which is displayed
     */
    public String getAlertText()
    {
        try
        {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        } 
        catch (NoAlertPresentException e) 
        {
            throw e;
        }
    }

    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        } 
        catch (NoAlertPresentException e) 
        {
            throw e;
        }
    }

    /**
     * method to Accept Alert if alert is present
     */

    public void acceptAlert()
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * method to Dismiss Alert if alert is present
     */

    public void dismissAlert()
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    /**
     * method to get network settings
     */
    @SuppressWarnings("unchecked")
	public void getNetworkConnection() 
    {
        System.out.println(((AndroidDriver<WebElement>) driver).getConnection());
    }


    /**
     * method to set network settings
     *
     * @param airplaneMode pass true to activate airplane mode else false
     * @param wifi         pass true to activate wifi mode else false
     * @param data         pass true to activate data mode else false
     */
    @SuppressWarnings("unchecked")
	public void setNetworkConnection(boolean airplaneMode, boolean wifi, boolean data)
    {

        long mode = 1L;

        if (wifi) {
            mode = 2L;
        } else if (data) {
            mode = 4L;
        }

        ConnectionState connectionState = new ConnectionState(mode);
        ((AndroidDriver<WebElement>) driver).setConnection(connectionState);
        System.out.println("Your current connection settings are :" + ((AndroidDriver<WebElement>) driver).getConnection());
    }


   
    /**
     * method to get all the context handles at particular screen
     */
    @SuppressWarnings("unchecked")
	public void getContext() 
    {
        ((AppiumDriver<WebElement>) driver).getContextHandles();
    }

    /**
     * method to set the context to required view.
     *
     * @param context view to be set
     */
    @SuppressWarnings("unchecked")
	public void setContext(String context)
    {

        Set<String> contextNames = ((AppiumDriver<WebElement>) driver).getContextHandles();

        if (contextNames.contains(context)) 
        {
            ((AppiumDriver<WebElement>) driver).context(context);
            AutomationConfiguration.Log.info("Context changed successfully");
        } 
        else 
        {
        	AutomationConfiguration.Log.info(context + "not found on this page");
        }

        AutomationConfiguration.logInfo("Current context" + ((AppiumDriver<WebElement>) driver).getContext());
    }

    /**
     * method to long press on specific element by passing locator
     *
     * @param locator element to be long pressed
     */
    public void longPress(By locator) 
    {
        try {
            WebElement element = driver.findElement(locator);

            @SuppressWarnings("rawtypes")
			TouchAction touch = new TouchAction((MobileDriver) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
            AutomationConfiguration.logInfo("Long press successful on element: " + element);
        } 
        catch (NoSuchElementException e) 
        {
            //Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
        	AutomationConfiguration.Log.error("Element not found");
        	throw e;
        }

    }

    /**
     * method to long press on specific x,y coordinates
     *
     * @param x x offset
     * @param y y offset
     */
    public void longPress(int x, int y) 
    {
        @SuppressWarnings({ "unchecked", "rawtypes" })
		TouchAction touch = new TouchAction((MobileDriver<WebElement>) driver);
        @SuppressWarnings("rawtypes")
		PointOption pointOption = new PointOption();
        pointOption.withCoordinates(x, y);
        touch.longPress(pointOption).release().perform();
        AutomationConfiguration.logInfo("Long press successful on coordinates: " + "( " + x + "," + y + " )");

    }

    /**
     * method to long press on element with absolute coordinates.
     *
     * @param locator element to be long pressed
     * @param x       x offset
     * @param y       y offset
     */
    @SuppressWarnings("rawtypes")
	public void longPress(By locator, int x, int y) 
    {
        try
        {
            WebElement element = driver.findElement(locator);
            @SuppressWarnings("unchecked")
			TouchAction touch = new TouchAction((MobileDriver<WebElement>) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withPosition(new PointOption().withCoordinates(x, y)).withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
            AutomationConfiguration.logInfo("Long press successful on element: " + element + "on coordinates" + "( " + x + "," + y + " )");
        } 
        catch (NoSuchElementException e) 
        {
            //Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
        	AutomationConfiguration.Log.error("Element not found");
        	throw e;
        }

    }

    /**
     * method to swipe on the screen on provided coordinates
     *
     * @param startX   - start X coordinate to be tapped
     * @param endX     - end X coordinate to be tapped
     * @param startY   - start y coordinate to be tapped
     * @param endY     - end Y coordinate to be tapped
     * @param duration duration to be tapped
     */

    public void swipe(double startX, double startY, double endX, double endY, double duration)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        // swipeObject.put("touchCount", 1.0);
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        js.executeScript("mobile: swipe", swipeObject);
    }


    public static String UiScrollable(String uiSelector) 
    {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }

    /**
     * method to open notifications on Android
     */

    @SuppressWarnings("unchecked")
	public void openNotifications() 
    {
        ((AndroidDriver<WebElement>) driver).openNotifications();
    }

    /**
     * method to launchApp
     */

    @SuppressWarnings("unchecked")
	public void launchApp() 
    {
        ((AppiumDriver<WebElement>) driver).launchApp();
    }


    /**
     * method to click on Element By Name
     *
     * @param elementByName - String element name to be clicked
     */

    @SuppressWarnings("unchecked")
	public void click(String elementByName)
    {
        ((AppiumDriver<WebElement>) driver).findElementByName(elementByName).click();
    }

    /**
     * method to scroll down on screen from java-client 6
     *
     * @param swipeTimes       number of times swipe operation should work
     * @param durationForSwipe time duration of a swipe operation
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void scrollDown(int swipeTimes, int durationForSwipe) 
    {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 1; i <= swipeTimes; i++) 
        {
            int start = (int) (dimension.getHeight() * 0.5);
            int end = (int) (dimension.getHeight() * 0.3);
            int x = (int) (dimension.getWidth() * .5);


            new TouchAction((AppiumDriver<WebElement>) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .release().perform();
        }
    }


    /**
     * method to scroll up on screen from java-client 6
     *
     * @param swipeTimes       number of times swipe operation should work
     * @param durationForSwipe time duration of a swipe operation
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void scrollUp(int swipeTimes, int durationForSwipe)
    {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) 
        {
            int start = (int) (dimension.getHeight() * 0.3);
            int end = (int) (dimension.getHeight() * 0.5);
            int x = (int) (dimension.getWidth() * .5);


            new TouchAction((AppiumDriver<WebElement>) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .release().perform();
        }
    }



}
