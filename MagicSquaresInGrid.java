//840

package Leetcode;

/*
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?

Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.

 

Example 1:


Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:

while this one is not:

In total, there is only one magic square inside the given grid.
Example 2:

Input: grid = [[8]]
Output: 0
 

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 10
0 <= grid[i][j] <= 15
 */

public class MagicSquaresInGrid {
    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length - 2; i++) {
            for(int j = 0; j < grid[0].length - 2; j++) {
                if(isMagicSquare(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isMagicSquare(int[][] grid, int i, int j) {
        int[] count = new int[16];
        for(int x = i; x < i + 3; x++) {
            for(int y = j; y < j + 3; y++) {
                if(grid[x][y] < 1 || grid[x][y] > 9) {
                    return false;
                }
                count[grid[x][y]]++;
                if(count[grid[x][y]] > 1) {
                    return false;
                }
            }
        }
        int sum = grid[i][j] + grid[i][j + 1] + grid[i][j + 2];
        for(int x = i + 1; x < i + 3; x++) {
            int temp = grid[x][j] + grid[x][j + 1] + grid[x][j + 2];
            if(temp != sum) {
                return false;
            }
        }
        for(int y = j; y < j + 3; y++) {
            int temp = grid[i][y] + grid[i + 1][y] + grid[i + 2][y];
            if(temp != sum) {
                return false;
            }
        }
        if(grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] != sum) {
            return false;
        }
        if(grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j] != sum) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	MagicSquaresInGrid obj = new MagicSquaresInGrid();
    	int[][] grid = {{4,3,8,4},{9,5,1,9},{2,7,6,2}};
    	System.out.println(obj.numMagicSquaresInside(grid));
    }
}