package src.MaximumContiguousSubsequenceSum;

/**
 * Created by FisherBi on 2016/7/30.
 */
public class MaximumContiguousSubsequenceSum {

    /**
     * Cubic maximum contiguous subsequence sum algorithm.
     */
    public static int maxSubSum1(int[] a){
        int maxSum = 0;

        for(int i = 0; i < a.length; i++){
            for(int j = i; j < a.length; j++){
                int thisSum = 0;
                for(int k = i; k <= j; k++){
                    thisSum += a[k];
                }
                if(thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

}
