package cn.edu.pzhu.mytest;

import cn.edu.pzhu.mytest.bean.Node;
import cn.edu.pzhu.mytest.bean.Person;
import cn.edu.pzhu.mytest.bean.QQChatListener;
import cn.edu.pzhu.mytest.bean.Son;
import cn.edu.pzhu.mytest.bean.TreeNode;
import cn.edu.pzhu.mytest.bean.TreeNodeUtils;
import cn.edu.pzhu.mytest.bean.WeChatListener;
import cn.edu.pzhu.mytest.leetcode.MinStack;
import cn.edu.pzhu.mytest.proxy.PersonCgLibProxy;
import cn.edu.pzhu.mytest.proxy.SportInvocationHandler;
import cn.edu.pzhu.mytest.proxy.SportService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest
class MyTestApplicationTests {

    @Autowired
    private SportService sportService;

    @Autowired
    private SportInvocationHandler sportInvocationHandler;

    @Test
    public void parseKaExcel() {
        String path = "/Users/hfy/Desktop/2024ka.xlsx";

    }

    @Test
    public void testSecondOfDay() {
        DateTime dateTime = determineDateStart("202402");
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

    }

    private DateTime determineDateStart(String month) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMM");
        DateTime parseDateTime = dateTimeFormatter.parseDateTime(month);
        DateTime now = DateTime.now();
        if (now.getMonthOfYear() == parseDateTime.getMonthOfYear()) {
            return now;
        }

        return parseDateTime;
    }

    @Test
    public void testConcurrentHashMap() {
        System.out.println(new String("ABC") == "ABC");


    }

    @Test
    public void testReduce() {
        List<Son> list = new ArrayList<>();
        System.out.println(list.stream().map(Son::getBigDecimal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
    }

    @Test
    @SneakyThrows
    public void testSerializable() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(write(new Node(123)));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Node node = (Node) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(node);
    }


    @SneakyThrows
    private byte[] write(Node node) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(node);
        objectOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Test
    public void testString() {
        String s1 = new String("1") + new String("1");
        System.out.println(System.identityHashCode(s1));
        s1.intern();
        String s2 = "11";
        System.out.println(s1 == s2);
        System.out.println(System.identityHashCode(s2));

        String s3 = "11";
        System.out.println(s3 == s2);
        System.out.println(System.identityHashCode(s3));
    }


    @Test
    public void testSplit() {
        String s = "123sad123";
        String[] strings = s.split(",");
        for (String string : strings) {
            System.out.println(string);
        }

    }


    @Test
    public void testReference() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");

        list.forEach(System.out::println);
        System.out.println("--------");
        refreshList(list);
        list.forEach(System.out::println);
    }

    private void refreshList(List<String> list) {
        list = new ArrayList<>();
        list.add("CG");
    }


    @Test
    public void testAviator() {
        String rule = "16<x";
//        AviatorEvaluator.getInstance().setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        AviatorEvaluator.getInstance().setOption(Options.TRACE_EVAL, true);
        Expression expression = AviatorEvaluator.compile(rule, true);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x", new BigDecimal("16.1"));
        System.out.println(expression.execute(paramMap));
    }

    @Test
    @SneakyThrows
    public void testScriptEngineManager2() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");

        String express = "12000.0110<=X<=12000.0130";
        Bindings bindings = new SimpleBindings();
        bindings.put("X", "12000.010");
        Object eval = scriptEngine.eval(express, bindings);
        System.out.println(eval);
    }


    @Test
    public void testExpress() {
        String express = "12000.011<=#X&&#X<=12000.013";
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("X", new BigDecimal("12000.013"));
        Boolean value = parser.parseExpression(express).getValue(context, boolean.class);
        System.out.println(value);
    }

    @Test
    @SneakyThrows
    public void testScriptEngineManager() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
        for (ScriptEngineFactory engineFactory : engineFactories) {
            System.out.printf("%s:%s%n", engineFactory.getEngineName(), engineFactory.getEngineVersion());
        }

        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
        Object eval = scriptEngine.eval("3+2*(1+2*(-4/(8-6)+7))");
        System.out.println(eval);
    }


    // /aa/bb/../cc
    // /aa/cc


    // A B C D
    // AA AB AC AD
    // BA BB BC BD
    //...

    @Test
    public void testGetTitle() {
        System.out.println(getTitle(1));
        System.out.println(getTitle(26));
        System.out.println(getTitle(27));
    }

    public String getTitle(int column) {
        Stack<String> stack = new Stack<>();
        while (column > 0) {
            column--;
            stack.push(getChar(column % 26));
            column /= 26;
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = stack.pop() + result;
        }

        return result;
    }

    public String getChar(int n) {
        char c = (char) ('A' + n);
        return String.valueOf(c);
    }


    @Test
    public void testMockCd() {
        System.out.println(mockCd("/aa/bb/../../cc"));
        System.out.println(mockCd("/aa/bb/../cc"));

        System.out.println(mockCd2("/aa/bb/../../cc"));
        System.out.println(mockCd2("/aa/bb/../cc"));
    }


    public String mockCd(String srcDir) {
        int index = srcDir.indexOf("..");
        if (index == -1) {
            return srcDir;
        }

        String dest = srcDir.substring(index + 2);
        String pre = srcDir.substring(0, index - 1);

        int lastIndex = pre.lastIndexOf("/");
        pre = pre.substring(0, lastIndex);

        return mockCd(pre + dest);
    }

    public String mockCd2(String srcDir) {
        String[] split = srcDir.split("/");
        LinkedList<String> list = new LinkedList<>();
        for (String s : split) {
            if (s.equals("..")) {
                list.removeLast();
            } else {
                list.add(s);
            }
        }

        return String.join("/", list);
    }


    @Test
    public void testDfs() {
        List<String> pathList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();

        dfs(pathList, resultList, "123");
        resultList.stream().forEach(System.out::println);

    }

    public void dfs(List<String> pathList, List<String> resultList, String s) {
        char[] chars = s.toCharArray();
        if (pathList.size() == chars.length) {
            resultList.add(String.join("", pathList));
            return;
        }

        for (char c : chars) {
            String aChar = String.valueOf(c);
            if (!pathList.contains(aChar)) {
                pathList.add(aChar);
                dfs(pathList, resultList, s);
                pathList.remove(aChar);
            }
        }

    }


    @Test
    public void test() {
        String declarationIds = "130759756,130759758,130759761,130759768,130759771,130759785,130759786,130759787,130851378,130851394,130851396,130947300,130947306,130947315,130947320,130947321,130947333,130947335,130947341,130947342,130947346,131025645,131025655,131025657,131025667,131025668,131025672,131025674,141606538,144124630,144221126,144301542,145573147,148471612,155284790,164267843,172110133";
        String incomeDeclarationIds = "130759756,130759758,130759761,130759768,130759771,130759785,130759786,130759787,130759788,130851378,130851394,130851396,130947300,130947306,130947315,130947320,130947321,130947333,130947335,130947341,130947342,130947346,131025645,131025655,131025657,131025667,131025668,131025672,131025674,141606538,144124630,144221126,144301542,145573147,148471612,155284790,164267843,172110133";

        String[] split = declarationIds.split(",");
        String[] split2 = incomeDeclarationIds.split(",");

        Set<String> stringSet = Arrays.stream(split).collect(Collectors.toSet());
        for (String s : split2) {
            if (!stringSet.contains(s)) {
                System.out.println(s);
            }
        }

    }


    @Test
    public void testSort1() {
        String test = "Type";
        String test2 = "BabA";
        System.out.println(sort1(test));
        System.out.println(sort1(test2));

        char c = 'a';
        System.out.println(((int) c));

    }

    public String sort2(String s) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length - 1; i++) {
            char a = array[i];
            if (a < 91) {
                a += 32;
            }

            for (int j = i + 1; j < array.length; j++) {
                char b = array[j];
                if (b < 91) {
                    b += 32;
                }

                if (a > b) {
                    char temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

        }

        return new String(array);
    }

    public String sort1(String s) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length - 1; i++) {
            char a = array[i];
            if (a < 91) {
                a += 26;
            }

            for (int j = i + 1; j < array.length; j++) {
                char b = array[j];
                if (b < 91) {
                    b += 26;
                }

                if (a > b) {
                    char temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

        }

        return new String(array);
    }


    @Test
    public void dealWithZip() {
        File file = new File("/Users/zhangchao/Desktop/新建文件夹");
        for (File excelFile : file.listFiles()) {
            editExcel(excelFile);
        }

    }

    public void editExcel(File file) {
        try (FileInputStream inputStream = new FileInputStream(file);
             HSSFWorkbook wb = new HSSFWorkbook(inputStream);) {
            HSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNumber = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNumber; i++) {
                HSSFRow row = sheet.getRow(i);

                // 末尾新增一列
                createCellAfterRow(row);
                // 添加*
                appendCharIfNecessary(row);

                // 向右移动
                rightMove(row);

                // 并设置第一个单元格为【工号】
                setEmployeeNumber(row);

                // 交换
                change45And46(row);
            }
            wb.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void change45And46(HSSFRow row) {
        HSSFCell cell44 = row.getCell(44);
        HSSFCell cell45 = row.getCell(45);
        String temp = cell44.getStringCellValue();
        cell44.setCellValue(cell45.getStringCellValue());
        cell45.setCellValue(temp);
    }

    private void setEmployeeNumber(HSSFRow row) {
        HSSFCell firstCell = row.getCell(0);
        if (row.getRowNum() == 0) {
            firstCell.setCellValue("工号");
        } else {
            firstCell.setCellValue("");
        }
    }

    private void rightMove(HSSFRow row) {
        int lastCellNumber = row.getLastCellNum();
        for (int i = lastCellNumber - 2; i >= 0; i--) {
            HSSFCell pre = row.getCell(i);
            HSSFCell cell = row.getCell(i + 1);
            if (pre == null) {
                pre = row.createCell(i);
            }

            if (cell == null) {
                cell = row.createCell(i + 1);
            }

            cell.setCellValue(pre.getStringCellValue());
        }
    }

    private void appendCharIfNecessary(HSSFRow row) {
        if (row.getRowNum() != 0) {
            return;
        }

        for (int j = 0; j < 8; j++) {
            HSSFCell cell = row.getCell(j);
            String value = cell.getStringCellValue();
            cell.setCellValue("*" + value);
        }
    }

    private void createCellAfterRow(HSSFRow row) {
        short lastCellNum = row.getLastCellNum();
        row.createCell(lastCellNum);
    }


    @Test
    public void testArrayBlockingQueue() {

        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(10);

        Thread thread1 = new Thread(() -> {
            try {
                System.out.printf("线程：%s,执行take之前...\n", Thread.currentThread().getName());
                queue.take();
                System.out.printf("线程：%s,执行take结束...\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(() -> {
            System.out.printf("线程：%s,执行offer之前...\n", Thread.currentThread().getName());
            queue.offer("cg");
            System.out.printf("线程：%s,执行offer结束...\n", Thread.currentThread().getName());
        });

        thread2.start();
    }


    @Test
    public void testValue() {
        int a = 123;
        char c = 'c';
        double d = 123.33;

        change(a, c, d);
        System.out.println(a);
        System.out.println(c);
        System.out.println(d);

        String str = "zhanngcz";
        change(str);
        System.out.println(str);

        Person person = new Person("zhangcz");
        changePerson(person);
        System.out.println(person);

    }

    private void changePerson(Person person) {
        person = new Person("ccg");
    }

    private void change(String str) {
        str = new String("cg");
    }

    private void change(int a, char c, double d) {
        a = 456;
        c = 'g';
        d = 444.445;
    }

    @Test
    public void testImmutable() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person("zc");
        personList.add(person);

        Set<Person> personSet = ImmutableSet.copyOf(personList);
        System.out.println(personSet);

        person.setName("cg");
        System.out.println(personSet);
    }

    @Test
    public void testRemove() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Iterator<Integer> iterator1 = list.iterator();
        Iterator<Integer> iterator2 = list.iterator();
        iterator1.next();
        iterator1.remove();
        iterator2.next();

        System.out.println(list);
    }

    @Test
    public void testEventBus() {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("zcThread").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000l,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );


        EventBus eventBus = new AsyncEventBus(MoreExecutors.directExecutor(), (exception, context) -> {
            System.out.println("method:" + context.getSubscriberMethod().getName());
            throw new RuntimeException(exception.getMessage());
        });

        eventBus.register(new WeChatListener());
        eventBus.register(new QQChatListener());

        eventBus.post("小超很棒");
        System.out.println("ending");
    }

    private List<Integer> tempList = new ArrayList<>();
    private List<List<Integer>> resultList = new ArrayList<>();


    @Test
    public void testTree() {
        TreeNode root = new TreeNode(0, null, null, null);
        TreeNode treeNode1 = new TreeNode(1, 0, null, null);
        TreeNode treeNode2 = new TreeNode(2, 0, null, null);
        TreeNode treeNode3 = new TreeNode(3, 0, null, null);
        TreeNode treeNode4 = new TreeNode(4, 1, null, null);
        TreeNode treeNode5 = new TreeNode(5, 1, null, null);


        List<TreeNode> list = Lists.newArrayList(treeNode1, treeNode2, treeNode3, treeNode4, treeNode5);
        TreeNode treeNode = TreeNodeUtils.build(list, new TreeNode(0, null, null, null));

        System.out.println(JSON.toJSONString(treeNode));

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 1);
        map.put(5, 1);

        TreeNodeUtils.traverse(treeNode, treeNodeId -> {
            System.out.println("当前节点id：" + treeNodeId);
            System.out.println("当前父节点id：" + map.get(treeNodeId));
            System.out.println();

        });

        TreeNode treeNode6 = TreeNodeUtils.find(treeNode, 5);
        System.out.println("从下向上遍历节点：");
        TreeNodeUtils.traverseWithReverse(treeNode6, treeNodeId -> {
            System.out.print(treeNodeId + "->");
        });
    }


    @Test
    public void testStack() {
        MinStack minStack = new MinStack();
        minStack.push(-1);
        minStack.top();
        System.out.println(minStack.getMin());
        System.out.println(minStack.getMin());
    }

    @Test
    public void testInteger() {
        System.out.println(Integer.toBinaryString(2));
    }

    @Test
    public void testSubsets() {
        int[] nums = new int[]{1, 2, 3, 4};
        for (List<Integer> subset : subsets(nums)) {
            System.out.println(subset);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> tempList = new ArrayList<>();
        List<List<Integer>> resultList = new ArrayList<>();

        dfs(0, nums, resultList, tempList);
        return resultList;
    }

    private void dfs(int n, int[] nums, List<List<Integer>> resultList, List<Integer> tempList) {
        // 保存结果
        resultList.add(new ArrayList<>(tempList));

        for (int i = n; i < nums.length; i++) {
            // 选择当前值
            tempList.add(nums[i]);
            // 递归下一层
            dfs(i + 1, nums, resultList, tempList);
            // 回滚当前值(回溯到上一层状态)
            tempList.remove(tempList.size() - 1);
        }
    }

    public void dfs2(int n, int[] nums) {
        if (n == nums.length) {
            resultList.add(new ArrayList<>(tempList));
            return;
        }

        tempList.add(nums[n]);
        dfs2(n + 1, nums);

        tempList.remove(tempList.size() - 1);
        dfs2(n + 1, nums);
    }

    @Test
    public void testTowSum() {
        int[] nums = new int[]{3, 3};
        int target = 6;

        int[] ints = twoSum(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        // 注意：这里遍历时，i 和 j 不能存在相等的情况
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    @Test
    public void testCgLib() {
        Person person = new Person("cg");

        Person personProxy = (Person) new PersonCgLibProxy(person).getInstance();
        personProxy.testPublic();


    }


    @Test
    public void testProxy() {
        System.out.println(sportService.getClass());
        sportService.running();
        sportService.playGames();

        System.out.println("----------");

        SportService sportServiceProxy = (SportService) Proxy.newProxyInstance(sportService.getClass().getClassLoader(), sportService.getClass().getInterfaces(), sportInvocationHandler);
        System.out.println(sportServiceProxy.getClass());
        sportServiceProxy.running();
        sportServiceProxy.playGames();
//        sportService.playGames();
    }

    @Test
    public void testPrivate() {
        Son son = new Son("1");
//        son.
    }

    @Test
    void contextLoads() {
        Son son = new Son("son1");
        System.out.println(son);

        Person person = new Person("tom");
//        person.
//        person.
    }

}
