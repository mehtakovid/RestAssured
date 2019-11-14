package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailAPI {
	
	public static String URL;
	public static String BrowserCode;
	public static String AccessToken;
	
	
	//This method should be made generic and placed inside utility class
	public void constructAuthURL(String BaseURI, String Resource,String scope,String auth_url,String client_id,
			String responseType,String redirect_uri,String state) { 
		
		URL = BaseURI+Resource+"?scope="+scope+"&auth_url="+auth_url+"&client_id="+client_id+
				"&response_type="+responseType+"&redirect_uri="+redirect_uri+"&state="+state;
		System.out.println(URL);
		
	}
	

	
	public void getCodeFromURLthroughBrowser(WebDriver driver, WebDriverWait wait, String Username,String Password) throws Exception {
		driver.get(URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).sendKeys(Username);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']"))).sendKeys(Password);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(10000);
		
		String URLWITHCODE = driver.getCurrentUrl();
		String[] arr =  URLWITHCODE.split("&code=");
		String code[] = arr[1].split("&scope=");
		
		BrowserCode = code[0];
		
		System.out.println("The Code is :- " +BrowserCode);
	
	}
	
	
	public void getToken() {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		System.out.println("The Code is :- " +BrowserCode);
		Response res = given().urlEncodingEnabled(false)
		.queryParam("code", BrowserCode)
		.queryParam("client_id", "745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com")
		.queryParam("client_secret", "FPMg6ii-JBWy-4CB7fVHBzzD")
		.queryParam("redirect_uri", "https://www.getpostman.com/oauth2/callback")
		.queryParam("grant_type","authorization_code").
		when()
		.post("/oauth2/v4/token").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("The response is : " +res.asString());
		
		JsonPath json = res.jsonPath();
		AccessToken = json.get("access_token");
	}
		
	
	/******
	 * below is the non working code
	 * @param email
	 */
	
	
	public void getUserProfile(String email) {
		
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
