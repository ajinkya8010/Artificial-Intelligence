package AI;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe2A {
	public static void main(String args[]) {
		int grid[]=new int[9];
		Arrays.fill(grid, 2);
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose X(Enter 3) or O(Enter 5)");
		int input = sc.nextInt();
		System.out.println("Lets toss a coin!");
		int t = toss();
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
			int g = game(grid,c,input,flag);
			if(g==1) {
				break;
			}
			c++;
			input = input==3?5:3;
			flag=(!flag);
		}
		System.out.println("GAME OVER");
		if(c==10) System.out.println("Match drawn");
		
	}
	
	
	public static boolean isBlank(int grid[],int idx) {//To check if position is blank
		if(grid[idx]!=2) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public static void go(int grid[],int idx,int input) {//Place at given position
		grid[idx]=input;
	}
	
	
	public static int toss() {//Toss the coin
		int arr[]= {3,5};
		Random rand = new Random();
		return arr[rand.nextInt(2)];
	}
	
	
	public static void goMake2(int grid[],int input) {//Place at empty non corner position
		if(grid[4]==2) {
			grid[4]=input;
		}else if(grid[1]==2) {
			grid[1]=input;
		}else if(grid[3]==2) {
			grid[3]=input;
		}else if(grid[5]==2) {
			grid[5]=input;
		}else if(grid[7]==2) {
			grid[7]=3;
		}	
	}
	
	
	public static void takeInput(int grid[],int input) {
		System.out.println("Where you wanna place: "+input+" ?");
		Scanner sc = new Scanner(System.in);
		int i=sc.nextInt();
		grid[i-1]=input;
	}
	
	
	public static void display(int grid[]) {
		for(int i=0;i<9;i++) {
			System.out.print(grid[i]+" ");
			if(i%3==2) {
				System.out.println();
			}		
		}
	}
	
	public static int loc(int grid[],int i,int j,int k) {//To find winning location
		if(grid[i]==2) {
			return i;
		}
		if(grid[j]==2) {
			return j;
		}
		return k;
	}
	
	
	
	public static boolean possWin(int grid[],int winLoc[],int input) {
		int a = grid[0]*grid[1]*grid[2];
		if(a==18 && input==3) {
		    winLoc[0] = loc(grid, 0, 1, 2);
			return true;
		}
		if(a==50 && input==5) {
			winLoc[0] = loc(grid, 0, 1, 2);
			return true;
		}
		a = grid[3]*grid[4]*grid[5];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 3, 4, 5);
			return true;
		}
		if(a==50 && input==5) {
			 winLoc[0] = loc(grid, 3, 4, 5);
			return true;
		}
	
		a = grid[6]*grid[7]*grid[8];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 6, 7, 8);
			return true;
		}
		if(a==50 && input==5) {
			 winLoc[0] = loc(grid, 6, 7, 8);
			return true;
		}
		
		
		
		a = grid[0]*grid[3]*grid[6];
		if(a==18 && input==3) {
		    winLoc[0] = loc(grid, 0, 3, 6);
			return true;
		}
		if(a==50 && input==5) {
			winLoc[0] = loc(grid, 0, 3, 6);
			return true;
		}
		
		
		
		a = grid[1]*grid[4]*grid[7];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 1, 4, 7);
			return true;
		}
		if(a==50 && input==5) {
			 winLoc[0] = loc(grid, 1, 4, 7);
			return true;
		}
		
		
		
		a = grid[2]*grid[5]*grid[8];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 2, 5, 8);
			return true;
		}
		if(a==50 && input==5) {
			 winLoc[0] = loc(grid, 2, 5, 8);
			return true;
		}
		
		
		
		a = grid[0]*grid[4]*grid[8];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 0, 4, 8);
			return true;
		}
		if(a==50 && input==5) {
			 winLoc[0] = loc(grid, 0, 4, 8);
			return true;
		}
		
		
		a = grid[2]*grid[4]*grid[6];
		if(a==18 && input==3) {
			 winLoc[0] = loc(grid, 2, 4, 6);
			return true;
		}
		if(a==50 && input==5) {
			winLoc[0] = loc(grid, 2, 4, 6);
			return true;
		}
		return false;
	}
	
	
	public static void goAnyWhere(int grid[],int input) {//Place at Any non blank position
		for(int i=0;i<9;i++) {
			if(grid[i]==2) {
				grid[i]=input;
			}
		}
	}
	

	public static int game(int grid[], int c,int input,boolean flag) {//Main game position.
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
				if(possWin(grid,winLoc,input==3?5:3)) {
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
				if(possWin(grid,winLoc,input)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					display(grid);
					return 1;
				}else if(possWin(grid,winLoc,input==3?5:3)){
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
				if(possWin(grid,winLoc,input)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					display(grid);
					return 1;
				}else if(possWin(grid,winLoc,input==3?5:3)){
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
				if(possWin(grid,winLoc,input)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input==3?5:3)){
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
				if(possWin(grid,winLoc,input)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input==3?5:3)){
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
				if(possWin(grid,winLoc,input)) {
					go(grid, winLoc[0], input);
					System.out.println("Nice try, Computer Won");
					return 1;
				}else if(possWin(grid,winLoc,input==3?5:3)){
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
