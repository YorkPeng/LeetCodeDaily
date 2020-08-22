/**
 * @author ZJPang
 * @date 2020/8/13 0013
 */

public class no43 {
    public static void main(String[] args) {
        System.out.println(multiply("9", "9"));
    }
    public static String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        if((n == 1 && num1.equals("0")) || (m == 1 && num2.equals("0") )){
            return "0";
        }
        int[] temp = new int[n + m];
        char[] str1 = num1.toCharArray();
        char[] str2 = num2.toCharArray();
        int count = 0;
        int i = m - 1;
        while(i >= 0){
            int j  = n - 1;
            while(j >= 0){
                int index = temp.length - (n - j - 1) - (m - i - 1) - 1;
                int last = temp[index];
                int cur = (str1[j] - '0') * (str2[i] - '0');
                temp[index] = (cur + count + last) % 10;
                count = (cur + count + last) / 10;
                j--;
            }
            j++;
            if(count != 0){
                temp[temp.length - (n - j - 1) - (m - i - 1) - 2] += count;
                count = 0;
            }
            i--;
        }
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        for (int value : temp) {
            if (value == 0 && !start) {
                continue;
            }
            start = true;
            sb.append(value);
        }
        return sb.toString();
    }
}
