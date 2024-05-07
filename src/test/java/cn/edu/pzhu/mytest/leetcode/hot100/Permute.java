package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangcz
 * @since 20240425
 */
public class Permute {

    private List<List<Integer>> list = new ArrayList<>();

    private LinkedList<Integer> pathList = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        int[] visited = new int[nums.length];
        dfs(nums, visited);
        return list;
    }

    private void dfs(int[] nums, int[] visited) {
        if (pathList.size() == nums.length) {
            list.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                pathList.add(nums[i]);
                dfs(nums, visited);
                visited[i] = 0;
                pathList.removeLast();
            }
        }
    }
}
