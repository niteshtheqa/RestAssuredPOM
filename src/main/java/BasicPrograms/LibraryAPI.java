/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package BasicPrograms;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.Payload;
import utility.Utility;

public class LibraryAPI {

	public static Properties prop;
	
//	public static String isbn = "gfdgdg";
//	public static String aisle = "354353";
//	public static String bookId = isbn + aisle;

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		prop = new Properties();
		String filePath = Paths.get("./src/main/java/configuration/configuration.properties").toAbsolutePath()
				.normalize().toString();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);

	}

	@Test(dataProvider="BooksData" ,dataProviderClass=Utility.class,description = "Add book using Library API", priority = 2, enabled = true)
	public void addBook(String isbn , String aisle) {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response response = given().header("Content-Type", "application/json").log().all()
				.body(Payload.addBookPayload(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response();
		JsonPath res = response.jsonPath();
		System.out.println("Book With ID :" + res.getString("ID") + " ==> " + res.getString("msg"));
	}

	@Test(description = "delete book using Library API", priority = 1,enabled =false)
	public void deleteBook() {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response response = given().header("Content-Type", "application/json").log().all()
				.body(Payload.deleteBookPayload("")).when().post("/Library/DeleteBook.php").then().log().all()
				.assertThat().statusCode(200).extract().response();
		// JsonPath res = response.jsonPath();
		// System.out.println(response.jsonPath().get("msg").toString());
	}

}
