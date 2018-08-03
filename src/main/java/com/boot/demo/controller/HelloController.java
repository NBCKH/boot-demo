package com.boot.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenkaihua
 * @date 2018/8/1 15:14
 */
@RestController
@RequestMapping("/rest")
@SuppressWarnings("ALL")
public class HelloController extends BaseController {
//    @Autowired
//    BootUserMapper bootUserMapper;

    @GetMapping("/hello")
    public ResponseEntity getHelloController() {
//        return successObject(bootUserMapper.selectList(new QueryWrapper<>()));
        return null;
    }
}
