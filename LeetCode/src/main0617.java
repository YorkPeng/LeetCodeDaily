import java.util.HashMap;
import java.util.LinkedList;

public class main0617 {
    public static void main(String[] args) {
        main0617 main = new main0617();
        System.out.println(main.maxScoreSightseeingPair2(new int[]{7,8,8,10}));
    }

    /**
     * LC 1014. 最佳观光组合。
     * 解法一：暴力法，超时
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair1(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if ((A[i] + A[j] + i - j) > max) {
                    max = A[i] + A[j] + i - j;
                }
            }
        }
        return max;
    }

    public int maxScoreSightseeingPair2(int[] A) {
        int res = 0;
        int temp = A[0];
        int lastIndex = 0;
        for (int i = 1; i < A.length; i++) {
            res = Math.max(temp + A[i] + lastIndex - i, res);
            if ((Math.abs(temp - A[i]) <= (i - lastIndex) || temp <= A[i])) {
                temp = A[i];
                lastIndex = i;
            }
        }
        return res;
    }

    public int maxScoreSightseeingPair(int[] A) {
        int res = 0;
        int temp = A[0];
        for (int i = 1; i < A.length; i++) {
            res = Math.max(temp + A[i] - i, res);
            //如果两个景点评分之差比距离之差还要小，就要将上一个选取的景点移动到当前景点上。
            //同时遇到大于等于评分的景点也要往前移动，我们需要取得是最大值。
            temp = Math.max(temp, A[i] + i);
        }
        return res;
    }
}
