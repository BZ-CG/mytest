package cn.edu.pzhu.mytest.leetcode;

/**
 * 数独
 *
 * @author zhangcz
 * @since 20220228
 */
public class Sudoku {

    private char[][] result;

    public void solveSudoku(char[][] board) {
        dfsForSudoku(board, 0, 0);
        System.out.println(result);
    }


    private void dfsForSudoku(char[][] board, int row, int column) {
        if (finished(board)) {
            result = board;
            return;
        }

        for (int i = row; i < board.length; i++) {
            for (int j = column; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }

                for (int k = 1; k <= 9; k++) {
                    char c = (char) (k + 48);
                    if (existInRow(board, i, c) || existInColumn(board, j, c) || existInSquare(board, i, j, c)) {
                        continue;
                    }

                    board[i][j] = c;
                    if (finished(board)) {
                        result = board;
                        return;
                    }
                    dfsForSudoku(board, i, j);
                    board[i][j] = '.';
                }
            }
        }
    }

    private boolean finished(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean existInRow(char[][] board, int row, char k) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] == k) {
                return true;
            }
        }

        return false;
    }

    private boolean existInColumn(char[][] board, int column, char k) {
        for (char[] chars : board) {
            if (chars[column] == k) {
                return true;
            }
        }

        return false;
    }


    private boolean existInSquare(char[][] board, int row, int column, char c) {
        int rowStart = (row / 3) * 3;
        int rowEnd = rowStart + 3;

        int columnStart = (column / 3) * 3;
        int columnEnd = columnStart + 3;

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if (board[i][j] == c) {
                    return true;
                }
            }
        }

        return false;
    }

}
