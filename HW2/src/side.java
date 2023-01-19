import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
// WORKED ON WITH yasin akbashev

public class side extends JPanel {
    int L0 = 2;
    int L1 = 1;
    int[] square = {1, 14, 14, 4, 11,7,6,9,8,10,10,5,13,2,3,15};
    LinkedList<String> alreadysaid = new LinkedList<>();
    LinkedList<String> meetsgoal = new LinkedList<>();
    LinkedList<Integer> listofnums=new LinkedList<>();

    public side(int L0, int L1){
        this.L0=L0;
        this.L1=L1;
    }
    public side(){
    }



    public static void main(String[] args) {
        side lucasnumberall=new side();
        lucasnumberall.lucasnumbersnormally();
        lucasnumberall.lucasnumbersunique(5,1);
        System.out.println("This is your square");
        for (int i = 1; i <=lucasnumberall.square.length; i++) {
            System.out.print(lucasnumberall.square[i-1]+" ");
            if (i%4==0){
                System.out.println();
            }
        }
        side eggqare = new side();
        System.out.println();
        System.out.println("Would you like to see the cool visual we made? It shows what the computer is currently looking at, so you can follow-along. yes/no");
        Scanner keyboard = new Scanner(System.in);
        String decide = keyboard.next();
        int howmean=0;
        boolean meanie =false;
        while (!decide.toString().toLowerCase().equals("yes") && !decide.toString().toLowerCase().equals("no") ){
            System.out.println("Yes or no");
            decide= keyboard.next();
            if (decide.toString().toLowerCase().equals("no") ) {
                howmean++;
                meanie=true;
            }
        }
        if (decide.toString().toLowerCase().equals("no") && !meanie) {
            howmean++;
            meanie=true;
        }
        while (howmean<3 && meanie){
            System.out.println("are you suuuuuuuuuuuuuuuuuuure?");
            decide= keyboard.next();
            if (!decide.toString().toLowerCase().equals("yes") && !decide.toString().toLowerCase().equals("no") ) {
                System.out.println("I dont get it, it's a yes or no question, so i'll ask again:");
            }
            else if (decide.toString().toLowerCase().equals("yes") ) {
                howmean++;
            }
            else if (decide.toString().toLowerCase().equals("no")){
                meanie=false;
                howmean=1;
            }
        }
        if (howmean>1){
            eggqare.lucassquare4lame(eggqare.square);
        }
        else{
            eggqare.lucassquare4cool(eggqare.square);
        }
        for (String s:eggqare.meetsgoal) {
            System.out.println(s);
        }
        System.out.println("The total number of 4-element combinations that are equal to the goal is "+eggqare.meetsgoal.size());

        System.out.println("would you like to see a cool visual?");
        decide = keyboard.next();
        while (!decide.toString().toLowerCase().equals("yes") && !decide.toString().toLowerCase().equals("no") ){
            System.out.println("Yes or no");
            decide= keyboard.next();
        }
        Temp t = new Temp();
        for (int i : t.square) {
            System.out.print(i + " ");
        }
        System.out.println();
        LinkedList<Integer> listofsums = new LinkedList<>();
        listofsums.add(0);
        boolean lame=false;
        if (decide.toString().toLowerCase().equals("no") ) {
            lame=true;
        }
        if (!lame) {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1200, 1000);
            f.setVisible(true);
            f.add(t.p);
            for (int i = 0; i < t.square.length; i++) {
                int sum = t.square[i];
                //System.out.println("sum is " +sum);
                if (!(listofsums.contains(sum))) {
                    listofsums.add(sum);
                }
                for (int j = i + 1; j < t.square.length; j++) {
                    sum += t.square[j];
                    //System.out.println("sum is " +sum);
                    if (!(listofsums.contains(sum))) {
                        listofsums.add(sum);
                    }
                }
                //System.out.println("Reset");
            }
            int largestsum = 0;
            int largestindex = 0;
            ExecutionTimer exec = new ExecutionTimer();
            int[] array = new int[listofsums.size()];
            for (int i = 0; i < listofsums.size(); i++) {
                array[i] = listofsums.get(i);
            }
            Arrays.sort(array);
            int[] tempray = new int[16];
            for (int i = 0; i < array.length; i++) {
                t.solvesmall(array[i], t.square, 0, 0, "");
                //System.out.println(t.goaltotal+" is how many total combinations there are for a sum of "+array[i]);
                //System.out.println(t.comparisons+" is how many comaprisons were made to get that");
                t.supertotal += t.comparisons;
                t.supergoaltotal.add(t.goaltotal);
                if (largestsum < t.goaltotal) {
                    largestindex = array[i];
                    largestsum = t.goaltotal;
                }
                t.goaltotal = 0;
                t.comparisons = 0;
            }
            exec.end();
            System.out.println("IT took me " + (int) exec.duration() + " miliseconds to get all the sum stuff");
            System.out.println("The total amount of comarisons made to find every single possible sum of every possible sum was " + t.supertotal);
            System.out.println("The most common sum of sums was " + largestindex + " with a total of " + largestsum + " combinations");
            System.out.println("Keep in mind, this was done exclusively with all the possible numbers from the square. It was not done for every number from 0 to the total sum of the square contents.");
            System.out.println("Its more efficient that way.");
        }
        else{
            for (int i = 0; i < t.square.length; i++) {
                int sum = t.square[i];
                //System.out.println("sum is " +sum);
                if (!(listofsums.contains(sum))) {
                    listofsums.add(sum);
                }
                for (int j = i + 1; j < t.square.length; j++) {
                    sum += t.square[j];
                    //System.out.println("sum is " +sum);
                    if (!(listofsums.contains(sum))) {
                        listofsums.add(sum);
                    }
                }
                //System.out.println("Reset");
            }
            int largestsum = 0;
            int largestindex = 0;
            ExecutionTimer exec = new ExecutionTimer();
            int[] array = new int[listofsums.size()];
            for (int i = 0; i < listofsums.size(); i++) {
                array[i] = listofsums.get(i);
            }
            Arrays.sort(array);
            int[] tempray = new int[16];
            for (int i = 0; i < array.length; i++) {
                t.solvesmallLame(array[i], t.square, 0, 0, "");
                //System.out.println(t.goaltotal+" is how many total combinations there are for a sum of "+array[i]);
                //System.out.println(t.comparisons+" is how many comaprisons were made to get that");
                t.supertotal += t.comparisons;
                t.supergoaltotal.add(t.goaltotal);
                if (largestsum < t.goaltotal) {
                    largestindex = array[i];
                    largestsum = t.goaltotal;
                }
                t.goaltotal = 0;
                t.comparisons = 0;
            }
            exec.end();
            System.out.println("IT took me " + (int) exec.duration() + " miliseconds to get all the sum stuff");
            System.out.println("The total amount of comarisons made to find every single possible sum of every possible sum was " + t.supertotal);
            System.out.println("The most common sum of sums was " + largestindex + " with a total of " + largestsum + " combinations");
            System.out.println("Keep in mind, this was done exclusively with all the possible numbers from the square. It was not done for every number from 0 to the total sum of the square contents.");
            System.out.println("Its more efficient that way.");
        }
    }

    public void lucasnumbersnormally(){
        LinkedList<Integer> timesihave = new LinkedList<>();
        LinkedList<Integer> listofnums=new LinkedList<>();
        for (int i = 0; i <42; i++) {
            ExecutionTimer exec = new ExecutionTimer();
            side egg = new side();
            egg.lucas2(i);
            exec.end();
            for (int j = 0; j <egg.listofnums.size(); j++) {
                if (!listofnums.contains(egg.listofnums.get(i))){
                    listofnums.add(egg.listofnums.get(i));
                }
            }
            timesihave.add(((int) exec.duration()));
            System.out.println(exec.duration()+"ms for lucas number: " +i );
        }
        for (int i = 0; i <timesihave.size(); i++) {
            if (i>=30&&i<= timesihave.size()-2){
                System.out.println(( (double)(timesihave.get(i+1) )/ (double)timesihave.get(i))+"this is the ratio of the runtime for lucas number " +(i+1)+"/"+i);
            }
        }
        for (int i = 0; i <listofnums.size(); i++) {
            if (i<=timesihave.size()-2) {
                System.out.println(((double) (listofnums.get(i + 1)) / (double) listofnums.get(i)) + "this is the ratio of lucas number " + (i + 1) + "/ lucas number" + i);
            }
        }
        System.out.println("Keep in mind, lucasnumbers below 30 had a runtime of 0ms (for my computer, atleast), and would result in a divide by 0 error.");
    }


    public void lucasnumbersunique(int l0, int l1){
        LinkedList<Integer> timesihave = new LinkedList<>();
        LinkedList<Integer> listofnums=new LinkedList<>();
        for (int i = 0; i <42; i++) {
            ExecutionTimer exec = new ExecutionTimer();
            side egg = new side(l0,l1);
            egg.lucas2(i);
            exec.end();
            for (int j = 0; j <egg.listofnums.size(); j++) {
                if (!listofnums.contains(egg.listofnums.get(i))){
                    listofnums.add(egg.listofnums.get(i));
                }
            }
            timesihave.add(((int) exec.duration()));
            System.out.println(exec.duration()+"ms for lucas number: " +i );
        }
        for (int i = 0; i <timesihave.size(); i++) {
            if (i>=30&&i<= timesihave.size()-2){
                System.out.println(( (double)(timesihave.get(i+1) )/ (double)timesihave.get(i))+"this is the ratio of the runtime for student lucas number" +(i+1)+"/"+i);
            }
        }
        for (int i = 0; i <listofnums.size(); i++) {
            if (i<=timesihave.size()-2) {
                System.out.println(((double) (listofnums.get(i + 1)) / (double) listofnums.get(i)) + "this is the ratio of student lucas number" + (i + 1) + "/ student lucas number" + i);
            }
        }
        System.out.println("Keep in mind, lucasnumbers below 30 had a runtime of 0ms (for my computer, atleast), and would result in a divide by 0 error.");
    }

    public void lucassquare4lame(int[] a){
        int goal = 0;
        for (int i = 0; i < a.length/4; i++) {
            goal += a[i];
        }
        int totalchecks=0;
        int total=0;
        for (int i =0; i <a.length; i++) { //THIS IS THE FIRST LOOP THIS IS IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
            int itemp=0;
            total+=a[i];
            for (int j = i+1; j <a.length; j++) {
                int jtemp=total;
                total+=a[j];
                for (int k = j+1; k <a.length; k++) {
                    int ktemp=total;
                    total+=a[k];
                    for (int l = k+1; l <a.length; l++) {
                        int ltemp=total;
                        total+=a[l];
                        if (total==goal){
                            meetsgoal.add(total+" is reached with " +a[i] + " for the first number, "+a[j] + " for the second number, "+a[k] + " for the third number, "+a[l] + " for the fourth number, ");
                        }
                        totalchecks++;
                        total=ltemp;
                    }
                    total=ktemp;
                }
                total=jtemp;
            }
            total=itemp;
        }
        meetsgoal.add("We've done "+totalchecks+" total comparisons");
    }
    public void lucassquare4cool(int[] a) {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peac p = new peac();
        f.setSize(1000,1000);
        f.setVisible(true);
        int goal = 0;
        for (int i = 0; i < a.length/4; i++) {
            goal += a[i];
        }
        int totalchecks=0;
        int total=0;
        for (int i =0; i <a.length; i++) { //THIS IS THE FIRST LOOP THIS IS IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
            int itemp=0;
            f.remove(p);
            p.wheream[i]=true;
            total+=a[i];
            //System.out.println(total +"total i");
            //System.out.println("pos1 ="+p1+ "aka the first number");
            //System.out.println(a[i]);
            int currcheck=i;
            for (int j = i+1; j <a.length; j++) {
                int jtemp=total;
                p.wheream[j]=true;
                total+=a[j];
                //System.out.println(total +"total j");
                //System.out.println("pos2 ="+p2+ "aka the second number");
                //System.out.println(a[j]);
                for (int k = j+1; k <a.length; k++) {
                    int ktemp=total;
                    p.wheream[k]=true;
                    total+=a[k];
                    //System.out.println(total +"total k");
                    //System.out.println("pos3 ="+p3+ "aka the third number");
                    //System.out.println(a[k]);
                    for (int l = k+1; l <a.length; l++) {
                        int ltemp=total;
                        total+=a[l];
                        p.wheream[l]=true;
                        //System.out.println(total +" TOTAL " +a[i] + " for the first number, "+a[j] + " for the second number, "+a[k] + " for the third number, "+a[l] + " for the fourth number, ");
                        if (total==goal){
                            meetsgoal.add(total+" is reached with " +a[i] + " for the first number, "+a[j] + " for the second number, "+a[k] + " for the third number, "+a[l] + " for the fourth number, ");
                            //System.out.println("Number has been added, number of reached 33's is " + meetsgoal.size());
                        }
                        f.add(p);
                        try {
                            TimeUnit.MILLISECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        p.repaint();
                        totalchecks++;
                        total=ltemp;
                        p.wheream[l]=false;
                    }
                    total=ktemp;
                    p.wheream[k]=false;
                }
                total=jtemp;
                p.wheream[j]=false;
            }
            total=itemp;
            p.wheream[i]=false;
            //System.out.println("WE ARE CHANGING THE NUMBER WE LOOK AT");
            //System.out.println("We've done "+totalchecks+" total comparisons");
        }
        meetsgoal.add("We've done "+totalchecks+" total comparisons");
        f.setVisible(false);
    }
    public int lucas2(int n) {

        if (!alreadysaid.contains("L(" + 0 + ") = " + (L0))) {
            System.out.println("L(" + 0 + ") = " + (L0));
            alreadysaid.add("L(" + 0 + ") = " + (L0));
            listofnums.add(L0);
        }
        if (!alreadysaid.contains("L(" + 1 + ") = " + (L1))) {
            System.out.println("L(" + 1 + ") = " + (L1));
            alreadysaid.add("L(" + 1 + ") = " + (L1));
            listofnums.add(L1);
        }
        if (!alreadysaid.contains("L(" + 2 + ") = " + (L0 + L1))) {
            System.out.println("L(" + 2 + ") = " + (L0 + L1));
            alreadysaid.add("L(" + 2 + ") = " + (L0 + L1));
            listofnums.add(L0+L1);
        }

        if (n == 0) {
            return L0;
        } else if (n == 1) {
            return L1;
        } else if (n == 2) {
            return L0 + L1;
        }

        int a = lucas2(n - 1);
        int b = lucas2(n - 2);

        if (!alreadysaid.contains("L(" + n + ") = " + (a + b))) {
            System.out.println("L(" + n + ") = " + (a + b));
            alreadysaid.add("L(" + n + ") = " + (a + b));
            listofnums.add(a+b);
        }
        return a + b;
    }
}