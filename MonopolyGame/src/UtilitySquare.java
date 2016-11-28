package edu.neumont.csc110.a.monopoly;

public class UtilitySquare extends Square {
	
	public UtilitySquare(int location, String name, String color, int cost, int rent, int rentWithTwo, int mortgage) {
		super(location, name);
		setColor(color);
		setCost(cost);
		setRent(rent);
		setRentWithTwo(rentWithTwo);
		setMortgage(mortgage);
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("This color is: " + getColor());
		System.out.println("The cost is: " + getCost());
		System.out.println("The rent is : " + getRent() +" * the amount rolled");
		System.out.println("Rent with two owned is: " + getRentWithTwo() +" * the amount rolled");
		System.out.println("Mortgage value: " + getMortgage());
	}
}