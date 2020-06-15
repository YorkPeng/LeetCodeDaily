import java.util.*;

public class day0612 {
    public static void main(String[] args) {
        day0612 main = new day0612();
        System.out.println(main.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        int target = - nums[0];
        int i = 0;
        while (i < nums.length){
            int left = i+1;
            int right = nums.length - 1;
            while(left < right){
                if(nums[left] + nums[right] == target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(-target);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    int flag = nums[left++];
                    while(left < right && flag == nums[left]){
                        left++;
                    }
                    flag = nums[right--];
                    while(left < right && nums[right] == flag){
                        right--;
                    }
                }else if(nums[left] + nums[right] < target){
                    int flag = nums[left++];
                    while(left < right && flag == nums[left]){
                        left++;
                    }
                }else if(nums[left] + nums[right] > target){
                    int flag = nums[right--];
                    while(left < right && nums[right] == flag){
                        right--;
                    }
                }
            }
            while (i < nums.length && nums[i] == -target){
                i++;
            }
            target = (i >= nums.length? 0:-nums[i]);
        }
        return res;
    }
}
