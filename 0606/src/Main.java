import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * LC 128。并查集思想。
     * https://www.cnblogs.com/noKing/p/8018609.html
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = num - 1;
            int right = num + 1;
            //这里其实就是把并查集中二维数组的第二维的分组号变成了它连续序列的长度。
            int leftCount = map.getOrDefault(left, 0);
            //右手边
            int rightCount = map.getOrDefault(right, 0);
            int currentCount = leftCount + rightCount + 1;
            max = Math.max(currentCount, max);
            //我们需要将这个范围内的数字归纳到同一个分组中，分组用分组的长度来表示。
            //正常的话我们需要将这个count的范围内的数据全部都改成相同的分组，但是我们这里只维护了最远的边界的值。为什么呢？
            //假如说我们现在有 123 567这两个分组，我们插入4，插入之后我们1号和7号的长度就会被修改成7。那么下次我们能得到的最大长度会在什么地方出现呢？
            //只有插入0或者8这两个数字，因为我们已经排除了出现重复数字的情况了。所以我们插入0的时候，我们读取的是1号的长度，修改的是7号的长度
            //这时候插入仍然还是在-1和8号位可以达到最大的长度。
            map.put(num, currentCount);
            map.put(num - leftCount, currentCount);
            map.put(num + rightCount, currentCount);
        }
        return max;
    }

    /**
     * LC 829。等差数列问题。数学问题不会做。
     *
     * @param N
     * @return
     */
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        // TODO: 2020/6/6 0006  要找个时间做完这个题。
        return res;
    }

    /**
     * LC 105
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        for(int i = 0; i < inorder.length; i++){
            if(preorder[0] == inorder[i]){
                index = i;
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(preorder,1,index+1),Arrays.copyOfRange(inorder,0,index+1));
        root.right = buildTree(Arrays.copyOfRange(preorder,index+1,preorder.length),Arrays.copyOfRange(inorder,index+1,inorder.length));
        return root;
    }
}
