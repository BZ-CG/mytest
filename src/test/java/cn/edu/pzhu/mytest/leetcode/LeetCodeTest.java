package cn.edu.pzhu.mytest.leetcode;

import cn.edu.pzhu.mytest.bean.MyLinkedList;
import cn.edu.pzhu.mytest.leetcode.model.TreeNode;
import cn.edu.pzhu.mytest.util.ResultUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * leetcode
 *
 * @author zhangcz
 * @since 20201028
 */
@SpringBootTest
public class LeetCodeTest {

    @Test
    public void testDecodeString() {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString("abc3[cd]xyz"));

        System.out.println();

        System.out.println(decodeString2("3[a]2[bc]"));
        System.out.println(decodeString2("3[a2[c]]"));
        System.out.println(decodeString2("2[abc]3[cd]ef"));
        System.out.println(decodeString2("abc3[cd]xyz"));
    }

    public String decodeString2(String s) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> stringStack = new Stack<>();

        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ) {
            if (Character.isDigit(charArray[i])) {
                int start = i;
                while (Character.isDigit(charArray[i])) {
                    i++;
                }

                numberStack.push(Integer.parseInt(s.substring(start, i)));
                continue;
            }

            if (Character.isLetter(charArray[i]) || charArray[i] == '[') {
                stringStack.push(charArray[i]);
                i++;
                continue;
            }

            String substring = "";
            while (stringStack.peek() != '[') {
                substring = stringStack.pop() + substring;
            }

            String newString = "";
            int number = numberStack.pop();
            while (number-- > 0) {
                newString += substring;
            }

            stringStack.pop();
            for (char c : newString.toCharArray()) {
                stringStack.push(c);
            }
            i++;
        }

        String result = "";
        while (!stringStack.isEmpty()) {
            result = stringStack.pop() + result;
        }

        return result;
    }

    public String decodeString(String s) {
        int lastIndexOf = s.lastIndexOf("[");
        if (lastIndexOf == -1) {
            return s;
        }

        int numberIndex = lastIndexOf - 1;
        while (numberIndex >= 0 && Character.isDigit(s.toCharArray()[numberIndex])) {
            numberIndex--;
        }

        int number = Integer.parseInt(s.substring(numberIndex + 1, lastIndexOf));
        int substringIndex = lastIndexOf + 1;
        while (substringIndex < s.length() && Character.isLetter(s.toCharArray()[substringIndex])) {
            substringIndex++;
        }

        String substring = s.substring(lastIndexOf + 1, substringIndex);
        String newString = s.substring(0, numberIndex + 1);
        for (int i = 0; i < number; i++) {
            newString += substring;
        }

        newString += s.substring(substringIndex + 1);
        return decodeString(newString);
    }

    @Test
    public void testFindMin() {
        System.out.println(findMin(new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
        System.out.println(findMin(new int[] { 11, 13, 15, 17 }));
        System.out.println(findMin(new int[] { 2, 1 }));

        System.out.println();

        System.out.println(findMin2(new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(findMin2(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
        System.out.println(findMin2(new int[] { 11, 13, 15, 17 }));
        System.out.println(findMin2(new int[] { 2, 1 }));
    }

    public int findMin2(int[] nums) {
        int mid;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    public int findMin(int[] nums) {
        return getMinByBinarySearch(nums, 0, nums.length - 1);
    }

    private int getMinByBinarySearch(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        if (nums[left] < nums[right]) {
            return nums[left];
        }

        int mid = left + ((right - left) >> 1);
        if (nums[left] <= nums[mid]) {
            return Math.min(getMinByBinarySearch(nums, left, mid), getMinByBinarySearch(nums, Math.min(mid + 1, right), right));
        }

        return Math.min(getMinByBinarySearch(nums, left, Math.max(mid - 1, left)), getMinByBinarySearch(nums, mid, right));
    }

    @Test
    public void testFindMedianSortedArrays() {
        System.out.println(findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }));
        System.out.println(findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        List<Integer> list = new ArrayList<>(m + n);
        int index1 = 0, index2 = 0;
        while (index1 < m && index2 < n) {
            if (nums1[index1] <= nums2[index2]) {
                list.add(nums1[index1]);
                index1++;
            } else {
                list.add(nums2[index2]);
                index2++;
            }
        }

        while (index1 < m) {
            list.add(nums1[index1]);
            index1++;
        }

        while (index2 < n) {
            list.add(nums2[index2]);
            index2++;
        }

        if (((m + n) & 1) == 1) {
            return list.get((m + n) / 2);
        }

        return (list.get((m + n) / 2 - 1) + list.get((m + n) / 2)) / 2.0;
    }

    @Test
    public void testSearch() {
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 5));
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));
        System.out.println(search(new int[] { 1 }, 3));
        System.out.println(search(new int[] { 3, 1 }, 1));
    }

    public int search(int[] nums, int target) {
        int mid;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    @Test
    public void testSearchRange() {
        int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
        ResultUtils.printArr(searchRange(nums, 8));
        ResultUtils.printArr(searchRange(nums, 6));
        ResultUtils.printArr(searchRange(new int[] {}, 0));

        System.out.println();

        ResultUtils.printArr(searchRange2(nums, 8));
        ResultUtils.printArr(searchRange2(nums, 6));
        ResultUtils.printArr(searchRange2(new int[] {}, 0));

    }

    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }

        int mid;
        int left = 0, right = nums.length - 1;
        int minLeft = -1;
        int maxRight = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                minLeft = mid;
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                maxRight = mid;
                left = mid + 1;
            }
        }

        return new int[] { minLeft, maxRight };
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] { -1, -1 };
        }

        int mid;
        int left = 0, right = nums.length - 1, result = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                result = mid;
                break;
            }
        }

        if (result == -1) {
            return new int[] { -1, -1 };
        }

        int start = result;
        while (start >= 0) {
            if (nums[start] != target) {
                break;
            }
            start--;
        }

        int end = result;
        while (end < nums.length) {
            if (nums[end] != target) {
                break;
            }
            end++;
        }

        return new int[] { start + 1, end - 1 };
    }

    @Test
    public void testSearchMatrix2() {
        int[][] matrix = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
        System.out.println(searchMatrix2(matrix, 3));
        System.out.println(searchMatrix2(matrix, 13));
        System.out.println(searchMatrix2(new int[][] { { 1 } }, 0));
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = binaryInColumn(matrix, target);
        return binaryInRow(matrix[row], target);
    }

    private boolean binaryInRow(int[] matrix, int target) {
        int mid;
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (matrix[mid] > target) {
                right = mid - 1;
            } else if (matrix[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    private int binaryInColumn(int[][] matrix, int target) {
        int mid;
        int left = 0, right = matrix.length - 1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else if (matrix[mid][0] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return Math.max(left - 1, 0);
    }

    @Test
    public void testSearchInsert2() {
        int[] nums = new int[] { 1, 3, 5, 6 };
        System.out.println(searchInsert2(nums, 5));
        System.out.println(searchInsert2(nums, 2));
        System.out.println(searchInsert2(nums, 7));
        System.out.println(searchInsert2(nums, -1));
    }

    public int searchInsert2(int[] nums, int target) {
        int mid;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (target > nums[left]) {
            return left + 1;
        }

        return left;
    }

    @Test
    public void testDominantIndex() {

        int[] arr = new int[] { 3, 6, 1, 0 };
        System.out.println(dominantIndex(arr));

        arr = new int[] { 1, 2, 3, 4 };
        System.out.println(dominantIndex(arr));

    }

    public int dominantIndex(int[] nums) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        for (int num : nums) {
            if (num == max) {
                continue;
            }
            if (max < 2 * num) {
                return -1;
            }
        }

        return index;
    }

    @Test
    public void testSubsets2() {
        int[] nums = new int[] { 1, 2, 3 };
        Subsets subsets = new Subsets();
        List<List<Integer>> list = subsets.subsets(nums);
        printListList(list);
    }

    @Test
    public void testPermute2() {
        int[] nums = new int[] { 1, 2, 3 };
        Permute permute = new Permute();
        List<List<Integer>> list = permute.permute(nums);
        printListList(list);
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count = Math.max(count, dfsForMaxAreaOfIsland(grid, i, j));
                }

            }
        }

        return count;
    }

    private int dfsForMaxAreaOfIsland(int[][] grid, int row, int column) {
        int m = grid.length;
        int n = grid[0].length;
        if (row < 0 || column < 0 || row >= m || column >= n) {
            return 0;
        }

        if (grid[row][column] != 1) {
            return 0;
        }

        grid[row][column] = 2;
        int count = 0;
        count += dfsForMaxAreaOfIsland(grid, row - 1, column);
        count += dfsForMaxAreaOfIsland(grid, row + 1, column);
        count += dfsForMaxAreaOfIsland(grid, row, column - 1);
        count += dfsForMaxAreaOfIsland(grid, row, column + 1);
        return count + 1;
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsForNumLands(grid, i, j);
                }

            }
        }

        return count;
    }

    private void dfsForNumLands(char[][] grid, int row, int column) {
        int m = grid.length;
        int n = grid[0].length;
        if (row < 0 || column < 0 || row >= m || column >= n || grid[row][column] == '0') {
            return;
        }

        grid[row][column] = '0';
        dfsForNumLands(grid, row - 1, column);
        dfsForNumLands(grid, row + 1, column);
        dfsForNumLands(grid, row, column - 1);
        dfsForNumLands(grid, row, column + 1);
    }

    public void flatten(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        preOrder(root, dummy);
        root.right = dummy.right.right;
    }

    private void preOrder(TreeNode root, TreeNode newRoot) {
        if (root == null) {
            return;
        }

        newRoot.right = new TreeNode(root.val);
        preOrder(root.left, newRoot.right);
        preOrder(root.right, newRoot.right);
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                TreeNode poll = queue.poll();
                if (i == size) {
                    list.add(poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }

        return list;
    }

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> queue = new LinkedList<>();
        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.push(root);
                root = root.left;
            }

            root = queue.poll();
            k--;
            if (k == 0) {
                break;
            }

            root = root.right;
        }
        return root.val;
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderForValidBST(root, list);

        for (int i = 1; i < list.size(); i++) {
            int pre = list.get(i - 1);
            int current = list.get(i);
            if (pre >= current) {
                return false;
            }
        }

        return true;
    }

    private void inorderForValidBST(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderForValidBST(root.left, list);
        list.add(root.val);
        inorderForValidBST(root.right, list);
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        return dfsBuildBST(nums, 0, nums.length - 1);
    }

    private TreeNode dfsBuildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        if (left == right) {
            return new TreeNode(nums[left]);
        }

        int mid = left + ((right - left) >> 1);
        TreeNode l = dfsBuildBST(nums, left, mid - 1);
        TreeNode r = dfsBuildBST(nums, mid + 1, right);
        return new TreeNode(nums[mid], l, r);
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }

                list.add(poll.val);
                size--;
            }

            resultList.add(list);
        }

        return resultList;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            StringBuilder sb = new StringBuilder();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }

                if (poll.right != null) {
                    queue.add(poll.right);
                }

                sb.append(poll.val);
                size--;
            }

            if (!sb.toString().equals(sb.reverse().toString())) {
                return false;
            }
        }

        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetricTree(root, root);
    }

    public boolean isSymmetricTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isSymmetricTree(left.left, right.right) && isSymmetricTree(left.right, right.left);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public int maxDepth2(TreeNode root) {
        Set<Integer> depthSet = new HashSet<>();
        dfsForDepth(root, 1, depthSet);
        int max = 0;
        for (Integer integer : depthSet) {
            max = Math.max(integer, max);
        }
        return max;
    }

    private void dfsForDepth(TreeNode root, int depth, Set<Integer> depthSet) {
        if (root == null) {
            return;
        }

        depthSet.add(depth);
        dfsForDepth(root.left, depth + 1, depthSet);
        dfsForDepth(root.right, depth + 1, depthSet);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    @Test
    public void testSortList() {
        ListNode node = sortList(buildListNode(new int[] { 4, 2, 1, 3 }));
        System.out.println(node);
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;

        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        list.sort(Comparator.comparing(node -> node.val));

        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).next = list.get(i);
        }

        list.get(list.size() - 1).next = null;
        return list.get(0);
    }

    public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node pre = dummy;
        Node temp = head;

        Map<Node, Node> oldToNewNodeMap = new HashMap<>();
        while (temp != null) {
            Node node = new Node(temp.val);
            oldToNewNodeMap.put(temp, node);

            pre.next = node;
            pre = node;
            temp = temp.next;
        }

        temp = head;
        pre = dummy.next;
        while (temp != null) {
            pre.random = oldToNewNodeMap.get(temp.random);
            temp = temp.next;
            pre = pre.next;
        }

        return dummy.next;
    }

    class Node {

        int val;

        Node next;

        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    @Test
    public void testSwapPairs4() {
        ListNode listNode = buildListNode(new int[] { 1, 2, 3, 4, 5 });
        ListNode head = swapPairs4(listNode);
        System.out.println(head);
    }

    public ListNode swapPairs4(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode two = head.next;
            ListNode three = two.next;

            pre.next = two;
            two.next = head;
            head.next = three;

            pre = head;
            head = three;
        }

        return dummy.next;
    }

    @Test
    public void testReverseKGroup() {
        ListNode listNode = buildListNode(new int[] { 1, 2, 3, 4, 5 });
        ListNode head = reverseKGroup(listNode, 2);
        System.out.println(head);

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }

            ListNode next = tail.next;
            reverseNode(head, tail);

            pre.next = tail;
            head.next = next;

            pre = head;
            head = head.next;
        }

        return dummy.next;
    }

    private void reverseNode(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode end = tail.next;
        while (head != end) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    private void swapNode(List<ListNode> list, int slow, int fast) {
        while (slow <= fast) {
            ListNode temp = list.get(slow);
            list.set(slow, list.get(fast));
            list.set(fast, temp);
            slow++;
            fast--;
        }
    }

    @Test
    public void testSwapPairs3() {
        ListNode node = buildListNode(new int[] { 1, 2, 3, 4 });
        ListNode head = swapPairs3(node);
        System.out.println(head);
    }

    public ListNode swapPairs3(ListNode head) {
        if (head == null) {
            return null;
        }

        int index = 0;
        ListNode[] arr = new ListNode[101];
        while (head != null) {
            arr[index++] = head;
            head = head.next;
        }

        for (int i = 0, j = i + 1; j < index; i = i + 2, j = j + 2) {
            ListNode temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        for (int i = 1; i < index; i++) {
            arr[i - 1].next = arr[i];
        }

        arr[index - 1].next = null;
        return arr[0];
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;

        int length = 0;
        int count = n + 1;
        while (fast != null) {
            if (count <= 0) {
                slow = slow.next;
            }
            fast = fast.next;
            count--;
            length++;

        }

        if (length == n) {
            return head.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode pre = head;

        int round = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + round;
            round = value / 10;

            pre.next = new ListNode(value % 10);
            pre = pre.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int value = l1.val + round;
            round = value / 10;

            pre.next = new ListNode(value % 10);
            pre = pre.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int value = l2.val + round;
            round = value / 10;

            pre.next = new ListNode(value % 10);
            pre = pre.next;
            l2 = l2.next;
        }

        if (round != 0) {
            pre.next = new ListNode(round);
        }

        return head.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    private void insertInLast(ListNode head, ListNode node) {
        ListNode pre = null;
        ListNode temp = head;
        while (temp != null) {
            pre = temp;
            temp = temp.next;
        }
        pre.next = node;
    }

    public ListNode detectCycle3(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }

            set.add(head);
            head = head.next;
        }

        return null;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;

        }

        return false;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        for (int i = 0, j = list.size() - 1; i <= j; i++, j--) {
            if (!Objects.equals(list.get(i), list.get(j))) {
                return false;
            }
        }

        return true;
    }

    public ListNode reverseList4(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;

        while (newHeadA != newHeadB) {
            newHeadA = newHeadA == null ? newHeadB : newHeadA.next;
            newHeadB = newHeadB == null ? newHeadA : newHeadB.next;
        }

        return newHeadA;
    }

    @Test
    public void testSearchMatrix() {
        //        int[][] matrix = new int[][] {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}};
        //        System.out.println(searchMatrix(matrix, 5));

        int[][] matrix = new int[][] { { 1, 3, 5, 7, 9 }, { 2, 4, 6, 8, 10 }, { 11, 13, 15, 17, 19 }, { 12, 14, 16, 18, 20 }, { 21, 22, 23, 24, 25 } };
        System.out.println(searchMatrix(matrix, 13));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }

            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }

    @Test
    public void testRotate() {
        int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotate(matrix);
        ResultUtils.printArr(matrix);

    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int temp;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    @Test
    public void testSpiralOrder() {
        //        int[][] matrix = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        //        int[][] matrix = new int[][] {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        int[][] matrix = new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 } };
        List<Integer> integerList = spiralOrder(matrix);
        System.out.println(integerList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int round = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        while (list.size() != m * n) {
            for (int j = round; j < n - round; j++) {
                list.add(matrix[round][j]);
            }

            if (list.size() == m * n) {
                break;
            }
            for (int i = round + 1; i < m - round; i++) {
                list.add(matrix[i][n - round - 1]);
            }

            if (list.size() == m * n) {
                break;
            }

            for (int j = n - round - 2; j >= round; j--) {
                list.add(matrix[m - round - 1][j]);
            }

            if (list.size() == m * n) {
                break;
            }
            for (int i = m - round - 2; i > round; i--) {
                list.add(matrix[i][round]);
            }

            round++;
        }

        return list;
    }

    @Test
    public void testSetZeroes() {
        //        int[][] matrix = new int[][] {{1,1,1}, {1,0,1}, {1,1,1}};
        int[][] matrix = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        setZeroes(matrix);
        ResultUtils.printArr(matrix);
    }

    public void setZeroes(int[][] matrix) {
        Set<Integer> existZeroInRowIndexSet = new HashSet<>(matrix.length);
        Set<Integer> existZeroInColumnIndexSet = new HashSet<>(matrix[0].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    existZeroInRowIndexSet.add(i);
                    existZeroInColumnIndexSet.add(j);
                }
            }
        }

        for (Integer i : existZeroInRowIndexSet) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }

        for (Integer i : existZeroInColumnIndexSet) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = 0;
            }
        }
    }

    @Test
    public void testMaxSlidingWindow2() {
        int[] arr = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        ResultUtils.printArr(maxSlidingWindow2(arr, 3));

        arr = new int[] { 1 };
        ResultUtils.printArr(maxSlidingWindow2(arr, 1));

    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        List<Integer> resultList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            valueList.add(nums[i]);
        }

        Integer maxValue = getMaxValue(valueList);
        resultList.add(maxValue);
        for (int i = k; i < nums.length; i++) {
            int needRemoveValue = valueList.get(0);
            int needAddValue = nums[i];

            valueList.remove(0);
            valueList.add(needAddValue);
            if (needRemoveValue == maxValue) {
                maxValue = getMaxValue(valueList);
            } else {
                maxValue = needAddValue > maxValue ? needAddValue : maxValue;
            }

            resultList.add(maxValue);
        }

        int[] arr = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            arr[i] = resultList.get(i);
        }

        return arr;
    }

    private Integer getMaxValue(List<Integer> valueList) {
        int max = valueList.get(0);
        for (int i = 1; i < valueList.size(); i++) {
            int value = valueList.get(i);
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }

            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }


    @Test
    public void testFindAnagrams() {
        ResultUtils.printList(findAnagrams("cbaebabacd", "abc"));
        ResultUtils.printList(findAnagrams("abab", "ab"));
        ResultUtils.printList(findAnagrams("af", "be"));
        ResultUtils.printList(findAnagrams("baa", "aa"));

    }

    public List<Integer> findAnagrams(String s, String p) {
        int windowLength = p.length();
        List<Integer> resultList = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i <= charArray.length - windowLength; i++) {
            int end = i + windowLength;

            if (isAnagrams(s.substring(i, end), p)) {
                resultList.add(i);
            }
        }

        return resultList;
    }

    private boolean isAnagrams(String s1, String s2) {
        char[] charArray1 = s1.toCharArray();
        int[] arr1 = new int[26];

        for (char c : charArray1) {
            arr1[c - 'a']++;
        }

        char[] charArray2 = s2.toCharArray();
        int[] arr2 = new int[26];
        for (char c : charArray2) {
            arr2[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testLengthOfLongestSubstring() {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("a"));
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = -1, left = -1;
        char[] chars = s.toCharArray();
        Map<Character, Integer> indexMap = new HashMap<>(s.length());

        for (int i = 0; i < chars.length; i++) {
            Integer index = indexMap.get(chars[i]);
            if (index != null) {
                left = Math.max(left, index);
            }

            indexMap.put(chars[i], i);
            max = Math.max(max, i - left);
        }

        return max;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    break;
                } else {
                    set.add(chars[j]);
                }
            }

            max = Math.max(max, set.size());
        }

        return max;
    }

    @Test
    public void testTrap() {

//        int[] arr = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap(arr));

    }

    public int trap(int[] height) {
        int left = 0, leftMax = 0, right = height.length - 1, rightMax = 0, sum = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    sum += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    sum += rightMax - height[right];
                }
                right--;
            }
        }

        return sum;
    }

    public int trap2(int[] height) {
        int n = height.length;
        int[] leftMaxArr = new int[n];
        int[] rightMaxArr = new int[n];

        leftMaxArr[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMaxArr[i] = Math.max(leftMaxArr[i - 1], height[i]);
        }

        rightMaxArr[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(rightMaxArr[i + 1], height[i]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i];
        }

        return sum;
    }

    @Test
    public void testMaxArea() {
//        int[] arr = new int[] {1,8,6,2,5,4,8,3,7};
        int[] arr = new int[]{1, 1};
        System.out.println(maxArea(arr));
    }

    public int maxArea(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int current = (j - i) * Math.min(height[i], height[j]);
            if (current > max) {
                max = current;
            }

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return max;
    }


    @Test
    public void testThreeSum3() {
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
//        int[] arr = new int[] {0,1,1};
//        int[] arr = new int[] {0,0,0};
        ResultUtils.printList(threeSum3(arr));
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;

            }

            int left = i + 1, right = nums.length - 1;
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }
        }

        return resultList;
    }

    @Test
    public void testMoveZeroes() {
        int[] arr = new int[]{0, 1, 0, 3, 12};
        moveZeroes(arr);

        ResultUtils.printArr(arr);
    }


    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    @Test
    public void testEquationsPossible() {
        EquationsPossible equationsPossible = new EquationsPossible();
        String[] arr = new String[]{"a==b", "b!=a"};
        System.out.println(equationsPossible.equationsPossible(arr));

        arr = new String[]{"a==b", "b==c", "a==c"};
        System.out.println(equationsPossible.equationsPossible(arr));

        arr = new String[]{"c==c", "b==d", "x!=z"};
        System.out.println(equationsPossible.equationsPossible(arr));

    }

    @Test
    void testLongestConsecutive() {
//        int[] arr = new int[] {100,4,200,1,3,2};
//        System.out.println(longestConsecutive(arr));

//        int[] arr = new int[] {0,2,1,1};
//        int[] arr = new int[] {100,4,200,1,3,2};
        int[] arr = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(arr));
        System.out.println(longestConsecutive2(arr));
    }


    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;
        for (Integer value : set) {
            // 存在比value小的节点，跳过
            if (set.contains(value - 1)) {
                continue;
            }

            int index = 0;
            while (set.contains(value)) {
                value++;
                index++;
            }

            max = Math.max(max, index);
        }

        return max;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int pre = nums[0];
        int index = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            if (value == pre + 1) {
                pre = value;
                index++;
                max = Math.max(max, index);
                continue;
            }

            // 相等则跳过
            if (value == pre) {
                continue;
            }

            index = 1;
            pre = value;
        }

        return max;
    }

    @Test
    void testGroupAnagrams() {
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        ResultUtils.printListList(groupAnagrams(strings));
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = Arrays.stream(strs).collect(Collectors.groupingBy(this::sortString));
        return new ArrayList<>(map.values());
    }

    public String sortString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }


    @Test
    void testTwoSum() {
        int[] nums = new int[]{2, 7, 11, 15};
        ResultUtils.printArr(twoSum(nums, 9));

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            Integer index = indexMap.get(target - value);
            if (index != null) {
                return new int[]{index, i};
            }
            indexMap.put(value, i);
        }

        return null;
    }


    @Test
    public void testAllPathsSourceTarget() {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> listList = allPathsSourceTarget(graph);
        for (List<Integer> integerList : listList) {
            System.out.println(integerList.stream().map(String::valueOf).collect(Collectors.joining("->")));
        }
    }


    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> pathList = new LinkedList<>();
        pathList.add(0);
        dfsForAllPathsSourceTarget(0, graph, pathList, resultList);

        return resultList;
    }

    private void dfsForAllPathsSourceTarget(int start, int[][] graph, LinkedList<Integer> pathList, List<List<Integer>> resultList) {
        if (start == graph.length - 1) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i : graph[start]) {
            pathList.offer(i);
            dfsForAllPathsSourceTarget(i, graph, pathList, resultList);
            pathList.removeLast();
        }
    }

    @Test
    public void testIsMatch2() {
        System.out.println(isMatch2("aa", "a"));
        System.out.println(isMatch2("aa", "a*"));
        System.out.println(isMatch2("ab", ".*"));
    }

    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        char[] aChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (matched(pChars[j - 1], aChars[i - 1])) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChars[j - 1] == '*') {
                    boolean notUse = dp[i][j - 2];
                    boolean use = dp[i - 1][j] || matched(pChars[j - 2], aChars[i - 1]);

                    dp[i][j] = notUse || use;
                }
            }

        return dp[m][n];
    }

    private boolean matched(char c1, char c2) {
        if (c1 == '.') {
            return true;
        }

        return c1 == c2;
    }

    @Test
    public void testIsMatch() {
//        System.out.println(isMatch("aa", "a"));
//        System.out.println(isMatch("aa", "*"));
//        System.out.println(isMatch("cb", "?a"));
//        System.out.println(isMatch("txt12.xls", "te?t*.*"));
//        System.out.println(isMatch("0QZz", "**Z"));
//        System.out.println(isMatch("abcd", "?*Bc*?"));
//        System.out.println(isMatch("acdcb", "a*c?b"));
//        System.out.println(isMatch("hhhhhhhahhaahhahhhhaaahhahhahaaahhahahhhahhhahaaahaah", "h*h*ah**ha*h**h***hha"));
        System.out.println(isMatch("", "***"));
        System.out.println(isMatch("abcabczzzde", "*abc???de*"));
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
            if (pChars[i - 1] == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (aChars[i - 1] == pChars[j - 1] || pChars[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChars[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }

        return dp[m][n];
    }


    @Test
    public void testMySqrt() {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(9));
        System.out.println(mySqrt(2147395599));

    }


    public int mySqrt(int x) {
        int low = 0;
        int height = x;
        int mid = (low + height) / 2;
        while (low <= height) {
            if ((long) mid * mid - x > 0) {
                height = mid - 1;
            } else {
                low = mid + 1;
            }
            mid = (low + height) / 2;
        }

        return mid;
    }


    @Test
    public void testFindLengthOfLCIS() {
        int[] arr = new int[]{1, 3, 5, 4, 7};
        System.out.println(findLengthOfLCIS(arr));

        arr = new int[]{2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS(arr));

        arr = new int[]{1, 3, 5, 7};
        System.out.println(findLengthOfLCIS(arr));


    }

    // 1,3,5,4,7
    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int slow = 0, fast = 1;

        while (fast < nums.length) {
            if (nums[fast] <= nums[fast - 1]) {

                slow = fast;
            }

            max = Math.max(max, fast - slow + 1);
            fast++;
        }

        return max;
    }


    @Test
    public void testCombination() {
        List<List<Integer>> combination = combination(20, 19);
        for (List<Integer> list : combination) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combination(int n, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> pathList = new LinkedList<>();

        dfsForCombination(n, k, pathList, resultList, 1);
        return resultList;
    }

    private void dfsForCombination(int n, int k, LinkedList<Integer> pathList, List<List<Integer>> resultList, int start) {
        if (pathList.size() == k) {
            resultList.add(new ArrayList<>(pathList));
            return;
        }

        for (int i = start; i <= n - (k - pathList.size()) + 1; i++) {
            pathList.add(i);
            dfsForCombination(n, k, pathList, resultList, i + 1);
            pathList.removeLast();
        }
    }


    @Test
    public void testPermutation() {
        String[] strings = permutation("qqe");
        for (String string : strings) {
            System.out.println(string);
        }

    }

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        int[] visited = new int[chars.length];
        Arrays.sort(chars);
        List<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfsForPermutation(chars, visited, path, result);
        return result.toArray(new String[0]);
    }

    private void dfsForPermutation(char[] chars, int[] visited, StringBuilder path, List<String> result) {
        if (path.length() == chars.length) {
            result.add(path.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (i != 0 && chars[i] == chars[i - 1] && visited[i - 1] == 1) {
                continue;
            }
            if (visited[i] == 1) {
                continue;
            }

            path.append(chars[i]);
            visited[i] = 1;
            dfsForPermutation(chars, visited, path, result);
            path.deleteCharAt(path.length() - 1);
            visited[i] = 0;
        }
    }


    @Test
    public void testMaxDepth() {

        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
    }

    public int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
                max = Math.max(max, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }

        return max;
    }

    @Test
    public void testConvertToTitle() {
        System.out.println(convertToTitle(1));
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(27));
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            char c = (char) ('A' + columnNumber % 26);
            stringBuilder.append(c);
            columnNumber /= 26;
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    @Test
    public void testTitleToNumber() {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
    }

    public int titleToNumber(String columnTitle) {
        char[] charArray = columnTitle.toCharArray();
        int result = 0;
        for (int i = 0; i < charArray.length; i++) {
            result = result * 26 + getNumberByChar(charArray[i]);
        }

        return result;
    }

    public int getNumberByChar(char c) {
        return c - 'A' + 1;
    }


    @Test
    public void testNumTrees() {
        for (int i = 1; i <= 5; i++)
            System.out.println(numTrees(i));
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    @Test
    public void testIntegerBreak() {
        for (int i = 2; i <= 10; i++) {
            System.out.println(integerBreak(i));
        }
    }


    public int integerBreak(int n) {
        if (n <= 2) {
            return (n - 1);
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(j * (i - j), j * dp[i - j]), dp[i]);
            }
        }

        return dp[n];
    }

    @Test
    public void testUniquePathsWithObstacles() {
        int[][] obstacleGrid = new int[][]{{0, 0}, {1, 1}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // 如果起点或者终点就是障碍物，直接返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 如果存在障碍物就停止初始化
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    @Test
    public void testUniquePaths() {
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(7, 3));
        System.out.println(uniquePaths(3, 3));

        UniquePathsDemo uniquePathsDemo = new UniquePathsDemo();
        System.out.println(uniquePathsDemo.uniquePaths(3, 7));
        System.out.println(uniquePathsDemo.uniquePaths(3, 2));
        System.out.println(uniquePathsDemo.uniquePaths(7, 3));
        System.out.println(uniquePathsDemo.uniquePaths(3, 3));
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }

    @Test
    public void testMinCostClimbingStairs() {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }


    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[length];
    }

    @Test
    public void testClimbStairs() {
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

    public int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs3(int n) {
        if (n <= 3) {
            return n;
        }

        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        for (int i = 4; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public int climbStairs2(int n) {
        if (n <= 3) {
            return n;
        }

        int pre1 = 2;
        int pre2 = 3;
        int result = pre1 + pre2;
        for (int i = 4; i < n; i++) {
            pre1 = pre2;
            pre2 = result;
            result = pre1 + pre2;
        }

        return result;
    }

    @Test
    public void testFib() {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
    }

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }

        return fib2(n - 1) + fib2(n - 2);
    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }

        int pre1 = 0;
        int pre2 = 1;
        int result = pre1 + pre2;
        for (int i = 2; i < n; i++) {
            pre1 = pre2;
            pre2 = result;
            result = pre1 + pre2;
        }

        return result;
    }

    @Test
    public void testMaxProfit3() {
        int[] prices = new int[]{1, 3, 7, 5, 10, 3};
        System.out.println(maxProfit(prices, 3));
    }

    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int sell = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, sell + prices[i] - fee);
            sell = Math.max(sell, profit - prices[i]);
        }

        return profit;
    }

    @Test
    public void testMonotoneIncreasingDigits() {
        System.out.println(monotoneIncreasingDigits(10));
        System.out.println(monotoneIncreasingDigits(1234));
        System.out.println(monotoneIncreasingDigits(332));
        System.out.println(monotoneIncreasingDigits(853567367));
    }

    public int monotoneIncreasingDigits(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        int startIndex = chars.length;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                startIndex = i;
            }
        }

        for (int i = startIndex; i < chars.length; i++) {
            chars[i] = '9';
        }

        return Integer.parseInt(new String(chars));
    }

    public int monotoneIncreasingDigits2(int n) {
        while (true) {
            if (isIncrease(n)) {
                return n;
            }
            n--;
        }
    }

    private boolean isIncrease(int n) {
        int pre = 10;
        while (n != 0) {
            int temp = n % 10;
            if (temp > pre) {
                return false;
            }

            pre = temp;
            n = n / 10;
        }

        return true;
    }

    @Test
    public void testMerge() {
        int[][] arr = new int[][]{{1, 4}, {4, 5}};
        printArray(merge(arr));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        int preStart = intervals[0][0];
        int preEnd = intervals[0][1];

        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= preEnd) {
                preEnd = Math.max(preEnd, interval[1]);
            } else {
                list.add(new int[]{preStart, preEnd});

                preStart = interval[0];
                preEnd = interval[1];
            }
        }

        // 添加最后一个区间
        list.add(new int[]{preStart, preEnd});
        return list.toArray(new int[list.size()][]);
    }

    @Test
    public void testPartitionLabels() {
        System.out.println(partitionLabels2("ababcbacadefegdehijhklij"));
//        System.out.println(findPartitions("ababcbacadefegdehijhklij"));

    }

    public List<Integer> partitionLabels2(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        int[][] partitions = findPartitions(s);
        Arrays.sort(partitions, Comparator.comparingInt(x -> x[0]));

        int preLeft = partitions[0][0];
        int preRight = partitions[0][1];
        List<Integer> list = new ArrayList<>();
        for (int[] partition : partitions) {
            if (partition[0] > preRight) {
                list.add(preRight - preLeft + 1);
                preLeft = partition[0];
            }

            preRight = Math.max(partition[1], preRight);
        }

        // 添加最后一个区间
        list.add(preRight - preLeft + 1);
        return list;
    }

    public int[][] findPartitions(String s) {
        int count = 0;
        int[][] hash = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hash[c - 'a'][0] == 0) {
                hash[c - 'a'][0] = i + 1;
                count++;
            }

            hash[c - 'a'][1] = i + 1;
        }

        int index = 0;
        int[][] res = new int[count][2];
        for (int[] ints : hash) {
            if (ints[0] == 0 && ints[1] == 0) {
                continue;
            }

            res[index][0] = ints[0];
            res[index][1] = ints[1];
            index++;
        }

        return res;
    }

    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        int[] lastArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastArr[s.charAt(i) - 'a'] = i;
        }

        List<Integer> list = new ArrayList<>(s.length());
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastArr[s.charAt(i) - 'a']);
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1;
                end = 0;
            }
        }

        return list;
    }

    @Test
    public void testEraseOverlapIntervals() {
        int[][] arr = new int[][]{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
        System.out.println(eraseOverlapIntervals(arr));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0 || intervals.length == 1) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));

        int count = 0;
        int preEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            // 存在覆盖情况
            if (interval[0] < preEnd) {
                count++;
                // 保留右边界小的
                if (interval[1] < preEnd) {
                    preEnd = interval[1];
                }
            } else {
                preEnd = interval[1];
            }
        }

        return count;
    }

    @Test
    public void testFindMinArrowShots() {
        int[][] arr = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(arr));

    }

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, Comparator.comparingInt(x -> x[1]));

        // 至少需要一个
        int count = 1;
        // 右坐标
        int pre = points[0][1];
        for (int[] point : points) {
            // 当前左坐标大于上一右坐标，说明不在同一个重叠区域，需要更新下一个重叠区域
            if (point[0] > pre) {
                count++;
                pre = point[1];
            }
        }

        return count;
    }

    @Test
    public void testLemonadeChange() {
        // [5,5,5,5,20,20,5,5,5,5]
        // 5,5,5,5,5,10,20,20
        int[] bills = new int[]{5, 5, 10, 10, 20};
        System.out.println(lemonadeChange(bills));
    }

    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                count5++;
            } else if (bill == 10) {
                count10++;
                count5--;
            } else {
                if (count10 > 0) {
                    count10--;
                    count5--;
                } else {
                    count5 = count5 - 3;
                }
            }
            if (count5 < 0) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testCanCompleteCircuit() {
        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};

        System.out.println(canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentSum = 0;
        int totalSum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            currentSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (currentSum < 0) {
                index = i + 1;
                currentSum = 0;
            }
        }

        // 所以加油站余量小于0，一定跑不了一圈
        return totalSum < 0 ? -1 : index;
    }


    @Test
    public void testLargestSumAfterKNegations() {
        int[] nums = new int[]{2, -3, -1, 5, -4};
        int k = 2;

        System.out.println(largestSumAfterKNegations(nums, k));
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }

            min = Math.min(min, nums[i]);
            sum += nums[i];
        }

        if ((k & 1) == 1) {
            sum -= 2 * min;
        }

        return sum;
    }

    @Test
    public void testJump() {
        int[] arr = new int[]{1, 2, 1, 1, 1};
        System.out.println(jump(arr));
    }

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        // 最大步数
        int count = 0;
        // 当前覆盖最大距离
        int cover = 0;
        // 下一步覆盖最大距离
        int nextCover = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            nextCover = Math.max(nextCover, i + nums[i]);
            // 走到当前覆盖最大距离，需要走下一步
            if (i == cover) {
                // 更新当前最大覆盖距离
                cover = nextCover;
                count++;
            }
        }

        return count;
    }

    @Test
    public void testCanJump() {
        int[] arr = new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        System.out.println(canJump(arr));
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, i + nums[i]);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testMaxProfit() {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};

        System.out.println(maxProfit2(arr));
    }

    public int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        int n = prices.length;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[n - 1][1];
    }

    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }


    @Test
    public void testMaxSubArray() {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray2(arr));
    }

    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        int result = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public int maxSubArray(int[] nums) {
        int count = 0;
        int result = Integer.MIN_VALUE;

        for (int num : nums) {
            count = count + num;
            if (count > result) {
                result = count;
            }

            if (count < 0) {
                count = 0;
            }
        }

        return result;
    }

    @Test
    public void testFindContentChildren() {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};

        System.out.println(findContentChildren(g, s));
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; ) {
            if (s[j] >= g[i]) {
                count++;
                i++;
            }
            j++;
        }
        return count;
    }

    @Test
    public void testSudoku() {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku(board);

        for (char[] chars : board) {
            System.out.println(Stream.of(chars).map(String::valueOf).collect(Collectors.joining(",")));
        }


//        int  k = 2;
//        char c = (char) (k+48);
//        System.out.println(c);
    }


    @Test
    public void testSolveNQueens() {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> listList = solveNQueens.solveNQueens(5);
        for (List<String> list : listList) {
            String collect = String.join(",", list);
            System.out.printf("[%s]\n", collect);
        }
    }

    @Test
    public void testPermuteUnique() {
        int[] nums = new int[]{1, 1, 2};
        PermuteUnique permuteUnique = new PermuteUnique();
        printListList(permuteUnique.permuteUnique(nums));
    }

    @Test
    public void testPermute() {
        int[] nums = new int[]{1, 1, 2};
        Permute permute = new Permute();
        List<List<Integer>> listList = permute.permute(nums);
        printListList(listList);
    }


    @Test
    public void FindSubsequences() {
        int[] nums = new int[]{4, 6, 7, 7};
        FindSubsequences findSubsequences = new FindSubsequences();
        List<List<Integer>> listList = findSubsequences.findSubsequences(nums);
        printListList(listList);
    }

    private void printListList(List<List<Integer>> listList) {
        for (List<Integer> list : listList) {
            String collect = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.printf("[%s]\n", collect);
        }
    }

    @Test
    public void testSubsetsWithDup() {
        int[] nums = new int[]{1, 2, 2};
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();
        List<List<Integer>> listList = subsetsWithDup.subsetsWithDup(nums);
        for (List<Integer> list : listList) {
            String collect = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.printf("[%s]\n", collect);
        }
    }

    @Test
    public void testSubsets() {
        int[] nums = new int[]{1, 2, 3};
        Subsets subsets = new Subsets();
        List<List<Integer>> listList = subsets.subsets(nums);
        for (List<Integer> list : listList) {
            String collect = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.printf("[%s]\n", collect);
        }
    }

    @Test
    public void testCombinationSum() {
        int target = 400;
        int[] candidates = new int[]{100, 200, 4, 12};
        CombinationSum combinationSum = new CombinationSum();

        List<List<Integer>> listList = combinationSum.combinationSum(candidates, target);
        for (List<Integer> list : listList) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testLetterCombinations() {
        String digits = "34";
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> stringList = letterCombinations.letterCombinations(digits);
        for (String s : stringList) {
            System.out.print(s + " ");
        }
    }


    @Test
    public void testCombinationSum3() {
        int k = 3;
        int n = 9;

        CombinationSum3 combinationSum3 = new CombinationSum3();
        List<List<Integer>> listList = combinationSum3.combinationSum3(k, n);

        for (List<Integer> list : listList) {
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }


    @Test
    public void testCombine() {
        int n = 4;
        int k = 3;

        Combine combine = new Combine();
        List<List<Integer>> listList = combine.combine(n, k);

        for (List<Integer> list : listList) {
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }


    @Test
    public void testConvertBST() {
        TreeNode root = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        ConvertBST convertBST = new ConvertBST();

        TreeNode treeNode = convertBST.convertBST(root);
        System.out.println(treeNode);
    }

    public TreeNode convertBST(TreeNode root) {
        return buildTreeForConvertBST(root, 0);
    }

    private TreeNode buildTreeForConvertBST(TreeNode root, int sum) {
        if (root == null) {
            return null;
        }

        TreeNode right = buildTreeForConvertBST(root.right, sum);
        int value = sum + root.val;
        if (right != null) {
            value += right.val;
        }

        TreeNode newNode = new TreeNode(value);
        newNode.left = buildTreeForConvertBST(root.left, value);
        newNode.right = right;
        return newNode;
    }


    @Test
    public void testSortedArrayToBST() {
//        int[] nums = {-10,-3,0,5,9};
        int[] nums = {-10, -3, 0};
//        int[] nums = {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode);
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        int mid = (nums.length - 1) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        node.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        return node;
    }


    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val > high) {
            // 超过上界，剪掉右子树
            root = root.left;
            // 剪掉之后，继续剪左子树
            root = trimBST(root, low, high);
            return root;
        }

        if (root.val < low) {
            // 小于下界，剪掉左子树
            root = root.right;
            // 剪掉之后，继续剪右子树
            root = trimBST(root, low, high);
            return root;
        }

        // 处于之间，递归剪左右子树
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }


    @Test
    public void testDeleteNode() {
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);

        three.left = two;
        three.right = four;
        six.right = seven;
        five.left = three;
        five.right = six;


        TreeNode treeNode = deleteNode(five, 5);
        System.out.println(treeNode);
    }


    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 左右节点都为null，删除的是叶子节点，直接删除即可
            if (left == null && right == null) {
                return null;
            }

            // 左节点为null，直接返回右节点
            if (left == null) {
                return right;
            }

            // 右节点为null，直接返回左节点
            if (right == null) {
                return left;
            }

            // 左右节点都不为null，删除节点的左孩子应该是删除节点的右孩子的最左孩子
            TreeNode temp = left;
            while (temp.right != null) {
                temp = temp.right;
            }

            temp.right = right;
            return left;
        }

        if (root.val > key) {
            root.left = deleteNode2(root.left, key);
        } else {
            root.right = deleteNode2(root.right, key);
        }

        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 1.左右节点都为null，删除的是叶子节点，直接删除即可
            if (left == null && right == null) {
                return null;
            }

            // 2.左节点为null，直接返回右节点
            if (left == null) {
                return right;
            }

            // 3.右节点为null，直接返回左节点
            if (right == null) {
                return left;
            }

            // 4.左右节点都不为null，删除节点的左孩子应该是删除节点的右孩子的最左孩子
            // 4.1 删除节点的右孩子的最左孩子
            TreeNode temp = right;
            while (temp.left != null) {
                temp = temp.left;
            }

            // 4.2 删除节点的右孩子的最左孩子指向删除节点的左孩子
            temp.left = left;
            // 4.3 返回删除节点的右孩子
            return right;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode temp = root;
        while (true) {
            if (val < temp.val) {
                if (temp.left != null) {
                    temp = temp.left;
                } else {
                    // 找到待插入位置
                    temp.left = new TreeNode(val);
                    break;
                }
            } else {
                if (temp.right != null) {
                    temp = temp.right;
                } else {
                    // 找到待插入位置
                    temp.right = new TreeNode(val);
                    break;
                }
            }
        }

        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root;
        while (true) {
            if (temp.val > p.val && temp.val > q.val) {
                temp = temp.left;
            } else if (temp.val < p.val && temp.val < q.val) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
    }

    public boolean isBalanced2(TreeNode root) {
        return getHeightAndAssert(root) >= 0;
    }

    private int getHeightAndAssert(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeightAndAssert(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getHeightAndAssert(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    @Test
    public void testCountNodes() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

//        right.left = new TreeNode(6);
//        right.right = new TreeNode(7);
        System.out.println(countNodes2(root));
//        dfs(root);
    }


    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                count++;
                TreeNode node = queue.poll();
                if (node.left != null && node.right == null) {
                    return count * 2;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return count;
    }


    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int left = minDepth(root.left) + 1;
        int right = minDepth(root.right) + 1;
        return Math.min(left, right);
    }

    public int maxDepth(cn.edu.pzhu.mytest.bean.Node root) {
        if (root == null) {
            return 0;
        }

        Queue<cn.edu.pzhu.mytest.bean.Node> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                cn.edu.pzhu.mytest.bean.Node node = queue.poll();
                if (node.children != null) {
                    for (cn.edu.pzhu.mytest.bean.Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }

            depth++;
        }

        return depth;
    }

    public int maxDepth2(cn.edu.pzhu.mytest.bean.Node root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        if (root.children != null) {
            for (cn.edu.pzhu.mytest.bean.Node node : root.children) {
                maxDepth = Math.max(maxDepth, maxDepth(node));
            }
        }

        return maxDepth + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> currentLevelList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                currentLevelList.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            list.add(currentLevelList);
        }

        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<Integer>> levelNumberToNodeListMap = new HashMap<>();
        dfs(root, 1, levelNumberToNodeListMap);

        Set<Integer> levelNumberSet = levelNumberToNodeListMap.keySet();
        List<Integer> levelNumberList = new ArrayList<>(levelNumberSet);
        levelNumberList.sort(Comparator.naturalOrder());

        for (Integer levelNumber : levelNumberList) {
            list.add(levelNumberToNodeListMap.get(levelNumber));
        }

        return list;
    }

    private void dfs(TreeNode root, int levelNumber, Map<Integer, List<Integer>> levelNumberToNodeListMap) {
        if (root == null) {
            return;
        }

        levelNumberToNodeListMap.computeIfAbsent(levelNumber, k -> new ArrayList<>()).add(root.val);
        dfs(root.left, levelNumber + 1, levelNumberToNodeListMap);
        dfs(root.right, levelNumber + 1, levelNumberToNodeListMap);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    public void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    @Test
    public void testTopKFrequent() {
        int[] arr = new int[]{4, 1, -1, 2, -1, 2, 3};
        int k = 2;

        printArray(topKFrequent2(arr, k));

    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.length);
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = countMap.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] resultArray = new int[queue.size()];
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            resultArray[i] = queue.poll().getKey();
        }

        return resultArray;
    }


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.length);
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> countList = new ArrayList<>(nums.length);
        countMap.forEach((num, count) -> countList.add(count));
        countList.sort(Comparator.comparing(Integer::intValue).reversed());

        int targetK = countList.get(k - 1);
        List<Integer> resultList = new ArrayList<>(nums.length);
        countMap.forEach((num, count) -> {
            if (count >= targetK) {
                resultList.add(num);
            }
        });

        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size() && i < k; i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] resultArray = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        resultArray[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
            // 移除不在窗口内的元素
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            resultArray[i - k + 1] = nums[deque.peekFirst()];
        }

        return resultArray;
    }


    @Test
    public void testEvalRPN() {
        String[] arr = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(arr));


    }

    public int evalRPN(String[] tokens) {
        Set<String> operatorSet = new HashSet<>(Arrays.asList("-", "+", "*", "/"));
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (!operatorSet.contains(token)) {
                stack.push(token);
                continue;
            }

            int result = 0;
            int number1 = Integer.parseInt(stack.pop());
            int number2 = Integer.parseInt(stack.pop());

            switch (token) {
                case "+":
                    result = number2 + number1;
                    break;
                case "-":
                    result = number2 - number1;
                    break;
                case "*":
                    result = number2 * number1;
                    break;
                case "/":
                    result = number2 / number1;
                    break;
                default:
            }

            stack.push(String.valueOf(result));
        }

        return Integer.parseInt(stack.pop());
    }

    public String removeDuplicates2(String s) {
        char[] chars = s.toCharArray();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (top == -1 || chars[top] != chars[i]) {
                chars[++top] = chars[i];
            } else {
                top--;
            }
        }

        return new String(chars, 0, top + 1);
    }

    @Test
    public void testRemoveDuplicates() {
        System.out.println(removeDuplicates2("abbaca"));
        System.out.println(removeDuplicates2("abbaaa"));
    }

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(currentChar);
                continue;
            }

            Character peekChar = stack.peek();
            if (currentChar != peekChar) {
                stack.push(currentChar);
                continue;
            }

            stack.pop();
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    @Test
    public void testIsValid() {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("{[]}}"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '(') {
                stack.push(')');
            } else if (stack.isEmpty() || ch != stack.pop()) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void testEven() {
        System.out.println(1 & 1);
        System.out.println(2 & 1);
        System.out.println(3 & 1);
        System.out.println(4 & 1);
    }

    public ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode index1 = fast;
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }

        return null;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> existNodeSet = new HashSet<>(100);
        ListNode temp = head;

        while (temp != null) {
            if (existNodeSet.contains(temp)) {
                return temp;
            }
            existNodeSet.add(temp);
            temp = temp.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> existNodeSet = new HashSet<>(100);
        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != null) {
            existNodeSet.add(tempA);
            tempA = tempA.next;
        }

        while (tempB != null) {
            if (existNodeSet.contains(tempB)) {
                return tempB;
            }

            tempB = tempB.next;
        }

        return null;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;

        int lengthA = 0;
        int lengthB = 0;

        while (tempA != null) {
            tempA = tempA.next;
            lengthA++;
        }

        while (tempB != null) {
            tempB = tempB.next;
            lengthB++;
        }

        int gap = Math.abs(lengthA - lengthB);
        tempA = headA;
        tempB = headB;

        if (lengthA > lengthB) {
            while (gap-- > 0) {
                tempA = tempA.next;
            }
        } else {
            while (gap-- > 0) {
                tempB = tempB.next;
            }
        }

        while (tempA != null) {
            if (tempA == tempB) {
                return tempA;
            }

            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;
    }

    @Test
    public void testStrStr() {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("", ""));

        System.out.println(strStr2("hello", "ll"));
        System.out.println(strStr2("aaaaa", "bba"));
        System.out.println(strStr2("", ""));

    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        return haystack.indexOf(needle);
    }

    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        // 构建next前缀数组
        int[] next = new int[needle.length()];
        buildNext(next, needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }

            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }

            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
        }

        return -1;
    }

    /**
     * 构建前缀数据
     *
     * @param next 前缀数组
     * @param s    指定字符串
     */
    private void buildNext(int[] next, String s) {
        int j = 0;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
    }

    @Test
    public void testReverseLeftWords() {
        System.out.println(reverseLeftWords2("abcdefg", 7));
        System.out.println(reverseLeftWords2("lrloseumgh", 6));

        System.out.println(reverseLeftWords("abcdefg", 7));
        System.out.println(reverseLeftWords("lrloseumgh", 6));


    }

    public String reverseLeftWords2(String s, int n) {
        String left = s.substring(0, n);
        String right = s.substring(n);
        return right + left;
    }

    public String reverseLeftWords(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder(s);
        reverseString(stringBuilder, 0, n - 1);
        reverseString(stringBuilder, n, s.length() - 1);
        reverseString(stringBuilder, 0, s.length() - 1);

        return stringBuilder.toString();
    }

    /**
     * 翻转字符串
     *
     * @param stringBuilder stringBuilder对象
     * @param start         字符串起索引
     * @param end           字符串止索引
     */
    private void reverseString(StringBuilder stringBuilder, int start, int end) {
        while (start < end) {
            char temp = stringBuilder.charAt(start);
            stringBuilder.setCharAt(start, stringBuilder.charAt(end));
            stringBuilder.setCharAt(end, temp);
            start++;
            end--;
        }
    }


    @Test
    public void testReverseWords() {
        String string = "Alice does not even like bob";
        System.out.println(reverseWords(string));
    }

    public String reverseWords(String s) {
        String[] array = s.split(" ");
        List<String> list = new ArrayList<>(array.length);

        // 获取每个单词
        for (String str : array) {
            if (str.trim().equals("")) {
                continue;
            }

            list.add(str);
        }

        // 翻转
        Collections.reverse(list);

        // 拼接
        return String.join(" ", list);
    }

    @Test
    public void testReplaceSpace() {
        System.out.println(replaceSpace2("We are happy."));
    }

    public String replaceSpace2(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                stringBuilder.append(c);
            } else {
                stringBuilder.append("%20");
            }
        }

        return stringBuilder.toString();
    }

    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }


    @Test
    public void testReverseStr() {
        System.out.println(reverseStr("abcdefg", 2));
        System.out.println(reverseStr("abcd", 2));
        System.out.println(reverseStr("abcdefgh", 2));
        System.out.println(reverseStr("abcdefgh", 3));
    }

    public String reverseStr(String s, int k) {
        int index = 0;
        char[] charArray = s.toCharArray();
        while (index < charArray.length) {
            // 确定交换区域，index ~ index+k，注意这里最大取到数组的length-1
            int end = Math.min(index + k - 1, charArray.length - 1);

            // 交换index ~ index+k
            swap(charArray, index, end);

            // 移动2k
            index += 2 * k;
        }

        return new String(charArray);
    }

    /**
     * 交换字符
     *
     * @param arr   字符数组
     * @param start 索引起
     * @param end   索引止
     */
    private void swap(char[] arr, int start, int end) {
        while (start < end) {
            arr[start] ^= arr[end];
            arr[end] ^= arr[start];
            arr[start] ^= arr[end];

            start++;
            end--;
        }
    }

    @Test
    public void testReverseString() {
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        reverseString2(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }


    public void reverseString2(char[] s) {
        int left = 0, right = s.length - 1;
        char temp;
        while (left < right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    public void reverseString(char[] s) {
        List<Character> list = new ArrayList<>();
        for (char c : s) {
            list.add(c);
        }

        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            s[i] = list.get(i);
        }
    }


    @Test
    public void testFourSum() {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        printAnswerFourSum(fourSum2(nums, target));

    }

    private void printAnswerFourSum(List<List<Integer>> list) {
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("[]");
            return;
        }

        for (List<Integer> integerList : list) {
            System.out.println(integerList.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<String> existAnswerSet = new HashSet<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        // 将四元组拼接为字符串
                        String answerString = "" + nums[i] + nums[j] + nums[left] + nums[right];
                        // 如果当前字符串未出现过，证明是一组新的四元组，加入结果集中
                        if (!existAnswerSet.contains(answerString)) {
                            existAnswerSet.add(answerString);
                            resultList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        }

                        left++;
                        right--;
                    }
                }
            }
        }

        return resultList;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();

        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 剪枝重复 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                // 剪枝重复 nums[j]
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {

                        resultList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 剪枝重复 nums[left]
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // 剪枝重复 nums[right]
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 移动指针
                        left++;
                        right--;
                    }
                }
            }
        }

        return resultList;
    }

    @Test
    public void testThreeSum() {
        int[] arr = new int[]{-2, 0, 1, 1, 2};
        printThreeSumResult(threeSum(arr));
    }

    private void printThreeSumResult(List<List<Integer>> list) {
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("[]");
            return;
        }

        for (List<Integer> integerList : list) {
            System.out.println(integerList.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 第一个数都大于0，证明没有有效解
            if (nums[i] > 0) {
                return resultList;
            }

            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    // 有效解
                    resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 去重，过滤相同的数字
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // 去重，过滤相同的数字
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
            }
        }

        return resultList;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return new ArrayList<>();
        }

        Set<String> set = new HashSet<>();
        List<List<Integer>> resultList = new ArrayList<>();

        // 构架哈希表
        Map<Integer, Integer> numberCountMap = new HashMap<>(length);
        for (int num : nums) {
            int count = numberCountMap.getOrDefault(num, 0) + 1;
            numberCountMap.put(num, count);
        }

        for (int i = 0; i < length; i++) {
            // 当前数被占用，从哈希表中移除
            add(numberCountMap, nums[i], -1);
            for (int j = i + 1; j < length; j++) {
                // 当前数被占用，从哈希表中移除
                add(numberCountMap, nums[j], -1);
                int target = -(nums[i] + nums[j]);
                Integer targetCount = numberCountMap.get(target);
                if (targetCount != null && targetCount > 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(target);
                    // 剪枝，将结果排序后组成一个字符串，如果当前字符串未出现过，证明是一组新解，加入到结果中
                    String key = getKey(list);
                    if (!set.contains(key)) {
                        resultList.add(list);
                        set.add(key);
                    }
                }
                // 当前循环结束，复原哈希表
                add(numberCountMap, nums[j], 1);
            }
            // 当前循环结束，复原哈希表
            add(numberCountMap, nums[i], 1);
        }


        return resultList;
    }

    private String getKey(List<Integer> list) {
        list.sort(Comparator.naturalOrder());
        return "" + list.get(0) + "" + list.get(1) + "" + list.get(2);
    }

    private void add(Map<Integer, Integer> map, Integer key, int count) {
        Integer num = map.get(key);
        num = num + count;
        map.put(key, num);
    }


    @Test
    public void testCanConstruct() {
        System.out.println(canConstruct("aab", "baa"));
        System.out.println(canConstruct2("aab", "baa"));
    }


    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] indexArr = new int[26];
        for (char c : magazine.toCharArray()) {
            indexArr[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            int count = indexArr[c - 'a'];
            if (count <= 0) {
                return false;
            }

            indexArr[c - 'a']--;
        }

        return true;
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> charToCountMap = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            int count = charToCountMap.getOrDefault(c, 0) + 1;
            charToCountMap.put(c, count);
        }

        for (char c : ransomNote.toCharArray()) {
            Integer count = charToCountMap.get(c);
            if (count == null) {
                return false;
            }

            count--;
            charToCountMap.put(c, count);
            if (count == 0) {
                charToCountMap.remove(c);
            }
        }

        return true;
    }


    @Test
    public void testFourSumCount() {

        int[] arr1 = new int[]{1, 2};
        int[] arr2 = new int[]{-2, -1};
        int[] arr3 = new int[]{-1, 2};
        int[] arr4 = new int[]{0, 2};

        System.out.println(fourSumCount(arr1, arr2, arr3, arr4));

    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap1 = buildSumMap(nums1, nums2);
        Map<Integer, Integer> sumMap2 = buildSumMap(nums3, nums4);

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : sumMap1.entrySet()) {
            // A，B的和
            Integer num = entry.getKey();
            // A+B出现的次数
            Integer numCount = entry.getValue();
            // -(C+D) 出现的次数
            Integer targetCount = sumMap2.get(-num);
            if (targetCount != null) {
                count += numCount * targetCount;
            }
        }

        return count;
    }

    /**
     * 两数和出现次数的哈希表
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return key: 两数和, value:两数和出现的次数
     */
    public Map<Integer, Integer> buildSumMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                int count = map.getOrDefault(sum, 0) + 1;
                map.put(sum, count);
            }
        }

        return map;
    }

    @Test
    public void TestIsHappy() {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
        System.out.println(isHappy(1));
        System.out.println(isHappy(100));

        System.out.println(isHappy2(19));
        System.out.println(isHappy2(2));
        System.out.println(isHappy2(1));
        System.out.println(isHappy2(100));


    }


    public boolean isHappy2(int n) {
        int slow = n;
        int fast = getValue(n);
        while (fast != 1 && fast != slow) {
            fast = getValue(fast);
            fast = getValue(fast);
            slow = getValue(slow);
        }

        return fast == 1;
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getValue(n);
        }

        return n == 1;
    }

    public int getValue(int n) {
        int res = 0;
        while (n != 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }

        return res;
    }

    @Test
    public void testIntersection() {
        int[] numArray1 = new int[]{1, 2, 2, 1};
        int[] numArray2 = new int[]{2, 2};
        printArray(intersection(numArray1, numArray2));
    }

    public void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    public void printArray(int[][] arrays) {
        for (int[] array : arrays) {
            printArray(array);
            System.out.println();
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        Set<Integer> set3 = new HashSet<>(Math.min(set1.size(), set2.size()));

        for (Integer integer : set1) {
            if (set2.contains(integer)) {
                set3.add(integer);
            }
        }


        return set3.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void testIsAnagram() {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
        System.out.println(isAnagram("aacc", "ccac"));

        System.out.println(isAnagram2("anagram", "nagaram"));
        System.out.println(isAnagram2("rat", "car"));
        System.out.println(isAnagram2("aacc", "ccac"));

        System.out.println(isAnagram3("anagram", "nagaram"));
        System.out.println(isAnagram3("rat", "car"));
        System.out.println(isAnagram3("aacc", "ccac"));


    }

    public boolean isAnagram3(String s, String t) {
        int[] nums = new int[26];
        if (s.length() != t.length()) {
            return false;
        }

        int length = s.length();
        for (int i = 0; i < length; i++) {
            nums[s.charAt(i) - 'a']++;
            nums[t.charAt(i) - 'a']--;
        }

        for (int num : nums) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> charMap1 = new HashMap<>(26);
        Map<Character, Integer> charMap2 = new HashMap<>(26);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer count = charMap1.getOrDefault(ch, 0) + 1;
            charMap1.put(ch, count);
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            Integer count = charMap2.getOrDefault(ch, 0) + 1;
            charMap2.put(ch, count);
        }

        for (Map.Entry<Character, Integer> entry : charMap1.entrySet()) {
            Integer integer = charMap2.get(entry.getKey());
            if (!entry.getValue().equals(integer)) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void TestRemoveNthFromEnd() {
        ListNode head = buildListNode(new int[]{1, 2});

        printListNode(removeNthFromEnd(head, 2));
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode();
        newHead.next = head;

        ListNode fast = newHead;
        ListNode slow = newHead;

        while (n-- > 0) {
            fast = fast.next;
        }

        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        pre.next = slow.next;
        return newHead.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        int index = 0;
        ListNode[] arr = new ListNode[50];

        while (head != null) {
            arr[index++] = head;
            head = head.next;
        }

        // 待删除节点索引
        int removeIndex = index - n;
        // 越界判断
        if (removeIndex < 0) {
            return arr[0];
        }

        // 如果删除的是头节点，那么头节点的next成为新的头结点
        if (removeIndex == 0) {
            return arr[0].next;
        }

        // 待删除节点的前节点
        ListNode pre = arr[removeIndex - 1];
        // 待删除节点的next
        ListNode next = arr[removeIndex].next;
        // 删除节点
        pre.next = next;

        return arr[0];
    }

    @Test
    public void testSwapPairs() {
        ListNode head = buildListNode(new int[]{2, 1, 4, 3, 5});
        ListNode newHead = swapPairs(head);

        printListNode(newHead);
    }


    public void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }

        System.out.println();
    }


    public ListNode buildListNode(int[] arr) {
        ListNode head = new ListNode();
        ListNode temp = head;

        for (int value : arr) {
            temp.next = new ListNode(value);
            temp = temp.next;
        }

        return head.next;
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode temp = newHead;
        ListNode pre = null;

        int index = 0;
        while (head != null) {
            index++;

            // 奇数位记录为前驱节点
            if ((index & 1) == 1) {
                pre = head;
            } else {
                // 偶数位交换节点
                temp.next = new ListNode(head.val);
                temp = temp.next;

                temp.next = new ListNode(pre.val);
                temp = temp.next;
            }

            head = head.next;
        }

        // 如果链表长度为奇数，最后一个节点未能配套交换，所以这里需要单独添加到新链表最后
        if ((index & 1) == 1) {
            temp.next = new ListNode(pre.val);
        }

        return newHead.next;
    }

    public ListNode reverseList3(ListNode head) {
        // 临时结点
        ListNode temp;
        // 前结点
        ListNode pre = null;
        // 当前结点
        ListNode current = head;
        while (current != null) {
            // 先保存当前结点的next
            temp = current.next;
            // 改变指针指向
            current.next = pre;

            // 移动指针
            pre = current;
            current = temp;
        }

        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        // pre = null, current = head
        return reverse(null, head);
    }

    public ListNode reverse(ListNode pre, ListNode current) {
        // 递归边界
        if (current == null) {
            return pre;
        }

        // 保存当前结点的next
        ListNode temp = current.next;
        // 改变指针指向
        current.next = pre;

        // 移动指针并递归
        return reverse(current, temp);
    }

    public ListNode reverseList(ListNode head) {
        // 哨兵节点
        ListNode node = new ListNode();
        while (head != null) {
            ListNode newNode = new ListNode(head.val);
            newNode.next = node.next;
            node.next = newNode;
            head = head.next;

        }

        return node.next;
    }

    @Test
    public void testMyLinkedList() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtTail(3);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(1, 2);
        System.out.println(myLinkedList.get(1));

        myLinkedList.deleteAtIndex(1);
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(100));

        myLinkedList.addAtIndex(-1, 0);
        myLinkedList.addAtIndex(3, 4);
        myLinkedList.addAtIndex(5, 5);
        System.out.println(myLinkedList.get(-100));

    }


    @Test
    public void testRemoveElements() {
        int[] headArr = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode();
        ListNode node = head;

        for (int i : headArr) {
            ListNode node1 = new ListNode();
            node1.val = i;
            node.next = node1;
            node = node1;
        }

//        while (head.next != null) {
//            head = head.next;
//            System.out.println(head.val);
//        }

        ListNode newHead = removeElements2(head.next, 6);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode();
        ListNode node = newHead;
        while (head != null) {
            if (head.val != val) {
                node.next = new ListNode(head.val);
                node = node.next;
            }

            head = head.next;
        }

        return newHead.next;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // 递归边界
        if (head == null) {
            return null;
        }

        // 递归
        head.next = removeElements2(head.next, val);

        // 删除元素
        return head.val == val ? head.next : head;
    }

    public static class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            String value = val + "";
            ListNode next = this.next;
            while (next != null) {
                value += "->" + next.val;
                next = next.next;
            }

            return value;
        }
    }

    @Test
    public void testGenerateMatrix() {
        int[][] ints = generateMatrix(1);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int count = 1;
        int i = 0;
        int j = 0;
        int[][] result = new int[n][n];
        while (count <= n * n) {
            // 向右
            while (j < n && result[i][j] == 0) {
                result[i][j] = count++;
                j++;
            }

            i++;
            j--;
            // 向下
            while (i < n && result[i][j] == 0) {
                result[i][j] = count++;
                i++;
            }

            j--;
            i--;
            // 向左
            while (j >= 0 && result[i][j] == 0) {
                result[i][j] = count++;
                j--;
            }

            i--;
            j++;
            // 向上
            while (i >= 0 && result[i][j] == 0) {
                result[i][j] = count++;
                i--;
            }

            j++;
            i++;
        }


        return result;
    }

    @Test
    public void testMinSubArrayLen() {
        int[] testCaseArr = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, testCaseArr));
    }


    public int minSubArrayLen2(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                int length = (end - start) + 1;
                result = Math.min(result, length);
                // 移动起指针，缩小窗口大小
                sum -= nums[start++];
            }
        }

        if (result == Integer.MAX_VALUE) {
            return 0;
        }

        return result;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    int length = (j - i) + 1;
                    result = Math.min(result, length);
                    break;
                }

            }
        }

        if (result == Integer.MAX_VALUE) {
            return 0;
        }
        return result;
    }


    @Test
    public void testRemoveElement() {
        int[] testCaseArr = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(testCaseArr, 2));
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int index = 0;
        while (index < length) {
            if (nums[index] == val) {
                nums[index] = nums[length - 1];
                length--;
            } else {
                index++;
            }
        }

        return length;
    }


    @Test
    public void testSearchInsert() {
        int[] testCaseArr = new int[]{1, 3, 5, 6};
        System.out.println(searchInsert(testCaseArr, 0));
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }

        return left;
    }

    @Test
    public void testDailyTemperatures() {
        int[] testCaseArr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] resArr = dailyTemperatures(testCaseArr);
        for (int value : resArr) {
            System.out.print(value + ",");
        }
    }

    public int[] dailyTemperatures(int[] T) {
        int[] resArr = new int[T.length];
        Arrays.fill(resArr, 0);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            int value = T[i];
            while (!stack.isEmpty() && T[stack.peek()] < value) {
                int index = stack.pop();
                resArr[index] = i - index;
            }

            stack.push(i);
        }

        return resArr;
    }

    @Test
    public void testCountBits() {
        int[] arr = countBits(5);
        for (int value : arr) {
            System.out.print(value + " ");
        }

    }

    @Test
    public void testConvertBinary() {
        System.out.println((hammingDistance(1, 0)));
    }


    public int[] countBits(int num) {
        int[] resultArr = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            resultArr[i] = resultArr[i >> 1] + (i & 1);
        }

        return resultArr;
    }

    /**
     * 461.汉明距离
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        List<Integer> binaryX = convertBinary(x);
        List<Integer> binaryY = convertBinary(y);

        // 找出长度较大的二进制，然后不足的补足0(虽然获取到的二进制list数据是反的，但是我们只需要比较相等位置的数字是否相等，所以不影响结果)
        int maxSize = Math.max(binaryX.size(), binaryY.size());
        if (binaryX.size() != maxSize) {
            while (binaryX.size() != maxSize) {
                binaryX.add(0);
            }
        } else {
            while (binaryY.size() != maxSize) {
                binaryY.add(0);
            }
        }

        // 相同位置不相等就加1
        int distance = 0;
        for (int i = 0; i < binaryX.size(); i++) {
            if (!binaryX.get(i).equals(binaryY.get(i))) {
                distance++;
            }
        }

        return distance;
    }

    /**
     * 将num转为二进制
     *
     * @param num 待转为二进制的十进制数
     * @return 二进制list(注意list顺序是反的)
     */
    private List<Integer> convertBinary(int num) {
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 2);
            num = num / 2;
        }

        return list;
    }

    @Test
    public void test() {
        int a = 1;
        int b = 4;

        int c = a ^ b;
        System.out.println(Integer.bitCount(a ^ b));
    }

}
