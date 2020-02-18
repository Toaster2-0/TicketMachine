package gui.controller;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.MoneyBag;
import main.Singleton;
import main.TicketMachine;

public class ServiceController implements Controller {
	private String COIN = "coin";
	private String CHANGE = "change";
	private String PLUS = "plus";
	private String MINUS = "minus";
	private String REMOVE_OFFER = "removeOffer";
	private String TICKETCOST = "ticketcost";
	private String TICKETNAME = "ticketname";
	private String TOTAL_VALUE = "totalValue";
	private String COINS = "coins";
	private String OFFERS = "offers";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		TicketMachine t = Singleton.getInstance().getTicketMachine();
		
		if(request.getParameter(COIN)!=null) {
			int coin = MoneyBag.getIndexByValue(Integer.parseInt(request.getParameter(COIN)));
			if(request.getParameter(CHANGE)!=null) {
				if(request.getParameter(CHANGE).equals(PLUS )) {
					t.setMoney(coin, t.getMoneyLeft(coin)+1);
				}else if(request.getParameter(CHANGE).equals(MINUS )) {
					t.setMoney(coin, t.getMoneyLeft(coin)-1);
				}
			}else {
				int newAmmount = Integer.parseInt(request.getParameter("newAmmount"));
				t.setMoney(coin, newAmmount);
			
			}
		}
		if (request.getParameter(REMOVE_OFFER )!=null) {
			t.removeOffer(request.getParameter(REMOVE_OFFER));
		}
		if(request.getParameter(TICKETNAME)!=null && request.getParameter(TICKETCOST )!=null) {
			String ticketname = request.getParameter(TICKETNAME);
			int ticketcost = Integer.parseInt(request.getParameter(TICKETCOST));
			t.addOffer(ticketname, ticketcost);
		}
		request.setAttribute(TOTAL_VALUE , t.getTotalValue());
		SortedMap<Integer, Integer> money = new TreeMap<>();
		for(int i= 0; i < MoneyBag.money.length; i++) {
			money.put(MoneyBag.money[i], t.getMoneyLeft(i));
		}
		request.setAttribute(COINS, money);
		request.setAttribute(OFFERS, t.getOffers());
		t.save();
		return null;
	}

}
