//2807

package Leetcode;

/*
Given the head of a linked list head, in which each node contains an integer value.
Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
Return the linked list after insertion.
The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

Example 1:
Input: head = [18,6,10,3]
Output: [18,6,6,2,10,1,3]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes (nodes in blue are the inserted nodes).
- We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
- We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
- We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
There are no more adjacent nodes, so we return the linked list.

Example 2:
Input: head = [7]
Output: [7]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes.
There are no pairs of adjacent nodes, so we return the initial linked list.

Constraints:
The number of nodes in the list is in the range [1, 5000].
1 <= Node.val <= 1000
*/

public class InsertGreatestCommonDivisorsInLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public ListNode insertGCDs(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            int gcd = gcd(current.val, current.next.val);
            ListNode gcdNode = new ListNode(gcd, current.next);
            current.next = gcdNode;
            current = gcdNode.next;
        }
        return head;
    }
    
    public static void main(String[] args) {
        InsertGreatestCommonDivisorsInLinkedList obj = new InsertGreatestCommonDivisorsInLinkedList();
        ListNode head = obj.new ListNode(1);
        head.next = obj.new ListNode(2);
        head.next.next = obj.new ListNode(3);
        head.next.next.next = obj.new ListNode(4);
        head.next.next.next.next = obj.new ListNode(5);
        head.next.next.next.next.next = obj.new ListNode(6);
        head.next.next.next.next.next.next = obj.new ListNode(7);
        head.next.next.next.next.next.next.next = obj.new ListNode(8);
        head.next.next.next.next.next.next.next.next = obj.new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = obj.new ListNode(10);
        head = obj.insertGCDs(head);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
