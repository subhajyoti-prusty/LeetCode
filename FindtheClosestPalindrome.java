//564

package Leetcode;

/*
Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.
The closest is defined as the absolute difference minimized between two integers.

Example 1:
Input: n = "123"
Output: "121"

Example 2:
Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 
Constraints:
1 <= n.length <= 18
n consists of only digits.
n does not have leading zeros.
n is representing an integer in the range [1, 1018 - 1].
*/

public class FindtheClosestPalindrome {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        long big = findBig(num);
        long small = findSmall(num);
        return Math.abs(num - small) > Math.abs(big - num) ? String.valueOf(big) : String.valueOf(small);
    }

    private long findBig(long num) {
        String n = String.valueOf(num);
        int len = n.length();
        String half = n.substring(0, (len + 1) / 2);
        long firstHalf = Long.parseLong(half);
        long big = Long.parseLong(half + new StringBuilder(half).reverse().substring(len % 2));
        if (big <= num) {
            big = Long.parseLong(String.valueOf(firstHalf + 1)
                    + new StringBuilder(String.valueOf(firstHalf + 1)).reverse().substring(len % 2));
        }
        return big;
    }

    private long findSmall(long num) {
        String n = String.valueOf(num);
        int len = n.length();
        String half = n.substring(0, (len + 1) / 2);
        long firstHalf = Long.parseLong(half);
        long small = Long.parseLong(half + new StringBuilder(half).reverse().substring(len % 2));
        if (small >= num) {
            small = Long.parseLong(String.valueOf(firstHalf - 1)
                    + new StringBuilder(String.valueOf(firstHalf - 1)).reverse().substring(len % 2));
        }
        if (small == 0) {
            small = 9;
            for (int i = 1; i < len; i++) {
                small = small * 10 + 9;
            }
        }
        return small;
    }

    public static void main(String[] args) {
        FindtheClosestPalindrome obj = new FindtheClosestPalindrome();
        System.out.println(obj.nearestPalindromic("123"));
        System.out.println(obj.nearestPalindromic("1"));
    }
}


//Optimal solution

/*
class Solution {
    public String nearestPalindromic(String n) {
        if (Long.parseLong(n) == 1) { return "0"; }
        char[] arr = n.toCharArray();
        int mid = arr.length / 2;
        
        return getClosestPalindrome(arr, mid - (1 - arr.length % 2), mid);  
    }
    String getNextPalindromeForPalindromeNumber(String palinString, int beforeMid, int afterMid) {
        int carry = 1;
        char[] num = palinString.toCharArray();
        
        while (beforeMid >= 0) {
            int number = (num[beforeMid] - '0');
            number += carry;
            num[beforeMid] = (char)(number % 10 + '0');
            num[afterMid] = (char)(number % 10 + '0');;
            carry = number / 10;
            beforeMid--;
            afterMid++;
        }
        if (carry == 1) {
            char[] numNew = new char[num.length + 1];
            Arrays.fill(numNew, '0');
            numNew[numNew.length - 1] = '1';
            numNew[0] = '1';
            return new String(numNew);
        } else {
            return new String(num);
        }
    }
    String getPrevPalindromeForPalindromeNumber(String palinString, int beforeMid, int afterMid) {
        int carry = 1;
        char[] num = palinString.toCharArray();
        
        while (beforeMid >= 0) {
            int number = (num[beforeMid] - '0');
            number = (number == 0 && carry == 1) ? 10 : number;
            number -= carry;
            num[beforeMid] = (char)(number + '0');
            num[afterMid] = (char)(number + '0');;
            carry = (number == 9 && carry == 1) ? 1 : 0;
            beforeMid--;
            afterMid++;
        }
        if (num[0] == '0') {
            char[] numNew = new char[num.length - 1];
            Arrays.fill(numNew, '9');
            return new String(numNew);
        } else {
            return new String(num);
        }
    }
    String getClosestPalindrome(char[] num, int beforeMid, int afterMid) {
        Long originalNum = Long.parseLong(new String(num));
        
        while (beforeMid >= 0) {
            if (num[beforeMid] != num[afterMid]) {
                if (num[beforeMid] > num[afterMid]) {
                    num[afterMid] = num[beforeMid];
                } else {
                    num[afterMid] = num[beforeMid];
                }
            }
            beforeMid--;
            afterMid++;
        }
        String palinString = new String(num);
        Long palinNum = Long.parseLong(palinString);
        
        String prevPalinString = null;
        String nextPalinString = null;
        if (palinNum.equals(originalNum)) {
            prevPalinString = getPrevPalindromeForPalindromeNumber(palinString, (num.length / 2) - (1 - num.length % 2), num.length / 2);
            nextPalinString = getNextPalindromeForPalindromeNumber(palinString, (num.length / 2) - (1 - num.length % 2), num.length / 2);
        } else if (palinNum < originalNum) {
            prevPalinString = palinString;
            nextPalinString = getNextPalindromeForPalindromeNumber(palinString, (num.length / 2) - (1 - num.length % 2), num.length / 2);
        } else {
            prevPalinString = getPrevPalindromeForPalindromeNumber(palinString, (num.length / 2) - (1 - num.length % 2), num.length / 2);  
            nextPalinString = palinString;
        }
        Long prevPalinNum = Long.parseLong(prevPalinString);
        Long nextPalinNum = Long.parseLong(nextPalinString);

        if ((originalNum - prevPalinNum) <= (nextPalinNum - originalNum)) {
            return prevPalinString;    
        } else {
            return nextPalinString;
        }
    }
}
*/