package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * combinationSum
 *
 * @author zhangcz
 * @since 20220228
 */
public class CombinationSum {

    private int sum = 0;
    private LinkedList<Integer> list = new LinkedList<>();
    private List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return resultList;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            resultList.add(new ArrayList<>(list));
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝：当前值大于剩余值不再搜索
            if (candidates[i] > target - sum) {
                break;
            }

            sum += candidates[i];
            list.add(candidates[i]);

            dfs(candidates, target, i);

            sum -= candidates[i];
            list.removeLast();
        }
    }
}
