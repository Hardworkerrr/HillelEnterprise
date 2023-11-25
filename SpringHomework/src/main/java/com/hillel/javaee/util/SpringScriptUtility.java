package com.hillel.javaee.util;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


public class SpringScriptUtility {

    public static void runScript(String path, Connection connection) {
        boolean continueOrError = false;
        boolean ignoreFailedDrops = false;
        String commentPrefix = "--";
        String separator = ";";
        String blockCommentStartDelimiter = "/*";
        String blockCommentEndDelimiter = "*/";

        ScriptUtils.executeSqlScript(connection, new EncodedResource(new PathResource(path)), continueOrError, ignoreFailedDrops, commentPrefix, separator, blockCommentStartDelimiter, blockCommentEndDelimiter);
    }

    public static String readResourceSql(String path) {
        ClassLoader classLoader = SpringScriptUtility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        StringBuilder result = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            reader.lines().forEach(result::append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    public static Map<String,String> readResourceProperties(String path){
        ClassLoader classLoader = SpringScriptUtility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        Map<String,String> properties = new HashMap<>();
        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            reader.lines().forEach(s -> {
                String[] props = s.split("=");
                properties.put(props[0],props[1]);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
