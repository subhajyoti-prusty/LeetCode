// 959

package Leetcode;

/*
 An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

 

Example 1:


Input: grid = [" /","/ "]
Output: 2
Example 2:


Input: grid = [" /","  "]
Output: 1
Example 3:


Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
*/

class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] graph = new int[3 * n][3 * n];
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            String row = grid[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (c == '/') {
                    graph[3 * i][3 * j + 2] = 1;
                    graph[3 * i + 1][3 * j + 1] = 1;
                    graph[3 * i + 2][3 * j] = 1;
                } else if (c == '\\') {
                    graph[3 * i][3 * j] = 1;
                    graph[3 * i + 1][3 * j + 1] = 1;
                    graph[3 * i + 2][3 * j + 2] = 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                if (graph[i][j] == 0) {
                    dfs(graph, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int[][] graph, int i, int j) {
        if (i < 0 || i >= graph.length || j < 0 || j >= graph[0].length || graph[i][j] == 1) {
            return;
        }
        graph[i][j] = 1;
        dfs(graph, i - 1, j);
        dfs(graph, i + 1, j);
        dfs(graph, i, j - 1);
        dfs(graph, i, j + 1);
    }
}

