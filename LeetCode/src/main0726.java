import java.util.ArrayList;
import java.util.List;

/**
 * @author ZJPang
 * @date 2020/7/26 0026
 */

public class main0726 {
    public static void main(String[] args) {
        System.out.println(getHappyString(10, 100));
    }
    public static String getHappyString(int n, int k) {
        char[] words = new char[3];
        words[0] = 'a';
        words[1] = 'b';
        words[2] = 'c';
        List<String> temp = new ArrayList<>();
        backTracking(temp, new ArrayList<>(), words, n);
        if(temp.size() < k){
            return "";
        }
        return temp.get(k-1);
    }

    static void backTracking(List<String> list, List<String> temp, char[] words, int n){
        if(temp.size()  == n){
            list.add(String.join("", temp));
            return;
        }
        for (char word : words) {
            if (temp.size() == 0 || !temp.get(temp.size() - 1).equals(String.valueOf(word))) {
                temp.add(String.valueOf(word));
                backTracking(list, temp, words, n);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
