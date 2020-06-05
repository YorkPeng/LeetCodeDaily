public class No1 {
    public static void main(String[] args) {

    }

    /**
     * 面试题58-2
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        StringBuffer stringBuffer = new StringBuffer(s.substring(n,s.length()));
        String temp = s.substring(0,n);
        stringBuffer.append(temp);
        return stringBuffer.toString();
    }

}
