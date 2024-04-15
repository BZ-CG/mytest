package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 寻找递增子序列
 *
 * @author zhangcz
 * @since 20220228
 */
public class FindSubsequences {
    private List<List<Integer>> resultList = new ArrayList<>();
    private LinkedList<Integer> pathList = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        for (int i = 2; i <= nums.length; i++) {
            dfsForSubsets(nums, i, 0);
        }

        return resultList;
    }

    private void dfsForSubsets(int[] nums, int n, int start) {
        if (pathList.size() == n) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        Set<Integer> usedSet = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (usedSet.contains(nums[i]) || !isGreaterThanPre(nums[i], pathList.peekLast())) {
                continue;
            }

            pathList.add(nums[i]);
            usedSet.add(nums[i]);
            dfsForSubsets(nums, n, i + 1);
            pathList.removeLast();
        }
    }

    private boolean isGreaterThanPre(Integer current, Integer pre) {
        if (pre == null) {
            return true;
        }

        return current.compareTo(pre) >= 0;
    }
}
