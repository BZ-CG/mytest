package cn.edu.pzhu.mytest.leetcode.top150;

/**
 * @author zhangcz
 * @since 20240910
 */
public class IntToRoman {
//    TreeMap<Integer, String> map = new TreeMap<Integer, String>(Comparator.reverseOrder()) {{
//        put(1, "I");
//        put(5, "V");
//        put(10, "X");
//        put(50, "L");
//        put(100, "C");
//        put(500, "D");
//        put(1000, "M");
//    }};
//
//
//    Map<Integer, String> map2 = new HashMap<Integer, String>() {{
//        put(4, "IV");
//        put(9, "IX");
//        put(40, "XL");
//        put(90, "XC");
//        put(400, "CD");
//        put(900, "CM");
//    }};
//
//    public boolean isBegin4Or9(int n) {
//        int mod = 0;
//        while (n > 0) {
//            mod = n % 10;
//            n /= 10;
//        }
//        return mod == 4 || mod == 9;
//    }
//
//    public String intToRoman(int num) {
//        Stack<String> stack = new Stack<>();
//        int step = 10;
//        while (num > 0) {
//            int mod = num % step;
//            if (isBegin4Or9(mod)) {
//                stack.push(map2.get(mod));
//            } else {
//                int temp = mod;
//                StringBuilder sb = new StringBuilder();
//                Set<Map.Entry<Integer, String>> entries = map.entrySet();
//                for (Map.Entry<Integer, String> entry : entries) {
//                    if (temp <= 0) {
//                        break;
//                    }
//
//                    Integer number = entry.getKey();
//                    String roman = entry.getValue();
//
//                    while (temp >= number) {
//                        sb.append(roman);
//                        temp -= number;
//                    }
//                }
//                stack.push(sb.toString());
//            }
//
//            num -= mod;
//            step *= 10;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        while (!stack.isEmpty()) {
//            sb.append(stack.pop());
//        }
//
//        return sb.toString();
//    }


    int[] valueArr = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbolArr = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        int n = valueArr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int number = valueArr[i];
            String symbol = symbolArr[i];

            while (num >= number) {
                sb.append(symbol);
                num -= number;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntToRoman intToRoman = new IntToRoman();
        System.out.println(intToRoman.intToRoman(3749));
        System.out.println(intToRoman.intToRoman(58));
        System.out.println(intToRoman.intToRoman(1994));
    }
}
