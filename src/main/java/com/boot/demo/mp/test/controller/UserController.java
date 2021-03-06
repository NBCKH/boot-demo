package com.boot.demo.mp.test.controller;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.boot.demo.controller.BaseController;
import com.boot.demo.mp.test.entity.RootUser;
import com.boot.demo.mp.test.mapper.RootUserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenkaihua
 * @date 2018/8/3 17:10
 */
@RestController
@RequestMapping("/rest/user")
@SuppressWarnings("ALL")
public class UserController extends BaseController {
    @Resource
    RootUserMapper rootUserMapper;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        List<RootUser> rootUsers = rootUserMapper.selectList(new Wrapper<RootUser>() {
            @Override
            public String getSqlSegment() {
                return "";
            }
        });
        return successObject(rootUsers);
    }

    @GetMapping("/getByPage")
    public ResponseEntity getUserByPage(){
        Page<RootUser> page = new Page<>(1,2);
        List<RootUser> rootUsers = rootUserMapper.selectPage(page,null);
        page.setRecords(rootUsers);
        return successObject(rootUsers);
    }
}
