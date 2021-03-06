package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import main.MoneyBag;

import exceptions.MoneyException;
import exceptions.ValidierungsException;


public class TicketMachine {
	
	private Scanner scanner = new Scanner(System.in);
	private MoneyBag storage;
	private Map<String, Integer> offers = new HashMap<>();
	private static Datei folder = new Datei(System.getProperty("user.home")+"/Ticket");
	private static Datei file = new Datei(System.getProperty("user.home")+"/Ticket/coins.txt");
	public static String WELCOME_MESSAGE = "Hey welcome to the German Train Services";
	public static String BYE_MESSAGE = "Good luck that the German train Services aren't late :D";
	
	public static void main(String[] args) {
		TicketMachine s = load();
		s.start();
		s.save();
		
	}
	
	/**outstream for Console (probaply outdated)
	 * 
	 */
	public  void start() {
		System.out.println(WELCOME_MESSAGE);
		for (Entry<String, Integer> offer : offers.entrySet()) {
			System.out.println("You can buy the "+ offer.getKey() +" for "+ MoneyBag.centToEuro(offer.getValue())+" Euros");
		}
		String input = "";
		do {		
			if(input!="") {
				System.out.println("This is no available ticket");
			}
			System.out.println("Type in the Ticket you want");
		    input = scanner.nextLine();
		}while(!offers.containsKey(input));
		String chosenOffer = input;
		System.out.println("that costs "+ MoneyBag.centToEuro(offers.get(chosenOffer))+" Euros");
		Integer paid = offers.get(chosenOffer);
		while(0 < paid) {
			input = scanner.nextLine();
			try {
				paid -= pay(input);
			} 
			catch (MoneyException e) {
				System.out.println(e.getMessage());
			}
			catch(NumberFormatException e) {
				System.out.println("please write an actual number");
			}
		}
		try {
			List<Integer> change = changeMoney(paid*-1);
			for (Integer coin : change) {
				System.out.println("Here u got "+coin);
			}
		} catch (MoneyException e) {
			e.printStackTrace();
		}
		System.out.println(BYE_MESSAGE);	
	}
	public TicketMachine(MoneyBag storage) {
		this.storage = storage;
	}
	
	/**loads the file coin.txt and converts it to the Ticketmachine it cant make a ticketmachine or there is no file
	 *  it creates a  Ticketmachine with 10000, 10000, 10000, 1000, 1000, 1000, 100, 100, 10, 10, 10, 10
	 * 	and offer "TagesKarte", 50
	 * @return
	 */
	public static TicketMachine load() {
		TicketMachine s = null;
		if(TicketMachine.file.dateiExistiert()) {
			try {
				String read = TicketMachine.file.lese();
				String[] readSplit = read.split(";");
				s = new TicketMachine(new MoneyBag(Integer.parseInt(readSplit[0]), Integer.parseInt(readSplit[1]), Integer.parseInt(readSplit[2]), Integer.parseInt(readSplit[3]), Integer.parseInt(readSplit[4]), Integer.parseInt(readSplit[5]), Integer.parseInt(readSplit[6]), Integer.parseInt(readSplit[7]), Integer.parseInt(readSplit[8]), Integer.parseInt(readSplit[9]), Integer.parseInt(readSplit[10]), Integer.parseInt(readSplit[11])));
				for(int i = 12; i <readSplit.length-1; i+=2) {
					s.addOffer(readSplit[i], Integer.parseInt(readSplit[i+1]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(s == null) {
			s = new TicketMachine(new MoneyBag(10000, 10000, 10000, 1000, 1000, 1000, 100, 100, 10, 10, 10, 10));
			try {
				s.addOffer("TagesKarte", 50);
			} catch (ValidierungsException e) {
				e.printStackTrace();
			}
		}
		return s;
		
	}
	
	/**saves the Ticketmachine to the default folder
	 * 
	 */
	public void save() {
		folder.dateiPfadErstellen();
		try {
			file.schreibe("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < MoneyBag.money.length; i++) {
			try {
				file.schreibe(""+storage.getMoneyLeft(i)+";", true);
			} catch (MoneyException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		for(Entry<String, Integer> offer: offers.entrySet()) {
			try {
				file.schreibe(""+offer.getKey()+";"+offer.getValue()+";", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * gets the String of an int and changes the coin ammount in the Moneybag and returns the int of the inserted string
	 * @param paidAmount the value of the coin which was paid
	 * @return
	 * @throws MoneyException
	 * @throws NumberFormatException
	 */
	public Integer pay(String paidAmount) throws MoneyException, NumberFormatException {
		int value = Integer.parseInt(paidAmount);
		storage.setMoney(MoneyBag.getIndexByValue(value), storage.getMoneyLeft(MoneyBag.getIndexByValue(value))+1);
		return value;
	}
	
	/**
	 * returns a list with the available coins of the change money and changes the value of them in the moneybag
	 * @param moneyOverPrice
	 * @return
	 * @throws MoneyException
	 */
	public List<Integer> changeMoney(int moneyOverPrice) throws MoneyException {
		List<Integer> change = new ArrayList<Integer>();
		for(int i = MoneyBag.money.length-1; i >= 0; i--) {
			while(storage.getMoneyLeft(i)>0 && moneyOverPrice / MoneyBag.money[i] !=0) {
				storage.setMoney(i, storage.getMoneyLeft(i)-1);
				moneyOverPrice -= MoneyBag.money[i];
				change.add(MoneyBag.money[i]);
			}
		}
		if(moneyOverPrice!=0) {
			throw new MoneyException("not enough Cash in vault");
		}
		return change;
	}
	/**
	 * changes the ammount of the stored coins of index to new Value
	 * @param index
	 * @param newValue
	 * @throws MoneyException
	 */
	public void setMoney(int index, int newValue) throws MoneyException {
		if(newValue<0) {
			throw new MoneyException("you can't insert less than 0");
		}
		storage.setMoney(index, newValue);
	}
	/**returns the ammount of coins left of index
	 * 
	 */
	public int getMoneyLeft(int index) {
		try {
			return storage.getMoneyLeft(index);
		} catch (MoneyException e) {
			return 0;
		}
	}
	/**returns the Value of stored Money in cents
	 * 
	 * @return
	 */
	public int getTotalValue() {
		return storage.getTotalValue();
	}
	/**
	 * removes the Offer with the inserted title if existing
	 * @param title
	 */
	public void removeOffer(String title) {
		offers.remove(title);
	}
	/**
	 * adds a new Offer with the given title and price
	 * @param title
	 * @param price
	 * @throws ValidierungsException
	 */
	public void addOffer(String title, int price) throws ValidierungsException {
		if(title=="") {
			throw new ValidierungsException("Title can't be emty");
		}
		if(price<0) {
			throw new ValidierungsException("Price can't be lower than 0");
		}
		if(title.contains(";")) {
			throw new ValidierungsException("Semicolonexception D:");
		}
		offers.put(title, price);
	}
	/**
	 * returns the available offers
	 * @return
	 */
	public Map<String, Integer> getOffers() {
		return offers;
	}
	
	/**
	 * changes the offers to the new Map given
	 * @param offers
	 */
	public void setOffers(Map<String, Integer> offers) {
		this.offers = offers;
	}

}
