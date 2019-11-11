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
		
		System.out.println("Violiting Sites resposne is : " +res.asString());
		
	}
	
	public void getAbusiveStatus(String website) {
		
		RestAssured.baseURI="https://abusiveexperiencereport.googleapis.com";
		
		Response res = given().
			queryParam("key", "AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/v1/sites/http%3A%2F%2Fwww."+website+".com%2F.").
		
		then().assertThat().statusCode(200).extract().response();
		
		System.out.println("Abusive Status Response is : " +res.asString());
	}
	
	
}
