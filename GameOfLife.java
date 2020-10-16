///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            GameOfLife	
// Files:            GameOfLife.java
// Semester:         CS302 Fall 2015
//
// Author:           Ian Henscheid	
// Email:            henscheid@wisc.edu	
// CS Login:         henscheid
// Lecturer's Name:  Jim Williams
// Lab Section:      341
///////////////////////////////////////////////////////////////////////////////

/**
 * This game is based on John Conway's Game of Life as described in Wikipedia.
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 */


// PUT import statements here
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

//displays John Conway's Game of Life with 5 different pattern choices
public class GameOfLife {

	// DO NOT ADD STATIC FIELDS TO THIS CLASS
	// YOU MUST SOLVE ALL PARTS BY PASSING THE 
	// THE VALUES YOU NEED TO AND FROM METHODS.
	// WE WILL TEST ALL METHODS INDEPENDENT OF 
	// OTHER METHODS.  
	//
	// THAT CAN ONLY WORK IF YOU DEFINE EACH 
	// METHOD AS DESCRIBED.  YOU MAY NOT IMPLEMENT
	// YOUR OWN DESIGN EXCEPT TO ADD PRIVATE
	// HELPER METHODS AS YOU LIKE.  YOU MUST
	// STILL IMPLEMENT ALL PUBLIC METHODS
	// OF THIS CLASS.

	/**
	 * Program execution starts here.
	 * @param args UNUSED
	 */    
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		//declare local variables
		int choice = 0;
		int genNum = 0;
		
		// Display Welcome message        
		System.out.println("Welcome to the Game of Life");
		
			//menu choices
		while (choice != 9 ) {
			System.out.println("Select a pattern of life for the world");
			System.out.print("1 - Glider\n2 - Beacon\n3 - Boat\n4 - R-pentomino"
					+ "\n5 - Random\n9 - Exit\nChoice:");
			
			do {
				// check for integer input
				if (input.hasNextInt()) {
					choice = input.nextInt();
					if (choice < 1 || choice > 5 && choice != 9) {
						System.out.println("Invalid input");
						System.out.println(
								"Select a pattern of life for the world");
						
						System.out.print("1 - Glider\n2 - Beacon\n3 - Boat\n" +
								"4 - R-pentomino\n5 - Random\n" + 
								"9 - Exit\nChoice:");
					}
				} else {
					System.out.println("Invalid input");
					
					System.out.println
						("Select a pattern of life for the world");
					
					System.out.print("1 - Glider\n2 - Beacon\n3 - Boat\n" +
							"4 - R-pentomino\n5 - Random\n9 - Exit\nChoice:");
				}
				input.nextLine();
				
			} while (choice < 1 || choice > 5 && choice != 9);
			if (choice == 9) {
				System.out.println("End of Game of Life.");
			} else {
				boolean[][]world = GameOfLife.createNewWorld
						(Config.WORLD_ROWS, Config.WORLD_COLUMNS);
					//initialize the world based on the user's choice 
				String patternName = "Pattern";
				
				if (choice == 1) {
					GameOfLife.initializeGlider(world);
					patternName = "Glider";
				}
				
				else if (choice == 2) {
					GameOfLife.initializeBeacon(world);
					patternName = "Beacon";
				}
				
				else if (choice == 3) {
					GameOfLife.initializeBoat(world);
					patternName = "Boat";
				}
				
				else if (choice == 4) {
					GameOfLife.initializeRpentomino(world);
					patternName = "R-pentomino";
				}
				
				else if (choice == 5) {
					GameOfLife.initializeRandomWorld(world);
					patternName = "Random";
				}

				GameOfLife.printWorld(patternName, world, genNum);
				
				System.out.println("\nOptions\n(Enter): show next generation");
				System.out.print("end(Enter): end this simulation\nChoice:");
				String nextGen = input.nextLine();
//				System.out.println();
				
				while (nextGen.equals("")) {	
					genNum++;
					System.out.println(patternName + " generation: " + genNum);
					boolean[][] newWorld = GameOfLife.createNewWorld
							(Config.WORLD_ROWS, Config.WORLD_COLUMNS);
					GameOfLife.nextGeneration(world, newWorld);
					world = newWorld;
					System.out.println
						("\nOptions\n(Enter): show next generation");
					
					System.out.print
						("end(Enter): end this simulation\nChoice:");
					nextGen = input.nextLine();
					System.out.println();
				}
				
				GameOfLife.clearWorld(world);
				genNum = 0;
			}
		}
				//loop to print out world and prompt for next generation or Exit          
	} 	

	/**
	 * Create a new world
	 * @param numRows The number of rows to be in the created world
	 * @param numColumns  The number of columns to be in the created world
	 * @return A double dimension array of boolean that is numRows by 
	 * numColumns in size and initialized to all false values. 
	 */
	public static boolean[][] createNewWorld( int numRows, int numColumns) {
		boolean [][] world = new boolean[numRows][numColumns];
		GameOfLife.clearWorld(world);
		
		//create the world of the proper size
		return world;
	}

	/**
	 * Sets all the cells in the world to not alive (false).
	 * @param world 
	 */
	public static void clearWorld( boolean[][]world) {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				world[i][j] = false;
			}
		}
	  //Two for loops 
		// set all cells to false 
	}	

	/**
	 * Initializes the world to the Glider pattern.
	 * <pre>
	 * ..........
	 * .@........
	 * ..@@......
	 * .@@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre>
	 * @param world  the existing double dimension array that will be 
	 * reinitialized to the Glider pattern. 
	 */
	public static void initializeGlider(boolean[][]world) {
		
		// TODO: Implement this method		
		//initialize to all false values
		GameOfLife.clearWorld(world);
		//in the world set specific cells to true that are alive for the 
		//glider pattern
		world [1][1] = true;
		world [2][2] = true;
		world [2][3] = true;
		world [3][1] = true;
		world [3][2] = true;
		
		System.out.println();

		
	}

	/**
	 * Initializes the world to the Beacon pattern.
	 * <pre>
	 * ..........
	 * .@@.......
	 * .@........
	 * ....@.....
	 * ...@@.....
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre> 
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the Beacon pattern.
	 */		
	public static void initializeBeacon(boolean[][] world) {

		// TODO: Implement this method				
		//initialize to all false values
		GameOfLife.clearWorld(world);
		
		//in the world set the cells to true that are alive for the 
		// Beacon pattern.

		//		clearWorld()
		world [1][1] = true;
		world [1][2] = true;
		world [2][1] = true;
		world [3][4] = true;
		world [4][3] = true;
		world [4][4] = true;
		System.out.println();


	}

	/**
	 * Initializes the world to the Boat pattern.
	 * <pre>
	 * ..........
	 * .@@.......
	 * .@.@......
	 * ..@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre> 
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the Boat pattern.
	 */		
	public static void initializeBoat(boolean[][] world) {
		// TODO: Implement this method
		//initialize to all false values
		GameOfLife.clearWorld(world);
		
		//in the world set the cells to true that are alive for the 
		// Boat pattern.	
		world [1][1] = true;
		world [1][2] = true;
		world [2][1] = true;
		world [2][3] = true;
		world [3][2] = true;
		System.out.println();

	}	
	/**
	 * Initializes the world to the R-pentomino pattern.
	 * <pre>
	 * ..........
	 * ..@@......
	 * .@@.......
	 * ..@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre> 
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the R-pentomino pattern.
	 */		
	public static void initializeRpentomino(boolean[][] world) {
		// TODO: Implement this method
		//initialize to all false values
		GameOfLife.clearWorld(world);
		
		//in the world set the cells to true that are alive for the 
		// R-pentomino pattern.	
		world [1][2] = true;
		world [1][3] = true;
		world [2][1] = true;
		world [2][2] = true;
		world [3][2] = true;	
		System.out.println();

	}	
	/**
	 * Initialize the GameOfLife world with a random selection of cells alive. 
	 * 
	 * @param world  the existing double dimension array that will be 
	 * reinitialized to a Random pattern.
	 */	
	public static void initializeRandomWorld(boolean[][] world){
		// TODO: Implement this method	    	

		//initialize to all false values
		GameOfLife.clearWorld(world);
		//loop through every row
		for (int i = 0; i < Config.WORLD_ROWS; i++) {
			
			//here we are within a row, so loop through every column
			
				for (int j = 0; j < Config.WORLD_COLUMNS; j++) {
					
				//for the cell in the specific row and column, give it a 
				//true value 'Config.CHANCE_ALIVE' percent of the time.
				//(hint: if Config.CHANCE_ALIVE is 0.25, then there should be 
				// about a 25% chance this cell is alive.  
				// Recall that the nextDouble() method from the java.util.Random 
				// class returns a uniformly distributed double value between 
				// 0.0 and 1.0.)            	

				if( Config.RNG.nextDouble() < Config.CHANCE_ALIVE) {
					world [i][j] = true;
					//set cell to be alive
				}

			}
		}
		System.out.println();

	}

	/** 
	 * Whether a cell is living in the next generation of the game.
	 * 
	 * The rules of the game are as follows:
	 * 1) Any live cell with fewer than two live neighbors dies, as if caused
	 *    by under-population.
	 * 2) Any live cell with two or three live neighbors lives on to the next 
	 *    generation.
	 * 3) Any live cell with more than three live neighbors dies, as if by 
	 *    overcrowding.
	 * 4) Any dead cell with exactly three live neighbors becomes a live cell, 
	 *    as if by reproduction.
	 * 
	 * @param numLivingNeighbors The number of neighbors that are currently
	 *        living.
	 * @param cellCurrentlyLiving Whether the cell is currently living.
	 * 
	 * @return True if this cell is living in the next generation.    
	 */
	// TODO: PUT METHOD HEADER COMMENTS HERE	
	public static boolean isCellLivingInNextGeneration( int numLivingNeighbors, 
			boolean cellCurrentlyLiving) {
		// TODO: Implement this method	
		
		//determine if living cell lives on to next generation
		if (cellCurrentlyLiving) {
			if (numLivingNeighbors == 2 || numLivingNeighbors == 3) {
				return true;
			} else {
				return false;
			}	
		}
		
		//determine if dead cell will become alive next generation
		else if (!cellCurrentlyLiving) {
			if (numLivingNeighbors == 3) {
				return true;
			} else {
				return false;
			}
		} else {
		return false;
		}
	}


	/**
	 * Whether a specific neighbor is alive.
	 *
	 * Check whether the specific cell is alive or dead.
	 * 
	 * Note that cellRow and cellColumn may not be valid. If the cellRow is 
	 * less than 0 or greater than the number of rows -1 
	 * then the cell is outside the world. If the cellColumn is less than 0 
	 * or is greater than the number of columns -1 then 
	 * the cell is outside the world. Return false for any cell outside the 
	 * world.
	 * 
	 * @param world The current world.
	 * @param neighborCellRow The row of the cell which we are wanting to know
	 *  is alive.
	 * @param neighborCellColumn The column of the cell for which we are wanting
	 *  to know is alive.
	 * 
	 * @return Whether the cell is alive.
	 */	
	public static boolean isNeighborAlive(boolean [][]world, 
			int neighborCellRow, int neighborCellColumn) {

		// TODO: Implement this method    	
		//if valid row index
		if (neighborCellRow < 0 || neighborCellColumn < 0 ||
				neighborCellRow >= world.length || neighborCellColumn >=
				world[0].length) {
			return false;
		}
		if (world[neighborCellRow][neighborCellColumn]) {
			return true;
		} else {
		return false;
		}
	}

	/**
	 * Counts the number of neighbors that are currently living around the 
	 * specified cell.
	 *
	 * A cell has eight neighbors. The neighbors are the cells that are 
	 * horizontally, vertically and diagonally adjacent.
	 * 
	 * Note that if a specific cell is on the edge of the world then it may not 
	 * have neighboring cells.  For example: for the 0'th row (top row) of the 
	 * world there are not any rows above it.
	 * Assume all those cells are dead (have false values).
	 * 
	 * @param world The current world.
	 * @param cellRow The row of the cell for which we are looking for neighbors.
	 * @param cellColumn The column of the cell for which we are looking for 
	 * neighbors.
	 * 
	 * @return The number of neighbor cells that are currently living.
	 */
	// TODO: PUT METHOD HEADER COMMENTS HERE
	public static int numNeighborsAlive(boolean[][] world, int cellRow, 
			int cellColumn) {
		// TODO: Implement this method		
		int aliveCount = 0;
		if (cellRow < 0 || cellRow > world.length - 1 || cellColumn < 0 ||
			cellColumn > world[0].length - 1) {
			world[cellRow][cellColumn] = false;
		}
		//neighbors in the row above
		if (cellRow > 0) {
			
			//top left
			if (cellColumn > 0) {
				if (isNeighborAlive(world, cellRow - 1, cellColumn - 1)) {
					aliveCount++;
				}
			}
			
			//directly above
			if (isNeighborAlive(world, cellRow - 1, cellColumn)) {
				aliveCount++;
			}
			
			//top right
			if (cellColumn < world[0].length - 1 ) {
				if (isNeighborAlive(world, cellRow - 1, cellColumn + 1)) {
					aliveCount++;
				}
			}
		}

		//neighbors in the row below
		if (cellRow < world.length - 1) {
			
			//bottom left
			if (cellColumn > 0) {
				if (isNeighborAlive(world, cellRow + 1, cellColumn - 1)) {
					aliveCount++;
				}
			}
			
			//directly below
			if (isNeighborAlive(world, cellRow + 1, cellColumn)) {
				aliveCount++;
			}
			
			//bottom right
			if (cellColumn < world[0].length - 1) {
				if (isNeighborAlive(world, cellRow + 1, cellColumn + 1)) {
					aliveCount++;
				}
			}
		}
		
		//neighbor to the left
		if (cellColumn > 0) {
			if (isNeighborAlive(world, cellRow, cellColumn - 1)) {
				aliveCount++;
			}
		}
		
		//neighbor to the right
		if (cellColumn < world[0].length - 1) {
		if (isNeighborAlive(world, cellRow, cellColumn + 1)) {
			aliveCount++;
		}
		}

		return aliveCount;
	}

	/**
	 * Determines the next generation of the world.
	 * 
	 * @param currentWorld The world currently shown. 
	 * @param newWorld The new world to determine by looking at
	 * each cells neighbors in the current world. 
	 */
	public static void nextGeneration(boolean[][] currentWorld, 
			boolean[][] newWorld) {
		
		// TODO: Implement this method		
		int count = 0;
		
		//for each row
		for (int i = 0; i < Config.WORLD_ROWS; i++) {
			for (int j = 0; j < Config.WORLD_COLUMNS; j++) {
				
				//determine if cell is currently alive
				boolean cellLiving = currentWorld[i][j];
				
				//determine the number of neighbors alive in current world
				int numAlive = numNeighborsAlive(currentWorld, i, j);
				
				//determine whether or not cell will live to next gen
				newWorld [i][j] = isCellLivingInNextGeneration
						(numAlive, cellLiving);
				
				//print out next generation
				if (newWorld[i][j] == true) {
					System.out.print(Config.ALIVE);
					count++;
				} else {
					System.out.print(Config.DEAD);
				}
			if (j == Config.WORLD_COLUMNS - 1 ) {
				System.out.println("");
			}
			}
			
		}
		System.out.println(count + " cells are alive.");

			//for each column

				//determine the number of neighbors that are alive for the 
				//specific cell

				//determine whether the cell should be alive the next generation

	}

	/**
	 * Prints out the world showing each cell as alive or dead.
	 * 
	 * Loops through every cell of the world. If a cell is alive, print out
	 * the Config.ALIVE character, otherwise the Config.DEAD character. 
	 * 
	 * Tracks how many cells are alive and prints out the number of cells alive
	 * or that no cells are alive.
	 * 
	 * @param patternName The name of the pattern chosen by the user. 
	 * @param world The array representation of the current world. 
	 * @param generationNum The number of the current generation.  
	 */    
	public static void printWorld( String patternName, boolean[][] world, 
			int generationNum) {
		// TODO: Implement this method    	
		//declare and initialize local variables
		int count = 0;
		
		//print out pattern and generation
		System.out.println(patternName + " generation: " + generationNum);
		
		//for each row in the world
		for (int i = 0; i < Config.WORLD_ROWS; i++) {
			
			//for each column in the world
			for (int j = 0; j < Config.WORLD_COLUMNS; j++) {
				
				//if the cell is alive
				if (world[i][j] == true) {
					System.out.print(Config.ALIVE);
					count++;
					
					//otherwise if the cell is dead.
				} else {
					System.out.print(Config.DEAD);
				}
			if (j == Config.WORLD_COLUMNS - 1 ) {
				System.out.println("");
			}
			}
		}
		//print out the number of cells alive.  
		System.out.println(count + " cells are alive.");

	}
}