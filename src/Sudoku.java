public class Sudoku {
static int[][] result = new int[9][9];

    public static void main(String[] args) {
        int[][] board =
                {       {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0} };

              solve(board);
              int[][] res = getResult();
              display(res);


    }
    static void solve(int[][] board){
         isSolved(board);
         result = board;

    }
    static int[][] getResult(){
        return result;
    }

    public static boolean isSolved(int[][] board){
        int row =-1;
        int col =-1;

        boolean EmptyLeft = false;
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j <board.length ; j++) {
                if (board[i][j] == 0 ){
                    row = i ;
                    col = j ;
                    EmptyLeft = true;
                    break;
                }
            }
            if (EmptyLeft) break;
        }
        if (!EmptyLeft) return true; // sudoku is solved

        // backtrack

        for (int number = 1; number <= 9  ; number++) {
            if (isSafe(board , row ,col , number)){
                board[row][col] = number;
                if (isSolved(board)) return true;
                else board[row][col] = 0 ;
            }
        }

        return false;
    }

    public static boolean isSafe(int[][] board , int row , int col , int num ){
        // for rows
        for (int i = 0; i <board[0].length ; i++) {
            if (board[row][i] == num) return false;
        }
        // column
        for (int i = 0; i <board.length ; i++) {
            if (board[i][col] == num) return false;
        }
        // For 3 X 3
        int sqrt = (int)Math.sqrt(board.length);
        int row_start = row - row%sqrt;
        int col_start = col - col%sqrt;

        for (int i = row_start; i <row_start+sqrt ; i++) {
            for (int j = col_start; j <col_start+sqrt ; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true ;
    }
  static void display(int[][] board){
        for (int[] arr  : board){
            for (int ele : arr){
                System.out.print(ele+" ");
            }
            System.out.println();
     }
}

}
