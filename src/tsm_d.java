import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class tsm_d {

    static int N=4;
    static int STARTNODE=0;
    static int Finished_state=(1<<N)-1;
    static int npow=(int) Math.pow(2,N);
    static Double cost_matrix[][]= {{0.0,10.0,15.0,20.0},{5.0,0.0,9.0,10.0},{6.0,13.0,0.0,12.0},{8.0,8.0,9.0,0.0}};
    static Double memo[][]=new Double[N][1<<N];
    static int prev[][]=new int[N][1<<N];


    static int state=1<< STARTNODE;



    public static void print_memo(){
        for(int i=0;i < N; i++){
            for(int j=0;j <npow;j++){
                System.out.print(memo[i][j]+ "   ");
            }

            System.out.println( );
        }
        System.out.println("prev");
        for(int i=0;i < N; i++){
            for(int j=0;j <npow;j++){
                System.out.print(prev[i][j]+ "   ");
            }

            System.out.println( );
        }
    }
    public static void initialize_memo() {
        /*for(int i=0;i<N;i++){
            for(int j=0;j<npow;j++) {
                memo[i][j] = -1;
                prev[i][j]=-1;
            }
        }*/
        for(int i=0;i<N;i++){
            memo[i][0]=cost_matrix[i][0];
        }

    }

    public static double tsp(int curr, int state, Double[][] memo, int[][] prev) {

        if(state== Finished_state) return cost_matrix[curr][STARTNODE];
        if(memo[curr][state]!=null) return memo[curr][state];

        double mincost=Double.MAX_VALUE;
        int index=-1;
        for(int next=0;next<N;next++){
            //skip if next has aleady been visited
            if((state & (1<<next))!=0) continue;
            int next_state= state|(1<<next);//next=1 so nextstate=0010 |0001=0011
            double currCost=cost_matrix[curr][next] +tsp(next,next_state,memo,prev);
            if(currCost<mincost){
                mincost=currCost;
                index=next;
            }
        }
            prev[curr][state]=index;
            return memo[curr][state] = mincost;
    }

    public static void main(String [] args){
    initialize_memo();
        //print memo

        double tsp = tsp(0, state, memo, prev);
        //get tour
        int tour[]=new int[N];
        int t=0;
        print_memo();
        int index=STARTNODE;
        while(t<N){
            tour[t]=index;
            t+=1;
            int next=prev[index][state];
            int next_state=state|(1<<next);
            state=next_state;
            index=next;

        }
      for(int i=0;i<N;i++){
          System.out.print(tour[i]+"--->");
      }
       System.out.print(STARTNODE);

    }
}
