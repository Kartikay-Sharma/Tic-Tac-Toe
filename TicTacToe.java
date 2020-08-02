package TicTacToe_Game;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TicTacToe {
	
	static int n;										//No. of players
	static Set<Integer> p1List = new HashSet<Integer>(9);					//Record of Player 1 (user) moves
	static Set<Integer> p2List = new HashSet<Integer>(9);					//Record of Computer (cpu) or Player 2 (user) moves
	static char [][] gameBoard = {{' ', '|', ' ', '|', ' '},				//Layout of the game
			   	      {'-', '+', '-', '+', '-'},
			   	      {' ', '|', ' ', '|', ' '},
			   	      {'-', '+', '-', '+', '-'},
			   	      {' ', '|', ' ', '|', ' '}};

	public static void main(String[] args) {
		
		int p1Pos, p2Pos;
		String p1;
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();							//Computer will move randomly (No A.I.)
		while(true) {
			
			System.out.print("How many players(1 or 2): ");		
			
			n = sc.nextInt();
			if(n == 1) {
				p1="";
				break;
			}
			else if(n == 2) {
				p1 = " (Player 1) ";
				break;
			}
			else {
				System.out.println("Choose between 1 or 2.");
			}
			
		}
		
		display();									//Display empty gameboard
		
		while(true) {
			
			System.out.print("Enter a position" + p1 + ": ");
			p1Pos = sc.nextInt();
			while(p1List.contains(p1Pos) || p2List.contains(p1Pos)) {		// If a symbol is already placed on the position
				System.out.print("Please enter an empty position: ");		// it will ask for a different position
				p1Pos = sc.nextInt();
			}
			placeSymbol(p1Pos, "player 1");
			
			if(checkWinner()!=null)
				break;
			if(n==2)								//When single player (for convenience)
				display();
			
			if(n == 1) {
				
				p2Pos = rand.nextInt(9) + 1;					// For range 1-9 (without +1 range will be 0-8)
				while(p1List.contains(p2Pos) || p2List.contains(p2Pos)) {	//Does not allow computer to replace any player's
					p2Pos = rand.nextInt(9) + 1;				//symbols or its own
				}
				placeSymbol(p2Pos, "computer");
				
			}
			else {
				
				System.out.print("Enter a position (Player 2): ");
				p2Pos = sc.nextInt();
				while(p1List.contains(p2Pos) || p2List.contains(p2Pos)) {	//If a symbol is already placed on the position
					System.out.print("Please enter an empty position: ");	//it will ask for a different position
					p2Pos = sc.nextInt();
				}
				placeSymbol(p2Pos, "player 2");
				
			}
			
			if(checkWinner()!=null)
				break;
			display();
			
		}
		
		sc.close();
		
	}
	
	public static void placeSymbol(int pos, String user) {
		
		char symbol = ' ';
		if(user.equals("player 1")) {
			symbol = 'X';
			p1List.add(pos);
		}
		else if(user.equals("computer") || user.equals("player 2")) {
			symbol = 'O';
			p2List.add(pos);
		}
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
		
	}
	
	public static String checkWinner() {
		
		List<Integer> topRow = Arrays.asList(1, 2, 3) ;					//These are the combinations for winning
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);
		List<Integer> cross1 = Arrays.asList(1, 5, 9);
		List<Integer> cross2 = Arrays.asList(3, 5, 7);
		
		List<List> win = new ArrayList<List>();
		win.add(topRow);
		win.add(midRow);
		win.add(botRow);
		win.add(leftCol);
		win.add(midCol);
		win.add(rightCol);
		win.add(cross1);
		win.add(cross2);
		
		for(List l : win) {								//Checks if any player's record contains any winning combination
			 
			if(p1List.containsAll(l)) {
				display();
				if(n==1)
					System.out.println("Congratulations you WON!!!");
				else
					System.out.println("Player 1 Wins!!!");
				return "p1 Wins";
			}
			else if(p2List.containsAll(l)) {
				display();
				if(n==1)
					System.out.println("Better luck next time :(");
				else
					System.out.println("Player 2 Wins!!!");
				return "p2 Wins";
			}
			
		}
		if(p1List.size() + p2List.size() == 9) {					//Checks if the gameboard is full or not
			display();
			System.out.println("TIE.");
			return "tie";
		}
		
		return null;
		
	}
	
	public static void display() {								//Displays the gameboard
		
		for(char [] rows : gameBoard) {
			for(char c : rows) {
				System.out.print(c);
			}
			System.out.println();
		}
		
	}

}
