package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
//        //设置文件路径
//        String filename = "/Volumes/ssd1 1/tmp/tmp.xlsx";
//        //调用方法实现写操作
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());

        //调用方法实现读操作
        String filename = "/Volumes/ssd1 1/tmp/tmp.xlsx";
        EasyExcel.read(filename, ReadData.class, new ExcelListener()).sheet().doRead();

    }

    //返回一个随机的list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("No"+i);
            list.add(data);
        }
        return list;
    }

}
