package com.jkf.code.man.core.handler.core;

import com.jkf.code.man.core.config.TableConfig;
import org.apache.commons.lang3.StringUtils;

/**
 * 如果每行为空，调用其他
 */
public class EmptyFormatter implements LineFormatter {
    @Override
    public String format(String line, TableConfig tableConfig) {
        if (StringUtils.isBlank(line)) {
            return line;
        }
        return null;
    }
}
