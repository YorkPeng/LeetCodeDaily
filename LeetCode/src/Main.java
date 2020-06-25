import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    /**
     * LC 67. 二进制求和
     * 2020年6月23日 10:24:25
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        char[] str_a = a.toCharArray();
        char[] str_b = b.toCharArray();
        int i = str_a.length-1;
        int j = str_b.length-1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 && j >= 0){
            sb.insert(0, (str_a[i] - '0') ^ (str_b[j] - '0') ^ count);
            count = ((str_a[i] - '0') & (str_b[j] - '0')) | ((str_a[i] - '0' & count) | (str_b[j] - '0' & count));
            i--;
            j--;
        }
        while(i >= 0){
            sb.insert(0,(str_a[i] - '0') ^ count);
            count = (str_a[i] & count);
            i--;
        }
        while(j >= 0){
            sb.insert(0,(str_b[j] - '0') ^ count);
            count &= (str_b[j] - '0');
            j--;
        }
        sb.insert(0,count);
        int index = 0;
        while(index < sb.length()-1 && sb.charAt(index) == '0'){
            index++;
        }
        return sb.toString().substring(index);
    }

    /**
     * LC 16. 最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int index = 0;
        int res = Integer.MAX_VALUE;
        while(index < nums.length - 2){
            int left = index + 1;
            int right = nums.length - 1;
            while(left < right){
                int temp = nums[left] + nums[right] + nums[index];
                if(res == Integer.MAX_VALUE || (Math.abs(temp - target) < Math.abs(res - target))){
                    res = temp;
                }
                if(temp == target){
                    return target;
                }
                else if(temp > target){
                    int curVal = nums[right];
                    while(right > left && nums[right] == curVal){
                        right--;
                    }
                }else{
                    int curVal = nums[left];
                    while ((left < right && nums[left] == curVal)){
                        left++;
                    }
                }
            }
            int curVal = nums[index];
            while(index < nums.length-2 && nums[index] == curVal){
                index++;
            }
        }
        return res;
    }

    /**
     * LC 139.单词拆分
     * 传统DP问题
     * 2020年6月25日
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //注意，这里必须要长一位，因为0号位会被作为标志位来使用。
        boolean[] dp = new boolean[s.length()+1];
        //dp[0]其实就是空字符串，空字符串必定在另外一个非空字符串的集合中。
        dp[0] = true;
        //外层的i用于控制当前字符串的右边界，所以会是小于等于s.length()
        for(int i = 1; i <= s.length(); i++){
            //内层的j用于控制左边界，慢慢去收缩这个边界。
            for(int j = 0; j < i; j++){
                //注意！这里判断条件有两个，第一个是当前剪切下来的字符串在字典中是否存在
                //第二个是要检查我们dp[j]就是我们的前一位字符是否被正确匹配，如果这个不成立
                //本次的收缩边界就有一个地方留下了间隙，不予执行。
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        //返回最后那个位置的值
        return dp[s.length()];
    }
}
