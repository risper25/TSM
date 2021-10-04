import java.util.*;

public class tsm {
    static int[][] two_dim_cost_matrix= new int[100][100];
    static boolean[] visited_copy_array = new boolean[100];
    static int no_of_vertices,current_vertex,total_min_cost = 0;

    public static void inputs(){
        Scanner s=new Scanner(System.in);
        
        System.out.println("enter number of cities");
        no_of_vertices=s.nextInt();
        System.out.println("enter the starting city: ");
        current_vertex=s.nextInt();


        for(int i=0;i < no_of_vertices; i++){
            for(int j=0;j < no_of_vertices;j++){
                if(i==j){
                    two_dim_cost_matrix[i][j]=0;
                }
                else {
                    System.out.println("Enter the cost between the path: "+(i+1)+ "---->"+(j+1)+": ");
                    two_dim_cost_matrix[i][j]=s.nextInt();
                }
            }

        }
        //print the cost matrix
        System.out.println("cost matrix");
        for(int i=0;i < no_of_vertices; i++){
            for(int j=0;j < no_of_vertices;j++){
                System.out.print(two_dim_cost_matrix[i][j]+ "   ");
            }

            System.out.println( );
        }
    }
    public static int next_visit(int c_vertex){
        int MIN = Integer.MAX_VALUE;
        int cost_spent= 0,next_vertex = Integer.MAX_VALUE;
        for (int i=0;i<no_of_vertices;i++){
            if(two_dim_cost_matrix[c_vertex-1][i]!=0 && !visited_copy_array[i]){
                if (two_dim_cost_matrix[c_vertex-1][i]+two_dim_cost_matrix[i][c_vertex-1] < MIN){
                    MIN = two_dim_cost_matrix[c_vertex-1][i] + two_dim_cost_matrix[i][c_vertex-1];
                    cost_spent = two_dim_cost_matrix[c_vertex-1][i];

                    next_vertex=i;
                }
            }

        }
        if(MIN != Integer.MAX_VALUE){
            total_min_cost+=cost_spent;

        }
        return next_vertex+1;
    }

    public static void shortest_distance(int c_vertex){
        visited_copy_array[c_vertex-1]=true;
        System.out.print(c_vertex+"------>");
        int nxt_visit= next_visit(c_vertex);
        if(nxt_visit == Integer.MAX_VALUE+1){
            System.out.print(current_vertex);
            total_min_cost+=two_dim_cost_matrix[c_vertex-1][current_vertex-1];

            return;
        }
        shortest_distance(nxt_visit);

    }
    public static void main(String[] args){
        inputs();
        System.out.println("\nOptimal path: ");
        shortest_distance(current_vertex);
        System.out.println();
        System.out.println("\nTour cost: "+total_min_cost);



    }




}
