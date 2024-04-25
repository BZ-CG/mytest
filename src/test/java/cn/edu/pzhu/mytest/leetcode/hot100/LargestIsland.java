package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangcz
 * @since 20240425
 */
public class LargestIsland {

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] map = new int[m][n];
        Map<Integer, Integer> areaMap = new HashMap<>();

        int result = 0;
        int index = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && map[i][j] == 0) {
                    int area = dfsForMaxAreaOfIsland(grid, i, j, map, index);
                    areaMap.put(index, area);

                    result = Math.max(result, area);
                    index++;
                }
            }
        }

        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int current = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int row = i + dir[k][0];
                        int column = j + dir[k][1];

                        if (row < 0 || column < 0 || row >= m || column >= n) {
                            continue;
                        }

                        int landIndex = map[row][column];
                        if (landIndex == 0) {
                            continue;
                        }

                        if (set.contains(landIndex)) {
                            continue;
                        }

                        int area = areaMap.get(landIndex);
                        current += area;

                        set.add(landIndex);
                    }

                    result = Math.max(result, current);
                }
            }
        }

        return result;
    }

    private int dfsForMaxAreaOfIsland(int[][] grid, int row, int column, int[][] map, int index) {
        int m = grid.length;
        int n = grid[0].length;
        if (row < 0 || column < 0 || row >= m || column >= n) {
            return 0;
        }

        if (grid[row][column] != 1) {
            return 0;
        }

        map[row][column] = index;
        grid[row][column] = 2;
        int count = 0;
        count += dfsForMaxAreaOfIsland(grid, row - 1, column, map, index);
        count += dfsForMaxAreaOfIsland(grid, row + 1, column, map, index);
        count += dfsForMaxAreaOfIsland(grid, row, column - 1, map, index);
        count += dfsForMaxAreaOfIsland(grid, row, column + 1, map, index);
        return count + 1;
    }
}
