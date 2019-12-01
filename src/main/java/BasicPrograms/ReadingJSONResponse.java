package BasicPrograms;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReadingJSONResponse {

	@Test
	public void readJSONResponse() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Pune");

		String body = response.body().asString();

		System.out.println(body);

		System.out.println(response.body().jsonPath().toString());
		System.out.println("andReturn" + response.andReturn().asString());

	}

}
