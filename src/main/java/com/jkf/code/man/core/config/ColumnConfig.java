package com.jkf.code.man.core.config;

import com.jkf.code.man.core.enums.ColumnType;
import lombok.Getter;

import java.io.Serializable;

public class ColumnConfig implements Serializable {
    /**
     * 特殊字段：更新时间
     */
    public static String SPECIAL_COL_GMT_UPDATED = "gmt_updated";
    /**
     * 特殊字段：创建时间
     */
    public static String SPECIAL_COL_GMT_CREATED = "gmt_created";
    /**
     * 字段名称
     */
    @Getter
    private String name;
    /**
     * 字段描述
     */
    @Getter
    private String desc;
    /**
     * 字段类型
     */
    @Getter
    private ColumnType type;
    /**
     * 长度，主要用于：ColumnType.VARCHAR
     */
    @Getter
    private Integer len;

    /**
     * 是否是主键
     */
    @Getter
    private Boolean primaryKey;


    /**
     * 请使用ColumnBuilder
     */
    public ColumnConfig(String name, ColumnType type, boolean primaryKey, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.primaryKey = primaryKey;
    }
    /**
     * 请使用ColumnBuilder
     */
    public ColumnConfig(String name, Integer len, ColumnType type, boolean primaryKey, String desc) {
        this.name = name;
        this.len = len;
        this.type = type;
        this.desc = desc;
        this.primaryKey = primaryKey;
    }

    private ColumnConfig() {
    }


}