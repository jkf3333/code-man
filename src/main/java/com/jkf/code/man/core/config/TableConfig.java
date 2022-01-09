package com.jkf.code.man.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表信息
 */
@AllArgsConstructor
public class TableConfig implements Serializable {
    /**
     * 作者
     */
    @Setter
    @Getter
    private String anthor;
    /**
     * 表名
     */
    @Getter
    private String name;
    /**
     * 描述
     */
    @Getter
    private String desc;

    /**
     * 字段列表
     */
    @Getter
    private List<ColumnConfig> columnList = new ArrayList<>();

    private TableConfig() {
    }

}
