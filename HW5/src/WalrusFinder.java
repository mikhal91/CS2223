import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedList;
// worked on with Michael Alecia
public class WalrusFinder {

    public static void main(String[] args) {
        //notuniquehash();
        uniquehash();
    }

    public static void notuniquehash(){

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("WalrusAndTheCarpenterD2022");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);

        BufferedReader br = new BufferedReader(streamReader);

        Hashtable<Integer, String> hashedWords = new Hashtable<>();
        int C = 123;
        int m = 350;

        ArrayList<String> words = new ArrayList<>();
        try {
            String st;
            while ((st = br.readLine()) != null) {
                for (String s : st.split("\\s+")) {
                    String formattedString = s.replaceAll("[^'\\-a-zA-Z]*", "");
                    //System.out.println(formattedString);
                    words.add(formattedString);
                    m++;
                }
            }
        } catch (IOException i) {
            System.out.println("IOException");
        }
        System.out.println("there are "+words.size()+" words in the string of stuff");
        //ArrayList<String> words = new ArrayList<>();
        //hash the words
        boolean tableFull = false;
        for (String s : words) {
            int hash = 0;
            for (Character c : s.toCharArray()) {
                hash = (hash * C + (int) (c)) % m;
            }
            //add the word and its calculated hash to the hashtable
            int startHash = hash;
            while(hashedWords.get(hash) != null){
                ++hash;
                if(startHash == hash){
                    tableFull = true;
                    break;
                }
                if(hash >= m){
                    hash = 0;
                }
            }

            if(tableFull){
                System.out.println("Fuck");
                System.out.println(s);
                break;
            }

            System.out.println("Adding word '" + s + "' with hash " + hash);
            hashedWords.put(hash, s);
        }
    }
    public static void uniquehash(){

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("WalrusAndTheCarpenterD2022");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);

        BufferedReader br = new BufferedReader(streamReader);

        Hashtable<Integer, String> hashedWords = new Hashtable<>();
        int C = 123;
        int m = 350;

        ArrayList<String> words = new ArrayList<>();
        try {
            String st;
            while ((st = br.readLine()) != null) {
                for (String s : st.split("\\s+")) {
                    String formattedString = s.replaceAll("[^'\\-a-zA-Z]*", "");
                    if (!words.contains(formattedString)){
                        words.add(formattedString);
                    }
                }
            }
        } catch (IOException i) {
            System.out.println("IOException");
        }
        //System.out.println(m+" m size");
        //System.out.println("there are "+words.size()+" unique words in the string of stuff");
        //hash the words
        boolean tableFull = false;
        int cascade=0;
        // the following function does the putting of the hashes in the right of the places
        int longestdrift=0;
        String stringoflongest="";
        int drifthash=0;
        for (String s : words) {
            int hash = 0;
            for (Character c : s.toCharArray()) {
                hash = (hash * C + (int) (c)) % m;
            }
            if (s.length()!=0){
                int driftnow=0;
                //add the word and its calculated hash to the hashtable
                if (hashedWords.get(hash)!=null){
                    int startHash = hash;
                    while(hashedWords.get(hash) != null){
                        driftnow++;
                        ++hash;
                        if(startHash == hash){
                            tableFull = true;
                            break;
                        }
                        if(hash >= m){
                            hash = 0;
                            driftnow++;
                        }
                    }
                    if (driftnow>longestdrift){
                        longestdrift=driftnow;
                        stringoflongest=s;
                        drifthash=startHash;
                    }

                    if(tableFull){
                        System.out.println("oh no, something's gone terribly wrong. The hash is full.");
                        System.out.println(s);
                        break;
                    }
                    //System.out.println("Adding word '" + s + "' with hash " + hash);
                    hashedWords.put(hash, s);
                }
                else{
                    cascade++;
                    //System.out.println("Adding word '" + s + "' with hash " + hash);
                    hashedWords.put(hash, s);
                }
            }

        }
        int unique=0;
        double nothing=0;
        int longestopen=0;
        int oldopen=0;
        int oldcluster=0;
        int newcluster=0;
        int endoflongest=0;
        int endofcluster=0;
        LinkedList<Integer> listofhashv=new LinkedList<>();
        boolean wraparound=false;
        int startofcluster=0;
        for (int i = 0; i <m; i++) {
            if (hashedWords.get(i)!=null) {
                if(i==m-1) {
                    newcluster++;
                    if (oldopen < longestopen) {
                        endoflongest = i;
                        oldopen = longestopen;
                    }
                    longestopen = 0;
                    int hash = 0;
                    for (Character c : hashedWords.get(i).toCharArray()) {
                        hash = (hash * C + (int) (c)) % m;
                    }
                    listofhashv.add(hash);
                    System.out.println(i + "   " + hashedWords.get(i) + "    " + hash);
                    unique++;
                    /// THE LOOP BEGINS, DONT TOUCH WHAT IS ABOVE
                    for (int j = 0; j <m; j++) {
                        if (hashedWords.get(j)!=null){
                            wraparound=true;
                            newcluster++;
                        }
                        else{
                            if (oldcluster<newcluster){
                                endofcluster=j;
                                oldcluster=newcluster;
                            }
                            System.out.println("The cluster is "+newcluster);
                            break;
                        }
                    }

                }
                else {
                    newcluster++;
                    if (oldopen < longestopen) {
                        endoflongest = i;
                        oldopen = longestopen;
                    }
                    longestopen = 0;
                    int hash = 0;
                    for (Character c : hashedWords.get(i).toCharArray()) {
                        hash = (hash * C + (int) (c)) % m;
                    }
                    listofhashv.add(hash);
                    System.out.println(i + "   " + hashedWords.get(i) + "    " + hash);
                    unique++;
                }
            }
            else{
                startofcluster=i+1;
                if (oldcluster<newcluster){
                    endofcluster=i;
                    oldcluster=newcluster;
                }
                newcluster=0;
                longestopen++;
                System.out.println(i + "       " + -1);
                nothing++;
            }
        }
        System.out.println("There are "+unique+" distinct entries in the hash table.");
        System.out.println("Of these, "+cascade+" land in their appropriate spots, i.e. without drifting.");
        System.out.println("There are "+nothing+" open cells in the hash table.");
        System.out.println("The load factor (alpha) is thus "+( (double)unique/m)+" for our hash table.");
        System.out.println();
        System.out.println("The longest run of open cells is "+oldopen+" entries long");
        System.out.println("Position "+(endoflongest-oldopen)+" to position "+(endoflongest-1)+" (inclusive)");
        System.out.println();
        System.out.println();
        // if you are reading this, please understand: true, this doesnt work for the most open cells, but ours doesnt need it cause we dont have that many open cells, probably, hopefully.
        System.out.println("The longest cluster is "+oldcluster+" entries long:");
        if (wraparound){
            System.out.println("Position "+startofcluster+" to position "+(Math.abs(m-oldcluster-startofcluster+1))+" (inclusive, cluster starts at first number)");
        }
        else{
            System.out.println("Position "+(endofcluster-oldcluster)+" to position "+(endofcluster-1)+" (inclusive, cluster starts at first number)");
        }
        int mostcom=0;
        int z=0;
        for (int i = 0; i <listofhashv.size(); i++) {
            int lookat=listofhashv.get(i);
            int numtimes=0;
            for (Integer integer : listofhashv) {
                if (integer == lookat) {
                    numtimes++;
                }
            }
            if (numtimes>z){
                mostcom=listofhashv.get(i);
                z=numtimes;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Hash Value "+mostcom+" occurs "+z+" times.");
        System.out.println();
        System.out.println("The word '"+stringoflongest+"' drifts more than any other word,");
        if (drifthash+longestdrift>=m){
            System.out.println(longestdrift+" places from hash adress "+drifthash+" all the way to position "+(drifthash+longestdrift-m-1)+".");
        }
        else{
            System.out.println(longestdrift+" places from hash adress "+drifthash+" all the way to position "+(drifthash+longestdrift)+".");
        }
    }
}