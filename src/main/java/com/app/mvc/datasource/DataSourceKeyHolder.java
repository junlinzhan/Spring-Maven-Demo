package com.app.mvc.datasource;

import java.util.LinkedList;

/**
 * 保存一个线程中的动态数据源的key
 * Created by jimin on 16/03/22.
 */
public class DataSourceKeyHolder {

    private static final ThreadLocal<LinkedList<String>> holder = new ThreadLocal<LinkedList<String>>() {
        @Override
        protected LinkedList<String> initialValue() {
            return new LinkedList<>();
        }
    };

    public static void set(String key) {
        holder.get().push(key);
    }

    public static void clear() {
        holder.get().pop();
    }

    public static String getCurrentKey() {
        if (holder.get().size() == 0)
            return null;
        return holder.get().getFirst();
    }

    public static boolean isNestedCall() {
        return holder.get().size() > 1;
    }

}
