import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

class Game {
	//generates the game board
	private Board gameArea = new Board();
	//a list of the community chest cards
	private ArrayList<Cards> communityChest = new ArrayList<Cards>();
	//a list of the chance cards
	private ArrayList<Cards> chance = new ArrayList<Cards>();
	//Start Menu for when application first starts up
	private final String[] menu = {"Play a New Game", "Credits", "Exit"};
	//options of the players turn
	private final String[] playerOptions = {"Buy Space","Do not buy space","Build","Sell property","Make offer","Mortgage","End turn","Quit game", "Show my propreties"};
	//a list of the player
	private ArrayList<Player> gameMembers = new ArrayList<Player>();
	private CreateCardLists cardLists = new CreateCardLists();
	public Game()throws IOException
	{
		//creates the card piles
		communityChest = cardLists.getComChest();
		communityChest = shuffleList(communityChest);
		chance =cardLists.getChance();
		chance = shuffleList(chance);
	}
	
	private void play()throws IOException
	{
		boolean good = true;
		int PlayerNum = ConsoleUI.promptForInt("Please enter the number of players. 2-8", 2,8);
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
				//gives players the option of ending the entire game without a winner
				good = ConsoleUI.promptForBool("Is the game still being played? Y/N","y","n");
			}		
		}while(gameMembers.size()>1 && good);
		//win condition
	
		if(gameMembers.size()==1)
		{
			System.out.println(gameMembers.get(0).getName() + " You have WON");
		}
		startMenu();
	}
	private void playerTurn(Player currentPlayer)throws IOException
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
			
			if(position.getIsOwned() && !currentPlayer.ownes(position)&& position.getRent()>0 )
			{
				payRent(position, currentPlayer);
			}
			else if(position.getName().equalsIgnoreCase("chance") || position.getName().equalsIgnoreCase("community Chest"))
			{
				if(position.getName().equalsIgnoreCase("chance"))
				{
					position = selectChance(currentPlayer,position);
				}
				else
					position = selectCommunity(currentPlayer,position);
			}
			System.out.println ();
			System.out.println (currentPlayer.getName() + " it is your turn");
			option = ConsoleUI.promptForMenuSelection(playerOptions,false);
			uniqueSquares(position,currentPlayer);
			if(option == 1)
			{
				option1(position, currentPlayer);
			}
			else if(option ==2 )
			{
				if(!position.getIsOwned())
				{
					option2DoNotBuy(position,currentPlayer);
				}
				else
				{
					System.out.println ("That is not an option on this square");
				}
			}
			else if(option == 3)
			{
				option3Build(position,currentPlayer);
			}
			else if(option ==4)
			{
				option4SellProp(currentPlayer);
			}
			else if(option == 5)
			{
				option5makeOffer(currentPlayer);
			}
			else if(option == 6)
			{
				option6Mortgage(currentPlayer);

			}
			else if(option ==9)
			{
				currentPlayer.showHoldings();
				option = -1;
			}
			if(doubles && turn!=3 && option == 7)
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
		}while(option <7);
	
		if(!gameArea.getPos(currentPlayer.getPlace()).getIsOwned() && option ==7)
		{
			auctionNotBought(gameArea.getPos(currentPlayer.getPlace()));
		}
		
		else if(option == 8)
		{
			Option8quitGame(currentPlayer);
		}
	}
	
	private void payRent(Square position, Player currentPlayer)
	{
		// need to add what is done for owneing multiple things of the same color as well as railroads and utilities
		System.out.println ("This space is owned you pay rent of $" + position.getRent() +" dollars");
		currentPlayer.setMoney(-1*position.getRent());
		gameMembers.get(position.getPlayer()-1).setMoney(position.getRent());
		System.out.println (gameMembers.get(position.getPlayer()-1).getName() + " you have been paid rent");
	}
	//handle the squares you cant buy
	private void uniqueSquares(Square position , Player currentPlayer)
	{
			if(gameArea.getPosName(currentPlayer.getPlace()).trim().equalsIgnoreCase("got to jail"))
			{
				//player goes to jail
				System.out.println ("you go to jail");
				currentPlayer.goToPlace(11);
			}
			
	}

	private void option1(Square position , Player currentPlayer)
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
					System.out.println ("You have bought " + position.getName());
				}
				else
				{
					System.out.println ("You cannot buy that square");
				}
				//set a boolean within the square to say it is owned and by who
				//check if player can afford it
	}

	private void option2DoNotBuy(Square position , Player currentPlayer)throws IOException
	{
		//send current space to auction
		auctionNotBought(position);
	}

	private void option3Build(Square position , Player currentPlayer)throws IOException
	{
		String[] playerProperty = new String[currentPlayer.getHoldings().size()];
		String[] BuildOp= {"Build house","Build Hotel"};
				currentPlayer.showHoldings();
				for(int i=0; i<playerProperty.length;i++)
				{
					playerProperty[i]=currentPlayer.getHoldings().get(i).getName();
				}
				//checks is player has ability to build a house
				int propToSell = ConsoleUI.promptForMenuSelection(playerProperty,false);
				if(currentPlayer.canBuild(currentPlayer.getProperty(propToSell)))
				{
					int choice = ConsoleUI.promptForMenuSelection(BuildOp,false);
					//handle the even building of houses and if they can or can not build a hotel
					
				}
	}
	
	private void option4SellProp(Player currentPlayer)
	{
		// has the user select if they want to sell a property or a card
		//and if they want to sell to a specific player or not 
		//then handle the selling from there
		//if it is a property and they do not want to sell specifically put it in auction
		//else send to trade method
	}

	private void option5makeOffer(Player currentPlayer)
	{
		// handles the player offering to buy from other players any property
		//utilize the trade method after asking a player who they would like to trade with
		//
	}

	private void option6Mortgage(Player currentPlayer) throws IOException
	{
		/*Handles the mortgaging of a chosen square
		 *player has stuff to get a list of owned properties and certain values and such
		 */
		boolean mortgaging = true;
		String propertyNames[] = new String[currentPlayer.PropertyNum()];
		for(int i = 0; i < currentPlayer.PropertyNum(); i++){
			String currentProperty = currentPlayer.getProperty(i).getName();
			propertyNames[i] = currentProperty +  " Mortgage: " + currentPlayer.getProperty(i).getMortgage();
		}
		do{
			System.out.println("Please select the property that you would like to mortgage.");
			int selection = ConsoleUI.promptForMenuSelection(propertyNames, false);
			
			Square propertyToMortgage = currentPlayer.getProperty(selection - 1);
			if(propertyToMortgage.getHouses() == 0){
				propertyToMortgage.setIsMortgaged();
				currentPlayer.addMortgagedProperty(propertyToMortgage);
				currentPlayer.setMoney(propertyToMortgage.getMortgage());
				currentPlayer.removeProperty(propertyToMortgage);
				System.out.println("This property has been mortgaged.");
			}
			else{
				System.out.println("That property currently has building(s) on it. All buildings need to be sold before a property can be mortgaged.");
			}
			
			mortgaging = ConsoleUI.promptForBool("Would you like to mortgage another property? (Y/N)", "Y", "N"); 
			
		}while(mortgaging);
		System.out.println("Properties which you have mortgaged.");
		currentPlayer.getMortgagedProperties();
	}
	
	//option 7 does not need a method so this skips to option 8
	private void Option8quitGame(Player currentPlayer)throws IOException
	{
		System.out.println ("You have quit the game");
			//action all property off
			currentPlayer.setMoney(-1*currentPlayer.getMoney());
			
			for(int i=0; i<=currentPlayer.PropertyNum();i++)
			{
				System.out.println ();
				System.out.println (currentPlayer.getName() + " you may not participate in this auction. You are quiting and have forfieted all your money");
				auctionNotBought(currentPlayer.getProperty(0));
				currentPlayer.propertyChange(currentPlayer.getProperty(0));
			}
			//remove player from list
			gameMembers.remove(currentPlayer);
	}
	
	private int rollDice()
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
	
	public Square selectCommunity(Player  currentPlayer, Square position)
	{
		//may need change to acomidate the get out of jail card leaving the deck
		System.out.println (communityChest.get(0));
		communityChest.add(communityChest.get(0));
		communityChest.remove(0);
		//modifiying player money with value on the card
		//return the square the player is on eeven if they did not move
		return position;
	}

	private Square selectChance(Player currentPlayer, Square position)
	{
		//possible needed changes for get out of jail
		System.out.println (chance.get(0));
		chance.add(chance.get(0));
		chance.remove(0);
		//modifiying player money with value on the card
		//return the square the player is on even if they did not move
		return position;
	}

	private ArrayList<Cards> shuffleList(ArrayList<Cards> a){
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
	private void auctionNotBought(Square property)throws IOException
	{
		boolean isBought = false;
		//use a player that holds the current highest bid
		Player highBid = new Player("No one");
		int bid =0;
		System.out.println (property.getName() + " is up for Auction");
		//loops through players until bidding ceases
		do
		{
			//control how many people are bidding
			int bidding = gameMembers.size();
			//loops through players offering them the chance to bid
			for(int i=0; i<gameMembers.size();i++)
			{
				int attemptBid =0;
				boolean isBidding = false;
				if(highBid != gameMembers.get(i))
				{
					System.out.println (gameMembers.get(i).getName() + " The current bid is $" + bid +" and is held by "+ highBid.getName());
					isBidding = ConsoleUI.promptForBool("Would you like to place a bid? Y/N", "y","n");
				}
				else
				{
					bidding--;
				}
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
	
	private void trade(Player Init, Player Pass)
	{
		//tradeing between to player until a agreement is struck or they chose to end the trade
		//handle what each player wants to offer both money and other ownable items (such as property or cards)
		//ask the players if the trade is agreable
	}
}
