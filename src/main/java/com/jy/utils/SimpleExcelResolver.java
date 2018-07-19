
package com.jy.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//最为简单的excel格式解析器
public class SimpleExcelResolver implements ExcelResolver{
    public List<Map<String,String>> parseSheet(Sheet sheet,Map<String,String> mapperMap){
        Row row;
        int count = 0;
      //  取出每一个Row
        Iterator<Row> iterator = sheet.iterator();
        List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
        List<String> list = new ArrayList<String>();
        while(iterator.hasNext()) {
            row = iterator.next();
            List<String> rowString = parseRow(row);
            Iterator<String> it = rowString.iterator();
          //  由于第一行是标题，因此这里单独处理
            if (count == 0) {
                while(it.hasNext()){
                    list.add(it.next());
                }
            } else {
                Map<String,String> rowMap = new HashMap<String,String>();
                int num = 0;
                while(it.hasNext()){
                    rowMap.put(mapperMap.get(list.get(num)),it.next());
                    num++;
                }
                resultList.add(rowMap);
            }
            ++count;
        }
        return resultList;
    }

    /**
     * 功能描述:调用给定的mapper 和 方法名查询对象返回
     * @param:[beanName, clazz]
     * @return:java.util.List<org.apache.poi.ss.formula.functions.T>
     * @since: 1.0.0
     * @Author:jianglei
     * @Date: 2018/7/6
     */
    @Override
    public List<Object> parseDataBase(String beanName,Object instance,String sqlMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        Object bean = applicationContext.getBean(beanName);
        Class<?> beanClass = bean.getClass();
        List<Object> resultList = null;
        if(instance == null){
            Method select = beanClass.getDeclaredMethod(sqlMethodName,null);
            Object obj = select.invoke(bean, null);
            resultList = (List<Object>)obj;
        }else {
            Method select = beanClass.getDeclaredMethod(sqlMethodName,instance.getClass());
            Object obj = select.invoke(bean, instance);
            resultList = (List<Object>)obj;
        }
        return resultList;
    }


    //这里是解析每一行的代码
    private static List<String> parseRow(Row row) {
        List<String> rst = new ArrayList<>();
        Cell cell;
        //得到每一个cell
        Iterator<Cell> iterator = row.iterator();
        System.out.println(row.getRowNum());
        while (iterator.hasNext()) {
            cell = iterator.next();
           // 定义每一个cell的数据类型
            cell.setCellType(CellType.STRING);
           // 取出cell中的value
//            System.out.print(cell.getStringCellValue()+",");
            rst.add(cell.getStringCellValue());
        }
        System.out.println();
        return rst;
    }
}

