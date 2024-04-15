package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * letterCombinations
 * <p>
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 *
 * @author zhangcz
 * @since 20220228
 */
public class LetterCombinations {

    private List<String> resultList = new ArrayList<>();
    private static Map<Character, String[]> map = new HashMap<>();

    static {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }


    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return resultList;
        }

        dfs(digits, new StringBuilder(), 0);
        return resultList;
    }

    private void dfs(String digits, StringBuilder builder, int start) {
        if (start == digits.length()) {
            resultList.add(builder.toString());
            return;
        }

        char charNumber = digits.charAt(start);
        String[] strings = map.get(charNumber);
        for (String string : strings) {
            builder.append(string);
            dfs(digits, builder, start + 1);
            builder.deleteCharAt(builder.lastIndexOf(string));
        }
    }
}
