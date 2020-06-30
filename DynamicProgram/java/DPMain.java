import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPMain {
    public static void main(String[] args) {
        DPMain main = new DPMain();
        List<Integer> list1 = new ArrayList<>();
        list1.add(7);
        List<Integer> list2 = new ArrayList<>();
        list2.add(-5);
        list2.add(9);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(2);
        List<Integer> list4 = new ArrayList<>();
        list4.add(-8);
        list4.add(-2);
        list4.add(-7);
        list4.add(3);
        List<Integer> list5 = new ArrayList<>();
        list5.add(-2);
        list5.add(6);
        list5.add(-6);
        list5.add(1);
        list5.add(4);
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(list5);
        System.out.println(main.minimumTotal(list));
    }

    /**
     * LC 300.最长上升子序列
     * 线性DP->经典单串问题
     * 思想就是对于每个元素，如果它前面存在比他小的元素，那么就拿当前dp[i]的值和dp[j]+1进行比大小，取较大值
     * 考虑到不一定是最后一个元素为最长，我们还需要一个res来进行保存dp数组中最大值。
     * 同时因为整个数组最短的上升子序列必须是1，我们需要先往DP数组填充1.
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = i-1; j >= 0; j--){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * LC 1143.最长公共子序列
     * 线性DP->双串问题
     * 题目要求的是自序列而不是字串，我们采用一个二维数组作为DP数组 ，其中第一维作为text1的字符串状态
     * 第二维度作为text2的字符串状态。
     * dp[i][j]代表，text1中[0,i]和text2中[0,j]的最长字序列。
     * 我们数组的长度为其原来字符串长度+1，因为要留出0号下标作为标记位。
     * 如果我们当前两个字符相等，就直接取dp[i-1][j-1]+1，代表我们两个字符串都向左剪枝一位。
     * 否则我们考虑text1剪枝和text2剪枝中的最大值。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[i].length; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * LC 120. 三角形最小路径和
     * 经典DP问题
     * 本方法自顶向下做，我们先定义一个二维数组，然后把二维数组中填充Integer.MAX_VALUE,防止当前层最后一个访问上一层的没有初始化的数据。
     * 对于每一行第一个元素，直接与上一层第一个元素相加，因为它不存在左上角的数据。
     * 其余的取上一行当前列数据和上一行前一列的数据之中的最小值，然后加上当前数值即可。
     * 自顶向下的做可能比较抽象，这个题适合自底向上的做法，同时自底向上还可以压缩数组，把二维变一维。
     * 我们从triangle.size()-1开始遍历，使用同样的思想，最后的值肯定在dp[0][0]。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.get(triangle.size()-1).size();
        int[][] dp = new int[triangle.size()][length];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        dp[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < triangle.size(); i++){
            List<Integer> temp = triangle.get(i);
            dp[i][0] = dp[i-1][0] + temp.get(0);
            for(int j = 1; j < temp.size(); j++){
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + temp.get(j);
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < length; i++){
            res = Math.min(res,dp[triangle.size()-1][i]);
        }
        return res;
    }

    /**
     * LC 53.最大子序和
     * 经典DP问题。
     * 在这里我们采用更加简洁的O(1)空间复杂度的压缩算法。
     * 如果cur（当前和）小于0，我们直接将新的数替换到cur当中。
     * 根据nums[i] > cur + nums[i],如果cur加上当前值之后比当前值还小，我们应该直接抛弃旧值启用新值。
     * 我们每次计算完cur后都需要和max进行比较，注意我们max初始值应该是最小值。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0; i < nums.length; i++){
            if(cur < 0){
                cur = nums[i];
            }else{
                cur += nums[i];
            }
            max = Math.max(cur,max);
        }
        return max;
    }

    /**
     * LC 152. 乘积最大子数组
     * 这个题属于经典DP问题
     * 这个题和53题的不同在于，两个负数的乘积会变成正数，所以我们需要一个二维数组，用于记录本轮记录下的最大值和最小值。
     * 最大值取决于max(前一轮最大值*nums[i], 前一轮最小值*nums[i], nums[i])，这个三个条件都需要进行判断。
     * 最小值取决于min(前一轮最大值*nums[i], 前一轮最小值*nums[i], nums[i])。
     * 然后取最大值和最小值之间的最大值和res进行比较。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int res = dp[0][0];
        for(int i = 1; i < nums.length; i++){
            dp[i][0] = Math.max(dp[i-1][0] * nums[i], Math.max(dp[i-1][1] * nums[i], nums[i]));
            dp[i][1] = Math.min(dp[i-1][1] * nums[i], Math.min(dp[i-1][0] * nums[i], nums[i]));
            res = Math.max(res, Math.max(dp[i][0],dp[i][1]));
        }
        return res;
    }


    /**
     * LC 198.打家劫舍1
     * 这个题要注意nums.length ==0和==1的情况，要单独拿出来进行判断。
     * 然后dp[1]的赋值不仅仅是取决于nums[1]，而是nums[0]和nums[1]之间的最大值。
     * 我们在循环的过程中，dp[i] = max(dp[i-1], dp[i-2]+nums[i])
     * 如果偷了上一家，本家就不能偷。或者偷上上家，并且偷当前这家。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return Math.max(dp[nums.length - 2], dp[nums.length - 1]);
    }

    /**
     * LC 213. 打家劫舍2
     * 这个题其实跟上一题一样的思想，一间一间去检查即可。
     * 这个题不同的是我们第一家和最后一家是连起来的，我们就要把它拆成两次去做。
     * 首先是包含[0,nums.length-2]的家，我们使用上一题的思想即可。
     * 然后我们把dp[nums.length-3]和 dp[nums.length-2]之间的最大值取出来。
     * 接下来是判断[1,nums.length-1]的家，同样的操作。
     * 最后返回新dp数组倒数第一、第二个和上一次判断返回的值中的最大值即可。
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length - 1; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        int res = Math.max(dp[nums.length - 3], dp[nums.length - 2]);
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1],nums[2]);
        for(int i = 3; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return Math.max(res,Math.max(dp[nums.length - 2],dp[nums.length - 1]));
    }
}
