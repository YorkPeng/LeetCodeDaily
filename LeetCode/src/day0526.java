public class day0526 {
    public static void main(String[] args) {
        day0526 day0526 = new day0526();
        System.out.println(day0526.findDuplicate(new int[]{3,1,3,4,2}));
    }

    /**
     * 垃圾算法，不符合题目不能修改原数组的要求
     * @param nums
     * @return
     */
    public int findDuplicateFake(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while ((i + 1) != nums[i]) {
                if (nums[nums[i] - 1] == nums[i]) {
                    return nums[nums[i] - 1];
                }
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        return 0;
    }

    /**
     * 与循环链表查入口相似
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while(true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(slow == fast){
                fast = 0;
                while (slow != fast){
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return fast;
            }
        }
    }
}
