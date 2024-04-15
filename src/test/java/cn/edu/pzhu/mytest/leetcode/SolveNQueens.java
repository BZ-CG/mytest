package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后
 *
 * @author zhangcz
 * @since 20220228
 */
public class SolveNQueens {
    private boolean[] visitedColumn = new boolean[10];
    private List<List<String>> resultList = new ArrayList<>();

    public void dfs(String[][] arr, int row, int count, int n) {
        if (count == n) {
            saveResult(arr);
            return;
        }

        for (int j = 0; j < n; j++) {
            // 当前列已存在皇后
            if (visitedColumn[j]) {
                continue;
            }

            // 对角线已存在皇后
            if (leftUpExistQueen(arr, row, j) || rightUpExistQueen(arr, n, row, j)) {
                continue;
            }

            arr[row][j] = "Q";
            visitedColumn[j] = true;

            dfs(arr, row + 1, count + 1, n);

            arr[row][j] = ".";
            visitedColumn[j] = false;
        }
    }

    /**
     * 左上斜对角线是否存在皇后
     *
     * @param arr    数组
     * @param row    当前行
     * @param column 当前列
     * @return true:存在，false:不存在
     */
    private boolean leftUpExistQueen(String[][] arr, int row, int column) {
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(arr[i][j])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 右上斜是否存在皇后
     *
     * @param arr    数组
     * @param n      n
     * @param row    当前行
     * @param column 当前列
     * @return true:存在，false:不存在
     */
    private boolean rightUpExistQueen(String[][] arr, int n, int row, int column) {
        for (int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
            if ("Q".equals(arr[i][j])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 保存结果
     *
     * @param arr 当前棋盘结果
     */
    private void saveResult(String[][] arr) {
        List<String> list = new ArrayList<>();
        for (String[] strings : arr) {
            list.add(String.join("", strings));
        }

        resultList.add(list);
    }

    public List<List<String>> solveNQueens(int n) {
        String[][] arr = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ".";
            }
        }

        Arrays.fill(visitedColumn, false);

        dfs(arr, 0, 0, n);

        return resultList;
    }
}
