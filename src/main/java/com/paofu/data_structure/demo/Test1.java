package com.paofu.data_structure.demo;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/24 17:29
 */
@Slf4j
public class Test1 {

    static List<HashMap<String, Object>> list = new ArrayList<>();

    public static void main(String[] args) {
        String test = "12f3A";
//        System.out.println(test.matches(".*[0-9].*"));
//        System.out.println(test.matches(".*[a-z].*"));
//        System.out.println(test.matches(".*[A-Z].*"));

//        String str1 = "3157,3518,3519";
//        String str2 = "";
//        String[] list1 = str1.split(",");
//        String[] list2 = str2.split(",");
//        List<String> list3 = Arrays.stream(list1).collect(Collectors.toList());
//        List<String> list4 = Arrays.stream(list2).collect(Collectors.toList());
//        List<String> sadasd = list3.stream().filter((u) -> {
//            log.info("sadasd");
//            return u != null;
//        }).collect(Collectors.toList());
//
//        list3.retainAll(list4);
//        System.out.println(list3);

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        System.out.println(list.stream().anyMatch((u) -> u == 3));

//        System.out.println(test());
//        String str = "1,2,3,4,5";
//        List<String> collect = Arrays.stream(str.split(",")).collect(Collectors.toList());
//        System.out.println(collect);
//        for (String s : collect) {
//            System.out.println(s);
//        }
        String a = "111";
        String b = "222";
        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        System.out.println(StrUtil.join(",", list));

    }

    public static int test() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                return 0;
            }
        }
        return 1;
    }
}
