package cn.edu.pzhu.mytest.leetcode.hot100;

/**
 * @author zhangcz
 * @since 20240425
 */
public class IslandPerimeter {

    private int count = 0;

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int row, int column) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || row >= m || column < 0 || column >= n) {
            return;
        }

        if (grid[row][column] != 1) {
            return;
        }

        grid[row][column] = 2;

        if (row - 1 < 0 || grid[row - 1][column] == 0) {
            count++;
        }

        if (row + 1 >= m || grid[row + 1][column] == 0) {
            count++;
        }

        if (column - 1 < 0 || grid[row][column - 1] == 0) {
            count++;
        }

        if (column + 1 >= n || grid[row][column + 1] == 0) {
            count++;
        }

        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }

}
