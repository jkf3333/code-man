package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

import java.util.List;

/**
 * 转换：${allColumn}
 */
public class MapperAllColumnFormatter implements LineFormatter {
    private final static String RESULT_ALL_COLUMN_FLAG = "${allColumn}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(RESULT_ALL_COLUMN_FLAG)) {
            return line.replace(RESULT_ALL_COLUMN_FLAG, allColumn(tableConfig));
        }
        return null;
    }

    private static String allColumn(TableConfig config) {
        StringBuilder sb = new StringBuilder();
        List<ColumnConfig> colLIst = config.getColumnList();
        for (int i = 0; i < colLIst.size(); i++) {
            ColumnConfig colConfig = colLIst.get(i);
            sb.append(",").append(colConfig.getName());
            if ((i + 1) % 5 == 0) {
                sb.append("\n");
            }
        }
        return sb.substring(1);
    }
}
