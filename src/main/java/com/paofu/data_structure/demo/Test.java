package com.paofu.data_structure.demo;


import cn.hutool.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;


/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/18 11:17
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        List<JSONObject> list = new CopyOnWriteArrayList<>();
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                8,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.putOpt("name", Thread.currentThread().getName());
                jsonObject.putOpt("id", UUID.randomUUID().toString().substring(0,8));
                list.add(jsonObject);
                countDownLatch.countDown();
            });

        }
        threadPool.shutdown();

        countDownLatch.await();

        System.out.println(list.size());

    }
}
