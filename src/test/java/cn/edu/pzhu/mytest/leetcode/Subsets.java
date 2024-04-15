package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 *
 * @author zhangcz
 * @since 20220228
 */
public class Subsets {

    private List<List<Integer>> resultList = new ArrayList<>();
    private LinkedList<Integer> pathList = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            dfsForSubsets(nums, i, 0);
        }

        return resultList;
    }

    private void dfsForSubsets(int[] nums, int n, int start) {
        if (pathList.size() == n) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            pathList.add(nums[i]);
            dfsForSubsets(nums, n, i + 1);
            pathList.removeLast();
        }
    }
}
