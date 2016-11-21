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
			prop.getSquareInfo();
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
	public ArrayList<Square> getHoldings()
	{
		return ownedAssets;
	}
	
	// gets a single owned property so as to be used for auction or sale
	public Square getProperty(int num)
	{
		return ownedAssets.get(num);
	}
	public int PropertyNum()
	{
		//returns the number of properties currently owned by the player
		return ownedAssets.size();
	}
	
	// a statement to asses if the player can build upon the selected square
	public boolean canBuild(Square curProperty)
	{
		String color = curProperty.getColor();
		boolean good = false;
		if(color.equalsIgnoreCase("white")|| color.equalsIgnoreCase("black"))
		{
			return false;
		}
		if(color.equalsIgnoreCase("blue") || color.equalsIgnoreCase("brown"))
		{
			int owned =0;
			for(Square prop: ownedAssets)
			{
				if(prop.getColor().equalsIgnoreCase(color))
				{
					owned++;
				}
			}
			if(owned == 2)
			{
				good = true;
			} 
		}
		else
		{
			int owned =0;
			for(Square prop: ownedAssets)
			{
				if(prop.getColor().equalsIgnoreCase(color))
				{
					owned++;
				}
			}
			if(owned == 3)
			{
				good = true;
			}
		}
		return good;
	}
	
	public boolean ownesColorGroup(String color)
	{
		boolean good = false;
		if(color.equalsIgnoreCase("blue") || color.equalsIgnoreCase("brown") || color.equalsIgnoreCase("black"))
		{
			int owned =0;
			for(Square prop: ownedAssets)
			{
				if(prop.getColor().equalsIgnoreCase(color))
				{
					owned++;
				}
			}
			if(owned == 2)
			{
				good = true;
			} 
		}
		else if(!color.equalsIgnoreCase("white"))
		{
			int owned =0;
			for(Square prop: ownedAssets)
			{
				if(prop.getColor().equalsIgnoreCase(color))
				{
					owned++;
				}
			}
			if(owned == 3)
			{
				good = true;
			}
		}
		return good;
	}
}
