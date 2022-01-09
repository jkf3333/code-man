package com.jkf.code.man.core.handler.core.formatter.pojo;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import org.apache.commons.lang3.StringUtils;

/**
 * pojo对象转换 ${import}
 */
public class POJOImportFormatter implements LineFormatter {
    /**
     * 模板的作者标志
     */
    private static String FILE_TEMP_IMPORT_FLAG = "${import}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_IMPORT_FLAG)) {
            StringBuilder sb = new StringBuilder();
            for (ColumnConfig config : tableConfig.getColumnList()) {
                String importStr = formatColumnImport(config);
                if (StringUtils.isNotBlank(importStr)) {
                    if (!sb.toString().contains(importStr)) {
                        sb.append(importStr).append("\n");
                    }
                }
            }
            return line.replace(FILE_TEMP_IMPORT_FLAG, sb.toString());
        }
        return null;
    }

    private static String formatColumnImport(ColumnConfig config) {
        String importVal = "";
        switch (config.getType()) {
            case DATE_TIME:
                importVal = "import java.util.Date;";
                break;
            default:
                break;
        }
        return importVal;
    }
}
