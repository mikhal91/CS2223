public class man {
    public static void main(String[] args) {
        int[][] a = {
                {1,3,4,1,2,6},
                {0,0,0,0,0,},
                {7,1,3,1,7,2,},
                {5,1,3,1,-41,22,1,1}
        };
            maths(a);
    }
    public static double maths(int[][] a) {
        double snall = Integer.MAX_VALUE;
        double temp;

        for (int i = 0; i < a.length; i++) {
            temp = 0;
            for (int j = 0; j < a[i].length; j++) {
                temp += a[i][j];
            }
            if(snall > temp){
                snall = temp;
            }
        }
        System.out.println(snall);
        return snall;
    }
}
