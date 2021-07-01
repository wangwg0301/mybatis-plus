package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author wxg
 * @create 2021 -06 -30 13:14
 */
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
private Date   gmtModified;

    @TableLogic
    private Integer deleted;
}
