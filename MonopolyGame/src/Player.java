import java.util.ArrayList;

class Player {
	//has any cards that the player may have in their posetion
	private ArrayList<Cards> heldCards = new ArrayList<Cards>();
	//hold players current monetary holdings
	private int money;
	//holds the propertiues that the palyer has
	private ArrayList<Square> ownedAssets = new ArrayList<Square>();
	private ArrayList<Square>mortgagedProperties = new ArrayList<Square>();
	// some type of thing to hold the piece the player is using most likly an enum
	private boolean jailCard = false;
	private String name;
	private int place;
	private boolean inJail =false;
	private int jailTurns =0;
	private int playerPiece=0;
	
	//sets the starting amount of money to $1500
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
		if(money<=0 && ownedAssets.size()==0&&mortgagedProperties.size()>=0)
		{
			out = true;
		}
		return out;
	}
	
	public int getPlayerValue()
	{
		int worth =0;
		worth += money;
		if(ownedAssets.size()>0)
		{
			for(Square Prop: ownedAssets)
			{
				worth += Prop.getCost();
				worth+= Prop.getHouses()*Prop.getHouseCost();
				if(Prop.getHotel())
				{
					worth += Prop.getHouseCost();
				}
			}
		}
		if(mortgagedProperties.size()>0)
		{
			for(Square prop: mortgagedProperties)
			{
				worth += prop.getMortgage();
			}
		}
		return worth;
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
	
	
	//if player passes space 40 that means they passed go and can collect $200
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
	
	public int getCardsNum()
	{
		return heldCards.size();
	}
	
	public Cards getCard()
	{
		return heldCards.get(0);
	}
	
	
	//heldCards deal with any cards that the player currently owns
	public void setCard(Cards newCard)
	{
		if(heldCards.contains(newCard))
		{
			heldCards.remove(newCard);
		}
		else
			heldCards.add(newCard);
	}
	
	public void setJailCard() {
		if (!(getCard()==null)){
			jailCard = true;
		} else {
			jailCard = false;
		}
	}
	public boolean getJailCard() {
		return jailCard;
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
	public Square[] ableToSell()
	{
		//creates a list of proprties that have houses that can be sold
		ArrayList<Square> withBuildings = new ArrayList<Square>();
		for(int i=0; i< withBuildings.size(); i++)
		{
			if(ownedAssets.get(i).getHouses()>0)
			{
				withBuildings.add(ownedAssets.get(i));
			}
			else if(ownedAssets.get(i).getHotel())
			{
				withBuildings.add(ownedAssets.get(i));
			}
		}
		
		//finish with 
		Square[] buildings = (Square[])withBuildings.toArray();
		return buildings;
	}
	public String[] getHoldings()
	{
		String[] playerProps = new String[ownedAssets.size()];
		for(int i=0; i<playerProps.length;i++)
				{
					playerProps[i]=ownedAssets.get(i).getName();
				}
		return playerProps;
	}
	// a statement to asses if the player can build upon the selected square
	public boolean canBuildHouse(Square curProperty)
	{
		String color = curProperty.getColor();
		boolean good = false;
		if(color.equalsIgnoreCase("white")|| color.equalsIgnoreCase("black"))
		{
			return false;
		}
		else
		{
			good = ownesColorGroup(color);
		}
		// at this point if the player owns the entire group of the curProperty's color good will be true
		// the even building across properties must be checked for houses
		if(good)
		{
			for(int i=0; i<ownedAssets.size();i++)
			{
				if(color.equalsIgnoreCase(ownedAssets.get(i).getColor())&&curProperty != ownedAssets.get(i))
				{
					if((Math.abs((curProperty.getHouses()+1)-ownedAssets.get(i).getHouses()) >1)&&curProperty.getHouses()<4)
					{
						good = true;
					}
					else
					{
						System.out.println ("You can not yet build a house on this property" + curProperty.getName());
						good = false;
						break;
					}
					
				}
			}
		}
		return good;
	}
	
	//allows player to sell houses
	public boolean canSellHouse(Square curProperty)
	{
		String color = curProperty.getColor();
		boolean good=false;
		for(int i=0; i<ownedAssets.size();i++)
			{
				if(color.equalsIgnoreCase(ownedAssets.get(i).getColor())&&curProperty != ownedAssets.get(i))
				{
					if((Math.abs((curProperty.getHouses()-1)-ownedAssets.get(i).getHouses()) >1)&&curProperty.getHouses()<4)
					{
						good = true;
					}
					else
					{
						System.out.println ("You can not yet sell a house on this property" + curProperty.getName());
						good = false;
						break;
					}
					
				}
			}
			return good;
	}
	public boolean canSellHotel(Square curProperty)
	{
		boolean good =false;
		String color = curProperty.getColor();
		for(int i=0; i<ownedAssets.size();i++)
			{
				if(color.equalsIgnoreCase(ownedAssets.get(i).getColor()) && ownedAssets.get(i)!=curProperty)
				{
					if(((ownedAssets.get(i).getHouses() == 4 ) || (ownedAssets.get(i).getHotel())) &&  curProperty.getHotel())
					{
						good = true;
					}
					else
					{
						System.out.println ("You may not yet sell a hotel on this square");
						good = false;
						break;
					}
					
				}
			}
		return  good;
	}
	public boolean canBuildHotel(Square curProperty)
	{
		boolean good =false;
		String color = curProperty.getColor();
		for(int i=0; i<ownedAssets.size();i++)
			{
				if(color.equalsIgnoreCase(ownedAssets.get(i).getColor()) && ownedAssets.get(i)!=curProperty)
				{
					if((ownedAssets.get(i).getHouses() == 4 && curProperty.getHouses() == 4) || (ownedAssets.get(i).getHotel()))
					{
						good = true;
					}
					else
					{
						System.out.println ("You may not yet build a hotel on this square");
						good = false;
						break;
					}
					
				}
			}
		if(curProperty.getHotel())
		{
			System.out.println ("This square has a hotel");
			good = false;
		}
		return good;
	}
	
	public boolean ownesColorGroup(String color)
	{
		// this method if for the double rent case is a certain player owns the entire color group aka a monopoly
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
		else if(color.equalsIgnoreCase("white"));
		{
			int owned =0;
			for(Square prop: ownedAssets)
			{
				if(prop.getColor().equalsIgnoreCase(color))
				{
					owned++;
				}
			}
			if(owned == 4)
			{
				good = true;
			}
		}
		return good;
	}
	// gets a single owned property so as to be used for auction or sale
	public Square getProperty(int num)
	{
		return ownedAssets.get(num);
	}
	
	public boolean isInJail()
	{
		return inJail;
	}
	
	public void incJailTurns()
	{
		jailTurns++;
	}
	
	public int getJailTurns()
	{
		return jailTurns;
	}
	
	public void setJailTurns(int num)
	{
		jailTurns = num;
	}
	
	public void removeProperty(Square toRem){
		ownedAssets.remove(toRem);
	}
	
	public int PropertyNum()
	{
		//returns the number of properties currently owned by the player
		return ownedAssets.size();
	}
	//Adds a property to the mortgagesProperties
	public void addMortgagedProperty(Square a){
		mortgagedProperties.add(a);
	}
	
	public void getMortgagedProperties(){
		for(int i = 0; i < mortgagedProperties.size(); i++){
			System.out.println(mortgagedProperties.get(i));
		}
	}
	public int getTotalHouses() {
 		int houseNum = 0;
 		for (int i=0;i>PropertyNum();i++) {
 			Square propertyNum = getProperty(i);
 			houseNum =+ propertyNum.getHouses();
 		}
 		return houseNum;
 	}
 	public int getTotalHotels() {
 		int hotelNum = 0;
 		for (int i=0;i>PropertyNum();i++) {
 			Square propertyNum = getProperty(i);
 			if (propertyNum.getHotel()) {
 				hotelNum++;
 			}
 		}
 		return hotelNum;
 	}
 	
 	public Square getMortgagedProperty(int a){
 		return mortgagedProperties.get(a);
 	}
 	
 	public int getMortgagedPropertyLocation(Square a){
 		return mortgagedProperties.indexOf(a);
 	}
 	
 	public int getMortgageValue(int a){
 		double mortgageValue = mortgagedProperties.get(a).getMortgage() * .10;
 		int theMortgageVal = (int)mortgageValue + mortgagedProperties.get(a).getMortgage();
 		return theMortgageVal;
 	}
 	public void removeMortgagedPropertty(Square a){
 		ownedAssets.add(a);
 		mortgagedProperties.remove(a);
  	}
  	public int numMortgagedPoperties(){
 		return mortgagedProperties.size();
 	}
 	public void setPlayerPieceInt(int num)
 	{
 		playerPiece= num;
 	}
 	public int getPlayerPieceInt()
 	{
 		return playerPiece;
 	}
 	
}
