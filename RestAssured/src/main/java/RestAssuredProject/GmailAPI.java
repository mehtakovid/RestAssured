package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GmailAPI {
	
	public void getUserProfile(String email) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().
		queryParam("key","AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/gmail/v1/users/"+email+"/profile").
		
		then().assertThat().statusCode(200).extract().response();
		
		System.out.println("User profile response : " +res.asString());
	}

}
