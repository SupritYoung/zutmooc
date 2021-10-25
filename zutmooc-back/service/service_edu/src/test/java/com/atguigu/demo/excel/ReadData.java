package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReadData {
    //设置列对应的属性
    @ExcelProperty(index = 0)
    private int sid;

    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;
}
