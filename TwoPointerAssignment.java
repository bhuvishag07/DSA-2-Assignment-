import java.util.*;

public class TwoPointerAssignment {

    // PART A: Remove Duplicates
    // Uses a separate list; checks each element against it. Time O(n^2), space O(n)
    public static int removeDuplicatesReference(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            boolean found = false;

            for (int j = 0; j < result.size(); j++) {
                if (arr[i] == result.get(j)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.add(arr[i]);
            }
        }

        return result.size();
    }

    // PART A: Remove Duplicates - Optimized
    // Slow/fast pointers, in-place. Time O(n), space O(1)
    public static int removeDuplicatesOptimized(int[] arr) {
        int n = arr.length;

        if (n == 0) {
            return 0;
        }

        int slow = 0;   // index of the last unique element

        for (int fast = 1; fast < n; fast++) {
            if (arr[fast] != arr[slow]) {   // new unique value found
                slow++;
                arr[slow] = arr[fast];
            }
        }

        return slow + 1;   // count of unique elements
    }

    // PART B: Pair Sum
    // Tries every pair (i, j) with i < j. Time O(n^2), space O(1)
    public static int[] pairSumBruteForce(int[] arr, int target) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};   // no pair found
    }

    // PART B: Pair Sum - Optimized
    // Two pointers from opposite ends. Time O(n), space O(1)
    public static int[] pairSumOptimized(int[] arr, int target) {
        if (arr.length < 2) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[]{left, right};
            }
            else if (sum < target) {
                left++;    // sum too small, move to a larger value
            }
            else {
                right--;   // sum too large, move to a smaller value
            }
        }

        return new int[]{-1, -1};   // no pair found
    }

    // Function to Display Array
    public static void displayArray(int[] arr, int k) {
        System.out.print("[");

        for (int i = 0; i < k; i++) {
            System.out.print(arr[i]);

            if (i < k - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("]");
    }

    // MAIN FUNCTION
    public static void main(String[] args) {

        // PART A: REMOVE DUPLICATES

        System.out.println("PART A: REMOVE DUPLICATES");

        int[][] duplicateTests = {
            {1, 1, 2, 2, 2, 3, 4, 4},
            {5},
            {},
            {-3, -3, -1, 0, 0}
        };

        for (int i = 0; i < duplicateTests.length; i++) {
            int[] arr = duplicateTests[i];

            int referenceK = removeDuplicatesReference(arr);
            int k = removeDuplicatesOptimized(arr);

            System.out.println("\nTest Case " + (i + 1));
            System.out.println("Reference Unique Count: " + referenceK);
            System.out.println("Optimized Unique Count: " + k);
            System.out.print("Unique Prefix: ");
            displayArray(arr, k);
            System.out.println();
        }

        // PART B: PAIR SUM

        System.out.println("\nPART B: PAIR SUM");

        int[][] pairTests = {
            {1, 2, 4, 7, 11, 15},
            {1, 3, 5, 8},
            {2, 4, 6},
            {1, 2, 2, 3}
        };

        int[] targets = {15, 7, 20, 4};

        for (int i = 0; i < pairTests.length; i++) {
            int[] arr = pairTests[i];
            int target = targets[i];

            int[] brute = pairSumBruteForce(arr, target);
            int[] optimized = pairSumOptimized(arr, target);

            System.out.println("\nTest Case " + (i + 1));
            System.out.println("Target: " + target);

            System.out.println("Brute Force Indices: ("
                    + brute[0] + ", " + brute[1] + ")");

            System.out.println("Optimized Indices: ("
                    + optimized[0] + ", " + optimized[1] + ")");
        }
    }
}
