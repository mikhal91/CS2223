//Worked on this with Yasin Akbashev
//Worked on this with Michael Alicea


import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class palindromecheck {
    boolean alreadywent=false;


    public static void main(String[] args) {
        palindromecheck og = new palindromecheck();
        og.supahpalindrome();


    }

    public void supahpalindrome(){
        Scanner keyboard = new Scanner(System.in);
        palindromecheck og = new palindromecheck();
        System.out.println("Give me a word, set of numbers, poem, whatever. I'll tell you if it's a palindrome. Nothing except for the letters and numbers will be looked at, capitalization does not matter also.");
        boolean itsgood=og.palindromecheck(keyboard.nextLine());
        if (itsgood){
            System.out.println("Yep, its a palindrome.");
        }
        else{
            System.out.println("Nope, its not a palindrome.");
        }
    }

    public Boolean palindromecheck(String c){
        c=c.toLowerCase();
        String withoutAccent = Normalizer.normalize(c, Normalizer.Form.NFD);
        c=withoutAccent.replaceAll("[^a-z0-9 ]", "");
        for (int i = 0; i <c.length(); i++) {
            if (Pattern.matches("[^a-z0-9]",c.charAt(i)+"")){
                c=c.replace(c.charAt(i)+"","");
            }
        }
        if (c.length()<=1){
            return true;
        }
        if (c.charAt(0)==(c.charAt(c.length()-1))){
            return palindromecheck(c.substring(1,c.length()-1));
        }
        return false;
    }
}