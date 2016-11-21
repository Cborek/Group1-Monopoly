public class PaymentSquare extends Square {
	
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