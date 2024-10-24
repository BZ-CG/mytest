package cn.edu.pzhu.mytest.leetcode.top150;

import java.util.Arrays;

/**
 * @author zhangcz
 * @since 20240910
 */
public class HIndex {


    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) {
                return n - i;
            }
        }

        return 0;
    }


    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        System.out.println(hIndex.hIndex(new int[]{3, 0, 6, 1, 5}));
        System.out.println(hIndex.hIndex(new int[]{1, 3, 1}));
    }

}
