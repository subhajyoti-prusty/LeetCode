//1514

package Leetcode;
import java.util.*;

/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

Example 1:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.

Example 2:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000

Example 3:
Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 
Constraints:
2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
*/

class PathwithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], i});
            graph.get(edge[1]).add(new int[]{edge[0], i});
        }

        double[] prob = new double[n];
        prob[start] = 1.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.offer(new double[]{start, 1.0});

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int node = (int) cur[0];
            double curProb = cur[1];

            if (node == end) {
                return curProb;
            }

            for (int[] next : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = next[0];
                double nextProb = succProb[next[1]] * curProb;

                if (nextProb > prob[nextNode]) {
                    prob[nextNode] = nextProb;
                    pq.offer(new double[]{nextNode, nextProb});
                }
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        PathwithMaximumProbability obj = new PathwithMaximumProbability();
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.2};
        int start = 0;
        int end = 2;
        System.out.println(obj.maxProbability(n, edges, succProb, start, end));
    }
}

//Optimal Solution

/*
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] probs = new double[n];
        probs[start_node] = 1;
        for(int i = 0; i < n - 1; i++) {
            boolean change = false;
            for(int j = 0; j < edges.length; j++) {
                int src = edges[j][0];
                int dest = edges[j][1];
                double prob = succProb[j];

                if(probs[src] * prob > probs[dest]) {
                    probs[dest] = probs[src] * prob;
                    change = true;
                }
                if(probs[dest] * prob > probs[src]) {
                    probs[src] = probs[dest] * prob;
                    change = true;
                }
            }
            if(change == false) break;
        }
        return probs[end_node];
    }
}
*/