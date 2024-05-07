package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangcz
 * @since 20240425
 */
public class CombinationSum {

    private List<List<Integer>> resultList = new ArrayList<>();

    private LinkedList<Integer> pathList = new LinkedList<>();

    private int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return resultList;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            pathList.add(candidates[i]);
            sum += candidates[i];
            dfs(candidates, target, i);
            pathList.removeLast();
            sum -= candidates[i];
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[] { 2, 3, 5 };
        int target = 8;

        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> list1 = combinationSum.combinationSum(candidates, target);
        for (List<Integer> list : list1) {
            String collect = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.printf("[%s]\n", collect);
        }
    }
}
