import java.util.ArrayList;

class Player {
	//has any cards that the player may have in their posetion
	private ArrayList<Cards> heldCards = new ArrayList<Cards>();
	//hold players current monetary holdings
	private int money;
	//holds the propertiues that the palyer has
	//private ArrayList<Property> ownedAssets = new ArrayList<Property>();
	// some type of thing to hold pthe piece the player is using most likly an enum
	
	public Player()
	{
		money = 1500;
	}
	
	public boolean isout()
	{
		//checks the money and assets to determine if the player can or cannot continue
		boolean out =false;
		return out;
	}
	
	public void setMoney(int change)
	{
		money += change;
	}
	
	public void showHoldings()
	{
		//show the current properties held by the player
	}
	public void propertyChange(property changed)
	{
		// handles adding the property of removing it depending on the scenario in game
	}
}
