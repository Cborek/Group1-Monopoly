
public class Bank {
	private int amount;
	private int numHouses = 32;
	private int numHotels = 12;
	
	//Gives the player a set amount of money. This is dictated by community chest and chance cards;
	public int giveMoney(int theAmount){
		amount = theAmount;
		return amount	;
	}
	
	//Takes an amount of money from the player. The amount taken is determined by a community chest or chance card.
	public int takeMoney(int theAmount){
		amount = theAmount;
		return amount;
	}
	
	//Gives a player 200 dollars. Used when a player passes GO.
	public int paySalary(){
		return 200;
	}
	
	//Takes cost of x amount of houses from the player.
	public int giveHouses(int num, int costPerHouse){
		int  totalCost = num * costPerHouse;
		numHouses -= num;
		if(numHouses == 0){
			System.out.println("There are no houses left to be purchased.");
			return 0;
		}
		else{
			return totalCost;
		}
	}
	
	public int giveHotel(int hotelCost){
		numHouses += 4;
		numHotels--;
		if(numHotels == 0){
			System.out.println("There are no hotels left to be purchased.");
			return 0;
		}
		else{
			return hotelCost;
		}
	}
}
