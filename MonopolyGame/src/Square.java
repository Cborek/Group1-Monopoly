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
	private int hotelRent;
	private int numHouses;
	private int rentWithTwo;
	private int rentWithThree;
	private int rentWithFour;
	private int player = 0;
	
	// square object for assigning values of a square. Can be assigned in this class or board class
	public static Square square1 = new PropertySquare(2, "Vegas",  "Orange", 200, 1, 2, 3,4,5,6,7,8, 1);
	
	public Square( int location, String name) {
		this.location = location;
		this.name = name;
	}

	// prints out info of property square
	public void getSquareInfo() {
		System.out.println("The location is: " +location);
		System.out.println("The Name is: " + name);
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

	// get cost to buy the square
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
	public int getRent(int numHouses){
//		if(numHouses == 1){
//			rent = oneHouse;
//		}
//		if(numHouses == 2){
//			rent = twoHouses;
//		}
//		if(numHouses == 3){
//			rent = threeHouses;
//		}
//		if(numHouses == 4){
//			rent = fourHouses;
//		}
//		if(numHouses == 5){
//			addHotel(hotel);
//		}
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	
	// Gets rent depending on the number of houses they have
	public void setOneHouseRent(int oneHouse) {
		this.oneHouse = oneHouse;
	}
	public int getOneHouseRent() {
		return oneHouse;
	}
	
	public void setTwoHousesRent(int twoHouses) {
		this.twoHouses = twoHouses;
	}
	public int getTwoHousesRent() {
		return twoHouses;
	}
	
	public void setThreeHousesRent(int threeHouses) {
		this.threeHouses = threeHouses;
	}
	public int getThreeHousesRent() {
		return threeHouses;
	}
	
	public void setFourHousesRent(int fourHouses) {
		this.fourHouses = fourHouses;
	}
	public int getFourHousesRent() {
		return fourHouses;
	}
	
	public void setHotelRent(int hotel) {
		this.hotelRent = hotel;
	}
	public int getHotelRent() {
		return hotelRent;
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
	
	// Gets rent for owning 2,3,or 4 squares
	public void setRentWithTwo(int rentWithTwo) {
		this.rentWithTwo = rentWithTwo;
	}
	public int getRentWithTwo() {
		return rentWithTwo;
	}
	
	public void setRentWithThree(int rentWithThree) {
		this.rentWithThree = rentWithThree;
	}
	public int getRentWithThree() {
		return rentWithThree;
	}
	
	public void setRentWithFour(int rentWithFour) {
		this.rentWithFour = rentWithFour;
	}
	public int getRentWithFour() {
		return rentWithFour;
	}
	
	//Gets the mortgage of a property
	public int getMortgage(){
		return mortgage;
	}
	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}
	
	
}


