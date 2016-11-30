public class PaymentSquare extends Square {
	//this class handles players paying rent to players. gets information of the square they must pay rent for
	public PaymentSquare(int location, String name, int rent) {
		super(location, name);
		setRent(rent);
		super.setIsOwned();
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("The rent is: " +getRent());
	}
}
