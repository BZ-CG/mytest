package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.util.ResultUtils;

/**
 * @author zhangcz
 * @since 20240627
 */
public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();

        int[] arr = new int[] { 1, 1 };
        nextPermutation.nextPermutation(arr);

        ResultUtils.printArr(arr);
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
            }
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



