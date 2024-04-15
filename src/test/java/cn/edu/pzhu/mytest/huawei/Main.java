package cn.edu.pzhu.mytest.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static List<String> resultList = new ArrayList<>();
    private static LinkedList<String> pathList = new LinkedList<>();
    private static int[] visited = new int[30];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(getMaxNumber(line));
    }


    private static String getMaxNumber2(String line) {
        if (line == null || line.length() == 0) {
            return "0";
        }

        String[] strings = line.split(",");
        if (strings.length == 0) {
            return "0";
        }

        dfsForGetMaxNumber(strings);
        resultList.sort(Comparator.reverseOrder());
        return resultList.get(0);
    }

    private static void dfsForGetMaxNumber(String[] strings) {
        if (pathList.size() == strings.length) {
            String collect = pathList.stream().collect(Collectors.joining());
            resultList.add(collect);
            return;
        }

        for (int i = 0; i < strings.length - 1; i++) {
            if (visited[i] == 1 || strings[i].compareTo(strings[i + 1]) < 0) {
                continue;
            }

            visited[i] = 1;
            pathList.add(strings[i]);
            dfsForGetMaxNumber(strings);
            visited[i] = 0;
            pathList.removeLast();
        }
    }


    private static String getMaxNumber(String line) {
        if (line == null || line.length() == 0) {
            return "0";
        }

        String[] strings = line.split(",");
        if (strings.length == 0) {
            return "0";
        }

        Arrays.sort(strings, (s1, s2) -> {
            if (s1.startsWith(s2)) {
                int len1 = s1.length();
                int len2 = s2.length();
                int num1 = s1.charAt(len1 - 1);
                int num2 = s2.charAt(len2 - 1);
                return num1 - num2;
            }

            if (s2.startsWith(s1)) {
                int len1 = s1.length();
                int len2 = s2.length();
                int num1 = s1.charAt(len1 - 1);
                int num2 = s2.charAt(len2 - 1);
                return num2 - num1;
            }

            return s1.compareTo(s2);
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            stringBuilder.append(strings[i]);
        }

        return stringBuilder.toString();
    }
}