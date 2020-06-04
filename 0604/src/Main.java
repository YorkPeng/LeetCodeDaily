import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(Arrays.toString(main.productExceptSelf(new int[]{1, 2, 3, 4})));
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
}
