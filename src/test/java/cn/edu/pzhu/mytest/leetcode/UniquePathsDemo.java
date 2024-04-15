package cn.edu.pzhu.mytest.leetcode;

/**
 * 不同路径
 *
 * @author zhangcz
 * @since 20220228
 */
public class UniquePathsDemo {
    public int uniquePaths(int m, int n) {
        return dfs(1, 1, m, n);
    }

    private int dfs(int i, int j, int m, int n) {
        if (i > m || j > n) {
            return 0;
        }

        if (i == m && j == n) {
            return 1;
        }

        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}
