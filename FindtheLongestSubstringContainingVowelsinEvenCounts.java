//1317

package Leetcode;

/*
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

Example 1:
Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

Example 2:
Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.

Example 3:
Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 
Constraints:
1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
*/

public class FindtheLongestSubstringContainingVowelsinEvenCounts {
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] vowels = new int[5];
        int[] firstOccurrence = new int[32];
        for (int i = 0; i < 32; i++) {
            firstOccurrence[i] = -1;
        }
        firstOccurrence[0] = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                vowels[0]++;
            } else if (c == 'e') {
                vowels[1]++;
            } else if (c == 'i') {
                vowels[2]++;
            } else if (c == 'o') {
                vowels[3]++;
            } else if (c == 'u') {
                vowels[4]++;
            }
            int mask = 0;
            for (int j = 0; j < 5; j++) {
                if (vowels[j] % 2 == 1) {
                    mask |= (1 << j);
                }
            }
            if (firstOccurrence[mask] != -1) {
                result = Math.max(result, i + 1 - firstOccurrence[mask]);
            } else {
                firstOccurrence[mask] = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindtheLongestSubstringContainingVowelsinEvenCounts obj = new FindtheLongestSubstringContainingVowelsinEvenCounts();
        System.out.println(obj.findTheLongestSubstring("eleetminicoworoep")); // 13
        System.out.println(obj.findTheLongestSubstring("leetcodeisgreat")); // 5
        System.out.println(obj.findTheLongestSubstring("bcbcbc")); // 6
    }
}

//Optimal Solution

/*
class Solution {
    static boolean[] isVowel = new boolean[]{true, false, false, false, true, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false};
    static int[] xor = new int[]{1, 0, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0};
    public int findTheLongestSubstring(String s) {
        int[] earliest = new int[32];
        Arrays.fill(earliest, -2);
        earliest[0] = -1;
        char[] cs = s.toCharArray();
        int key = 0;
        int longest = 0;
        for (int i = 0; i < cs.length; i++) {
            if (isVowel[cs[i] - 'a']) key ^= xor[cs[i] - 'a'];
            if (earliest[key] == -2) earliest[key] = i;
            else {
                int len = i - earliest[key];
                if (len > longest) longest = len;
            }
        }
        return longest;
    }
}
*/