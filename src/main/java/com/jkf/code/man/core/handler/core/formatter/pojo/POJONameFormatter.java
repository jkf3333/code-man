package com.jkf.code.man.core.handler.core.formatter.pojo;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转化：${pojoName}
 */
public class POJONameFormatter implements LineFormatter {
    /**
     * 模板的table名称标志
     */
    private String FILE_TEMP_POJO_NAME_FLAG = "${pojoName}";

    /**
     * 后缀
     */
    private String suffix;

    public POJONameFormatter() {
        suffix = "";
    }

    public POJONameFormatter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_POJO_NAME_FLAG)) {
            return line.replace(FILE_TEMP_POJO_NAME_FLAG, CodeManStringUtil.underlineToCamel(tableConfig.getName()) + suffix);
        }
        return null;
    }
}
