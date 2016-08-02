package EuclideanAlgorithm;

/**
 * 欧几里得算法
 * Created by fisbii on 16-8-1.
 */
public class EuclideanAlgorithm {
    /**
     * 计算两个数的最大公约数
     * O(logN)
     */
    public long gcd(long m,long n){
        while(n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
