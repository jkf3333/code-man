package com.jkf.code.man.core.config;

import com.jkf.code.man.core.enums.SearchConfigTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询条件配置
 * 见：SearchConfigBuilder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchConfig implements Serializable {
    /**
     * 查询条件的名称，可以下换线，
     * 可以驼峰，但是必须存在数据库中
     */
    private String name;

    /**
     * 查询条件
     */
    private SearchConfigTypeEnum typeEnum;
}
