import java.util.*;

public class day0531 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        day0531 day0531 = new day0531();
        System.out.println(day0531.longestOnes(new int[]{0,0,1,1,1,0,0}, 0));
    }

    /**
     * 每日一题 101
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }

    /**
     * 递归方法
     *
     * @param p
     * @param q
     * @return
     */
    public boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return helper(p.left, q.right) && helper(p.right, q.left);
    }

    /**
     * 迭代大法，其实跟递归一个思路，利用队列去存储元素，存的是左节点的左子树、右节点的右子树、左节点的右子树、右节点的左子树。
     *
     * @param root
     * @return
     */
    public boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    /**
     * 654 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //递归的终止条件
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        //寻找数组中最大的那个数，这个数组是乱的 好像没啥规律 只能扫描一次去找了。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        //我们将最大元素设置为当前子树的根节点，然后递归去找左子树和右子树。
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, index));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, index + 1, nums.length));
        return root;
    }

    /**
     * 1282
     *
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        //记录每一个分组中有多少个人
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //答案
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            //使用拉链法解决冲突
            List<Integer> list = map.get(groupSizes[i]);
            //如果是空的，说明这个分组还没有被初始化，这里进行初始化
            if (list == null) {
                list = new ArrayList<>();
                map.put(groupSizes[i], list);
            }
            //将当前下标放入这个拉链中。
            list.add(i);
            //如果当前拉链长度已经到达了这个分组的最大长度，直接加入答案中，并且重新建一个list用于存放下一次的数据。
            if (list.size() == groupSizes[i]) {
                res.add(list);
                list = new ArrayList<>();
                map.put(groupSizes[i], list);
            }
        }
        return res;
    }

    /**
     * 1302
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            res = 0;
            for (int i = 0; i < length; i++) {
                TreeNode temp = queue.poll();
                res += temp.val;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return res;
    }

    /**
     * 1004
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        if (A.length == 0) {
            return 0;
        }
        int fast = 0;
        int slow = 0;
        int max = 0;
        while (fast < A.length) {
            if(A[fast] == 0){
                if(K > 0){
                    K--;
                }else{
                    max = Math.max(max,fast - slow);
                    //这个是个巧妙地地方，A[slow++]==1，我们会访问到当前滑动窗口中第二次出现为0的地方，因为我们判断A[slow] == 1之后，仍然会执行一次slow++
                    while(A[slow++] == 1);
                }
            }
            fast++;
        }
        return Math.max(max,fast-slow);
    }

}

