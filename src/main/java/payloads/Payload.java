/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package payloads;

public class Payload {

	public static String googleAddPlacePayloadJSON() {
		return "{\r\n" + "    \"location\": {\r\n" + "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n" + "    },\r\n" + "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n" + "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n" + "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n" + "        \"shop\"\r\n" + "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n" + "    \"language\": \"French-IN\"\r\n" + "}";
	}

	public static String googleAddPlacePayloadXML() {
		return null;
	}

	public static String googleDeletePlacePayloadJSON(String placeID) {
		return "{" + "\"place_id\": \"" + placeID + "\"" + "}";
	}

	public static String addBookPayload(String isbn, String aisle) {
		return "{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\"" + isbn + "\",\r\n\"aisle\":\""
				+ aisle + "\",\r\n\"author\":\"John foe\"\r\n}\r\n";
	}

	public static String deleteBookPayload(String id) {
		return "{\r\n\"ID\" : \"" + id + "\" \r\n}\u00A0\r\n";
	}

}
