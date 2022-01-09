package com.jkf.code.man.core.builder;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.enums.ColumnType;

/**
 * 创建字段
 */
public class ColumnBuilder {

    public static ColumnConfig varcharCof(String name, Integer length, String desc) {
        return new ColumnConfig(name, length, ColumnType.VARCHAR, false, desc);
    }

    public static ColumnConfig bigIntCof(String name, String desc) {
        return new ColumnConfig(name, ColumnType.BIG_INT, false, desc);
    }

    public static ColumnConfig intCof(String name, String desc) {
        return new ColumnConfig(name, ColumnType.INT, false, desc);
    }

    public static ColumnConfig dateTimeCof(String name, String desc) {
        return new ColumnConfig(name, ColumnType.DATE_TIME, false, desc);
    }

    public static ColumnConfig booleanCof(String name, String desc) {
        return new ColumnConfig(name, ColumnType.BOOLEAN, false, desc);
    }

    public static ColumnConfig primaryKey(String name) {
        return new ColumnConfig(name, ColumnType.BIG_INT, true, "主键");
    }

    /**
     * 校验备注，不能包含特殊：
     */
    private static void checkSpecialCharacters(String desc) {


    }

}