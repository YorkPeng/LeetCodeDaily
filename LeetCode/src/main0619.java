public class main0619 {
    public static void main(String[] args) {
        main0619 main = new main0619();
        System.out.println(main.isPalindrome("race a car"));
    }

    /**
     * 125. 验证回文串.
     * 简简单单
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] str = s.toLowerCase().toCharArray();
        int left = 0;
        int right = str.length-1;
        while(left < right){
            if(str[left] < '0' || (str[left] > '9' && str[left] < 'a') || str[left] >'z'){
                left++;
            }else if(str[right] < '0' || (str[right] > '9' && str[right] < 'a') || str[right] >'z'){
                right--;
            }else if(str[left] != str[right]){
                return false;
            }else{
                left++;
                right--;
            }
        }
        return true;
    }
}
