package edu.neumont.csc110.a.monopoly;

public class CardSquare extends Square {
	boolean chance;
	public CardSquare(int location, String name) {
		
		super(location, name);
		if (name.equalsIgnoreCase("Chance")) {
			//CODE TO CHOOSE CHANCE CARDS
		} else if(name.equalsIgnoreCase("Community Chest")){
			//CODE TO CHOOSE COMMUNITY CHEST
		}
	}
	
	public void getInfo() {
		getSquareInfo();
	}
	
}
