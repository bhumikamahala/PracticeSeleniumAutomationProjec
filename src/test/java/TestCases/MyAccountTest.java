package TestCases;

import PageObject.Request.AccountCreationDet;
import PageObject.Request.IndexPage;
import PageObject.Request.MyAccountPage;
import PageObject.Request.RegisteredUserAccountDetail;
import org.testng.annotations.Test;

public class MyAccountTest extends BaseClass{
     @Test(description = "Register and login to website")
    public void registrationAndLogin(){
        driver.get(url);
        logger.info("url opened");

        IndexPage indexPage = new IndexPage(driver);
        indexPage.ClickOnSignIn();
        logger.info("click on sign in");

         String Firstname = java.util.UUID.randomUUID().toString().substring(0,5).replaceAll("[^A-Za-z]", "");
         String Lastname = java.util.UUID.randomUUID().toString().substring(0,5).replaceAll("[^A-Za-z]", "");
         String email = Firstname + Lastname + "@gmail.com";
         String password = "Password123";
        MyAccountPage myAccountPage = new MyAccountPage(driver);
         myAccountPage.enterEmailText(email);
        myAccountPage.clickOnAccountCreate();
        logger.info("click on submit account create");

         AccountCreationDet accountCreationDet = new AccountCreationDet(driver);
         accountCreationDet.selectTitleMr();
            accountCreationDet.InputFirstName(Firstname);
            accountCreationDet.InputLastName(Lastname);
         accountCreationDet.InputPassword(password);
//         accountCreationDet.InputAddressLine1("123 Main St");
//         accountCreationDet.InputCity("New York");
//         accountCreationDet.InputAddressAlias("My Address");
//         accountCreationDet.selectState("New York");
//         accountCreationDet.InputPostalCode("10001");
//         accountCreationDet.selectCountry("United States");
//         accountCreationDet.InputMobilePhone("1234567890");
         accountCreationDet.clickOnRegisterButton();
         // validate that account is created successfully
         RegisteredUserAccountDetail registeredUserAccountDetail = new RegisteredUserAccountDetail(driver);
         String acctName = registeredUserAccountDetail.getAccountName();
            if(acctName.equals("John Doe")){
               logger.info("account created successfully - test passed");
            } else {
                logger.info("account creation failed - test failed");
            }
         logger.info("account registered successfully");
    }


}
