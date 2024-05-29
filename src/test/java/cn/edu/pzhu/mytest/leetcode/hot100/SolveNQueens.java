package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.util.ResultUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * N 皇后
 *
 * @author zhangcz
 * @since 20240530
 */
public class SolveNQueens {

    private List<List<String>> resultList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        String[][] bord = new String[n][n];
        int[] columnVisited = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bord[i][j] = ".";
            }
        }

        dfs(0, n, bord, columnVisited);
        return resultList;
    }

    private void dfs(int row, int n, String[][] bord, int[] columnVisited) {
        if (row > n - 1) {
            saveBord(bord);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (columnVisited[i] != 0 || !check(row, i, bord)) {
                continue;
            }

            bord[row][i] = "Q";
            columnVisited[i] = 1;
            dfs(row + 1, n, bord, columnVisited);
            bord[row][i] = ".";
            columnVisited[i] = 0;
        }
    }

    private boolean check(int row, int column, String[][] bord) {
        boolean matched = true;
        int i = row, j = column;
        while (i >= 0 && j < bord.length) {
            if (bord[i][j].equals("Q")) {
                matched = false;
                break;
            }

            i--;
            j++;
        }

        i = row;
        j = column;
        while (i >= 0 && j >= 0) {
            if (bord[i][j].equals("Q")) {
                matched = false;
                break;
            }

            i--;
            j--;
        }

        return matched;
    }

    private void saveBord(String[][] bord) {
        List<String> list = new ArrayList<>();
        for (String[] strings : bord) {
            list.add(String.join("", strings));
        }

        resultList.add(list);
    }

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> listList = solveNQueens.solveNQueens(4);
        ResultUtils.printListList(listList);
    }
}
