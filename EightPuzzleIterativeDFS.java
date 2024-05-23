package AI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import AI.EightPuzzleAStar.State;

public class EightPuzzleIterativeDFS {
	private static Set<String> visitedStates = new HashSet<>();
	private static int ans[][] = new int[3][3];
	private static Stack<StateInfo> s = new Stack<>();
	public static boolean isGoalState(int goalState[][], int state[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (goalState[i][j] != state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static class StateInfo {
		int[][] state;
		int row;
		int col;
		int prev;
		int depth;
		StateInfo parent;
		public StateInfo(int[][] state, int row, int col, int prev, int depth,StateInfo parent) {
			this.state = copyState(state);
			this.row = row;
			this.col = col;
			this.prev = prev;
			this.depth = depth;
			this.parent=parent;
		}
	}

	private static int[][] copyState(int[][] state) {
		int[][] copy = new int[state.length][];
		for (int i = 0; i < state.length; i++) {
			copy[i] = Arrays.copyOf(state[i], state[i].length);
		}
		return copy;
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

	public static boolean eightPuzzle(int srow, int scol, int initialState[][], int goalState[][], int maxDepth) {

		Stack<StateInfo> stack = new Stack<>();
		stack.push(new StateInfo(initialState, srow, scol, -1, 0,null));
		while (!stack.isEmpty()) {
			StateInfo currentStateInfo = stack.pop();
			int[][] currentState = currentStateInfo.state;
			int sr = currentStateInfo.row;
			int sc = currentStateInfo.col;
			int prev = currentStateInfo.prev;
			int depth = currentStateInfo.depth;
			if (depth > maxDepth) {
				continue;
			}
			String currentStateIdentifier = getStateIdentifier(currentState);
			
			if (isGoalState(goalState, currentState)) {
				
				StateInfo temp = currentStateInfo;
            	while(temp!=null) {
            		s.push(temp);
            		temp=temp.parent;
            	}
            	System.out.println("Stack size: "+s.size());
            	while(!s.isEmpty()) {
            		temp=s.pop();
            		for(int k=0;k<3;k++) {
            			for(int j=0;j<3;j++) {
            				System.out.print(temp.state[k][j]+" ");
            			}
            			System.out.println();
            		}
            		System.out.println();
            	}
				
				System.out.println("Depth: " + depth);
				System.out.println("Reached goal state");
				ans = copyState(currentState);
				return true;
			}

			if (visitedStates.contains(currentStateIdentifier)) {
				continue;
			}
			
			visitedStates.add(currentStateIdentifier);

			if (prev != 1 && sc > 0) {// left
				int temp = currentState[sr][sc - 1];
				currentState[sr][sc - 1] = 0;
				currentState[sr][sc] = temp;
				stack.push(new StateInfo(copyState(currentState), sr, sc - 1, 3, depth + 1,currentStateInfo));
				// Backtracking
				temp = currentState[sr][sc];
				currentState[sr][sc] = 0;
				currentState[sr][sc - 1] = temp;
			}

			if (prev != 2 && sr > 0) {// up
				int temp = currentState[sr - 1][sc];
				currentState[sr - 1][sc] = 0;
				currentState[sr][sc] = temp;
				stack.push(new StateInfo(copyState(currentState), sr - 1, sc, 0, depth + 1,currentStateInfo));
				// Backtracking
				temp = currentState[sr][sc];
				currentState[sr][sc] = 0;
				currentState[sr - 1][sc] = temp;
			}

			if (prev != 3 && sc < 2) {// right
				int temp = currentState[sr][sc + 1];
				currentState[sr][sc + 1] = 0;
				currentState[sr][sc] = temp;
				stack.push(new StateInfo(copyState(currentState), sr, sc + 1, 1, depth + 1,currentStateInfo));
				// Backtracking
				temp = currentState[sr][sc];
				currentState[sr][sc] = 0;
				currentState[sr][sc + 1] = temp;
			}

			if (prev != 0 && sr < 2) {// down
				int temp = currentState[sr + 1][sc];
				currentState[sr + 1][sc] = 0;
				currentState[sr][sc] = temp;
				stack.push(new StateInfo(copyState(currentState), sr + 1, sc, 2, depth + 1,currentStateInfo));
				// Backtracking
				temp = currentState[sr][sc];
				currentState[sr][sc] = 0;
				currentState[sr + 1][sc] = temp;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		
		/*int initialState[][]= {
				{8, 7, 1},
				{6, 5, 4},
				{3, 2, 0 },
		};
		int goalState[][]= {	
				{1, 2, 3},
				{8, 4, 5},
				{7, 6, 0}
		};*/
		
		int initialState[][]= {
			{3, 1, 2},
			{8, 4, 5},
			{7, 6, 0 },
		};
		int goalState[][]= {
			{1, 2, 3},
			{8, 4, 5},
			{7, 6, 0}
		};
		
		
		/*int initialState[][]= {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5},
		};
		int goalState[][]= {
			{1, 2, 3},
			{7, 8, 4},
			{6, 0, 5}
		};*/
		
		
		int sr = 0, sc = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (initialState[i][j] == 0) {
					sr = i;
					sc = j;
				}
			}
		}
		// System.out.println(sr+""+sc);
		// 0-up,1-right,2-down,3-left
		int k;
		for (k = 0; k < 22; k++) {
			visitedStates.clear();
			boolean res = eightPuzzle(sr, sc, initialState, goalState, k);
			if (res)
				break;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}
}
