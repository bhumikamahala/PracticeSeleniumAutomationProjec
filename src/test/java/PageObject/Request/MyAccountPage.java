package PageObject.Request;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    // create webDriver Object
    WebDriver ldriver ;

    public MyAccountPage(WebDriver rdriver){
        ldriver = rdriver ;
        PageFactory.initElements(rdriver,this);
    }

    // identify the webelements which we have to interact
    @FindBy(id = "email_create")
    WebElement createEmailId;

    @FindBy(name = "SubmitCreate")
    WebElement submitCreate;

    @FindBy(id="email")
    WebElement registeredEmail;

    @FindBy(id="passwd")
    WebElement registeredPassword;

    @FindBy(id="SubmitLogin")
    WebElement signInButton;

    // perform click action
    public void enterEmailText(String emailAdd){
        createEmailId.sendKeys(emailAdd);
    }


    public void clickOnAccountCreate(){
        submitCreate.click();
    }

    public void enterRegisteredEmail(String email){
        registeredEmail.sendKeys(email);


}}
