public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.addBinary("110010", "10111"));
    }

    /**
     * LC 67. 二进制求和
     * 2020年6月23日 10:24:25
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        char[] str_a = a.toCharArray();
        char[] str_b = b.toCharArray();
        int i = str_a.length-1;
        int j = str_b.length-1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 && j >= 0){
            sb.insert(0, (str_a[i] - '0') ^ (str_b[j] - '0') ^ count);
            count = ((str_a[i] - '0') & (str_b[j] - '0')) | ((str_a[i] - '0' & count) | (str_b[j] - '0' & count));
            i--;
            j--;
        }
        while(i >= 0){
            sb.insert(0,(str_a[i] - '0') ^ count);
            count = (str_a[i] & count);
            i--;
        }
        while(j >= 0){
            sb.insert(0,(str_b[j] - '0') ^ count);
            count &= (str_b[j] - '0');
            j--;
        }
        sb.insert(0,count);
        int index = 0;
        while(index < sb.length()-1 && sb.charAt(index) == '0'){
            index++;
        }
        return sb.toString().substring(index);
    }
}
