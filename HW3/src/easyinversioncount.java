public class easyinversioncount {

    public static void main(String[] args){
        int[] array = new int[]{3, 2, 1};

        System.out.println("Number of inversions in " + printArray(array) + " is: " + bubbleSort(array));
    }


    //counts the amount of swaps
    public static int bubbleSort(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    ++count;
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return count;
    }

    public static String printArray(int[] a){
        int n = a.length;
        String answer = "[";
        for (int i=0; i < n; ++i) {
            answer += (a[i]);
            answer += i == n - 1 ? "" : " ";
        }
        answer += "]";
        return answer;
    }
}
