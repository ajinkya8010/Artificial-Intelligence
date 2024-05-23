package AI;

import java.util.*;

import AI.waterjarproblemdfs.State;


public class waterjarproblembfs {
	private static Stack<State> s= new Stack<>();
	static class State {
        int x;//Max capacity - 4
        int y;//Max capacity - 3
        State parent;
        public State(int x, int y,State parent) {
            this.x = x;
            this.y = y;
            this.parent=parent;
        }
    }

    public static void solveBFS(int jug1, int jug2, int dp[][]) {

        Queue<State> q = new LinkedList<>();
        q.add(new State(0, 0,null));
        int count = 0;
        int depth = 0;
        while (!q.isEmpty()) {

            int size = q.size();
            
            for(int i=0;i<size;i++) {
            	State temp = q.poll();
            	count++;
            	int curr4g = temp.x;
            	int curr3g = temp.y;
            	if(dp[curr4g][curr3g]!=-1) {
            		continue;
            	}
            	dp[curr4g][curr3g]=1;
                if(curr4g==2) {
                	
                	State curr = temp;
                	while(curr!=null) {
                		s.push(curr);
                		curr = curr.parent;
                	}
                	
                	while(!s.isEmpty()) {
                		curr = s.pop();
                		System.out.println("4gallon jug: "+curr.x+" 3gallon jug: "+curr.y);
                	}
                	
                	System.out.println("Goal state reached");
                	System.out.println("Depth: "+depth);
                	System.out.println("Nodes explored: "+count);
                	return;
                }
                
                
                //Fill 4 gallon jug
                if(curr4g<4) {
                	q.add(new State(4,curr3g,temp));
                }
               
                //Fill 3 gallon jug
                if(curr3g<3) {
                	q.add(new State(curr4g,3,temp));
                }
                
                
                //Empty 4 gallon jug
                if(curr4g>0) {
                	q.add(new State(0,curr3g,temp));
                }
                
                //Empty 3 gallon jug
                if(curr3g>0) {
                	q.add(new State(curr4g,0,temp));
                }
                
                //Transfer from 4 gallon to 3 gallon
                //Till 4 is empty
                if(curr3g==0 && curr4g>0 && curr4g<=3) {
                	q.add(new State(0,curr4g,temp));
                }else if(curr3g==1 && curr4g>0 && curr4g<=2) {
                	q.add(new State(0,1+curr4g,temp));
                }else if(curr3g==2 && curr4g==1) {
                	q.add(new State(0,2+curr4g,temp));
                }
                
                //Till 3 is full
                if(curr3g==0 && curr4g>=3) {
                	q.add(new State(curr4g-3,3,temp));
                }else if(curr3g==1 && curr4g>=2) {
                	q.add(new State(curr4g-2,3,temp));
                }else if(curr3g==2 && curr4g>=1) {
                	q.add(new State(curr4g-1,3,temp));
                }
                
                //Transfer from 3 gallon to 4 gallon
                //Till 3 is empty
                if(curr3g==1 && curr4g<=3) {
                	q.add(new State(curr4g+1,0,temp));
                }else if(curr3g==2 && curr4g<=2) {
                	q.add(new State(curr4g+2,0,temp));
                }else if(curr3g==3 && curr4g<=1) {
                	q.add(new State(curr4g+3,0,temp));
                } 
                
                //Till 4 is full
                if(curr3g>=1 && curr4g==3) {
                	q.add(new State(4,curr3g-1,temp));
                }else if(curr3g>=2 && curr4g==2) {
                	q.add(new State(4,curr3g-2,temp));
                }else if(curr3g==3 && curr4g==1) {
                	q.add(new State(4,0,temp));
                }
        
            } 
            depth+=1;
        }
    }

    
    public static void main(String[] args) {
    	int dp[][] = new int[5][4];
    	for(int i=0;i<5;i++) {
    		for(int j=0;j<4;j++) {
    			dp[i][j]=-1;
    		}
    	}
        solveBFS(0, 0, dp);//nodes exp - 41, depth - 6
      //Without keeping track of visited states: nodes exp - 434, depth - 6
    }
    

}