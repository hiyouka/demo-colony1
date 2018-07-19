
package com.jy.utils;

import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author jianglei
 * @create 2018/5/28
 * @since 1.0.0
 */
public interface ExcelResolver {

    List<Map<String,String>> parseSheet(Sheet sheet, Map<String, String> mapperMap);

    List<Object> parseDataBase(String beanName, Object instance, String sqlMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}