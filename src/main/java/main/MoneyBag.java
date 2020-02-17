package main;

import exceptions.MoneyException;

public class MoneyBag {
	private int oneCent;
	private int twoCent;
	private int fiveCent;
	private int tenCent;
	private int twentyCent;
	private int fiftyCent;
	private int oneEuro;
	private int twoEuro;
	private int fiveEuro;
	private int tenEuro;
	private int twentyEuro;
	private int fiftyEuro;
	public static int[] money = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000};
	public MoneyBag(int oneCent, int twoCent, int fiveCent, int tenCent, int twentyCent, int fiftyCent, int oneEuro,
			int twoEuro, int fiveEuro, int tenEuro, int twentyEuro, int fiftyEuro) {
		super();
		this.oneCent = oneCent;
		this.twoCent = twoCent;
		this.fiveCent = fiveCent;
		this.tenCent = tenCent;
		this.twentyCent = twentyCent;
		this.fiftyCent = fiftyCent;
		this.oneEuro = oneEuro;
		this.twoEuro = twoEuro;
		this.fiveEuro = fiveEuro;
		this.tenEuro = tenEuro;
		this.twentyEuro = twentyEuro;
		this.fiftyEuro = fiftyEuro;
	}
	
	public int getTotalValue() {
		int totalValue=0;
		for (int i= 0; i < money.length;i++) {
			try {
				totalValue += money[i]*getMoneyLeft(i);
			} catch (MoneyException e) {
			}
		}
		return totalValue;
	}
	
	public static int getIndexByValue(int value) throws MoneyException {
		for(int i = 0; i < money.length; i++) {
			if(money[i]==value) {
				return i;
			}
		}
		throw new MoneyException("I guess here is a counterfeiter, because this is no coin");
	}
	public void setMoney(int index, int newValue) throws MoneyException {
		switch (index) {
		case 0:
			oneCent = newValue;
			break;
		case 1:
			twoCent = newValue;
			break;
		case 2:
			fiveCent = newValue;
			break;
		case 3:
			tenCent = newValue;
			break;
		case 4:
			twentyCent = newValue;
			break;
		case 5:
			fiftyCent = newValue;
			break;
		case 6:
			oneEuro = newValue;
			break;
		case 7:
			twoEuro = newValue;
			break;
		case 8:
			fiveEuro = newValue;
			break;
		case 9:
			tenEuro = newValue;
			break;
		case 10:
			twentyEuro = newValue;
			break;
		case 11:
			fiftyEuro = newValue;
			break;
		default:
			throw new MoneyException("There are only coins until 50 Euros");
		}
	}
		
	public int getMoneyLeft(int index) throws MoneyException {
		switch (index) {
		case 0:
			return oneCent;
		case 1:
			return twoCent;
		case 2:
			return fiveCent;
		case 3:
			return tenCent;
		case 4:
			return twentyCent;
		case 5:
			return fiftyCent;
		case 6:
			return oneEuro;
		case 7:
			return twoEuro;
		case 8:
			return fiveEuro;
		case 9:
			return tenEuro;
		case 10:
			return twentyEuro;
		case 11:
			return fiftyEuro;
		default:
			throw new MoneyException("There are only coins until 50 Euros");
		}
	}
	public static float centToEuro(int cent) {
		return (float) (cent/100.0);
	}
	
	
}
