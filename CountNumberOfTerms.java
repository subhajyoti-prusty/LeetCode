// (1395)
package Leetcode;

// There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

// You have to form a team of 3 soldiers amongst them under the following rules:

// Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
// A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
// Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

// Example 1:

// Input: rating = [2,5,3,4,1]
// Output: 3
// Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
// Example 2:

// Input: rating = [2,1,3]
// Output: 0
// Explanation: We can't form any team given the conditions.
// Example 3:

// Input: rating = [1,2,3,4]
// Output: 4

// Constraints:

// n == rating.length
// 3 <= n <= 1000
// 1 <= rating[i] <= 105
// All the integers in rating are unique.

public class CountNumberOfTerms {

    public static int numTeams(int[] rating) {
        int n = rating.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int leftSmaller = 0;
            int leftLarger = 0;
            int rightSmaller = 0;
            int rightLarger = 0;
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    if (rating[i] < rating[j]) {
                        rightLarger++;
                    } else {
                        rightSmaller++;
                    }
                } else if (i > j) {
                    if (rating[i] < rating[j]) {
                        leftLarger++;
                    } else {
                        leftSmaller++;
                    }
                }
            }
            count += leftSmaller * rightLarger + leftLarger * rightSmaller;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] rating = { 2, 5, 3, 4, 1 };
        System.out.println(numTeams(rating));
    }
}

// optimal Solution
/*
 * public static int numTeams(int[] rating) {
 * int n = rating.length;
 * int count = 0;
 * 
 * for (int i = 0; i < n - 2; i++) {
 * for (int j = i + 1; j < n - 1; j++) {
 * for (int k = j + 1; k < n; k++) {
 * if ((rating[i] < rating[j] && rating[j] < rating[k]) ||
 * (rating[i] > rating[j] && rating[j] > rating[k])) {
 * count++;
 * }
 * }
 * }
 * }
 * 
 * return count;
 * }
 * }
 */