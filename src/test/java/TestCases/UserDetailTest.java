package TestCases;
import PageObject.Response.UserDetailResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserDetailTest {

    @Test
    public void sampleTest(){
        RequestSpecBuilder reqspec = new RequestSpecBuilder();
        reqspec.setBaseUri("https://jsonplaceholder.typicode.com");

        RequestSpecification requestSpec = reqspec.build();

        Response response =
                given()
                        .spec(requestSpec)
                        .when()
                        .get("/posts")
                        .then()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,"Status code is not matching");
        response.prettyPrint();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.jsonPath().getString("id[0]"),"1","Id is not matching");
       Assert.assertEquals(response.jsonPath().getString("title[0]"),"sunt aut facere repellat provident occaecati excepturi optio reprehenderit","Title is not matching");
       Assert.assertEquals(response.getHeader("content-type"),"application/json; charset=utf-8","Content type is not matching");
      // Assert.assertTrue(response.getTime() <9000 ,"Response time is more than 2000 ms");
        //The method body(Matcher) is part of ValidatableResponse, not Response.
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserDetails.json"));
        List<Integer> ids = response.jsonPath().getList("id");
        for (Integer id : ids) {
            Assert.assertNotNull(id);
        }
        // Deserialize into POJO into single object
//        UserDetailResponse userDetail = response.as(UserDetailResponse.class);
//
//        // Validate fields
//        Assert.assertEquals(userDetail.getId(), 1, "Id is not matching");
//        Assert.assertEquals(userDetail.getUserId(), 1, "UserId is not matching");
        // Deserialize into List
        List<UserDetailResponse> userDetails =
                Arrays.asList(response.as(UserDetailResponse[].class));

          // Access first element
        UserDetailResponse firstPost = userDetails.get(0);

        // Validate
        Assert.assertEquals(firstPost.getId(), 1, "Id is not matching");
    }

    @Test
    public void sampleTestUser(){
        RequestSpecBuilder reqspec = new RequestSpecBuilder();
        reqspec.setBaseUri("https://jsonplaceholder.typicode.com");
        RequestSpecification requestSpecification = reqspec.build();
        Response response = given().spec(requestSpecification)
                .when().get("/users/1")
                .then().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,"Status code is not matching");
        response.prettyPrint();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.jsonPath().getString("id"),"1","Id is mismatch");
       Assert.assertEquals(response.jsonPath().getString("address.street"),"Kulas Light","Street is mismatch");
       Assert.assertEquals(response.jsonPath().getString("address.geo.lat"),"-37.3159","Latitude is mismatch");
    }

    @Test
    public void samplePostTest(){
        RequestSpecBuilder reqspec = new RequestSpecBuilder();
        reqspec.setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-type","application/json; charset=UTF-8");
        RequestSpecification requestSpecification = reqspec.build();
        String requestBody = """
                {
                  "title": "foo",
                  "body": "bar",
                  "userId": 1
                }
                """;
        String JsonBodyRequest = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}";
        Response response = given().spec(requestSpecification)
                .body(JsonBodyRequest)
                .when()
                .post("/posts")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED,"Status code is not matching");
        response.prettyPrint();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.jsonPath().getString("title"),"foo","Title is mismatch");
       Assert.assertEquals(response.jsonPath().getString("body"),"bar","Body is mismatch");
       Assert.assertEquals(response.jsonPath().getString("userId"),"1","UserId is mismatch");
    }
}
