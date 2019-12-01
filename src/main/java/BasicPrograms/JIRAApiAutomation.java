/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package BasicPrograms;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import payloads.JIRAPayloads;
import resources.JIRAResources;

public class JIRAApiAutomation {
	public static Properties prop;

	public static String generateSessionID() throws IOException {

		prop = new Properties();
		String filePath = Paths.get("./src/main/java/configuration/configuration.properties").toAbsolutePath()
				.normalize().toString();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);

		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		// GIVEN
		RequestSpecification httpRequest = given().header("Content-Type", "application/json")
				.body(JIRAPayloads.jiraAuthPayload());
		// WHEN
		@SuppressWarnings("rawtypes")
		ResponseBody httpResponse = httpRequest.request(Method.POST, JIRAResources.jiraAuthResource()).body();

		JsonPath body = httpResponse.jsonPath();
		String sessionID = body.getString("session.value");
		return sessionID;

	}

	public static void createAnIssueBug() throws IOException {
		RequestSpecification httpRequest = given().header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID=" + JIRAApiAutomation.generateSessionID())
				.body(JIRAPayloads.jiraCreateIssuePayload("Bug"));
		ResponseBody responseBody = httpRequest.request(Method.POST, JIRAResources.jiraCreateIssueResource()).body();
		JsonPath jsonPath = responseBody.jsonPath();

	}
}
