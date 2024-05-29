package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 *
 * @author zhangcz
 * @since 20240530
 */
public class GenerateParenthesis {

    private List<String> resultList = new ArrayList<>();

    private StringBuilder sb = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n);
        return resultList;
    }

    private void dfs(int left, int right, int n) {
        if (left > n || left < right) {
            return;
        }

        if (sb.length() == n * 2) {
            resultList.add(sb.toString());
            return;
        }

        sb.append("(");
        dfs(left + 1, right, n);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        dfs(left, right + 1, n);
        sb.deleteCharAt(sb.length() - 1);

    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> stringList = generateParenthesis.generateParenthesis(3);
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}
