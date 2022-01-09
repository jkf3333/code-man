package com.jkf.code.man.core.handler.mapper;

import com.google.common.collect.Lists;
import com.jkf.code.man.core.Constants;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.handler.core.LineFormatUtils;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.handler.core.formatter.*;
import com.jkf.code.man.core.handler.core.formatter.pojo.POJOFiledFormatter;
import com.jkf.code.man.core.handler.core.formatter.pojo.POJOImportFormatter;
import com.jkf.code.man.core.handler.core.formatter.pojo.POJONameFormatter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * 生成Mapper
 */
public class MapperHandler {
    private static List<LineFormatter> LINE_FORMATTER = Lists.newArrayList();
    private static String TEMP_FILE_PATH = "com\\jkf\\code\\man\\core\\handler\\mapper\\mapper.txt";

    static {
        LINE_FORMATTER.add(new AuthorFormatter());
        LINE_FORMATTER.add(new TimeFormatter());
        LINE_FORMATTER.add(new TableNameFormatter());
        LINE_FORMATTER.add(new DescFormatter());
        LINE_FORMATTER.add(new POJOFiledFormatter());
        LINE_FORMATTER.add(new POJONameFormatter("VO"));
        LINE_FORMATTER.add(new POJOImportFormatter());
    }

    public static String createMapper(TableConfig tableConfig) {
        StringBuffer sb = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(TEMP_FILE_PATH))) {
            while (true) {
                String line = br.readLine();
                if (Constants.FILE_TEMP_END_FLAG.equals(line)) {
                    break;
                }
                //处理每一行的数据
                line = LineFormatUtils.formatLine(line, tableConfig, LINE_FORMATTER);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
