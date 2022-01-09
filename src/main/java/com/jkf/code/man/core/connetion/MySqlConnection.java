package com.jkf.code.man.core.connetion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库链接，提供基础的方法
 */
public class MySqlConnection {

    public static void main(String[] args) {
        System.out.println(showTable("test_jkf_rdw"));
    }

    /**
     * 根据提供的表名，返回建表语句
     * 例如：
     * CREATE TABLE `test_jkf_rdw` (
     * `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
     * `name` varchar(20) DEFAULT '' COMMENT '姓名',
     * `age` int(11) DEFAULT NULL COMMENT '年龄',
     * `is_adult` tinyint(1) DEFAULT NULL COMMENT '是否成年人，true=成年人，false=未成年',
     * `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     * `gmt_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     * PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表格'
     *
     * @param tableName 表名，必填
     */
    public static String showTable(String tableName) {
        DBHelper dbHelper = null;
        try {
            String sql = "show create table " + tableName + ";";
            dbHelper = new DBHelper(sql);
            ResultSet resultSet = dbHelper.pst.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("Create Table");
            }
            return "";
        } catch (SQLException e) {
            throw new RuntimeException("数据库访问错误", e);
        } finally {
            if (dbHelper != null) {
                dbHelper.close();
            }
        }
    }

    public static void createTable(List<String> createSql) {
        for (String sql : createSql) {
            execute(sql);
        }
    }

    private static void execute(String sql) {
        DBHelper dbHelper = null;
        try {
            dbHelper = new DBHelper(sql);
            dbHelper.pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbHelper != null) {
                dbHelper.close();
            }
        }
    }

}
