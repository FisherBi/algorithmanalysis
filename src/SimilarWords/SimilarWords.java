package SimilarWords;

import java.util.*;

/**
 * Created by fisbii on 16-9-2.
 */
public class SimilarWords<KeyType> {

    /**
     * 给出包含一些单词作为关键字和只在一个字母上不同的一列单词作为关键字的值，输出那些具有
     * minWords个或更多个通过1字母替换得到单词的单词
     * @param adjWords
     * @param minWords
     */
    public static void printHighChangeables(Map<String,List<String>> adjWords, int minWords){
        for(Map.Entry<String,List<String>> entry : adjWords.entrySet()){
            List<String> words = entry.getValue();

            if(words.size() >= minWords){
                System.out.println(entry.getKey() + "(");
                System.out.println(words.size() + "):");
                for(String w : words){
                    System.out.println(" " + w);
                }
                System.out.println();
            }
        }
    }

    /**
     * 检测两个单词是否只在一个字母上不同
     * @param word1
     * @param word2
     * @return
     */
    private static boolean oneCharOff(String word1,String word2){
        if(word1.length() != word2.length()){
            return false;
        }

        int diffs = 0;

        for(int i = 0; i < word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)){
                if(++diffs > 1)
                    return false;
            }
        }
        return diffs == 1;
    }

    /**
     * 计算一个Map对象函数，该对象以一些单词作为关键字而以只在一个字母处不同的一列单词作为关键字的值。
     * 该函数对一个89000单词的词典运行96秒
     * @param theWords
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWords(List<String> theWords){
        Map<String,List<String>> adjWords = new TreeMap<>();

        String[] words = new String[theWords.size()];

        theWords.toArray(words);
        for(int i = 0; i < words.length; i++){
            for(int j = i+1; j < words.length; j++){
                if(oneCharOff(words[i],words[j])){
                    update(adjWords,words[i],words[j]);
                    update(adjWords,words[j],words[i]);
                }
            }
        }
        return adjWords;
    }

    private static <KeyType> void update(Map<KeyType,List<String>> m, KeyType key, String value){
        List<String> lst = m.get(key);
        if(lst == null){
            lst = new ArrayList<>();
            m.put(key,lst);
        }
        lst.add(value);
    }

    /**
     * 计算一个Map对象函数，该对象以一些单词作为关键字而以只在一个字母处不同的一列单词作为关键字的值。
     * 将单词按照长度分组，该函数对一个89000单词的词典运行51秒
     * @param theWords
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWords1(List<String> theWords){
        Map<String,List<String>> adjWords = new TreeMap<>();
        Map<Integer,List<String>> wordsByLength = new TreeMap<>();

        //按长度将单词分组
        for(String w : theWords){
            update(wordsByLength,w.length(),w);
        }

        //将每组单词分开
        for(List<String> groupsWords : wordsByLength.values()){
            String[] words = new String[groupsWords.size()];

            groupsWords.toArray(words);
            for(int i = 0; i < words.length; i++){
                for(int j = i + 1; j < words.length; j++){
                    if(oneCharOff(words[i],words[j])){
                        update(adjWords,words[i],words[j]);
                        update(adjWords,words[j],words[i]);
                    }
                }
            }
        }
        return adjWords;
    }

    public static Map<String,List<String>> computeAdjacentWords2(List<String> words){
        Map<String,List<String>> adjWords = new TreeMap<>();
        Map<Integer,List<String>> wordsByLength = new TreeMap<>();

        //按长度将单词分组
        for(String w : words){
            update(wordsByLength,w.length(),w);
        }
        //将每组单词分开
        for(Map.Entry<Integer,List<String>> entry : wordsByLength.entrySet()){
            List<String> groupsWords = entry.getValue();
            int groupNum = entry.getKey();

            //遍历每组单词个数的位置
            for(int i = 0; i < groupNum; i++){
                //Remove one character in specified position,computing
                //representative. Words with same representative are adjacent,
                //so first populate a map...
                Map<String,List<String>> repToWord = new TreeMap<>();
                for(String str : groupsWords){
                    String rep = str.substring(0,i) + str.substring(i+1);
                    update(repToWord,rep,str);
                }
                // and then look for map values with more than one string
                for(List<String> wordClique : repToWord.values()){
                    if(wordClique.size() >= 2){
                        for(String s1 : wordClique){
                            for(String s2 : wordClique){
                                if(!Objects.equals(s1, s2)){
                                    update(adjWords,s1,s2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return adjWords;
    }
}
