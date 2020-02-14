package gui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Singleton;
import main.TicketMachine;

public class TicketController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		TicketMachine t = Singleton.getInstance().getTicketMachine();
		if(request.getParameter("chosenOffer")==null) {
			request.setAttribute("message", TicketMachine.WELCOME_MESSAGE);
			request.setAttribute("offer", t.getOffers());
		}else if(request.getAttribute("toPay")==null) {
			request.setAttribute("chosenOffer", request.getParameter("chosenOffer"));
			request.setAttribute("chosenOffer", request.getParameter("chosenOffer"));
			request.setAttribute("toPay", t.getOffers().get(request.getAttribute("chosenOffer")));
		}else if(Integer.parseInt(request.getParameter("toPay"))>=0) {
			request.setAttribute("toPay", request.getParameter("toPay"));
			request.setAttribute("toPay", (int)request.getAttribute("toPay")-t.pay(""+request.getParameter("coin")));
			if((int)request.getAttribute("toPay")<=0) {
				request.setAttribute("change", t.changeMoney((int)request.getAttribute("toPay")*-1));
				request.setAttribute("message", TicketMachine.BYE_MESSAGE);
			}
		}
		return null;
	}

}
