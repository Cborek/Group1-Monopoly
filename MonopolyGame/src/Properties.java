public class Properties {
	private String name;
	private String color;
	private int cost;
	private int mortgage;
	private int rent;
	private int houseCost;
	private int oneHouse;
	private int twoHouses;
	private int threeHouses;
	private int fourHouses;
	private int hotel;
	private int numHouses = 0;
	
	public Properties(String theName, String theColor, int theCost, int theMortgage, int theRent, int theHouseCost,  int withOneHouse,
			int withTwoHouses, int withThreeHouses, int withFourHouses, int withHotel){
		name = theName;
		color = theColor;
		cost = theCost;
		mortgage = theMortgage;
		rent = theRent;
		houseCost = theHouseCost;
		oneHouse = withOneHouse;
		twoHouses = withTwoHouses;
		threeHouses = withThreeHouses;
		fourHouses = withFourHouses;
		hotel = withHotel;
	}
	
	//Returns the name of the property.
	public String getName(){
		return name;
	}
	
	//Returns the properties color.
	public String getColor(){
		return color;
	}
	//Returns the cost of purchasing this property.
	public int getCost(){
		return cost;
	}
	
	//Returns the cost of rent when a player lands on this property.
	public int getRent(){
		if(numHouses == 1){
			rent = oneHouse;
		}
		if(numHouses == 2){
			rent = twoHouses;
		}
		if(numHouses == 3){
			rent = threeHouses;
		}
		if(numHouses == 4){
			rent = fourHouses;
		}
		if(numHouses == 5){
			addHotel(hotel);
		}
		return rent;
	}
	
	//Sets the number of houses that a property has on it and the properties modified cost depending on the number of houses. Number of houses is zero by default. 
	public void addHouses(int theNumHouses, int theRent){
		numHouses = theNumHouses;
		rent = theRent;
	}
	
	public void addHotel(int hotelRent){
		numHouses = 0;
		rent = hotelRent;
	}
	//Return the number of houses that a property has on it. Five houses is a hotel.
	public int getHouses(){
		return numHouses;
	}
	
	//Return the cost of adding a house to the property.
	public int getHouseCost(){
		return houseCost;
	}
	
	public int getMortgage(){
		return mortgage;
	}
	
	//Returns all of the information for this property.
	public void getInfo(){
		System.out.println("The Name is: " + name);
		System.out.println("This color is: " + color);
		System.out.println("The cost is: " + cost);
		System.out.println("The rent is : " + rent);
		System.out.println("The cost of a building  is: " + houseCost);
		System.out.println("Rent with one house: " + oneHouse);
		System.out.println("Rent with two houses: " + twoHouses);
		System.out.println("Rent with three houses: " + threeHouses);
		System.out.println("Rent with four houses: " + fourHouses);
		System.out.println("Rent with hotel: " + hotel);
		System.out.println("Mortgage value: " + mortgage);
	}
}