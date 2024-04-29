package com.freerun.user.controller;


import com.freerun.common.domain.dto.PageDTO;
import com.freerun.user.domain.dto.StudentFormDTO;
import com.freerun.user.domain.query.UserPageQuery;
import com.freerun.user.domain.vo.StudentPageVo;
import com.freerun.user.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学员详情表 前端控制器
 */
@RestController
@RequestMapping("/students")
@Api(tags = "用户管理接口")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation("分页查询学生信息")
    @GetMapping("/page")
    public PageDTO<StudentPageVo> queryStudentPage(UserPageQuery pageQuery){
        return studentService.queryStudentPage(pageQuery);
    }

    @ApiOperation("学员注册")
    @PostMapping("/register")
    public void registerStudent(@RequestBody StudentFormDTO studentFormDTO) {
        studentService.saveStudent(studentFormDTO);
    }

    @ApiOperation("修改学员密码")
    @PutMapping("/password")
    public void updateMyPassword(@RequestBody StudentFormDTO studentFormDTO) {
        studentService.updateMyPassword(studentFormDTO);
    }
}
