package PageObject.Request;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisteredUserAccountDetail {

    WebDriver ldriver;
    public RegisteredUserAccountDetail(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
    }

     @FindBy(xpath = "//a[@title='View my customer account']")
     WebElement accountName;

    public String getAccountName(){
        return accountName.getText();}
}
