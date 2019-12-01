/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package BasicPrograms;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import resources.GooglePlaceResources;
import utility.Utility;

public class Basics {
	public static String placeID = null;
	public static Properties prop;

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		prop = new Properties();
		String filePath = Paths.get("./src/main/java/configuration/configuration.properties").toAbsolutePath()
				.normalize().toString();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);

	}

	@Test(description = "Verify header values i.e Server with GET method call",enabled = false)
	public void header() {
		// BaseURL or Host
		RestAssured.baseURI = prop.getProperty("GOOGLE_HOST");
		given().param("location", "-33.8670522,151.1957362").param("key", prop.getProperty("google_key"))
				.param("radius", "1500").log().all().when().get("/maps/api/place/nearbysearch/json").then()
				.statusCode(200).and().contentType(ContentType.JSON).and().body("results[0].name", equalTo("Sydney"))
				.and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Server", equalTo("scaffolding on HTTPServer2")).and()
				.header("Content-Encoding", equalTo("gzip")).log().all();

	}

	@Test(description = "Add place using POST call",enabled = false)
	// WE need to specify types of parameter here ,1, Query Parameter , 2.Path
	// parameter ,3.Header parameter

	public void useOfPOSTMethod() {
		RestAssured.baseURI = prop.getProperty("HOST");
		given().queryParam("key", prop.getProperty("key")).body(Payload.googleAddPlacePayloadJSON()).log()
				.all().when().post(GooglePlaceResources.googlePlaceAddResourceXML()).then().log().all().assertThat()
				.statusCode(200).and().body("status", equalTo("OK"));

	}

	@Test(description = "E2E- Add place and delete the same",enabled = false)
	public void addAndDeletePlace() {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response response = given().queryParam("key", prop.getProperty("key")).log().all()
				.body(Payload.googleAddPlacePayloadJSON()).when()
				.post(GooglePlaceResources.googlePlaceAddResourceJSON()).then().extract().response();

		String res = response.asString();
		System.out.println("Response Status :" + response.jsonPath().getString("status"));

		placeID = response.jsonPath().getString("place_id");

		given().queryParam("key", "qaclick123").log().all()
				.body(Payload.googleDeletePlacePayloadJSON(placeID)).when()
				.post(GooglePlaceResources.googlePlaceDeleteResource()).then().log().all().assertThat().statusCode(200)
				.and().body("status", equalTo("OK"));

	}

	@Test()
	public void seandAndretrievePayloadWithXMLFormat() throws IOException {

		String path = Paths.get("./src/main/java/xmlpayloadfiles/AddPlaceXMLPayload.xml").toAbsolutePath().normalize()
				.toString();
		Response response = given().queryParam("key", prop.getProperty("key")).log().all()
				.body(Utility.getStringFromXML(path)).when().post(GooglePlaceResources.googlePlaceAddResourceXML()).then()
				.log().all().extract().response();
//		XmlPath xmlResponse = new XmlPath(response);
		System.out.println("XML Output : "+response.xmlPath().getString("phone_number"));

	}

}
