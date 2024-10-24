package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class Candy {


    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) count += Math.max(left[i], right[i]);

        return count;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
        System.out.println(candy.candy(new int[]{1, 0, 2}));
        System.out.println(candy.candy(new int[]{1, 2, 2}));
        System.out.println(candy.candy(new int[]{1, 3, 2, 2, 1}));
        System.out.println(candy.candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
    }
}




