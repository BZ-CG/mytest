package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class MaxProfit2 {


    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }


    public static void main(String[] args) {
        MaxProfit2 maxProfit = new MaxProfit2();
        System.out.println(maxProfit.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
