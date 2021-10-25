package com.atguigu.staservice.service.impl;

import com.alibaba.nacos.client.naming.utils.RandomUtils;
import com.atguigu.commonutils.R;
import com.atguigu.staservice.client.UcenterClient;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.mapper.StatisticsDailyMapper;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author zheng
 * @since 2021-05-04
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    UcenterClient ucenterClient;
    @Override
    public void registerCount(String day) {
        //先删除,然后再插入，维护数据最新
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        //远程调用获取统计数量
        R R = ucenterClient.getCountResigiter(day);
        Integer countRegister = (Integer) R.getData().get("countRegister");

        //将数据添加到数据库
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);//注册人数
        sta.setDateCalculated(day);//统计日期
        sta.setLoginNum(RandomUtils.nextInt(200));
        sta.setVideoViewNum(RandomUtils.nextInt(300));
        sta.setCourseNum(RandomUtils.nextInt(200));
        baseMapper.insert(sta);
    }

    @Override
    public Map<String, Object> getData(String type, String begin, String end) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("date_calculated",type);
        wrapper.between("date_calculated",begin,end);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        List<String> date = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        int n = staList.size();
        for(int i = 0;i < n;i ++){
            date.add(staList.get(i).getDateCalculated());
            if(type.equals("course_num")){//每日新增课程数
                count.add(staList.get(i).getCourseNum());
            }else if(type.equals("login_num")){//登录人数
                count.add(staList.get(i).getLoginNum());
            }else if(type.equals("register_num")){//注册人数
                count.add(staList.get(i).getRegisterNum());
            } else {
                count.add(staList.get(i).getVideoViewNum());
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("date",date);
        map.put("count",count);
        return map;
    }
}
