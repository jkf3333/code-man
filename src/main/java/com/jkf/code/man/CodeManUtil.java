package com.jkf.code.man;

import com.alibaba.fastjson.JSONObject;
import com.jkf.code.man.core.builder.TableBuilder;
import com.jkf.code.man.core.config.TableConfig;
import com.jkf.code.man.core.connetion.MySqlConnection;
import com.jkf.code.man.core.cover.SqlCoverTableConfig;
import com.jkf.code.man.core.cover.TableConfigCoverSql;
import com.jkf.code.man.core.handler.dto.DTOHandler;
import com.jkf.code.man.core.handler.po.POHandler;
import com.jkf.code.man.core.handler.vo.VOHandler;
import org.junit.Test;

import java.util.ArrayList;

/**
 * code man的主要功能
 */
public class CodeManUtil {

    public static void main(String[] args) {
        TableConfig config = TableBuilder.builder()
                .name("testJkfRdw")
                .desc("测试表格")
                .idColumn()
                .varcharColumn("name", 20, "姓名")
                .integerColumn("age", "年龄")
                .booleanColumn("isAdult", "是否成年人，true=成年人，false=未成年")
                .defaultColumn()
                .build();
        ArrayList<String> tableSql = TableConfigCoverSql.createTableSql(config, true);
        System.out.println(JSONObject.toJSONString(tableSql));
        MySqlConnection.createTable(tableSql);
    }

    @Test
    public void showTableInfo() {
        MySqlConnection.showTable("test_jkf_rdw");
    }

    /**
     * 展示mysql的table的TableConfig属性`
     */
    @Test
    public void tableSqlCoverTableConfig() {
        String tableSql = MySqlConnection.showTable("test_jkf_rdw");
        TableConfig config = SqlCoverTableConfig.cover2TableConfig(tableSql);
        System.out.println(JSONObject.toJSONString(config));
    }

    @Test
    public void createPO() {
        String tableSql = MySqlConnection.showTable("test_jkf_rdw");
        TableConfig config = SqlCoverTableConfig.cover2TableConfig(tableSql);
        config.setAnthor("太丘");
        String javaInfo = POHandler.createPO(config);
        System.out.println(javaInfo);

    }

    @Test
    public void createVO() {
        String tableSql = MySqlConnection.showTable("test_jkf_rdw");
        TableConfig config = SqlCoverTableConfig.cover2TableConfig(tableSql);
        config.setAnthor("太丘");
        String javaInfo = VOHandler.createVO(config);
        System.out.println(javaInfo);

    }

    @Test
    public void createDTO() {
        String tableSql = MySqlConnection.showTable("test_jkf_rdw");
        TableConfig config = SqlCoverTableConfig.cover2TableConfig(tableSql);
        config.setAnthor("太丘");
        String javaInfo = DTOHandler.createDTO(config);
        System.out.println(javaInfo);

    }



}
