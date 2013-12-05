package Dijkstra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graph {
    private static final int MAXNODES = 50;
    private static final int INFINITY = Integer.MAX_VALUE;
    int n = 0;
    int[][] weight = new int[MAXNODES][MAXNODES];
    int[] distance = new int[MAXNODES];
    int[] precede = new int[MAXNODES];

    /**
     * Find the shortest path across the graph using Dijkstra's algorithm.
     */
    void buildSpanningTree(int source, int destination) {
    	
        boolean[] visit = new boolean[MAXNODES];

        for (int i=0 ; i<n ; i++) {
        	distance[i] = INFINITY;
            precede[i] = INFINITY;
        }
        distance[source] = 0;

        int current = source;
        while (current != destination) {
            int distcurr = distance[current];
            int smalldist = INFINITY;
            int k = -1;
            visit[current] = true;

            for (int i=0; i<n; i++) {
                if (visit[i])
                    continue;

                int newdist = distcurr + weight[current][i];
                if (newdist < distance[i]) {
                    distance[i] = newdist;
                    precede[i] = current;
                }
                if (distance[i] < smalldist) {
                    smalldist = distance[i];
                    k = i;
                }
            }
            //System.out.println(current + " - " + k);
            current = k;
        }
       /* for (int i=0 ; i<n ; i++){
            for (int j=0 ; j<n ; j++){
           System.out.println("Weight : OUT " + i +" -  IN "  + j +" = " + weight[i][j]);
            }
        }
        System.out.println("\n\n distance: ");
        for (int i : distance) {
			System.out.println(" distance " + i);
		}
        System.out.println("\n\n precede: ");
        for (int i : precede) {
        	System.out.println(" precede " + i);
		}
        System.out.println("\n\n visit: ");
        for (boolean b : visit) {
        	System.out.println(" visit " + b);
		}*/
    }

    /**
     * Get the shortest path across a tree that has had its path weights
     * calculated.
     */
    int[] getShortestPath(int source, int destination) {
        int i = destination;
        int finall = 0;
        int[] path = new int[MAXNODES];

        path[finall] = destination;
        finall++;
        //System.out.println(precede[i]+" - "+source);
        while (precede[i] != source) {
        	
            i = precede[i];
            path[finall] = i;
            finall++;
        }
        path[finall] = source;

        int[] result = new int[finall+1];
        System.arraycopy(path, 0, result, 0, finall+1);
        return result;
    }

    /**
     * Print the result.
     */
    void displayResult(int[] path) {
        System.out.println("\nThe shortest path followed is : \n");
        int totalCost = 0;
        for (int i = path.length-1 ; i>0 ; i--){
            System.out.println("\t\t( " + path[i] + " ->" + path[i-1] +
                    " ) with cost = " + weight[path[i]][path[i-1]]);
            totalCost+=weight[path[i]][path[i-1]];
        }
        System.out.println("For the Total Cost = " + totalCost);
    }

    /**
     * Prompt for a number.
     */
    int getNumber(String msg) {
        int ne = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("\n" + msg + " : ");
            ne = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            System.out.println("I/O Error");
        }
        return ne;
    }

    /**
     * Prompt for a tree, build and display a path across it.
     */
    void SPA() {
    	for (int i = 0 ; i < MAXNODES ; i++){
    		for(int j = 0 ; j < MAXNODES; j++){
            	weight[i][j] = 1000000;
            }
    	}
    	
    	try {
    		File arquivo = new File( "import.txt" );
    		BufferedReader bf = new BufferedReader(new FileReader(arquivo));
    		List<String> lista = new ArrayList<String>();
    		while(bf.ready()){
    			
    			String[] array = bf.readLine().split(" ");
    			//adiciona na tabela de adjacencias
    			int nodoSource = Integer.parseInt(array[0]);
    			int nodoTarget = Integer.parseInt(array[1]);
    			int cost 	   = Integer.parseInt(array[2]);
    			weight[nodoSource][nodoTarget] = cost;
    			
    			//Adiciona na lista para extrair o n
    			lista.add(array[0]);
    			lista.add(array[1]);
    		}
    		Set<String> outra = new HashSet<String>(lista);  
    		n= outra.size();
    		
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

        int s = getNumber("Enter the source node");
        int d = getNumber("Enter the destination node");

        buildSpanningTree(s, d);
        displayResult(getShortestPath(s, d));
    }
}

public class Dijkstra {
    public static void main(String args[]) {
        Graph g = new Graph();
        g.SPA();
    }
}
