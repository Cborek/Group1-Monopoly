
import java.util.ArrayList;

class CreateCardLists {
	
	private  ArrayList<Cards> CommunityChest = new ArrayList<Cards>();
	private ArrayList<Cards> Chance = new ArrayList<Cards>();
	public CreateCardLists()
	{
		addCommChestCards();
		addCommunChest();
	}
	
	public void addCommChestCards(){
		
	 
		CommunityChest.add(new Cards("PaymentCard", "Doctor's Fee", "Pay $50", -50)); //1 
		CommunityChest.add(new Cards("PaymentCard", "Inheritance", "You Inherit $100", 10)); //2
		CommunityChest.add(new Cards("PaymentCard", "Income Tax Refund", "Collect $20", 20)); //3
		CommunityChest.add(new Cards("PaymentCard", "You are assessed for street repairs", "$40 per house. $115 per hotel", 0)); //4 This card needs extra action to calculate cost depending on # of houses/hotels
		CommunityChest.add(new Cards("PaymentCard", "Grand Opera Opening", "Collect $50 from every player", 50)); //5 This card needs to be $50 X number of players in the game
		CommunityChest.add(new Cards("PaymentCard", "Pay School Tax", "$150", -150)); //6
		CommunityChest.add(new Cards("PaymentCard", "Life Insurance Matures", "Collect $100", 100)); //7
		CommunityChest.add(new Cards("PaymentCard", "Receive For Services", "$25", 25)); //8
		CommunityChest.add(new Cards("PaymentCard", "From sale of stock you get: ", "$45", 45)); //9
		CommunityChest.add(new Cards("PaymentCard", "Won 2nd place in a beauty contest", "Collect $10", 10)); //10
		CommunityChest.add(new Cards("PaymentCard", "Bank error in your favor", "Collect $200", 200)); //11
		CommunityChest.add(new Cards("PaymentCard", "Advance to GO", "Collect $200", 200)); //12 This card needs to move player to the GO space
		CommunityChest.add(new Cards("PaymentCard", "Xmas Fun Matures", "Collect $100", 100)); //13
		CommunityChest.add(new Cards("PaymentCard", "Go To Jail", "Do not collect $200", 0)); //14 This card needs to move player to the JAIL SPACE
		CommunityChest.add(new Cards("PaymentCard", "Get out of jail free", "May be kept until needed, or sold.", 0)); //15 This card should allow player to keep this option in their inventory until they use it.
		CommunityChest.add(new Cards("PaymentCard", "Pay Hospital: ", "$100", -100)); //16
		
		
	}
	
	public ArrayList<Cards> getComChest()
	{
		return CommunityChest;
	}
	public void addCommunChest() {
		Chance.add(new Cards("PaymentCard", "Bank pays off your dividend.", "Collect $50", 50));
		Chance.add(new Cards("PaymentCard", "Pay poor tax.", "Pay $15", (-15)));
		Chance.add(new Cards("PaymentCardMultiplier", "You have been elected chairman of the board.", "Pay each player $50", -50));
		Chance.add(new Cards("GetOutOfJail", "Get out of jail free.", "This card may be kept until needed, or sold", 0)); // INT NOT NEEDED
		Chance.add(new Cards("PaymentCardWithTwo", "Make general repairs on all your property", "For each house pay $25 and for each hotel $100", 25, 100));
		
		// LOCATION CARDS
		Chance.add(new LocationCards("LocationCard", "", "Go back three spaces", -3));
		Chance.add(new LocationCards("LocationCard", "", "Advance to Illinois Ave.", 25)); // ILLINOIS AVE on square 25
		Chance.add(new LocationCards("LocationCard", "Advance to St. Charles Place.", "If you pass go, collect $200", 12)); // ST. CHARLES PLACE on square 12
		Chance.add(new LocationCards("LocationCard", "Take a ride on the Reading.", "If you pass go, collect $200", 6)); // READING RAILROAD on square 6
		Chance.add(new LocationCards("LocationCard", "Take a walk on the Boardwalk.", "Advance token to Boardwalk", 40)); // BOARDWALK on square 40
		Chance.add(new LocationCards("LocationCard", "Go directly to jail.", "Do not pass go, do not collect $200", 11)); // JAIL on square 11
		Chance.add(new LocationCards("LocationCard", "Advance to go.", "Collect $200", 1)); // GO on square 1
		
		// UTILITY CARDS
		Chance.add(new UtilityCards("UtilityCard", "ADVANCE TOKEN TO NEAREST UTILITY.", 
				"IF UNOWNED, you may buy it from the Bank. IF OWNED, "
				+"throw dice and pay owner a total ten times the amount thrown", 13, 29)); 
		// RAILROAD CARDS
		Chance.add(new RailRoadCards("RailRoadCard", "Advance token to the nearest Railroad and pay owner Twice the Rental to which he/she is otherwise entitled.", 
				"If Railroad is UNOWNED, you may buy it from the Bank", 6, 16, 26, 36)); 
		Chance.add(new RailRoadCards("RailRoadCard", "Advance token to the nearest Railroad and pay owner Twice the Rental to which he/she is otherwise entitled.", 
				"If Railroad is UNOWNED, you may buy it from the Bank", 6, 16, 26, 36)); 
		
		
		}
		public ArrayList<Cards> getChance()
		{
			return Chance;
		}
	}