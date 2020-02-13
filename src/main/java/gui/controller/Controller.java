package gui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	public class TicketController {

	}

	String execute(HttpServletRequest request,
					  HttpServletResponse response, StringBuffer message) throws Exception;
}

