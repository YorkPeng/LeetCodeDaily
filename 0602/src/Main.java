public class Main {
    public static void main(String[] args) {

    }

    /**
     * 剑指offer64
     * @param n
     * @return
     */
    public int sumNums(int n) {
        //利用了短路特性，如果n到达0了，判断语句只会执行前半句，即停止了递归。真的很巧妙。
        boolean sum = ((n>0) && (n += sumNums(n-1) )> 0);
        return n;
    }
}
