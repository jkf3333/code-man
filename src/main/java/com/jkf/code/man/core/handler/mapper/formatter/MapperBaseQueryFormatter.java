package com.jkf.code.man.core.handler.mapper.formatter;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.MapperConfig;
import com.jkf.code.man.core.config.SearchConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.enums.ColumnType;
import com.jkf.code.man.core.handler.core.LineFormatter;
import com.jkf.code.man.core.utils.CodeManStringUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 转换：${baseQuery}
 */
public class MapperBaseQueryFormatter implements LineFormatter {
    private final static String FLAG = "${baseQuery}";
    private final static String TEST_VARCHAR_TEMP = "<if test=\"%s != null and %s != '' \">%s</if>";
    private final static String TEST_BOOLEAN_FALSE_TEMP = "<if test=\"%s != null and %s == false \">%s</if>";
    private final static String TEST_OBJ_TEMP = "<if test=\"%s != null \">%s</if>";
    private final static String BOOLEAN_OPT = "" +
            "        <if test=\"${poName} != null and ${poName} == true\">\n" +
            "            ${colName} =true\n" +
            "        </if>\n" +
            "        <if test=\"${poName} != null and ${poName} == false\">\n" +
            "            ${colName} =false\n" +
            "        </if>";
    private final static String LIST_OPT = "" +
            "        <if test=\"${poName} != null and ${poName}.size >0 \">\n" +
            "            ${colName} in\n" +
            "            <foreach item=\"${poName}Temp\" collection=\"list\" separator=\",\" open=\"(\" close=\")\" index=\"\">\n" +
            "                #{${poName}}\n" +
            "            </foreach>\n" +
            "        </if>";
    private final static String EQ_OPT_VARCHAR = "" +
            "        <if test=\"${poName} != null and ${poName} != ''\">\n" +
            "            ${colName}=${poName}\n" +
            "        </if>";
    private final static String EQ_OPT = "" +
            "        <if test=\"${poName} != null\">\n" +
            "            ${colName} &lt;=${poName}\n" +
            "        </if>";
    private final static String START_OPT = "" +
            "        <if test=\"${poName} != null\">\n" +
            "            ${colName} &gt;=${poName}\n" +
            "        </if>";
    private final static String END_OPT = "" +
            "        <if test=\"${poName} != null\">\n" +
            "            ${colName} &lt;=${poName}\n" +
            "        </if>";

    @Override
    public String format(String line, TableConfig tableConfig) {
        if (line.contains(FLAG)) {
            return line.replace(FLAG, baseQuery(tableConfig));
        }
        return null;
    }

    private static String baseQuery(TableConfig config) {
        StringBuilder sb = new StringBuilder();
        List<ColumnConfig> colLIst = config.getColumnList();
        Map<String, ColumnConfig> nameMap = colLIst.stream().collect(Collectors.toMap(col -> CodeManStringUtil.underlineToCamel(col.getName()), col -> col);
        MapperConfig mapConfig = MapperConfig.get();
        for (SearchConfig search : mapConfig.getQueryColumns()) {
            if (!nameMap.containsKey(search.getName())) {
                throw new IllegalArgumentException("查询条件[" + search.getName() + "]不在table[" + config.getName() + "]中，请检查");
            }
            sb.append(formatSearch(search, nameMap.get(search.getName())));
        }

        return sb.substring(1);
    }

    private static String formatSearch(SearchConfig search, ColumnConfig columnConfig) {

    }

    private static String test(SearchConfig searchConfig, ColumnConfig columnConfig) {
        switch (searchConfig.getTypeEnum()) {
            case EQ:
                if(ColumnType.VARCHAR==columnConfig.getType()){

                }else
            case IN:
                return
        }
    }
}
