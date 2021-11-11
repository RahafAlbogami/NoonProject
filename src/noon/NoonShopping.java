package noon;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class NoonShopping {

	AndroidDriver<MobileElement> driver;

	@BeforeTest
	void setUp() throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "sdk_gphone64_x86_64");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability("platformVersion", "12");

		this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		this.driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

		// Get the dimension of the device
		Dimension dims = driver.manage().window().getSize();
		// init start point = center of screen
		PointOption pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
		// center of header
		PointOption pointOptionEnd = PointOption.point(dims.width / 2, 10);
		// swap screen to get the apps screen
		TouchAction action = new TouchAction(driver);
		action.press(pointOptionStart).waitAction(new WaitOptions().withDuration(Duration.ofMillis(3000))) // requirement
				.moveTo(pointOptionEnd).release().perform();
		Thread.sleep(3000);
		
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"noon\")")).click();


	}
	
	@Test
	void Login() throws InterruptedException {

		this.driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"My Account, tab, 4 out of 5\"]")
				.click();

		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")
				.click();

		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText")
				.sendKeys("EMAIL");

		Thread.sleep(3000);

		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.EditText")
				.sendKeys("PASSWORD");

		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
				.click();

	}

	@Test(dependsOnMethods = { "Login" })
	void GoCategories() throws InterruptedException {
		Thread.sleep(3000);
		this.driver.findElementByAccessibilityId("Categories, tab, 2 out of 5").click();

	}

	@Test(dependsOnMethods = { "GoCategories" })
	void FirstCategories() {
		// Just For You
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Just for you\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Video Games\")")).click();
		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")
				.click();

		// ADD TO CART
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"ADD TO CART\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONTINUE SHOPPING\")")).click();

	}

	@Test(dependsOnMethods = { "FirstCategories" })
	void SecondCategories() {
		// Electronics
		this.driver.findElementByAccessibilityId("selected, Categories, tab, 2 out of 5").click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Electronics\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Headphones & Speakers\")"))
				.click();
		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[2]\r\n")
				.click();

		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]\r\n")
				.click();

		// ADD TO CART
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"ADD TO CART\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONTINUE SHOPPING\")")).click();

	}

	@Test(dependsOnMethods = { "SecondCategories" })
	void ThirdCategories() {
		// Fragrance 
		this.driver.findElementByAccessibilityId("selected, Categories, tab, 2 out of 5").click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Fragrance\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Premium Scents\")")).click();

		// Scroll to the object and Click
		this.driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Miss Dior Blooming\").instance(0))")
				.click();

		// ADD TO CART
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"ADD TO CART\")")).click();
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CONTINUE SHOPPING\")")).click();

	}

	@Test(dependsOnMethods = { "ThirdCategories" })
	void checkout() throws InterruptedException {
		// CART
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Cart\")")).click();
		this.driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup\r\n")
				.click();
		
		// Choose Payment Method (Credit Card)
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Pay With Card\")")).click();
		Thread.sleep(3000);
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Add a New card\")")).click();
		
		// Fill Credit Card Form
		MobileElement credit = this.driver
				.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Enter your card number\")"));
		credit.click();
		credit.sendKeys("CREDIT_CARD");

		MobileElement Expiry = this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"MM/YY\")"));
		Expiry.click();
		Expiry.sendKeys("MM/YY");

		MobileElement secureCode = this.driver
				.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Secure Code\")"));
		secureCode.click();
		secureCode.sendKeys("CCV");
		
		// ADD CARD
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"ADD MY CARD\")")).click();
	}

	@Test(dependsOnMethods = { "checkout" })
	void creditCardValidation() {
		// HANDLE ERROR MESSAGE 
		String expectedResult = "Oops! This card number is invalid.";
		MobileElement errorMsg = this.driver.findElement(
				MobileBy.AndroidUIAutomator("new UiSelector().text(\"Oops! This card number is invalid.\")"));
		String actualResult = errorMsg.getText();
		Assert.assertEquals(expectedResult, actualResult);

	}
	
	@AfterTest
	void close() {
		this.driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"CANCEL\")")).click();
	}

}
