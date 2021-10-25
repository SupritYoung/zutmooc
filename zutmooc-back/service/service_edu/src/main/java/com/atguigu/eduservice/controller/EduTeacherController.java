package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(description = "讲师管理")
@RestController //Controller+ResponseBody 返回数据
@RequestMapping("/eduservice/teacher")
//@CrossOrigin   //解决跨域问题
public class EduTeacherController {
    //注入Service
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师表中所有数据
    //访问地址：http://localhost:8001/eduservice/teacher/findAll
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll") //rest风格
    public R findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null); //没有复杂查询，Wrapper传null即可
        return R.ok().data("items", list);
    }

    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                 @PathVariable String id){ //获取路径变量
        boolean flag = teacherService.removeById(id);
        if(flag)
            return R.ok();
        else
            return R.error();
    }

    //分页查询讲师
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}") //路径传值：当前页，每页记录数
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页，将分页所有数据封装到pageTeacher对象中
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();    //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合

        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
        //第二种方式（两种方式均可）
//      return R.ok().data("total", total).data("rows", records);
    }

    //带分页的条件查询讲师
    @ApiOperation(value = "分页条件查询讲师")
    //用@RequestBody必须改用Post方式（GET是从请求行中取值
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
                                  //@RequestBody：帮忙把数据以json格式封装到teacherQuery中
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //创建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //wrapper构建多条件查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            //这个地方用是数据库里的字段
            wrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher, wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    //添加讲师
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //根据讲师ID进行查询
    @ApiOperation(value = "根据讲师ID进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    //修改讲师
    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

