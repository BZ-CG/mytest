package cn.edu.pzhu.mytest.leetcode.top150;

import cn.edu.pzhu.mytest.util.ResultUtils;

/**
 * @author zhangcz
 * @since 20240910
 */
public class Rotate {

//    public void rotate(int[] nums, int k) {
//        int length = nums.length;
//        k = k % length;
//
//        int count = 0;
//        int start = 0;
//        int pre = nums[start];
//
//        do {
//            int next = (start + k) % length;
//            int temp = nums[next];
//            nums[next] = pre;
//            pre = temp;
//            start = next;
//
//            count++;
//        } while (count != length);
//    }

    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;

        int start;
        int pre;
        int count = gcd(k, length);
        for (int i = 0; i < count; i++) {
            start = i;
            pre = nums[start];
            do {
                int next = (start + k) % length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                start = next;
            } while (start != i);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[] nums = {-1, -100, 3, 99};
        rotate.rotate(nums, 2);
        ResultUtils.printArr(nums);
    }

}
