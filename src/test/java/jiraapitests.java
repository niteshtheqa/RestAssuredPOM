import org.testng.annotations.Test;

import io.restassured.RestAssured;

/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */

/**
 * @author nites
 *
 */
public class jiraapitests {
	@Test
	public void authenticationUser() {
		RestAssured.baseURI = "http://localhost:8080";

	}

	@Test
	public void createAnIssueBug() {

	}

	@Test
	public void deleteAnIssueBug() {

	}

	@Test
	public void addCommentOnAnIssueBug() {

	}

	@Test
	public void updateCommentOnAnIssueBug() {

	}
}
