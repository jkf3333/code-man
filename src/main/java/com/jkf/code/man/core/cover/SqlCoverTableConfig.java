package com.jkf.code.man.core.cover;

import com.google.common.collect.Lists;
import com.jkf.code.man.core.builder.ColumnBuilder;
import com.jkf.code.man.core.builder.TableBuilder;
import com.jkf.code.man.core.config.*;
import com.jkf.code.man.core.enums.ColumnType;
import com.jkf.code.man.core.utils.CodeManStringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 建表语句转化为tableConfig
 */
public class SqlCoverTableConfig {


    private static final String PRIMARY_KEY_FLAG = "PRIMARY KEY";
    private static final String COMMENT_FLAG = "COMMENT";

    /**
     * sql语句，转化为tableConfig
     *
     * @param tableSql 建表语句，示例：
     *                 CREATE TABLE `test_jkf_rdw` (
     *                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
     *                 `name` varchar(20) DEFAULT '' COMMENT '姓名',
     *                 `age` int(11) DEFAULT NULL COMMENT '年龄',
     *                 `is_adult` tinyint(1) DEFAULT NULL COMMENT '是否成年人，true=成年人，false=未成年',
     *                 `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *                 `gmt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     *                 PRIMARY KEY (`id`)
     *                 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表格'
     * @return tableConfig
     */
    public static TableConfig cover2TableConfig(String tableSql) {
        if (StringUtils.isBlank(tableSql)) {
            throw new IllegalArgumentException("tableSql为空");
        }
        TableConfig config = getTableConfig(tableSql, "太丘");
        return config;
    }

    /**
     * 获取表名，注释
     */
    private static TableConfig getTableConfig(String tableSql, String author) {
        String tableName = CodeManStringUtil.firstInfoInApostrophe(tableSql);
        if (StringUtils.isBlank(tableName)) {
            throw new IllegalArgumentException("创建table的sql不符合格式，无法获取table的name属性");
        }
        String tabComment = CodeManStringUtil.lastInfoInSingleQuotes(tableSql);
        if (StringUtils.isBlank(tabComment)) {
            throw new IllegalArgumentException("创建table的sql不符合格式，无法获取table的comment属性");
        }
        List<ColumnConfig> columnList = getColumnConfig(
                CodeManStringUtil.outmostInfoInParentheses(tableSql));
        return TableBuilder.builder(author)
                .name(tableName)
                .desc(tabComment)
                .columnList(columnList)
                .build();
    }

    /**
     * 获取字段属性列表
     */
    private static List<ColumnConfig> getColumnConfig(String columnSql) {
        //获取字段信息
        String[] colArr = columnSql.split(",");
        List<ColumnConfig> configList = Lists.newArrayListWithCapacity(colArr.length);
        String primaryKey = "";
        for (String colStr : colArr) {
            ColumnConfig config = formatConfig(colStr);
            if (config != null) {
                configList.add(config);
            }
            if (colStr.contains(PRIMARY_KEY_FLAG)) {
                //主键
                primaryKey = CodeManStringUtil.firstInfoInApostrophe(colStr);
            }
        }
        if (StringUtils.isBlank(primaryKey)) {
            throw new IllegalArgumentException("创建table的sql不符合格式，无法获取primary key");
        }
        fillPrimaryKey(configList, primaryKey);
        return configList;
    }

    /**
     * 填充主键tabSql:
     */
    private static void fillPrimaryKey(List<ColumnConfig> configList, String primaryKey) {
        for (int i = 0; i < configList.size(); i++) {
            ColumnConfig config = configList.get(i);
            if (config.getName().equals(primaryKey)) {
                //主键
                configList.set(i, ColumnBuilder.primaryKey(config.getName()));
            }
        }
    }


    private static ColumnConfig formatConfig(String columnSql) {
        for (ColumnType colType : ColumnType.values()) {
            boolean hasColumn = columnSql.contains(colType.getCode()) || columnSql.contains(colType.getCode().toUpperCase());
            if (hasColumn) {
                String name = CodeManStringUtil.firstInfoInApostrophe(columnSql);
                if (StringUtils.isBlank(name)) {
                    throw new IllegalArgumentException("创建table的sql不符合格式，无法获取column的name属性");
                }
                String desc = CodeManStringUtil.lastInfoInSingleQuotes(columnSql);
                if (StringUtils.isBlank(desc)) {
                    throw new IllegalArgumentException("创建table的sql不符合格式，无法获取column的comment");
                }
                switch (colType) {
                    case BIG_INT:
                        return ColumnBuilder.bigIntCof(name, desc);
                    case BOOLEAN:
                        return ColumnBuilder.booleanCof(name, desc);
                    case INT:
                        return ColumnBuilder.intCof(name, desc);
                    case VARCHAR:
                        return ColumnBuilder.varcharCof(name, 1, desc);
                    case DATE_TIME:
                        return ColumnBuilder.dateTimeCof(name, desc);
                    default:
                        return null;
                }
            }
        }
        return null;

    }

    private static String primaryKey(String tableSql) {
        return CodeManStringUtil.firstInfoInParentheses(tableSql).replaceAll("`", "").trim();
    }
}
