package gui.controller;

import gui.controller.TicketController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyController implements Controller {
	
	/**forwards to the TicketController (only there for errors)(not used currently)
	 * 
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		new TicketController().execute(request, response, message);
		return "/tickets";
	}

}
