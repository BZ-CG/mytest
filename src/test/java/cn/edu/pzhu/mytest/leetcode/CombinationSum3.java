package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * combinationSum3
 *
 * @author zhangcz
 * @since 20220228
 */
public class CombinationSum3 {

    public int sum = 0;
    public LinkedList<Integer> pathList = new LinkedList<>();
    public List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfsForCombinationSum3(k, n, 1);
        return resultList;
    }

    private void dfsForCombinationSum3(int k, int n, int start) {
        if (pathList.size() == k && sum == n) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        // 剪枝1：上限最大值
        int maxIndex = Math.min(n - sum, 9);
        int needNumber = k - pathList.size();
        for (int i = start; i <= maxIndex; i++) {
            // 剪枝2：元素个数
            if (maxIndex - i + 1 < needNumber) {
                continue;
            }

            sum += i;
            pathList.add(i);

            dfsForCombinationSum3(k, n, i + 1);

            sum -= i;
            pathList.removeLast();
        }
    }
}
