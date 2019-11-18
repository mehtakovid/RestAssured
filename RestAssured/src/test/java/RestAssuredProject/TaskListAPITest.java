package RestAssuredProject;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TaskListAPITest extends BaseClass {

	GoogleTaskListAPI TS1 = new GoogleTaskListAPI();
	
	@BeforeTest
	public void gettingAuthToken() throws Exception {
		BaseClass.constructAuthenticationURL("https://accounts.google.com","/o/oauth2/v2/auth","https://www.googleapis.com/auth/tasks",
				"https://accounts.google.com/o/oauth2/auth",
				"745077292106-oihr15fk3od8mn5i12eb1jlkk67352eh.apps.googleusercontent.com",
				"code","https://www.getpostman.com/oauth2/callback",
				"empty");
		BaseClass.driverInitialization();
		BaseClass.getCodeThroughBrowserAuthentication();
		BaseClass.driverTearDown();
		BaseClass.getBearerAccessToken();
	} 
	
	
	
	@Test
	public void listAllTaskListsTest() {
		TS1.listAllTaskLists(AccessToken);
	}
	
	@Test
	public void createNewTaskTest() {
		TS1.createNewTask(AccessToken);
	}
	
	
	
}
