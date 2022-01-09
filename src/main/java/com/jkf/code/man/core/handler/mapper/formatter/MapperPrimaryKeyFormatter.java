package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

/**
 * 转换：${primaryKey}
 */
public class MapperPrimaryKeyFormatter implements LineFormatter {
    private final static String FLAG = "${primaryKey}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FLAG)) {
            return line.replace(FLAG, primaryKey(tableConfig));
        }
        return null;
    }

    private static String primaryKey(TableConfig config) {
        for (ColumnConfig columnConfig : config.getColumnList()) {
            if (Boolean.TRUE.equals(columnConfig.getPrimaryKey())) {
                return columnConfig.getName();
            }
        }
        return "";
    }
}
