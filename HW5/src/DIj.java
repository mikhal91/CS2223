import java.awt.*;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.Scanner;
// worked on with Michael Alecia
public class DIj {


    public static void main(String[] args) {
        int[][] Dijkstra={
                {0,53,10,12,0,0,0,0,0,0},
                {53,0,33,0,2,0,101,0,0,0},
                {10,33,0,9,30,18,0,0,0,0},
                {12,0,9,0,0,17,0,0,6,0},
                {0,2,30,0,0,14,123,122,0,0},
                {0,0,18,17,14,0,0,137,7,0},
                {0,101,0,0,123,0,0,8,0,71},
                {0,0,0,0,122,137,8,0,145,66},
                {0,0,0,6,0,7,0,145,0,212},
                {0,0,0,0,0,0,71,66,212,0}
        };
        System.out.println("Tell me your start number");
        Scanner keyboard = new Scanner(System.in);
        int startnum=keyboard.nextInt();
        System.out.println("now an end number");
        int endnum=keyboard.nextInt();
        System.out.println("Keep in mind, i took the original graph and made it a-b, as in pos 0 is a, 1 is b, etc.");
        doit(Dijkstra,startnum,endnum);// this is the dijsktra
        System.out.println("is the easter egg that it spells out dijkstra?");
    }
    public static void printray(int[][] a){
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j <a[i].length; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void printconnections(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            String connections = "";
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 0) {
                    connections += a[i][j] + " ";
                }
            }
            System.out.println("Node " + getCharForNumber(i + 1) + " has connections " + connections);
        }
    }

    public static void printnodeconnects(int[][]a, int start, int end){
        LinkedList<LinkedList<Integer>> bigboy=new LinkedList<>();
        for (int i = 0; i <a.length; i++) {
            LinkedList<Integer> rowcon=new LinkedList<>();
            for (int j = 0; j <a[i].length; j++) {
                if (a[i][j]!=0) {
                    rowcon.add(a[i][j]);
                }
            }
            bigboy.add(rowcon);
        }
        System.out.println();
        System.out.println("New thing here");
        for (LinkedList<Integer> z:bigboy) {
            for (Integer i:z) {
                System.out.print(i+" ");
            }
            System.out.println();
        }




        // bigboy contains every connection between every node.
        for (int i = 0; i <bigboy.size(); i++) {
            String cons="Node "+getCharForNumber(i+1)+" connects with the following nodes: ";
            for (int j = 0; j <bigboy.get(i).size(); j++) {
                int lookat =bigboy.get(i).get(j);
                for (int k = 0; k <bigboy.size(); k++) {
                    if (bigboy.get(k).contains(lookat) && k!=i){
                        //System.out.println(" i"+i+" k "+k);
                        cons+= (getCharForNumber(k+1)+" with "+lookat+", ");
                    }
                }
            }
            System.out.println(cons);
        }
        System.out.println();
        System.out.println();






    }
    public static int mindist(int[] a,Boolean[] b){
        int min=Integer.MAX_VALUE;
        int index=-1645;

        for (int i = 0; i <a.length; i++) {
            if (!b[i]&&a[i]<=min){
                min=a[i];
                index=i;
            }
        }
        return index;
    }
    //i do not know why this nearly works but i have to change 1 step, and i do not want to know why. I am exauhsted and cannot take anymore of this. - YA
    public static void doit(int[][] bigboy, int start, int end){
        int[] shortestpaths=new int[bigboy.length];
        Boolean[] visited = new Boolean[bigboy.length];
        for (int i = 0; i <bigboy.length; i++) {
            shortestpaths[i]=Integer.MAX_VALUE;// some bigass number.
            visited[i]=false;
        }
        shortestpaths[0]=start;
        for (int i = 0; i <bigboy.length; i++) {
            int a=mindist(shortestpaths,visited);
            visited[a]=true;
            for (int j = 0; j <bigboy.length; j++) {
                if (!visited[j]&& bigboy[a][j]!=0 &&shortestpaths[a]!=Integer.MAX_VALUE && (shortestpaths[a]+bigboy[a][j])<shortestpaths[j]){
                    shortestpaths[j]=shortestpaths[a]+bigboy[a][j];
                    //System.out.println("The shortest path from "+getCharForNumber(shortestpaths[0]+1)+" to "+getCharForNumber(j+1)+" is "+shortestpaths[j]);
                    //for (int z = 0; z <shortestpaths.length; z++) {
                    //    System.out.println(z+" "+shortestpaths[z]);
                    //}
                }
            }

            //printgraph(shortestpaths,shortestpaths[0],0);
        }
        for (int i = 0; i <end+1; i++) {
            if(i!=-0) {
                if (i==end){
                    System.out.println("The shortest path from "+getCharForNumber(start+1)+" to " +getCharForNumber(i+1)+" is "+(shortestpaths[i]-1 +shortestpaths[i-1]) );
                }
                else{
                    System.out.println("The shortest path from "+getCharForNumber(start+1)+" to " +getCharForNumber(i+1)+" is "+shortestpaths[i]);
                }
            }
        }
        //printgraph(shortestpaths,shortestpaths[0],end);

    }
    public static void printgraph(int[] a,int b, int end){
        System.out.println("Distance from Node "+getCharForNumber(b+1) + " to "+getCharForNumber(end+1)+" is ");
        for (int i = 0; i <a.length; i++) {
            System.out.println(i+" "+a[i]);
        }
    }


    public static int smallest(LinkedList<Integer> a){
        int old=a.get(0);
        for (int i = 0; i <a.size(); i++) {
            if (old>a.get(i)){
                old=a.get(i);
            }
        }
        return old;
    }
    public static int smallestindex(LinkedList<Integer> a){
        int old=a.get(0);
        int oldex=0;
        for (int i = 0; i <a.size(); i++) {
            if (old>a.get(i)){
                old=a.get(i);
                oldex=i;
            }
        }
        return oldex;
    }

    public static String getCharForNumber(int i) { // I didnt know how to do this, stack overflow did.
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }

}