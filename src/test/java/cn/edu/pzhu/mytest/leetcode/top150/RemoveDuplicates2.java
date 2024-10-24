package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class RemoveDuplicates2 {

    public int removeDuplicates(int[] nums) {
        int slow = 1, fast = 1;
        int pre = nums[0];
        int count = 1;
        while (fast < nums.length) {
            if (nums[fast] == pre) {
                count++;
            } else {
                pre = nums[fast];
                count = 1;
            }

            if (count <= 2) {
                nums[slow] = nums[fast];
                slow++;
            }

            fast++;
        }

        return slow;
    }


    public static void main(String[] args) {
        RemoveDuplicates2 removeDuplicates = new RemoveDuplicates2();

        System.out.println(removeDuplicates.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        System.out.println(removeDuplicates.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }
}
