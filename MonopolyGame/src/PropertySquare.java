class PropertySquare extends Square {

	public PropertySquare(int location, String name, String color, int cost, int rent, int houseCost,  int withOneHouse,
			int withTwoHouses, int withThreeHouses, int withFourHouses, int withHotel, int mortgage) {
		super(location, name);
		setColor(color);
		setCost(cost);
		setRent(rent);
		setOneHouseRent(withOneHouse);
		setTwoHousesRent(withTwoHouses);
		setThreeHousesRent(withThreeHouses);
		setFourHousesRent(withFourHouses);
		setHotelRent(withHotel);
		setHouseCost(houseCost);
		setMortgage(mortgage);
		
		
	}
	
	public void getInfo(){
		getSquareInfo();
		System.out.println("This color is: " + getColor());
		System.out.println("The cost is: " + getCost());
		System.out.println("The rent is : " + getRent());
		System.out.println("The cost of a building  is: " + getHouseCost());
		System.out.println("Rent with one house: " + getOneHouseRent());
		System.out.println("Rent with two houses: " + getTwoHousesRent());
		System.out.println("Rent with three houses: " + getThreeHousesRent());
		System.out.println("Rent with four houses: " + getFourHousesRent());
		System.out.println("Rent with hotel: " + getHotelRent());
		System.out.println("Mortgage value: " + getMortgage());
	}
	
}