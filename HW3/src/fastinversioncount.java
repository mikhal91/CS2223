import java.util.Arrays;

public class fastinversioncount {


    public static void main(String[] args){
        int[] testArr1 = new int[]{3, 2, 1};
        int[] testArr2 = new int[]{4, 3, 2, 1, 8, 7, 6 ,5};
        int numInversions1 = mergeSortAndCount(testArr1, 0, testArr1.length - 1);
        int numInversions2 = mergeSortAndCount(testArr2, 0, testArr2.length - 1);
        System.out.println("Number of inversions in " + printArray(testArr1) + " is: " + numInversions1);
        System.out.println("Number of inversions in " + printArray(testArr2) + " is: " + numInversions2);

    }


    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;

        if (left < right) {
            int m = (left + right) / 2;

            count += mergeSortAndCount(arr, left, m);

            count += mergeSortAndCount(arr, m + 1, right);

            count += mergeAndCount(arr, left, m, right);
        }

        return count;
    }

    private static int mergeAndCount(int[] arr, int leftIndex, int middleIndex, int rightIndex) {
        int[] left = Arrays.copyOfRange(arr, leftIndex, middleIndex + 1);
        int[] right = Arrays.copyOfRange(arr, middleIndex + 1, rightIndex + 1);

        int i = 0, j = 0, k = leftIndex, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (middleIndex + 1) - (leftIndex + i);
            }
        }


        while (i < left.length)
            arr[k++] = left[i++];


        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    public static String printArray(int[] arr){
        int n = arr.length;
        String answer = "[";
        for (int i=0; i < n; ++i) {
            answer += (arr[i]);
            answer += i == n - 1 ? "" : " ";
        }
        answer += "]";
        return answer;
    }
}