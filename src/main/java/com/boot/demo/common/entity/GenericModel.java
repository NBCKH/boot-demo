package com.boot.demo.common.entity;

import com.baomidou.mybatisplus.annotations.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by filamoon on 2018/5/24
 */
@Getter
@Setter
public class GenericModel extends GenericReadOnlyModel {

    protected Date updated;

    protected Date deleted;

    @Version
    protected Integer version;
}
