//1140
package Leetcode;
import java.util.HashMap;
import java.util.Map;

/*
Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
The game continues until all the stones have been taken.
Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

Example 1:
Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 

Example 2:
Input: piles = [1,2,3,4,5,100]
Output: 104
 
Constraints:
1 <= piles.length <= 100
1 <= piles[i] <= 104
*/


public class StoneGameII {
    private Map<String, Integer> dp = new HashMap<>();

    private int score(boolean alice, int idx, int M, int[] piles) {
        if (idx == piles.length) {
            return 0;
        }
        String key = alice + "," + idx + "," + M;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int res = alice ? 0 : Integer.MAX_VALUE;
        int total = 0;

        for (int x = 1; x <= 2 * M; ++x) {
            if (idx + x > piles.length) {
                break;
            }
            total += piles[idx + x - 1];

            if (alice) {
                res = Math.max(res, total + score(!alice, idx + x, Math.max(M, x), piles));
            } else {
                res = Math.min(res, score(!alice, idx + x, Math.max(M, x), piles));
            }
        }

        dp.put(key, res);
        return res;
    }

    public int stoneGameII(int[] piles) {
        return score(true, 0, 1, piles);
    }

    public static void main(String[] args) {
        StoneGameII obj = new StoneGameII();
        int[] piles = {2, 7, 9, 4, 4};
        System.out.println(obj.stoneGameII(piles));
    }
}

//Optimal Soln

/*
class Solution {

    public int stoneGameII(int[] piles) {
        // Store the suffix sum of all array elements.
        int[] suffixSum = Arrays.copyOf(piles, piles.length);

        for (int i = suffixSum.length - 2; i >= 0; i--) {
            suffixSum[i] += suffixSum[i + 1];
        }
        return maxStones(suffixSum, 1, 0, new int[piles.length][piles.length]);
    }

    private int maxStones(
        int[] suffixSum,
        int maxTillNow,
        int currIndex,
        int[][] memo
    ) {
        // If currIndex + 2*maxTillNow lies outside the array, pick all remaining stones.
        if (currIndex + 2 * maxTillNow >= suffixSum.length) {
            return suffixSum[currIndex];
        }
        if (memo[currIndex][maxTillNow] > 0) return memo[currIndex][maxTillNow];
        int res = Integer.MAX_VALUE;
        // Find the minimum value res for the next move possible.
        for (int i = 1; i <= 2 * maxTillNow; i++) {
            res = Math.min(
                res,
                maxStones(
                    suffixSum,
                    Math.max(i, maxTillNow),
                    currIndex + i,
                    memo
                )
            );
        }
        // Memoize the difference of suffixSum[p] and res. This denotes the maximum
        // stones that can be picked.
        memo[currIndex][maxTillNow] = suffixSum[currIndex] - res;
        return memo[currIndex][maxTillNow];
    }
}
*/