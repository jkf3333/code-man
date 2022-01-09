package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

import java.util.List;

/**
 * 转换：${allColumnExcludeId}
 */
public class MapperAllColumnExcludeIdFormatter implements LineFormatter {
    private final static String FLAG = "${allColumnExcludeId}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FLAG)) {
            return line.replace(FLAG, allColumn(tableConfig));
        }
        return null;
    }

    private static String allColumn(TableConfig config) {
        StringBuilder sb = new StringBuilder();
        List<ColumnConfig> colLIst = config.getColumnList();
        for (int i = 0; i < colLIst.size(); i++) {
            ColumnConfig colConfig = colLIst.get(i);
            if (Boolean.TRUE.equals(colConfig.getPrimaryKey())) {
                //忽略主键
                continue;
            }
            sb.append(",").append(colConfig.getName());
            if ((i + 1) % 5 == 0) {
                sb.append("\n");
            }
        }
        return sb.substring(1);
    }
}
