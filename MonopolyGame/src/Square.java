package edu.neumont.csc110.a.monopoly;

public class Square {

	private int location;
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
	private int numHouses;
	
	private int player = 0;
	
	// square object for assigning values of a square. Can be assigned in this class or board class
	public static Square square1 = new PropertySquare(2, "Vegas",  "Orange", 200, 1, 4, 4,4,4,4,4,4);
		
		/*
		 * new board
		 * loop(for the size of arrList i++)
		 * takes property at i
		 * build a new square with property
		 */
		//}
	
	public Square( int location, String name) {
		this.location = location;
		this.name = name;
	}

	// prints out info of given square
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
	
	//get location of board on squares
	public int getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	// get cost of square
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	// Player methods will get player number from player class
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	
	// gets color of square
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public void setRent(int rent) {
		this.rent = rent;
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
	public void setHouses(int numHouses) {
		this.numHouses = numHouses;
	}
	
	//Return the cost of adding a house to the property.
	public int getHouseCost(){
		return houseCost;
	}
	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}
	
	//Gets the mortgage of a property
	public int getMortgage(){
		return mortgage;
	}
	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}
	
	
}

class GoSquare extends Square{

	public GoSquare(int location, String name, int cost) {
		super(location, name);
		setCost(cost);
	}
	
}

class railRoadSquare extends Square {
	
	public railRoadSquare(int location, String name, int cost, int mortgage) {
		super(location, name);
		setCost(cost);
		setMortgage(mortgage);
	}
}

class PropertySquare extends Square {

	public PropertySquare(int location, String name, String color, int cost, int rent, int houseCost,  int withOneHouse,
			int withTwoHouses, int withThreeHouses, int withFourHouses, int withHotel, int mortgage) {
		super(location, name);
		setColor(color);
		setCost(cost);
		setRent(rent);
		setHouseCost(houseCost);
		setHouses(getHouses());
		setMortgage(mortgage);
		
		getPlayer();
		
	}
	
}


