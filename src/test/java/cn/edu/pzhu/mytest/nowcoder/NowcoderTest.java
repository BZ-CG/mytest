package cn.edu.pzhu.mytest.nowcoder;

import cn.edu.pzhu.mytest.nowcoder.model.Interval;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 牛客test
 *
 * @author zhangcz
 * @since 20230627
 */
@SpringBootTest
public class NowcoderTest {


    @Test
    public void testGcd() {
        System.out.println(gcd(4, 6));
        System.out.println(gcd(6, 4));
        System.out.println(gcd(5, 7));
        System.out.println(gcd(2, 4));
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }


    @Test
    public void testGetCubeRoot() {
        System.out.println(getCubeRoot(new BigDecimal("19.9")).setScale(1, RoundingMode.HALF_UP).toString());
        System.out.println(getCubeRoot(new BigDecimal("2.7")).setScale(1, RoundingMode.HALF_UP).toString());
        System.out.println(getCubeRoot(new BigDecimal("-20")).setScale(1, RoundingMode.HALF_UP).toString());
        double a = 1e-3;
    }

    public BigDecimal getCubeRoot(BigDecimal bigDecimal) {
        boolean isNegative = false;
        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            isNegative = true;
            bigDecimal = bigDecimal.multiply(new BigDecimal("-1"));
        }

        BigDecimal step = new BigDecimal("0.01");
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal temp = result.multiply(result).multiply(result);
        while (temp.compareTo(bigDecimal) < 0) {
            result = result.add(step);
            temp = result.multiply(result).multiply(result);
        }

        if (isNegative) {
            return result.multiply(new BigDecimal("-1"));
        }

        return result;
    }


    @Test
    public void testMinWindow() {
//        System.out.println(minWindow("XDOYEZODEYXNZ","XYZ"));
//        System.out.println(minWindow("abcAbA","AA"));
//        System.out.println(minWindow("ab","A"));

        System.out.println(minWindow2("XDOYEZODEYXNZ", "XYZ"));
        System.out.println(minWindow2("abcAbA", "AA"));
        System.out.println(minWindow2("ab", "A"));
        System.out.println(minWindow2("wyqaalfdtavrmkvrgbrmauenibfxrzjpzzqzlveexayjkvdsyueboloymtvfugvtgnutkyzhaztlvwdvqkwgvmejhbpdimwqbslnrkutkpehnkefwblrprcxvtaffzxitivmssgehklvwqastojihmhcfkhnlexemtrhnxlujxrgpuyiikspycufodubisfwnydaxrwhqqpfkppuzjlzlfhbjbcttkriixkiohpexgjjvafxjqyvyfyjhbccltlvsvdgeumdavoyxtvhmtekzctidxkqsxmlvrrzmefobtmznhizdmlcoemudwkvuyirscqegvsjrfkgoshrgsvvyhrbgdycehtwjlcrjucabpgsjnjqhhnfqeiwhgalptjyflpoiuqjjwdslpiswvxobfljnnwdhgdortezpulysoqddbxbwuqabdjqqhtzpxpjsvkjrvhjmzoralvzhlzkqkbgrwijvzspvcymafymfmfhaaghnfsdrvmlruuntmcqqbdqideprkxrmfbanvfeqrphnlwjxbzqcegmhnczxbslitnvotaemroadkjclksppzeyoiznlsytnopchritiyvlleqypiqgjugxeikpclipzxtgoebxcxkpdaoulecuajueretvpbkqbgwrkaooxbeaduvoaxlaifgblzwdcjtfpsxbsnrrovturokrovtycbcqcytfjomygj", "baxtr"));
    }

    public String minWindow2(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            push(need, c, 1);
        }

        int left = 0, right = 0, valid = 0, start = 0, length = Integer.MAX_VALUE;
        while (right < s.length()) {
            char nextInWindowChar = s.charAt(right);
            right++;
            push(window, nextInWindowChar, 1);
            if (getCount(need, nextInWindowChar) > 0) {
                if (getCount(need, nextInWindowChar) == getCount(window, nextInWindowChar)) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }

                char nextRemoveChar = s.charAt(left);
                left++;
                if (getCount(need, nextRemoveChar) > 0) {
                    if (getCount(need, nextRemoveChar) == getCount(window, nextRemoveChar)) {
                        valid--;
                    }
                }
                push(window, nextRemoveChar, -1);
            }
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }

    private int getCount(Map<Character, Integer> map, Character key) {
        return map.getOrDefault(key, 0);
    }

    private void push(Map<Character, Integer> map, Character key, Integer count) {
        int finishCount = map.getOrDefault(key, 0) + count;
        map.put(key, finishCount);
    }

    /**
     * @param s string字符串
     * @param t string字符串
     * @return string字符串
     */
    public String minWindow(String s, String t) {
        Set<Character> set = new HashSet<>(t.length());
        for (char c : t.toCharArray()) {
            set.add(c);
        }

        int length = t.length();
        while (length <= s.length()) {
            for (int i = 0; i + length <= s.length(); i++) {
                if (!set.contains(s.charAt(i))) {
                    continue;
                }

                String subString = s.substring(i, i + length);
                if (isContain(subString, t)) {
                    return subString;
                }
            }
            length++;
        }
        return "";
    }

    private boolean isContain(String s, String subString) {
        int[] sMap = new int[53];
        for (char c : s.toCharArray()) {
            int index = getCode(c);
            sMap[index]++;
        }
        int[] subMap = new int[53];
        for (char c : subString.toCharArray()) {
            int index = getCode(c);
            subMap[index]++;
        }

        for (int i = 0; i < subMap.length; i++) {
            if (subMap[i] == 0) {
                continue;
            }

            if (subMap[i] > sMap[i]) {
                return false;
            }
        }

        return true;
    }

    public int getCode(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }

        return c - 'A' + 26;
    }


    @Test
    public void testLongestPalindrome() {
        System.out.println(getLongestPalindrome("ababc"));
        System.out.println(getLongestPalindrome("abbba"));
        System.out.println(getLongestPalindrome("b"));
        System.out.println(getLongestPalindrome("abba"));
        System.out.println(getLongestPalindrome("aaaaaccc"));
    }

    //aba
    //abba
    public int getLongestPalindrome(String s) {
        int max = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            max = Math.max(max, getPalindromeLength(chars, i, i));
            max = Math.max(max, getPalindromeLength(chars, i, i + 1));
        }
        return max;
    }

    public int getPalindromeLength(char[] arr, int left, int right) {
        if (arr[left] != arr[right]) {
            return 1;
        }

        while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }

        return right - left + 1 - 2;
    }

    @Test
    public void testIsValid() {
        System.out.println(isValid("[]"));
        System.out.println(isValid("["));
        System.out.println(isValid("]"));
        System.out.println(isValid("([)]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('[', ']');
        map.put('(', ')');
        map.put('{', '}');
        String leftBrackets = "([{";
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (leftBrackets.contains(String.valueOf(aChar))) {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                Character pop = stack.pop();
                if (map.get(pop) != aChar) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void testMerge() {
        // [[10,30],[20,60],[80,100],[150,180]]
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(20, 60));
        intervals.add(new Interval(80, 100));
        intervals.add(new Interval(150, 180));

        ArrayList<Interval> resultList = merge(intervals);
        for (Interval interval : resultList) {
            System.out.printf("[%s,%s]", interval.start, interval.end);
        }
    }


    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }

        ArrayList<Interval> list = new ArrayList<>(intervals.size());
        intervals.sort(Comparator.comparingInt(i -> i.start));

        Interval pre = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.start <= pre.end) {
                pre.end = Math.max(pre.end, current.end);
            } else {
                list.add(pre);
                pre = current;
            }
        }

        list.add(pre);
        return list;
    }

}
