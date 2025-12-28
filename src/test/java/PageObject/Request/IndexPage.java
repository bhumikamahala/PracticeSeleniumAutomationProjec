package PageObject.Request;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// using pageFactory approach to implement POM
public class IndexPage {
    // create object of webdriver
    WebDriver ldriver;

    // create constructor and initialise the webdriver
    public IndexPage(WebDriver rdriver){
        ldriver = rdriver ;
        PageFactory.initElements(rdriver,this);
    }

    // identify web elements with which we interact
     @FindBy(linkText = "Sign in") // identify webelement
    WebElement signIn ;   // store the webelement after identify

    // identify the action performed on this web element
    public void ClickOnSignIn(){
        signIn.click();
    }
}
