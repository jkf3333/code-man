package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.MapperConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 转换：${nameSpace}
 */
public class MapperResultMapNameSpaceFormatter implements LineFormatter {
    private final static String RESULT_NAME_SPACE_FLAG = "${nameSpace}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(RESULT_NAME_SPACE_FLAG)) {
            return line.replace(RESULT_NAME_SPACE_FLAG, nameSpace(tableConfig));
        }
        return null;
    }

    private static String nameSpace(TableConfig config) {
        return MapperConfig.get() + "." + CodeManStringUtil.underlineToCamel(config.getName());
    }
}
