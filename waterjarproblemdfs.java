package AI;

import java.util.*;


public class waterjarproblemdfs {
	static int count = 0;
	private static Stack<State> st= new Stack<>();
	static class State {
		int x;// Max capacity - 4
		int y;// Max capacity - 3
		int depth;
		State parent;
		public State(int x, int y,int depth,State parent) {
			this.x = x;
			this.y = y;
			this.depth=depth;
			this.parent=parent;
		}
	}

	public static void solveDFS(int j1, int j2, int dp[][]) {
		Stack<State> s = new Stack<>();
		s.push(new State(j1, j2,0,null));
		while(!s.empty()) {
			
			State currentStateInfo = s.pop();
			int jug1 = currentStateInfo.x;
			int jug2 = currentStateInfo.y;
			int depth=currentStateInfo.depth;
			if (jug1 == 2) {
				State curr = currentStateInfo;
	        	while(curr!=null) {
	        		st.push(curr);
	        		curr = curr.parent;
	        	}
	        	
	        	while(!st.isEmpty()) {
	        		curr = st.pop();
	        		System.out.println("4gallon jug: "+curr.x+" 3gallon jug: "+curr.y);
	        	}
				
				System.out.println("Goal state reached");
				System.out.println("Depth: " + depth);
				System.out.println("Nodes explored: "+count);
				return;
			}
			
			if (dp[jug1][jug2] != -1) {
				continue;
			}
			
			dp[jug1][jug2] = 1;
			count+=1;
			
			// Fill 4 gallon jug
			if (jug1 < 4) {
				s.push(new State(4,jug2,depth+1,currentStateInfo));
			}

			// Fill 3 gallon jug
			if (jug2 < 3) {
				s.push(new State(jug1,3,depth+1,currentStateInfo));
			}

			// Empty 4 gallon jug
			if (jug1 > 0) {
				s.push(new State(0,jug2,depth+1,currentStateInfo));
			}

			// Empty 3 gallon jug
			if (jug2 > 0) {
				s.push(new State(jug1,0,depth+1,currentStateInfo));
			}

			// Transfer from 4 gallon to 3 gallon
			// Till 4 is empty
			if (jug2 == 0 && jug1 > 0 && jug1 <= 3) {
				s.push(new State(0,jug1,depth+1,currentStateInfo));
			} else if (jug2 == 1 && jug1 > 0 && jug1 <= 2) {
				s.push(new State(0,1+jug1,depth+1,currentStateInfo));
			} else if (jug2 == 2 && jug1 == 1) {
				s.push(new State(0,2+jug1,depth+1,currentStateInfo));
			}

			// Till 3 is full
			if (jug2 == 0 && jug1 >= 3) {
				s.push(new State(jug1-3,3,depth+1,currentStateInfo));
			} else if (jug2 == 1 && jug1 >= 2) {
				s.push(new State(jug1-2,3,depth+1,currentStateInfo));
			} else if (jug2 == 2 && jug1 >= 1) {
				s.push(new State(jug1-1,3,depth+1,currentStateInfo));
			}

			// Transfer from 3 gallon to 4 gallon
			// Till 3 is empty
			if (jug2 == 1 && jug1 <= 3) {
				s.push(new State(jug1+1,0,depth+1,currentStateInfo));
			} else if (jug2 == 2 && jug1 <= 2) {
				s.push(new State(jug1+2,0,depth+1,currentStateInfo));
			} else if (jug2 == 3 && jug1 <= 1) {
				s.push(new State(jug1+3,0,depth+1,currentStateInfo));
			}

			// Till 4 is full
			if (jug2 >= 1 && jug1 == 3) {
				s.push(new State(4,jug2-1,depth+1,currentStateInfo));
			} else if (jug2 >= 2 && jug1 == 2) {
				s.push(new State(4,jug2-2,depth+1,currentStateInfo));
			} else if (jug2 == 3 && jug1 == 1) {
				s.push(new State(4,0,depth+1,currentStateInfo));
			}
		}	
	}

	public static void main(String[] args) {
		int dp[][] = new int[5][4];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				dp[i][j] = -1;
			}
		}
		solveDFS(0, 0, dp);
	}
}