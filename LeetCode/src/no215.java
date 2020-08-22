import java.util.Random;

/**
 * @author ZJPang
 * @date 2020/8/22 0022
 */

public class no215 {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
    public static int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int p = partition(nums, left, right);
            if(p == target){
                return nums[p];
            }else if(p > target){
                right = p - 1;
            }else if(p < target){
                left = p + 1;
            }
        }
        return 0;
    }

    private static int partition(int[] nums, int left, int right){
        //加入随机选择元素以提高效率
        Random random = new Random();
        int pivot = random.nextInt(right - left + 1) + left;
        int mid = nums[pivot];
        nums[pivot] = nums[left];
        nums[left] = mid;
        int flag = nums[left];
        int l = left + 1;
        int r = right;
        while(l <= r){
            while(r >= left + 1 && nums[r] >= flag){
                r--;
            }
            while(l <= right && nums[l] <= flag){
                l++;
            }
            if(l > r){
                break;
            }
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
        nums[left] = nums[r];
        nums[r] = flag;
        return r;
    }
}
