package com.jkf.code.man.core.handler.core.formatter;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转换 ${tableName}
 */
public class TableNameFormatter implements LineFormatter {
    /**
     * 模板的table名称标志
     */
    private String FILE_TEMP_TABLE_NAME_FLAG = "${tableName}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_TABLE_NAME_FLAG)) {
            return line.replace(FILE_TEMP_TABLE_NAME_FLAG, CodeManStringUtil.underlineToCamel(tableConfig.getName()));
        }
        return null;
    }
}
