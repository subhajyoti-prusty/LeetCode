//719

package Leetcode;
import java.util.Arrays;

/*
The distance of a pair of integers a and b is defined as the absolute difference between a and b.
Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.


Example 1:
Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.

Example 2:
Input: nums = [1,1,1], k = 2
Output: 0

Example 3:
Input: nums = [1,6,1], k = 3
Output: 5
 

Constraints:
n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2
*/

public class FindKthSmallestPairDistance {

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = nums[n - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            int left = 0;
            for (int right = 0; right < n; right++) {
                while (nums[right] - nums[left] > mid) {
                    left++;
                }
                count += right - left;
            }
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        FindKthSmallestPairDistance obj = new FindKthSmallestPairDistance();
        int[] nums = { 1, 3, 1 };
        int k = 1;
        System.out.println(obj.smallestDistancePair(nums, k));
    }
}

// Optimal Solution

/*
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
       Arrays.sort(nums);
       int low = 0;
       int high = nums[nums.length-1]-nums[0];
       while(low<high){
        int mid = low +(high-low)/2;
        int count = countPairsWithSumMid(nums,mid);
        if(count>=k){
          high=mid;   
        } else {
            low = mid+1;
        }
       }
       return low;    
    }
    int countPairsWithSumMid(int[] nums, int sum){
        int count=0;
        int left=0;

        for(int right=1;right<nums.length;right++){
            while(nums[right]-nums[left]>sum){
                left++;
            }
            count+=right-left;  
        }
        return count;
        
    }
}
*/