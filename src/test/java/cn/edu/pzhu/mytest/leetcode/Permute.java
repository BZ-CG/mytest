package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 *
 * @author zhangcz
 * @since 20220228
 */
public class Permute {
    private List<List<Integer>> resultList = new ArrayList<>();
    private LinkedList<Integer> pathList = new LinkedList<>();
    private int[] visited = new int[21];

    public List<List<Integer>> permute(int[] nums) {
        dfsForPermute(nums);
        return resultList;
    }

    private void dfsForPermute(int[] nums) {
        if (pathList.size() == nums.length) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int num : nums) {
            if (visited[num + 10] == 1) {
                continue;
            }

            pathList.add(num);
            visited[num + 10] = 1;
            dfsForPermute(nums);
            pathList.removeLast();
            visited[num + 10] = 0;
        }
    }
}
