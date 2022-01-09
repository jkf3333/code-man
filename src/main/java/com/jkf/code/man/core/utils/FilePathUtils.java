package com.jkf.code.man.core.utils;

public class FilePathUtils {
    public static void main(String[] args) {
        System.out.println(baseJavaPath());
    }

    /**
     * 返回java文件的根目录
     */
    public static String baseJavaPath() {
        String path = FilePathUtils.class.getResource("/").getPath();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return path.replace("target/classes/", "src/main/java/");
    }
}
