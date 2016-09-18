package KMP;

/**
 * Created by fisbii on 16-9-18.
 */
public class KMP {

    /**
     *已知前一步计算时最大相同的前后缀长度为k（k>0），即T[0]···T[k-1]；
     * 此时比较第k项T[k]与T[i],如果T[k]等于T[i]，那么很简单跳出while循环;
     * 如果不等呢？？？那么我们应该利用已经得到的next[0]···next[k-1]来求T[0]···T[k-1]这个子串中最大相同前后缀，
     * 可能有同学要问了——为什么要求T[0]···T[k-1]的最大相同前后缀呢？？？是啊！为什么呢？ 原因在于T[k]已经和T[i]失配了，
     * 而且T[i-k] ··· T[i-1]又与T[0] ···T[k-1]相同，看来T[0]···T[k-1]这么长的子串是用不了了，
     * 那么我要找个同样也是T[0]打头、T[k-1]结尾的子串即T[0]···T[j-1](j==next[k-1])，看看它的下一项T[j]是否能和T[i]匹配。
     * @param T
     * @param next
     */
    public static void getNext(String T,int[] next){
        int i;  //数组T下标
        int k;  //最大前后缀长度
        next[0] = 0;
        for(i = 1,k=0; i < T.length(); i++){  //循环从第二个字符开始，依次计算每个字符对应的next值
            while(k > 0 && T.charAt(i) != T.charAt(k))
                k = next[k-1];
            if(T.charAt(i) == T.charAt(k)){
                k++;
            }
            next[i] = k;
        }
    }

    public static int kmp(String S,String T){
        int[] next = new int[T.length()];
        getNext(T,next);
        for(int i = 0, k = 0; i < S.length(); i++){
            while(k > 0 && S.charAt(i) != T.charAt(k))
                k = next[k-1];
            if(S.charAt(i) == T.charAt(k)){
                k++;
            }
            if(k == T.length()){
                return i-k+1;
            }
        }
        return -1;
    }

    public static void main(String args[]){
        String S = "ababxbababcadfdsss";
        String T = "ds";
        int result = kmp(S,T);
        if(result != -1){
            System.out.println("匹配串T能匹配主串S，从主串S下标为"+ result +"的字符开始");
        }else{
            System.out.println("两串不匹配");
        }
    }
}
