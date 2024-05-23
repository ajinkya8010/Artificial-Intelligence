package AI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import AI.EightPuzzleAStar.State;

class Nodee{
    int grid[][];
    int sr;
    int sc;
    int prev;
    int depth;
    Nodee parent;
    public Nodee(int grid[][],int sr,int sc,int prev,int depth,Nodee parent){
        this.grid=grid;
        this.sr=sr;
        this.sc=sc;
        this.prev=prev;
        this.depth=depth;
        this.parent=parent;
    }   
}

public class EightPuzzleBFS {
	private static int numnodes = 0;
	private static Stack<Nodee> s= new Stack<>();
	private static int ans[][]=new int[3][3];
	public static boolean isGoalState(int goalState[][], int state[][]) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(goalState[i][j]!=state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	

	public static void eightPuzzle(int sr,int sc,int initialState[][],int goalState[][]) {
        Queue<Nodee> q = new LinkedList<>();
        int newState[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
        	for(int j=0;j<3;j++) {
        		newState[i][j] = initialState[i][j];
        	}
            
        }
        q.add(new Nodee(newState,sr,sc,-1,0,null));
        while(!q.isEmpty()){
            int count = q.size();
            for(int i=0;i<count;i++){
                Nodee node = q.poll();
                int state[][]=node.grid;               
                if(isGoalState(goalState,state)) {
                	Nodee temp = node;
                	while(temp!=null) {
                		s.push(temp);
                		temp=temp.parent;
                	}
                	while(!s.isEmpty()) {
                		temp=s.pop();
                		for(int k=0;k<3;k++) {
                			for(int j=0;j<3;j++) {
                				System.out.print(temp.grid[k][j]+" ");
                			}
                			System.out.println();
                		}
                		System.out.println();
                	}
                	System.out.println("Goal state reached");
                	System.out.println("Depth: "+node.depth);
                	for(int k=0;k<3;k++) {
                		for(int j=0;j<3;j++) {
                			ans[k][j]=state[k][j];
                		}
                	}
                	return;
                }
                int srow = node.sr;
                int scol = node.sc;
                int prev = node.prev;
                numnodes+=1;
                if(prev!=1 && scol>0) {//left
        			int temp = state[srow][scol-1];
        			state[srow][scol-1] = 0;
        			state[srow][scol] = temp;
        			int newstate[][] = new int[3][3];
        	        for(int k=0;k<3;k++) {
                		for(int j=0;j<3;j++) {
                			newstate[k][j]=state[k][j];
                		}
                	}
        			q.add(new Nodee(newstate,srow,scol-1,3,node.depth+1,node));
        			//Backtracking
        			temp = state[srow][scol];
        			state[srow][scol] = 0;
        			state[srow][scol-1] = temp;
        		}
        		
        		if(prev!=2 && srow>0) {//up
        			int temp = state[srow-1][scol];
        			state[srow-1][scol] = 0;
        			state[srow][scol] = temp;
        			int newstate[][] = new int[3][3];
        			 for(int k=0;k<3;k++) {
                 		for(int j=0;j<3;j++) {
                 			newstate[k][j]=state[k][j];
                 		}
                 	}
        			q.add(new Nodee(newstate,srow-1,scol,0,node.depth+1,node));
        			//Backtracking
        			temp = state[srow][scol];
        			state[srow][scol] = 0;
        			state[srow-1][scol] = temp;
        		}
        		
        		if(prev!=3 && scol<2) {//right
        			int temp = state[srow][scol+1];
        			state[srow][scol+1] = 0;
        			state[srow][scol] = temp;
        			int newstate[][] = new int[3][3];
        			for(int k=0;k<3;k++) {
                 		for(int j=0;j<3;j++) {
                 			newstate[k][j]=state[k][j];
                 		}
                 	}
        			q.add(new Nodee(newstate,srow,scol+1,1,node.depth+1,node));
        			//Backtracking
        			temp = state[srow][scol];
        			state[srow][scol] = 0;
        			state[srow][scol+1] = temp;
        		}
        		
        		if(prev!=0 && srow<2) {//down
        			int temp = state[srow+1][scol];
        			state[srow+1][scol] = 0;
        			state[srow][scol] = temp;
        			int newstate[][] = new int[3][3];
        			for(int k=0;k<3;k++) {
                 		for(int j=0;j<3;j++) {
                 			newstate[k][j]=state[k][j];
                 		}
                 	}
        			q.add(new Nodee(newstate,srow+1,scol,2,node.depth+1,node));
        			//Backtracking
        			temp = state[srow][scol];
        			state[srow][scol] = 0;
        			state[srow+1][scol] = temp;
        		}
        		
            }
        }
	     System.out.println("No solution was found");
    }
	
	
	
	
	public static void main(String[] args) {
		/*int initialState[][]= {
				{3, 1, 2},
				{8, 4, 5},
				{7, 6, 0 },
		};
		int goalState[][]= {
				
				{1, 2, 3},
				{8, 4, 5},
				{7, 6, 0}
				
				
		};*/ //16 depth
		
		int initialState[][]= {
				{8, 7, 1},
				{6, 5, 4},
				{3, 2, 0 },
		};
		int goalState[][]= {
				
				{1, 2, 3},
				{8, 4, 5},
				{7, 6, 0}
				
				
		}; //22 depth
		

		/*int initialState[][]= {
				{8, 7, 1},
				{6, 5, 4},
				{3, 2, 0 },
		};
		int goalState[][]= {
				
				{1, 3, 2},
				{4, 0, 5},
				{7, 6, 8}
				
		};*/
		
		int sr=0,sc=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(initialState[i][j]==0) {
					sr=i;
					sc=j;
				}
			}
		}
		//int prev = -1;
		//0-up,1-right,2-down,3-left
		eightPuzzle(sr,sc,initialState,goalState);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("Nodes: "+numnodes);
	}
	
	
	
}
