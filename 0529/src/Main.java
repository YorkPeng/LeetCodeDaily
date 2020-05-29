public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.rob(new int[]{1,2,3,1}));
    }

    /**
     * 打家劫舍问题，这个是这类问题中最简单的一题
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        //处理特殊情况，避免下面初始化dp数组的时候引起越界问题
        if(length == 0){
            return 0;
        }else if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        //对于第一家，只有打劫才是最好的情况，所以直接加上第一家的钱。
        dp[0] = nums[0];
        //对于第二家，我们要看第一家和第二家那家的钱最多 ，取最多的那家打劫。
        dp[1] = Math.max(dp[0],nums[1]);
        for(int i = 2; i < length; i++){
            //对于第i家，因为不能连续打劫，所以要取max(打劫第i-1家，打劫第i-2家+第i家）的值。
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        }
        //因为打劫完钱最多的肯定在最后一家或者倒数第二家，所以返回其较大值。
        return Math.max(dp[length-1],dp[length-2]);
    }
}
