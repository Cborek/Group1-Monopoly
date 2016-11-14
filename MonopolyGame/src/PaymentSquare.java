package edu.neumont.csc110.a.monopoly;

public class PaymentSquare extends Square {
	
	public PaymentSquare(int location, String name, int rent) {
		super(location, name);
		setRent(rent);
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("The rent is: " +getRent(1));
	}
}
