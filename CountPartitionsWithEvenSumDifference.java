public class CountPartitionsWithEvenSumDifference {
    public int countPartitions(int[] nums) {

        int total = 0;
        for (int num : nums)
            total += num;

        int leftSum = 0;
        int count = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            int rightSum = total - leftSum;

            if ((leftSum % 2) == (rightSum % 2)) {
                count++;
            }
        }
        return count;
    }
    //     int partition = 0;
    //     int n = nums.length;

    //     for(int i = 0; i < n - 1; i++){
    //         int sumLeft = 0;
    //         int sumRight = 0;

    //         for(int left = 0; left <= i; left++){
    //             sumLeft += nums[left];
    //         }

    //         for(int right = i+1 ; right <= n - 1; right++){
    //             sumRight += nums[right];
    //         }

    //         int difference = sumLeft - sumRight;

    //         if(difference % 2 == 0){
    //             partition++;
    //         }

    //     }
    //     return partition;
    // }
}
