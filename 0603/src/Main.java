public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.new21Game(21,17,10));
    }

    /**
     * LC 837 今天每日一题我投降了，真的不会数学问题，这个概率把我搞蒙了。
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
}
