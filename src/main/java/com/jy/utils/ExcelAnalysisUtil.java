
package com.jy.utils;

import com.jy.model.user.SysRole;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈excel文件解析〉
 * @author jianglei
 * @create 2018/5/28
 * @since 1.0.0
 */
public class ExcelAnalysisUtil {

        private static final String SUFFIX_2003 = ".xls";
        private static final String SUFFIX_2007 = ".xlsx";

        //实体类属性的类型集合(支持四种)
        private static final Class[] toMapClass = {String.class,Integer.class,Double.class,Float.class};
        //根据路径初始化workBook
        private static Workbook initWorkBook(String filePath)throws IOException{
            File file = new File(filePath);
            InputStream in = new FileInputStream(file);
            Workbook workBook = null;
            if(filePath.endsWith(SUFFIX_2003)){
                workBook = new HSSFWorkbook(in);
            }else if(filePath.endsWith(SUFFIX_2007)){
                workBook = new XSSFWorkbook(in);
            }
            return workBook;
        }

    private static Workbook instanceWorkBook(String format){
        Workbook workBook = null;
        if(format.endsWith(SUFFIX_2003)){
            workBook = new HSSFWorkbook();
        }else if(format.endsWith(SUFFIX_2007)){
            workBook = new XSSFWorkbook();
        }
        return workBook;
    }

//
//        //解析单个sheet
//        private static List<Map<String,String>> parseSheet(Sheet sheet,Map<String,String> mapperMap){
//            Row row;
//            int count = 0;
//           // 取出每一个Row
//            Iterator<Row> iterator = sheet.iterator();
//            List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
//            List<String> list = new ArrayList<String>();
//            while(iterator.hasNext()) {
//                row = iterator.next();
//                List<String> rowString = parseRow(row);
//                Iterator<String> it = rowString.iterator();
//               // 由于第一行是标题，因此这里单独处理
//                if (count == 0) {
//                   while(it.hasNext()){
//                       list.add(it.next());
//                   }
//               } else {
//                   Map<String,String> rowMap = new HashMap<String,String>();
//                   int num = 0;
//                   while(it.hasNext()){
//                       rowMap.put(mapperMap.get(list.get(num)),it.next());
//                       num++;
//                   }
//                    resultList.add(rowMap);
//                }
//                ++count;
//            }
//            return resultList;
//        }
//
//
//      //  这里是解析每一行的代码
//        private static List<String> parseRow(Row row) {
//            List<String> rst = new ArrayList<>();
//            Cell cell;
//         //   得到每一个cell
//            Iterator<Cell> iterator = row.iterator();
//            System.out.println(row.getRowNum());
//            while (iterator.hasNext()) {
//                cell = iterator.next();
//               // 定义每一个cell的数据类型
//                cell.setCellType(CellType.STRING);
//               // 取出cell中的value
//                System.out.print(cell.getStringCellValue()+",");
//                rst.add(cell.getStringCellValue());
//            }
//            System.out.println();
//            return rst;
//        }

    /**
     * 功能描述: 将map转化为实体对象(支持对象属性为String,Integer,Double,Float)
     * @param:[map, clazz]
     * @return:T
     * @since: 1.0.0
     * @Author:jianglei
     * @Date: 2018/5/28
     */
    public static <T> T mapToEntity(Map<String,String> map,Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        T ins = clazz.newInstance();
        for(Map.Entry<String,String> entry : map.entrySet()){
            Field field = clazz.getDeclaredField(entry.getKey());
            Class paramClass = null;
            Object paramValue = null;
            for(Class<?> cla : toMapClass){
                if(field.getType().equals(cla)){
                    paramClass = cla;
                    if(cla.equals(String.class)){            //   String没有valueOf(String)方法
                        paramValue = entry.getValue();
                    }else{
                        Method valueOf = cla.getDeclaredMethod("valueOf", String.class);
                        paramValue = valueOf.invoke(null,entry.getValue());
                    }
                    break;
                }
            }
            Method setParam = clazz.getDeclaredMethod("set" + entry.getKey().substring(0, 1).toUpperCase()
                    + entry.getKey().substring(1, entry.getKey().length()),paramClass);

            setParam.invoke(ins,paramValue);
        }
        return ins;
    }

        public static void main(String[] args) {
//        List<User> parse = ExcelAnalysisUtil.parse("C:/Users/20625/Desktop/Group/信息.xlsx", User.class, new String[]{"姓名:name",
//                                        "班级:className", "身高:height", "性别:sex", "年龄:age", "部门id:deptId"},new SimpleExcelResolver());
//        System.out.println(parse);
            ExcelAnalysisUtil.outPutToExcel("C:/Users/20625/Desktop/group/test.xlsx",new SimpleExcelResolver(),null);
    }


    public static void outPutToExcel(String toPath, ExcelResolver resolver,List<Map<String,Object>> paramList)  {
        Workbook workbook = instanceWorkBook(SUFFIX_2007);
        try {
            paramList = new ArrayList<>();
            /*     手动构造参数    */
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("beanName","sysRoleMapper");
            paramMap.put("instance",new SysRole());
            paramMap.put("sqlMethodName","selectAll");
            paramList.add(paramMap);
            for(Map<String,Object> map : paramList){
                List<Object> tList = resolver.parseDataBase(map.get("beanName")+"", map.get("instance"), map.get("sqlMethodName")+"");
                Sheet sheet = workbook.createSheet("www");
                Object obj = tList.get(0);
                Class<?> cla = obj.getClass();
                Field[] field1 = cla.getDeclaredFields();
                Row row1 = sheet.createRow(0);
                for(int j = 0;j<field1.length;j++){
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(field1[j].getName());
                }
                for(int i = 0;i<tList.size();i++){
                    Row row = sheet.createRow(i+1);
                    Object instance =  tList.get(i);
                    Class<?> clazz = instance.getClass();
                    Field[] fields = clazz.getDeclaredFields();

                    for(int j = 0;j<fields.length;j++){
                        Field field = fields[j];
                        Annotation[] annotations = field.getAnnotations();
//                        for(Annotation annotation : annotations){
//                        if(Autowire.class.equals(annotation.getClass())){//如果有某个注解
                            String methName = "get" + field.getName().substring(0,1).toUpperCase()+field.getName().substring(1,field.getName().length());
                            Method mothod = clazz.getDeclaredMethod(methName);
                            Object val = mothod.invoke(instance, null);
                            Cell cell = row.createCell(j);
                            cell.setCellValue(val+"");
//                            break;
//                        }
//                        }
                    }
                }
                FileOutputStream fos = new FileOutputStream(toPath);
                workbook.write(fos);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


  /**
   * 功能描述: 解析excel将其映射到指定实体类上(需要指定excel表格中所映射到类上的属性的数组(mapperString)
   *          例:"姓名(excel单元格属性名):name(实体类属性名)")
   * @param:[filePath, clazz, mapperString, resolver(excel解析器 针对不同格式excel进行解析)]
   * @return:java.util.List<T>
   * @since: 1.0.0
   * @Author:jianglei
   * @Date: 2018/5/28
   */
    public static <T> List<T> parse(String filePath, Class<T> clazz,String[] mapperString,ExcelResolver resolver)  {
        Map<String,String> mapperMap = new HashMap<String,String>();
        for(String str : mapperString){
            String[] stol = str.split(":");
            mapperMap.put(stol[0],stol[1]);
        }
        Workbook workbook = null;
        List<T> resultList = new ArrayList<T>();
        try {
            workbook = initWorkBook(filePath);
            int sheetNum = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                List<Map<String, String>> maps = resolver.parseSheet(workbook.getSheetAt(i),mapperMap);
                for(Map<String,String> map : maps){
                    T pojo = mapToEntity(map, clazz);
                    resultList.add(pojo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("属性名未找到");
            e.printStackTrace();
        }
        return resultList;
    }

//    public static void main(String[] args) {
//        List<User> parse = ExcelAnalysisUtil.parse("C:/Users/20625/Desktop/Group/信息.xlsx", User.class, new String[]{"姓名:name",
//                                        "班级:className", "身高:height", "性别:sex", "年龄:age", "部门id:deptId"},new SimpleExcelResolver());
//        System.out.println(parse);
//    }

}

////最为简单的excel格式解析器
//public class SimpleExcelResolver implements ExcelResolver{
//    public List<Map<String,String>> parseSheet(Sheet sheet,Map<String,String> mapperMap){
//        Row row;
//        int count = 0;
//      //  取出每一个Row
//        Iterator<Row> iterator = sheet.iterator();
//        List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
//        List<String> list = new ArrayList<String>();
//        while(iterator.hasNext()) {
//            row = iterator.next();
//            List<String> rowString = parseRow(row);
//            Iterator<String> it = rowString.iterator();
//          //  由于第一行是标题，因此这里单独处理
//            if (count == 0) {
//                while(it.hasNext()){
//                    list.add(it.next());
//                }
//            } else {
//                Map<String,String> rowMap = new HashMap<String,String>();
//                int num = 0;
//                while(it.hasNext()){
//                    rowMap.put(mapperMap.get(list.get(num)),it.next());
//                    num++;
//                }
//                resultList.add(rowMap);
//            }
//            ++count;
//        }
//        return resultList;
//    }
//
//    /**
//     * 功能描述:调用给定的mapper 和 方法名查询对象返回
//     * @param:[beanName, clazz]
//     * @return:java.util.List<org.apache.poi.ss.formula.functions.T>
//     * @since: 1.0.0
//     * @Author:jianglei
//     * @Date: 2018/7/6
//     */
//    @Override
//    public List<T> parseDataBase(String beanName,T instance,String sqlMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
//        Object bean = applicationContext.getBean(beanName);
//        Class<?> beanClass = bean.getClass();
//        List<T> resultList = null;
//        if(instance == null){
//            Method select = beanClass.getDeclaredMethod(sqlMethodName,null);
//            Object obj = select.invoke(bean, null);
//            resultList = (List<T>)obj;
//        }else {
//            Method select = beanClass.getDeclaredMethod(sqlMethodName,instance.getClass());
//            Object obj = select.invoke(bean, instance);
//            resultList = (List<T>)obj;
//        }
//        return resultList;
//    }
//
//
//    //这里是解析每一行的代码
//    private static List<String> parseRow(Row row) {
//        List<String> rst = new ArrayList<>();
//        Cell cell;
//        //得到每一个cell
//        Iterator<Cell> iterator = row.iterator();
//        System.out.println(row.getRowNum());
//        while (iterator.hasNext()) {
//            cell = iterator.next();
//           // 定义每一个cell的数据类型
//            cell.setCellType(CellType.STRING);
//           // 取出cell中的value
////            System.out.print(cell.getStringCellValue()+",");
//            rst.add(cell.getStringCellValue());
//        }
//        System.out.println();
//        return rst;
//    }
//}


