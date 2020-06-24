import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
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
}
