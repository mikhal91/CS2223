import java.util.Scanner;
import java.util.Random;

// Worked on with Michael Alicea
// Worked on with Yasin Akbashev



public class doubleTrouble {
    Scanner keyboard = new Scanner(System.in);
    int[] gameboard = {3, 7, 5};
    boolean playeturn = false;
    boolean gamegoing = true;
    boolean playerlost = false;
    int numeaten =0;

    public static void main(String[] args) {
        doubleTrouble doubleTrouble = new doubleTrouble();
        doubleTrouble.greeting();
        doubleTrouble.screen();
        if (doubleTrouble.playeturn){
            while (doubleTrouble.gamegoing) {
                doubleTrouble.playgame();
                doubleTrouble.computerplays();
            }
        }
        else{
            while (doubleTrouble.gamegoing) {
                doubleTrouble.computerplays();
                doubleTrouble.playgame();
            }
        }
        doubleTrouble.printwinner();
    }
    void runRandComputer() {
        int firstRow = gameboard[0];
        int secondRow = gameboard[1];
        int row = largestOf(gameboard[0], gameboard[1], gameboard[2]);
        if (firstRow==row){
            row=0;
        }
        if (secondRow==row){
            row=1;
        }
        else{
            row=2;
        }
        makeAMove(row, 1);
    }
    int largestOf(int a, int b, int c) {
        int largestRow = 0;
        if (a > largestRow) {
            largestRow = a;
        }
        if (b > largestRow) {
            largestRow = b;
        }
        if (c > largestRow) {
            largestRow = c;
        }
        return largestRow;
    }
    void makeAMove(int row, int numPieces){
        if(row == 0){
            gameboard[0] = gameboard[0] - numPieces;
            System.out.println("computer takes " + numPieces +" green");
            numeaten++;
        } else if(row == 1){
            gameboard[1] = gameboard[1] - numPieces;
            System.out.println("computer takes " + numPieces +" yellow");
            numeaten++;
        } else {
            gameboard[2] = gameboard[2] - numPieces;
            System.out.println("computer takes " + numPieces +" orange");
            numeaten++;
        }
    }
    public void computerplays(){
        if (gamegoing) {
            playeturn = false;

            // calculate the nim sum of the game
            int xor = gameboard[0] ^ gameboard[1] ^ gameboard[2];
            if (xor == 0) {
                runRandComputer(); // takes 1 from the largest pile
            } else {
                DoDaAi(xortowin());
            }
            System.out.println("Current board");
            System.out.println(gameboard[0] + " green " + gameboard[1] + " yellow " + gameboard[2] + " orange");
            whowon();
            playeturn = true;
        }
    }
    public void DoDaAi(String b){
        if (!b.equals("bad")) {
            int green = Integer.parseInt(String.valueOf(b.charAt(0)));
            if (green != gameboard[0]) {
                int diff = gameboard[0]-green;
                gameboard[0] = green;
                System.out.println("While you were distracted, the computer ate " + (diff) +" green markers");
                numeaten+= diff;
            }
            int yellow = Integer.parseInt(String.valueOf(b.charAt(2)));
            if (yellow != gameboard[1]) {
                int diff = gameboard[1]-yellow;
                gameboard[1] = yellow;
                System.out.println("While you were distracted, the computer ate " + (diff) +" yellow markers");
                numeaten+= diff;
            }
            int orange = Integer.parseInt(String.valueOf(b.charAt(4)));
            if (orange != gameboard[2]) {
                int diff = gameboard[2]-orange;
                gameboard[2] = orange;
                System.out.println("While you were distracted, the computer ate " + (diff) +" orange markers");
                numeaten+= diff;
            }
        }
        else{
            System.out.println("something went wrong here");
        }
    }
    public String xortowin(){ // xor will range from 1-7
        for (int i = 0; i <=gameboard[0]; i++) {
            for (int j = 0; j <=gameboard[1] ; j++) {
                for (int k = 0; k<=gameboard[2] ; k++) {
                    int xornow=i^j^k;
                    if (    (xornow==0 && i!=gameboard[0] && j==gameboard[1] && k==gameboard[2]) ||
                            (xornow==0 && i==gameboard[0] && j!=gameboard[1] && k==gameboard[2]) ||
                            (xornow==0 && i==gameboard[0] && j==gameboard[1] && k!=gameboard[2]) ){
                        return i +" " +j +" " +k +" ";
                    }
                }
            }
        }
        return "bad";
    }
    public void greeting() {
        System.out.println("Welcome to double trouble");
    }
    public boolean getturn() {
        return playeturn;
    }
    public void screen() {
        System.out.println("do you want to go first? Yes/no");
        String oogabooga=keyboard.next().toLowerCase();
        if (oogabooga.equals("yes")){
            playeturn = true;
        }
        else if (oogabooga.equals("no")){
        }
        else{
            System.out.println("Yes or no. Capitalization doesnt matter.");
            screen();
        }
    }
    public void playgame() { //This is the player. This code is for letting the player do something (like lose).
        if (gamegoing) {
            String color = choosecolor();
            int toremove = 0;
            System.out.println("You chose " + color);
            if (color.equals("green") || color.equals("g")) {
                if (gameboard[0] != 0) {
                    System.out.println("How many pieces would you like to remove");
                    toremove = keyboard.nextInt();
                    if (gameboard[0] - toremove < 0) {
                        System.out.println("Sorry, there are only " + gameboard[0] + " green pieces.");
                        playgame();
                    } else if (toremove == 0) {
                        System.out.println("You can't remove 0 pieces, that's just boring.");
                        playgame();
                    } else {
                        gameboard[0] -= toremove;
                        System.out.println("there are now " + gameboard[0] + " green pieces left.");
                        System.out.println("Current board");
                        System.out.println(gameboard[0] + " green " + gameboard[1] + " yellow " + gameboard[2] + " orange");
                        if (gamegoing){
                            System.out.println("The computer makes its genius move");}
                        whowon();
                    }
                } else {
                    System.out.println("Sorry, there are " + gameboard[0] + " green pieces. Choose another color.");
                    playgame();
                }
            }
            if (color.equals("yellow") || color.equals("y")) {
                if (gameboard[1] != 0) {

                    System.out.println("How many pieces would you like to remove");
                    toremove = keyboard.nextInt();
                    if (gameboard[1] - toremove < 0) {
                        System.out.println("Sorry, there are only " + gameboard[1] + " yellow pieces.");
                        playgame();
                    } else if (toremove == 0) {
                        System.out.println("You can't remove 0 pieces, that's just boring.");
                        playgame();
                    } else {
                        gameboard[1] -= toremove;
                        System.out.println("there are now " + gameboard[1] + " yellow pieces left.");
                        System.out.println("Current board");
                        System.out.println(gameboard[0] + " green " + gameboard[1] + " yellow " + gameboard[2] + " orange");
                        if (gamegoing){
                            System.out.println("The computer makes its genius move");}
                        whowon();
                    }
                } else {
                    System.out.println("Sorry, there arent any yellow pieces. Choose another color.");
                    playgame();
                }
            }
            if (color.equals("orange") || color.equals("o")) {
                if (gameboard[2] != 0) {
                    System.out.println("How many pieces would you like to remove");
                    toremove = keyboard.nextInt();
                    if (gameboard[2] - toremove < 0) {
                        System.out.println("Sorry, there are only " + gameboard[2] + " orange pieces.");
                        playgame();
                    } else if (toremove == 0) {
                        System.out.println("You can't remove 0 pieces, that's just boring.");
                        playgame();
                    } else {
                        gameboard[2] -= toremove;
                        System.out.println("there are now " + gameboard[2] + " orange pieces left.");
                        System.out.println("Current board");
                        System.out.println(gameboard[0] + " green " + gameboard[1] + " yellow " + gameboard[2] + " orange");
                        if (gamegoing){
                            System.out.println("The computer makes its genius move");}
                        whowon();
                    }
                } else {
                    System.out.println("Sorry, there arent any orange pieces. Choose another color.");
                    playgame();
                }
            }
        }
    }
    public String choosecolor(){
        System.out.println("What color do you choose");
        String color = "";
        whowon();
        boolean stopfornow = true;
        while (stopfornow && gamegoing){
            color = keyboard.next().toString().toLowerCase();
            stopfornow=!acceptable(color);
        }
        return color;
    }
    public boolean acceptable(String color){
        if (!color.equals("green") && !color.equals("g")  && !color.equals("yellow") && !color.equals("y") && !color.equals("orange") && !color.equals("o") ) {
            System.out.println("That's not an accepted color, choose orange green or yellow. Captalization does not matter.");
            return false;
        }
        else{
            return true;
        }
    }
    public void printwinner(){
        if (playerlost){
            System.out.println("A machine of true intelligence has beaten you. In your many attempts at a good move, it ate " + numeaten+" markers");
        }
        else{
            System.out.println("Congrats, you've won.");
        }
    }
    public void whowon() {
        if (gameboard[0] == 0 && gameboard[1] == 0 && gameboard[2] == 0 && playeturn) {
            gamegoing = false; // playerlost doesnt need to be changed, its already false
            playerlost=false;
        }
        if (gameboard[0] == 0 && gameboard[1] == 0 && gameboard[2] == 0 && !playeturn) {
            gamegoing = false;
            playerlost=true;
        }
    }
}