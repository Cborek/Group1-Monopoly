public class CardSquare extends Square {
	boolean chance;
	public CardSquare(int location, String name) {
		
		super(location, name);
		if (name.equalsIgnoreCase("Chance")) {
			//CODE TO CHOOSE CHANCE CARDS
		} else if(name.equalsIgnoreCase("Community Chest")){
			//CODE TO CHOOSE COMMUNITY CHEST
		}
		super.setIsOwned();
	}
	
	public void getInfo() {
		getSquareInfo();
	}
	
}