import java.util.ArrayList;
import java.util.List;

public class day0610 {
    public static void main(String[] args) {
        day0610 main = new day0610();
        System.out.println(main.isPalindrome(10));
    }

    /**
     * LC 9.回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        int temp = x;
        List<Integer> list =  new ArrayList<>();
        while(temp != 0){
            list.add(0,temp % 10);
            temp /= 10;
        }
        int left = 0;
        int right = list.size()-1;
        while(left < right){
            if(list.get(left++).intValue() != list.get(right--).intValue()){
                return false;
            }
        }
        return true;
    }
}
