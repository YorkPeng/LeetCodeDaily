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
        System.out.println(Arrays.deepToString(main.findContinuousSequence(9)));
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
}
