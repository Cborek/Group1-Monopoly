
class Cards {
	
	private String cardType;
	private String description;
	private String action;
	private int amount;
	private int amountTwo;
	
	public Cards(String type, String name, String todo, int monitary)
	{
		setCardType(type);
		description = name;
		action = todo;
		amount = monitary;
	}	
	
	// CARD constructor for card that requires more than one value. PAY for HOUSE and HOTEL
	public Cards(String type, String name, String todo, int monitary, int monitaryTwo) {
		setCardType(type);
		description = name;
		action = todo;
		amount = monitary;
		amountTwo = monitaryTwo;
	}
	public void getInfo()
	{
		System.out.println (description);
		System.out.println (action);
		if(amount > 0)
		{
			System.out.println ("collect " +amount);
		}
		else if(amount < 0)
		{
			System.out.println ("pay " + amount);
		}
		if(amountTwo!=0) {
			System.out.println("pay " +amountTwo);
		}
			
	}
	public int getAmount()
	{
		return amount;
	}
	// Method to take player to GO square
	public void goToGo(Player currentPlayer) {
		currentPlayer.goToPlace(1);
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String cardType() {
		return getCardType();
	}
}
