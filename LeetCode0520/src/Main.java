import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.findTheLongestSubstring("eleetminicoworoep"));
    }

    /**
     * 使用到了前缀和的思想，还有状态压缩（位数组，与二进制位让老鼠找毒药类似）
     * 前缀和可以参考LeetCode 560题，当时我也是做不出来的，这次这题还是没做出来 我佛了
     * 520的每日一题真的是“我爱你”
     * @param s
     * @return
     */

    public int findTheLongestSubstring(String s) {
        char[] vol = new char[]{'a','e','i','o','u'};
        int[] list = new int[32];
        Arrays.fill(list,Integer.MAX_VALUE);
        list[0] = -1;
        int max = 0;
        int state = 0;
        for(int i = 0;i < s.length(); i++){
            for(int j = 0; j < vol.length; j++){
                if(s.charAt(i) == vol[j]){
                    state ^= 1<<j;
                }
            }
            if(list[state] == Integer.MAX_VALUE){
                list[state] = i;
            }
            max = Math.max(max,i-list[state]);
        }
        return max;
    }


}
