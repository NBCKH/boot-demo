package com.boot.demo.mp.test.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.boot.demo.common.entity.GenericModel;

/**
 * @author chenkaihua
 * @date 2018/8/3 17:00
 */
@TableName("boot_user")
public class RootUser extends GenericModel {
    @TableField(value = "refId")
    private String refId;
    private String username;
    private String password;
    private String salt;
    private String name;
    @TableField(value = "idCardNo")
    private String idCardNo;
}
