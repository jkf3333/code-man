package com.jkf.code.man.core.handler.core.formatter;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换 ${time}
 */
public class TimeFormatter implements LineFormatter {
    /**
     * 模板的时间标志
     */
    private String FILE_TEMP_TIME_FLAG = "${time}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_TIME_FLAG)) {
            return line.replace(FILE_TEMP_TIME_FLAG, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        return null;
    }
}
