package com.tech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.model.UserDetails;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItemInArray;


public class UserDetailsAPITest {

    @BeforeClass
    public void setUrl(){
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.basePath = "/api";
    }

    /**
     * Validate full JSON
     */
    @Test
    public void verifyGetUserJSON(){
        UserDetails expectedUserDetails = UserDetails.builder()
                .userId("123")
                .firstName("Ramesh")
                .lastName("Katiyar")
                .accountNumbers(new int[]{123,456,789})
                .build();

        UserDetails actualUserDetails = RestAssured
                .get("/user")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(UserDetails.class);

        Assert.assertEquals(actualUserDetails, expectedUserDetails);
    }

    /**
     * Test the Path Variable
     * "/api/user/333"
     */
    @Test
    public void verifyGetUserById(){
        String userId = "333";

        Response response = RestAssured
                .given()
                .pathParam("id", userId) //Get value by Id
                .get("/user/{id}");

        Assert.assertEquals(userId, response.getBody().path("userId"));
    }

    /**
     * Test the Array using Hamcrest
     */
    @Test
    public void verifyAccountsArray(){
        String expectedAccount = "456";

        RestAssured
                .get("/user")
                .then()
                .body("accountNumbers",hasItemInArray(expectedAccount));
    }

    /**
     * Verify Post Request
     */
    @Test
    public void verifyAddUser() {
        String jsonBody = objectToJson(getUserDetails());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Verify Put Request
     */
    @Test
    public void verifyUpdateUser(){
        String jsonBody = objectToJson(getUserDetails());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", "222")
                .body(jsonBody)
                .when()
                .put("/user/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Verify Delete Request
     */
    @Test
    public void verifyDeleteUser(){
        RestAssured
                .given()
                .pathParam("id", "111")
                .when()
                .delete("/user/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Set Cookies
     */
    @Test
    public void setCookieVerification(){
        String session = "abc";
        RestAssured
                .given()
                .header("Cookie", session)
                .contentType(ContentType.JSON)
                .post("/user");
    }

    /**
     * Use of JSON PATH
     * It's nothing much but a way of extracting values from JSON.
     */
    @Test
    public void checkJSONPathFeature(){
        String jsonString = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/user")
                .then()
                .extract()
                .asString();


        JsonPath jsonPath = new JsonPath(jsonString);

        Assert.assertEquals(jsonPath.getString("firstName"), "Ramesh");
    }

    private String objectToJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println("Error.");
        }
        return null;
    }

    private UserDetails getUserDetails(){
        return UserDetails.builder()
                .userId("444")
                .firstName("Jhone")
                .lastName("K")
                .build();
    }
}
