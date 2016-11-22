

public class RailRoadCards extends Cards{
	
	private String description;
	private String action;
	private int squareLocation1;
	private int squareLocation2;
	private int squareLocation3;
	private int squareLocation4;

	public RailRoadCards(String type, String name, String todo, int squareValue1, int squareValue2, int squareValue3, int squareValue4) {
		super(type, name, todo, squareValue1);
		setCardType(type);
		description = name;
		action = todo;
		squareLocation1 = squareValue1;
		squareLocation2 = squareValue2;
		squareLocation3 = squareValue3;
		squareLocation4 = squareValue4;
	}
	
	// Loops through 40 squares and checks if value is equal to a RailRoad square
	// and sets the player location equal to said square
	public void moveToRailRoadSquare(Player currentPlayer){
		for (int i=currentPlayer.getPlace();i>0;i++ ) {
			if (i == squareLocation1) {
				currentPlayer.goToPlace(squareLocation1);
				break;
			} else if (i == squareLocation2) {
				currentPlayer.goToPlace(squareLocation2);
				break;
			} else if (i == squareLocation3) {
				currentPlayer.goToPlace(squareLocation3);
				break;
			} else if (i == squareLocation4) {
				currentPlayer.goToPlace(squareLocation4);
				break; 
			} else if (i>40) {
				currentPlayer.setMoney(200); // Money for passing GO
				i=1;
			}
		}
	}
	
	// Gets cardType on card and returns value for comparing in game
			
	public void getInfo()
	{
		System.out.println (description);
		System.out.println (action);			
	}
}
