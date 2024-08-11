//1568

package Leetcode;

/*
You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

Example 1:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.

Example 2:
Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 30
grid[i][j] is either 0 or 1.
*/

public class MinimumNumberofDaystoDisconnectIsland {
    public int minDays(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (countIslands(grid) != 1) return 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { 
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (countIslands(grid) != 1) return 1;
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }
    
    private void dfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) return;
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
    
    private int countIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            copy[i] = grid[i].clone();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { 
                if (copy[i][j] == 1) {
                    count++;
                    dfs(copy, i, j);
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        MinimumNumberofDaystoDisconnectIsland obj = new MinimumNumberofDaystoDisconnectIsland();
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(obj.minDays(grid));
    }
}

//Optimal Solution

/*
class Solution {
    int time;
    int[][] vis;
    int[][] low;
    int[] d=new int[]{0,1,0,-1,0};
    boolean arti;
    public int minDays(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        arti=false;
        vis=new int[n][m];
        low=new int[n][m];
        time=1;
        boolean hasArt=false;
        boolean found=false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    if(found)
                        return 0;
                    found=true;
                    art(i,j,grid,-100,-100);
                }
            }
        }

        if(time==1)
            return 0;

        if(time==2)
            return 1;
        if(arti)
            return 1;
        else
            return 2;
    }

    public void art(int row,int col,int[][] grid , int parRow,int parCol){
        grid[row][col]=0;
        vis[row][col]=time;
        low[row][col]=time;
        time++;
        int child=0;
        for(int i=0;i<4;i++){
            int x=d[i]+row;
            int y=d[i+1]+col;

            if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || (x==parRow && y==parCol) || (vis[x][y]==0 && grid[x][y]==0))
                continue;
            if(vis[x][y]==0){
                child++;
                art(x,y,grid,row,col);
                low[row][col]=Math.min(low[row][col],low[x][y]);
                if(low[x][y]>=vis[row][col] && (parRow!=-100 && parCol!=-100))
                    arti=true;
            }else{
                low[row][col]=Math.min(low[row][col],vis[x][y]);
            }
        }

        if(parRow==-100 && parCol==-100 && child>1)
            arti=true;
    }
}
*/
