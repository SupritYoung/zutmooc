package com.atguigu.staservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author zheng
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/staservice/statistics")
//@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    StatisticsDailyService sta;
    //统计某一天的注册人数
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day){
        sta.registerCount(day);
        return R.ok();
    }
    //返回日期的json数据
    @GetMapping("showData/{type}/{begin}/{end}")
    public R show(@PathVariable String type,@PathVariable String begin,
                       @PathVariable String end){
        Map<String,Object> map = sta.getData(type,begin,end);
        return R.ok().data(map);
    }

}

