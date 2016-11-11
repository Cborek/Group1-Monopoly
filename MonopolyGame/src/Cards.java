
class Cards {
	
	private String description;
	private String action;
	public Cards(String name, String todo)
	{
		description =name;
		action = todo;
	}
	public void getInfo()
	{
		System.out.println (description);
		System.out.println (action);
	}
}
