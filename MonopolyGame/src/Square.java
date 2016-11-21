public class Square {

	private int location; 		//Location of Square on board
	private String name; 		//Name of the Square
	private String color; 		//Color value of the square
	private int cost; 			//The cost to buy the square
	private int mortgage;		//The mortgage cost of the square
	private int rent;			//The rent for landing on a square
	private int houseCost;		//The cost to buy a house
	private int oneHouse;		//The rent if property has ONE house
	private int twoHouses;		//The rent if property has TWO houses
	private int threeHouses;	//The rent if property has THREE house
	private int fourHouses;		//The rent if property has FOUR houses
	private int hotelRent;		//The rent if property has a hotel
	private int numHouses = 0;	//The number of houses on each square
	private int rentWithTwo;	//The rent for owning TWO squares with same color    ie. Utilities and RailRoad
	private int rentWithThree;	//The rent for owning THREE squares with same color
	private int rentWithFour;	//The rent for owning FOUR squares with same color
	private int player = 0;		//PLAYER NUMBER  IS NOT USED YET IN THIS CLASS  and  MAY NOT BE USED
	private String contents;	//Holds the string contents in the blank squares in the board
	private boolean isOwned;   //tells if the squared is owned and can be purchased
	
	//Square constructor for BLANK SQUARES
	//I would rename this but we have already assigned all of our blank squares with the new Square() constructor   
	public Square(String contents){
		this.contents = contents;
		isOwned = true;
	}
	public String getContents() {
		return contents;
	}
	
	
	//Square Constructor for BOARD SQUARES   All squares on the board will have AT LEAST these two values
	public Square( int location, String name) {
		this.location = location;
		this.name = name;
		isOwned =false;
	}
	

	// Prints out basic info of square that is shared between all squares
	public void getSquareInfo() {
		System.out.println("The location is: " +location);
		System.out.println("The Name is: " + name);
	}
	
	
	// Gets location of board on squares
	public int getLocation() {
		return location;
	}
	// Gets name of board on squares
	public String getName() {
		return name;
	}


	// get cost to buy the square
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	
	// Player methods will get player number from player class 
	//IS NOT USED YET  and  MAY NOT BE USED
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	
	
	
	// Gets color of square
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}	
	
	
	// Returns the cost of rent when a player lands on an a property square depending on the number of houses they own.
	// getRent is also used to get basic rent of squares that do not have houses  ie.  Utilities, RailRoad, GoSquare rent will add money instead of subtract   
	public int getRent(){	
		getHouses();
		if(numHouses == 0){
			return rent;
		}
		if(numHouses == 1){
			rent = getOneHouseRent();
		}
		if(numHouses == 2){
			rent = getTwoHousesRent();
		}
		if(numHouses == 3){
			rent = getThreeHousesRent();
		}
		if(numHouses == 4){
			rent = getFourHousesRent();
		}
		if(numHouses == 5){
			rent = getHotelRent();
		}
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	
	
	// Gets rent depending on the number of houses they have
	// The returned values will then used in the getRent method to set values equal to rent
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
	
	
	// Method to add number of houses to a square
	public void addHouse(){
		if (numHouses<4){
		numHouses++;
		}
	}
	//Method to subtract number of houses from a square
	public void subtractHouse(){
		if (numHouses>0) {
			numHouses--;
		}
	}
	// adds a hotel to the square and sets number of houses to 0
	public void addHotel(){
		numHouses = 0;
		rent = hotelRent;
	}
	//Return the number of houses that a property has on it.
	public int getHouses(){
		return numHouses;
	}
	
	
	//Return the cost of adding a house to the property.
	public int getHouseCost(){
		return houseCost;
	}
	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}
	
	
	// Gets rent for owning 2,3,or 4 squares    Two for the utilities squares    Four for the RailRoads
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
	
	//sets and get the isOwned boolean
	public boolean getIsOwned()
	{
		return isOwned;
	}
	
	public void setIsOwned()
	{
		isOwned =!isOwned;
	}
}