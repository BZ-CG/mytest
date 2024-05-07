package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangcz
 * @since 20240425
 */
public class Subsets {

    private List<List<Integer>> list = new ArrayList<>();

    private LinkedList<Integer> pathList = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return list;
    }

    private void dfs(int[] nums, int start) {
        list.add(new ArrayList<>(pathList));
        for (int i = start; i < nums.length; i++) {
            pathList.add(nums[i]);
            dfs(nums, i + 1);
            pathList.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        Subsets subsets = new Subsets();
        List<List<Integer>> list1 = subsets.subsets(nums);
        for (List<Integer> list : list1) {
            String collect = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.printf("[%s]\n", collect);
        }
    }
}
