package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class MaxProfit {


    public int maxProfit(int[] prices) {
        int min = prices[0], result = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            result = Math.max(result, price - min);
        }

        return result;
    }


    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        System.out.println(maxProfit.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
