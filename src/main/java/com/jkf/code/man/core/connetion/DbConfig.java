package com.jkf.code.man.core.connetion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据库链接配置
 */
@Data
@AllArgsConstructor
public class DbConfig implements Serializable {
    private static DbConfig DEFAULT = new DbConfig(
            "jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true",
            "root", "jkf3333");
    private static ThreadLocal<DbConfig> DB_TL = new ThreadLocal<>();
    /**
     * 请求链接
     */
    private String url;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    public static void set(String url, String name, String password) {
        DB_TL.set(new DbConfig(url, name, password));
    }

    public static DbConfig getConfig() {
        DbConfig config = DB_TL.get();
        if (config == null) {
            config = DEFAULT;
            DB_TL.set(config);
        }
        return config;
    }
}
