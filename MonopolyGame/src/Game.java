import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

class Game {
	//hold the number of players in the game
	private int PlayerNum;
	//generates the game board
	private Board gameArea = new Board();
	//a list of the community chest cards
	private ArrayList<Cards> communityChest = new ArrayList<Cards>();
	//a list of the chance cards
	private ArrayList<Cards> chance = new ArrayList<Cards>();
	//Start Menu for when application first starts up
	private final String[] menu = {"Play a New Game", "Credits", "Exit"};
	//options of the players turn
	private final String[] playerOptions = {"Buy Space","Do not buy space","Build","Sell property","Make offer","End turn","Quit game"};
	//a list of the player
	private ArrayList<Player> gameMembers = new ArrayList<Player>();
	
	public Game()throws IOException
	{
		//creates the card piles
		communityChest = shuffleList(communityChest);
		chance = shuffleList(chance);
	}
	
	private void play()throws IOException
	{
		boolean good = true;
		PlayerNum = ConsoleUI.promptForInt("Please enter the number of players. 2-8", 2,8);
		//creates players based on the number of players
		for(int i=0; i< PlayerNum; i++)
		{
			System.out.print("Player " + (i+1) + " ");
			gameMembers.add(new Player(ConsoleUI.promptForInput("What is your name?",false)));
		}
		// handle the loop of game play
		do{
			
			for(int whoseTurn =0; whoseTurn < gameMembers.size(); whoseTurn++)
			{
				printGame();
				System.out.println (gameMembers.get(whoseTurn).getName() + " it is your turn");
				playerTurn(gameMembers.get(whoseTurn));
			}
			for(Player member: gameMembers)
			{
				System.out.println (member.getName() + " is on " + gameArea.getPosName(member.getPlace()));
			}
			if(gameMembers.size()>1)
			{
				good = ConsoleUI.promptForBool("Is the game still being played? Y/N","y","n");
			}		
		}while(gameMembers.size()>1 && good);
		if(gameMembers.size()==1)
		{
			System.out.println(gameMembers.get(0).getName() + " You have WON");
		}
		startMenu();
	}
	public void playerTurn(Player currentPlayer)throws IOException
	{
		// runs throught the options and possibilities of the players turn
		int turn =1;
		boolean doubles = false;
		int option =0;
		do
		{
			if(option == 0)
			{
				int total =0;
				int die1 = rollDice();
				int die2 = rollDice();
				total = die1+die2;
				doubles = isDoubles(die1,die2);
				System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space "+ currentPlayer.getPlace());
				System.out.println ("You rolled "+ die1 +" and " +die2+ " You move "+ total + " spaces");
				//get not set dumby fix it later 
				//above is covered
				currentPlayer.setPlace(total);
				System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space " +currentPlayer.getPlace());
			}
			//the position value is equal to the square the player is currently on
			Square position = gameArea.getPos(currentPlayer.getPlace());
			if(position.getIsOwned() && !currentPlayer.ownes(position))
			{
				System.out.println ("This space is owned you pay rent of $" + position.getRent() +" dollars");
				currentPlayer.setMoney(-1*position.getRent());
				gameMembers.get(position.getPlayer()-1).setMoney(position.getRent());
				System.out.println (gameMembers.get(position.getPlayer()-1).getName() + " you have been paid rent");
			}
			System.out.println ();
			System.out.println (currentPlayer.getName() + " it is your turn");
			option = ConsoleUI.promptForMenuSelection(playerOptions,false);
			if(gameArea.getPosName(currentPlayer.getPlace()).trim().equalsIgnoreCase("got to jail"))
			{
				//player goes to jail
				System.out.println ("you go to jail");
				currentPlayer.goToPlace(11);
			}
			
			if(option == 1)
			{
				if(!position.getIsOwned() && currentPlayer.getMoney()>=position.getCost())
				{
					currentPlayer.setMoney(-1*position.getCost());
					//Player purchases the space
					currentPlayer.propertyChange(position);
					// player is passed the square they are on
					position.setIsOwned();
					//the square is now owned;
					position.setPlayer(gameMembers.indexOf(currentPlayer)+1);
				}
				else
				{
					System.out.println ("You cannot buy that square");
				}
				//set a boolean within the square to say it is owned and by who
				//check if player can afford it
			}
			else if(option ==2)
			{
				//send current space to auction
				auctionNotBought(position);
			}
			if(doubles && turn!=3 && option == 6)
			{
				System.out.println ("You rolled doubles go again");
				turn++;
				option =0;
			}
			else if(turn ==3 && doubles)
			{
				System.out.println ("You go to jail. You rolled to many doubles");
				currentPlayer.goToPlace(11);
				//create the in jail or just visiting
				doubles = false;
			}
		}while(option <6);
		if(!gameArea.getPos(currentPlayer.getPlace()).getIsOwned() && option ==6)
		{
			auctionNotBought(gameArea.getPos(currentPlayer.getPlace()));
		}
		else if(option == 7)
		{
			System.out.println ("You have quit the game");
			//action all property off
			//remove player from list
			gameMembers.remove(currentPlayer);
		}
	}
	public int rollDice()
	{
		//generate the roll of a six sided die
		int roll =0;
		Random rndm = new Random();
		roll = rndm.nextInt(6)+1;
		return roll;
	}
	
	private boolean isDoubles(int die1, int die2)
	{
		//checks if doubles have been roled
		return die1==die2;
	}
	public void printGame()throws IOException
	{
		//prints the board as well as the players and where they are on the board
		gameArea.showBoard();
	}
	
	public void startMenu()throws IOException{
		int menuOption = ConsoleUI.promptForMenuSelection(menu, false);
		if(menuOption == 1){
			play();
		}
		else if(menuOption == 2){
			System.out.println("Neumont University Fall 2016 Quarter 1");
			System.out.println("Ryan Cox CSC 110: Section A");
			System.out.println("Intro to CS Final Project Group 1 - Monopoly");
			System.out.println("Game programmed by: ");
			System.out.println("Cooper Astle\nColin Borek\nMelissa Buena\nJoshua Carpenter");
			startMenu();
		}
		else{
			System.out.println("Exiting 'Monopoly'...");
			System.out.println("Goodbye!");
		}
	}
	
	public void selectCommunity()
	{
		//may need change to acomidate the get out of jail card leaving the deck
		System.out.println (communityChest.get(0));
		communityChest.add(communityChest.get(0));
		communityChest.remove(0);
		//modifiying player money with value on the card
	}
	public void selectChance()
	{
		//possible needed changes for get out of jail
		System.out.println (chance.get(0));
		chance.add(chance.get(0));
		chance.remove(0);
		//modifiying player money with value on the card
	}
	public ArrayList<Cards> shuffleList(ArrayList<Cards> a){
		Random rand = new Random();
		for(int i = 0; i < a.size(); i++){
			int pullVal = rand.nextInt(a.size());
			Cards copy = a.get(pullVal);
			a.remove(pullVal);
			a.add(rand.nextInt(a.size()), copy);
		}
		return a;
	}
	
	//handles the auctions if a player does not buy the square they landed on
	public void auctionNotBought(Square property)throws IOException
	{
		boolean isBought = false;
		//use a player that holds the current highest bid
		Player highBid = new Player("No one");
		int bid =0;
		//loops through players until bidding ceases
		do
		{
			//control how many people are bidding
			int bidding = gameMembers.size();
			//loops through players offering them the chance to bid
			for(int i=0; i<gameMembers.size();i++)
			{
				int attemptBid =0;
				System.out.println (gameMembers.get(i).getName() + " The current bid is $" + bid +" and is held by "+ highBid.getName());
				boolean isBidding = ConsoleUI.promptForBool("Would you like to place a bid? Y/N", "y","n");
				if(isBidding && gameMembers.get(i).getMoney() > bid)
				{
					attemptBid = ConsoleUI.promptForInt("How much will you bid",bid+1,gameMembers.get(i).getMoney());
				}
				else
				{
					bidding--;
				}
				if(attemptBid > bid)
				{
					String checkBid = "You bid $" + attemptBid + ". Are you sure you want to bid? Y/N";
					if(ConsoleUI.promptForBool(checkBid,"y","n"))
					{
						bid = attemptBid;
						highBid=gameMembers.get(i);
					}
				}
				else
				{
					System.out.println ("You are currently not bidding");
				}
			}
			//when no one is bidding the auction is over
			if(bidding==0)
			{
				isBought=true;
			}
		}while(!isBought || highBid.getName().equalsIgnoreCase("no one"));
		System.out.println (highBid.getName() + " You have purchased " + property.getName() +" for $" + bid);
		//give the winning player the space and retrive the money spent on the bid
		property.setIsOwned();
		property.setPlayer(gameMembers.indexOf(highBid)+1);
		highBid.propertyChange(property);
		highBid.setMoney(-1*bid);
	}
}
