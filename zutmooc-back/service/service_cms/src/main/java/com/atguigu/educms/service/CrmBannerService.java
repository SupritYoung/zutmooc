package com.atguigu.educms.service;

import com.atguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
