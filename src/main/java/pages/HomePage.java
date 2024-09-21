package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.w3c.dom.ls.LSException;

import constants.Attribute;

// new, you have to manually write it to get access of common actions
// this is possible when they are static in nature, * means all
// This is called static import
import static common.CommonActions.*;
import static common.CommonWaits.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

public class HomePage {
	public WebDriver driver;
	public WebDriverWait wait;
	JavascriptExecutor js;
	Actions actions;

	// parameterized constructor initialized when class in instantiated [object created]
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// PageFactory class help to instantiate WebElements
		// Important interview question
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
		
	}
	
	@FindBy(xpath = "//em[@id='cms-homepage-header-logo-unauth' and @class='cms-icon cms-sprite-loggedout ms-3' ]")
	WebElement logo;
	
	// 1st way: Most common way to write the WebElement (@FindBy), 
	// you must know how to do that, 98% cases, this is used
	// Use of unique 'Id' attribute as locator
	@FindBy(id = "cms-login-submit")
	WebElement loginButton;
	
	// Use of unique 'name' attribute as locator
	@FindBy(name = "user-d")
	WebElement userId;
	
	// 2nd way to create a WebElement: not common, here I used for the 'password'
	// Just to make you guys familiar, if you see in your job
	@FindBy(how = How.NAME, using = "pass-d")
	WebElement password;
	
	// Use of unique  'class' attribute as locator
	// FYI: Never take a class value as unique if they have white space between words. 
	// Example: class value of logo, this is unique, but a compound class --> "cms-icon cms-sprite-loggedout ms-3"
	@FindBy(className = "cms-newuser-reg")
	WebElement newUserRegistration;
	
	// 3rd way to create a WebElement with "By" Class: 
	// Not common, here I used unlock web element from the home page
	// instead of xpath, we can use id, name, class etc as locator.
	// By unlock = By.id("cms-unlock-account");	
	By unlock = By.xpath("//a[contains(text(), 'un')]"); 
	
	@FindBy(linkText = "User ID")
	WebElement forgotUserId;
	
	@FindBy(partialLinkText = "Passwor")
	WebElement forgotPassword;
	
	// To Test implicitly wait
	@FindBy(className = "-newuser-reg")
	WebElement incorrectNewUserRegistration;
	
	@FindBy(xpath = "//label[@id='cms-label-tc']")
	WebElement termsAndCondition;
	
	@FindBy(css = "em.cms-icon.cms-sprite-loggedout.ms-3")
	WebElement logoByCssSelector;
	
	@FindBy(xpath = "//span[text()='Help']")
	WebElement help;
	
	@FindBy(xpath = "//h1[text()='CMS Enterprise Portal - Help Center']")
	WebElement helpPageHeader;
	
	public void clickLogo() {
		logo.click(); // common method 'clickElement()' is not used here
	}
	
	// We used throws to handle exception in this method
	public void clickLoginButton() {
		pause(4000); // common method 'pause()' is used from here
		clickElement(loginButton); // common method 'clickElement()' is used from here
		pause(4000);	
	}
	
	public void clickUserId() {
		clickElement(userId);
	}
	
	public void clickPassword() {
		clickElement(password);
	}
	
	// We used try-catch block to handle exception in this method
	public void clickNewUserRegistration() {
		try {
			Thread.sleep(4000);
			clickElement(newUserRegistration);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Not common, just for your reference
	public void clickUnlock() {
		try {
			Thread.sleep(4000);
			// This is the new way to call the web element
			driver.findElement(unlock).click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println(e); // alternate of e.printStackTrace()
		}		
	}
	
	// How to test implicitlyWait()? or
	// How to show the exception: NoSuchElementException
	public void clickIncorrectNewUserRegistration() {
		try {
			Thread.sleep(4000);
			clickElement(incorrectNewUserRegistration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// We are using sendKeys() method to input the text in any field
	public void inputTextInUserIdField () {
		pause(4000);
		userId.sendKeys("May2024QA"); // common method 'inputText()' is not used here
		pause(4000);
	}
	
	// We are using 3 common actions here
	public void inputTextInUserIdAndPasswordFieldThenIAgreeAndClickToTheLoginButton() {
		pause(4000);
		inputText(userId, "enthrall_12");
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		clickElement(loginButton);
		pause(4000);
	}
	
	// Alternate of above method {Raw Code, some people use like this way}
	// We can use a web element directly in the method, that is also common
	// we don't need to create "webElement" and "common method"
	public void useOfByClassInLoginProcess() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.name("user-d")).sendKeys("enthrall_12");
		driver.findElement(By.name("pass-d")).sendKeys("OnthrallTest@1234");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//label[@id='cms-label-tc']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("cms-login-submit")).click();
		Thread.sleep(4000);
	}
	
	// Important: Use of linkText as locator
	// Web Element: forgot userId
	public void use_of_linkText_as_locator_in_forgot_userId_hyperlink() {
		pause(3000);
		clickElement(forgotUserId);
		pause(4000);
	}
	
	// Important: Use of partial linkText as locator
	// Web Element: forgot password
	public void use_of_partialLinkText_as_locator_in_forgot_passowrd_hyperlink() {
		pause(3000);
		clickElement(forgotPassword);
		pause(4000);
	}
	
	// Web Element: Logo
	// logo: class attribute value:  cms-icon cms-sprite-loggedout ms-3
	// Exception 'InvalidSelectorException': 'Compound class names not permitted',
	// so even if you see class value is unique but separated by space, 
	// avoid this as class name locator, rather use css selector
	// This scenario will show error and will failed everywhere
	public void why_we_use_cssSelector_as_locator_in_logo () {
		driver.findElement(By.className("cms-icon cms-sprite-loggedout ms-3")).click();
	}
	
	// Web Element: New User Registration Button
	// Important: Use of cssSelector as locator
	// cssSelector is very important for interview
	// cssSelector by class --> inside the string, first put html tag, then dot, then write value of the class attribute 
	public void role1_use_of_cssSelector_by_class_name_value () {
		driver.findElement(By.cssSelector("a.cms-newuser-reg")).click();
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// if you wanna create cssSelector by id 
	// first html tag, then # (hash), then write the value of id attribute inside the string
	public void role2_use_of_cssSelector_by_id_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit")).click();
	}
	
	// Web Element: logo
	// Important: Use of cssSelector as locator
	// if the class (compound class) contain separate words [cms-icon cms-sprite-loggedout ms-3], 
	// they are actually different class, then
	// we have to close the gap between classes by putting the dot/period
	// cssSelector by class --> htmltag.class name value 
	// remove the space between words in class value, and replace with dot/period
	public void role3_use_of_cssSelector_by_compund_class_name_value () {
		driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).click();
	}
	
	// Above 3 is mostly used, 99% cases of css selector, also very common important interview question
	// below 3 high level, no need to see if you feel they are tough

	// Use of cssSelector as locator
	// Web Element: login Button
	// high level, you can ignore
	// cssSelector by another attribute
	// --> htmltag[id/class/name attribute = 'value of the attribute' ] , in xpath - you use // and @, which is absent here 
	// and you can use any other attribute name except id and class
	public void role4_use_of_cssSelector_by_attribute_and_its_value () {
		driver.findElement(By.cssSelector("button[name='Submit Login']")).click();
		pause(3);
	}
	
	// Web Element: NUR Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag.value of class[id/class/name attribute = 'value of the attribute'] , in xpath you use // and @, which is absent here and you can use attribute except id and class
	// That's why we use title
	public void role5_use_of_cssSelector_by_class_name_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("a.cms-newuser-reg[title='New User Registration']")).click();
		pause(3);
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag#value of ID[attribute name='value'] , in xpath you use // and @, which is absent here and you can use attribute except id and class	
	public void role6_use_of_cssSelector_by_id_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit[title='Login']")).click();
		pause(3);
	}
	
	// Important: Use of tagName as locator
	// tag name: we have to go to Amazon and find "table" tag, which is unique
	
	// in cms portal not a single tag except header
	public void use_of_tagName_as_locator() {
		driver.findElement(By.tagName("header")).click();
	}
	
	// Web Element: logo
	// isDisplayed() method is boolean type, outcome: true or false
	// isDisplayed() is the method used to verify the presence of a web element within the web page.
	// Use of isDisplayed() available in --> image, link, button, text field, check box etc.
	public void use_of_isDisplayed_01() {
		boolean elementDisplayed = driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).isDisplayed();
		System.out.println("Is the Logo displayed? Ans: " + elementDisplayed);
	}
	
	public void use_of_isDisplayed_02() {
		boolean eDisplayed = logoByCssSelector.isDisplayed();
		System.out.println("Is the Logo displayed? Ans: " + eDisplayed);
	}

	// use of isDisplayed method inside our common actions --> elementDisplayed()
	public void use_of_isDisplayed_in_login () {
		elementDisplayed(userId); // newly added
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password); // newly added
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		clickElement(loginButton);
		pause(4000);
	}
	
	// Web Element: login Button
	// isEnabled() is the method used to verify if the web element is enabled or
	// disabled within the web page. isEnabled() is primarily used with buttons.
	// Use of isEnabled(), a boolean type method
	
	// use of isEnabled method inside elementEnabled()
	public void use_of_isEnabled_in_login() {
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); // newly added
		clickElement(loginButton);
		pause(4000);
	}
	
	// Web Element: checkbox
	// Used with radio buttons, dropdowns and checkboxes.
	// use of isSelected() method inside
	
	public void use_of_isSelected_in_login() {
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		elementSelected(termsAndCondition); // newly added
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); 
		clickElement(loginButton);
		pause(4000);
	}
	
	
	// In real time scenario we do below test at the beginning of a page, 
	// but by using common action, not by following below codes
	public void getMethodsOfThePage() {
		String actual = driver.getTitle();
		System.out.println("Title of the Page is: " + actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Title doesn't match up");
		
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentURL);
		String expectedURL= "https://portal.cms.gov/portal/";
		Assert.assertEquals(currentURL, expectedURL, "The driver failed to direct at right URL");
		
		// use of getText() in "login button"
		String actualTextPresntInTheWebElement = loginButton.getText();
		System.out.println("Text Present as: " + actualTextPresntInTheWebElement);
		String expectedTextPresntInTheWebElement = "Login";
		Assert.assertEquals(actualTextPresntInTheWebElement, expectedTextPresntInTheWebElement, "The text present in the WebElement doesn't match");
	}
	
	// This is the first method used during automation framework
	// what is title?
	// what is the url?
	// is logo displayed?
	// here, method coming from common action
	public void newUserRegistrationPageValidation() {
		pause(3000);
		elementDisplayed(logo);
		verifyTitle(driver, " CMS Enterprise Portal");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/");
		elementEnabled(newUserRegistration);
		verifyTextOfTheWebElement(newUserRegistration, "New User Registration");
		clickElement(newUserRegistration);
		pause(5000);
		verifyTitle(driver, "CMS Enterprise Portal - New User Registration");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/newuserregistration");
	}
	
	// getAttribute() will teach later
		
	// use of clear()
	public void use_of_clear_in_login() {
		elementDisplayed(userId);
		inputText(userId, "May 2024");
		elementDisplayed(password);
		inputText(password, "abc@1234213");
		pause(3000);
		clearTextFromTheField(userId); // new here
		pause(3000);
		clearTextFromTheField(password); // new here
		pause(3000);
		inputText(userId, "enthrall_12");		
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		elementSelected(termsAndCondition);
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); 
		verifyTextOfTheWebElement(loginButton, "Login");
		clickElement(loginButton);
		pause(4000);
	}
	
	public void switch_between_window() {
		elementDisplayed(help);
		clickElement(help); // a child window will open
		pause(4000);
		Set<String> allWindowHandles =  driver.getWindowHandles();
		// Extract Parent and child window from all window handles
		String parent = (String)allWindowHandles.toArray()[0];
		String child = (String)allWindowHandles.toArray()[1];
		driver.switchTo().window(child);
		pause(4000);
		verifyTextOfTheWebElement(helpPageHeader, "CMS Enterprise Portal - Help Center");
		pause(4000);
	}
	
	// use of Keys.ENTER, most common then [raw code]
	public void use_of_sendKeys_method_then_click_by_enter_key_of_the_laptop_01 () {
		pause(4000);
		driver.findElement(By.id("cms-login-userId")).sendKeys("May 2024", Keys.ENTER);
		pause(4000);
	}
	
	// use of Keys.ENTER, common method inputTextThenClickEnter() used
	public void use_of_sendKeys_method_then_click_by_enter_key_of_the_laptop_02 () {
		pause(4000);
		elementDisplayed(userId);
		inputTextThenClickEnter(userId, "May 2024 QA");
		pause(4000);
	}
	
	// use of Keys.RETURN , common method used
	public void use_of_sendKeys_method_then_click_by_return_key_of_the_laptop () {
		elementDisplayed(userId);
		inputTextThenClickReturn(userId, "enthrall_12");
		pause(4000);
		elementDisplayed(password);
		inputTextThenClickReturn(password, "OnthrallTest@1234"); // Used here
		pause(4000);
	}
	
	// use of Keys.TAB, common method used
	public void use_of_sendKeys_method_then_click_by_tab_key_of_the_laptop () {
		elementDisplayed(userId);
		inputTextThenClickTab(userId, "enthrall_12");  // the focus will go to next input
		pause(4000);
	}
	
	// use of navigate()
	// mostly interview question, never used in framework or in real time environment
	public void use_of_navigate_method () {
		pause(4000);
		driver.navigate().to("https://www.ebay.com");
		pause(4000);
		driver.navigate().back();
		pause(4000);
		driver.navigate().forward();
		pause(4000);
		driver.navigate().refresh();
		pause(4000);
	}
	
	// Very very important interview question
	public void use_of_mouse_hoverAction_on_ourLocations () {
		pause(4000);
		// below process we don't use in framework
		driver.navigate().to("https://www.mountsinai.org/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 
		// use of normalize-space(text()) as an xpath is new here, please have a look
		WebElement ourLocations = driver.findElement(By.xpath("//a[normalize-space(text()) = 'Our Locations' and @class='hidden-xs dropdown']")); 
		// Actions actions = new Actions(driver);
		actions.moveToElement(ourLocations).build().perform();
		pause(5000);		
	}
	
	// alternate of click()
	// very very  Important interview question + they ask you to write the code in MS word
	// JavaScriptExecutor is an Interface that helps to execute JavaScript through Selenium Webdriver. 
	// so, practice it by paper pen, then in ms word
	// when you see error message "click intercepted" or "ElementNotInteractableException", you use it
	// login button used
	public void alternate_of_click_method() {
		// WebElement loginButton = driver.findElement(By.id("cms-login-submit"));
		// above line, we used it at the beginning, no need to show it here
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", loginButton); // memorize the content
		// arguments[0] means, find the web element of index 0, means first occurrence
		pause(4000);
	}
	
	// interview question
	// how to input text inside a field by JavascriptExecutor, alternate of sendKeys()
	// user id field is used to input text
	public void alternate_of_send_keys_method() {
		pause(4000);
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='enthrall_12'", userId);
		pause(4000);		
	}	
	
	// TODO: Nasir Fail, why?
	// login process by JavascriptExecutor
	// alternative of click(), sendKeys() is used
	public void login_process_by_JavascriptExecutor(){
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		elementDisplayed(userId);		
		js.executeScript("arguments[0].value = 'enthrall_12' ", userId);
		pause(3000);
		elementDisplayed(password);
		js.executeScript("arguments[0].value = 'OnthrallTest@1234' ", password);
		pause(3000);
		elementSelected(termsAndCondition);
		js.executeScript("arguments[0].click()", termsAndCondition);
		pause(3000);
		elementEnabled(loginButton);
		verifyTextOfTheWebElement(loginButton, "Login");
		js.executeScript("arguments[0].click()", loginButton);
		pause(3000);
	}

	// TODO: Fail, why?
	// login process by JavascriptExecutor common method
	public void login_process_by_JavascriptExecutor_common_method(){
		elementDisplayed(userId);	
		inputTextUsingJavascriptExecutor(driver, "arguments[0].value = 'enthrall_12' ", userId);
		pause(3000);
		elementDisplayed(password);
		inputTextUsingJavascriptExecutor(driver, "arguments[0].value = 'OnthrallTest@1234' ", password);
		pause(3000);
		elementSelected(termsAndCondition);
		clickUsingJavascriptExecutor(driver, termsAndCondition);
		pause(3000);
		elementEnabled(loginButton);
		verifyTextOfTheWebElement(loginButton, "Login");
		clickUsingJavascriptExecutor(driver, loginButton);
		pause(3000);
	}
	
	// it will fail, because selenium can't handle hidden element
	public void how_to_handle_hidden_element_by_regular_selenium_method() {
		pause(3);
		driver.navigate().to("https://www.letskodeit.com/practice");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		pause(3000);
		// identify the 'Hide' element and click on it [line 548]
		// The search field will be disappeared, but we can pass value on it, as we got the info before
		driver.findElement(By.id("hide-textbox")).click();
		pause(4000);
		// identify element and set/input text or value (line 540) by selenium
		driver.findElement(By.xpath("//input[@id='displayed-text']")).sendKeys("Hello");
		// it will fail by below error message
		// org.openqa.selenium.ElementNotInteractableException: element not interactable
		// whenever you find element not interactable in console, that is for sure a hidden element	
	}
	
	// it will pass
	public void how_to_handle_hidden_element_by_javascriptExecutor() {
		pause(3000);
		driver.navigate().to("https://www.letskodeit.com/practice");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		pause(3000);
		// identify the 'Hide' element and click on it [line 548]
		// The search field will be disappeared, but we can pass value on it, as we got the info before
		// we can click by regular selenium method like 520
		WebElement hide = driver.findElement(By.id("hide-textbox"));
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", hide); // clicking on hide button is not necessary by JS
		pause(3000);
		// identify element and set/input text or value (line 540) by selenium
		// identify element and set/input text or value by JavascriptExecutor
		WebElement searchField = driver.findElement(By.xpath("//input[@id='displayed-text']"));
		js.executeScript("arguments[0].value='Hello'", searchField);
		pause(3000);
		
		// save below code for getTitle, getTest, Refresh by JavscriptExecutor
		// You can really know what was the text written by the JavascriptExecutor		
		// Not important and not related below
		// Extra code, alternative of  getText() 
		// Extra not related to hidden elements and not important
		// To find out what you send as text, not necessary for this scenario
		// Just save the below code for future reference
		String s = (String) js.executeScript("return document.getElementById('displayed-text').value");
		System.out.print("Value entered in hidden field: " + s + "\n");
		
		// Not important
		// How to get title of the page by JavaScript
		// How to read a JavaScript variable in Selenium WebDriver?
		// How to getTitle by Javascript, 
		String sText = js.executeScript("return document.title;").toString(); // fetching page title by javascript
		System.out.println("The title of the Page is: "+sText);	
		
		// Not related with this test
		// How to refresh by Javascript, 
		js.executeScript("history.go(0)"); // To do refresh by Javascript
		
	}
	
	// very very important
	// when the web element always failed in test, use explicitly wait, 
	// this web site doesn't have complex elements
	// "login" web element 
	// using visibilityOfElementLocated() method , Number one
	// This is a very common scenario in industry to use explicitly wait
	public void use_of_explicitly_wait_01() {
		pause(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id= 'cms-login-submit']"))).click();
		pause(3000);
	}
	
	// New User Registration webElement
	// using elementToBeClickable() method, number two
	public void use_of_explicitly_wait_02(){
		pause(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'New')]"))).click();
		pause(4000);
	}
	
	// userId webElement
	// using visibilityOf() method, number three
	public void use_of_explicitly_wait_03(){
		pause(3000);
		wait.until(ExpectedConditions.visibilityOf(userId)).isDisplayed(); // here userId is webElement type
		inputText(userId, "enthrall_12");
		pause(3000);
	}
	
	// Try to remember this above 3 conditions-name for interview, most time they asked it
	
	// Not important, you can use it to practice
	// "unlock" web element 
	// using presenceOfElementLocated() method
	public void use_of_explicitly_wait_04(){
		pause(3);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Password')]"))).click();
		pause(4);
	}
	
	// Not important, you can use it to practice
	// "Login Button" web element
	// using textToBePresentInElement() method
	// this is not a clickable method, just to recognize the web element
	public void use_of_explicitly_wait_05(){
		pause(3);
		boolean outcome = wait.until(ExpectedConditions.textToBePresentInElement(loginButton, "Login"));
		System.out.println(outcome);
		pause(4);
	}
	
	// New User Registration webElement
	// using commonWaits fir elementToBeClickable() method
	public void use_of_explicitly_wait_from_common_waits(){
		pause(4000);
		waitUntilConditionThenClick(driver, newUserRegistration);
		pause(3000);
	}
	
	// Here We used User ID field
	// getAttribute() actually give the value of the Attribute, not common
	// raw use, in next method we will use from common action
	public void use_of_getAttribute_method () {
		elementSelected(userId);
		pause(3000);
		String ml = userId.getAttribute("maxlength");
		System.out.println("The value of the maxlength attribute is: " + ml);
		String ph = userId.getAttribute("placeholder");
		System.out.println("The value of the placeholder attribute is: " + ph);
	}
	
	public void use_of_getAttribute_by_common_method () {
		elementSelected(userId);
		getAttributeValue(userId, Attribute.MAX_LENGTH);
		pause(3000);
		getAttributeValue(userId, Attribute.PLACEHOLDER);
		pause(3000);
	}
	
	// important interview question
	// 1st way: Scroll by Actions class
	// scroll bottom and then top
	public void use_of_scroll_down_and_up_by_actions_class () {
		pause(3000);
		// for Scroll Down using Actions class, to go at the bottom of the page
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		pause(3000);
		// for Scroll Up using Actions class at the top of the page
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		pause(3000);
		// instead of END and HOME, we can use Keys.UP or Keys.Down
		// But it doesn't change much but the test case passes, we will not use them		
	}
	
	// not important, just to know
	public void use_of_scroll_down_and_scroll_up_by_robot_class () throws InterruptedException, AWTException {
		// For some reason, they are not going completely Up or Down
		Robot robot = new Robot();
		// Scroll Down using Robot class
		robot.keyPress(KeyEvent.VK_PAGE_DOWN); // Constant for the PAGE_DOWN virtual key.
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		pause(3000);
		// Scroll Up using Robot class
        robot.keyPress(KeyEvent.VK_PAGE_UP); // Constant for the PAGE_UP virtual key. 
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
        pause(3000);		
	}
	
	// important interview question
	// 2nd way: Scroll by javascriptExecutor
	// scroll in a certain position (not at the bottom or up)
	public void use_of_scroll_down_and_up_In_A_Certain_Pixel_by_javascriptExecutor () {
		pause(3000);
		// This will scroll down the page by 1000 pixel vertically
		// here 0 is x axis [horizontal], 1000 y axis [vertical]
		// you choose your pixel accordingly to reach to that web element
		js.executeScript("window.scrollBy(0, 1000)", "");
		// You can change the value to any pixel, and put your own to see the web element you wanna test
		pause(3000);
		js.executeScript("window.scrollBy(0, -1000)", ""); // scroll up till 1000px, but not necessary based on your test
		// minus when it goes opposite of down
		pause(3000);
	}
		
	
	// scroll Into View The Element
	// This is very very important, standard interview question
	// This is better to use
	public void scroll_into_view_the_element() {
		WebElement enterprisePortal = driver.findElement(By.xpath("//h1[text()='Enterprise Portal']"));
		WebElement learnMore = driver.findElement(By.xpath("//a[contains(text(), 'Lear')]"));
		js.executeScript("arguments[0].scrollIntoView(true)", enterprisePortal);
		pause(3000);
		js.executeScript("arguments[0].click()", learnMore);	
		pause(3000);
	}
	
	public void scroll_into_view_the_element_by_commonActions() {
		WebElement enterprisePortal = driver.findElement(By.xpath("//h1[text()='Enterprise Portal']"));
		WebElement learnMore = driver.findElement(By.xpath("//a[contains(text(), 'Lear')]"));
		scrollIntoViewTheElementUsingJavascriptExecutor(driver, learnMore);
		pause(3000);
		clickUsingJavascriptExecutor(driver, enterprisePortal);
		pause(3000);
	}
	
	// very very important for interview
	public void web_based_alert_accept () {
		pause(4);
		driver.get("http://softwaretestingplace.blogspot.com/2017/03/javascript-alert-test-page.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[contains(text(), 'Try it')]")).click();
		pause(4);
		Alert alert = driver.switchTo().alert();
		pause(3000);
		System.out.println("The text present in the alert is: " + alert.getText());
		alert.accept(); // will click on OK button
		pause(3000);
		// line 768, not part of the accept function, 
		// we just added to know about, the text is present in the alert or not,
		// also if you use it after 769, it might not retrieve the text		

	}
	
	// very very important for interview
	public void web_based_alert_dismiss () {
		pause(4);
		driver.get("http://softwaretestingplace.blogspot.com/2017/03/javascript-alert-test-page.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[contains(text(), 'Try it')]")).click();
		pause(4);
		Alert alert = driver.switchTo().alert();
		pause(3000);
		System.out.println("The text present in the alert is: " + alert.getText());
		alert.dismiss(); // will click on Cancel button
		pause(3000);
	}
	
	// Only important for interview
	public void authentication_pop_up (){
		pause(3000);	
		String userName = "admin";
		String password = "admin";
		// original one is: "https://the-internet.herokuapp.com/basic_auth";
		// Updated one is: "https://admin:admin@the-internet.herokuapp.com/basic_auth";
		String url = "https://" + userName + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		pause(3000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(3000);
		
		// The below part is not part of this test
		// identify and get text after authentication of pop up
		String t = driver.findElement(By.tagName("p")).getText(); // we use tag name as a locator in our course
		System.out.println("The Text is: " + t);
		Assert.assertEquals(t, "Congratulations! You must have the proper credentials.");
	}
	
	// only important for interview
	public void use_of_right_click_action() {
		pause(3000);
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement rcButton = driver.findElement(By.xpath("//span[contains(text(), 'right click me')]"));
		actions.moveToElement(rcButton).contextClick().build().perform(); // right click action
		pause(3000);
		
		// From Line 827, not part of testing, just completed the scenario
		// Just keep below code, Can't find the web element for Edit at present, the line 775 is from my collection.
		// Below 2 is not relevant to right click, just doing some extra, which we know already
		// Next: I want to click on Edit link on the displayed menu options
		WebElement edit = driver.findElement(By.xpath("//span[text()='Edit']"));
		pause(3000);
		edit.click(); // a regular click, not a right click
		pause(3000);
		// Switch to the alert box and click on OK button
		Alert alert = driver.switchTo().alert();
		System.out.println("\nAlert Text: " + alert.getText());
		alert.accept();
		pause(3000);	
		
	}
	
	// only important for interview
	public void use_of_double_click_action() {
		pause(3000);
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(3000);
		WebElement dcButton = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		actions.doubleClick(dcButton).build().perform();
		pause(3000);
		// Not part of the double click action
		// Switch to the alert box and click on OK button
		Alert alert = driver.switchTo().alert();
		System.out.println("\nAlert Text: " + alert.getText());
		alert.accept();
		pause(3000);		
	}
	
	// not important
	public void use_of_drag_and_drop_action () {
		pause(3000);
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// WebElement which needs to drag (Bank Button)
		WebElement sourceLocator = driver.findElement(By.xpath("//a[contains(text(), 'BA')]")); 
		// Element where need to be dropped.(In 'Account' field of debit side)
		WebElement targetLocator = driver.findElement(By.xpath("//ol[contains(@id, 'b')]")); 
		// We Use Actions class for drag and drop.
		actions.dragAndDrop(sourceLocator, targetLocator).build().perform();
		pause(5000);		
		
	}
	
	// not important
	public void use_of_slider_action () {
		pause(3000);	
		driver.get("https://demoqa.com/slider/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Retrieve WebElemnt 'slider' to perform mouse hover
		// This is the field where volume is increased
		WebElement slider = driver.findElement(By.cssSelector("input.range-slider.range-slider--primary"));
		// Move mouse to x offset 50 i.e. in horizontal direction
		pause(3000);
		// to test the slider is working or not
		// dragAndDropBy (element, int xoffset, int yoffset)
		actions.dragAndDropBy(slider, 50, 0).build().perform(); 
		// learn from here, 50 is in pixel which might not match with real volume change, real volume 60
		pause(5000);
		// slider.click();
		System.out.println("Moved slider in horizontal directions");
	}
	
	// How to read the content of a Table 
	public void read_table () {
		pause(5000);	
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(5000);	
		// Scrolled to the end of page
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		pause(5000);	
		// WebElement table = driver.findElement(By.tagName("table")); // tag as a locator 
		WebElement table = driver.findElement(By.cssSelector("table.navFooterMoreOnAmazon")); 
		System.out.println(table.getText());
		pause(5000);		
	}
	
	// How to read the row of a Table 
	public void read_any_row_of_the_table () {
		pause(5000);	
		driver.get("https://www.amazon.com");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(5000);	
		actions = new Actions(driver); 
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		pause(5000);	
		WebElement row = driver.findElement(By.cssSelector("table.navFooterMoreOnAmazon tr:nth-child(1)")); 
		System.out.println(row.getText());
		pause(5000);
	}
	
	// How to read any cell of a row of the Table 
	public void read_any_cell_of_a_row_of_the_table () {
		pause(5000);	
		driver.get("https://www.amazon.com");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(5000);	
		//actions = new Actions(driver); 
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		pause(5000);
		WebElement cell = driver.findElement(By.cssSelector("table.navFooterMoreOnAmazon tr:nth-child(1) td:nth-child(7)"));
		// WebElement cell = driver.findElement(By.tagName("table tr:nth-child(1) td:nth-child(7)")); // Not working
		System.out.println(cell.getText());
		pause(3000);
	}
	
	// regarding TestNG 
	// use of groups
	public void getMethodsOfThePage03() {
		String actual = driver.getTitle();
		System.out.println("Title name: "+ actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match ....... ");		
	}
	
	public void getMethodsOfThePage04() {
		String actual = driver.getTitle();
		System.out.println("Title name: "+ actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match ....... ");		
	}
	
	public void getMethodsOfThePage05() {
		String actual = driver.getTitle();
		System.out.println("Title name: "+ actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match ....... ");		
	}
	
	public void use_of_expectedExceptions01 () {
		System.out.println("We can verify whether a code throws the expected exception or not. Here it will fail");
		int i = 1/0;	
	}
	
	public void use_of_expectedExceptions02 () {
		System.out.println("We can verify whether a code throws the expected exception or not. Here it will Pass");
		int i = 1/0;	
	}
	
	public void use_of_expectedExceptions03 () {
		driver.findElement(By.id("xxs-login-submit")).click(); // cms login button
		pause(4);
	}
	
	// for test dependOnMethod()
	public void new_user_registration_button_enabled(){
		WebElement nur = driver.findElement(By.xpath("//a[contains(text(), 'New User Registration')]"));
		boolean buttonEnabled = nur.isEnabled();			
		System.out.println("Is the Button Enabled? Ans: "+ buttonEnabled);
		Assert.assertTrue(true, "The New User Registration Button is disable .....");	
	}
	
	public void newUserRegistrationButtonClick() {
		driver.findElement(By.xpath("//a[contains(text(), 'New User Registration')]")).click();
		pause(3000);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	}
	
	// This test to explain the next one, it is not tested
	public void nonSkipHomePageTitleTest() {
		String expected = "CMS Enterprise Portal";
		String actual = driver.getTitle();
		System.out.println("home page title is: " + actual);
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match...");	
		System.out.println("No need to skip the test");
	}	
	
	// how to handle Exception: try, catch, throw, throws, finally
	// below examples of where 'throw' is used
	// Que: How to skip a test? Ans: by "throw new SkipException()" method
	public void skipHomePageTitle01() {
		String expected = "CMS Enterprise Portal";
		if(expected.equals(driver.getTitle())) {
			throw new SkipException("Skipping -- as the title matches as expected");
			// if above condition is true, then no more below execution
		} else {
			System.out.println("Home Page Title doesn't match...");
		}
		System.out.println("I am out of the if else condition");
	}
	
	public void skipHomePageTitle02() {
		String expected = "      CMS Enterprise Portal"; // title will not match
		if(expected.equals(driver.getTitle())) {
			throw new SkipException("Skipping -- as the title matches as expected");
		} else {
			System.out.println("Home Page Title doesn't match...");
		}
		System.out.println("I am out of the if else condition");
	}
	
	public void getMethodsOfThePage06() {
		String actual = driver.getTitle();
		System.out.println("Title name: "+ actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match ....... ");		
	}
	
	public void getMethodsOfThePage07() {
		String actual = driver.getTitle();
		System.out.println("Title name: "+ actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Home Page Title doesn't match ....... ");	
		System.out.println("Thread: "+ Thread.currentThread().getName()); // to know which thread is running
	}
	






	
	

}
