package com.jkf.code.man.core.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * mapper的相关配置
 */
@AllArgsConstructor
@Getter
public class MapperConfig implements Serializable {
    private static ThreadLocal<MapperConfig> MAPPER_TL = new ThreadLocal<>();
    /**
     * 命名空间：例如 com.jkf.code.man.core.config
     */
    private String nameSpace;
    /**
     * 是否开启数据权限校验，
     * 如果开启，那么删除、查询必须添加相关的orgId参数
     * 当然orgId字段必须存在
     */
    private boolean withPrivilegeCheck;

    /**
     * 指定字段的查询条件的组装，字段可以是下划线，也可以驼峰
     * 例如：
     * 等于条件：直接使用字段名称
     * 不等于：字段名称加
     */
    private List<SearchConfig> queryColumns;


    public static MapperConfig get() {
        return MAPPER_TL.get();
    }

    public MapperConfig(String nameSpace, Boolean withPrivilegeCheck, List<SearchConfig> queryColumns) {
        this.nameSpace = nameSpace;
        this.withPrivilegeCheck = withPrivilegeCheck;
        this.queryColumns = queryColumns;
        MAPPER_TL.set(this);
    }
}
