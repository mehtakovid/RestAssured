package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AbusiveExperienceAPI {
	
	public static JsonPath jsonRes;
	
	public void getViolatingSites()  {
		
		RestAssured.baseURI="https://abusiveexperiencereport.googleapis.com";
		
		Response res = given().
		queryParam("key","AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when()
		.get("/v1/violatingSites").
		
		then()
		.assertThat()
		.statusCode(200)
		.extract().response();
		
		int totalSites = res.jsonPath().getList("violatingSites").size();
		System.out.println(totalSites);
		jsonRes = new JsonPath(res.asString());
		for(int i=0;i<totalSites;i++) {
			Assert.assertEquals(jsonRes.get("violatingSites["+i+"].abusiveStatus"), "FAILING");
		}
		for(int i=0;i<10;i++) {
			System.out.println("Abusive Experience failing 10 Site Name : "+jsonRes.get("violatingSites["+i+"].reviewedSite"));
		}
		
	}
	
	/******
	 * Checks whether the abusive status of
	 * given Website is passing or not
	 * @validations Timeout,StatusCode,Nodevalue
	 * @param website
	 */
	public void getAbusiveStatus(String website) {
		
		RestAssured.baseURI="https://abusiveexperiencereport.googleapis.com";
		
		Response res = given().
			queryParam("key", "AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/v1/sites/http%3A%2F%2Fwww."+website+".com%2F.").
		
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("abusiveStatus",equalTo("PASSING"))
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		
		System.out.println("Filter Status of the requested Site : "+jsonRes.get("filterStatus").toString());
		System.out.println("Last Change Date : "+jsonRes.get("lastChangeTime").toString().substring(0, 10));
		
		System.out.println("Abusive Status Response is : " +res.asString());
	}
	
	
}
