import java.util.HashMap;

public class day0527 {
    public static void main(String[] args) {
        day0527 day0527 = new day0527();
        System.out.println(day0527.subarraysDivByK(new int[]{4,5,0,-2,-3,1},5));
    }

    /**
     * 解法一，使用map存放和对K取模后的数以及出现的次数。
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int temp = 0;
        map.put(0,1);
        for(int i = 0; i < A.length; i++){
            temp += A[i];
            //这个步骤是将负数转换成正数，为什么要这么操作呢？因为小于0的数字，除余之后得到的还是小于0。但是余数为-5 和余数为5其实可以归为一类。
            int curMod = ((temp % K) + K) % K;
            int preMod = map.getOrDefault(curMod,0);
            count += preMod;
            map.put(curMod,map.getOrDefault(curMod,0)+1);
        }
        return count;
    }

    /**
     * 解法2，将map转换成数组，因为这里只会出现K个数，完全可以用数组代替map的工作
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK2(int[] A, int K) {
        int[] map = new int[K];
        ++map[0];
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (a + prefix) % K;
            if (prefix < 0) prefix += K;
            res += map[prefix]++;
        }
        return res;
    }
}
