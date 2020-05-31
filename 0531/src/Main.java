import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        return helper(root,root);
    }

    /**
     * 递归方法
     * @param p
     * @param q
     * @return
     */
    public boolean helper(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p ==  null || q == null || p.val != q.val ){
            return false;
        }
        return helper(p.left,q.right) && helper(p.right,q.left);
    }

    /**
     * 迭代大法，其实跟递归一个思路，利用队列去存储元素，存的是左节点的左子树、右节点的右子树、左节点的右子树、右节点的左子树。
     * @param root
     * @return
     */
    public boolean helper(TreeNode root){
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
}
