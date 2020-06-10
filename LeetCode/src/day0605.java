import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day0605 {
    public static void main(String[] args) {
        day0605 day0605 = new day0605();
        System.out.println(Arrays.toString(day0605.spiralOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}})));
    }
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new int[0];
        }
        int left = 0;
        int up = 0;
        int right = matrix[0].length - 1;
        int down = matrix.length - 1;
        List<Integer> list = new ArrayList<>();
        //边界问题很重要 ，被卡了十多分钟，我吐了。
        while(left <= right && up <= down){
            for(int i = left; i <= right; i++){
                list.add(matrix[up][i]);
            }
            up++;
            for(int i = up; i <= down; i++){
                list.add(matrix[i][right]);
            }
            right--;
            for(int i = right; i >= left && up <= down; i--){
                list.add(matrix[down][i]);
            }
            down--;
            for (int i = down; i >= up && left <= right; i--){
                list.add(matrix[i][left]);
            }
            left++;
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
