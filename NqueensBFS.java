package AI;

import java.util.ArrayList;
import java.util.LinkedList;
import java .util.Queue;
class Node{
    int grid[];
    int idx;
    public Node(int grid[],int idx){
        this.grid=grid;
        this.idx=idx;
    }
}


public class NqueensBFS {
	public static int num_nodes = 0;
    public static boolean isCorrect(int i,int j,int k,int l) {
        if(i==k) return false;
        if(j==l) return false;
        if(Math.abs(i-k)==Math.abs(j-l)) return false;
        return true;
    }
    
    public static int[] nqueens(int n) {
        Queue<Node> q = new LinkedList<>();
        int grid[]=new int[n];
        q.add(new Node(grid,-1));
        while(!q.isEmpty()){
            int count = q.size();
            for(int i=0;i<count;i++){
                Node node = q.poll();
                int g[]=node.grid;
                int index = node.idx;
                if(index==grid.length-1){
                    return g;
                }
                num_nodes+=1;
                for(int c=0;c<n;c++) {
                    if(index>=0){
                    	boolean flag=true;
                    	for(int k=0;k<=index;k++) {	
                    		if(!isCorrect(k, g[k], index+1, c)){
                    			flag=false;
                    			break;
                            }
                    	}
                    	if(flag) {
                    		int newg[]=g.clone();
                            newg[index+1]=c;
                            q.add(new Node(newg,index+1));
                    	}
                    }else{
                    	int newg[]=g.clone();
                        newg[index+1]=c;
                        q.add(new Node(newg,index+1));
                    }           
                }
            }
        }
        System.out.println("hi");
        return grid;   
    }
    public static void main(String[] args){
        int n = 8;
        int grid[] = nqueens(n);
        for(int i=0;i<n;i++){
            System.out.print(grid[i]+" ");
        }
        System.out.println("\nNumber of nodes processed: "+num_nodes);
    }
}