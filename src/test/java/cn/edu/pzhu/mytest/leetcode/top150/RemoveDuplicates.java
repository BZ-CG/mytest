package cn.edu.pzhu.mytest.leetcode.top150;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangcz
 * @since 20240910
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (!set.contains(value)) {
                nums[k] = value;
                set.add(value);
                k++;
            }
        }

        return k;
    }


    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        System.out.println(removeDuplicates.removeDuplicates(nums));
    }
}
