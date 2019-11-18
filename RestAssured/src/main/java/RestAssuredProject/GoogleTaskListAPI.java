package RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GoogleTaskListAPI {

	public void listAllTaskLists(String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.queryParam("maxResults", 2).
		when()
		.get("/tasks/v1/users/@me/lists").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("List All Task Lists response : "+res.asString());
	}
	
	public void createNewTask(String AccessToken) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("tasklist", "tasklist").
		when()
		.post("/tasks/v1/lists/{tasklist}/tasks").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("New Task creation response : "+res.asString());
	}
	
	public void DeleteCreatedTask(String AccessToken, String Task, String TaskList) {
		RestAssured.baseURI="https://www.googleapis.com";
		Response res = given().auth().oauth2(AccessToken)
		.pathParam("task", Task)
		.pathParam("tasklist", TaskList).
		when()
		.delete("/tasks/v1/lists/{tasklist}/tasks/{task}").
		then()
		.assertThat().statusCode(200).extract().response();
		
		System.out.println("Delete Created Task response : "+res.asString());
	}
}
