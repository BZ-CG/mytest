package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class RomanToInt {

//    Map<String, Integer> map = new HashMap<String, Integer>() {{
//        put("I", 1);
//        put("V", 5);
//        put("X", 10);
//        put("L", 50);
//        put("C", 100);
//        put("D", 500);
//        put("M", 1000);
//    }};
//
//
//    public int romanToInt(String s) {
//        int sum = 0;
//        int n = s.length();
//        char[] charArray = s.toCharArray();
//        for (int i = 0; i < n; i++) {
//            int value = map.get(String.valueOf(charArray[i]));
//            if (i < n - 1 && value < map.get(String.valueOf(charArray[i+1]))) {
//                sum -= value;
//            } else {
//                sum += value;
//            }
//
//
//        }
//
//        return sum;
//    }

    int[] arr = new int[26];

    {
        arr[getIndex('I')] = 1;
        arr[getIndex('V')] = 5;
        arr[getIndex('X')] = 10;
        arr[getIndex('L')] = 50;
        arr[getIndex('C')] = 100;
        arr[getIndex('D')] = 500;
        arr[getIndex('M')] = 1000;
    }

    public int getIndex(char c) {
        return c - 'A';
    }

    public int romanToInt(String s) {
        int sum = 0;
        int n = s.length();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int value = arr[getIndex(charArray[i])];
            if (i < n - 1 && value < arr[getIndex(charArray[i + 1])]) {
                sum -= value;
            } else {
                sum += value;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        System.out.println(romanToInt.romanToInt("III"));
        System.out.println(romanToInt.romanToInt("IV"));
        System.out.println(romanToInt.romanToInt("IX"));
        System.out.println(romanToInt.romanToInt("LVIII"));
        System.out.println(romanToInt.romanToInt("MCMXCIV"));
        System.out.println(romanToInt.romanToInt("XLIX"));
        System.out.println(romanToInt.romanToInt("CMXCIX"));

    }

}
