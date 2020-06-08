import com.sun.tools.javac.Main;

import java.util.*;

public class No1 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        No1 main = new No1();
        System.out.println(main.lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * 面试题58-2
     *
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        StringBuffer stringBuffer = new StringBuffer(s.substring(n, s.length()));
        String temp = s.substring(0, n);
        stringBuffer.append(temp);
        return stringBuffer.toString();
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.remove();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 这个题我第一次做是哈希做的，已经是一个月前了，现在第二次做，直接用O(1)的空间复杂度来完成，一开始读题读错了，导致各种提示指向原来的链表的问题。
     * 后来直接重构了代码就OK了。
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Node node = head;
        //复制链表中的节点
        while (node != null) {
            Node temp = new Node(node.val);
            temp.next = node.next;
            node.next = temp;
            node = node.next.next;
        }
        node = head;
        //修改复制节点的random值
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next == null ? null : node.next.next;
        }
        node = head;
        Node dummy = new Node(-1);
        Node cur = dummy;
        //将链表一分为二。
        while (node != null) {
            cur.next = node.next;
            cur = cur.next;
            node.next = cur.next;
            node = node.next;
        }
        return dummy.next;
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int slow = 1;
        int count = 0;
        for(int fast = 1; fast <= target/2 + 1; fast++){
            count += fast;
            if(count > target) {
                while (count > target) {
                    count -= slow;
                    slow++;
                }
            }
            if(count == target){
                int[] temp = new int[fast - slow + 1];
                int index = 0;
                for(int i = slow; i <= fast; i++){
                    temp[index++] = i;
                }
                list.add(temp);
            }
        }
        int[][] res = new int[list.size()][];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public char firstUniqChar(String s) {
        char res = ' ';
        for(int i = 0; i < s.length(); i++){
            if(s.indexOf(s.charAt(i)) == i && s.lastIndexOf(s.charAt(i),i+1) == -1){
                res = s.charAt(i);
                break;
            }
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null){
            return res;
        }
        queue.add(root);
        while(!queue.isEmpty()){
            int length = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < length; i++){
                TreeNode node = queue.remove();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                if((res.size() & 1) == 0){
                    temp.add(node.val);
                }else{
                    temp.add(0,node.val);
                }
            }
            res.add(temp);
        }
        return res;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int left = 0;
        int right = arr.length-1;
        while(true){
            int index = findKth(arr,left,right);
            if(index == k-1){
                break;
            }else if(index > k-1){
                right = index-1;
            }else{
                left = index + 1;
            }
        }
        return Arrays.copyOfRange(arr,0,k);
    }

    public int findKth(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int target = arr[left];
        while(l < r){
            while(l < r && arr[r] > target){
                r--;
            }
            while (l < r && arr[l] <= target){
                l++;
            }
            if(l >= r){
                break;
            }
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
        }
        arr[left] = arr[r];
        arr[r] = target;
        return r;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        List<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int value : pushed) {
            stack.add(value);
            while (!stack.isEmpty() && stack.get(stack.size() - 1) == popped[j]) {
                stack.remove(stack.size() - 1);
                j++;
            }
        }
        return stack.isEmpty();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        List<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        backTracking(res,root,temp ,sum-root.val);
        return res;

    }
    public void backTracking(List<List<Integer>> res, TreeNode root, List<Integer> temp, int sum){
        if(root == null){
            return;
        }
        if(sum == 0){
            if(root.left == null && root.right == null) {
                res.add(new ArrayList<>(temp));
                return;
            }
        }
        if(root.left != null) {
            temp.add(root.left.val);
            backTracking(res, root.left, temp, sum - root.left.val);
            temp.remove(temp.size()-1);
        }
        if(root.right != null) {
            temp.add(root.right.val);
            backTracking(res, root.right, temp, sum - root.right.val);
            temp.remove(temp.size() - 1);
        }
    }

    public String[] permutation(String s) {
        Set<String> res = new HashSet<>();
        char[] str = s.toCharArray();
        backTracking2(res,str,new StringBuffer(), new boolean[s.length()]);
        String[] temp = new String[res.size()];
        res.toArray(temp);
        return temp;
    }

    public void backTracking2(Set<String> res, char[] str, StringBuffer stringBuffer, boolean[] visited){
        if(stringBuffer.length() == str.length){
            res.add(new String(stringBuffer));
            return;
        }
        for(int i = 0; i < str.length; i++){
            if(!visited[i]){
                stringBuffer.append(str[i]);
                visited[i] = true;
                backTracking2(res,str,stringBuffer,visited);
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
                visited[i] = false;
            }
        }
    }

    public int translateNum(int num) {
        //动态规划题，这个做的不好。
        //首先先把数字转成字符串，看看有多少位
        String str = String.valueOf(num);
        int[] dp = new int[str.length()+1];
        //dp[1]的值绝对就是1，因为它存在0-9之间，所以绝对是为1.
        //dp[0]这个值比较难搞，主要是这个铺垫，我是看了大佬的题解才懂的，是一个反推法得出的结论.
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++){
            //每次都从字符串中裁剪两位出来
            String temp = str.substring(i-2,i);
            //如果它大于等于10并且小于等于25的话，它的翻译方法就有dp[i-2]+dp[i-1]个方法，因为他可以单独翻译一位或者两位。
            if(Integer.parseInt(temp) >= 10 && Integer.parseInt(temp) <= 25){
                dp[i] = dp[i-2]+dp[i-1];
            }else{
                //否则，它可能是以0开头的，或者大于25的。它们的共同点都是只能翻译最后一位的数字，所以只能等于dp[i-1]
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer res = dfs(root,new StringBuffer());
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str = data.split(",");
        LinkedList<String> temp = new LinkedList<>(Arrays.asList(str));
        TreeNode root = dfs2(temp);
        return root;
    }

    public StringBuffer dfs(TreeNode root, StringBuffer stringBuffer){
        if(root == null){
            stringBuffer.append("#,");
            return stringBuffer;
        }
        stringBuffer.append(root.val);
        stringBuffer.append(",");
        StringBuffer sb = dfs(root.left,stringBuffer);
        StringBuffer sb2 = dfs(root.right,sb);
        return sb2;
    }

    public TreeNode dfs2(LinkedList<String> data){
        if("#".equals(data.get(0))){
            data.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data.remove(0)));
        root.left = dfs2(data);
        root.right = dfs2(data);
        return root;
    }

    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length == 0){
            return true;
        }
        int root = postorder[postorder.length-1];
        int left = 0;
        while(left < postorder.length-1){
            if(postorder[left] > root){
                break;
            }
            left++;
        }
        for(int i = left; i < postorder.length-1; i++){
            if(postorder[i] < root){
                return false;
            }
        }
        return verifyPostorder(Arrays.copyOfRange(postorder,0,left)) && verifyPostorder(Arrays.copyOfRange(postorder,left,postorder.length-1));
    }

    /**
     * 面试题13. 机器人的运动范围
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        return backTracking(m,n,0,0,k, new boolean[m][n]);
    }

    public int backTracking(int m, int n, int i, int j, int k, boolean[][] visited){
        if(i < 0 || j < 0 || i >= m || j >= n || visited[i][j]){
            return 0;
        }
        int count = 0;
        int a = i;
        int b = j;
        while(a != 0){
            count += a % 10;
            a /= 10;
        }
        while(b != 0){
            count += b % 10;
            b /= 10;
        }
        if (count > k){
            return 0;
        }
        int res = 1;
        visited[i][j] = true;
        res += backTracking(m,n,i,j-1,k,visited);
        res += backTracking(m,n,i,j+1,k,visited);
        res += backTracking(m,n,i-1,j,k,visited);
        res += backTracking(m,n,i+1, j,  k,visited);
        return res;
    }

    /**
     * 面试题48. 最长不含重复字符的子字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int slow = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        //这次做的时候忘记迭代器的下标是i了，尴尬。
        for(int i = 0; i < s.length(); i++){
            //维护一个滑动窗口，如果遇到重复元素，慢指针直接跳到重复元素的下一个下标
            if(map.containsKey(s.charAt(i))){
                slow = Math.max(map.get(s.charAt(i)) + 1, slow);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max, i - slow + 1);
        }
        return max;
    }

    public String reverseWords(String s) {
        s = s.trim();
        StringBuffer sb = new StringBuffer(s);
        sb = sb.reverse();
        String[] str = sb.toString().split(" ");
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < str.length; i++) {
            if("".equals(str[i])){
                continue;
            }
            res.append(new StringBuffer(str[i]).reverse().toString());
        }
        return res.toString();
    }
}
