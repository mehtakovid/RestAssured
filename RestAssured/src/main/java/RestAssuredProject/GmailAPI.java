package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GmailAPI {
	
	public static String messageId;
	public static String draftMessageId;
	
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
	
	
	
	public void listUserMessages(String email,String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given()
		.auth().oauth2(AccessToken)
		.queryParam("includeSpamTrash", false)
		.queryParam("maxResults", 2)
		.queryParam("q", "from:greetings@travel.e-redbus.in").
		
		when()
		.get("/gmail/v1/users/"+email+"/messages").
		
		then()
		.assertThat().statusCode(200).extract().response();
		
		JsonPath resPath = new JsonPath(res.asString());
		messageId = resPath.get("messages[0].id");
		
		System.out.println("Response from List User Messages : " +res.asString());
	}
	
	
	public void getUserMessages(String Email, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", messageId)
		.pathParam("userId", Email)
		.queryParam("format", "raw").
		when()
		.get("gmail/v1/users/{userId}/messages/{id}").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Response for getUser Messages is : " +res.asString());
	}

	
	public void deletMessageFromGmail(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.pathParam("id", messageId).
		when()
		.post("/gmail/v1/users/{userId}/messages/{id}/trash").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Response for deleteMessage From Gmail is : " +res.asString());
		
	}
	
	public void sendMessageFromGmail(String email, String toEmail, String MessageText, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.header("To", toEmail)
		.pathParam("userId", email)
		.queryParam("uploadType", "media")
		.body("{\r\n" + 
				"  \"raw\":"+ MessageText+"\r\n" + 
				"}").
		when()
		.post("/gmail/v1/users/{userId}/messages/send").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Response for Send message from Gmail is : " +res.asString());
		
	}
	
	public void listUserDraftMessages(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.queryParam("maxResults", 2).
		when()
		.get("/gmail/v1/users/{userId}/drafts").
		then()
		.assertThat().statusCode(200).extract().response();
		
		JsonPath respath = new JsonPath(res.asString());
		draftMessageId= respath.get("drafts[0].id");
		
	}
	
	public void getDraftFromGmail(String email, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", draftMessageId)
		.pathParam("userId", email )
		.queryParam("format", "metadata").
		when()
		.get("/gmail/v1/users/{userId}/drafts/{id}").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Response for Get draft from Gmail is : " +res.asString());
	}
	
	public void deleteDraftFromGmail(String email,String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("id", draftMessageId)
		.pathParam("userId", email).
		when()
		.delete("/gmail/v1/users/{userId}/drafts/{id}").
		then()
		.assertThat().statusCode(204).extract().response();
		
		System.out.println("Response for Delete Draft from Gmail : " +res.asString());
	}
	
	public void createDraftinGmail(String email, String message, String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("userId", email)
		.queryParam("uploadType", "media")
		.body("{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"raw\": \"DraftMessage\"\r\n" + 
				"  }\r\n" + 
				"}").
		when()
		.post("/upload/gmail/v1/users/{userId}/drafts").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Response for createDraft in gmail is  : " +res.asString());
	}
	

}
