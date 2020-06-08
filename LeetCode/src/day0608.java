import java.util.Arrays;

public class day0608 {
    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{"a==b","b!=c","c==a"}));
    }

    /**
     * LC 990。 能把复杂问题简单化的就不要把复杂问题再复杂化！
     * 直接将判别等号和不等号分开两个循环做，时间效率依然是O(26n)，做到一个循环里反而害死自己！
     * @param equations
     * @return
     */
    public static boolean equationsPossible(String[] equations) {
        int[] equal = new int[26];
        for(int i = 0; i < equal.length; i++){
            equal[i] = i;
        }
        for(String equation: equations) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '=') {
                int group = equal[a - 'a'];
                for (int i = 0; i < equal.length; i++) {
                    if (equal[i] == group) {
                        equal[i] = equal[b - 'a'];
                    }
                }
            }
        }
        for (String equation: equations){
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if(equation.charAt(1) == '!'){
                if(equal[a - 'a'] == equal[b - 'a']){
                    return false;
                }
            }
        }
        return true;
    }
}
