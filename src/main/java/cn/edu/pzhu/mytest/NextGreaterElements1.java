package cn.edu.pzhu.mytest;

import java.util.Arrays;
import java.util.Stack;

/**
 * 下一个更大元素
 *
 * @author zhangcz
 * @since 20201028
 */
public class NextGreaterElements1 {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] resultArr = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int temp = -1;
            int index = -1;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    index = j;
                }

                if (index != -1 && nums2[j] > nums1[i]) {
                    temp = nums2[j];
                    break;
                }
            }

            resultArr[i] = temp;
        }

        return resultArr;
    }

    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] resultArr = new int[nums1.length];
        int[] tempArr = new int[10000 + 5];

        Arrays.fill(tempArr, -1);
        Stack<Integer> stack = new Stack<>();

        for (int value : nums2) {
            while (!stack.isEmpty() && value > stack.peek()) {
                tempArr[stack.pop()] = value;
            }
            stack.push(value);
        }

        for (int i = 0; i < nums1.length; i++) {
            resultArr[i] = tempArr[nums1[i]];
        }

        return resultArr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};
        int[] elements = nextGreaterElement2(nums, nums2);
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }
}
