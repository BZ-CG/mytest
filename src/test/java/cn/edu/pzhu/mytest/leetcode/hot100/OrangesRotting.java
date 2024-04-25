package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangcz
 * @since 20240425
 */
public class OrangesRotting {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                }
                if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        int minute = 0;
        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (freshCount > 0 && !queue.isEmpty()) {
            minute++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = dir[k][0] + poll[0];
                    int y = dir[k][1] + poll[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[] { x, y });
                        freshCount--;
                    }
                }
            }
        }

        if (freshCount != 0) {
            return -1;
        }

        return minute;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        OrangesRotting orangesRotting = new OrangesRotting();
        int minute = orangesRotting.orangesRotting(arr);
        System.out.println(minute);
    }

}
