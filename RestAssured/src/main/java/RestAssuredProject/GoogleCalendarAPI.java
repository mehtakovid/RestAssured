package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GoogleCalendarAPI {
	
	public void getCalendarList(String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 10).
		when()
		.get("/calendar/v3/users/me/calendarList").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Get Calendar List Response : "+res.asString());
	}
	
	public void getCalendar(String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("calendarId", "primary").
		when()
		.get("/calendar/v3/users/me/calendarList/{calendarId}").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Get Calendar Response : "+res.asString());
	}
	
	public void getCalendarColors(String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken).
		when()
		.get("/calendar/v3/colors").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Get Calendar Colors : " +res.asString());
		
	}
	
	public void getCalendarEventList(String AccessToken, String calendarId) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 8)
		.queryParam("q", "flight")
		.pathParam("calendarId", calendarId).
		when()
		.get("/calendar/v3/calendars/{calendarId}/events").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Calendar Event list Response is : "+res.asString());
	}
	
	
	//Body needs to be customized further to include relevant Params
	public void createAnEvent(String AccessToken,String CalendarId,String Timezone, String startDate, String endDate) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("sendUpdates", "all")
		.pathParam("calendarId", CalendarId)
		.body("{\r\n" + 
				"  \"end\": {\r\n" + 
				"    \"timeZone\":"+Timezone+",\r\n" + 
				"    \"date\":"+endDate+"\r\n" + 
				"  },\r\n" + 
				"  \"start\": {\r\n" + 
				"    \"timeZone\":"+Timezone+",\r\n" + 
				"    \"date\":"+startDate+"\r\n" + 
				"  }\r\n" + 
				"}").
		when()
		.post("/calendar/v3/calendars/{calendarId}/events");
//		then()
//		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Create an Event Response : " +res.asString());
		
	}
	
	public void listUserCalendarSettings(String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 3).
		when()
		.get("/calendar/v3/users/me/settings").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("List User Calendar Settings Response : "+res.asString());
	}

}
