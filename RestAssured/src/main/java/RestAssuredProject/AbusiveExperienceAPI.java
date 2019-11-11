package RestAssuredProject;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class AbusiveExperienceAPI {
	
	public void getViolatingSites()  {
		
		RestAssured.baseURI="https://abusiveexperiencereport.googleapis.com";
		
		Response res = given().
		queryParam("key","AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/v1/violatingSites").
		
		then().assertThat().statusCode(200).extract().response();
		
	}
	
	public void getAbusiveStatus() {
		
		RestAssured.baseURI="https://abusiveexperiencereport.googleapis.com";
		
		Response res = given().
			queryParam("key", "AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/v1/sites/http%3A%2F%2Fwww.google.com%2F.").
		
		then().assertThat().statusCode(200).extract().response();
	}
	
	
}
