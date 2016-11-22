

public class UtilityCards extends Cards{
	
	private String description;
	private String action;
	private int squareLocation1;
	private int squareLocation2;
	
	public UtilityCards(String type, String name, String todo, int squareValue1, int squareValue2) {
		super(type, name, todo, squareValue1);
		setCardType(type);
		squareLocation1 = squareValue1;
		squareLocation2 = squareValue2;
	}
	
	public void moveToUtilitySquare(Player currentPlayer){
		// Loops through 40 squares and checks if value is equal to a utility square
		// and sets the player location equal to said square
		for (int i=currentPlayer.getPlace();i>0;i++ ) {
			if (i == squareLocation1) {
				currentPlayer.goToPlace(squareLocation1);
				break;
			} else if (i == squareLocation2) {
				currentPlayer.goToPlace(squareLocation2);
				break;
			} else if (i>40) {
				currentPlayer.setMoney(200);
				i=1;
			}
		}
	}
	
	public void getInfo()
	{
		System.out.println (description);
		System.out.println (action);			
	}
	
}
