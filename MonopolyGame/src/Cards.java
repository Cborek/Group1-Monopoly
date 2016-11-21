class Cards {
	
	private String description;
	private String action;
	private int amount;
	public Cards(String name, String todo, int monitary)
	{
		description =name;
		action = todo;
		amount = monitary;
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
			System.out.println ("pay " + (amount*(-1)));
		}
			
	}
	public int getAmount()
	{
		return amount;
	}
}