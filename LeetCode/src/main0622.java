public class main0622 {
    public static void main(String[] args) {
        main0622 main = new main0622();
        System.out.println(main.patternMatching("abba", "dogcatcatdog"));
    }

    /**
     * 面试题16.18
     * 不会做。。
     * @param pattern
     * @param value
     * @return
     */
    public boolean patternMatching(String pattern, String value) {
        String[] str = new String[2];
        return solve(str, pattern, 0,value,0);
    }

    /**
     * 回溯法我是抄的思想，这个题我也没做出来，回溯法当时想得太多了，反而把整个题搞混乱了。
     * @param s
     * @param pattern
     * @param index1
     * @param value
     * @param index2
     * @return
     */
    public boolean solve(String []s,String pattern,int index1,String value,int index2){
        //匹配完成
        if(index1==pattern.length()&&index2==value.length()) return true;
        //注意匹配串匹配位置等于长度的时候也可以继续匹配，因为模式串的a，b可以匹配空串。
        if(index1>=pattern.length()||index2>value.length()) return false;
        int num=pattern.charAt(index1)-'a';
        if(s[num]==null){
            //从当前尝试a或b对应的字符串的每一种可能
            for(int i=index2;i<=value.length();i++){
                s[num]=value.substring(index2,i);
                //(s[num]==null||s[num^1]==null||!s[num].equals(s[num^1]))  [是指a，b对应的字符串不可相等]
                if((s[num]==null||s[num^1]==null||!s[num].equals(s[num^1]))&&solve(s,pattern,index1+1,value,i)) return true;
            }
            //失配时应将设置过的对应字符串为空
            s[num]=null;
            return false;
        }else{
            //若此前a或b已有对应的字符串匹配了，则查看当前位置时候能够匹配上。
            int end=index2+s[num].length();
            if(end> value.length()||!value.substring(index2,end).equals(s[num])) return false;
            return solve(s,pattern,index1+1,value,end);
        }
    }
}
