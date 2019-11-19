package RestAssuredProject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import static io.restassured.RestAssured.given;

public class GoogleCalendarAPI {
	JsonPath jsonRes;
	
	/*******
	 * List down the User's calendar properties
	 * @validations Status Code, Timeout, NodeValues
	 * @param AccessToken
	 */
	public void getCalendarList(String AccessToken, String Username) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 5)
		.pathParam("calendarId", Username).
		when()
		.get("/calendar/v3/users/me/calendarList/{calendarId}").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.body("defaultReminders", not(hasSize(0)))
		.body("timeZone", equalTo("Asia/Kolkata"))
		.body("accessRole", equalTo("owner"))
		.extract().response();
		jsonRes = new JsonPath(res.asString());
		System.out.println("Event Response Notification Type : "+jsonRes.get("notificationSettings.notifications[3].method").toString());
		System.out.println("Event Reminder Time by Email : "+jsonRes.get("defaultReminders[0].minutes").toString());
		System.out.println("Get Calendar List Response : "+res.asString());
	}
	
	/********
	 * Get the calendar details of Authenticated User
	 * @validations Status Code, TimeOut and Node Value
	 * @param AccessToken
	 */
	public void getCalendar(String AccessToken, String Username) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("calendarId", Username).
		when()
		.get("/calendar/v3/users/me/calendarList/{calendarId}").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(4000l))
		.contentType(ContentType.JSON)
		.body("id", equalTo(Username))
		.extract().response();
		
		System.out.println("Get Calendar Response : "+res.asString());
	}
	
	
	/*********
	 * Get calendar colors and print the Results Size
	 * @validations timeout,ContentType,get NodeValues Size
	 * @param AccessToken
	 */
	public void getCalendarColors(String AccessToken) {
		
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken).
		when()
		.get("/calendar/v3/colors").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(5000l))
		.body("calendar", not(Matchers.hasSize(0)))
		.body("event", not(Matchers.empty()))
		.contentType(ContentType.JSON)
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		System.out.println("Calendar 1 Background Value : "+jsonRes.get("calendar.1.background").toString());
		
		
		System.out.println("Get Calendar Colors : " +res.asString());
		
	}
	
	/*********
	 * Gets the Calendar events 
	 * with keyword flight and print the event summary
	 * where status is equal to confirmed
	 * @validations : StatusCode, NodeValidations and Conditional Output
	 * @param AccessToken
	 * @param calendarId
	 */
	public void getCalendarEventList(String AccessToken, String calendarId) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 4)
		.queryParam("q", "flight")
		.pathParam("calendarId", calendarId).
		when()
		.get("/calendar/v3/calendars/{calendarId}/events").
		then()
		.assertThat()
		.statusCode(200)
		.body("summary", equalTo("kovidmehta10@gmail.com"))
		.body("accessRole", equalTo("owner"))
		.log().ifValidationFails()
		.extract().response();
		
		jsonRes = new JsonPath(res.asString());
		System.out.println("Number of Events Returned : " +res.jsonPath().getList("items").size());
		
		for(int i=0;i<res.jsonPath().getList("items").size();i++) {
			if(jsonRes.get("items["+i+"].status").toString().equals("confirmed")) {
				System.out.println(jsonRes.get("items["+i+"].summary"));
			}
		}
		
	}
	
	
	/********
	 * Creates an event in the 
	 * authorized user calendar
	 * @validations StatusCode and Timeout
	 * @param AccessToken
	 * @param CalendarId
	 * @param Timezone
	 * @param startDate
	 * @param endDate
	 */
	public void createAnEvent(String AccessToken,String CalendarId,
			String Timezone, String startDate, String endDate) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("sendUpdates", "all")
		.pathParam("calendarId", CalendarId)
		.contentType("application/json\r\n")
		.body("{\r\n" + 
				"  \"end\": {\r\n" + 
				"    \"timeZone\": \""+Timezone+"\",\r\n" + 
				"    \"date\": \""+endDate+"\"\r\n" + 
				"  },\r\n" + 
				"  \"start\": {\r\n" + 
				"    \"timeZone\": \""+Timezone+"\",\r\n" + 
				"    \"date\": \""+startDate+"\"\r\n" + 
				"  }\r\n" + 
				"}").
		when()
		.post("/calendar/v3/calendars/{calendarId}/events").
		then()
		.assertThat()
		.statusCode(200)
		.time(lessThan(5000l))
		.extract().response();
		
		System.out.println("Create an Event Response : " +res.asString());
		
	}
	
	/***********
	 * Lists the user calendar Settings
	 * @validations Status Code,Timeout, Node Value 
	 * and Empty Response check
	 */
	public void listUserCalendarSettings(String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 3).
		when()
		.get("/calendar/v3/users/me/settings").
		then()
		.assertThat()
		.statusCode(200)
		.contentType("application/json")
		.time(lessThan(4000l))
		.body("items[0].id", equalTo("autoAddHangouts"))
		.body("items", not(Matchers.hasSize(0)))
		.body("items[1].id", equalTo("defaultEventLength"))
		.extract().response();
		
		System.out.println("List User Calendar Settings Response : "+res.asString());
	}

}
