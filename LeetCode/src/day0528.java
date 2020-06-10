import java.util.LinkedList;

public class day0528 {
    public static void main(String[] args) {
        day0528 day0528 = new day0528();
        System.out.println(day0528.decodeString("2[abc]3[cd]ef"));
    }

    /**
     * 执行用时 :
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 90.09%
     * 的用户
     * 内存消耗 :
     * 37.8 MB
     * , 在所有 Java 提交中击败了
     * 7.69%
     * 的用户
     * @param s
     * @return
     */
    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int num = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //遇到数字就将它累加起来，防止出现个位数以上的数字。同时将统计字符串的sb强制写入栈中刷新缓存。
            if(c >= '0'&& c <= '9'){
                if(sb.length() != 0){
                    stack.add(new String(sb));
                    sb = new StringBuilder();
                }
                num  = num * 10 + (c - '0');
                //遇到小写或者大写字母就直接统计
            }else if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                sb.append(c);
                //遇到[，首先先判断是否有数字生成，如果有就强制将它写入栈中然后刷新。然后再把自己写入到栈中
            }else if(c == '['){
                if(num != 0){
                    stack.add(String.valueOf(num));
                    num = 0;
                }
                stack.add(String.valueOf(c));
            }else if (c == ']'){
                //头插法直到遇到[，表示这个阶段需要重复的字符串已经寻找完毕
                while(!"[".equals(stack.getLast())){
                    sb.insert(0,stack.removeLast());
                }
                String word = new String(sb);
                sb = new StringBuilder();
                //移除[
                stack.removeLast();
                //获得重复次数
                int times = Integer.parseInt(stack.removeLast());
                //将word重复times次
                stack.add(word.repeat(times));
            }
        }
        //使用头插法将栈中元素拿回出来，利用sb可以防止最后出现只重复一次的字符，例如示例3
        while(!stack.isEmpty()){
            sb.insert(0,stack.removeLast());
        }
        return sb.toString();
    }
}
