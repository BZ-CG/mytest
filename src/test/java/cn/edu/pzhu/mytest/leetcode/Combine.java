package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * combine
 *
 * @author zhangcz
 * @since 20220228
 */
public class Combine {

    public List<List<Integer>> resultList = new ArrayList<>();
    public LinkedList<Integer> pathList = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0) {
            return new ArrayList<>();
        }

        dfsForCombine(n, k, 1);
        return resultList;
    }

    public void dfsForCombine(int n, int k, int start) {
        if (pathList.size() == k) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        // 剪枝：元素个数
        for (int i = start; i <= n - (k - pathList.size()) + 1; i++) {
            pathList.add(i);
            dfsForCombine(n, k, i + 1);
            pathList.removeLast();
        }
    }
}
