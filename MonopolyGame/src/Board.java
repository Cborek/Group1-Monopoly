import java.io.IOException;

public class Board {
	private Square[][] BOARD;
	

	public Board() {
		BOARD = new Square[11][11];
	
		//Assigns specific details for each space on the board
		BOARD[10][10] = new GoSquare(1, "GO");
		BOARD[10][10].setIsOwned();
		BOARD[10][9] = new PropertySquare(2, "Mediterranean Avenue", "Brown", 60, 2, 50, 10, 30, 90, 160, 250, 30);
		BOARD[10][8] = new CardSquare(3, "Community Chest");
		BOARD[10][7] = new PropertySquare(4, "Baltic Avenue", "Brown", 60, 4, 50, 20, 60, 180, 320, 450, 30);
		BOARD[10][6] = new PaymentSquare(5, "Income Tax", 200);
		BOARD[10][5] = new RailRoadSquare(6, "Reading Railroad", "White", 200, 25, 50, 100, 200, 100);
		BOARD[10][4] = new PropertySquare(7, "Oriental Avenue", "Light Blue", 100, 6, 50, 30, 90, 270, 400, 550, 50);
		BOARD[10][3] = new CardSquare(8, "Chance");
		BOARD[10][2] = new PropertySquare(9, "Vermont Avenue", "Light Blue", 100, 6, 50, 30, 90, 270, 400, 550, 50);
		BOARD[10][1] = new PropertySquare(10, "Connecticut Avenue", "Light Blue", 120, 8, 50, 40, 100, 300, 450, 600, 60);
		BOARD[10][0] = new Square(11, "Jail");
		BOARD[10][0].setIsOwned();
		BOARD[9][0] = new PropertySquare(12, "St. Charles Place", "Pink", 140, 10, 100, 50, 150, 450, 625, 750, 70);
		BOARD[8][0] = new UtilitySquare(13, "Electric Company", "Black", 150, 4, 10, 75); // 4 and 10 are the multipliers
		BOARD[7][0] = new PropertySquare(14, "States Avenue", "Pink", 140, 10, 100, 50, 150, 450, 625, 750, 70);
		BOARD[6][0] = new PropertySquare(15, "Virginia Avenue", "Pink", 160, 12, 100, 60, 180, 500, 700, 900, 80);
		BOARD[5][0] = new RailRoadSquare(16, "Pennsylvania Railroad", "White", 200, 25, 50, 100, 200, 100);
		BOARD[4][0] = new PropertySquare(17, "St. James Place", "Orange", 180, 14, 100, 70, 200, 550, 750, 950, 90);
		BOARD[3][0] = new CardSquare(18, "Community Chest");
		BOARD[2][0] = new PropertySquare(19, "Tennessee Avenue", "Orange", 180, 14, 100, 70, 200, 550, 750, 950, 90);
		BOARD[1][0] = new PropertySquare(20, "New York Avenue", "Orange", 200, 16, 100, 80, 220, 600, 800, 1000, 100);
		BOARD[0][0] = new Square(21, "Free Parking");
		BOARD[0][0].setIsOwned();
		BOARD[0][1] = new PropertySquare(22, "Kentucky Avenue", "Red", 220, 18, 150, 90, 250, 700, 875, 1050, 110);
		BOARD[0][2] = new CardSquare(23, "Chance");
		BOARD[0][3] = new PropertySquare(24, "Indiana Avenue", "Red", 220, 18, 150, 90, 250, 700, 875, 1050, 110);
		BOARD[0][4] = new PropertySquare(25, "Illinois Avenue", "Red", 240, 20, 150, 100, 300, 750, 925, 1100, 120);
		BOARD[0][5] = new RailRoadSquare(26, "B. & O. Railroad", "White", 200, 25, 50, 100, 200, 100);
		BOARD[0][6] = new PropertySquare(27, "Atlantic Avenue", "Yellow", 260, 22, 150, 110, 330, 800, 975, 1150, 130);
		BOARD[0][7] = new PropertySquare(28, "Ventnor", "Yellow", 260, 22, 150, 110, 330, 800, 975, 1150, 130);
		BOARD[0][8] = new UtilitySquare(29, "Water Works", "Black", 150, 4, 10, 75); // 4 and 10 are the multipliers
		BOARD[0][9] = new PropertySquare(30, "Marvin Gardens", "Yellow", 280, 24, 150, 120, 360, 850, 1025, 1200, 140);
		BOARD[0][10] = new Square(31, "Go to Jail");
		BOARD[0][10].setIsOwned();
		BOARD[1][10] = new PropertySquare(32, "Pacific Avenue", "Green", 300, 26, 200, 130, 390, 900, 1100, 1275, 150);
		BOARD[2][10] = new PropertySquare(33, "North Carolina Avenue", "Green", 300, 26, 200, 130, 390, 900, 1100, 1275, 150);
		BOARD[3][10] = new CardSquare(34, "Community Chest");
		BOARD[4][10] = new PropertySquare(35, "Pennsylvania Avenue", "Green", 320, 28, 200, 150, 450, 1000, 1200, 1400, 160);
		BOARD[5][10] = new RailRoadSquare(36, "Short Line", "White", 200, 25, 50, 100, 200, 100);
		BOARD[6][10] = new CardSquare(37, "Chance");
		BOARD[7][10] = new PropertySquare(38, "Park Place", "Blue", 350, 35, 200, 175, 500, 1100, 1300, 1500, 175);
		BOARD[8][10] = new PaymentSquare(39, "Luxury Tax", 100);
		BOARD[9][10] = new PropertySquare(40, "Boardwalk", "Blue", 400, 50, 200, 200, 600, 1400, 1700, 2000, 200);

		// blank squares used as placeholders for printing of the board
		BOARD[1][1] = new Square ("  ");
		BOARD[1][2] = new Square ("  ");
		BOARD[1][3] = new Square ("  ");
		BOARD[1][4] = new Square ("  ");
		BOARD[1][5] = new Square ("  ");
		BOARD[1][6] = new Square ("  ");
		BOARD[1][7] = new Square ("  ");
		BOARD[1][8] = new Square ("  ");
		BOARD[1][9] = new Square ("  ");
		BOARD[2][1] = new Square ("  ");
		BOARD[2][2] = new Square ("  ");
		BOARD[2][3] = new Square ("  ");
		BOARD[2][4] = new Square ("  ");
		BOARD[2][5] = new Square ("  ");
		BOARD[2][6] = new Square ("  ");
		BOARD[2][7] = new Square ("  ");
		BOARD[2][8] = new Square ("  ");
		BOARD[2][9] = new Square ("  ");
		BOARD[3][1] = new Square ("  ");
		BOARD[3][2] = new Square ("  ");
		BOARD[3][3] = new Square ("  ");
		BOARD[3][4] = new Square ("  ");
		BOARD[3][5] = new Square ("  ");
		BOARD[3][6] = new Square ("  ");
		BOARD[3][7] = new Square ("  ");
		BOARD[3][8] = new Square ("  "); 
		BOARD[3][9] = new Square ("  ");
		BOARD[4][1] = new Square ("  ");
		BOARD[4][2] = new Square ("  ");
		BOARD[4][3] = new Square ("  ");
		BOARD[4][4] = new Square ("  ");
		BOARD[4][5] = new Square ("  ");
		BOARD[4][6] = new Square ("  ");
		BOARD[4][7] = new Square ("  ");
		BOARD[4][8] = new Square ("  ");
		BOARD[4][9] = new Square ("  ");
		BOARD[5][1] = new Square ("  ");
		BOARD[5][2] = new Square ("  ");
		BOARD[5][3] = new Square ("  ");
		BOARD[5][4] = new Square (" MONOPOLY ");
		BOARD[5][5] = new Square ("");
		BOARD[5][6] = new Square ("");
		BOARD[5][7] = new Square ("");
		BOARD[5][8] = new Square (" ");
		BOARD[5][9] = new Square (" ");
		BOARD[6][1] = new Square ("  ");
		BOARD[6][2] = new Square ("  ");
		BOARD[6][3] = new Square ("  ");
		BOARD[6][4] = new Square ("  ");
		BOARD[6][5] = new Square ("  ");
		BOARD[6][6] = new Square ("  ");
		BOARD[6][7] = new Square ("  ");
		BOARD[6][8] = new Square ("  ");
		BOARD[6][9] = new Square ("  ");
		BOARD[7][1] = new Square ("  ");
		BOARD[7][2] = new Square ("  ");
		BOARD[7][3] = new Square ("  ");
		BOARD[7][4] = new Square ("  ");
		BOARD[7][5] = new Square ("  ");
		BOARD[7][6] = new Square ("  ");
		BOARD[7][7] = new Square ("  ");
		BOARD[7][8] = new Square ("  ");
		BOARD[7][9] = new Square ("  ");
		BOARD[8][1] = new Square ("  ");
		BOARD[8][2] = new Square ("  ");
		BOARD[8][3] = new Square ("  ");
		BOARD[8][4] = new Square ("  ");
		BOARD[8][5] = new Square ("  ");
		BOARD[8][6] = new Square ("  ");
		BOARD[8][7] = new Square ("  ");
		BOARD[8][8] = new Square ("  ");
		BOARD[8][9] = new Square ("  ");
		BOARD[9][1] = new Square ("  ");
		BOARD[9][2] = new Square ("  ");
		BOARD[9][3] = new Square ("  ");
		BOARD[9][4] = new Square ("  ");
		BOARD[9][5] = new Square ("  ");
		BOARD[9][6] = new Square ("  ");
		BOARD[9][7] = new Square ("  ");
		BOARD[9][8] = new Square ("  ");
		BOARD[9][9] = new Square ("  ");
	}
	//code to print a visual of the monopoly board
	public void showBoard() throws IOException {
		System.out.println();
		System.out.println("     ----------------------------------------------");
			
		for (int i = 0; i < BOARD.length; i++) {
		
			System.out.print("|----|                                            |----|");
			System.out.println();
			for (int j = 0; j < BOARD.length; j++) {
		
				if(i == 10 && j==10){
					System.out.print("  0" + BOARD[i][j].getLocation() + " ");
				}
				else if (j==0 ||i==0|| j==10) {
					
					System.out.print("  " + BOARD[i][j].getLocation() + " ");
					
				} 
				else if(i==10 && j > 1 ){
					System.out.print("  0" + BOARD[i][j].getLocation() + " ");
				}
				else if(i == 10 && j==1){
					System.out.print("  " + BOARD[i][j].getLocation() + " ");
				}
				else {
					System.out.print("  " + BOARD[i][j].getContents() + " ");
				}
			}
			System.out.println();
		}
			System.out.print("|----|                                            |----|");	
			System.out.println();
			System.out.println("     ---------------------------------------------");
			System.out.println();
	}
	//gets square number thats on the board
	public Square getPos(int num)
	{
		//returns the value of a position
		for(int r=0;r<BOARD.length;r++)
		{
			for(int c=0; c< BOARD[r].length; c++)
			{
				if(BOARD[r][c].getLocation() == num)
				{
					return BOARD[r][c];
				}
			}
		}
		return BOARD[0][0];
	}
	
	//gets the name of the specific square space number
	public String getPosName(int num)
	{
		//returns the value of a position
		for(int r=0;r<BOARD.length;r++)
		{
			for(int c=0; c< BOARD[r].length; c++)
			{
				if(BOARD[r][c].getLocation() == num)
				{
					return BOARD[r][c].getName();
				}
			}
		}
		return BOARD[0][0].getName();
	}
}
