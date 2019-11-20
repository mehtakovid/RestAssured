package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class GmailAPI {
	
	public static String messageId;
	public static String draftMessageId;
	public JsonPath jsonRes;
	
	
	/*****
	 * Get's the user details with total Message in inbox
	 * @validations Timeout,Header,NodeValue
	 * @param email
	 * @param AccessToken
	 */
	public void getUserProfile(String email,String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().
		auth().preemptive().oauth2(AccessToken).
		header("Content-Type","application/json").
		queryParam("key","AIzaSyCijvqCvlY1bjdB-h8lELdT571jBXr6vqU").
		
		when().
		get("/gmail/v1/users/"+email+"/profile").
		
		then().assertThat()
		.statusCode(200)
		.time(lessThan(4l), TimeUnit.SECONDS)
		.header("Vary", "X-Origin")
		.header("Server", "GSE")
		.header("X-Frame-Options", "SAMEORIGIN")
		.body("messagesTotal", not(hasSize(0)))
		.extract().response();
		
		jsonRes=new JsonPath(res.asString());
		System.out.println("Total Number of Messages in your inbox is : " +jsonRes.get("messagesTotal"));
		
		System.out.println("User profile response : " +res.asString());
	}
	
	/*******
	 * Provides the search result of email 
	 * with the given search keyword and fetches
	 * the messageID to be used in other locations
	 * @param email
	 * @param AccessToken
	 */
	
	public void listUserMessages(String email,String AccessToken, String Query) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given()
		.auth().oauth2(AccessToken)
		.queryParam("includeSpamTrash", false)
		.queryParam("maxResults", 2)
		.queryParam("q", Query).
		
		when()
		.get("/gmail/v1/users/"+email+"/messages").
		
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("resultSizeEstimate",not(hasSize(0)))
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		messageId = jsonRes.get("messages[0].id");
		System.out.println("Result Size Estimate is : "+jsonRes.get("resultSizeEstimate").toString());
		System.out.println("Response from List User Messages : " +res.asString());
	}
	
	/*******
	 * gets a particular message details when
	 * provided with message ID from list user
	 * messages request
	 * @Validations Timeout,NodeValue and StatusCode
	 * and testNG Assertions
	 * @param Email
	 * @param AccessToken
	 */
	public void getUserMessages(String Email, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", messageId)
		.pathParam("userId", Email)
		.queryParam("format", "raw").
		when()
		.get("/gmail/v1/users/{userId}/messages/{id}").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("snippet", not(hasSize(0)))
		.header("Cache-Control", "private, max-age=0, must-revalidate, no-transform")
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		int sizeofLabels = res.jsonPath().getList("labelIds").size();
		Assert.assertEquals(sizeofLabels, 3);
		System.out.println("Message Details are as follows : "+jsonRes.get("snippet").toString());
		System.out.println("Size of the message : "+jsonRes.get("sizeEstimate").toString());
		System.out.println("Response for getUser Messages is : " +res.asString());
	}

	/*****
	 * Deletes the email from Gmail
	 * when provided with the thread Id
	 * using list messages request
	 * @validations 
	 * @param email
	 * @param AccessToken
	 */
	public void deletMessageFromGmail(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.pathParam("id", messageId).
		when()
		.post("/gmail/v1/users/{userId}/messages/{id}/trash").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		Assert.assertEquals(jsonRes.get("id").toString(), messageId);
		
		System.out.println("Response for deleteMessage From Gmail is : " +res.asString());
		
	}
	
	/*****
	 * Sends an email to an recipient in To address
	 * @validations Status code and time Out
	 * @param email
	 * @param toEmail
	 * @param MessageText
	 * @param AccessToken
	 */
	public void sendMessageFromGmail(String email, String toEmail, 
			String MessageText, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.header("To", toEmail)
		.pathParam("userId", email)
		.queryParam("uploadType", "media")
		.body("{\r\n" + 
				"  \"raw\": \""+MessageText+"\"\r\n" + 
				"}").
		when()
		.post("/gmail/v1/users/{userId}/messages/send").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.extract().response();
		
		System.out.println("Response for Send message from Gmail is : " +res.asString());
		
	}
	
	/********
	 * List down drafts messages in 
	 * user gmail and asserts that the
	 * result size equals to MaxResults
	 * provided by user in query
	 * @validations 
	 * @param email
	 * @param AccessToken
	 */
	
	public void listUserDraftMessages(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.queryParam("maxResults", 2).
		when()
		.get("/gmail/v1/users/{userId}/drafts").
		then()
		.assertThat()
		.time(lessThan(4000l))
		.statusCode(200)
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		draftMessageId= jsonRes.get("drafts[0].id");
		
		int sizeofResults = res.jsonPath().getList("drafts").size();
		Assert.assertEquals(sizeofResults, 2);
		
	}
	
	/********
	 * Get the draft message details from gmail
	 * @validations Timeout,StatusCode,NodeValue
	 * @param email
	 * @param AccessToken
	 */
	public void getDraftFromGmail(String email, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", draftMessageId)
		.pathParam("userId", email )
		.queryParam("format", "metadata").
		when()
		.get("/gmail/v1/users/{userId}/drafts/{id}").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("snippet", not(hasSize(0)))
		.extract().response();
		jsonRes = new JsonPath(res.asString());
		
		Assert.assertEquals(jsonRes.get("id").toString(), draftMessageId);
		
			System.out.println("Details of Draft message is as follows : "+jsonRes.get("snippet"));
		
		System.out.println("Response for Get draft from Gmail is : " +res.asString());
	}
	
	
	/*****
	 * Deletes the given draft from Gmail
	 * @validations Timeout, NodeValue, Status Code
	 * @param email
	 * @param AccessToken
	 */
	public void deleteDraftFromGmail(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", draftMessageId)
		.pathParam("userId", email).
		when()
		.delete("/gmail/v1/users/{userId}/drafts/{id}").
		then()
		.assertThat()
		.statusCode(204)
		.time(lessThan(4000l))
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		System.out.println("Response for Delete Draft from Gmail : " +res.asString());
	}
	
	/****
	 * Creates a draft email in Gmail account
	 * @validations Timeout, NodeValue, StatusCode
	 * @param email
	 * @param message
	 * @param AccessToken
	 */
	public void createDraftinGmail(String email, String message, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.queryParam("uploadType", "media")
		.body("{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"raw\": \""+message+"\",\r\n" + 
				"  }\r\n" + 
				"}").
		when()
		.post("/upload/gmail/v1/users/{userId}/drafts").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("id",is(not(null)))
		.extract().response();
		System.out.println("Response for createDraft in gmail is  : " +res.asString());
	}
	

}
