package cn.edu.pzhu.mytest.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效括号
 *
 * @author zhangcz
 * @since 20240530
 */
public class ValidBracket {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
                continue;
            }

            if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidBracket validBracket = new ValidBracket();
        System.out.println(validBracket.isValid("()"));
        System.out.println(validBracket.isValid("()[]{}"));
        System.out.println(validBracket.isValid("(]"));
        System.out.println(validBracket.isValid("([]"));
        System.out.println(validBracket.isValid("]"));
    }
}
