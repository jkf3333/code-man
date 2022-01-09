package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转换：${resultMap}
 */
public class MapperResultMapFormatter implements LineFormatter {
    private final static String RESULT_MAP_FLAG = "${resultMap}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(RESULT_MAP_FLAG)) {
            return line.replace(RESULT_MAP_FLAG, resultMap(tableConfig));
        }
        return null;
    }

    private static String resultMap(TableConfig config) {
        return CodeManStringUtil.underlineToCamel(config.getName()) + "Map";
    }
}
