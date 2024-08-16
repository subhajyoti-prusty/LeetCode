// 624

package Leetcode;

/*
You are given m arrays, where each array is sorted in ascending order.

You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.

Return the maximum distance.

 

Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:

Input: arrays = [[1],[1]]
Output: 0
 

Constraints:

m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
*/

public class MaximumDistanceinArrays {
    public int maxDistance(int[][] arrays) {
        int min = arrays[0][0];
        int max = arrays[0][arrays[0].length - 1];
        int result = 0;
        
        for (int i = 1; i < arrays.length; i++) {
            result = Math.max(result, Math.max(Math.abs(arrays[i][arrays[i].length - 1] - min), Math.abs(max - arrays[i][0])));
            min = Math.min(min, arrays[i][0]);
            max = Math.max(max, arrays[i][arrays[i].length - 1]);
        }
        
        return result;
    }
}

 //Optimal Soluntion

/*
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        int secMin = min;
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        int secMax = max;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> arr = arrays.get(i);
            int curMin = arr.get(0);
            int curMax = arr.get(arr.size() - 1);
            if (min > curMin) {
                secMin = min;
                min = curMin;
                minIdx = i;
            } else if (secMin > curMin) {
                secMin = curMin;
            }

            if (max < curMax) {
                secMax = max;
                max = curMax;
                maxIdx = i;
            } else if (secMax < curMax) {
                secMax = curMax;
            }
        }
        return minIdx == maxIdx ? Math.max(max-secMin, secMax-min) : max - min;
    }
}
*/


