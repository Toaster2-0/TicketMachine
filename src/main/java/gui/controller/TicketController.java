package gui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.MoneyBag;
import main.Singleton;
import main.TicketMachine;

public class TicketController implements Controller {
	private String CHOSEN_OFFER = "chosenOffer";
	private String TO_PAY = "toPay";
	private String OFFER = "offer";
	private String COIN = "coin";
	private String MESSAGE = "message";
	private String MONEY = "money";
	private String CHANGE = "change";
	/**provides the required attributes and functions of the ticketsite
	 * 
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		TicketMachine t = Singleton.getInstance().getTicketMachine();
		if(request.getParameter(CHOSEN_OFFER)==null) {
			request.setAttribute(MESSAGE, TicketMachine.WELCOME_MESSAGE);
			request.setAttribute(OFFER, t.getOffers());
		}else if(request.getParameter(TO_PAY)==null) {
			request.setAttribute(CHOSEN_OFFER, request.getParameter(CHOSEN_OFFER));
			request.setAttribute(TO_PAY, t.getOffers().get(request.getAttribute(CHOSEN_OFFER)));
			request.setAttribute(MONEY, MoneyBag.money);
		}else if(Integer.parseInt(request.getParameter(TO_PAY))>=0) {
			request.setAttribute(MONEY, MoneyBag.money);
			request.setAttribute(TO_PAY, Integer.parseInt(request.getParameter(TO_PAY)));
			request.setAttribute(TO_PAY, (int)request.getAttribute(TO_PAY)-t.pay(""+request.getParameter(COIN)));
			request.setAttribute(CHOSEN_OFFER, request.getParameter(CHOSEN_OFFER));
			if((int)request.getAttribute(TO_PAY)<=0) {
				request.setAttribute(CHANGE, t.changeMoney((int)request.getAttribute(TO_PAY)*-1));
				request.setAttribute(TO_PAY, 0);
				request.setAttribute(MESSAGE, TicketMachine.BYE_MESSAGE);
				t.save();
			}
		}
		return null;
	}

}
