package com.jkf.code.man.core.builder;

import com.jkf.code.man.core.config.SearchConfig;
import com.jkf.code.man.core.enums.SearchConfigTypeEnum;
import com.jkf.code.man.core.utils.CodeManStringUtil;

/**
 * 组装查询条件
 */
public class SearcherConfigBuilder {

    /**
     * 创建相等的查询条件
     */
    public SearchConfig buildEq(String name) {
        return new SearchConfig(CodeManStringUtil.underlineToCamel(name), SearchConfigTypeEnum.EQ);
    }

    /**
     * 创建不相等的查询条件
     */
    public SearchConfig buildNotEq(String name) {
        return new SearchConfig(CodeManStringUtil.underlineToCamel(name), SearchConfigTypeEnum.NOT_EQ);
    }

    /**
     * 创建开始（大于等于）的查询条件
     * *
     */
    public SearchConfig buildStart(String name) {
        return new SearchConfig(CodeManStringUtil.underlineToCamel(name), SearchConfigTypeEnum.START);
    }

    /**
     * 创建开始（小于等于）的查询条件
     * *
     */
    public SearchConfig buildEnd(String name) {
        return new SearchConfig(CodeManStringUtil.underlineToCamel(name), SearchConfigTypeEnum.END);
    }

    /**
     * 创建 in 的查询条件
     */
    public SearchConfig buildIn(String name) {
        return new SearchConfig(CodeManStringUtil.underlineToCamel(name), SearchConfigTypeEnum.IN);
    }

}
