package main;



public class Singleton {
	private static Singleton instance;
	private TicketMachine ticket;
	private Singleton() {
		ticket = TicketMachine.load();
	}
	/**
	 * returns the ticketmachine of this instance
	 * @return
	 */
	public TicketMachine getTicketMachine() {
		return ticket;
	}
	/**creats and (or just) returns the Instance
	 * 
	 * @return
	 */
	public static Singleton getInstance() {
		if(instance==null) {
			Singleton.instance =  new Singleton();
		}
		return Singleton.instance;
	}
}
