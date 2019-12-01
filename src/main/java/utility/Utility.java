/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;

public class Utility {
	public static String getStringFromXML(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));

	}

	@DataProvider(name = "BooksData")
	public static Object[][] getData() {
		return new Object[][] { {"erihrow","4552"}, {"fdhgd","6756"}, {"fhjfh","7997"} };
	}
}
