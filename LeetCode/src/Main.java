import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.findKthLargest(new int[]{7,6,5,4,3,2,1}, 5));
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

    /**
     * LC 139.单词拆分
     * 传统DP问题
     * 2020年6月25日
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //注意，这里必须要长一位，因为0号位会被作为标志位来使用。
        boolean[] dp = new boolean[s.length()+1];
        //dp[0]其实就是空字符串，空字符串必定在另外一个非空字符串的集合中。
        dp[0] = true;
        //外层的i用于控制当前字符串的右边界，所以会是小于等于s.length()
        for(int i = 1; i <= s.length(); i++){
            //内层的j用于控制左边界，慢慢去收缩这个边界。
            for(int j = 0; j < i; j++){
                //注意！这里判断条件有两个，第一个是当前剪切下来的字符串在字典中是否存在
                //第二个是要检查我们dp[j]就是我们的前一位字符是否被正确匹配，如果这个不成立
                //说明我们可能重复利用了某个字符，这样会导致单词划分有了重复。
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        //返回最后那个位置的值
        return dp[s.length()];
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        backTracking(graph,visited,0,res,temp);
        return res;
    }

    public void backTracking(int[][] graph, boolean[] visited, int index, List<List<Integer>> res, List<Integer> temp){
        if(visited[index]){
            return;
        }
        if(graph[index].length == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        visited[index] = true;
        for(int i = 0; i < graph[index].length; i++){
            temp.add(graph[index][i]);
            backTracking(graph,visited,graph[index][i],res,temp);
            temp.remove(temp.size()-1);
        }
        visited[index] = false;
    }

    /**
     * LC 209.长度最小的子数组
     * 2020年6月28日
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        //防止空测试用例出现
        if(nums.length < 1){
            return 0;
        }
        //使用滑动窗口思想，我们把慢指针调到-1,快指针从0开始
        int slow = -1;
        int fast = 0;
        //记录当前滑动窗口内的和
        int sum = 0;
        //使用一个较大的数来进行初始化res结果
        int res = nums.length+1;
        //当前滑动窗口内的长度
        int cur = 0;
        while(fast < nums.length){
            //累加和并且累加长度
            sum += nums[fast++];
            cur++;
            //当慢指针比快指针小并且滑动窗口内的和大于等于s的时候，开始收缩滑动窗口
            while(slow < fast && sum >= s){
                //获取res和当前长度res中较小的值
                res = Math.min(res,cur);
                //这里由于我们slow从-1开始，要先自增再使用
                sum -= nums[++slow];
                //滑动窗口左边界收缩
                cur--;
            }
        }
        //注意，这里要增加一个判断，否则会出现整个数组都不满足大于等于s的条件而导致的输出错误。
        return res == nums.length+1?0:res;
    }

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] numToChar = new char[]{'0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] str = s.toCharArray();
        for(int i = 0; i < str.length; i++){
            if(str[i] == '#'){
                int num = 0;
                for(int j = 0; j < 2; j++){
                    num = num * 10 + Integer.parseInt(String.valueOf(s.charAt(i-2+j)));
                    sb.deleteCharAt(sb.length()-1);
                }
                sb.append(numToChar[num]);
            }else {
                sb.append(numToChar[str[i] - '0']);
            }
        }
        return sb.toString();
    }

    /**
     * LC 215. 数组中的第K个最大元素
     * 2020-06-29每日一题
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int length = nums.length;
        while(true){
            int cur = helper(nums,left,right);
            if(cur == length - k){
                return nums[cur];
            }else if( cur > length - k){
                right = cur - 1;
            }else{
                left = cur + 1;
            }
        }
    }

    private int helper(int[] nums, int l, int r){
        int left = l;
        int right = r;
        int flag = nums[left++];
        while(left <= right){
            while(left <= right && nums[right] >= flag){
                right--;
            }
            while(left <= right && nums[left] < flag){
                left++;
            }
            if(left >= right){
                break;
            }
            int temp =  nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
        nums[l] = nums[right];
        nums[right] = flag;
        return right;
    }


}
