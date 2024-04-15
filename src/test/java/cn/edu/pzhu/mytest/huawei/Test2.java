package cn.edu.pzhu.mytest.huawei;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * TEST1
 *
 * @author zhangcz
 * @since 20230627
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Map<String, String> map = getMap(line);
        System.out.printf("%s %s\n", map.get("flag"), map.get("result"));
    }


    private static Map<String, String> getMap(String line) {
        String[] split = line.split(";");
        double[][] arr1 = new double[3][5];

        for (int i = 0; i < 3; i++) {
            String[] data = split[i].split(",");
            for (int j = 0; j < 5; j++) {
                arr1[i][j] = Double.parseDouble(data[j]);
            }
        }

        int[] arr2 = new int[5];
        String[] strings = split[3].split(",");
        for (int i = 0; i < strings.length; i++) {
            arr2[i] = Integer.parseInt(strings[i]);
        }

        double[] arr3 = new double[3];
        String[] strings1 = split[4].split(",");
        for (int i = 0; i < strings1.length; i++) {
            arr3[i] = Double.parseDouble(strings1[i]);
        }

        String[] arr4 = split[5].split(",");

        BigDecimal maxValue = BigDecimal.valueOf(Long.MIN_VALUE);
        boolean flag = true;

        for (int i = 0; i < 3; i++) {
            BigDecimal result = BigDecimal.ZERO;
//            double result = 0;
            for (int j = 0; j < 5; j++) {
                BigDecimal number = BigDecimal.valueOf(arr1[i][j]);
                result = result.add(number.multiply(new BigDecimal(arr2[j])));
            }

            result = result.add(BigDecimal.valueOf(-arr3[i]));
            maxValue = result.max(maxValue);
            flag = flag && (getFlag(result.longValue(), arr3[i], arr4[i]));
        }

        Map<String, String> map = new HashMap<>();
        map.put("flag", String.valueOf(flag));
        long value = (long) maxValue.longValue();
        map.put("result", String.valueOf(value));

        return map;
    }

    private static boolean getFlag(long d1, double d2, String achar) {
        boolean flag = true;
        switch (achar) {
            case ">":
                flag = d1 > d2;
                break;
            case ">=":
                flag = d1 >= d2;
                break;
            case "<":
                flag = d1 < d2;
                break;
            case "<=":
                flag = d1 <= d2;
                break;
            case "=":
                flag = (d1 == d2);
                break;
            default:
                flag = false;
        }

        return flag;
    }
}
