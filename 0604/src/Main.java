import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 5));
    }

    /**
     * LC 238 其实挺好理解的题，从左边和右边各扫描一次
     * 第一次将[0,i-1]的乘积给到res[i]，第二次将[i+1,nums.length]的乘积给到res[i]
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if(nums.length == 0){
            return res;
        }
        int temp = 1;
        for(int i = 0; i < nums.length; i++){
            res[i] = temp;
            temp *= nums[i];
        }
        temp = 1;
        for(int i = nums.length-1; i >= 0; i--){
            res[i] *= temp;
            temp *= nums[i];
        }
        return res;
    }

    /**
     * LC 645
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++){
            while(nums[i] != i+1){
                int temp = nums[i];
                if(nums[temp - 1] == temp){
                    res[0] = temp;
                    break;
                }
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1){
                res[1] = i+1;
            }
        }
        return res;
    }

    /**
     * LC 448
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            while(nums[i] != i+1){
                int temp = nums[i];
                if(nums[temp - 1] == temp){
                    break;
                }
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++){
            if(i+1 != nums[i]){
                res.add(i+1);
            }
        }
        return res;
    }

    /**
     * LC 875.这个题一眼真的看不出来是二分查找，事实上，这个珂珂吃香蕉的速度会从1到MAX(piles)中取一个值。
     * 我们在这个范围之间找到最小的那一个可以吃完所有香蕉的速度就可以了。
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int right = -1;
        for(int pile:piles){
            if(pile > right){
                right = pile;
            }
        }
        int left = 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(canFinish(piles,mid,H)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return right;
    }

    public boolean canFinish(int[] piles,int speed, int H){
        int sumTime = 0;
        for(int pile: piles){
            sumTime += Math.ceil((1.0 * pile) / speed);
        }
        return sumTime <= H;
    }

    /**
     * LC 1011
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int right = 0;
        for(int weight: weights){
            right += weight;
        }
        int left = 0;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(canShip(weights, mid, D)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }

    public boolean canShip(int[] weights, int capacity, int D){
        int totalDays = 0;
        int sum = 0;
        for(int i = 0; i < weights.length; i++){
            sum += weights[i];
            if(sum > capacity){
                totalDays++;
                sum = weights[i];
                if(sum > capacity){
                    return false;
                }
            }
        }
        if(sum > 0){
            totalDays++;
        }
        return totalDays < D;
    }

    /**
     * LC 72
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1+1][length2+1];
        for(int i = 0; i <= length1; i++){
            dp[i][0] = i;
        }
        for(int i = 0; i <= length2; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= length1; i++){
            for (int j = 1; j <= length2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //dp[i-1][j]就是对word1的删除操作，dp[i][j-1]就是对word1的增加操作，dp[i-1][-1]就是进行替换操作
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1])) +1;
                }
            }
        }
        return dp[length1][length2];
    }
}
