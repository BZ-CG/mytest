package cn.edu.pzhu.mytest.leetcode.top150;

import cn.edu.pzhu.mytest.util.ResultUtils;

/**
 * @author zhangcz
 * @since 20240910
 */
public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] arr = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            arr[i] = left[i] * right[i];
        }

        return arr;
    }

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        ResultUtils.printArr(productExceptSelf.productExceptSelf(new int[]{1, 2, 3, 4}));
        ResultUtils.printArr(productExceptSelf.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
        ResultUtils.printArr(productExceptSelf.productExceptSelf(new int[]{-1, 1, 0, -3, 3, 0}));


    }

}
