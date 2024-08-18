//264

package Leetcode;

/*
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
Given an integer n, return the nth ugly number.

Example 1:
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

Example 2:
Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

Constraints:
1 <= n <= 1690
*/

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        
        int i2 = 0, i3 = 0, i5 = 0;
        int next2 = 2, next3 = 3, next5 = 5;
        
        for (int i = 1; i < n; i++) {
            int next = Math.min(next2, Math.min(next3, next5));
            ugly[i] = next;
            
            if (next == next2) {
                i2++;
                next2 = ugly[i2] * 2;
            }
            
            if (next == next3) {
                i3++;
                next3 = ugly[i3] * 3;
            }
            
            if (next == next5) {
                i5++;
                next5 = ugly[i5] * 5;
            }
        }
        
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        int n = 10;
        UglyNumberII obj = new UglyNumberII();
        System.out.println(obj.nthUglyNumber(n));
    }
}

/*
class Ugly{
public int[] nums = new int[1690];
Ugly(){
    nums[0] = 1;
    int ugly,i2 = 0,i3=0,i5=0;

    for(int i=1;i<1690;i++){
        ugly = Math.min(Math.min(nums[i2]*2,nums[i3]*3),nums[i5]*5);
        nums[i] = ugly;

        if(ugly == nums[i2]*2) ++i2;
        if(ugly == nums[i3]*3) ++i3;
        if(ugly == nums[i5]*5) ++i5;
        
    }
}
}
class Solution {
    public static Ugly u = new Ugly();
    public int nthUglyNumber(int n) {
        return u.nums[n-1];
    }
}
*/