public class main0615 {
    public static void main(String[] args) {
        main0615 main = new main0615();
        System.out.println(main.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    /**
     * LC 14. 最长公共前缀
     * 我的思想是现在字符串数组中找到最短的那一个。
     * 然后去检查这个字符串数组中的每一个字符串，看看是不是由这个最短的字符串开始的。
     * 当检查到不是的时候，就中断，然后删除掉最后的那个字符。
     * 总体代码比较杂乱.
     * 以下这个代码是简化后的，看起来顺眼很多。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        String temp = strs[0];
        for(int i = 1; i < strs.length; i++){
            while(strs[i].indexOf(temp) != 0){
                temp =  temp.substring(0,temp.length()-1);
            }
        }
        return temp;
    }
}
