package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class CanJump2 {


    public int canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int step = 0;
        int cover = 0;
        int nextCover = 0;
        for (int i = 0; i < n - 1; i++) {
            nextCover = Math.max(nextCover, nums[i] + i);
            if (i == cover) {
                cover = nextCover;
                step++;
            }
        }

        return step;
    }

    public static void main(String[] args) {
        CanJump2 canJump = new CanJump2();
        System.out.println(canJump.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump.canJump(new int[]{2, 3, 0, 1, 4}));
    }


}
