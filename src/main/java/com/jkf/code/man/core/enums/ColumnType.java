package com.jkf.code.man.core.enums;

/**
 * column的类型字段
 */
public enum ColumnType {
    /**
     * 长整型
     */
    BIG_INT("bigint"),
    /**
     * 整型
     */
    INT("int"),
    /**
     * 字符串
     */
    VARCHAR("varchar"),
    /**
     * 日期时间
     */
    DATE_TIME("datetime"),
    /**
     * boolean
     */
    BOOLEAN("tinyint");

    private final String code;

    ColumnType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
