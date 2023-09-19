/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_csd201;

/**
 ** Java Program to Implement Graph Coloring Algorithm
 *
 */
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Class GraphColoring *
 */
public class GraphColoring {

    private int V, numOfColors;
    private int[] color;
    private int[][] graph;
    private char ver[];

    /**
     * Function to assign color *
     */
    public void graphColor(String s) {
        //ver = "ACDGBHKEJLFI".toCharArray();
        //this.V = v;
        loadData(s);
        //numOfColors = noc;
        color = new int[V];
        //graph = g;

        try {
            solve(0);
            System.out.println("No solution");
        } catch (Exception e) {
            System.out.println("\nSolution exists ");
            display();
        }
    }

    /**
     * function to assign colors recursively *
     */
    public void solve(int v) throws Exception {
        /**
         * base case - solution found *
         */
        if (v == V) {
            throw new Exception("Solution found");
        }
        /**
         * try all colours *
         */
        for (int c = 1; c <= numOfColors; c++) {
            if (isPossible(v, c)) {
                /**
                 * assign and proceed with next vertex *
                 */
                color[v] = c;
                solve(v + 1);
                /**
                 * wrong assignement *
                 */
                color[v] = 0;
            }
        }
    }

    /**
     * function to check if it is valid to allot that color to vertex *
     */
    public boolean isPossible(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && c == color[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * display solution *
     */
    public void display() {
        //System.out.print("\nColors : \n");
        for (int j = 1; j <= numOfColors; j++) {
            System.out.print("Ke sach " + j + ": Gom cac quyen sach: ");
            for (int i = 0; i < V; i++) {

                if (j == color[i]) {
                    System.out.print(ver[i] + " ");
                }
            }
            System.out.println("");
        }

   
    }

    //load file
    void loadData(String fname) {
        RandomAccessFile f;
       // int i, j, x;
        String s;
        StringTokenizer t;
        String s3 = "";
         Map<String, Integer> bookIndices = new HashMap<>();
         ArrayList<String> list = new ArrayList();

        try {
            f = new RandomAccessFile(fname, "r");
            s = f.readLine();//just use the data in the k-th line
            s = s.trim();
            int c = Integer.parseInt(s);
            numOfColors = c;
            s = f.readLine();//just use the data in the k-th line
            s = s.trim();
            int n = Integer.parseInt(s);
            this.V = n;
            graph = new int[n][n];
            for(int r=0;r<n;r++){
                for(int r2=0;r2<n;r2++) {
                    graph[r][r2] = 0;
                }
            }
            for (int y = 1; y <= c; y++) {
                s = f.readLine();
                s = s.trim();
                String s2 ="";
                String txt[] = s.split(",");
                for (int tt = 0; tt < txt.length; tt++) {
                    
                    String s1 = txt[tt].trim();
                   
                    s2 = s2.concat(s1);
                    s3 = s3.concat(s1);

                }
                list.add(s2);
                for (String book : txt) {
                bookIndices.put(book, bookIndices.size());
            }
                 for ( int j = 0; j < txt.length; j++) {
                for (int k = j + 1; k < txt.length; k++) {
                    int index1 = bookIndices.get(txt[j]);
                    int index2 = bookIndices.get(txt[k]);
                    graph[index1][index2] = 1;
                    graph[index2][index1] = 1;
                }
            }

            }
            this.ver = s3.toCharArray();
            
            s=f.readLine();
            s=s.trim();
            int nRules =Integer.parseInt(s);
            for(int i =1;i<=nRules;i++) {
                s = f.readLine();
                s = s.trim();
                String[] line = s.split(" not in ");
                String sach = line[0];
                int ke = Integer.parseInt(line[1]);
                int sachIndex =bookIndices.get(sach);
                int keIndex = ke -1;
                String sachkeIndex[] = list.get(keIndex).split("");
                for(int y=0;y<sachkeIndex.length;y++){
                   int entry = bookIndices.get(sachkeIndex[y]);
                    graph[sachIndex][entry] = 1;
                    graph[entry][sachIndex] = 1;
                }
            }
            
             

           /* for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    graph[i][j] = x;
                }
            }*/

            f.close();

        } catch (Exception e) {
        }

    }

    /**
     * Main function *
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Graph Coloring Algorithm Test\n");
        /**
         * Make an object of GraphColoring class *
         */
        GraphColoring gc = new GraphColoring();

        /**
         * Accept number of vertices *
         */
        /* System.out.println("Enter number of verticesz\n");
        int V = scan.nextInt();*/
        /**
         * get graph *
         */
        /*System.out.println("\nEnter matrix\n");
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = scan.nextInt();*/
 /* System.out.println("\nNhap so loai sach(Enter number of color): ");
        int c = scan.nextInt();*/
        gc.graphColor("D:\\FPT\\PRF\\project_CSD201\\Matrix.txt");

    }
}
