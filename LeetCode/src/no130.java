import java.util.Arrays;

/**
 * @author ZJPang
 * @date 2020/8/11 0011
 */

public class no130 {
    public static void main(String[] args) {
        char[][] str = new char[][]{{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}};
        solve(str);
        System.out.println(Arrays.deepToString(str));
    }

    public static void solve(char[][] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'O'){
                    helper(board, i, j);
                }
            }
        }
    }

    private static boolean helper(char[][] board, int row, int col){
        if(row < 0 || row >= board.length || col < 0 || col >= board[row].length || ((row == 0 || row == board.length - 1 || col == 0 || col == board[row].length - 1) && board[row][col] == 'O')
        || board[row][col] == 'Z'){
            return false;
        }
        if(board[row][col] == 'X'){
            return true;
        }
        board[row][col] = 'Z';
        boolean flag =  helper(board, row + 1, col) && helper(board, row - 1, col) && helper(board, row, col + 1) && helper(board, row, col-1);
        if(!flag){
            board[row][col] = 'O';
        }else{
            board[row][col] = 'X';
        }
        return flag;
    }
}
