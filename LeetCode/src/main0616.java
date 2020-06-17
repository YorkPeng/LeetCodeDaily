import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;

public class main0616 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    /**
     * LC 297.二叉树的序列化与反序列化
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuffer res = new StringBuffer();
        res = dfs(root,res);
        return res.deleteCharAt(res.length()-1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        String[] strs = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(strs));
        return reverse(list);
    }

    public StringBuffer dfs(TreeNode root, StringBuffer stringBuffer){
        if(root == null){
            stringBuffer.append("#,");
            return stringBuffer;
        }
        stringBuffer.append(root.val);
        stringBuffer = dfs(root.left,stringBuffer);
        stringBuffer = dfs(root.right,stringBuffer);
        return stringBuffer;
    }

    public TreeNode reverse(LinkedList<String> data){
        if(("#").equals(data.getFirst())){
            data.removeFirst();
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data.removeFirst()));
        root.left = reverse(data);
        root.right = reverse(data);
        return root;
    }
}
