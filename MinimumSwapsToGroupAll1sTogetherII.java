// 2134

package Leetcode;
import java.util.Arrays;

// A swap is defined as taking two distinct positions in an array and swapping the values in them.

// A circular array is defined as an array where we consider the first element and the last element to be adjacent.

// Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.

// Example 1:

// Input: nums = [0,1,0,1,1,0,0]
// Output: 1
// Explanation: Here are a few of the ways to group all the 1's together:
// [0,0,1,1,1,0,0] using 1 swap.
// [0,1,1,1,0,0,0] using 1 swap.
// [1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
// There is no way to group all 1's together with 0 swaps.
// Thus, the minimum number of swaps required is 1.
// Example 2:

// Input: nums = [0,1,1,1,0,0,1,1,0]
// Output: 2
// Explanation: Here are a few of the ways to group all the 1's together:
// [1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
// [1,1,1,1,1,0,0,0,0] using 2 swaps.
// There is no way to group all 1's together with 0 or 1 swaps.
// Thus, the minimum number of swaps required is 2.
// Example 3:

// Input: nums = [1,1,0,0,1]
// Output: 0
// Explanation: All the 1's are already grouped together due to the circular property of the array.
// Thus, the minimum number of swaps required is 0.

// Constraints:

// 1 <= nums.length <= 105
// nums[i] is either 0 or 1.

public class MinimumSwapsToGroupAll1sTogetherII {
    public int minSwaps(int[] nums) {
        int k = Arrays.stream(nums).sum();
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < k; ++i) {
            cnt += nums[i];
        }
        int mx = cnt;
        for (int i = k; i < n + k; ++i) {
            cnt += nums[i % n] - nums[(i - k + n) % n];
            mx = Math.max(mx, cnt);
        }
        return k - mx;
    }

    public static void main(String[] args) {
        MinimumSwapsToGroupAll1sTogetherII obj = new MinimumSwapsToGroupAll1sTogetherII();
        int[] nums = { 0, 1, 0, 1, 1, 0, 0 };
        System.out.println(obj.minSwaps(nums));
    }
}

// optimal code 

// public int minSwaps(int[] nums) {
//     int k=0;
//     for(int i=0;i<nums.length;i++){
//         k+=nums[i];
//     }
//     int sum=0; int min=nums.length;
//     for(int j=0;j<nums.length;j++){
//         sum+=nums[j];
//         if(j>=k){sum-=nums[j-k];}
//         if(j>=k-1)min=Math.min(min,k-sum);
//     }
//     for(int i=0;i<=k-2;i++){
//         //length-k,...,length-1
//         //length-k+1 - 0; length-k+2 - 1; length-k+3 - 2;
//         sum=sum+nums[i]-nums[nums.length-k+i];
//         min=Math.min(min,k-sum);
//     }
//     return min;
// }