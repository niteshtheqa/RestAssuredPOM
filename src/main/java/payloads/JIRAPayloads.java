/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package payloads;

/**
 * @author nites
 *
 */
public class JIRAPayloads {

	public static String jiraAuthPayload() {
		return "{\r\n    \"username\": \"netf7636\",\r\n    \"password\": \"I@Mitengg2611\"\r\n}";
	}

	public static String jiraCreateIssuePayload(String issueName) {
		return "{\r\n\"fields\":{\r\n\"project\": {\r\n\"key\": \"RES\"\r\n},\r\n\"summary\":\"creditcard bug\",\r\n\"description\":\"Creating of an issue using project keys and issue type names using the REST API\",\r\n \"issuetype\": {\r\n \"name\": \""
				+ issueName + "\"\r\n}\r\n}\r\n}";
	}
}
