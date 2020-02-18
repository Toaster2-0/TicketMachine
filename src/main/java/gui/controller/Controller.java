package gui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	/**sets and processes the required Attributes for a site
	 * 
	 * @param request
	 * @param response
	 * @param message
	 * @return the new location of the jsp (for example "/template" for /template.jsp)
	 * @throws Exception
	 */
	String execute(HttpServletRequest request,
					  HttpServletResponse response, StringBuffer message) throws Exception;
}

