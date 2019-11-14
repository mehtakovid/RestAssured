package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GmailAPI {

	
	public void getUserProfile(String email,String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().
		auth().preemptive().oauth2(AccessToken).
		header("Content-Type","application/json").
		queryParam("key","AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/gmail/v1/users/"+email+"/profile").
		
		then().assertThat().statusCode(200).extract().response();
		
		System.out.println("User profile response : " +res.asString());
	}

}
