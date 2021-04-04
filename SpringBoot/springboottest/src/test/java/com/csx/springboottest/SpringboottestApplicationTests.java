package com.csx.springboottest;

import com.csx.springboottest.bean.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@SpringBootTest
class SpringboottestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    /**
     * 选择排序：从第一个元素开始，依次与其他所有元素作比较，发现比自身大/小，就彼此交换位置，那么第一轮循环下来，
     * 就找到最大/小元素；
     *
     * @param
     * @return void
     * @Author Cuisx
     * @Date 19:10 2021/3/4
     */
    @Test
    public void test04() {

        int[] arr = new int[]{1, 10, 2, 3, 9, 0, 5, 3};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int aa;
                    aa = arr[j];
                    arr[j] = arr[i];
                    arr[i] = aa;
                }
                System.out.println("内层循环>>>： " + j + Arrays.toString(arr));
            }
            System.out.println("\n");
            //System.out.println("外层循环"+(i+1)+": "+Arrays.toString(arr));
        }
    }


    /**
     * 冒泡排序：相邻两个元素作比较，交换位置；一次外层循环后会找出最大/最小值；
     *
     * @param
     * @return void
     * @Author Cuisx
     * @Date 19:09 2021/3/4
     */
    @Test
    public void test03() {

        int[] arr = new int[]{1, 10, 2, 3, 9, 0, 5, 3};

        int num = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int change;
                    change = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = change;
                    flag = false;
                }
                if (flag) {
                    continue;
                }
                num++;
                System.out.println("内层循环>>> 第" + i + "次循环： " + Arrays.toString(arr));
            }
            System.out.println("num: " + num);

        }


    }

    @Test
    public void test02() {

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key01", 001);
        map.put("key02", 002);
        map.put("key03", 003);
        map.put("key04", 004);
        map.put("key05", 005);

        //1.方式一：在for循环中使用entries实现Map的遍历：
        System.out.println("方式一：在for循环中使用entries实现Map的遍历：");
        for (Map.Entry<String, Integer> result : map.entrySet()) {
            System.out.println(result);
        }
        System.out.println("\n");

        //2.方式二：在for循环中遍历key或者values，一般适用于只需要map中的key或者value时使用，在性能上比使用entrySet较好；
        System.out.println("方式二：在for循环中遍历key或者values，一般适用于只需要map中的key或者value时使用，在性能上比使用entrySet较好；");
        for (String key : map.keySet()) {
            System.out.println("key: " + key);
        }
        System.out.println("\n");
        for (Integer value : map.values()) {
            System.out.println("value: " + value);
        }

        System.out.println("\n");
        System.out.println("方式三：通过Iterator遍历；");
        //3.方式三：通过Iterator遍历；
        Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            System.out.println("entry: " + entry);
            System.out.println("entry.key: " + entry.getKey());
            System.out.println("entry.value: " + entry.getValue());
        }

        //4.方式四：通过键找值遍历，这种方式的效率比较低，因为本身从键取值是耗时的操作；
        System.out.println("方式四：通过键找值遍历，这种方式的效率比较低，因为本身从键取值是耗时的操作；");
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }


    }


    @Test
    public void testLog() {
        logger.trace(">>>>>>>>>>trace");
        logger.debug(">>>>>>>>>>debug");
        logger.info(">>>>>>>>>>info");
        logger.warn(">>>>>>>>>>warn");
        logger.error(">>>>>>>>>>error");
    }

    @Test
    public void test01() {
        boolean boo = ioc.containsBean("ppppp");
        System.out.println(boo);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
