package com.jkf.code.man.core.handler.core;

import com.jkf.code.man.core.config.TableConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class LineFormatUtils {
    private static LineFormatter emptyFormatter = new EmptyFormatter();

    public static String formatLine(String line, TableConfig tableConfig, List<LineFormatter> formatterList) {
        if (CollectionUtils.isEmpty(formatterList)) {
            return line;
        }
        String newLine = emptyFormatter.format(line, tableConfig);
        if (Objects.equals(line,newLine)) {
            return newLine;
        }
        for (LineFormatter lineFormatter : formatterList) {
            newLine = lineFormatter.format(line, tableConfig);
            if (newLine == null) {
                continue;
            } else {
                return newLine;
            }
        }
        return line;

    }
}
