package com.paofu.data_structure.demo;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.excel.EasyExcel;
import com.paofu.data_structure.demo.day01.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/18 11:17
 */
public class Test {

    public static void main(String[] args) {
        HttpResponse response = HttpRequest.post("https://api.jisuapi.com/country/query?appkey=c0a5e5b5cf7e445d").execute();
        JSONObject jsonObject = new JSONObject(response.body());
        List<JSONObject> result = (List<JSONObject>) jsonObject.get("result");
        List<Country> list = new ArrayList<>();
        for (JSONObject object : result) {
            Object currency = object.get("currency");
            if (!ObjectUtil.equals(currency, null)) {
                Country country = new Country();
                country.setName((String) object.get("name"));
                country.setCname((String) object.get("cname"));
                country.setEname((String) object.get("ename"));
                country.setCurrency((String) currency);
                list.add(country);
            }
//            Object isCountry = object.get("iscountry");
//            if (!ObjectUtil.equals(isCountry, null)) {
//                if ((Integer) object.get("iscountry") == 1) {
//                    Country country = new Country();
//                    country.setName((String) object.get("name"));
//                    country.setCname((String) object.get("cname"));
//                    country.setEname((String) object.get("ename"));
//                    Object currency = object.get("currency");
//                    if (!ObjectUtil.equals(currency, null)) {
//                        country.setCurrency((String) currency);
//                    }
//                    list.add(country);
//                }
//            }
        }
        String fileName = System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, Country.class).sheet("国家货币表").doWrite(list);
    }
}
