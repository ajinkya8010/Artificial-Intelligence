package AI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;



public class ShortestPathHillClimbing {
	private static Stack<Integer> st = new Stack<>();
	public static class state implements Comparable<state>{
		int index;
		int heuristics;
		state parent;
		int cost;
		public state(int index,int heuristics,state parent,int cost) {
			this.index=index;
			this.heuristics=heuristics;
			this.parent=parent;
			this.cost=cost;
		}
		@Override
		public int compareTo(state o) {
			return this.heuristics-o.heuristics;
		} 
	}
	
	public static int dfs(int adjMatrix[][],int s,int d,HashMap<Integer,Integer> map) {
		HashSet<Integer> set = new HashSet<>();
		int h = map.get(s);
		state currState = new state(s,h,null,0);
		int path = 0;
		System.out.print(currState.index+" ");
		boolean isGoalReached = false;
		while(!isGoalReached) {
			state minState=null;
			int min = Integer.MAX_VALUE;
			int curridx = currState.index;
			if(curridx==d) {
				System.out.println();
				System.out.println("Destination reached!");
				return currState.cost;
			}
			if (set.contains(curridx)) {
   	     		continue;
   	     	}
			for(int j=0;j<adjMatrix.length;j++) {
				if(adjMatrix[curridx][j]!=0 && adjMatrix[curridx][j]!=9999) {
					int heu = map.get(j);
					if(heu<min) {
						min=heu;
						minState = new state(j,heu,currState,currState.cost+adjMatrix[curridx][j]);
					}
				}
			}
			currState=minState;
			System.out.print(currState.index+" ");
		}
		return path;
	}
	
	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of nodes: ");
		int n = sc.nextInt();
		System.out.println("Enter the number of edges: ");
		int e = sc.nextInt();
		int adjMatrix[][]=new int[n][n];
		for(int i=0;i<e;i++) {
			System.out.println("Enter two ends of edges: ");
			int one = sc.nextInt();
			int two = sc.nextInt();
			System.out.println("Enter the cost between the edges: ");
			int cost = sc.nextInt();
			adjMatrix[one][two] = cost;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j && adjMatrix[i][j]==0) {
					adjMatrix[i][j]=9999;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(adjMatrix[i][j]+" "); 
			}
			System.out.println();
		}*/
		
		int adjMatrix[][]= {
				{0,75,9999,140,9999,9999,9999,9999},
				{75,0,71,9999,9999,9999,9999,9999},
				{9999,71,0,151,9999,9999,9999,9999},
				{140,9999,151,0,80,99,9999,9999},
				{9999,9999,9999,80,0,9999,97,9999},
				{9999,9999,9999,99,9999,0,9999,211},
				{9999,9999,9999,9999,97,9999,0,101},
				{9999,9999,9999,9999,9999,211,101,0}
		};
		//Source - 0, Destination - 7
		HashMap<Integer,Integer> map = new HashMap<>();
		//Heuristics(stright line distances from each city to 7)
		map.put(0, 366);
		map.put(1, 374);
		map.put(2, 380);
		map.put(3, 253);
		map.put(4, 193);
		map.put(5, 178);
		map.put(6, 98);
		map.put(7, 0);
		System.out.print("Path is: ");
		int cost = dfs(adjMatrix,0,7,map);
		System.out.println();
		System.out.println("Path cost is: "+cost);
		
		
		
	}
	
}

