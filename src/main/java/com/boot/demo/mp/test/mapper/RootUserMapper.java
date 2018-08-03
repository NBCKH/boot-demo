package com.boot.demo.mp.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.demo.mp.test.entity.RootUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenkaihua
 * @date 2018/8/3 17:08
 */
@Mapper
public interface RootUserMapper extends BaseMapper<RootUser> {
}
