package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.MapperConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转换：${resultMapType}
 */
public class MapperResultMapTypeFormatter implements LineFormatter {
    private final static String RESULT_MAP_TYPE_FLAG = "${resultMapType}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(RESULT_MAP_TYPE_FLAG)) {
            return line.replace(RESULT_MAP_TYPE_FLAG, resultMapType(tableConfig));
        }
        return null;
    }

    private static String resultMapType(TableConfig config) {
        return MapperConfig.get() + "." + CodeManStringUtil.underlineToCamel(config.getName());
    }
}
