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
	private String[] menu = {"Play a New Game", "Credits", "Exit"};
	
	public Game()throws IOException
	{
		PlayerNum = ConsoleUI.promptForInt("Please enter the number of players. 2-8", 2,8);
		//creates players based on the number of players
		//creates the card piles
	}
	
	public void play()
	{
		// handle the loop of game play
	}
	public void playerTurn()
	{
		// runs throught the options and possibilities of the players turn
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
	public void printGame()
	{
		//prints the board as well as the players and where they are on the board
		gameArea.showBoard();
	}
	
	public void startMenu(){
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
	
}