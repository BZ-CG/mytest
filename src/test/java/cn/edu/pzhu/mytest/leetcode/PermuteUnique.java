package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 *
 * @author zhangcz
 * @since 20220228
 */
public class PermuteUnique {
    private List<List<Integer>> resultList = new ArrayList<>();
    private LinkedList<Integer> pathList = new LinkedList<>();
    private int[] visited = new int[11];

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dfsForPermute(nums);
        return resultList;
    }

    private void dfsForPermute(int[] nums) {
        if (pathList.size() == nums.length) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 剪枝,剪掉重复元素
            if (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) {
                continue;
            }

            if (visited[i] == 1) {
                continue;
            }

            pathList.add(nums[i]);
            visited[i] = 1;
            dfsForPermute(nums);
            pathList.removeLast();
            visited[i] = 0;
        }
    }
}
