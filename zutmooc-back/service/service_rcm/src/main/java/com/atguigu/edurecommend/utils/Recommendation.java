package com.atguigu.edurecommend.utils;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

@Component
public class Recommendation {
    public static int RECOMMENDCOUNT = 5;   //生成的推荐数量

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //通过计算余弦相似度并取TopN, 返回为uid的用户生成的若干个推荐物品
    public ArrayList<EduCourse> getRecommend(String uid){

        //所有用户列表
        ArrayList<UcenterMember> users = ucenterMemberService.getAllUsers();
        //所有视频列表
        ArrayList<EduCourse> items = eduCourseService.getAllItems();


        //共现矩阵（C[i][j]表示既喜欢i又喜欢j的人数）
        int[][] C = new int[items.size()][items.size()];
        //喜欢数量（N[i]表示喜欢第i个物品第人数）
        int[] N = new int[items.size()];

        //将离散的物品id连续化处理
        HashMap<String, Integer> idMap = new HashMap<>();   
        for (int i = 0; i < items.size(); i++) {
            idMap.put(items.get(i).getId(), i);
        }

        //求共现矩阵
        for(UcenterMember user: users){
            //当前用户则跳过
            if(user.getId() == uid) continue;
            //当前用户的喜欢列表
            ArrayList<EduCourse> likeItems = orderService.findLikesItemsByUser(user.getId());

            //针对每个用户的喜欢列表，两两加一
            for(int i = 0; i < likeItems.size(); i++){
                //喜欢第i个物品第人数+1
                N[idMap.get(likeItems.get(i).getId())] += 1;
                for(int j = 0; j < i; j++){
                    C[idMap.get(likeItems.get(i).getId())][idMap.get(likeItems.get(j).getId())] += 1;
                    C[idMap.get(likeItems.get(j).getId())][idMap.get(likeItems.get(i).getId())] += 1;
                }
            }
        }

        //预处理列表，用来作降序排列
        TreeSet<EduCourse> preList = new TreeSet<EduCourse>(new Comparator<EduCourse>() {
            @Override
            public int compare(EduCourse o1, EduCourse o2) {
                if(o1.getW() < o2.getW()){
                    return -1;
                }else if(o1.getW() == o2.getW()){
                    return 0;
                }else{
                    return 1;
                }
            }
        });

        //当前用户喜欢的视频列表
        ArrayList<EduCourse> likeItems = orderService.findLikesItemsByUser(uid);
        boolean[] isLike = new boolean[items.size()];  //判重数组，是否已经喜欢
        for (int i = 0; i < likeItems.size(); i++) {
            //当前物品已经在喜欢列表中，无需再计算相似度
            if (isLike[idMap.get(items.get(i).getId())])
                continue;
            //对于当前用户喜欢的每个物品，计算和其他没有喜欢过的物品的相似度
            for (int j = 0; j < items.size(); j++) {
                if (isLike[idMap.get(items.get(j).getId())])
                    continue;

                int ii = idMap.get(items.get(i).getId()), jj = idMap.get(items.get(j).getId());
                double Wij = (double) C[i][j] / Math.sqrt(N[ii] * N[jj]); //计算余弦相似度

                //只在相似度更大时替换，因为我们的目的是推荐，不是算矩阵
                if (items.get(j).getW() == null || Wij > items.get(j).getW()) {
                    items.get(j).setW(Wij);
                    items.get(j).setReason("看过了" + items.get(i).getTitle() + "课程的用户大多也观看了该课程");
                }
            }
        }

        //将最终的相似度存入列表并降序排序
        for (EduCourse item : items) {
            if(item.getW() != null && item.getW() > 0)
                preList.add(item);
        }

        //取相似度最大的若干个物品作为推荐结果
        ArrayList<EduCourse> recommendResult = new ArrayList<>();
        for(int i = 0; preList.size()>0 && i<RECOMMENDCOUNT; i++){
            recommendResult.add(preList.pollLast());
            preList.pollLast();
        }

        return recommendResult;
    }
}