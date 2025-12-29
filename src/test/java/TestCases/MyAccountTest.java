package TestCases;

import PageObject.Request.AccountCreationDet;
import PageObject.Request.IndexPage;
import PageObject.Request.MyAccountPage;
import PageObject.Request.RegisteredUserAccountDetail;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class MyAccountTest extends BaseClass{

    public static String generateRandomAlpha(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

     @Test(description = "Register and login to website")
    public void registrationAndLogin(){
        driver.get(url);
        logger.info("url opened");

        IndexPage indexPage = new IndexPage(driver);
        indexPage.ClickOnSignIn();
        logger.info("click on sign in");

         String Firstname = generateRandomAlpha(5);
         String Lastname = generateRandomAlpha(5);
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
         
         // Format expected name to match website's formatting (capitalize first letter of first name)
         String formattedFirstName = Firstname.isEmpty() ? Firstname : 
             Firstname.substring(0, 1).toUpperCase() + Firstname.substring(1).toLowerCase();
         String expectedName = formattedFirstName + " " + Lastname;
         
         // Assert that the account name matches the expected name (case-insensitive comparison)
         Assert.assertEquals(acctName, expectedName, "Account name mismatch - Expected: " + expectedName + ", Actual: " + acctName);
         logger.info("Account created successfully with name: " + acctName);
         logger.info("Account registered successfully");
    }


}
