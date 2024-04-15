package cn.edu.pzhu.mytest;

import java.util.Arrays;
import java.util.Stack;

/**
 * 下一个更大元素
 *
 * @author zhangcz
 * @since 20201028
 */
public class NextGreaterElements {
    public static int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] answerArr = new int[length];

        for (int i = 0; i < nums.length; i++) {
            int temp = -1;
            for (int j = i + 1; j < length + i; j++) {
                if (nums[j % length] > nums[i]) {
                    temp = nums[j % length];
                    break;
                }
            }
            answerArr[i] = temp;
        }

        return answerArr;
    }

    public static int[] nextGreaterElements2(int[] nums) {
        int length = nums.length;
        int[] answerArr = new int[length];

        Arrays.fill(answerArr, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length * 2; i++) {
            while (!stack.isEmpty() && nums[i % length] > nums[stack.peek()]) {
                answerArr[stack.pop()] = nums[i % length];
            }

            stack.push(i % length);
        }

        return answerArr;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 3, 2, 1};
        int[] elements = nextGreaterElements2(nums);
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
    }
}
