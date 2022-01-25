package com.paofu.data_structure.demo.day01;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: 泡芙和树
 * @Date: 2022/1/18 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @ColumnWidth(30)
    @ExcelProperty(value = "国家名称")
    private String name;

    @ColumnWidth(30)
    @ExcelProperty(value = "中文名称")
    private String cname;

    @ColumnWidth(30)
    @ExcelProperty(value = "英文名称")
    private String ename;

    @ExcelProperty(value = "国家货币")
    @ColumnWidth(30)
    private String currency;
}
