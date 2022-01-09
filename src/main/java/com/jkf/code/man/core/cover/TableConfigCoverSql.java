package com.jkf.code.man.core.cover;

import com.google.common.collect.Lists;
import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TableConfigCoverSql {
    private final static String DROP_TABLE_TEMP = "DROP TABLE IF EXISTS `%s`; \n";
    private final static String CREATE_TABLE_TEMP = "" +
            "CREATE TABLE `%s` (\n" +
            "%s" +
            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='%s';\n";

    /**
     * 根据tableConfig，生成创建表的sql
     */
    public static ArrayList<String> createTableSql(TableConfig tableConfig) {
        return createTableSql(tableConfig, false);

    }


    public static ArrayList<String> createTableSql(TableConfig tableConfig, boolean needDrop) {
        String msg = validate(tableConfig);
        if (StringUtils.isNotEmpty(msg)) {
            throw new IllegalArgumentException(msg);
        }
        String createSql = String.format(CREATE_TABLE_TEMP,
                //table name
                tableConfig.getName(),
                //字段
                coverColumns(tableConfig.getColumnList()),
                //table 描述
                tableConfig.getDesc());
        if (needDrop) {
            String dropSql = String.format(DROP_TABLE_TEMP, tableConfig.getName());
            return Lists.newArrayList(dropSql, createSql);
        }
        return Lists.newArrayList(createSql);

    }


    private static String coverColumns(List<ColumnConfig> columnList) {
        StringBuffer sb = new StringBuffer();
        String primaryKey = "";
        for (ColumnConfig col : columnList) {
            if (col.getPrimaryKey()) {
                //主键 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                sb.append("`" + col.getName() + "` " + formatColumn(col) + " NOT NULL AUTO_INCREMENT COMMENT '" + col.getDesc() + "',\n");
                primaryKey = "PRIMARY KEY (`" + col.getName() + "`)\n";
            } else {
                //普通字段 `name` varchar(20) DEFAULT '' COMMENT '名称',
                sb.append("`" + col.getName() + "` " + formatColumn(col) + " COMMENT '" + col.getDesc() + "', \n");
            }
        }
        sb.append(primaryKey);
        return sb.toString();
    }

    private static String formatColumn(ColumnConfig config) {
        switch (config.getType()) {
            case INT:
                return "int(11)";
            case BIG_INT:
                return "bigint(20)";
            case VARCHAR:
                return "varchar(" + config.getLen() + ") DEFAULT ''";
            case DATE_TIME: {
                if (ColumnConfig.SPECIAL_COL_GMT_UPDATED.equals(config.getName())) {
                    return "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP";
                }
                if (ColumnConfig.SPECIAL_COL_GMT_CREATED.equals(config.getName())) {
                    return "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP";
                }
                return "datetime ";

            }
            case BOOLEAN:
                return "tinyint(1)";
            default:
                return "";
        }
    }

    private static String validate(TableConfig tableConfig) {
        if (tableConfig == null) {
            return "table配置信息不能为空";
        }
        if (StringUtils.isBlank(tableConfig.getName())) {
            return "table的表名必填";
        }

        if (StringUtils.isBlank(tableConfig.getDesc())) {
            return "table[" + tableConfig.getName() + "]的描述必填";
        }
        if (CollectionUtils.isEmpty(tableConfig.getColumnList())) {
            return "table[" + tableConfig.getName() + "]的column必填";
        }
        Long count = tableConfig.getColumnList().stream().filter(col -> col.getPrimaryKey()).count();
        if (count == 0) {
            return "table[" + tableConfig.getName() + "]必须指定主键字段";
        } else if (count > 1) {
            return "table[" + tableConfig.getName() + "]只能有一个主键";
        }
        return "";
    }
}
