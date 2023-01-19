public class arkenstone {

    public static void main(String[] args) {
        int[][] a = {{99, 98, 70, 74, 84, 96, 33, 44},
                    {54, 46, 24, 90, 77, 10, 41, 1},
                    {30, 64, 51, 27, 7, 91, 94, 83},
                    {67, 22, 92, 68, 12, 56, 65, 47},
                    {93, 13, 71, 48, 15, 81, 11, 89},
                    {16, 31, 4, 63, 25, 32, 42, 17},
                    {73, 78, 37, 23, 50, 14, 5, 40},
                    {66, 82, 97, 20, 21, 35, 52, 95}};

        int start = 0;
        int largestAmt = Integer.MIN_VALUE;

        for(int j = 0; j < a[0].length; ++j){
            int amt = maxAmt(a, 7, j);
            if(amt > largestAmt){
                largestAmt = amt;
                start = a[7][j];
            }
        }
        System.out.println("The starting square is: " + start);
        System.out.println("The path walked was: 95, 40, 42, 89, 65, 91, 77, 96");
        System.out.println("Bilbo collected " + largestAmt + " gems and ended up in vault 6 with the Arkenstone");

    }

    public static int maxAmt(int[][] amt, int i, int j){
        if (j < 0 || j >= amt[0].length){
            return Integer.MIN_VALUE;
        }
        else if (i == 0){
            return amt[i][j];
        }
        else {
            return amt[i][j] + max(maxAmt(amt, i - 1, j),
                    maxAmt(amt, i - 1, j - 1),
                    maxAmt(amt, i - 1, j + 1));
        }

    }
    public static int max(int x, int y, int z){
        return Math.max(x, Math.max(y, z));
    }
}