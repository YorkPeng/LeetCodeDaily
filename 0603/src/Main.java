import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.restoreIpAddresses("25525511135"));
    }

    /**
     * LC 837 今天每日一题我投降了，真的不会数学问题，这个概率把我搞蒙了。
     * https://leetcode-cn.com/problems/new-21-game/solution/dphua-dong-chuang-kou-si-xiang-qiu-jie-java-by-bai/
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        double[]dp=new double[N+1];
        double sum=0;
        dp[0]=1;
        if(K>0) sum+=1;
        for(int i=1;i<=N;i++){
            dp[i]=sum/W;
            if(i<K) sum+=dp[i];
            if(i>=W) sum-=dp[i-W];
        }
        double ans=0;
        for(int i=K;i<=N;i++) ans+=dp[i];
        return ans;
    }

    /**
     * 翻转单词顺序列 剑指Offer
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        char[] strs = str.toCharArray();
        reverse(strs,0,strs.length-1);
        int lastBlank = -1;
        for(int i = 0; i < strs.length; i++){
            if(strs[i] == ' '){
                int curBlank = i;
                reverse(strs,lastBlank+1,curBlank-1);
                lastBlank = curBlank;
            }
        }
        reverse(strs,lastBlank+1,strs.length-1);
        return new String(strs);
    }

    public void reverse(char[] str, int from, int end){
        while(from < end){
            char temp = str[from];
            str[from] = str[end];
            str[end] = temp;
            from++;
            end--;
        }
    }

    /**
     * LC 93
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0){
            return res;
        }
        backTracking(res,s,0,new ArrayList<>());
        return res;
    }

    public void backTracking(List<String> res, String str, int index, List<String> list){
        //如果list中已经有4个元素，就进入判断，但是要成为一个真正的答案还需要它已经遍历到了字符串的结尾。
        if(list.size() == 4){
            if(index == str.length()){
                res.add(String.join(".",list));
            }
            return;
        }

        for(int j = 1; j < 4 ; j++){
            //防止数组越界
            if(j + index > str.length()){
                break;
            }
            String temp = str.substring(index,index+j);
            //判断0开头的IP，以及是大于255的IP
            if((temp.startsWith("0") && temp.length() > 1) || ((Integer.parseInt(temp) > 255) && temp.length() == 3)){
                continue;
            }
            list.add(temp);
            backTracking(res,str,index+j,list);
            list.remove(list.size()-1);
        }
    }
}
