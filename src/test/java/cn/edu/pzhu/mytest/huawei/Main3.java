package cn.edu.pzhu.mytest.huawei;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 3
 *
 * @author zhangcz
 * @since 20230627
 */
public class Main3 {
    private static List<BigInteger> resultList = new ArrayList<>();
    private static LinkedList<String> pathList = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(getMaxNumber(line));
    }

    private static String getMaxNumber(String line) {
        if (line == null || line.length() == 0) {
            return "0";
        }

        String[] strings = line.split(" ");
        if (strings.length == 0) {
            return "0";
        }

        int[] visited = new int[strings.length];
        dfsForGetMaxNumber(strings, visited);
        resultList.sort(Comparator.reverseOrder());
        return resultList.get(0).toString();
    }

    private static void dfsForGetMaxNumber(String[] strings, int[] visited) {
        if (pathList.size() == strings.length) {
            String collect = String.join("", pathList);
            try {
                BigInteger bigInteger = new BigInteger(collect);
                resultList.add(bigInteger);
            } catch (Exception e) {

            }

            return;
        }

        for (int i = 0; i < strings.length; i++) {
            if (visited[i] == 1) {
                continue;
            }

            visited[i] = 1;
            pathList.add(strings[i]);
            dfsForGetMaxNumber(strings, visited);
            visited[i] = 0;
            pathList.removeLast();
        }
    }
}
