package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 子集
 *
 * @author zhangcz
 * @since 20220228
 */
public class SubsetsWithDup {

    private List<List<Integer>> resultList = new ArrayList<>();
    private LinkedList<Integer> pathList = new LinkedList<>();
    private Set<String> existSubSet = new HashSet<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            dfsForSubsets(nums, i, 0);
        }

        return resultList;
    }

    private void dfsForSubsets(int[] nums, int n, int start) {
        if (pathList.size() == n) {
            String key = generateUniqueKey(pathList);
            if (!existSubSet.contains(key)) {
                resultList.add(new ArrayList<>(pathList));
                existSubSet.add(key);
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            pathList.add(nums[i]);
            dfsForSubsets(nums, n, i + 1);
            pathList.removeLast();
        }
    }

    private String generateUniqueKey(List<Integer> list) {
        return list.stream().sorted().map(String::valueOf).collect(Collectors.joining("-"));
    }
}
