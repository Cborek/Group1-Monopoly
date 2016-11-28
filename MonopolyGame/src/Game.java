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
	private final String[] playerOptions = {"Buy Space","Do not buy space","Build","Trade with players","Mortgage","End turn","Quit game", "Show my propreties","Repay Mortgage"};
	//a list of the player
	private ArrayList<Player> gameMembers = new ArrayList<Player>();
	private CreateCardLists cardLists = new CreateCardLists();
	private int PlayerNum;
	
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
		PlayerNum = ConsoleUI.promptForInt("Please enter the number of players. 2-8", 2,8);
		//creates players based on the number of players
		for(int i=0; i< PlayerNum; i++)
  		{
 			boolean isGoodPiece = false;
  			System.out.print("Player " + (i+1) + " ");
  			gameMembers.add(new Player(ConsoleUI.promptForInput("What is your name?",false)));
 			Player currentPlayer = gameMembers.get(i);
  			// also ask for the type of piece of the list in the enum player pieces
 						do{
 				int playerPiece = ConsoleUI.promptForInt("What token would you like to play as?\n1 Ship : 2 Racecar : "
 						+ "3 Iron : 4 Shoe : 5 Wheelbarrow\n6 Terrier : 7 Thimble : 8 Hat : 9 Horse : 10 Cannon", 1, 10);
 				
 				for(int j = 0; j < gameMembers.size(); j++){
 					if(gameMembers.get(j).getPlayerPieceInt() == playerPiece){
 						System.out.println("Sorry, that piece has already been chosen. Pick again.");
 						break;
 					}
 					else{
 					
 						currentPlayer.setPlayerPieceInt(playerPiece);
 						isGoodPiece=true;
 					}
 				}
 				
 				
 			}while(isGoodPiece == false);
 			System.out.print ("You are playing as the ");
 			gamePiece(currentPlayer);
 			System.out.println ();
  		}
		// handle the loop of game play
		do{
			
			for(int whoseTurn =0; whoseTurn < gameMembers.size(); whoseTurn++)
			{
				printGame();
				System.out.println (gameMembers.get(whoseTurn).getName() + " it is your turn. You have $" + gameMembers.get(whoseTurn).getMoney());
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
			if(option == 0 && !currentPlayer.isInJail())
			{
				int total =0;
				int die1 = rollDice();
				int die2 = rollDice();
				total = die1+die2;
				doubles = isDoubles(die1,die2);
				//handles the payers turn in jail
				if(currentPlayer.isInJail())
				{
					System.out.println ("You are in jail");
					currentPlayer.incJailTurns();
					moveInJail(currentPlayer, doubles);
				}
				if(!currentPlayer.isInJail())
				{
					System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space "+ currentPlayer.getPlace());
					System.out.println ("You rolled "+ die1 +" and " +die2);
 					System.out.print("Your ");
 					gamePiece(currentPlayer);
 					System.out.println("moved " + total + " spaces");
					//get not set dumby fix it later 
					//above is covered
					currentPlayer.setPlace(total);
					System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space " +currentPlayer.getPlace());
					System.out.println ();	
				}
			}
			//the position value is equal to the square the player is currently on
			Square position = gameArea.getPos(currentPlayer.getPlace());
			if(!position.getIsOwned())
			{
				System.out.println ("This square costs $" + position.getCost());
			}
			if(position.getIsOwned() && !currentPlayer.ownes(position)&& position.getRent()>0 && option ==0)
			{
				payRent(position, currentPlayer);
			}
			else if((position.getName().equalsIgnoreCase("chance") || position.getName().equalsIgnoreCase("community Chest"))&&option==0)
			{
				if(position.getName().equalsIgnoreCase("chance"))
				{
					position = selectChance(currentPlayer,position);
				}
				else
					position = selectCommunity(currentPlayer,position);
			}
			System.out.println ();
			System.out.println ("You have $" + currentPlayer.getMoney());
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
				option3Build(currentPlayer);
			}
			else if(option ==4)
			{
				option4SellProp(currentPlayer);
			}
			else if(option == 5)
			{
				option6Mortgage(currentPlayer);
			}
			else if(option ==8)
			{
				currentPlayer.showHoldings();
				option = 1;
			}
			else if(option == 9){
 			option10RepayMortgage(currentPlayer);
 			option = 1;
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
				currentPlayer.setInJail();
				//create the in jail or just visiting
				doubles = false;
			}
			// handle the bankruptcy case
		}while(option <6);
	
		if(!gameArea.getPos(currentPlayer.getPlace()).getIsOwned() && option ==6)
		{
			auctionNotBought(gameArea.getPos(currentPlayer.getPlace()));
		}
		
		else if(option == 7 && currentPlayer.PropertyNum() >0 && currentPlayer.numMortgagedPoperties() >0)
		{
			Option8quitGame(currentPlayer);
		}
		else if(option ==7 && currentPlayer.PropertyNum() ==0 && currentPlayer.numMortgagedPoperties() ==0)
		{
			//todo if they own nothing and are quiting
			System.out.println ("you have quit");
			gameMembers.remove(currentPlayer);
		}
	}
	
	private void payRent(Square position, Player currentPlayer)throws IOException
	{
		// need to add what is done for owneing multiple things of the same color as well as railroads and utilities
		boolean payDouble = false;
		int rent = position.getRent();
		if(position.getIsMortgaged())
		{
			System.out.println ("This property is mortgaged you pay no rent");
			return;
		}
		for(int i=0; i<gameMembers.size();i++)
		{
			if(!position.getName().contains("tax") &&gameMembers.get(i).ownesColorGroup(position.getColor()))
			{
				payDouble = true;
				if(gameMembers.get(i) == currentPlayer)
				{
					payDouble = false;
				}
			}
			else if(position.getName().contains("tax"))
			{
				payDouble=false;
			}
		}
		if(payDouble)
		{
			rent = rent*2;
		}
		// handle the player being so broke that they need to sell properties to pay rent
		while(rent > currentPlayer.getMoney() && currentPlayer.PropertyNum()>0)
		{
			String[] SellOp = {"Sell a building", "Morgage a property"};
			System.out.println ("You must mortgage or sell things you own to");
			int option = ConsoleUI.promptForMenuSelection(SellOp,false);
			if(option ==1)
			{
				sellBuildings(currentPlayer);
			}
			else if(option == 2)
			{
				option6Mortgage(currentPlayer);
			}
			
		}
		if(rent>currentPlayer.getMoney() &&currentPlayer.PropertyNum() ==0)
		{
			if(!position.getName().contains("tax"))
			{
				gameMembers.get(position.getPlayer()-1).setMoney(currentPlayer.getMoney());
				currentPlayer.setMoney(-1*currentPlayer.getMoney());
				//transfer of properties
				while(currentPlayer.PropertyNum()>0)
				{
					gameMembers.get(position.getPlayer()-1).propertyChange(currentPlayer.getProperty(0));
					currentPlayer.getProperty(0).setPlayer(position.getPlayer());
					currentPlayer.propertyChange(currentPlayer.getProperty(0));
				}
				while(currentPlayer.numMortgagedPoperties()>0)
				{
					gameMembers.get(position.getPlayer()-1).addMortgagedProperty(currentPlayer.getMortgagedProperty(0));
					currentPlayer.getMortgagedProperty(0).setPlayer(position.getPlayer());
					currentPlayer.removeMortgagedPropertty(currentPlayer.getMortgagedProperty(0));
					currentPlayer.propertyChange(currentPlayer.getProperty(currentPlayer.PropertyNum()-1));
				}
			}
			else
			{
				Option8quitGame(currentPlayer);
			}
		}
		else if(payDouble)
		{
			System.out.println ("This square and its other colors are owned by a single player. You pay double rent");
			System.out.println ("You pay $" + rent);
			currentPlayer.setMoney(-1*rent);
			gameMembers.get(position.getPlayer()-1).setMoney(rent);
			System.out.println (gameMembers.get(position.getPlayer()-1).getName() + " you have been paid rent");
		}
		else
		{
			System.out.println ("This space is owned you pay rent of $" + rent +" dollars");
			currentPlayer.setMoney(-1*rent);
			gameMembers.get(position.getPlayer()-1).setMoney(rent);
			if(position.getName().contains("tax"))
			{
				System.out.println ("You have paid tax");
			}
			else{
				System.out.println (gameMembers.get(position.getPlayer()-1).getName() + " you have been paid rent");
			}
			
		}
	}
	//handle the squares you cant buy
	private void uniqueSquares(Square position , Player currentPlayer)throws IOException
	{
			if(position.getName().equalsIgnoreCase("got to jail"))
			{
				//player goes to jail
				System.out.println ("you go to jail");
				currentPlayer.goToPlace(11);
				currentPlayer.setInJail();
			}
			if(position.getName().equalsIgnoreCase("income tax"))
			{
				String tenPerc = "Pay $" + ((double)currentPlayer.getPlayerValue()*.1);
				String[] options = {"Pay 200",tenPerc};
				int choice = ConsoleUI.promptForMenuSelection(options,false);
				if(choice ==1 && currentPlayer.getMoney()>=200)
				{
					payRent(position, currentPlayer);
				}
				else{
						int pay = ((int)(-1*(double)currentPlayer.getPlayerValue()*.1));
						while(pay > currentPlayer.getMoney() && currentPlayer.PropertyNum()>0)
						{
							String[] SellOp = {"Sell a building", "Morgage a property"};
							System.out.println ("You must mortgage or sell things you own to pay the tax");
							int option = ConsoleUI.promptForMenuSelection(SellOp,false);
							if(option ==1)
							{
								sellBuildings(currentPlayer);
							}
							else if(option == 2)
							{
								option6Mortgage(currentPlayer);
							}
						}	
						if(currentPlayer.getMoney()<pay)
							{
								Option8quitGame(currentPlayer);
							}
						else
							{
								System.out.println ("You pay the tax");
								currentPlayer.setMoney(-1*pay);
							}
						
				}
				if(position.getName().equalsIgnoreCase("luxury tax"))
				{
					System.out.println ("You pay the luxury tax");
					payRent(position,currentPlayer);
				}
				else return;
			}
	}	
	//handles the player in jail
	private void moveInJail(Player currentPlayer, boolean doubles)throws IOException
	{
		boolean payFine = ConsoleUI.promptForBool("Will you pay the $50 fine to get out of jail? Y/N","y","n");
		if(payFine)
		{
			currentPlayer.setInJail();
			currentPlayer.setMoney(1*50);
		}
		else if(doubles)
		{
			currentPlayer.setInJail();
			System.out.println ("You rolled doubles. You are out of jail");
		}
		else if(currentPlayer.getJailTurns()==3)
		{
			System.out.println ("You are not in jail anymore. You have been in jail for 3 turns. You pay $50");
				System.out.println ("You must pay $50");
				while(50 > currentPlayer.getMoney() && currentPlayer.PropertyNum()>0)
				{
					System.out.println ("You cannoy pay bail");
					String[] SellOp = {"Sell a building", "Morgage a property"};
					System.out.println ("You must mortgage or sell things you own to pay the fine");
					int option = ConsoleUI.promptForMenuSelection(SellOp,false);
					if(option ==1)
					{
						sellBuildings(currentPlayer);
					}
					else if(option == 2)
					{
						option6Mortgage(currentPlayer);
					}
					
				}
				if(currentPlayer.getMoney()<50)
				{
					Option8quitGame(currentPlayer);
				}
				else
				{
					System.out.println ("You pay the $50");
					currentPlayer.setMoney(-50);
				}
					
			currentPlayer.setInJail();
			currentPlayer.setJailTurns(0);
		}
		else
		{
			System.out.println ("You are still in jail");
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

	private void option3Build(Player currentPlayer)throws IOException
	{
		String[] playerProperty = currentPlayer.getHoldings();
		final String[] BuildOp= {"Build house","Build Hotel"};
				currentPlayer.showHoldings();
				//checks is player has ability to build a house
				if(currentPlayer.PropertyNum()>0){	
					int propToSell = ConsoleUI.promptForMenuSelection(playerProperty,false)-1;
					int choice = ConsoleUI.promptForMenuSelection(BuildOp,true);
					if(currentPlayer.canBuildHouse(currentPlayer.getProperty(propToSell)) && choice ==1)
					{
						//builds the players a house on the square chosen
						System.out.println ("You have built a house on " + currentPlayer.getProperty(propToSell).getName());
						currentPlayer.getProperty(propToSell).addHouse();
					}
					else if(choice == 2 && currentPlayer.canBuildHotel(currentPlayer.getProperty(propToSell)))
					{
						// handles building a hotel on square
						System.out.println ("You have built a hotel on " + currentPlayer.getProperty(propToSell).getName());
						currentPlayer.getProperty(propToSell).addHotel();
					}
					else{
						System.out.println ("You can not build here");
					}
				}
	}
	
	private void option4SellProp(Player currentPlayer)throws IOException
	{
		// has the user select if they want to sell a property, a card, or a building
		//and if they want to sell to a specific player or not 
		String[] sellTo = {"Sell a property or card","Sell a building"};
		boolean doneSelling = false;
		String[] Members = new String[gameMembers.size()];
		for(int i=0; i<Members.length; i++)
		{
			Members[i] = gameMembers.get(i).getName();
		}
		int selection = ConsoleUI.promptForMenuSelection(sellTo,true);
		// add the statement to cover the auction the square or not
		if(selection == 1)
		{
			do
			{
				//present the player with a list of others to sell to 
				System.out.println ("Who would you like to sell to?");
				int whoToSellTo = ConsoleUI.promptForMenuSelection(Members,true);
				if(whoToSellTo == 0)
				{
					doneSelling=true;
				}
				else
				{
					trade(currentPlayer,gameMembers.get(whoToSellTo));
				}
			}while(!doneSelling);
		}
		else if(selection == 2)
		{
			sellBuildings(currentPlayer);
		}
		//then handle the selling from there
		//if it is a property and they do not want to sell specifically put it in auction
		//else send to trade method
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
			
			Square propertyToMortgage = currentPlayer.getProperty(selection -1);
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
	
	private void Option8quitGame(Player currentPlayer)throws IOException
	{
		System.out.println ("You have lost the game");
			//action all property off
			currentPlayer.setMoney(-1*currentPlayer.getMoney());
			
			for(int i=0; i<=currentPlayer.PropertyNum();i++)
			{
				System.out.println ();
				System.out.println (currentPlayer.getName() + " you may not participate in this auction. You have lost and have forfieted all your money");
				auctionNotBought(currentPlayer.getProperty(0));
				currentPlayer.propertyChange(currentPlayer.getProperty(0));
			}
			for(int i=0;i<currentPlayer.numMortgagedPoperties();i++)
			{
				System.out.println ();
				System.out.println (currentPlayer.getName() + " you may not participate in this auction. You have lost and have forfieted all your money");
				auctionNotBought(currentPlayer.getMortgagedProperty(0));
				currentPlayer.propertyChange(currentPlayer.getMortgagedProperty(0));
			}
			//remove player from list
			gameMembers.remove(currentPlayer);
	}
	
	private void option10RepayMortgage(Player currentPlayer) throws IOException{
 		String[] propNames = currentPlayer.getHoldings();
 		System.out.println("You currently have the following properties mortgaged.");
 		currentPlayer.getMortgagedProperties();
 		System.out.println("For which property would you like to repay the mortgage?");
 		int selection = ConsoleUI.promptForMenuSelection(propNames, false) - 1;
 		
 		if(propNames[selection].equals(currentPlayer.getMortgagedProperty(selection).getName()) && currentPlayer.getMoney() >= currentPlayer.getMortgageValue(selection)){
 			System.out.println("The mortgage for this property has been repaid.");
 			currentPlayer.setMoney(currentPlayer.getMortgageValue(selection));
 		}
 		else{
 			System.out.println("You cannot afford to repay this property right now.");
 		}
	}
	
	private void sellBuildings(Player currentPlayer)throws IOException
	{
		Square[] sellable = currentPlayer.ableToSell();
		String[] names = new String[sellable.length];
		for(int i=0;i<names.length;i++)
		{
			names[i]= sellable[i].getName();
		}
		System.out.println ("On what property would you like to sell a house");
		int sellHouses = ConsoleUI.promptForMenuSelection(names,false);
		if(currentPlayer.canSellHouse(currentPlayer.getProperty(sellHouses-1)))
		{
			currentPlayer.getProperty(sellHouses-1).subtractHouse();
			currentPlayer.setMoney(currentPlayer.getProperty(sellHouses-1).getHouseCost()/2);
		}
		
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

	private void printGame()throws IOException
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
			System.out.println("Intro to CS Final Project Group 1  Monopoly");
			System.out.println("Game programmed by: ");
			System.out.println("Cooper Astle\nColin Borek\nMelissa Buena\nJoshua Carpenter");
			startMenu();
		}
		else{
			System.out.println("Exiting 'Monopoly'...");
			System.out.println("Goodbye!");
		}
	}
	
 	private Square selectCommunity(Player  currentPlayer, Square position)
  	{
 		//may need change to accommodate the get out of jail card leaving the deck
 		//may need change to acomidate the get out of jail card leaving the deck
  		communityChest.get(0).getInfo();
  		communityChest.add(communityChest.get(0));
 		if (communityChest.get(0).getCardType().equals("PaymentCard")){
 		communityChest.remove(0);
 		//modifiying player money with value on the card
 		if(communityChest.get(0).getCardType().equalsIgnoreCase("payment square"))
 		{
  			currentPlayer.setMoney(communityChest.get(0).getAmount());
 		} else if (communityChest.get(0) instanceof LocationCards) {
 			LocationCards location = (LocationCards)communityChest.get(0);
 			location.moveToLocationSquare(currentPlayer);
 		} else if (communityChest.get(0).getCardType().equalsIgnoreCase("PaymentCardWithTwo")) {
 			paymentCardWithTwo(currentPlayer, communityChest.get(0).getAmount(), communityChest.get(0).getAmountTwo());
 		} else if (communityChest.get(0).getCardType().equalsIgnoreCase("GetOutOfJail")) {
 			// CODE FOR KEEPING JAIL CARD
 		} else {
 			System.out.println("WE HAVE AN ERROR. THE COMMUNITY CARDS DID NOT WORK");
 			// Prints out error message if cards are not selected properly. FOR TESTING PURPOSES ONLY
  		}
 		communityChest.remove(0);
 		//modifying player money with value on the card
  		//return the square the player is on even if they did not move
 		System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space " +currentPlayer.getPlace());
 		
  		return position;
  		}
  		return position;
  	}

	private Square selectChance(Player currentPlayer, Square position){
  		//possible needed changes for get out of jail
  		chance.get(0).getInfo();
  		chance.add(chance.get(0));
 		if (chance.get(0).getCardType().equals("PaymentCard")){
 		chance.remove(0);
 		if(chance.get(0).getCardType().equalsIgnoreCase("payment square"))
 		{
  			currentPlayer.setMoney(chance.get(0).getAmount());
 		} 
 		else if (chance.get(0).getCardType().equalsIgnoreCase("PaymentCardMultiplier")) {
 			for(Player member: gameMembers) {
 				member.setMoney(chance.get(0).getAmount());
 			}
 			int amountPaid = ((chance.get(0).getAmount())*(PlayerNum-1) *2); 
 			//PlayerNum1
 			// Every player is given the amount that must be paid including the player paying it
 			// Because of this, current player will then pay the amount *numberOfPlayers1 and *2 to cancel out what he received
 			currentPlayer.setMoney(amountPaid);
 		} else if (chance.get(0).getCardType().equalsIgnoreCase("PaymentCardWithTwo")) {
 			paymentCardWithTwo(currentPlayer, chance.get(0).getAmount(), chance.get(0).getAmountTwo());
 		} else if (chance.get(0) instanceof LocationCards){
 			LocationCards location = (LocationCards)chance.get(0);
 			location.moveToLocationSquare(currentPlayer);
 		} else if (chance.get(0) instanceof UtilityCards) {
 			UtilityCards utility = (UtilityCards)chance.get(0);
 			utility.moveToUtilitySquare(currentPlayer);
 		} else if (chance.get(0) instanceof RailRoadCards) {
 			RailRoadCards railroad = (RailRoadCards)chance.get(0);
 			railroad.moveToRailRoadSquare(currentPlayer);
 		} else if (chance.get(0).getCardType().equalsIgnoreCase("GetOutOfJail")) {
 			// CODE FOR KEEPING JAIL CARD
 		} else {
 			System.out.println("WE HAVE AN ERROR. CHANCE CARDS DO NOT WORK");
 			// Prints out error message if cards are not selected properly. FOR TESTING PURPOSES ONLY
  		}
 		chance.remove(0);
 		System.out.println ("You are on "+gameArea.getPosName(currentPlayer.getPlace())+ " at space " +currentPlayer.getPlace());
 		// Prints out game number and name after selecting card whether they moved or not
 		//modifiying player money with value on the card
 		// changing the amount lost based on houses or number of players
 		//return the square the player is on even if they did not move
  		return position;
  		}
  		return position;
	}
	
	// Method for payment per houses and hotel number
 	private void paymentCardWithTwo(Player currentPlayer, int houseCost, int hotelCost) {
 		int amountPaid=(currentPlayer.getTotalHouses()*houseCost)+
 				(currentPlayer.getTotalHotels()*hotelCost);
 		currentPlayer.setMoney(amountPaid);
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
				else if(highBid == gameMembers.get(i))
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
				else if(highBid!=gameMembers.get(i))
				{
					System.out.println ("You are currently not bidding");
				}
			}
			//when no one is bidding the auction is over
			if(bidding<=0)
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
		
		// handle if the property is mortgaged
	}
	
	private void trade(Player Init, Player Pass)throws IOException
	{
		//tradeing between to player until a agreement is struck or they chose to end the trade
		boolean tradeDone = false;
		String[] tradeOp = {"Property","Money","Card"};
		ArrayList<Square> InitProp = new ArrayList<Square>();
		ArrayList<Cards> InitCards = new ArrayList<Cards>();
		int InitMon =0;
		ArrayList<Square> PassProp = new ArrayList<Square>();
		ArrayList<Cards> PassCards = new ArrayList<Cards>();
		int PassMon =0;
		String[] curInitProps = Init.getHoldings();
		String[] curPassProps = Pass.getHoldings();
		boolean agreeable = false;
		do
		{
			System.out.println (Init.getName() + "What would you like to offer?");
			int InitOp = ConsoleUI.promptForMenuSelection(tradeOp,false);
			InitProp.clear();
			PassProp.clear();
			//selection option of the Init player
			if(InitOp == 1 && Init.PropertyNum()>0)
			{
				int select =0;
				do
				{
					System.out.println ("What property would you like to offer");
					//add the selected property to the initprop list
					select = ConsoleUI.promptForMenuSelection(curInitProps,true);
					PassProp.add(Pass.getProperty(select-1));
				}while(select !=0);
			}
			else if(InitOp == 2)
			{
				int offer = ConsoleUI.promptForInt("How much will you offer",0,Init.getMoney());
				InitMon = offer;
			}
			else if(InitOp == 3)
			{
				if(Init.getCardsNum() >0)
				{
					System.out.println ("You have offered a get out of jail free card");
					InitCards.add(Init.getCard());
						// add the get of jail card to the offer list
						
				}
					//if the Init player has a get out of jail card to trade it is put up for trade
			}
			System.out.println ();
			System.out.println (Pass.getName()+ "What would you like to offer?");
			int passOp = ConsoleUI.promptForMenuSelection(tradeOp,false);
			//selection option of the Init player
			if(passOp == 1&& Init.PropertyNum()>0)
			{
				int select =0;
				do
				{
					System.out.println ("What property would you like to offer");
					//add the selected property to the initprop list
					select = ConsoleUI.promptForMenuSelection(curPassProps,true);
					PassProp.add(Pass.getProperty(select-1));
				}while(select !=0);
			}
			else if(passOp == 2)
			{
				int offer = ConsoleUI.promptForInt("How much will you offer",0,Pass.getMoney());
				PassMon = offer;
			}
			else if(passOp == 3)
			{
				if(Pass.getCardsNum() >0)
				{
					System.out.println ("You have offered a get out of jail free card");
					PassCards.add(Pass.getCard());
						// add the get of jail card to the offer list
						
				}
				else 
					System.out.println ("You have no card to offer");
					//if the Init player has a get out of jail card to trade it is put up for trade
			}
			boolean InitGood= false;
			boolean PassGood = false;
			System.out.print(Init.getName());
			InitGood =ConsoleUI.promptForBool(" Is this a good trade? Y/N" ,"y","n");
			System.out.println ();
			System.out.print(Pass.getName());
			PassGood =ConsoleUI.promptForBool(" Is this a good trade? Y/N" ,"y","n");
			if(InitGood == true && PassGood == true)
			{
				agreeable =true;
				tradeDone=true;
			}
			else
			{
				boolean InitCon = false;
				boolean PassCon = false;
				System.out.print(Init.getName());
				InitCon =ConsoleUI.promptForBool(" Will you continue negotiations? Y/N" ,"y","n");
				System.out.println ();
				System.out.print(Pass.getName());
				PassCon =ConsoleUI.promptForBool(" Will you continue negotiations? Y/N" ,"y","n");
				if(InitCon == false || PassCon == false)
				{
					tradeDone = true;
				}
			}
		}while(!tradeDone);
		//handle what each player wants to offer both money and other ownable items (such as property or cards)
		//ask the players if the trade is agreable
		if(agreeable)
		{
			Init.setMoney(-1*InitMon);
			Init.setMoney(PassMon);
			Pass.setMoney(-1*PassMon);
			Pass.setMoney(InitMon);
			for(Square prop: InitProp)
			{
				Init.propertyChange(prop);
				Pass.propertyChange(prop);
			}
			for(Square prop: PassProp)
			{
				Init.propertyChange(prop);
				Pass.propertyChange(prop);
			}
			for(Cards prop: InitCards)
			{
				Init.setCard(prop);
				Pass.setCard(prop);
			}
			for(Cards prop: PassCards)
			{
				Init.setCard(prop);
				Pass.setCard(prop);
			}
		}
		else
		{
			System.out.println ();
			System.out.println ("Negotiations have broken down. This trade is over");
		}
	}
	
	private void gamePiece(Player currentPlayer){
 		if (currentPlayer.getPlayerPieceInt() == 1){
 			System.out.println(PlayerPieces.Ship);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 2){
 			System.out.println(PlayerPieces.Racecar);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 3){
 			System.out.println(PlayerPieces.Iron);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 4){
 			System.out.println(PlayerPieces.Shoe);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 5){
 			System.out.println(PlayerPieces.Wheelbarrow);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 6){
 			System.out.println(PlayerPieces.Terrier);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 7){
 			System.out.println(PlayerPieces.Thimble);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 8){
 			System.out.println(PlayerPieces.Hat);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 9){
 			System.out.println(PlayerPieces.Horse);
 		}
 		else if(currentPlayer.getPlayerPieceInt() == 10){
 			System.out.println(PlayerPieces.Cannon);
 		}
 	}
 }
