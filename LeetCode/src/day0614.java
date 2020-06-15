import java.util.Arrays;

public class day0614 {
    public static void main(String[] args) {
        day0614 main = new day0614();
        System.out.println(main.findBestValue(new int[]{4,9,3}, 10));
    }

    public int findBestValue(int[] arr, int target) {
        //先进行排序，取得搜索的边界，边界就是0到数组元素中的最大值
        Arrays.sort(arr);
        //这个前缀和数组还是非常重要的，这个长度一定要多一，否则就会像之前做的一样搜出来的值不对。
        int[] prefixSum = new int[arr.length + 1];
        //计算前缀和数组
        for (int i = 1; i <= arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }
        //记录当前与目标值最短的距离
        int minDistance = target;
        //记录答案
        int ans = 0;
        for (int i = 0; i <= arr[arr.length - 1]; i++) {
            //首先二分搜索我们取的值在arr数组中的位置。
            int cur = helper(arr, i);
            //利用前缀和数组计算和。
            // 比如说我们有2，3，5 我们取3，题目的意思是大于3的都要变成3，我们上面取的值就是1，那么我们这里就是取前缀和数组下标为1的值
            //当然这个会有偏移，实际上是原来的0号数据。然后加上(3-1) * 3，这个的意思是我们数组中的3，5都要变成3。
            int sum = prefixSum[cur] + (arr.length - cur) * i;
            //判断距离差的大小。
            if (Math.abs(target - sum) < minDistance) {
                ans = i;
                minDistance = Math.abs(target - sum);
            }
        }
        return ans;
    }

    //二分查找模板，很容易
    public int helper(int[] arr,  int target){
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(arr[mid] >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
}
