public class day0609 {
    /**
     * 每日一题。面试题46. 把数字翻译成字符串。
     * 这个题比LeetCode原题简单，因为0也成为了字符，不用单独拿出来分析
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++){
            String temp = str.substring(i-2, i);
            if(Integer.parseInt(temp) >= 10 && Integer.parseInt(temp) <= 25){
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }
}
