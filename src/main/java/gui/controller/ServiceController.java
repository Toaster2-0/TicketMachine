package gui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.MoneyBag;
import main.Singleton;
import main.TicketMachine;

public class ServiceController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, StringBuffer message)
			throws Exception {
		TicketMachine t = Singleton.getInstance().getTicketMachine();
		
		if(request.getParameter("coin")!=null) {
			int coin = MoneyBag.getIndexByValue(Integer.parseInt(request.getParameter("coin")));
			if(request.getParameter("change")!=null) {
				if(request.getParameter("change").equals("plus")) {
					t.setMoney(coin, t.getMoneyLeft(coin)+1);
				}else if(request.getParameter("change").equals("minus")) {
					t.setMoney(coin, t.getMoneyLeft(coin)-1);
				}
			}else {
				int newAmmount = Integer.parseInt(request.getParameter("newAmmount"));
				t.setMoney(coin, newAmmount);
			
			}
		}
		if (request.getParameter("removeOffer")!=null) {
			t.removeOffer(request.getParameter("removeOffer"));
		}
		System.out.println(request.getParameter("ticketname"));
		if(request.getParameter("ticketname")!=null && request.getParameter("ticketcost")!=null) {
			String ticketname = request.getParameter("ticketname");
			int ticketcost = Integer.parseInt(request.getParameter("ticketcost"));
			t.addOffer(ticketname, ticketcost);
		}
		request.setAttribute("totalValue", MoneyBag.centToEuro(t.getTotalValue()));
		Map<Integer, Integer> money = new HashMap<>();
		for(int i= 0; i < MoneyBag.money.length; i++) {
			money.put(MoneyBag.money[i], t.getMoneyLeft(i));
		}
		request.setAttribute("coins", money);
		request.setAttribute("offers", t.getOffers());
		t.save();
		return null;
	}

}
