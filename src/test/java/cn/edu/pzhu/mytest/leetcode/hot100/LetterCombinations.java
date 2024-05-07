package cn.edu.pzhu.mytest.leetcode.hot100;

import cn.edu.pzhu.mytest.util.ResultUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangcz
 * @since 20240425
 */
public class LetterCombinations {

    private Map<Character, String> map = new HashMap<>();

    private List<String> list = new ArrayList<>();

    private StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        dfs(digits, 0);
        return list;
    }

    private void dfs(String digits, int start) {
        if (sb.length() == digits.length()) {
            list.add(sb.toString());
            return;
        }

        char c = digits.toCharArray()[start];
        String s = map.get(c);
        for (char c1 : s.toCharArray()) {
            sb.append(c1);
            dfs(digits, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> stringList = letterCombinations.letterCombinations("23");
        ResultUtils.printList(stringList);
    }
}
