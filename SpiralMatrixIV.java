//2326

package Leetcode;

/*
You are given two integers m and n, which represent the dimensions of a matrix.
You are also given the head of a linked list of integers.
Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
Return the generated matrix.

Example 1:
Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
Explanation: The diagram above shows how the values are printed in the matrix.
Note that the remaining spaces in the matrix are filled with -1.

Example 2:
Input: m = 1, n = 4, head = [0,1,2]
Output: [[0,1,2,-1]]
Explanation: The diagram above shows how the values are printed from left to right in the matrix.
The last space in the matrix is set to -1.
 
Constraints:
1 <= m, n <= 105
1 <= m * n <= 105
The number of nodes in the list is in the range [1, m * n].
0 <= Node.val <= 1000
*/

public class SpiralMatrixIV {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public int[][] matrixFromHead(ListNode head, int m, int n) {
        ListNode curr = head;
        int[][] ans = new int[m][n];

         // Initialize the matrix with -1
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                ans[i][j] = -1;
            }
        }

        int top = 0, bottom = m-1, left = 0, right= n-1;

        // Process until the linked list is exhausted or the matrix bounds are exceeded
        while(curr != null && top<=bottom && left <=right){

            // Fill top row (left to right)
            for(int i=left; i<=right && curr!=null; i++){
                ans[top][i] = curr.val;
                curr = curr.next;
            }
            top++;

            // Fill right column (top to bottom)
            for(int i=top; i<=bottom && curr!=null; i++){
                ans[i][right]= curr.val;
                curr = curr.next;
            }
            right--;

            // Fill bottom row (right to left)
            for(int i=right; i>=left && curr != null; i--){
                ans[bottom][i] = curr.val;
                curr = curr.next;
            }
            bottom--;

            // Fill left column (bottom to top)
            for(int i=bottom; i>=top && curr!=null; i--){
                ans[i][left] = curr.val;
                curr = curr.next;
            }
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SpiralMatrixIV obj = new SpiralMatrixIV();
        ListNode head = obj.new ListNode(3);
        head.next = obj.new ListNode(0);
        head.next.next = obj.new ListNode(2);
        head.next.next.next = obj.new ListNode(6);
        head.next.next.next.next = obj.new ListNode(8);
        head.next.next.next.next.next = obj.new ListNode(1);
        head.next.next.next.next.next.next = obj.new ListNode(7);
        head.next.next.next.next.next.next.next = obj.new ListNode(9);
        head.next.next.next.next.next.next.next.next = obj.new ListNode(4);
        head.next.next.next.next.next.next.next.next.next = obj.new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next = obj.new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next = obj.new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next.next = obj.new ListNode(0);
        int m = 3, n = 5;
        int[][] result = obj.matrixFromHead(head, m, n);
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
