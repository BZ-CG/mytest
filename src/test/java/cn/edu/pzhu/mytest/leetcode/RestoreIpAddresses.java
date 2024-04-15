package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 复原 IP 地址
 *
 * @author zhangcz
 * @since 20220228
 */
public class RestoreIpAddresses {

    private List<List<String>> splitList = new ArrayList<>();
    private LinkedList<String> list = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        dfsForSplit(s, 0);
        List<String> resultList = new ArrayList<>();
        for (List<String> stringList : splitList) {
            if (stringList.size() != 4) {
                continue;
            }

            boolean matched = true;
            for (int i = 0; i < stringList.size() && matched; i++) {
                matched = isMatched(stringList.get(i));
            }
            if (matched) {
                resultList.add(String.join(".", stringList));
            }
        }

        return resultList;
    }

    private boolean isMatched(String s) {
        if (s.length() != 1 && s.startsWith("0")) {
            return false;
        }

        if (s.length() > 3) {
            return false;
        }

        if (Integer.parseInt(s) > 255) {
            return false;
        }

        return true;
    }

    private void dfsForSplit(String s, int start) {
        if (s == null || s.length() == 0) {
            splitList.add(new ArrayList<>(list));
            return;
        }

        // 剪枝
        if (list.size() > 4) {
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String prefix = s.substring(start, i + 1);
            // 剪枝
            if (!isMatched(prefix)) {
                continue;
            }

            list.add(prefix);
            dfsForSplit(s.substring(i + 1), start);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();
        List<String> list = restoreIpAddresses.restoreIpAddresses("101023");
        list.stream().forEach(System.out::println);
    }

}
