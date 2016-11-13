import java.util.ArrayList;

class Player {
	//has any cards that the player may have in their posetion
	private ArrayList<Cards> heldCards = new ArrayList<Cards>();
	//hold players current monetary holdings
	private int money;
	//holds the propertiues that the palyer has
	private ArrayList<Properties> ownedAssets = new ArrayList<Properties>();
	// some type of thing to hold the piece the player is using most likly an enum
	private String name;
	
	public Player(String isName)
	{
		money = 1500;
		name = isName;
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
		for(Properties prop: ownedAssets)
		{
			System.out.println (prop.getInfo());
		}
		//may need changes for cleanliness in the console
	}
	public void propertyChange(properties changed)
	{
		// handles adding the property of removing it depending on the scenario in game
		if(ownedAssets.contains(changed))
		{
			ownedAssets.remove(changed);
		}
		else
			ownedAssets.add(changed);
	}
}
