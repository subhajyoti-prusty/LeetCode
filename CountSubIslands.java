//1905

package Leetcode;

/*
You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 
Constraints:
m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
*/

public class CountSubIslands {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid2[i][j] == 1){
                    if(dfs(grid1, grid2, i, j)){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    public boolean dfs(int[][] grid1, int[][] grid2, int i, int j){
        int m = grid1.length, n = grid1[0].length;
        if(i<0 || i>=m || j<0 || j>=n || grid2[i][j] == 0){
            return true;
        }
        if(grid1[i][j] == 0){
            return false;
        }
        grid2[i][j] = 0;
        boolean up = dfs(grid1, grid2, i-1, j);
        boolean down = dfs(grid1, grid2, i+1, j);
        boolean left = dfs(grid1, grid2, i, j-1);
        boolean right = dfs(grid1, grid2, i, j+1);
        return up && down && left && right;
    }

    public static void main(String[] args) {
        CountSubIslands countSubIslands = new CountSubIslands();
        int[][] grid1 = {{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}};
        int[][] grid2 = {{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}};
        System.out.println(countSubIslands.countSubIslands(grid1, grid2));
    }
}

//Optimal Solution

/*
class Solution {
    int n, m;
    int[][] isValid;
    int[] dx = new int[] {-1, 0 , 0 ,1};
    int[] dy = new int[] {0, -1, 1, 0};
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid1.length;
        m = grid1[0].length;
        isValid = new int[n][m];
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (isValid[i][j] == 0 && grid2[i][j] == 1) {
                    if (isSubIslands(i, j, grid1, grid2)) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    private boolean isSubIslands(int r, int c, int[][] grid1, int[][] grid2) {
        if (grid1[r][c] != 1) {
            isValid[r][c] = -1;
            return false;
        }
        isValid[r][c] = 1;
        for (int k = 0 ; k < 4; k++) {
            int nr = r + dx[k];
            int nc = c + dy[k];
            if (nr <  0 || nr >= n || nc < 0 || nc >= m) continue;
            if (isValid[nr][nc] == -1) {
                isValid[r][c] = -1;
                return false;
            }
            if (isValid[nr][nc] == 1) continue;
            if (grid2[nr][nc] == 1 && !isSubIslands(nr, nc, grid1, grid2)) {
                isValid[r][c] = -1;
                return false;
            }
        }
        return true;
    }
}
*/