//Worked together with Yasin Akbashev
//Worked together with Michael Alicea

/*
    1. BetterForwardElimination remedies the problem of ForwardElimination failing to provide a solution to the matrix when
    it tries to pivot around the number 0 which it cant do, by checking the pivot to make sure it is not 0, the program will
    think the array is unsolvable if the pivot is zero since there isn't any way to reduce the rows using the number zero.
    BetterForwardElimination chooses another row to use as its pivot instead.


    2. BetterForwardElimination has trouble with this matrix because there are infinite solutions to this matrix.
       To remedy this shortcoming we could check if two rows are multiples of eachother, and if they are it would
       stop looping through the function since that would mean there are infinite solutions, it could say this too.
     */



public class GaussJordanElimination {


    public static void main(String[] args) {

        float[][] a = {{1,1,1,1,1,1,1,1,1,122},
                {1,1,1,1,1,-1,-1,-1,-1,-88},
                {1,-1,1,-1,1,-1,1,-1,1,32},
                {1,1,0,0,0,0,0,0,0,3},
                {0,0,1,1,0,0,0,0,0,7},
                {0,0,0,0,1,1,0,0,0,18},
                {0,0,0,0,0,0,0,1,1,76},
                {1,1,-1,1,1,-1,1,1,-1,0},
                {9,-8,7,-6,5,-4,3,-2,1,41},

        };
        SolveMatrix(a);
    }

    public static float[][] SolveMatrix(float[][] A){
        float[][] a=new float[A.length][A[0].length];
        for (int row = 0; row <a.length; row++) {
            for (int col = 0; col <a[row].length; col++) {
                a[row][col]=A[row][col];
            }
        }
        for (int row = 0; row <a.length; row++) {
            for (int column = 0; column <a[row].length-1; column++) {

                if (row==column) {
                    if (a[row][column] != 0) {

                        for (int change = 0; change < a.length; change++) {
                            if (change != row && a[change][column] != 0) {
                                float x = a[change][column] / a[row][column];
                                //System.out.println("x is " + x);
                                //System.out.println("ARRAY BEFORE CHANGE IS BELOW");
                                printarray(a);
                                //System.out.println("ARRAY BEFORE CHANGE IS ABOVE");
                                System.out.println("R" + change + "=R" + change + " -" + x + "*R" + row);
                                for (int uprow = 0; uprow < a[change].length; uprow++) {
                                    a[change][uprow] = a[change][uprow] - (x * a[row][uprow]);
                                }
                                printarray(a);
                            }
                        }
                    }/// if the diag is not 0
                    else{
                        float largest = 0;
                        int largestindex = 0;
                        for (int bestrow = 0; bestrow <a.length; bestrow++) {
                            if (Math.abs(largest)<Math.abs(a[bestrow][column]) && Math.abs(largest)==0&& bestrow>row){
                                largest=a[bestrow][column];
                                largestindex=bestrow;
                            }
                        }
                        for (int bestrow = 0; bestrow <a.length; bestrow++) {
                            if (Math.abs(largest)>Math.abs(a[bestrow][column]) && Math.abs(a[bestrow][column])!=0&& bestrow>row){
                                largest=a[bestrow][column];
                                largestindex=bestrow;
                            }
                        }
                        //System.out.println("FOR SWAPPING the largest index is "+largestindex+" with the number "+largest);
                        int swapto=largestindex;
                        //System.out.println("WE ARE FUNKY TOWN NOW");
                        //System.out.println("ARRAY BEFORE CHANGE IS BELOW");
                        printarray(a);
                        //System.out.println("ARRAY BEFORE CHANGE IS ABOVE");
                        System.out.println("R"+row+"=R"+swapto);
                        for (int uprow = 0; uprow < a[swapto].length; uprow++) {
                            float temp=a[swapto][uprow];
                            float temp2=a[row][uprow];
                            a[row][uprow]=temp;
                            a[swapto][uprow]=temp2;
                        }
                        printarray(a);
                        //System.out.println("WE ARE NOW DOING MINUS PLUS");
                        for (int change = 0; change < a.length; change++) {
                            if (change != row && a[change][column] != 0) {
                                float x = a[change][column] / a[row][column];
                                //System.out.println("x is " + x);
                                //System.out.println("ARRAY BEFORE CHANGE IS BELOW");
                                printarray(a);
                                //System.out.println("ARRAY BEFORE CHANGE IS ABOVE");
                                System.out.println("R" + change + "=R" + change + " -" + x + "*R" + row);
                                for (int uprow = 0; uprow < a[change].length; uprow++) {
                                    a[change][uprow] = a[change][uprow] - (x * a[row][uprow]);
                                }
                                printarray(a);
                            }
                        }
                    }
                }
            }
        }



        System.out.println("We now bean town");
        for (int row = 0; row <a.length; row++) {
            for (int column = 0; column <a[row].length-1; column++) {
                if (column==row){
                    a[row][a[row].length-1]=a[row][a[row].length-1]/a[row][column];
                    a[row][column]=1;
                }
            }
        }
        printarray(a);
        System.out.println("Lets check if its correct now");
        if (iscorrect(A,a)){
            System.out.println("yep, its all good");
        }
        else{
            System.out.println("This is not a matrix with a solution. very sad!");
        }
        return a;
    }


    public static boolean iscorrect(float[][] A,float[][] a){
        float[] nums=new float[a.length];
        for (int i = 0; i <a.length; i++) {
            nums[i]=a[i][a.length];
        }
        for (int i = 0; i <nums.length; i++) {
            float goal=0;
            //System.out.println("We want goal to reach "+A[i][A.length]);
            for (int j = 0; j <a[i].length-1; j++) {
                goal+=A[i][j]*nums[j];
                //System.out.println("goal is "+goal+" and we're adding "+A[i][j]+"*"+nums[j]+" which is "+(A[i][j]*nums[i]));
            }
            if (goal!=A[i][A.length]){
                System.out.println("The matrix fails when trying out x variable number "+(i+1));
                return false;
            }
        }
        int index=1;
        for (float c:nums) {
            System.out.println(c +" is x"+index);
            index++;
        }
        return true;
    }

    public static boolean allgood(float[][]a){
        for (int i = 0; i <a.length; i++) {
            if (!rowalone(a,i)){
                return false;
            }
        }
        return true;
    }
    public static boolean rowalone(float[][] a,int rowindex){

        int num0sgoal=a[rowindex].length-2;
        int num0s=0;

        for (int i = 0; i <a[rowindex].length-1; i++) {
            if (a[rowindex][i]==0){
                //System.out.println("our array at "+rowindex+","+i+"= 0");
                num0s++;
            }
        }
        if (num0s==num0sgoal){
            //System.out.println("we have "+num0s+" 0's, and that means that there's 1 other number completely alone in the row.");
            return true;
        }
        //System.out.println("we have "+num0s+" 0's, but we want "+num0sgoal+" 0's, so the row has multiple non-zeros");
        return false;
    }
    public static int wheretoswap(float[][] a, int column){
        for (int row = 0; row <a.length; row++) {
            if (a[row][column]!=0){
                return row;
            }
        }

        return 0;
    }
    public static void printarray(float[][] a){
        System.out.println("array now is: ");
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j <a[i].length; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

    }



/*
boolean multifound=false;
                                float x=0;
                                int index=0;
                                for (int i = 0; i <a.length; i++) {
                                    if (a[i][column]!=0&&i!=row&&!multifound){
                                        x=diagonal/a[i][column];
                                        multifound=true;
                                        index=i;
                                    }
                                }
                                System.out.println("we're on the last column");
                                System.out.println("THE PIVOT IS 0, x is "+x);
                                System.out.println("ARRAY BEFORE CHANGE IS BELOW");
                                printarray(a);
                                System.out.println("ARRAY BEFORE CHANGE IS ABOVE");
                                for (int uprow = 0; uprow < a[rowcheck].length; uprow++) {
                                    a[row][uprow] = a[row][uprow] - (x * a[index][uprow]);
                                    printarray(a);
                                }
 */

}