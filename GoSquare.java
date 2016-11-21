public class GoSquare extends Square{

	public GoSquare(int location, String name) {
		super(location, name);
		super.setIsOwned();
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("The rent is: " +getRent());
	}
	
	//WRITE IN WHAT HAPPENS WHEN YOU LAND ON OR PASS THIS SQUARE
}