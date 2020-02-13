package exceptions;

public class MoneyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3941816702886136266L;

	public MoneyException(String error) {
		super(error);
	}
}
