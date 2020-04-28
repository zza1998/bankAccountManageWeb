package com.zza.jpaa.util;

import org.springframework.util.DigestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonUtil {

    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz)  {
        List<T> returnList = new ArrayList<>();
        if (list.size() == 0){
            return returnList;
        }
        Class[] c2 = null;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors){
            Class[] tClass = constructor.getParameterTypes();
            if (tClass.length == list.get(0).length){
                c2 = tClass;
                break;
            }
        }
        //构造方法实例化对象
        for(Object[] o : list){
            Constructor<T> constructor = null;
            try {
                constructor = clazz.getConstructor(c2);
                returnList.add(constructor.newInstance(o));
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        return returnList;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("1.0.0", "1.2.2"));
    }
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;

        if (v1.length>v2.length){
            String[] v3 = v1;
            v1= v2;
            v2 = v3;
        }
        for (int i = 0; i < v1.length; i++) {
            int a = Integer.parseInt(trims(v1[i]));
            int b = Integer.parseInt(trims(v2[i]));
            if (a>b){
                return len1>len2?-1:1;
            }else if(a<b){
                return len1>len2?1:-1;
            }
        }
        if (len1 == len2) return 0;
        for (int i = v1.length; i < v2.length; i++) {
            if (Integer.parseInt(trims(v2[i]))>0){
                return len1>len2?1:-1;
            }
        }
        return 0;
    }
    public static String trims(String string){

        int i = 0;
        while (i<string.length()&&string.charAt(i)=='0'){
            i++;
        }
        if (i == string.length()) return "0";
        return string.substring(i);
    }
}
