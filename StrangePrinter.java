//664

package Leetcode;

/*
There is a strange printer with the following two special properties:
The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

Example 1:
Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".

Example 2:
Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 
Constraints:
1 <= s.length <= 100
s consists of lowercase English letters.
*/

public class StrangePrinter {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for(int j = i+1; j < n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k+1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        StrangePrinter sp = new StrangePrinter();
        System.out.println(sp.strangePrinter("aaabbb"));
        System.out.println(sp.strangePrinter("aba"));
    }
}

//Optimal Soln

/*
class Solution {
    public int strangePrinter(String s) {
        char[] sc = s.toCharArray();
        final int n = removeDuplicates(sc);
        if (n <= 1)  return n;
        return dfs(0, n - 1, sc, new int[n][n]);
    }
    
    
    private int dfs(int left, int right, char[] sc, int[][] memo) {
        if (left >= right)  return (left == right) ? 1 : 0;
        if (memo[left][right] != 0)  return memo[left][right];
        int letter = sc[left];
        int answer = 1 + dfs(left + 1, right, sc, memo);
        for (int k = left + 1; k <= right; k++) 
            if (sc[k] == letter) 
                answer = Math.min(answer, dfs(left+1, k - 1, sc, memo) + dfs(k, right, sc, memo));
        return memo[left][right] = answer;
    }
    
    
    private int removeDuplicates(char[] sc) {
        int scOutIdx = 0;
        char prev = 0;
        for (char c : sc) {
            if (c != prev) {
                sc[scOutIdx++] = c;
                prev = c;
            }
        }
        return scOutIdx;
    }
}
*/