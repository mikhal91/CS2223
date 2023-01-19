import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Temp extends JFrame {
    LinkedList<String> anymeetsgoal = new LinkedList<>();
    int comparisons = 0;
    int[] square = {1, 14, 14, 4, 11,7,6,9,8,10,10,5,13,2,3,15};
    int goaltotal=0;
    int supertotal =0;
    LinkedList<Integer> supergoaltotal= new LinkedList<>();
    pecall p = new pecall();


    public void solvesmall(int goal, int[] a, int startnumber, int position, String pass) {
        if (startnumber==goal){
            p.repaint();
            anymeetsgoal.add(pass);
            goaltotal++;
            p.std=pass;
            p.goal=Integer.toString(goal);
            p.combinations=Integer.toString(comparisons);
            if (Integer.parseInt(p.largestcombo.substring(0))<comparisons){
                p.largestcombo=Integer.toString(comparisons);
                p.goalforlargest=Integer.toString(goal);
            }
            while(pass.contains(",")){
                for (int i = 0; i <p.sans.length; i++) {
                    if ((Integer.parseInt(pass.substring(0,pass.indexOf(','))))==p.sans[i] && !p.wheream[i]){
                        p.wheream[i]=true;
                    }
                }
                pass=pass.substring(pass.indexOf(',')+1);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.repaint();
            if (goal!= Arrays.stream(p.sans).sum()) {
                p.wheream[0] = false;
                p.wheream[1] = false;
                p.wheream[2] = false;
                p.wheream[3] = false;
                p.wheream[4] = false;
                p.wheream[5] = false;
                p.wheream[6] = false;
                p.wheream[7] = false;
                p.wheream[8] = false;
                p.wheream[9] = false;
                p.wheream[10] = false;
                p.wheream[11] = false;
                p.wheream[12] = false;
                p.wheream[13] = false;
                p.wheream[14] = false;
                p.wheream[15] = false;
            }
        }
        else {
            for (int i = position; i < a.length; i++) {
                if (startnumber + a[i] == goal) {
                    comparisons++;
                    solvesmall(goal, a, startnumber + a[i],i,pass+a[i]+",");
                }
                else if(startnumber+a[i]<goal){
                    solvesmall(goal, a, startnumber + a[i],i+1,pass+a[i]+",");
                }
            }
        }
        p.repaint();
    }

    public void solvesmallLame(int goal, int[] a, int startnumber, int position, String pass) {
        if (startnumber==goal){
            anymeetsgoal.add(pass);
            goaltotal++;
        }
        else {
            for (int i = position; i < a.length; i++) {
                if (startnumber + a[i] == goal) {
                    comparisons++;
                    solvesmallLame(goal, a, startnumber + a[i],i,pass+a[i]+",");
                }
                else if(startnumber+a[i]<goal){
                    solvesmallLame(goal, a, startnumber + a[i],i+1,pass+a[i]+",");
                }
            }
        }
        p.repaint();
    }
}