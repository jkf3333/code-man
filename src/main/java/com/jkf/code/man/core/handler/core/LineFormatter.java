package com.jkf.code.man.core.handler.core;

import com.jkf.code.man.core.config.TableConfig;

/**
 * 根据模板的每一行，进行数据转换
 * */
public interface LineFormatter {
    String format(String line, TableConfig tableConfig);
}
