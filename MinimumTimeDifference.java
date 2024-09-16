//539

package Leetcode;

/*
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.

Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:
2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
*/

public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            String[] time = timePoints.get(i).split(":");
            minutes[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
        Arrays.sort(minutes);
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            result = Math.min(result, minutes[i] - minutes[i - 1]);
        }
        result = Math.min(result, 1440 - minutes[n - 1] + minutes[0]);
        return result;
    }
    
    public static void main(String[] args) {
        MinimumTimeDifference sol = new MinimumTimeDifference();
        List<String> timePoints = new ArrayList<>();
        timePoints.add("23:59");
        timePoints.add("00:00");
        System.out.println(sol.findMinDifference(timePoints));
    }
}


//Optimal Solution

/*
class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) return 0; 

        boolean[] seen = new boolean[1440]; 
        
        for (String time : timePoints) {
            int minutes = convertToMinutes(time);
            if (seen[minutes]) return 0; 
            seen[minutes] = true;
        }
        
        int first = Integer.MAX_VALUE, prev = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < 1440; i++) {
            if (seen[i]) {
                if (first == Integer.MAX_VALUE) {
                    first = i;
                } else {
                    minDiff = Math.min(minDiff, i - prev);
                }
                prev = i;
            }
        }
        

        minDiff = Math.min(minDiff, 1440 - prev + first);
        
        return minDiff;
    }
    
    private int convertToMinutes(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 
             + (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }
}
//Kartikdevsharmaa
*/