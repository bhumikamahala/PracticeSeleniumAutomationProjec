package TestCases;

import PageObject.AccountCreationDet;
import PageObject.IndexPage;
import PageObject.MyAccountPage;
import PageObject.RegisteredUserAccountDetail;
import org.testng.annotations.Test;

public class MyAccountTest extends BaseClass{
     @Test(description = "Register and login to website")
    public void registrationAndLogin(){
        driver.get(url);
        logger.info("url opened");

        IndexPage indexPage = new IndexPage(driver);
        indexPage.ClickOnSignIn();
        logger.info("click on sign in");

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.enterEmailText("csabcd1234@gmail.com");
        myAccountPage.clickOnAccountCreate();
        logger.info("click on submit account create");

         AccountCreationDet accountCreationDet = new AccountCreationDet(driver);
         accountCreationDet.selectTitleMr();
            accountCreationDet.InputFirstName("John");
            accountCreationDet.InputLastName("Doe");
         accountCreationDet.InputPassword("Password123");
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
