import java.util.ArrayList;

class CreateCardLists {
	
	public ArrayList<Cards> CommunityChest = new ArrayList<Cards>();
	
	public CreateCardLists()
	{
		addCommChestCards();
	}
	
	public void addCommChestCards(){
		
	 
		CommunityChest.add(new Cards("Doctor's Fee", "Pay $50", -50)); //1 
		CommunityChest.add(new Cards("Inheritance", "You Inherit $100", 10)); //2
		CommunityChest.add(new Cards("Income Tax Refund", "Collect $20", 20)); //3
		CommunityChest.add(new Cards("You are assessed for street repairs", "$40 per house. $115 per hotel", 0)); //4 This card needs extra action to calculate cost depending on # of houses/hotels
		CommunityChest.add(new Cards("Grand Opera Opening", "Collect $50 from every player", 50)); //5 This card needs to be $50 X number of players in the game
		CommunityChest.add(new Cards("Pay School Tax", "$150", -150)); //6
		CommunityChest.add(new Cards("Life Insurance Matures", "Collect $100", 100)); //7
		CommunityChest.add(new Cards("Receive For Services", "$25", 25)); //8
		CommunityChest.add(new Cards("From sale of stock you get: ", "$45", 45)); //9
		CommunityChest.add(new Cards("Won 2nd place in a beauty contest", "Collect $10", 10)); //10
		CommunityChest.add(new Cards("Bank error in your favor", "Collect $200", 200)); //11
		CommunityChest.add(new Cards("Advance to GO", "Collect $200", 200)); //12 This card needs to move player to the GO space
		CommunityChest.add(new Cards("Xmas Fun Matures", "Collect $100", 100)); //13
		CommunityChest.add(new Cards("Go To Jail", "Do not collect $200", 0)); //14 This card needs to move player to the JAIL SPACE
		CommunityChest.add(new Cards("Get out of jail free", "May be kept until needed, or sold.", 0)); //15 This card should allow player to keep this option in their inventory until they use it.
		CommunityChest.add(new Cards("Pay Hospital: ", "$100", -100)); //16
		
		
	}
	
	public ArrayList<Cards> getComChest()
	{
		return CommunityChest;
	}
	}