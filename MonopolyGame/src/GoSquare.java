package edu.neumont.csc110.a.monopoly;

public class GoSquare extends Square{

	public GoSquare(int location, String name, int rent) {
		super(location, name);
		setRent(rent);
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("The rent is: " +getRent());
	}
	
	//WRITE IN WHAT HAPPENS WHEN YOU LAND ON OR PASS THIS SQUARE
}
