package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author gwk
 * @create 2021 -06 -30 19:58
 */

    @Data
    public class Product {
        private Long id;
        private String name;
        private Integer price;
        @Version
        private Integer version;
    }
