package cn.edu.pzhu.mytest.huawei;

import cn.edu.pzhu.mytest.bean.ListNode;
import cn.edu.pzhu.mytest.util.ResultUtils;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 华为测试
 *
 * @author zhangcz
 * @since 20220228
 */
@SpringBootTest
class HuaWeiTest {


    @Test
    public void testGetLongestSub() {
        System.out.println(getLongestSub(new int[]{2, 5, 1, 5, 4, 5}));
        System.out.println(dfsForGetLongestSubResult(new int[]{2, 5, 1, 5, 4, 5}));

    }

    public int dfsForGetLongestSubResult(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int number = dfsForGetLongestSub(arr, i);
            max = Math.max(max, number);
        }

        return max;
    }

    public int dfsForGetLongestSub(int[] arr, int start) {
        if (start > arr.length) {
            return 0;
        }

        int max = 1;
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] > arr[start]) {
                max = Math.max(max, dfsForGetLongestSub(arr, i) + 1);
            }
        }

        return max;
    }

    public int getLongestSub(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 0;

        Arrays.fill(dp, 1);
        for (int i = 1; i < arr.length; i++)
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }

        return max;
    }


    @Test
    public void testGetAutomorphicNumber() {
        ResultUtils.printList(getAutomorphicNumber(6));
        ResultUtils.printList(getAutomorphicNumber(10000));
    }

    public List<Integer> getAutomorphicNumber(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            if (String.valueOf(i * i).endsWith(String.valueOf(i))) {
                list.add(i);
            }
        }

        return list;
    }


    @Test
    public void testGetAvg() {
//        getAvg("1 2 3 4 5 6 7 8 9 0 -1");
//        getAvg("0 0 0");
//        getAvg("320 236 -78 -293 -12 104 -156 -271 -273 226 244 293 3 -37 -123 -187 -104 -167 -50 -213 14 309 -200 -185 234 -68 -23 -33 -286 -29 221 145 -251 123 -290 254 -240 133 242 -210 -2 212 319 146 300 176 -216 -89 91 -275 69 217 129 246 296 0 -72 -260 -268 -107 105 -199 296 -186 -141 -220 289 -201");
//        getAvg("254 117 -185 287 200 -218 144 104 74 113 14 -282 -58 48 163 -144 -129 -81 -66 181 37 -246 3 -90 92 55 278 -258 73 -291 264 134 167 155 -48 47 -98 196 58 -100 104 -125 -92 287 214 32 130 59 244 319 -84 -188 76 102 313 -74 -12 112 -158 203 62 134 207 -280 -182 254 250 29 181 -2 -55 302 -294 166 208 -181 -252 135 89 272 -29 236 -22 318 57 -212 -192 60 -108 -101 -31 21 91 99 252 77 -11 20 180 220 -229 99 269 122 -87 138 278 -204 243 46 177 28 55 60 -213 5 -272 10 122 73 -94 -36 234 202 -236 -152 102 -238 -234 -31 -260 313 -286 -95 -156 -13 -3 -166 -42 -176 -90 -220 -7 98 227 56 137 247 -160 155 271 -109 -41 -145 2 -91 145 -8 156 -264 -276 67 -138 -299 -125 213 8 -111 -24 -177 192 268 89 -29 183 200 310 79 91 298 245 -111 273 -162 90 291 -105 31 312 166 -170 258 -149 -251 167 -244 -199 106 -280 -277 -45 -35 -35 34 -240 -149 -121 49 -34 -48 -87 186 -217");
        BigDecimal b1 = new BigDecimal(12);
        BigDecimal b2 = new BigDecimal(5);
        System.out.println(b1.divide(b2, RoundingMode.HALF_UP).toString());
        System.out.printf("%.1f%n", 12.36);
    }

    public void getAvg(String s) {
        int positiveCount = 0;
        int negativeCount = 0;
        int positiveSum = 0;
        String[] strings = s.split(" ");
        for (String string : strings) {
            int number = Integer.parseInt(string);
            if (number > 0) {
                positiveCount++;
                positiveSum += number;
            }

            if (number < 0) {
                negativeCount++;
            }
        }

        if (positiveSum == 0) {
            System.out.printf("%s %s\n", negativeCount, "0.0");
        } else {
            System.out.printf("%s %.1f\n", negativeCount, positiveSum * 1.0 / positiveCount);
        }
    }

    @Test
    public void testAppendChar() {
        System.out.println(appendChar("Jkdi234klowe90a3"));
    }

    public String appendChar(String s) {
        List<Character> list = new ArrayList<>(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]) && (i == 0 || !Character.isDigit(chars[i - 1]))) {
                list.add('*');
            }

            list.add(chars[i]);

            if (Character.isDigit(chars[i]) && (i == chars.length - 1 || !Character.isDigit(chars[i + 1]))) {
                list.add('*');
            }
        }

        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);
        return sb.toString();
    }

    @Test
    public void testGetMaxNumberSubString() {
        getMaxNumberSubString("abcd12345ed125ss123058789");
        getMaxNumberSubString("a8a72a6a5yy98y65ee1r2");

        System.out.println(6 * 16 * 16 + 4 * 16 + 10);
    }


    public void getMaxNumberSubString(String string) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int cnt = 0;
        int start = 0;
        for (int i = 0; i < string.toCharArray().length; i++) {
            if (Character.isDigit(string.toCharArray()[i])) {
                start = i;
                break;
            }
        }

        for (int i = start + 1; i < string.toCharArray().length; i++) {
            if (Character.isDigit(string.toCharArray()[i])) {
                cnt++;
            } else {
                String subString = string.substring(start, i);
                if (subString.length() > max) {
                    max = subString.length();
                    sb.setLength(0);
                    sb.append(subString);
                } else if (subString.length() == max) {
                    sb.append(subString);
                }

                start = i + 1;
                cnt = 0;
            }
        }

        if (cnt != 0) {
            String subString = string.substring(start);
            if (subString.length() > max) {
                max = subString.length();
                sb.setLength(0);
                sb.append(subString);
            } else if (subString.length() == max) {
                sb.append(subString);
            }
        }

        System.out.printf("%s,%s\n", sb, max);
    }


    @Test
    public void testGetBoardPathCount() {
        System.out.println(getBoardPathCount(2, 2));
        System.out.println(getBoardPathCount(1, 1));
    }

    public int getBoardPathCount(int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        return dp[n][m];
    }

    @Test
    public void testIsValidIP() {

        System.out.println(isValidIP("255.255.255.1000"));
        System.out.println(isValidIP("10.137.17.1"));
        System.out.println(isValidIP("01.2.3.8"));
        System.out.println(isValidIP("0.2.3.8"));

    }

    public String isValidIP(String string) {
        String falseString = "NO";
        String trueString = "YES";
        if (string == null || string.length() == 0) {
            return falseString;
        }

        String[] split = string.split("\\.");
        if (split.length != 4) {
            return falseString;
        }

        for (String s : split) {
            try {
                if (s.startsWith("0") && s.length() > 1) {
                    return falseString;
                }
                if (!Character.isDigit(s.toCharArray()[0])) {
                    return falseString;
                }

                int value = Integer.parseInt(s);
                if (value > 255) {
                    return falseString;
                }
            } catch (Exception e) {
                return falseString;
            }
        }

        return trueString;
    }


    @Test
    public void testGetMaxLengthBit() {
        System.out.println(getMaxLengthBit(3));
        System.out.println(getMaxLengthBit(200));
        System.out.println(getMaxLengthBit(5));
    }

    public int getMaxLengthBit(int number) {
        int result = 0, count = 0;
        while (number != 0) {
            int mod = number % 2;
            if (mod == 1) {
                count++;
            } else {
                result = Math.max(result, count);
                count = 0;
            }

            number /= 2;
        }

        result = Math.max(result, count);
        return result;
    }

    @Test
    public void testGetLongestPalindrome() {

        System.out.println(getLongestPalindrome("cdabbacc"));
        System.out.println(getLongestPalindrome("a"));

    }

    public String getLongestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i < s.toCharArray().length; i++) {
            String split1 = split(s, i - 1, i + 1);
            String split2 = split(s, i, i + 1);

            result = findLongest(result, split1, split2);
        }

        return result;
    }

    private String findLongest(String result, String split1, String split2) {
        String temp = split1;
        if (split2.length() > temp.length()) {
            temp = split2;
        }

        return temp.length() >= result.length() ? temp : result;
    }

    private String split(String s, int left, int right) {
        char[] chars = s.toCharArray();
        while (left >= 0 && right < s.length() && chars[left] == chars[right]) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }


    @Test
    public void testContains() {
        System.out.println(contains("abc", "bc"));


    }

    public boolean contains(String s1, String s2) {
        int[] table = new int[26];
        for (char c : s1.toCharArray()) {
            int index = c - 'a';
            table[index]++;
        }

        for (char c : s2.toCharArray()) {
            int index = c - 'a';
            if (table[index] == 0) {
                return false;
            }
        }

        return true;
    }


    @Test
    public void testSortArray() {
        String[] arr1 = new String[]{"11", "2", "5"};
        String[] arr2 = new String[]{"-1", "0", "3", "2"};

        System.out.println(sortArray(arr1, arr2));
    }

    public String sortArray(String[] arr1, String[] arr2) {
        Set<Integer> set = new HashSet<>();
        if (arr1 != null && arr1.length > 0) {
            for (String s : arr1) {
                set.add(Integer.parseInt(s));
            }
        }

        if (arr2 != null && arr2.length > 0) {
            for (String s : arr2) {
                set.add(Integer.parseInt(s));
            }
        }

        List<Integer> list = new ArrayList<>(set);
        return list.stream().sorted().map(String::valueOf).collect(Collectors.joining());
    }


    @Test
    public void testTrain() {
        List<String> list = Lists.newArrayList("1", "2", "3");
        List<String> resultList = new ArrayList<>();

        train(resultList, new ArrayList<>(), new Stack<>(), list, 0);

        resultList.sort(Comparator.naturalOrder());
        ResultUtils.printList(resultList);
    }

    private void train(List<String> resultList, List<String> pathList, Stack<String> trainStack, List<String> list, int index) {
        if (pathList.size() == list.size()) {

            resultList.add(String.join(" ", pathList));
            return;
        }

        // 进站
        if (index < list.size()) {
            trainStack.push(list.get(index));
            train(resultList, pathList, trainStack, list, index + 1);
            trainStack.pop();
        }

        // 出站
        if (!trainStack.isEmpty()) {
            String peek = trainStack.pop();
            pathList.add(peek);
            train(resultList, pathList, trainStack, list, index);
            pathList.remove(pathList.size() - 1);
            trainStack.push(peek);
        }
    }


    @Test
    public void testGetDay() {
        System.out.println(getDay(2012, 12, 31));
        System.out.println(getDay(1982, 3, 4));
    }


    private int getDay(int year, int month, int day) {
        int[] arr = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        boolean leapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

        int count = 0;
        for (int i = 0; i < month - 1; i++) {
            // 2月
            if (i == 1) {
                // 闰年
                if (leapYear) {
                    count += 29;
                } else {
                    count += arr[i];
                }
            } else {
                count += arr[i];
            }
        }

        count += day;
        return count;
    }

    @Test
    public void testBuyChicken() {
        buyChicken();
    }

    private void buyChicken() {
        int totalCount = 100;
        int totalMoney = 100;

        for (int i = 0; i <= 20; i++)
            for (int j = 0; j <= 34; j++)
                for (int k = 0; k <= 100; k = k + 3) {
                    int count = i + j + k;
                    int money = i * 5 + j * 3 + k / 3;
                    if (count == totalCount && money == totalMoney) {
                        System.out.printf("%s %s %s\n", i, j, k);
                    }
                }
    }


    @Test
    public void testIsMatch() {
        System.out.println(isMatch("txt12.xls", "te?t*.*"));
        System.out.println(isMatch("0QZz", "**Z"));
        System.out.println(isMatch("zz", "z"));
        System.out.println(isMatch("abcd", "?*Bc*?"));
        System.out.println(isMatch("h#a", "h*?*a"));
        System.out.println(isMatch("txt12.xls", "t?t*1*.*"));


    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] aChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (pChars[i - 1] == '*' && isDigitOrLetter(aChars[i - 1])) {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (getCode(aChars[i - 1]) == getCode(pChars[j - 1]) || (pChars[j - 1] == '?' && isDigitOrLetter(aChars[i - 1]))) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChars[j - 1] == '*') {
                    boolean use = dp[i - 1][j] && isDigitOrLetter(aChars[i - 1]);
                    boolean notUse = dp[i][j - 1];

                    dp[i][j] = use || notUse;
                }
            }

        return dp[m][n];
    }

    private boolean isDigitOrLetter(char c) {
        return Character.isDigit(c) || Character.isLetter(c);
    }


    @Test
    public void testDigit() {

        System.out.println(Character.digit('8', 10));
        System.out.println(Integer.parseInt("8"));
    }

    @Test
    public void testCalculateMatrixCount() {
        List<String> list = new ArrayList<>();
        list.add("50 10");
        list.add("10 20");
        list.add("20 5");
        System.out.println(calculateMatrixCount(list, "(A(BC))"));
    }

    public int calculateMatrixCount(List<String> list, String express) {
        Map<Integer, String> indexToMatrixMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            // 构建矩阵索引
            indexToMatrixMap.put(i, list.get(i));
        }

        Stack<String> charStack = new Stack<>();
        Stack<String> matrixStack = new Stack<>();

        int sum = 0;
        for (char c : express.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                matrixStack.push(c + "");
            } else {
                if (c == '(') {
                    charStack.push(c + "");
                } else {
                    // 栈的顺序是相反的，所以先出栈的是矩阵2
                    String matrix2 = getMatrix(matrixStack.pop(), indexToMatrixMap);
                    String matrix1 = getMatrix(matrixStack.pop(), indexToMatrixMap);

                    String[] arr1 = matrix1.split(" ");
                    String[] arr2 = matrix2.split(" ");

                    sum += Integer.parseInt(arr1[0]) * Integer.parseInt(arr1[1]) * Integer.parseInt(arr2[1]);
                    String newMatrix = arr1[0] + " " + arr2[1];
                    // 将计算结果矩阵压入栈中
                    matrixStack.push(newMatrix);
                    // 弹出外侧的 (
                    charStack.pop();
                }
            }
        }

        return sum;
    }

    private String getMatrix(String s, Map<Integer, String> indexToMatrixMap) {
        if (s.length() > 1) {
            return s;
        }

        int index = s.toCharArray()[0] - 'A';
        return indexToMatrixMap.get(index);
    }


    @Test
    public void testMatrix() {
        int[][] a = {{1, 2, 3}, {3, 2, 1}};
        int[][] b = {{1, 2}, {2, 1}, {3, 3}};

        matrix(a, b);
    }


    public void matrix(int[][] a, int[][] b) {
        int row = a.length;
        int column = b[0].length;
        int[][] c = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                c[i][j] = calculateSum(a, i, b, j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == column - 1) {
                    System.out.println(c[i][j]);
                } else {
                    System.out.print(c[i][j] + " ");
                }
            }
        }
    }

    private int calculateSum(int[][] a, int row, int[][] b, int j) {
        int sum = 0;
        for (int i = 0; i < a[row].length; i++) {
            sum += a[row][i] * b[i][j];
        }
        return sum;
    }


    @Test
    public void testGetMaxSubString() {
        System.out.println(getMaxSubString("nvlrzqcjltmrejybjeshffenvkeqtbsnlocoyaokdpuxutrsmcmoearsgttgyyucgzgcnurfbubgvbwpyslaeykqhaaveqxijc", "wkigrnngxehuiwxrextitnmjykimyhcbxildpnmrfgcnevjyvwzwuzrwvlomnlogbptornsybimbtnyhlmfecscmojrxekqmj"));
        System.out.println(getMaxSubString2("nvlrzqcjltmrejybjeshffenvkeqtbsnlocoyaokdpuxutrsmcmoearsgttgyyucgzgcnurfbubgvbwpyslaeykqhaaveqxijc", "wkigrnngxehuiwxrextitnmjykimyhcbxildpnmrfgcnevjyvwzwuzrwvlomnlogbptornsybimbtnyhlmfecscmojrxekqmj"));
        System.out.println(getMaxSubStringForDP("nvlrzqcjltmrejybjeshffenvkeqtbsnlocoyaokdpuxutrsmcmoearsgttgyyucgzgcnurfbubgvbwpyslaeykqhaaveqxijc", "wkigrnngxehuiwxrextitnmjykimyhcbxildpnmrfgcnevjyvwzwuzrwvlomnlogbptornsybimbtnyhlmfecscmojrxekqmj"));
    }

    private String getMaxSubStringForDP(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];
        int maxLength = 0;
        int index = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        index = i;
                    }
                }
            }
        }

        return s1.substring(index - maxLength, index);
    }

    private String getMaxSubString(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        String result = "";
        for (int i = 0; i < chars1.length; i++) {
            char char1 = chars1[i];
            for (int j = 0; j < chars2.length; j++) {
                char char2 = chars2[j];
                if (char1 != char2) {
                    continue;
                }

                int index1 = i;
                int index2 = j;
                while (index1 < chars1.length && index2 < chars2.length && chars1[index1] == chars2[index2]) {
                    index1++;
                    index2++;
                }

                String substring = s1.substring(i, index1);
                if (substring.length() > result.length()) {
                    result = substring;
                }
            }
        }

        return result;
    }

    private String getMaxSubString2(String s1, String s2) {
        String longgerString = s1.length() > s2.length() ? s1 : s2;
        String shorterString = s1.length() > s2.length() ? s2 : s1;

        char[] chars1 = shorterString.toCharArray();
        List<String> subStringSet = new ArrayList<>();
        for (int i = 0; i < chars1.length; i++) {
            for (int j = i + 1; j <= chars1.length; j++) {
                subStringSet.add(shorterString.substring(i, j));
            }

        }

        String result = "";
        for (String s : subStringSet) {
            if (longgerString.contains(s) && s.length() > result.length()) {
                result = s;
            }
        }

        return result;
    }

    @Test
    public void testGetMP3Cursor() {
        getMP3Cursor(10, "UUUU");
        getMP3Cursor(1, "UUUU");
        getMP3Cursor(5, "DDDD");
    }

    public void getMP3Cursor(int number, String path) {
        // 当前光标位置
        int cursor = 1;
        // 起始窗口位置
        int up = 1, down = 4;
        for (char c : path.toCharArray()) {
            if (c == 'U') {
                // 特殊向上翻页，需要更新当前光标和窗口位置
                if (cursor == 1) {
                    down = number;
                    // up最小取到起始位置1
                    up = Math.max(down - 3, 1);
                    cursor = down;
                } else {
                    cursor--;
                }
            }

            if (c == 'D') {
                // 特殊向下翻页，需要更新当前光标和窗口位置
                if (cursor == number) {
                    up = 1;
                    // down最大取到最后位置number
                    down = Math.min(up + 3, number);
                    cursor = 1;
                } else {
                    cursor++;
                }
            }

            // 更新窗口
            if (cursor < up) {
                up--;
                down--;
            }

            // 更新窗口
            if (cursor > down) {
                up++;
                down++;
            }
        }

        for (int i = up; i <= down; i++) {
            if (i == down) {
                System.out.print(i);
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();
        System.out.println(cursor);
    }


    @Test
    public void testGetMaxGCRatioSubString() {
        System.out.println(getMaxGCRatioSubString2("ACGT", 2));
        System.out.println(getMaxGCRatioSubString2("AACTGTGCACGACCTGA", 5));
        System.out.println(getMaxGCRatioSubString2("CCCAAGTCTTCCAATCGTGCCCCCCAATTGAGTCTCGCTCCCCAGGTGAGATACATCAGAAGC", 63));
    }

    public String getMaxGCRatioSubString2(String str, int n) {
        int count = 0;
        // 遍历第一个窗口
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == 'C' || c == 'G') {
                count++;
            }
        }

        // 最大值初始化为第一个窗口
        int max = count;
        String result = str.substring(0, n);
        // 遍历每个窗口
        for (int i = 1, j = i + n; j <= str.length(); i++, j++) {
            char pre = str.charAt(i - 1);
            char next = str.charAt(j - 1);
            // 窗口左边出去的是CG
            if (pre == 'C' || pre == 'G') {
                count--;
            }
            // 窗口右边进来的是CG
            if (next == 'C' || next == 'G') {
                count++;
            }
            // 更新最大值
            if (count > max) {
                max = count;
                result = str.substring(i, j);
            }
        }

        return result;
    }

    public String getMaxGCRatioSubString(String str, int n) {
        double max = -1;
        String result = "";
        for (int i = 0, j = i + n; j <= str.length(); i++, j++) {
            String sub = str.substring(i, j);
            double ratio = calculateGCRatio(sub);
            if (ratio > max) {
                max = ratio;
                result = sub;
            }
        }

        return result;
    }

    private double calculateGCRatio(String s) {
        char c = 'C';
        char g = 'G';
        int count = 0;
        for (char c1 : s.toCharArray()) {
            if (c1 == c || c1 == g) {
                count++;
            }
        }

        return (count * 1.0) / s.length();
    }


    @Test
    public void testGetCountFromBinary() {
        System.out.println(getCountFromBinary(5));
        System.out.println(getCountFromBinary(8));
        System.out.println(Integer.toBinaryString(5));
    }

    public int getCountFromBinary(int number) {
        int count = 0;
        while (number > 0) {
            count += number & 1;
            number >>= 1;
        }

        return count;
    }

    public int getApple(int m, int n) {
        if (m == 0 || n == 1) {
            return 1;
        }

        if (n > m) {
            return getApple(m, m);
        }

        return getApple(m - n, n) + getApple(m, n - 1);
    }


    @Test
    public void testGetPrimeNumber() {
        printArr(getPrimeNumber(20));
        printArr(getPrimeNumber(4));
        printArr(getPrimeNumber(8));
    }

    public int[] getPrimeNumber(int number) {
        int left = number / 2;
        int right = number / 2;

        while (left > 0 && right < number) {
            if (isPrime(left) && isPrime(right)) {
                return new int[]{left, right};
            }

            left--;
            right++;
        }

        return null;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testGetOnlyOnceChar() {

        System.out.println(getOnlyOnceChar("asdfasdfo"));
        System.out.println(getOnlyOnceChar("asdfasdfa"));
    }


    public Character getOnlyOnceChar(String s) {
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            if (map.get(c) == 1) {
                return c;
            }
        }

        return null;
    }

    @Test
    public void testGetLeastK() {
        String str1 = "2163 7241 7053 8161 2847 5214 60 1386 4089 2448 3747 9573 3113 6155 7659 713";
        String[] s = str1.split(" ");
        List<Integer> integerList = Arrays.stream(s).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> leastK = getLeastK2(integerList, 8);
        String collect = leastK.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(collect);
    }

    public List<Integer> getLeastK2(List<Integer> list, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (Integer integer : list) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(integer);
            } else {
                priorityQueue.offer(integer);
                priorityQueue.poll();
            }
        }

        List<Integer> resultList = new ArrayList<>(k);
        while (!priorityQueue.isEmpty()) {
            resultList.add(priorityQueue.poll());
        }

        Collections.reverse(resultList);
        return resultList;
    }


    public List<Integer> getLeastK(List<Integer> list, int k) {
        list.sort(Comparator.naturalOrder());
        List<Integer> resultList = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            resultList.add(list.get(i));
        }

        return resultList;
    }


    @Test
    void testGetSum() {
        System.out.println(getSum("9876543210", "1234567890"));
        System.out.println(getSum2("9876543210", "1234567890"));

        System.out.println(getSum("123456765432", "3458998765432"));
        System.out.println(getSum2("123456765432", "3458998765432"));
    }


    public String getSum2(String str1, String str2) {
        // 翻转
        List<Integer> list1 = revers(str1);
        List<Integer> list2 = revers(str2);

        int len1 = list1.size();
        int len2 = list2.size();

        int carryBit = 0;
        StringBuilder sb = new StringBuilder();
        int length = len1 > len2 ? len1 : len2;
        for (int i = 0; i < length; i++) {
            // 如果超出则取0
            int value1 = getValue(list1, i);
            int value2 = getValue(list2, i);
            int sum = value1 + value2 + carryBit;
            if (sum < 10) {
                sb.append(sum);
                carryBit = 0;
            } else {
                sb.append(sum % 10);
                carryBit = sum / 10;
            }
        }

        // 如果最后还有进位，需要添加进位
        if (carryBit != 0) {
            sb.append(carryBit);
        }

        // 翻转结果
        sb.reverse();
        return sb.toString();
    }

    private int getValue(List<Integer> list, int index) {
        if (index >= list.size()) {
            return 0;
        }

        return list.get(index);
    }

    private List<Integer> revers(String str) {
        char[] chars = str.toCharArray();
        List<Integer> list = new ArrayList<>(chars.length);

        for (int i = chars.length - 1; i >= 0; i--) {
            list.add(chars[i] - '0');
        }

        return list;
    }

    public String getSum(String str1, String str2) {
        BigInteger bigInteger1 = new BigInteger(str1);
        BigInteger bigInteger2 = new BigInteger(str2);

        bigInteger1 = bigInteger1.add(bigInteger2);
        return bigInteger1.toString();
    }


    @Test
    public void testGetPerfectNumber() {
        System.out.println(getPerfectNumber(1000));
    }

    public int getPerfectNumber(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isPerfectNumber(i)) {
                count++;
            }
        }

        return count;
    }

    private boolean isPerfectNumber(int n) {
        if (n <= 1) {
            return false;
        }

        int sum = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum = sum + i + n / i;
            }
        }

        return sum == 2 * n;
    }


    @Test
    public void testGet7() {
        System.out.println(get7(30000));

    }


    public int get7(int n) {
        if (n < 7) {
            return 0;
        }

        int count = 0;
        for (int i = 7; i <= n; i++) {
            boolean matched = ((i % 7) == 0 || contain7(i));
            if (matched) {
                count++;
            }
        }

        return count;
    }

    private boolean contain7(int i) {
        while (i > 0) {
            int n = i % 10;
            if (n == 7) {
                return true;
            }

            i = i / 10;
        }
        return false;
    }


    @Test
    @SneakyThrows
    public void testExpressCalculate() {
        System.out.println(expressCalculate("400+5"));
        System.out.println(expressCalculate("400+(5*10)"));
        System.out.println(expressCalculate("400+(-5*10)"));
        System.out.println(expressCalculate("3+2*{1+2*[-4/(8-6)+7]}"));

        System.out.println(expressCalculate2("400+5"));
        System.out.println(expressCalculate2("400+(5*10)"));
        System.out.println(expressCalculate2("400+(-5*10)"));
        System.out.println(expressCalculate2("3+2*{1+2*[-4/(8-6)+7]}"));


        // ScriptEngineManager
    }

    public int expressCalculate2(String s) throws Exception {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 替换括号
        s = s.replaceAll("\\[", "(");
        s = s.replaceAll("\\{", "(");
        s = s.replaceAll("\\]", ")");
        s = s.replaceAll("\\}", ")");

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
        return (int) scriptEngine.eval(s);
    }

    public int expressCalculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 替换括号
        s = s.replaceAll("\\[", "(");
        s = s.replaceAll("\\{", "(");
        s = s.replaceAll("\\]", ")");
        s = s.replaceAll("\\}", ")");

        // 加一个小括号在最外层
        if (s.charAt(0) != '(') {
            s = "(" + s + ")";
        }

        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        boolean flag = false;
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                // 遇到右括号就计算
                while (operatorStack.peek() != '(') {
                    calculateAndPush(numberStack, operatorStack);
                }
                operatorStack.pop();
            } else if (flag) {
                // 运算符，如果栈顶的运算符优先级高就需要先计算
                while (priority(operatorStack.peek(), c)) {
                    calculateAndPush(numberStack, operatorStack);
                }
                operatorStack.push(c);
                // 运算符结束，后面的+,-就是正负号
                flag = false;
            } else {
                // 数字
                int j = i;
                // 正负号
                if (c == '-' || c == '+') {
                    i++;
                }

                while (Character.isDigit(s.charAt(i))) {
                    i++;
                }

                // 转换数字
                int number = Integer.valueOf(s.substring(j, i));
                numberStack.push(number);
                // 数字结束，后面+,-就是运算符
                flag = true;
                // for 循环后面i++，所以这里需要先减一
                i--;
            }
        }

        return numberStack.pop();
    }

    private boolean priority(char stackOperator, char currentOperator) {
        if (stackOperator == '(') {
            return false;
        }

        if ((stackOperator == '+' || stackOperator == '-') && (currentOperator == '*' || currentOperator == '/')) {
            return false;
        }

        return true;
    }

    private void calculateAndPush(Stack<Integer> numberStack, Stack<Character> operatorStack) {
        // 注意：栈的顺序是反的，这里先出栈的是参数二
        int value2 = numberStack.pop();
        int value1 = numberStack.pop();
        char operator = operatorStack.pop();

        int result = 0;
        switch (operator) {
            case '+':
                result = value1 + value2;
                break;
            case '-':
                result = value1 - value2;
                break;
            case '*':
                result = value1 * value2;
                break;
            case '/':
                result = value1 / value2;
                break;
        }

        numberStack.push(result);
    }


    @Test
    public void testTriangle() {
        System.out.println(triangle(2));
        System.out.println(triangle(3));
        System.out.println(triangle(4));
    }

    public int triangle(int n) {
        if (n <= 2) {
            return -1;
        }

        int[] arr = new int[]{2, 3, 2, 4};
        return arr[(n + 1) % 4];
    }

    private int getValue(int i, int j, int[][] arr) {
        if (i > arr.length - 1 || i < 0) {
            return 0;
        }

        if (j > arr[0].length - 1 || j < 0) {
            return 0;
        }

        return arr[i][j];
    }


    @Test
    public void testGetDistance() {
        System.out.println(getDistance("abcdefg", "abcdef"));
        System.out.println(getDistance("abcdefg", "abcde"));
        System.out.println(getDistance("ucyfsmg", "zuixhuhyjgksyhqkjqxwylkoubykjxtcvkyqjpzgltbemmbmqibxxqpkgbvwbmjotixanvciibubglizmumcrjavakiygyuv"));
    }

    public int getDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < m + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[n][m];
    }

    @Test
    public void testGetLastNode() {
        String s = "1 2 3 4 5 6 7 8";
        String[] split = s.split(" ");
        List<Integer> list = Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(getLastKNode2(list, 4));

    }

    public int getLastKNode2(List<Integer> list, int k) {
        ListNode temp = new ListNode();
        ListNode slow = null;
        for (int i = 0; i < list.size(); i++) {
            ListNode listNode = new ListNode(list.get(i));
            temp.next = listNode;
            temp = listNode;
            if (i == 0) {
                slow = listNode;
            }
            if (i > k - 1) {
                slow = slow.next;
            }
        }

        return slow.value;
    }

    public int getLastKNode(List<Integer> list, int k) {
        int length = 0;
        ListNode temp = new ListNode();
        ListNode head = temp;

        for (Integer value : list) {
            ListNode listNode = new ListNode(value);
            temp.next = listNode;
            temp = listNode;
            length++;
        }

        int index = length - k;
        head = head.next;
        while (index > 0) {
            head = head.next;
            index--;
        }

        return head.value;
    }


    @Test
    public void testCalculateExpression() {
//        System.out.println(calculateExpression("3+2*{1+2*[-4/(8-6)+7]}"));
        System.out.println(calculateExpression("3*5+8-0*3-6+0+0"));
    }

    public int calculateExpression(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '[') {
                chars[i] = '(';
            }

            if (chars[i] == '}' || chars[i] == ']') {
                chars[i] = ')';
            }
        }

        string = new String(chars);
        return recursionForCalculate(string);
    }

    private int recursionForCalculate(String string) {
        int lastIndex = string.lastIndexOf('(');
        int firstIndex = string.indexOf(')');
        if (lastIndex == -1) {
            return calculate(string);
        }

        int value = calculate(string.substring(lastIndex + 1, firstIndex));
        string = string.substring(0, lastIndex) + value + string.substring(firstIndex + 1);
        return recursionForCalculate(string);
    }

    public int calculate(String string) {
        LinkedList<String> charList = new LinkedList<>();
        LinkedList<Integer> numberList = new LinkedList<>();

        boolean needCalculate = false;

        char[] chars = string.toCharArray();
        String[] strings = new String[chars.length];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) {
                strings[index++] = String.valueOf(chars[i]);
            } else {
                String temp = "";
                while (i < chars.length && Character.isDigit(chars[i])) {
                    temp += chars[i];
                    i++;
                }
                i--;
                strings[index++] = temp;
            }
        }

        for (int i = 0; i < index; i++) {
            String temp = strings[i];
            if (temp.equals("-") && (i == 0 || !isDigit(strings[i - 1]))) {
                numberList.addLast(-Integer.parseInt(strings[i + 1]));
                i = i + 1;
                continue;
            }
            if (isDigit(temp)) {
                numberList.addLast(Integer.parseInt(temp));
                if (needCalculate) {
                    calculateAndPush(numberList, charList);
                    needCalculate = false;
                }
            } else {
                if (temp.equals("*") || temp.equals("/")) {
                    needCalculate = true;
                }
                charList.addLast(temp);
            }
        }

        while (!charList.isEmpty()) {
            calculateAndPush(numberList, charList);
        }

        return numberList.getFirst();
    }

    private boolean isDigit(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void calculateAndPush(LinkedList<Integer> numberList, LinkedList<String> charList) {
        Integer value2 = numberList.removeLast();
        Integer value1 = numberList.removeLast();
        int value = calculate(value1, value2, charList.removeLast());
        numberList.addLast(value);
    }


    private int calculate(int value1, int value2, String c) {
        switch (c) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
        }

        return 0;
    }

    private boolean isLeftChar(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private boolean isRightChar(char c) {
        return c == ')' || c == '}' || c == ']';
    }


    @Test
    public void testRemoveFromLinked() {
        System.out.println(removeFromLinked("6 2 1 2 3 2 5 1 4 5 7 2 2"));
        System.out.println(removeFromLinked("5 2 3 2 4 3 5 2 1 4 3"));
        System.out.println(removeFromLinked("1 3 3"));
    }

    public String removeFromLinked(String string) {
        LinkedList<String> linkedList = new LinkedList<>();
        String[] strings = string.split(" ");
        String firstNode = strings[1];

        linkedList.addFirst(firstNode);
        for (int i = 2; i < strings.length - 1; ) {
            String value1 = strings[i];
            String value2 = strings[i + 1];
            instLinked(linkedList, value1, value2);
            i = i + 2;
        }

        String deleteValue = strings[strings.length - 1];
        linkedList.removeIf(integer -> integer.equals(deleteValue));

        return String.join(" ", linkedList);
    }

    private void instLinked(LinkedList<String> linkedList, String value1, String value2) {
        int i;
        for (i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(value2)) {
                break;
            }
        }

        linkedList.add(i + 1, value1);
    }


    @Test
    public void testSubstring() {
        System.out.println(substring("abABCcDEF", 6));
        System.out.println(substring("bdxPKBhih", 6));
    }

    public String substring(String string, int length) {
        char[] newString = new char[length];
        for (int i = 0; i < string.toCharArray().length && i < length; i++) {
            newString[i] = string.toCharArray()[i];
        }

        return new String(newString);
    }


    @Test
    public void testGetBeautiful() {
        System.out.println(getBeautifulCount("zhangsan"));
        System.out.println(getBeautifulCount("lisi"));
        System.out.println(getBeautifulCount("lIsi"));
    }


    public int getBeautifulCount(String string) {
        Map<Character, Integer> map = new HashMap<>(string.length());
        for (char c : string.toCharArray()) {
            char temp = Character.toLowerCase(c);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        List<Integer> valueList = map.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int result = 0, step = 26;
        for (Integer value : valueList) {
            result += value * step;
            step--;
        }

        return result;
    }


    // 字符集合
    // 数独

    @Test
    public void testSortByScore() {
        List<String> scoreList = new ArrayList<>();
        scoreList.add("jack 70");
        scoreList.add("peter 96");
        scoreList.add("Tom 70");
        scoreList.add("smith 67");

        sortByScore(scoreList, 1);

    }

    public void sortByScore(List<String> scoreList, int sortType) {
        scoreList.sort((s1, s2) -> {
            int score1 = Integer.parseInt(s1.split(" ")[1]);
            int score2 = Integer.parseInt(s2.split(" ")[1]);
            if (sortType == 0) {
                return score2 - score1;
            } else {
                return score1 - score2;
            }
        });

        scoreList.forEach(System.out::println);
    }


    @Test
    public void testReversString() {
        System.out.println(reversString("I am a student"));
    }

    public String reversString(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }

    public void sortByType(List<Integer> list, int sortType) {
        if (sortType == 0) {
            list.sort(Comparator.naturalOrder());
        } else {
            list.sort(Comparator.reverseOrder());
        }

        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    @Test
    public void testJumpFloor() {
        System.out.println(jumpFloor(2));
        System.out.println(jumpFloor(7));
    }

    public int jumpFloor(int target) {
        int a1 = 1, a2 = 1, a3 = 1;
        for (int i = 2; i <= target; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    @Test
    public void testTwoSum() {
        int[] numbers = new int[]{20, 70, 110, 150};
        int target = 90;

        int[] ints = twoSum(numbers, target);
        printArr(ints);

    }

    public void printArr(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> numberToIndexMap = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            Integer index = numberToIndexMap.get(target - numbers[i]);
            if (index != null) {
                return new int[]{index, i + 1};
            }

            numberToIndexMap.put(numbers[i], i + 1);
        }

        return null;
    }


    @Test
    public void testGetWeightCount() {

        System.out.println(getWeightCount(new ArrayList<>(Arrays.asList(1, 1, 2))));
        System.out.println(getWeightCount2(new ArrayList<>(Arrays.asList(1, 1, 2))));
    }

    public int getWeightCount2(List<Integer> weightList) {
        Set<Integer> weightSet = new HashSet<>();
        weightSet.add(0);

        for (Integer integer : weightList) {
            Set<Integer> currentSet = new HashSet<>(weightSet);
            for (Integer integer1 : currentSet) {
                weightSet.add(integer + integer1);
            }
        }

        return weightSet.size();
    }

    public int getWeightCount(List<Integer> weightList) {
        Set<Integer> weightSet = new HashSet<>();
        weightSet.add(0);

        List<Integer> pathList = new ArrayList<>();
        int[] visited = new int[weightList.size() + 1];
        weightList.sort(Comparator.naturalOrder());
        for (int i = 1; i <= weightList.size(); i++) {
            dfsToGetWeightCount(i, weightList, weightSet, visited, pathList);
        }

        return weightSet.size();
    }

    private void dfsToGetWeightCount(int count, List<Integer> weightList, Set<Integer> weightSet, int[] visited, List<Integer> pathList) {
        if (pathList.size() == count) {
            Optional<Integer> reduce = pathList.stream().reduce(Integer::sum);
            weightSet.add(reduce.get());
            return;
        }

        for (int i = 0; i < weightList.size(); i++) {
            if (i != 0 && Objects.equals(weightList.get(i), weightList.get(i - 1)) && visited[i - 1] == 1) {
                continue;
            }

            if (visited[i] == 1) {
                continue;
            }

            visited[i] = 1;
            pathList.add(weightList.get(i));
            dfsToGetWeightCount(count, weightList, weightSet, visited, pathList);
            visited[i] = 0;
            pathList.remove(pathList.size() - 1);
        }
    }


    @Test
    public void testCountChar2() {

        countChar("1qazxsw23 edcvfr45tgbn hy67uj m,ki89ol.\\\\/;p0-=\\\\][");

    }

    public void countChar(String string) {
        int letterCount = 0;
        int whitespaceCount = 0;
        int digitCount = 0;
        int otherCount = 0;

        for (char c : string.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
                continue;
            }

            if (Character.isWhitespace(c)) {
                whitespaceCount++;
                continue;
            }

            if (Character.isDigit(c)) {
                digitCount++;
                continue;
            }

            otherCount++;
        }

        System.out.println(letterCount);
        System.out.println(whitespaceCount);
        System.out.println(digitCount);
        System.out.println(otherCount);

    }

    @Test
    public void testGetBallHeight() {
        Map<String, Double> map = getBallHeight(1000);
        System.out.println(map.get("sum"));
        System.out.println(map.get("height"));
    }

    public Map<String, Double> getBallHeight(int initHeight) {
        double sum = 0;
        double height = initHeight;
        int index = 0;
        while (true) {
            sum += height;
            height /= 2.0;
            index++;
            if (index == 5) {
                break;
            }
            sum += height;
        }

        Map<String, Double> map = new HashMap<>();
        map.put("sum", sum);
        map.put("height", height);

        return map;
    }


    @Test
    public void testGetRabbitCount() {
        System.out.println(getRabbitCount(3));
        System.out.println(getRabbitCount(5));
        System.out.println(getRabbitCount(6));
    }

    public int getRabbitCount(int n) {
        int a1 = 1, a2 = 1, a3 = a1 + a2;
        for (int i = 3; i <= n; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }

        return a3;
    }

    @Test
    public void testPrintTriangle() {
        printTriangle(4);
        printTriangle(5);
    }


    public void printTriangle(int n) {
        int i = 0, j = 0, value = 1;
        int[][] arr = new int[101][101];

        arr[i][j] = value++;
        while (true) {
            i++;
            j = 0;
            if (i > n) {
                break;
            }

            arr[i][j] = value++;
            int temp = i;
            while (temp >= 1) {
                arr[--temp][++j] = value++;
            }
        }

        for (int x = 0; x < n; x++) {
            int maxIndex = n - x;
            for (int y = 0; y < maxIndex; y++) {
                if (y != maxIndex - 1) {
                    System.out.print(arr[x][y] + " ");
                } else {
                    System.out.print(arr[x][y]);
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testSortByAscii() {

        System.out.println(sortByAscii("Ihave1nose2hands10fingers"));

    }

    public String sortByAscii(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    public void testBinary() {

        String s = "00001100";
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int n = s.charAt(i) - '0';
            result = result * 2 + n;
        }

        System.out.println(result);
        System.out.println(toNumberByBinary(s));
    }

    @Test
    public void testConvertIpFromNumber() {
        System.out.println(convertIpFromNumber(3868643487l));
        System.out.println(numberToIp(3868643487l));
    }

    @Test
    public void testCovertNumberFromIp() {
        System.out.println(convertNumberFromIp("39.66.68.72"));
        System.out.println(ipToNumber("39.66.68.72"));
    }


    public String numberToIp(long number) {
        String[] resultArr = new String[4];
        for (int i = 3; i >= 0; i--) {
            resultArr[i] = String.valueOf(number % 256);
            number /= 256;
        }

        return String.join(".", resultArr);
    }

    public long ipToNumber(String ip) {
        long result = 0;
        String[] split = ip.split("\\.");
        for (String s : split) {
            result = result * 256 + Integer.parseInt(s);
        }

        return result;
    }

    public String convertIpFromNumber(long number) {
        StringBuilder binary = new StringBuilder(toBinary(String.valueOf(number)));
        while (binary.length() < 32) {
            binary.insert(0, "0");
        }

        int step = 8;
        int left = 0;
        int right = 0;
        String binaryString = binary.toString();
        List<String> resultList = new ArrayList<>();
        while (right <= binaryString.length() - 1) {
            left = right;
            right += step;
            String substring = binaryString.substring(left, right);
            resultList.add(String.valueOf(toNumberByBinary(substring)));
        }

        return String.join(".", resultList);
    }

    public long convertNumberFromIp(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = s.split("\\.");
        for (String s1 : split) {
            stringBuilder.append(toBinary(s1));
        }

        return toNumberByBinary(stringBuilder.toString());
    }

    public long toNumberByBinary(String string) {
        long result = 0;
        int index = 0;
        char[] chars = string.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            int n = chars[i] - '0';
            result += n * Math.pow(2, index++);
        }

        return result;
    }

    public String toBinary(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        long n = Long.parseLong(number);
        while (n != 0) {
            stringBuilder.append(n % 2);
            n /= 2;
        }

        if (stringBuilder.length() < 8) {
            while (stringBuilder.length() != 8) {
                stringBuilder.append("0");
            }
        }

        return stringBuilder.reverse().toString();
    }

    @Test
    public void testWordSort() {

        System.out.println(wordSort("I am a student"));
        System.out.println(wordSort("$bo*y gi!r#l"));

    }

    public String wordSort(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isLetter(chars[i])) {
                chars[i] = ' ';
            }
        }

        String convertString = new String(chars);
        String[] strings = convertString.split(" ");
        List<String> list = new ArrayList<>(strings.length);
        for (String string : strings) {
            if (string.length() > 0 && Character.isLetter(string.charAt(0))) {
                list.add(string);
            }
        }

        Collections.reverse(list);
        return String.join(" ", list);
    }


    @Test
    public void testSortString2() {
        System.out.println(sortString("Type"));
        System.out.println(sortString("BabA"));
        System.out.println(sortString("By?e"));
        System.out.println(sortString("A Famous Saying: Much Ado About Nothing (2012/8)."));


    }


    public String sortString(String s) {
        char[] chars = s.toCharArray();
        List<Character> letterList = new ArrayList<>();

        for (char c : chars) {
            if (isLetter(c)) {
                letterList.add(c);
            }
        }

        letterList = sort(letterList);
        for (int i = 0; i < chars.length; i++) {
            if (!isLetter(chars[i])) {
                letterList.add(i, chars[i]);
            }
        }

        int index = 0;
        char[] values = new char[letterList.size()];
        for (Character character : letterList) {
            values[index++] = character;
        }

        return new String(values);
    }

    public List<Character> sort(List<Character> characterList) {
        Character[] toArray = characterList.toArray(new Character[]{});
        for (int i = 0; i < toArray.length; i++) {
            for (int j = 0; j < toArray.length - i - 1; j++) {
                if (getCode(toArray[j]) > getCode(toArray[j + 1])) {
                    Character temp = toArray[j + 1];
                    toArray[j + 1] = toArray[j];
                    toArray[j] = temp;
                }
            }
        }

        return new ArrayList<>(Arrays.asList(toArray));
    }

    public int getCode(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }

        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }

        return c;
    }

    public boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }

        if (c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;
    }


    @Test
    public void testDeleteLeastChar() {
//        System.out.println(deleteLeastChar("aabcddd"));
        System.out.println(deleteLeastChar("assssa"));


    }

    public String deleteLeastChar(String s) {
        int minCount = 21;
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (Integer value : charCountMap.values()) {
            minCount = Math.min(minCount, value);

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            Integer count = charCountMap.get(c);
            if (count == minCount) {
                continue;
            }

            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }


    @Test
    public void testMove() {

        System.out.println(move("A10;S20;W10;D30;X;A1A;B10A11;;A10;"));
        System.out.println(move("ABC;AKL;DA1;"));
    }


    public String move(String s) {
        String[] split = s.split(";");
        int x = 0, y = 0;
        for (String s1 : split) {
            boolean valid = valid(s1);
            if (!valid) {
                continue;
            }
            String direction = getChar(s1);
            int number = getNumber(s1);
            switch (direction) {
                case "A":
                    x -= number;
                    break;
                case "D":
                    x += number;
                    break;
                case "W":
                    y += number;
                    break;
                case "S":
                    y -= number;
                    break;
            }
        }

        return String.format("%s,%s", x, y);
    }

    public int getNumber(String s) {
        return Integer.parseInt(s.substring(1));
    }

    public String getChar(String s) {
        return s.substring(0, 1);
    }

    public boolean valid(String s) {
        if (!(s.length() == 3 || s.length() == 2)) {
            return false;
        }

        String temp = "ADWS";
        char[] chars = s.toCharArray();
        if (!temp.contains(String.valueOf(chars[0]))) {
            return false;
        }

        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testGet1CountFromBinary() {
        System.out.println(get1CountFromBinary(5));
        System.out.println(get1CountFromBinary(4));
        System.out.println(get1CountFromBinary(0));
    }

    public int get1CountFromBinary(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                count++;
            }
            num /= 2;
        }
        return count;
    }


    @Test
    public void testSortString() {
        List<String> list = new ArrayList<>();
        list.add("cap");
        list.add("to");
        list.add("cat");
        list.add("card");
        list.add("two");
        list.add("too");
        list.add("up");
        list.add("boat");
        list.add("boot");

//        sortString(list);
        list.sort(stringComparator());
        list.stream().forEach(System.out::println);
    }

    public Comparator<String> stringComparator() {
        return (s1, s2) -> {
            int len1 = s1.length();
            int len2 = s2.length();
            int minLength = len1 > len2 ? len2 : len1;

            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            int index = 0;
            while (index < minLength) {
                char char1 = c1[index];
                char char2 = c2[index];
                if (char1 != char2) {
                    return char1 - char2;
                }

                index++;
            }

            return len1 - len2;
        };
    }

    public void sortString(List<String> list) {
        list.sort(Comparator.naturalOrder());
    }

    @Test
    public void testGetReversedOrderString() {
        System.out.println(getReversedOrderString("I am a boy"));
        System.out.println(getReversedOrderString("nowcoder"));
    }

    public String getReversedOrderString(String s) {
        String[] strings = s.split(" ");
        for (int i = 0, j = strings.length - 1; i <= j; i++, j--) {
            String temp = strings[i];
            strings[i] = strings[j];
            strings[j] = temp;
        }


        return Stream.of(strings).collect(Collectors.joining(" "));
    }

    @Test
    public void testReversedOrderNumber() {
        reversedOrderNumber(0);
    }

    public void reversedOrderNumber(int n) {
        if (n == 0) {
            System.out.println(n);
        }

        while (n != 0) {
            System.out.print(n % 10);
            n /= 10;
        }

        System.out.println();
    }

    @Test
    public void testGetDifferentCharCount() {
        System.out.println(getDifferentCharCount("abc"));
        System.out.println(getDifferentCharCount("aaa"));
    }

    public int getDifferentCharCount(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet<>(s.length());
        for (char c : s.toCharArray()) {
            set.add(c);
        }

        return set.size();
    }


    @Test
    public void testMerge2() {
        List<String> list = new ArrayList<>();
        list.add("0 1");
        list.add("0 2");
        list.add("1 2");
        list.add("3 4");

        Map<Integer, Integer> integerMap = merge2(list);
        List<Integer> keyList = integerMap.keySet().stream().sorted().collect(Collectors.toList());
        for (Integer key : keyList) {
            System.out.println(key + " " + integerMap.get(key));
        }
    }

    @Test
    public void testMerge() {
        List<String> list = new ArrayList<>();
        list.add("0 1");
        list.add("0 2");
        list.add("1 2");
        list.add("3 4");

        int[] merge = merge(list);
        for (int i = 0; i < merge.length; i++) {
            if (merge[i] != 0) {
                System.out.println(i + " " + merge[i]);
            }
        }
    }

    public Map<Integer, Integer> merge2(List<String> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String s : list) {
            String[] split = s.split(" ");
            int index = Integer.parseInt(split[0]);
            int value = Integer.parseInt(split[1]);
            map.put(index, map.getOrDefault(index, 0) + value);
        }

        return map;
    }

    public int[] merge(List<String> list) {
        int[] table = new int[11111113];
        for (String s : list) {
            String[] split = s.split(" ");
            int index = Integer.parseInt(split[0]);
            int value = Integer.parseInt(split[1]);
            table[index] += value;
        }

        return table;
    }

    @Test
    public void testPrimeFactor() {
        int n = 2000000014;
        List<Integer> integerList = primeFactor(n);
        System.out.println(integerList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public List<Integer> primeFactor(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                list.add(i);
                n /= i;
            }
        }

        if (n != 1) {
            list.add(n);
        }
        return list;
    }

    @Test
    public void testConverterHexadecimal() {
        String string = "0x5A3";
        System.out.println(converterHexadecimal2(string));

    }

    public int converterHexadecimal2(String string) {
        if (string.startsWith("0x") || string.startsWith("0X")) {
            string = string.substring(2);
        }

        return Integer.parseInt(string, 16);
    }

    public int converterHexadecimal(String string) {
        if (string.startsWith("0x") || string.startsWith("0X")) {
            string = string.substring(2);
        }


        int index = 0;
        int result = 0;

        for (int i = string.length() - 1; i >= 0; i--) {
            result += getInt((string.charAt(i))) * Math.pow(16, index++);
        }
        return result;
    }

    public int getInt(char a) {
        if (a >= 'a' && a <= 'z') {
            return a - 'a' + 10;
        }

        if (a >= 'A' && a <= 'Z') {
            return a - 'A' + 10;
        }

        return a - '0';
    }


    @Test
    public void testSplitString() {
        String string = "abcefghijk";
        for (String s : splitString2(string)) {
            System.out.println(s);
        }
    }

    public List<String> splitString2(String string) {
        if (string == null || string.length() == 0) {
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>();
        while (string.length() >= 8) {
            resultList.add(string.substring(0, 8));
            string = string.substring(8);
        }

        if (string.length() > 0) {
            string += "0000000";
            resultList.add(string.substring(0, 8));
        }
        return resultList;
    }

    public List<String> splitString(String string) {
        if (string == null || string.length() == 0) {
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            stringBuilder.append(string.charAt(i));
            if ((i + 1) % 8 == 0) {
                resultList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }

        int length = stringBuilder.length();
        if (length > 0) {
            for (int i = 0; i < 8 - length; i++) {
                stringBuilder.append(0);
            }
            resultList.add(stringBuilder.toString());
        }
        return resultList;
    }


    @Test
    public void testSOdaBottle() {
        System.out.println(sodaBottle(3));
        System.out.println(sodaBottle(10));
        System.out.println(sodaBottle(81));
    }

    public int sodaBottle(int n) {
        if (n < 2) {
            return 0;
        }

        int count = 0;
        int sodaBottleCount = n;

        while (sodaBottleCount / 3 > 0) {
            int conversionCount = sodaBottleCount / 3;
            sodaBottleCount = sodaBottleCount % 3 + conversionCount;
            count += conversionCount;
        }

        // 手上还剩两个瓶子，向老板借一个瓶，兑换后再还给老板
        if (sodaBottleCount == 2) {
            count++;
        }

        return count;
    }


    @Test
    public void testConverterPassword() {
        System.out.println(converterPassword2("YUANzhi1987"));
    }

    public String converterPassword2(String oldPassword) {
        String a = "ABCDEFGHIJKlMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String b = "bcdefghijklmnopqrstuvwxyza22233344455566677778889999";

        char[] chars = oldPassword.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = b.charAt(chars[i] - 'A');
            } else if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = b.charAt(chars[i] - 'a' + 26);
            }

        }

        return new String(chars);
    }

    public String converterPassword(String oldPassword) {
        Map<String, Character> map = initMap();

        char[] chars = oldPassword.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = map.get(chars[i] + "");
            } else if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = getIndexChar(chars[i], map);
            }

        }

        return new String(chars);
    }


    public Character getIndexChar(Character c, Map<String, Character> map) {
        for (Map.Entry<String, Character> entry : map.entrySet()) {
            String key = entry.getKey();
            Character value = entry.getValue();

            if (key.contains(c + "")) {
                return value;
            }
        }

        return null;
    }

    public Map<String, Character> initMap() {
        Map<String, Character> map = new HashMap<>();
        map.put("abc", '2');
        map.put("def", '3');
        map.put("ghi", '4');
        map.put("jkl", '5');
        map.put("mno", '6');
        map.put("pqrs", '7');
        map.put("tuv", '8');
        map.put("wxyz", '9');

        for (char a = 'A'; a <= 'Z'; a++) {
            if (a == 'Z') {
                map.put(a + "", 'a');
            } else {
                char c = (char) (a + 33);
                map.put(a + "", c);
            }
        }
        return map;
    }


    @Test
    public void testInterceptPassword() {
        System.out.println(interceptPassword("ABBA"));
        System.out.println(interceptPassword("ABBBA"));
        System.out.println(interceptPassword("12HHHHA"));
        System.out.println(interceptPassword("vvgogaewginhopuxzwyryobjjpieyhwfopiowxmyylvcgsnhfcnogpqpukzmnpewavoutbloyrrhatimmxfqmcwgfebuoqbbgvubbkjfvxivjfijlpvtsnhagzfptahhyojwzamayoiegkenycnkxzkambimhdykdcxyyfjnvztzypmfczdhhnkmfuvgkhzbwmjznykkagqdrueohgcmeidjqsvfugcioeduohprjtfdmtzosnhoiganffarokxjifzzxhixdzycwfheqqegduzwtiacmdhqfmxhazcxsqyrvrihfqzjxvawdeandnwgjlquvzadruiqmcsgibglhicsvzqknztqpkiihdoisxipkourentfvrltieihiktgzswtgcmmlfrjifqinhrbplbsgswqlbjkyxjwoshsvxlhlpgzwbmxzwaemtowcxwourjwmmwjruowxcwotmeawzxmbwzgplhlxvshsowjxykjblqwsgsblpbrhniqfijrflmmcgtwszgtkihieitlrvftneruokpixsiodhiikpqtznkqzvscihlgbigscmqiurdazvuqljgwndnaedwavxjzqfhirvryqsxczahxmfqhdmcaitwzudgeqqehfwcyzdxihxzzfijxkoraffnagiohnsoztmdftjrphoudeoicgufvsqjdiemcghoeurdqgakkynzjmwbzhkgvufmknhhdzcfmpyztzvnjfyyxcdkydhmibmakzxkncynekgeioyamazwjoyhhatpfzgahnstvpljifjvixvfjkbbuvgbbqoubefgwcmqfxmmitahrryolbtuovawepnmzkupqpgoncfhnsgcvlyymxwoipofwhyeipjjboyrywzxupohnigweagogvv"));
    }

    public int interceptPassword(String password) {
        int maxLength = -100;
        for (int i = 0; i < password.length(); i++) {
            for (int j = i + 1; j <= password.length(); j++) {
                if (isValid(password.substring(i, j))) {
                    maxLength = Math.max(maxLength, (j - i));
                }
            }
        }

        return maxLength;
    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < chars.length; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testEncrypt() {
        System.out.println(encrypt("abcdefg"));
        System.out.println(decrypt("BCDEFGH"));
    }

    public String encrypt(String string) {
        int index = 0;
        char[] chars = new char[string.length()];
        for (char ch : string.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'z') {
                    chars[index++] = 'A';
                } else {
                    char c = Character.toUpperCase(ch);
                    chars[index++] = plus(c, 1);
                }
            }

            if (ch >= 'A' && ch <= 'Z') {
                if (ch == 'Z') {
                    chars[index++] = 'a';
                } else {
                    char c = Character.toLowerCase(ch);
                    chars[index++] = plus(c, 1);
                }
            }

            if (ch >= '0' && ch <= '9') {
                if (ch == '9') {
                    chars[index++] = '0';
                } else {
                    chars[index++] = plus(ch, 1);
                }
            }
        }

        return new String(chars);
    }

    private char plus(char ch, int k) {
        int value = ch;
        value = value + k;
        return (char) value;
    }

    public String decrypt(String string) {
        int index = 0;
        char[] chars = new char[string.length()];
        for (char ch : string.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a') {
                    chars[index++] = 'Z';
                } else {
                    char c = Character.toUpperCase(ch);
                    chars[index++] = plus(c, -1);
                }
            }

            if (ch >= 'A' && ch <= 'Z') {
                if (ch == 'A') {
                    chars[index++] = 'z';
                } else {
                    char c = Character.toLowerCase(ch);
                    chars[index++] = plus(c, -1);
                }
            }

            if (ch >= '0' && ch <= '9') {
                if (ch == '0') {
                    chars[index++] = '9';
                } else {
                    chars[index++] = plus(ch, -1);
                }
            }
        }

        return new String(chars);
    }

    @Test
    public void testGetBroWord() {

        System.out.println(getBroWord(Arrays.asList("cab ad abcd cba abc bca".split(" ")), "abc", 1));

    }

    public Map<String, String> getBroWord(List<String> stringList, String searchString, int k) {
        List<String> resultList = new ArrayList<>();
        for (String s : stringList) {
            if (isBroWord(s, searchString)) {
                resultList.add(s);
            }
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("count", resultList.size() + "");
        if (resultList.size() >= k) {
            resultList.sort(Comparator.naturalOrder());
            resultMap.put("result", resultList.get(k - 1));

        }

        return resultMap;
    }

    private boolean isBroWord(String string1, String string2) {
        if (string1.equals(string2)) {
            return false;
        }

        Map<Character, Integer> map1 = getCharCountMap(string1);
        Map<Character, Integer> map2 = getCharCountMap(string2);

        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();
            if (map2.getOrDefault(ch, 0) != count) {
                return false;
            }
        }

        return true;
    }

    private Map<Character, Integer> getCharCountMap(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : string.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;
    }


    @Test
    public void testValidPassword() {
        System.out.println(validPassword("021Abc9000"));
        System.out.println(validPassword("021Abc9Abc1"));
        System.out.println(validPassword("021ABC9000"));
        System.out.println(validPassword("021$bc9000"));
        System.out.println(validPassword("%Z7"));
    }

    public boolean validPassword(String password) {
        if (password.length() <= 8) {
            return false;
        }

        char[] chars = password.toCharArray();
        int exitUppercase = 0;
        int exitLowercase = 0;
        int exitNumber = 0;
        int exitOther = 0;
        boolean success = false;

        for (char aChar : chars) {
            if (isUppercase(aChar)) {
                exitUppercase = 1;
            }
            if (isLowercase(aChar)) {
                exitLowercase = 1;
            }
            if (isNumber(aChar)) {
                exitNumber = 1;
            }
            if (isOther(aChar)) {
                exitOther = 1;
            }

            if (exitUppercase + exitLowercase + exitNumber + exitOther >= 3) {
                success = true;
            }
        }

        if (!success) {
            return false;
        }


        for (int i = 0; i < chars.length - 3; i++) {
            int j = i + 3;
            String subString = password.substring(i, j);
            if (password.indexOf(subString) != password.lastIndexOf(subString)) {
                return false;
            }
        }

        return true;
    }

    public boolean isUppercase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public boolean isLowercase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isOther(char ch) {
        return !isUppercase(ch) && !isLowercase(ch) && !isNumber(ch) && ch != ' ' && ch != '\n';
    }

    @Test
    public void testCountChar() {
        Set<Integer> set = new HashSet<>();
        System.out.println(countChar("ABCabc", 'A'));
    }

    public int countChar(String string, char searchChar) {
        int count = 0;
        int searchCharCode = getCharCode(searchChar);
        for (char c : string.toCharArray()) {
            if (getCharCode(c) == searchCharCode) {
                count++;
            }
        }

        return count;
    }

    public int getCharCode(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a';
        }

        return ch - 'A';
    }


    @Test
    public void testGetLastWordLength() {

        System.out.println(getLastWordLength("hello nowcoder"));
        System.out.println(getLastWordLength("nowcoder"));

    }

    public int getLastWordLength(String string) {
        if (string == null || string.length() == 0) {
            return 0;
        }

        String[] wordArr = string.split(" ");
        return wordArr[wordArr.length - 1].length();
    }
}
