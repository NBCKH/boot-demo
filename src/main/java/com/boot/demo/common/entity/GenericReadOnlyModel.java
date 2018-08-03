package com.boot.demo.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by filamoon on 2018/5/24
 */
@Getter
@Setter
public class GenericReadOnlyModel implements Serializable {
    private static final long serialVersionUID = 1380010635608119749L;

    @TableId
    protected Long id;

    @NotBlank(message = "创建时间不能为空")
    protected Date created;
}
