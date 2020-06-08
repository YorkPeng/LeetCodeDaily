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
        System.out.println(Arrays.toString(main.getLeastNumbers(new int[]{0,0,0,2,0,5}, 0)));
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
}
