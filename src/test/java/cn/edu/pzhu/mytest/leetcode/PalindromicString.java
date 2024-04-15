package cn.edu.pzhu.mytest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回文串
 *
 * @author zhangcz
 * @since 20220228
 */
public class PalindromicString {

    private List<List<String>> resultList = new ArrayList<>();
    private LinkedList<String> list = new LinkedList<>();

    public List<List<String>> partition(String s) {
        dfsForSplit(s, 0);

//        Iterator<List<String>> iterator = resultList.iterator();
//        while (iterator.hasNext()) {
//            List<String> next = iterator.next();
//            boolean isPalindromic = true;
//            for (int i = 0; i < next.size() && isPalindromic; i++) {
//                isPalindromic = isPalindromic(next.get(i));
//            }
//
//            if (!isPalindromic) {
//                iterator.remove();
//            }
//        }

        return resultList;
    }

    private void dfsForSplit(String s, int start) {
        if (s == null || s.length() == 0) {
            resultList.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String prefix = s.substring(start, i + 1);
            if (!isPalindromic(prefix)) {
                continue;
            }

            list.add(prefix);
            dfsForSplit(s.substring(i + 1), start);
            list.removeLast();
        }
    }

    private boolean isPalindromic(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }
        return true;
    }


    public static void main(String[] args) {
        PalindromicString palindromicString = new PalindromicString();
        List<List<String>> listList = palindromicString.partition("aab");

        for (List<String> list : listList) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    /**
     * aab
     * a ab
     * a a b
     * aa b
     * aab
     */
}
