import java.util.*;

/**
 * @author ZJPang
 * @date 2020/8/23 0023
 */

public class test {
    public static void main(String[] args) {
        //System.out.println(mostVisited(2, new int[]{2,1,2,1,2,1,2,1,2}));
        System.out.println(findLatestStep(new int[]{3,5,1,2,4}, 1));
        HashMap<Integer,Integer> map = new HashMap<>();
    }
    public static List<Integer> mostVisited(int n, int[] rounds) {
        int[] bucket = new int[n+1];
        int last = 0;
        for(int i = 0; i < rounds.length - 1; i++){
            int start = 0;
            if(last == rounds[i]){
                start = (last + 1) % (n + 1);
                if(start == 0){
                    start = 1;
                }
            }else{
                start = rounds[i];
            }
            int end = rounds[i+1];
            while(start != end){
                bucket[start]++;
                start = (start + 1) % (n + 1);
                if(start == 0){
                    start = 1;
                }
            }
            bucket[start]++;
            last = start;
        }
        List<Integer> res = new ArrayList<>();
        int max = 0;
        for(int i = 1; i < bucket.length; i++){
            max = Math.max(max, bucket[i]);
        }
        for(int i = 1; i < bucket.length; i++){
            if(bucket[i] == max){
                res.add(i);
            }
        }
        return res;
    }

    public static int findLatestStep(int[] arr, int m) {
        char[] nums = new char[arr.length];
        Arrays.fill(nums,'0');
        int res = -1;
        for(int i = 0; i < arr.length; i++){
            nums[arr[i]-1] = '1';
            if(helper(nums, m)){
                res = i + 1;
            }
        }
        return res;
    }
    public static boolean helper(char[] nums, int m){
        String str = new String(nums);
        String[] group = str.split("0");
        for(int i = 0; i < group.length; i++){
            if(group[i].length() == m){
                return true;
            }
        }
        return false;
    }
}
