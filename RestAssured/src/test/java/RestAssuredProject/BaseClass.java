package RestAssuredProject;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseClass {

	/******
	 * Basic Variables Used across
	 * Tests are being initialized here
	 */
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String URL;
	public static String BrowserCode;
	public static String AccessToken;
	public static String Username = "kovidmehta10@gmail.com";
	public static String Password = "yourpassword";
	
	
	/*****
	 * MethodName : driverInitialization
	 * This is to provide the WebDriver Accessibility
	 * in RestAssured Framework to Perform Auths
	 * Created Date : - 11/14/2019
	 */
	
	public static void driverInitialization() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\komehta\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		wait=new WebDriverWait(driver,10);
	}
	
	/*****
	 * Method Name : driverTearDown
	 * Created to Close the open browsers
	 * Created Date - 11/14/2019
	 */
	public static void driverTearDown() {
		driver.quit();
		driver=null;
		wait=null;
		System.gc();
	}
	
	/*****
	 * Method Name : constructAuthenticationURL
	 * Created to construct the URL with different Parameters 
	 * needed for authentication and get the code 
	 * Created Date - 11/14/2019
	 */

	public static void constructAuthenticationURL(String BaseURI, String Resource,String scope,String auth_url,String client_id,
			String responseType,String redirect_uri,String state) { 
		
		URL = BaseURI+Resource+"?scope="+scope+"&auth_url="+auth_url+"&client_id="+client_id+
				"&response_type="+responseType+"&redirect_uri="+redirect_uri+"&state="+state;
		System.out.println(URL);
		
	}
	
	/*****
	 * Method Name : getCodeThroughBrowserAuthentication
	 * Created to Open the Auth URL in browser and  
	 * and Enter credentials to get auth Code 
	 * Created Date - 11/14/2019
	 */
	
	public static void getCodeThroughBrowserAuthentication() throws Exception {
		driver.get(URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).sendKeys(Username);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']"))).sendKeys(Password);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(5000);
		if(!driver.findElements(By.xpath("//h1[contains(text(),'This')]")).isEmpty()) {
			driver.findElement(By.xpath("//a[text()='Advanced']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Go to getpostman.com (unsafe)']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Allow'])[2]"))).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Allow']"))).click();
		}else {
			System.out.println("App verified Directly.");
		}
		Thread.sleep(10000);
		String[] arr =  driver.getCurrentUrl().split("&code=");
		String code[] = arr[1].split("&scope=");
		BrowserCode = code[0];
		System.out.println("The Code is :- " +BrowserCode);
	
	}
	
	
	/*****
	 * Method Name : getBearerAccessToken
	 * Uses the Browser code to get the bearer  
	 * Access token needed for OAUTH2.0
	 * Created Date - 11/14/2019
	 */
	public static void getBearerAccessToken() {
		
		RestAssured.baseURI="https://www.googleapis.com";
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
	
	
}
