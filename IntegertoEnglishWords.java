// 273

package Leetcode;

// Convert a non-negative integer num to its English words representation.

// Example 1:
// Input: num = 123
// Output: "One Hundred Twenty Three"

// Example 2:
// Input: num = 12345
// Output: "Twelve Thousand Three Hundred Forty Five"

// Example 3:
// Input: num = 1234567
// Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 
// Constraints:

// 0 <= num <= 231 - 1

public class IntegertoEnglishWords {
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }

    public static String helper(int num) {
        String[] lessThan20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                "Nineteen" };
        String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        String[] thousands = { "", "Thousand", "Million", "Billion" };

        String result = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                result = helper2(num % 1000, lessThan20, tens) + thousands[i] + " " + result;
            }
            num /= 1000;
            i++;
        }
        return result.trim();
    }

    public static String helper2(int num, String[] lessThan20, String[] tens) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return lessThan20[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + helper2(num % 10, lessThan20, tens);
        } else {
            return lessThan20[num / 100] + " Hundred " + helper2(num % 100, lessThan20, tens);
        }
    }

    public static void main(String[] args) {
        int num = 1234567;
        System.out.println(numberToWords(num));
    }    
}


// Optimized Solution

// class Solution {

//     private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
//             "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//     private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

//     public String numberToWords(int num) {
//         if (num == 0) {
//             return "Zero";
//         }
//         return helper(num);
//     }

//     private String helper(int num) {
//         StringBuilder result = new StringBuilder();
//         if (num < 20) {
//             result.append(belowTwenty[num]);
//         } else if (num < 100) {
//             result.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
//         } else if (num < 1000) {
//             result.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
//         } else if (num < 1000000) {
//             result.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
//         } else if (num < 1000000000) {
//             result.append(helper(num / 1000000)).append(" Million ").append(helper(num % 1000000));
//         } else {
//             result.append(helper(num / 1000000000)).append(" Billion ").append(helper(num % 1000000000));
//         }
//         return result.toString().trim();
//     }
// }