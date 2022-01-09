package com.jkf.code.man.core.handler.core.formatter.pojo;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转换：${pojoColumns}
 */
public class POJOFiledFormatter implements LineFormatter {
    private final String POJO_FLAG = "${pojoFiled}";

    private final static String FILED_TEMP = "    /**\n" +
            "     * %s\n" +
            "     */\n" +
            "    private %s %s;";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(POJO_FLAG)) {
            StringBuilder sb = new StringBuilder();
            for (ColumnConfig colConfig : tableConfig.getColumnList()) {
                sb.append(formatColumn(colConfig)).append("\n");
            }
            return sb.toString();
        }
        return null;
    }

    private static String formatColumn(ColumnConfig config) {
        String type = "";
        switch (config.getType()) {
            case INT:
                type = "Integer";
                break;
            case DATE_TIME:
                type = "Date";
                break;
            case BOOLEAN:
                type = "Boolean";
                break;
            case BIG_INT:
                type = "Long";
                break;
            case VARCHAR:
                type = "String";
                break;
            default:
                type = "";
        }
        return String.format(FILED_TEMP, config.getDesc(), type, CodeManStringUtil.underlineToCamel(config.getName()));
    }
}
