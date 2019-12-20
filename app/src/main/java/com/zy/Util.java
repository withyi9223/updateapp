package com.zy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * Created by yi on 2019/12/20.
 */
public class Util {


    public static <T> T getInstance(Object o) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            if (type == null) {
                return null;
            }
            ParameterizedType parameterizedType = null;
            if (type instanceof ParameterizedType) {
                parameterizedType = (ParameterizedType) type;
            }
            if (parameterizedType == null) {
                return null;
            }
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length == 0) {
                return null;
            }
            Type realType = parameterizedType.getActualTypeArguments()[0];
            Class<T> clz = (Class<T>) realType;
            return clz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
