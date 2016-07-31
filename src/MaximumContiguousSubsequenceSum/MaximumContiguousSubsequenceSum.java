package src.MaximumContiguousSubsequenceSum;

/**
 * Created by FisherBi on 2016/7/30.
 */
public class MaximumContiguousSubsequenceSum {

    /**
     * Cubic maximum contiguous subsequence sum algorithm.
     * O(N^3)
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

    /**
     * Quadratic maximum contiguous subsequence sum algorithm.
     * O(N^2)
     */
    public static int maxSubSum2(int[] a){
        int maxSum = 0;
        for(int i = 0; i < a.length; i++){
            int thisSum = 0;
            for(int j = i; j < a.length; j++){
                thisSum += a[j];
                if(maxSum < thisSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

    /**
     * Recursive maximum contiguous subsequence sum algorithm.
     * 分治法
     * O(NlogN)
     */
    private static int maxSumRec(int[] a, int left, int right){
        if(left == right){
            if(a[left] > 0)
                return a[left];
            else{
                return 0;
            }
        }
        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center+1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for(int i = center; i >= left; i--){
            leftBorderSum  += a[i];
            if(leftBorderSum > maxLeftBorderSum){
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for(int i = center+1; i <= right; i++){
            rightBorderSum += a[i];
            if(rightBorderSum  > maxLeftBorderSum){
                maxLeftBorderSum = rightBorderSum;
            }
        }
        return Math.max(Math.max(maxLeftSum,maxRightSum),maxLeftBorderSum + maxRightBorderSum);
    }

    public static int maxSubSum3(int[] a){
        return maxSumRec(a, 0, a.length-1);
    }

}
