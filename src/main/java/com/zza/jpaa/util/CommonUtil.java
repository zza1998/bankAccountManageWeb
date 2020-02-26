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
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal bigDecimal1 = new BigDecimal(3);
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }
}
