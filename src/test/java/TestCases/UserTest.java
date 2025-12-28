package TestCases;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

   @Test(description = "verify user details from API")
    public void getUserDetails(){
       RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
       RequestSpecification httpRequest = RestAssured.given();
       String response = httpRequest.get("/users/1").asString();
       System.out.println("User Details: " + response);
       JsonPath jsonPath = new JsonPath(response);
       String userName = jsonPath.getString("username");
       String city = jsonPath.getString("address.city");
//       String city = address.get("city").getAsString();
   Assert.assertEquals(userName, "Bret", "Username does not match");
//   Assert.assertNotNull(address);
    Assert.assertEquals(city, "Gwenborough", "City does not match");
   }


}
