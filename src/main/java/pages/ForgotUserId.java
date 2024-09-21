package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static common.CommonActions.*;

public class ForgotUserId {
	public WebDriver driver;
	public Select select;

	public ForgotUserId(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "cms-forgot-userid")
	WebElement forgotUserId;
	
	@FindBy(xpath = "//h1[contains(text(), 'Forgot User ID')]")
	WebElement forgotUserIdHeader;
	
	@FindBy(xpath = "//p[contains(text(), 'Please e')]")
	WebElement forgotUserIdSubHeader;
		
	@FindBy(xpath = "//p[contains(@id, 'cms-allfields-req')]")
	WebElement allFieldsRequired;
	
	@FindBy(xpath = "//input[contains(@id, 'cms-forgotid-fi')]")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@name='cms-forgotid-lastName']")
	WebElement lastName;
	
	@FindBy(xpath = "//select[@name='cms-forgotid-birthDate']")
	WebElement birthDate;
	
	@FindBy(xpath = "//select[@id='cms-forgotid-birthYear']")
	WebElement birthYear;
	
	@FindBy(xpath = "//select[contains(@name, 'cms-forgotid-birthM')]")
	WebElement birthMonth;
	
	@FindBy(xpath = "//select[@name='cms-forgotid-birthMonth']//option")
	List<WebElement> birthMonthList;
	
	@FindBy(xpath = "//input[@id='cms-forgotid-forgotEmail']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='cms-forgotid-usZipCode']")
	WebElement zipCode;
	
	@FindBy(xpath = "//button[contains(text(), 'Su')]")
	WebElement submitButton;
	
	public void navigateToForgotUserIdPage() {
		pause(4000);
		elementDisplayed(forgotUserId);
		clickElement(forgotUserId);
		pause(4000);
		verifyTitle(driver, "CMS Enterprise Portal - Forgot User ID");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/forgotuserid");
	}
	
	// drop down, by all 3 methods
	// drop down is a commonly asked interview question
		
	// birth month
	// use method --> selectByValue()
	public void use_of_dropdown_with_selectByValue_method() {
		select = new Select(birthMonth);
		select.selectByValue("08"); // for choosing August
		pause(4000);
	}
	
	// birth date
	// use method --> selectByIndex()
	// This method is not suggested, or not used much
	// Because, adding a new web element or deleting a new one can change the index number	
	public void use_of_dropdown_with_selectByIndex_method() {
		select = new Select(birthDate);
		select.selectByIndex(18);
		pause(4000);
	}
	
	// birth year
	// drop down is a commonly asked interview question
	// Most commonly use method --> selectByVisibleText()
	public void use_of_dropdown_selectByVisibleText_method() {
		select = new Select(birthYear);
		select.selectByVisibleText("1998");
	}
	
	public void verifyForgotUserId() {
		validationOfHeader(forgotUserIdHeader, "Forgot User ID");
		validationOfSubHeader(forgotUserIdSubHeader, "Please enter the following information to retrieve your User ID.");
		validationOfOtherHeader(allFieldsRequired, "All fields are required.");
		inputText(firstName, "Mohammad");
		pause(4000);
		inputText(lastName, "Sharkar");
		selectElelementFromDropdownOnebyOne(birthMonth, birthMonthList);
		selectDropdown(birthMonth, "June");
		pause(4000);
		selectDropdown(birthDate, "8");
		pause(4000);
		selectDropdown(birthYear, "1988");
		pause(4000);
		inputText(email, "tofael483@gmail.com");
		pause(4000);
		inputText(zipCode, "10019");
		pause(4000);
		elementEnabled(submitButton);
		verifyTextOfTheWebElement(submitButton, "Submit");
		clickElement(submitButton);
		pause(4000);
	}
	
	
	

}
