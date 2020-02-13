package main;



public class Singleton {
	private static Singleton instance;
	private TicketMachine ticket;
	private Singleton() {
		ticket = TicketMachine.load();
	}
	public TicketMachine getTicketMachine() {
		return ticket;
	}
	public static Singleton getInstance() {
		if(instance==null) {
			Singleton.instance =  new Singleton();
		}
		return Singleton.instance;
	}
}
