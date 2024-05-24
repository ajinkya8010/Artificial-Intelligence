package AI;
import java.util.*;
public class MnC {
    
    public static void printPath(State s)// printing the path
    {
        Stack<State> path = new Stack<>();

        while(s.parent!=null)
        {
            path.push(s);
            s = s.parent;
        }
        while(!path.empty())
        {
            State b = path.pop();
            System.out.println("left "+b.LM+", "+b.LC+", "+b.boat+" right "+b.RM+", "+b.RC+", boat is on the "+b.side);
        }
        
    }

    public static boolean checkState(State s)
    {
        if((s.LM>=0 && s.LC>=0 && s.RM>=0 && s.RC>=0) && (s.LM==0 || s.LM>=s.LC)&& (s.RM==0 || s.RM>=s.RC))// check if all are above zero
        {
            return true;
        }
        return false;
    }

    public static void DFS( Stack<State> stack,int[][][] visited)
    {
        State start = new State(3,3,0,0,1,"left",null);// start state is LHS(3,3,1) goal is (0,0,0)
        stack.push(start);
        while (!stack.isEmpty()) 
        {
            State newNode = stack.pop();
            visited[newNode.LM][newNode.LC][newNode.boat] = 1;
            //check for goal state
            if(newNode.LM==0 && newNode.LC ==0 && newNode.boat==0)// check for goal state
            {
                System.out.println("Goal state reached "+newNode.LM+", "+newNode.LC+", "+newNode.boat);
                printPath(newNode);
                break;
            }
            if(newNode.boat==1)//boat is on left, all possible left to right successors
            {
                System.out.println("Going left to right");
                System.out.println("current state: left- "+newNode.LM+", "+newNode.LC+" right "+newNode.RM+", "+newNode.RC+"  boat is "+newNode.boat);
                //generate all possible successors
                for(int i=0;i<=newNode.LM;i++)// finding possible combination of i,j = missionary,cannibal
                {
                    for(int j=0;j<=newNode.LC;j++)
                    {
                        if((i+j)>0 && (i+j)<=2)// boat cannot be empty and can carry max of 2 people
                        {
                            State newState = new State(newNode.LM-i, newNode.LC-j, newNode.RM+i, newNode.RC+j, 0, "right", newNode);
                            if(checkState(newState))//check if state is valid
                            {
                                if(visited[newState.LM][newState.LC][newState.boat]!=1)
                                stack.push(newState);
                            }
                        }
                    }
                }
            }
            else// if boat is on right side then generate right to left successors
            {
                System.out.println("Going right to left");
                System.out.println("current state: left- "+newNode.LM+", "+newNode.LC+" right "+newNode.RM+", "+newNode.RC+"  boat is "+newNode.boat);
                for(int i=0;i<=newNode.RM;i++)
                {
                    for(int j = 0;j<=newNode.RC;j++)
                    {
                        if((i+j)<=2 && ((i>0) || (j>0)))//since boat can carry at max 2 people and cannot have empty boat
                        {
                            State newState = new State(newNode.LM+i, newNode.LC+j, newNode.RM-i, newNode.RC-j, 1,"left", newNode);
                            if(checkState(newState))
                            {
                                System.out.println("i is "+i+" j is "+j);
                                System.out.println("Found state "+newState.LM+","+newState.LC);
                                if(visited[newState.LM][newState.LC][newState.boat]!=1)
                                stack.push(newState);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] Args)
    {
        Stack<State> stack = new Stack<>();
        int[][][] visited = new int[4][4][2];
        DFS(stack,visited);
    }
}
class State
{
    int LM;// numberr of left missionary
    int LC;// numberr of right cannibal
    int RM;// number of right missionary
    int RC;// numberr of right cannibal
    int boat;// presence of boat
    String side;// the side where the boat is
    State parent;//parent node for backtracking
    
    State(int LM,int LC,int RM,int RC,int boat,String side,State parent)
    {
        this.LM = LM;
        this.LC = LC;
        this.RM = RM;
        this.RC= RC;
        this.boat = boat;
        this.side = side;
        this.parent = parent;
    }
}