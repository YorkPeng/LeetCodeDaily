import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class day0607 {
    public static void main(String[] args) {
        day0607 main = new day0607();
    }


    /**
     * LC 126.这个题今天又没做出来，我服了，第一次提交答案错误，第二次提交就TLE了。我太难了
     * https://leetcode-cn.com/problems/word-ladder-ii/solution/dan-ci-jie-long-ii-by-leetcode-solution/
     * LC官方的题解其实挺好的，我知道为什么我的算法会TLE了，主要的原因就是做了太多的重复运算的事情555
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        return res;
    }
}
