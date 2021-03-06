package BinarySearch;

import java.util.Comparator;

/**
 * Created by fisbii on 16-8-1.
 */
public class  BinarySearch {
    public static <T extends Comparable<? super T>> int binarySearch(T[] a, T x){
        int low = 0, high = a.length - 1;

        while(low <= high){
            int mid = (low + high) / 2;

            if(a[mid].compareTo(x) < 0){
                low = mid + 1;
            }else if(a[mid].compareTo(x) > 0){
                high = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
