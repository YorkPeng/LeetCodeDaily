import java.util.ArrayList;
import java.util.List;

/**
 * @author ZJPang
 * @date 2020/8/21 0021
 */

public class no417 {
    public static void main(String[] args) {
        System.out.println(pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}));
    }

    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(dfs1(matrix, i, j, matrix[i][j], new boolean[matrix.length][matrix[0].length]) && dfs2(matrix, i, j, matrix[i][j], new boolean[matrix.length][matrix[0].length])){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    private static boolean dfs1(int[][] matrix, int r, int c, int last, boolean[][] visited){
        if(r < 0 || c < 0){
            return true;
        }
        if(r >= matrix.length || c >= matrix[0].length || matrix[r][c] > last || visited[r][c]){
            return false;
        }
        visited[r][c] = true;
        return dfs1(matrix, r - 1, c, matrix[r][c], visited) || dfs1(matrix, r + 1, c, matrix[r][c], visited) || dfs1(matrix, r, c - 1, matrix[r][c], visited) || dfs1(matrix, r, c + 1, matrix[r][c], visited);
    }

    private static boolean dfs2(int[][] matrix, int r, int c, int last, boolean[][] visited){
        if(r >= matrix.length || c >= matrix[0].length){
            return true;
        }
        if(r < 0 || c < 0 || matrix[r][c] > last || visited[r][c]){
            return false;
        }
        visited[r][c] = true;
        return dfs2(matrix, r - 1, c, matrix[r][c], visited) || dfs2(matrix, r + 1, c, matrix[r][c], visited) || dfs2(matrix, r, c - 1, matrix[r][c], visited) || dfs2(matrix, r, c + 1, matrix[r][c], visited);
    }
}
