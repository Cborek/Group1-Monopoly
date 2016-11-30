class Cards {
	
	//setting up variables for parameters of cards
	private String cardType;
	private String description;
	private String action;
	private int amount;
	private int amountTwo;
	
	//cards have these properties
	public Cards(String type, String name, String todo, int monetary)
	{
		setCardType(type);
		description = name;
		action = todo;
		amount = monetary;
	}	
	
	// CARD constructor for card that requires more than one value. PAY for HOUSE and HOTEL
	public Cards(String type, String name, String todo, int monetary, int monetaryTwo) {
		setCardType(type);
		description = name;
		action = todo;
		amount = monetary;
		amountTwo = monetaryTwo;
	}
	//gets info of cards including its description and action correlating with the card
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
	
	//getters to do get the amount of $ player must pay
	public int getAmount()
	{
		return amount;
	}
	public int getAmountTwo() {
		return amountTwo;
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
