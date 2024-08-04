// 1508

package Leetcode;
import java.util.Arrays;

/*
You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.
Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 109 + 7.

Example 1:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13 
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13. 

Example 2:
Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.

Example 3:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50
 
Constraints:
n == nums.length
1 <= nums.length <= 1000
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2
*/
public class RangeSumofSortedSubarraySums {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int mod = 1000000007;
        int[] sum = new int[n * (n + 1) / 2];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                sum[k++] = s;
            }
        }
        Arrays.sort(sum);
        int ans = 0;
        for (int i = left - 1; i < right; i++) {
            ans = (ans + sum[i]) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        RangeSumofSortedSubarraySums obj = new RangeSumofSortedSubarraySums();
        int[] nums = {1, 2, 3, 4};
        int n = 4;
        int left = 1;
        int right = 5;
        System.out.println(obj.rangeSum(nums, n, left, right));
    }
}

// Optimal Soln 

// import java.util.AbstractMap;
// import java.util.Map;

// class Solution {
//     long largestSubArraySumPossible = 0, smallestSubArraySumPossible = 0;

//     public Map.Entry<Integer, Long> subArraysWithSumLessThanOrEqualTo(int[] nums, long target){

//         int countOfSuchSubArrays = 0;
//         long totalSum = 0;
//         long windowSum = 0;
//         long currSum = 0;
//         int n = nums.length;

//         for (int left = 0, right = 0; right < n; ++right) {
//             currSum += (long) nums[right] * (right - left + 1);
//             windowSum += nums[right]; 
//             while (windowSum > target) {
//                 currSum -= windowSum;
//                 windowSum -= nums[left++];
//             }
//             countOfSuchSubArrays += right - left + 1;
//             totalSum += currSum;
//         }
//         return new AbstractMap.SimpleEntry<>(Integer.valueOf(countOfSuchSubArrays), Long.valueOf(totalSum));
//     }

//     public long firstKSubarraysSum(int[] nums, int k){
//         long start = smallestSubArraySumPossible, end = largestSubArraySumPossible;
//         while (start < end) {
//             long mid = start + (end - start) / 2;
//             if (subArraysWithSumLessThanOrEqualTo(nums, mid).getKey() < k) {
//                 start = mid + 1;
//             } else {
//                 end = mid;
//             }
//         }
//         Map.Entry<Integer, Long> map = subArraysWithSumLessThanOrEqualTo(nums, start);
//         long totalOfFirstKSubarraysSum = map.getValue();
//         int countOfSuchSubArrs = map.getKey();
//         return totalOfFirstKSubarraysSum - start * (countOfSuchSubArrs - k);
//     }

//     public int rangeSum(int[] nums, int n, int left, int right) {
//         int mod = (int) 1e9 + 7;
//         long ans = 0;
//         smallestSubArraySumPossible = nums[0];

//         for (int i = 0; i < n; i++) {
//             largestSubArraySumPossible += nums[i];
//             smallestSubArraySumPossible = Math.min(smallestSubArraySumPossible, nums[i]);
//         }

//         ans = firstKSubarraysSum(nums, right) % mod; 
//         ans -= firstKSubarraysSum(nums, left - 1) % mod;

//         return (int) (ans % mod);
//     }
// }