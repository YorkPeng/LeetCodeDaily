import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class Lc {

      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      }

    public static void main(String[] args) {
        Lc lc_1431 = new Lc();
        System.out.println(lc_1431.maxIncreaseKeepingSkyline(new int[][]{{3,0,8,4}, {2,4,5,7}, {9,2,6,3}, {0,3,1,0}}));
    }

    /**
     * 1431
     * 简单题，两次遍历就过了，不过不知道有没有一次遍历就过的方法。
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> list = new ArrayList<>(candies.length);
        int max = -1;
        for (int candy : candies) {
            max = max > candy ? max : candy;
        }
        for (int i = 0; i < candies.length; i++) {
            list.add(candies[i] + extraCandies >= max);
        }
        return list;
    }

    /**
     * 1379
     * 这个题没啥好说的，题目也是讲的糊里糊涂，一句话就是在cloned树中找到一个值与target相等的节点，然后返回即可。
     * 这里用递归去检查每一个节点的左子树和右子树是否存在即可。
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(cloned == null){
            return null;
        }
        if(original.val == target.val){
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left,cloned.left,target);
        if(left != null){
            return left;
        }
        TreeNode right = getTargetCopy(original.right, cloned.right, target);
        return right;
    }

    /**
     * 771，简单题
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        char[] str = S.toCharArray();
        for(char c: str){
            count += (J.indexOf(c) == -1?0:1);
        }
        return count;
    }

    /**
     * 535 异或大法。这应该是这个题最正常的解法了。当然可以直接将传参返回给题目~~
     * 也可以用map去存储每一个shortUrl和longUrl之间的映射
     */
    private static final int FACTOR = 20000;

    public String encode(String longUrl) {
        char[] chars = longUrl.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ FACTOR);
        }
        return String.valueOf(chars);
    }

    public String decode(String shortUrl) {
        return encode(shortUrl);
    }

    /**
     * 807.
     * 其实这个题也很好理解，就是去找同一行、同一列中最大的值，然后另起一个循环，去找一当前行、列中的最大值的较小值，看这个较小值和当前高度之差。
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int sum = 0;
        int[] cHigh = new int[grid[0].length];
        int[] rHigh = new int[grid.length];
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                rHigh[i] = Math.max(rHigh[i],grid[i][j]);
                cHigh[j] = Math.max(cHigh[j],grid[i][j]);
            }
        }
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                sum += (Math.min(rHigh[i],cHigh[j]) - grid[i][j]);
            }
        }
        return sum;
    }
}
