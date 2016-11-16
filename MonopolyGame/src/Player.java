import java.util.ArrayList;

class Player {
	//has any cards that the player may have in their posetion
	private ArrayList<Cards> heldCards = new ArrayList<Cards>();
	//hold players current monetary holdings
	private int money;
	//holds the propertiues that the palyer has
	private ArrayList<Square> ownedAssets = new ArrayList<Square>();
	// some type of thing to hold the piece the player is using most likly an enum
	private String name;
	private int place;
	private boolean inJail =false;
	
	public Player(String isName)
	{
		money = 1500;
		name = isName;
		place = 1;
	}
	
	public boolean isout()
	{
		//checks the money and assets to determine if the player can or cannot continue
		boolean out =false;
		
		return out;
	}
	
	public String getName()
	{
		return name;
	}
	public void setMoney(int change)
	{
		money += change;
	}
	public void goToPlace(int location)
	{
		place = location;
	}
	public void showHoldings()
	{
		//show the current properties held by the player
		for(Square prop: ownedAssets)
		{
			//System.out.println(prop.getSquareInfo());
		}
		//may need changes for cleanliness in the console
	}
	public void propertyChange(Square changed)
	{
		// handles adding the property of removing it depending on the scenario in game
		if(ownedAssets.contains(changed))
		{
			ownedAssets.remove(changed);
		}
		else
			ownedAssets.add(changed);
	}
	
	public void setPlace(int moved)
	{
		place+=moved;
		if(place >40)
		{
			place = place-40;
			System.out.println ("You passed go collect $200");
			setMoney(200);
		}
	}
	public int getPlace()
	{
		return place;
	}
	public int getMoney()
	{
		return money;
	}
	
	public void setInJail()
	{
		inJail = !inJail;
	}
	
	//checks if player ownes the given square
	public boolean ownes(Square check)
	{
		if(ownedAssets.contains(check))
		{
			return true;
		}
		return false;
	}
}
