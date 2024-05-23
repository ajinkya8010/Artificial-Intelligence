package AI;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.w3c.dom.ls.LSOutput;

public class TicTacToeARI {
	
	public static char toss() {
		char arr[]= {'X','O'};
		Random rand = new Random();
		return arr[rand.nextInt(2)];
	}
	
	public static void takeInput(char grid[],char input){
	    System.out.println("Where you want to place "+input);
	    Scanner sc = new Scanner(System.in);
	    int i = sc.nextInt();
	    grid[i-1]=input;
	}
	
	
	
	
	public static boolean isWinning(char grid[],char input) {
		int cnt = 0;
		for(int i=0;i<3;i++) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=3;i<6;i++) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=6;i<9;i++) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=0;i<=6;i+=3) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=1;i<=7;i+=3) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=2;i<=8;i+=3) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=0;i<=8;i+=4) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		cnt = 0;
		for(int i=2;i<=6;i+=2) {
			if(grid[i]==input) {
				cnt++;
			}
		}
		if(cnt==3) return true;
		return false;
	}
	
	public static boolean isDraw(char grid[]) {
		for(int i=0;i<grid.length;i++) {
			if(grid[i]=='-') {
				return false;
			}
		}
		return true;
	}
	
	public static int minMax(char grid[],int depth, boolean isMax,char input) {
		if(isWinning(grid, input)) {
			if(input=='X') return 1;
			return -1;
		}
		if(isDraw(grid)) {
			return 0;
		}
		
		if(isMax) {
			int bestScore = Integer.MIN_VALUE;
			for(int i=0;i<grid.length;i++) {
				if(grid[i]=='-') {
					grid[i]=input;
					int score = minMax(grid, depth+1, false, 'O');
					grid[i]='-';
					if(score>bestScore) {
						bestScore=score;
					}
				}
			}
			return bestScore;
		}else {
			int bestScore = Integer.MAX_VALUE;
			for(int i=0;i<grid.length;i++) {
				if(grid[i]=='-') {
					grid[i]=input;
					int score = minMax(grid, depth+1, true, 'X');
					grid[i]='-';
					if(score<bestScore) {
						bestScore=score;
					}
				}
			}
			return bestScore;
		}
		
	}
	
	
	public static int game(char grid[],char input,boolean iflag) {
		
		if(iflag) {
			takeInput(grid, input);
			System.out.println(input+ " played: ");
			for(int i=0;i<9;i++) {
				System.out.print(grid[i]+" ");
				if(i%3==2) {
					System.out.println();
				}
			}
		}else {
			
			int bestScore = -1;
			if(input=='X') {
				bestScore=Integer.MIN_VALUE;
			}else {
				bestScore=Integer.MAX_VALUE;
			}
			int loc=-1;
			for(int i=0;i<9;i++) {
				if(grid[i]=='-') {
					grid[i]=input;
					int h=0;
					if(input=='X') {
						h = minMax(grid,0,false,'O');
					}else {
						h= minMax(grid,0,true,'X');
					}
					grid[i]='-';
					if(input=='X') {
						if(h>bestScore) {
							bestScore=h;
							loc=i;
						}	
					}else {
						if(h<bestScore) {
							bestScore=h;
							loc=i;
						}
					}
					
				}
			}
			grid[loc]=input;
			System.out.println(input+ " played: ");
			System.out.println("Heuristic is: "+ bestScore);
			for(int i=0;i<9;i++) {
				System.out.print(grid[i]+" ");
				if(i%3==2) {
					System.out.println();
				}
			}
		}
		int result = 0;

	    if (isWinning(grid, 'X')) {
	        System.out.println("X won");
	        result = 1;
	    } else if (isWinning(grid, 'O')) {
	        System.out.println("O won");
	        result = -1;
	    } else if (isDraw(grid)) {
	        System.out.println("Match Draw");
	    }

	    return result;
		
		
	}
	
	public static void main(String[] args) {
		char grid[]=new char[9];
		Arrays.fill(grid, '-');
		System.out.println("What do you want to Choose (X/O)");
		Scanner sc = new Scanner(System.in);
		char input = sc.next().charAt(0);
		System.out.println("Let's toss a coin");
		char t = toss();
		System.out.println(t+" Will play first");
		boolean iflag = true;
		if(input!=t){
		    input = t;
		    iflag = false;
		}
		int c = 1;
		while(c<=9) {
			int g = game(grid,input,iflag);
			if(g == 1) {
				break;
			}else if(g==-1) {
				break;
			}
			iflag=(!iflag);
			c++;
			input = input=='X'?'O':'X';
		}	
	}
}
