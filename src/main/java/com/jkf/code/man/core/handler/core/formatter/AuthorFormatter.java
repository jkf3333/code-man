package com.jkf.code.man.core.handler.core.formatter;

import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatter;

/**
 * 转换 ${author}
 */
public class AuthorFormatter implements LineFormatter {
    /**
     * 模板的作者标志
     */
    private static String FILE_TEMP_AUTHOR_FLAG = "${author}";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FILE_TEMP_AUTHOR_FLAG)) {
            String author = tableConfig.getAnthor();
            if (author == null) {
                author = "";
            }
            return line.replace(FILE_TEMP_AUTHOR_FLAG, author);
        }
        return null;
    }
}
