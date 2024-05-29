package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.util.ResultUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回文串
 *
 * @author zhangcz
 * @since 20240530
 */
public class Palindromic {

    private List<List<String>> resultList = new ArrayList<>();

    private LinkedList<String> pathList = new LinkedList<>();

    public List<List<String>> partition(String s) {
        dfs(s);
        return resultList;
    }

    private void dfs(String s) {
        if (s.isEmpty()) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = 0; i < s.toCharArray().length; i++) {
            String substring = s.substring(0, i + 1);
            if (!isPalindromic(substring)) {
                continue;
            }
            pathList.add(substring);
            dfs(s.substring(i + 1));
            pathList.removeLast();
        }
    }

    private boolean isPalindromic(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        char[] array = s.toCharArray();
        for (int i = 0, j = array.length - 1; i <= j; i++, j--) {
            if (array[i] != array[j]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Palindromic palindromic = new Palindromic();
        List<List<String>> partition = palindromic.partition("aab");
        ResultUtils.printListList(partition);
    }
}
