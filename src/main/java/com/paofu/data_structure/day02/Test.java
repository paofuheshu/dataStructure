package com.paofu.data_structure.day02;

import lombok.Data;

/**
 * @author zhangqiang
 * @version 1.0
 * @date 2021/12/6 21:15
 */
@Data
public class Test {

    public String name;

    public Test(String name) {
        this.name = name;
    }
}
