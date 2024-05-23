package AI;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
//Why misplaced is admissible heuristic - It is clear that any tile which is misplaced must be moved at least once. (Not over-estimating)
//With Breadth first search and A* we should get same depth because optimality but difference will be in no of nodes.
public class EightPuzzleAStar {
	private static int numnodes = 0;
	private static Stack<State> s= new Stack<>();
	static class State implements Comparable<State>{
	    int grid[][];
	    int sr;
	    int sc;
	    int prev;
	    int depth;
	    int f;//f = depth+heuristics(Misplaced)
	    State parent;
	    public State(int grid[][],int sr,int sc,int prev,int depth,int f,State parent){
	        this.grid=grid;
	        this.sr=sr;
	        this.sc=sc;
	        this.prev=prev;
	        this.depth=depth;
	        this.f=f;
	        this.parent=parent;
	    }
		@Override
		public int compareTo(State o) {
			return this.f-o.f;
		}   
	}
	
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
	
	/*public static int heuristics(int state[][],int goalState[][]) {//Number of misplaced
		int h = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(state[i][j]!=goalState[i][j]) {
					h+=1;
				}
			}
		}
		return h;
	}*/
	
	public static int heuristics(int state[][],int goalState[][]) {//Manhattan distance
		int h = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int curr = state[i][j];
				for(int p=0;p<3;p++) {
					for(int q=0;q<3;q++) {
						if(goalState[p][q]==curr) {
							h+=(p-i+q-j);
						}
					}
				}
			}
		}
		return h;
	}
	
	public static String getStateIdentifier(int[][] state) {
        StringBuilder identifier = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                identifier.append(state[i][j]);
            }
        }
        return identifier.toString();
    }
	
	
	public static void eightPuzzle(int sr,int sc,int initialState[][],int goalState[][]) {
		 PriorityQueue<State> q = new PriorityQueue<>();//OPEN queue
		 HashSet<String> set = new HashSet<>();
	     int newState[][] = new int[3][3];
	     for (int i = 0; i < 3; i++) {
	        for(int j=0;j<3;j++) {
	        	newState[i][j] = initialState[i][j];
	        }    
	     }
	     int f = 0+heuristics(newState,goalState);
	     q.add(new State(newState,sr,sc,-1,0,f,null));
	     
	     while(!q.isEmpty()){
	            int count = q.size();
	            for(int i=0;i<count;i++){
	                State currState = q.poll();
	                int state[][]=currState.grid;               
	                if(isGoalState(goalState,state)) {
	                	System.out.println("Goal state reached");
	                	System.out.println("Depth: "+currState.depth);
	                	System.out.println("Path is: ");
	                	State temp = currState;
	                	while(temp!=null) {
	                		s.push(temp);
	                		temp=temp.parent;
	                	}
	                	System.out.println("Stack size: "+s.size());
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
	                	return;
	                }
	                String currentStateIdentifier = getStateIdentifier(state);
	       	     	if (set.contains(currentStateIdentifier)) {
	       	     		continue;
	       	     	}
	       	     	set.add(currentStateIdentifier);
	       	     	numnodes+=1;
	                int srow = currState.sr;
	                int scol = currState.sc;
	                int prev = currState.prev;
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
	        			q.add(new State(newstate,srow,scol-1,3,currState.depth+1,currState.depth+1+heuristics(state,goalState),currState));
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
	        			q.add(new State(newstate,srow-1,scol,0,currState.depth+1,currState.depth+1+heuristics(state,goalState),currState));
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
	        			q.add(new State(newstate,srow,scol+1,1,currState.depth+1,currState.depth+1+heuristics(state,goalState),currState));
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
	        			q.add(new State(newstate,srow+1,scol,2,currState.depth+1,currState.depth+1+heuristics(state,goalState),currState));
	        			temp = state[srow][scol];
	        			state[srow][scol] = 0;
	        			state[srow+1][scol] = temp;
	        		}		
	            }
	        }
	     System.out.println("No solution was found");
	}
	
	public static void main(String[] args) {
		int initialState[][]= {
				{8, 7, 1},
				{6, 5, 4},
				{3, 2, 0 },
		};
		int goalState[][]= {	
				{1, 2, 3},
				{8, 4, 5},
				{7, 6, 0}
		}; //Depth - 22, Nodes - 6405 (Depth+Misplaced), Depth - 22, Nodes - 82380 (Depth+Manhattan)
		
		/*int initialState[][]= {
				{3, 1, 2},
				{8, 4, 5},
				{7, 6, 0 },
		};
		int goalState[][]= {
				
				{1, 2, 3},
				{8, 4, 5},
				{7, 6, 0}
				
				
		};*/ //Depth - 16, Nodes - 526 (Depth+Misplaced), Depth - 16, Nodes - 8458(Depth+Manhattan)
		
		
		
		/*int initialState[][]= {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5},
		};
		int goalState[][]= {
			{1, 2, 3},
			{7, 8, 4},
			{6, 0, 5}
		};*/ //Depth - 3, Nodes - 4 (Depth+Misplaced), Depth - 3, Nodes - 15 (Depth+Manhattan)
		
		
		/*int initialState[][]= {
			{2, 8, 3},
			{1, 6, 4},
			{7, 0, 5},
		};
		int goalState[][]= {
			{1, 2, 3},
			{7, 8, 4},
			{0, 6, 5}
		};*/ //Depth - 5, Nodes - 6 (Depth+Misplaced), Depth - 5, Nodes - 36 (Depth+Manhattan)
		
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
		System.out.println("Nodes: "+numnodes);
	}
}
