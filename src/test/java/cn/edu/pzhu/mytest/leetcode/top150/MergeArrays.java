package cn.edu.pzhu.mytest.leetcode.top150;

import cn.edu.pzhu.mytest.util.ResultUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangcz
 * @since 20240910
 */
public class MergeArrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list = new ArrayList<>(m + n);

        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }

        while (i < m) {
            list.add(nums1[i]);
            i++;
        }

        while (j < n) {
            list.add(nums2[j]);
            j++;
        }

        for (i = 0; i < list.size(); i++) {
            nums1[i] = list.get(i);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{2};

        MergeArrays mergeArrays = new MergeArrays();
        mergeArrays.merge(nums1, 0, nums2, 1);

        ResultUtils.printArr(nums1);
    }

}
