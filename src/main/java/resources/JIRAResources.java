/**
 * @author Nitesh Wayafalkar
 * @Project Title  AutomationPractice
 * 
 */
package resources;

/**
 * @author nites
 *
 */
public class JIRAResources {
	public static String jiraAuthResource() {
		return "/rest/auth/1/session";
	}
	
	public static String jiraCreateIssueResource() {
		return "/rest/api/2/issue";
	}
}
