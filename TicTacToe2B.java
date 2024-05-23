package AI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe2B {
	public static void main(String args[]) {
		char grid[]=new char[9];
		
		HashMap<Integer, Integer> map1 = new HashMap<>();
		map1.put(4, 0);map1.put(9, 1);map1.put(2, 2);map1.put(3, 3);map1.put(5, 4);
		map1.put(7, 5);map1.put(8, 6);map1.put(1, 7);map1.put(6, 8);
		
		HashMap<Integer, Integer> map2 = new HashMap<>();
		map2.put(0, 4);map2.put(1, 9);map2.put(2, 2);map2.put(3, 3);map2.put(4, 5);
		map2.put(5, 7);map2.put(6, 8);map2.put(7, 1);map2.put(8, 6);
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose X or O");
		char input = sc.next().charAt(0);
		char t = toss();
		boolean flag=true;
		if(input!=t) {
			System.out.println("Computer will play first");
			input = t;
			flag=false;
		}else {
			System.out.println("You will play first");
		}
		int c = 1;
		while(c<=9) {
			int g = game(grid,c,input,flag,map1,map2);
			if(g==1) {
				break;
			}
			c++;
			input = input=='X'?'O':'X';
			flag=(!flag);
		}
		System.out.println("GAME OVER");
		if(c==10) System.out.println("Match drawn");
		
	}
	
	
	public static boolean isBlank(char grid[],int idx) {
		if(grid[idx]!='\u0000') {
			return true;
		}else {
			return false;
		}
	}
	
	
	public static void go(char grid[],int idx,char input) {
		grid[idx]=input;
	}
	
	
	public static char toss() {
		char arr[]= {'X','O'};
		Random rand = new Random();
		return arr[rand.nextInt(2)];
	}
	
	
	public static void goMake2(char grid[],char input) {
		if(grid[4]=='\u0000') {
			grid[4]=input;
		}else if(grid[1]=='\u0000') {
			grid[1]=input;
		}else if(grid[3]=='\u0000') {
			grid[3]=input;
		}else if(grid[5]=='\u0000') {
			grid[5]=input;
		}else if(grid[7]=='\u0000') {
			grid[7]=input;
		}	
	}
	
	
	public static void takeInput(char grid[],char input) {
		System.out.println("Where you wanna place: "+input+" ?");
		Scanner sc = new Scanner(System.in);
		int i=sc.nextInt();
		grid[i-1]=input;
	}
	
	
	public static void display(char grid[]) {
		for(int i=0;i<9;i++) {
			System.out.print(grid[i]+" ");
			if(i%3==2) {
				System.out.println();
			}		
		}
	}
	
	
	public static boolean possWin(char grid[],int winLoc[],char input,HashMap<Integer,Integer> map1,HashMap<Integer,Integer> map2) {
		for(int i=0;i<9;i++) {
			if(grid[i]==input) {
				for(int j=i+1;j<9;j++) {
					if(grid[j]==input) {
						int posi = 15-(map2.get(i)+map2.get(j));
						if(map1.containsKey(posi)){
							if(grid[map1.get(posi)]=='\u0000') {	
								winLoc[0]=map1.get(posi);
								return true;
							}
						}
					}
				}
			}
		}
		
		
		return false;
	}
	
	
	public static void goAnyWhere(char grid[],char input) {
		for(int i=0;i<9;i++) {
			if(grid[i]=='\u0000') {
				grid[i]=input;
			}
		}
	}
	

	public static int game(char grid[], int c,char input,boolean flag,HashMap<Integer,Integer> map1,HashMap<Integer,Integer> map2) {
		int winLoc[]=new int[1];
		if(c==1) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(!isBlank(grid, 0)) {
					go(grid, 0, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==2) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(!isBlank(grid, 4)) {
					go(grid, 4, input);
				}else {
					go(grid, 0, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==3) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(!isBlank(grid, 8)) {
					go(grid, 8, input);
				}else {
					go(grid,2, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==4) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)) {
					go(grid, winLoc[0], input);
				}else {
					goMake2(grid, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==5) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input,map1,map2)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					display(grid);
					return 1;
				}else if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)){
					go(grid, winLoc[0], input);
				}else if(!isBlank(grid, 6)) {
					go(grid, 6, input);
				}else {
					go(grid, 2, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==6) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input,map1,map2)) {
					go(grid, winLoc[0], input);
					display(grid);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)){
					go(grid, winLoc[0], input);
				}else {
					goMake2(grid, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==7) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input,map1,map2)) {
					go(grid, winLoc[0], input);
					display(grid);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)){
					go(grid, winLoc[0], input);
				}else {
					goAnyWhere(grid, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==8) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input,map1,map2)) {
					go(grid, winLoc[0], input);
					display(grid);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)){
					go(grid, winLoc[0], input);
				}else {
					goAnyWhere(grid, input);
				}
				display(grid);
			}
			return 0;
		}
		if(c==9) {
			if(flag) {
				takeInput(grid,input);
			}else {
				if(possWin(grid,winLoc,input,map1,map2)) {
					go(grid, winLoc[0], input);
					display(grid);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input=='X'?'O':'X',map1,map2)){
					go(grid, winLoc[0], input);
				}else {
					goAnyWhere(grid, input);
				}
				display(grid);
			}
			return 0;
		}
		return -1;
	}
	
}
