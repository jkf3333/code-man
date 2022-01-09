package com.jkf.code.man.core.builder;

import com.jkf.code.man.core.config.ColumnConfig;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.utils.CodeManStringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {
    /**
     * 作者
     */
    private String anthor;
    /**
     * 表名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;

    /**
     * 字段列表
     */
    private List<ColumnConfig> columnList = new ArrayList<>();


    public static TableBuilder builder(String author) {
        TableBuilder tb = new TableBuilder();
        tb.anthor = author;
        return tb;
    }

    public static TableBuilder builder() {
        return builder("");
    }

    public TableBuilder name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("名称不能为空");
        }
        //进行驼峰转下划线
        this.name = CodeManStringUtil.camelToUnderline(name);
        return this;
    }

    public TableBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    public TableBuilder varcharColumn(String name, Integer len, String desc) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("字段的名称不能为空");
        }
        if (StringUtils.isBlank(desc)) {
            throw new IllegalArgumentException("字段的描述不能为空");
        }
        if (len == null || len < 1) {
            throw new IllegalArgumentException("字段的长度必须为正整数");
        }
        columnList.add(ColumnBuilder.varcharCof(CodeManStringUtil.camelToUnderline(name), len, desc));
        return this;
    }

    public TableBuilder bigIntegerColumn(String name, String desc) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("字段的名称不能为空");
        }
        if (StringUtils.isBlank(desc)) {
            throw new IllegalArgumentException("字段的描述不能为空");
        }
        columnList.add(ColumnBuilder.bigIntCof(CodeManStringUtil.camelToUnderline(name), desc));
        return this;
    }

    public TableBuilder integerColumn(String name, String desc) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("字段的名称不能为空");
        }
        if (StringUtils.isBlank(desc)) {
            throw new IllegalArgumentException("字段的描述不能为空");
        }
        columnList.add(ColumnBuilder.intCof(CodeManStringUtil.camelToUnderline(name), desc));
        return this;
    }

    public TableBuilder dateTimeColumn(String name, String desc) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("字段的名称不能为空");
        }
        if (StringUtils.isBlank(desc)) {
            throw new IllegalArgumentException("字段的描述不能为空");
        }
        columnList.add(ColumnBuilder.dateTimeCof(CodeManStringUtil.camelToUnderline(name), desc));
        return this;
    }

    public TableBuilder booleanColumn(String name, String desc) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("字段的名称不能为空");
        }
        if (StringUtils.isBlank(desc)) {
            throw new IllegalArgumentException("字段的描述不能为空");
        }
        columnList.add(ColumnBuilder.booleanCof(CodeManStringUtil.camelToUnderline(name), desc));
        return this;
    }

    public TableBuilder columnList(List<ColumnConfig> columnList) {
        if (CollectionUtils.isEmpty(columnList)) {
            throw new IllegalArgumentException("字段不能为空");
        }
        this.columnList.addAll(columnList);
        return this;
    }

    public TableBuilder orgIdColumn() {
        this.bigIntegerColumn("orgId", "机构id");
        return this;
    }

    public TableBuilder idColumn() {
        columnList.add(ColumnBuilder.primaryKey("id"));
        return this;
    }

    public TableBuilder deletedTimeColumn() {
        this.booleanColumn("deleted", "主键id");
        return this;
    }

    public TableBuilder defaultColumn() {
        this.dateTimeColumn("gmtCreated", "创建时间");
        this.dateTimeColumn("gmtUpdated", "更新时间");
        return this;
    }


    public TableConfig build() {
        if (StringUtils.isBlank(this.name)) {
            throw new IllegalArgumentException("table名不能为空");
        }
        if (StringUtils.isBlank(this.desc)) {
            throw new IllegalArgumentException("table[" + name + "]的描述不能为空");
        }
        if (CollectionUtils.isEmpty(this.columnList)) {
            throw new IllegalArgumentException("table[" + name + "]的column不能为空");
        }

        Long count = this.columnList.stream().filter(col -> col.getPrimaryKey()).count();
        if (count == 0) {
            throw new IllegalArgumentException("table[" + name + "]必须指定主键字段");
        } else if (count > 1) {
            throw new IllegalArgumentException("table[" + name + "]只能有一个主键");
        }
        return new TableConfig(anthor, name, desc, columnList);
    }
}
