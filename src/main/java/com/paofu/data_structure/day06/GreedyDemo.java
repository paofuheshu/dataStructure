package com.paofu.data_structure.day06;

import java.util.*;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2022/1/5 18:50
 * 贪心算法示例
 */
public class GreedyDemo {

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>(16);
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> set3 = new HashSet<>();
        Set<String> set4 = new HashSet<>();
        Set<String> set5 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        set4.add("上海");
        set4.add("天津");
        set5.add("杭州");
        set5.add("大连");
        map.put("K1", set1);
        map.put("K2", set2);
        map.put("K3", set3);
        map.put("K4", set4);
        map.put("K5", set5);

        Set<String> allArea = new HashSet<>();
        allArea.addAll(set1);
        allArea.addAll(set2);
        allArea.addAll(set3);
        allArea.addAll(set4);
        allArea.addAll(set5);

        List<String> list = new ArrayList<>();

        Set<String> temp = new HashSet<>();
        String maxKey = null;
        while (allArea.size() != 0) {
            maxKey = null;
            for (String key : map.keySet()) {
                temp.clear();
                Set<String> areas = map.get(key);
                temp.addAll(areas);
                // 求交集,并会赋给temp
                temp.retainAll(allArea);
                if (temp.size() > 0 && (maxKey == null || temp.size() > map.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                list.add(maxKey);
                allArea.removeAll(map.get(maxKey));
            }
        }
        System.out.println(list);
    }
}
