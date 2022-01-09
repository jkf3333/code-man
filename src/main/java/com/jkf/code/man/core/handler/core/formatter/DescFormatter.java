package com.jkf.code.man.core.handler.core.formatter;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

/**
 * 转换 ${desc}
 */
public class DescFormatter implements LineFormatter {
    /**
     * 模板的描述标志
     */
    private String FILE_TEMP_DESC_FLAG = "${desc}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_DESC_FLAG)) {
            return line.replace(FILE_TEMP_DESC_FLAG, tableConfig.getDesc());
        }
        return null;
    }
}
