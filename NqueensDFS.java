package AI;

import java.util.ArrayList;
import java.util.Scanner;

public class NqueensDFS {
	public static boolean isCorrect(int i,int j,int k,int l) {
		if(i==k) return false;
		if(j==l) return false;
		if(Math.abs(i-k)==Math.abs(j-l)) return false;
		return true;
	}
    
    public static void nqueens(int grid[],int n,int idx,ArrayList<Integer> temp,ArrayList<Integer> ans,boolean f[]) {
		if(idx==n) {
			f[0]=true;
			ans.addAll(temp);
			return;
		}
		for(int c=0;c<n;c++) {
			if(idx==0 && f[0]!=true) {
				grid[idx]=c;
				temp.add(c+1);
				nqueens(grid, n, idx+1,temp,ans,f);
				temp.remove(temp.size()-1);
			}else if(f[0]!=true){
				boolean flag=true;
				for(int j=0;j<idx;j++) {
					if(!isCorrect(j, grid[j], idx, c)) {
						flag=false;
						break;
					}
				}
				if(flag) {
					grid[idx]=c;
					temp.add(c+1);
					nqueens(grid, n, idx+1,temp,ans,f);
					temp.remove(temp.size()-1);
				}else if(c<n && !flag){
					continue;
				}else {
					return;
				}
			}else {
				return;
			}
		}
	}
    static ArrayList<Integer> nQueen(int n) {
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        boolean flag[]=new boolean[1];
        int grid[]=new int[n];
        nqueens(grid,n,0,temp,ans,flag);
        return ans; 
    }
    
    public static void main(String[] args) {
    	System.out.println("Enter value of n: ");
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	System.out.println(nQueen(n));
    }
    
}
