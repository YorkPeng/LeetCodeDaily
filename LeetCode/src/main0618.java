public class main0618 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        main0618 main = new main0618();
        System.out.println(main.recoverFromPreorder("1-2-5"));
    }

    /**
     * 说实话代码写的挺烂的，不过AC了
     * @param S
     * @return
     */
    public TreeNode recoverFromPreorder(String S) {
        //处理空字符串
        if(S.length() == 0){
            return null;
        }
        return helper(S,1);
    }

    public TreeNode helper(String str, int level){
        //如果字符串为空，说明这边已经不存在节点了，直接返回空
        if(str.length() == 0){
            return null;
        }
        int j = 0;
        int num =  0;
        //处理根节点，因为根据题目的描述，我们的值可能不止一位，所以要逐个计算第一个数的数值
        while(j < str.length() && str.charAt(j) != '-'){
            num = num * 10 + Integer.parseInt(String.valueOf(str.charAt(j)));
            j++;
        }
        //创建根节点
        TreeNode root = new TreeNode(num);
        //这个是关键，我们定义一个指针，它的值为第一个数字结束的下标加当前的层数+1
        //例如 1-2-5 我们第一个数字结束的下标是0当前层数为1,所以我们开始遍历的下标应该为0+1+1。
        int i = j+level;
        //我们采用分治法思想，每次都把字符串分成左子树和右子树来进行处理。
        String left = "";
        String right = "";
        int count = 0;
        //记录左边界
        int lastIndex = i;
        while(i < str.length() - 1){
            //统计-出现的次数，当-出现的次数与层数相等，并且接下来的字符不是-，说明我们已经找到了右子树开始的下标
            if(str.charAt(i) == '-'){
                count++;
            }else{
                count = 0;
            }
            //在这里进行切割，要注意substring是左闭右开的
            if(count == level && str.charAt(i+1) != '-'){
                left = str.substring(lastIndex,i-level+1);
                right = str.substring(i+1);
                break;
            }
            i++;
        }
        //因为题目说明优先建立左子树，如果我们遍历整棵树都找不到右子树，说明剩余的字符串都是左子树
        //这里要注意还需要判断lastIndex是否小于字符串长度，否则会有字符串越界问题
        if(i >= str.length() - 1 && lastIndex < str.length()){
            left = str.substring(lastIndex);
        }
        //递归建立左子树和右子树
        root.left = helper(left,level+1);
        root.right = helper(right,level+1);
        //返回根节点
        return root;
    }
}
