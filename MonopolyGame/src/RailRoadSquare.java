
class RailRoadSquare extends UtilitySquare {
	
	public RailRoadSquare(int location, String name, String color, int cost, int rent, int rentWithTwo, int rentWithThree, int rentWithFour, int mortgage) {
		super(location, name, color, cost, rent, rentWithTwo, mortgage);
		setRentWithThree(rentWithThree);
		setRentWithFour(rentWithFour);
	}
	
	public void getInfo() {
		getSquareInfo();
		System.out.println("This color is: " + getColor());
		System.out.println("The cost is: " + getCost());
		System.out.println("The rent is : " + getRent());
		System.out.println("Rent with two RR owned: " + getRentWithTwo());
		System.out.println("Rent with three RR owned: " + getRentWithThree());
		System.out.println("Rent with four RR owned: " + getRentWithFour());
		System.out.println("Mortgage value: " + getMortgage());
	}
}
