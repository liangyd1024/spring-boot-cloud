package com.lyd.spring.boot.redis.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/17 ProjectName:spring-boot-cloud Version: 1.0
 */
public final class JsonUtils {


    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();



    public static <T> T json2Model(String json,Class<T> tClass) throws IOException {
        return OBJECT_MAPPER.readValue(json,tClass);
    }

    public static List json2List(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json,List.class);
    }


    public static <T> String object2Json(T model) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(model);
    }

    public static String file2json(String path) throws IOException {
        File file = new File(path);
        return OBJECT_MAPPER.readTree(file).toString();
    }

}
