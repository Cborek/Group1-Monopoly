

public class LocationCards extends Cards{
	
	private String description;
	private String action;
	private int location;
	
	public LocationCards(String type, String name, String todo, int value){
		super(type, name, todo, value);
		setCardType(type);
		description = name;
		action = todo;
		location = value;
	}
	
	// Method that determines whether the location value is positive -- takes you to number of square
	// If it is negative, that means the user moves back the number of spaces specified.
	public void moveToLocationSquare(Player currentPlayer) {
		if (location>0) {
			if (currentPlayer.getPlace()>location) {
				currentPlayer.setMoney(200);
			}
			currentPlayer.goToPlace(location);
		} else if (location<0) {
			int moveTo =currentPlayer.getPlace()+location;
			if (moveTo<=0) {
				moveTo+=40;
			}
			currentPlayer.goToPlace(moveTo);
		}
	}
	
	// Gets cardType on card and returns value for comparing in game
		
		
		// Prints out action on card  does not need name or int value
		public void getInfo() {
			System.out.println(description);
			System.out.println(action);
		}
	
}
