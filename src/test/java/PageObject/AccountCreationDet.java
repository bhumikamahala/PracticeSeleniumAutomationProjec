package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationDet {


    // create webDriver Object
    WebDriver ldriver;

    public AccountCreationDet(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    // identify the webelements which we have to interact
    @FindBy(id = "uniform-id_gender1")
    WebElement titleMr;

    @FindBy(id = "id_gender2")
    WebElement titleMrs;

    @FindBy(id = "customer_firstname")
    WebElement firstName;

    @FindBy(id = "customer_lastname")
    WebElement lastName;

    @FindBy(id = "passwd")
    WebElement password;

//    @FindBy(id = "firstname")
//    WebElement addressFirstName;
//
//    @FindBy(id = "lastname")
//    WebElement addressLastName;
//
//    @FindBy(id = "address1")
//    WebElement addressLine1;
//
//    @FindBy(id = "city")
//    WebElement city;
//
//    @FindBy(id = "uniform-id_state")
//    WebElement state;
//
//    @FindBy(id = "postcode")
//    WebElement postalCode;
//
//    @FindBy(id = "id_country")
//    WebElement country;

//    @FindBy(id = "phone_mobile")
//    WebElement mobilePhone;
//
//    @FindBy(id = "alias")
//    WebElement addressAlias;

    @FindBy(id="submitAccount")
    WebElement registerButton;

    // perform the action of selected webElments
    public void selectTitleMr() {
        titleMr.click();
    }

    public void InputFirstName(String fname) {
        firstName.sendKeys(fname);
    }

    public void InputLastName(String lname) {
        lastName.sendKeys(lname);
    }

    public void InputPassword(String pwd) {
        password.sendKeys(pwd);
    }

//    public void InputAddressFirstName(String addrFname) {
//        addressFirstName.sendKeys(addrFname);
//    }
//
//    public void InputAddressLastName(String addrLname) {
//        addressLastName.sendKeys(addrLname);
//    }
//
//    public void InputAddressLine1(String addrLine1) {
//        addressLine1.sendKeys(addrLine1);
//    }
//
//    public void InputCity(String cityName) {
//        city.sendKeys(cityName);
//    }
//
//
//    public void selectState(String stateName) {
//        Select select = new Select(state);
//        select.selectByVisibleText(stateName);
//    }
//
//    public void InputPostalCode(String pcode) {
//        postalCode.sendKeys(pcode);
//    }
//
//    public void selectCountry(String countryName) {
//        country.sendKeys(countryName);
//    }
//
//
//    public void InputMobilePhone(String mobPhone) {
//        mobilePhone.sendKeys(mobPhone);
//    }
//
//    public void InputAddressAlias(String addrAlias) {
//        addressAlias.sendKeys(addrAlias);
//    }

    public void clickOnRegisterButton() {
        registerButton.click();
    }

}

