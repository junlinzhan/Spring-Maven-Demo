package com.app.mvc.config;

import com.app.mvc.beans.JsonMapper;
import com.app.mvc.common.SpringHelper;
import com.app.mvc.common.UrlQPSLimiter;
import com.app.mvc.proxy.ProxyManager;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jimin on 15/11/7.
 */
@Slf4j
public class GlobalConfig {

    private static Map<String, String> configMap = Maps.newConcurrentMap();

    private static Map<String, List<String>> listStringMap = Maps.newConcurrentMap();

    private static Map<String, List<Integer>> listIntMap = Maps.newConcurrentMap();

    private static Map<String, Set<String>> setStringMap = Maps.newConcurrentMap();

    private static Map<String, Map<String, String>> mapStringStringMap = Maps.newConcurrentMap();

    public synchronized static void loadAllConfig() {
        log.info("load all config");
        List<Configuration> configurationList = SpringHelper.popBean(ConfigurationService.class).getAll();
        if (CollectionUtils.isEmpty(configurationList)) {
            log.info("no config");
        }
        Map<String, String> tempMap = Maps.newHashMap();
        for (Configuration configuration : configurationList) {
            tempMap.put(configuration.getK(), configuration.getV().trim());
        }
        ProxyManager.getProxyManager().reload(tempMap);
        configMap = tempMap;
        listStringMap = Maps.newConcurrentMap();
        listIntMap = Maps.newConcurrentMap();
        setStringMap = Maps.newConcurrentMap();
        UrlQPSLimiter.onChange();
        log.info("config: {}", JsonMapper.obj2String(configMap));
    }

    public static int getIntValue(String k, int defaultValue) {
        try {
            if (configMap.containsKey(k)) {
                return Integer.valueOf(configMap.get(k));
            } else {
                log.info("config use default value, key:{}, value:{}", k, defaultValue);
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            log.error("int parse error, k:{}", k, e);
            log.info("config use default value, key:{}, value:{}", k, defaultValue);
            return defaultValue;
        }
    }

    public static long getLongValue(String k, long defaultValue) {
        try {
            if (configMap.containsKey(k)) {
                return Long.valueOf(configMap.get(k));
            } else {
                log.info("config use default value, key:{}, value:{}", k, defaultValue);
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            log.error("long parse error, k:{}", k, e);
            log.info("config use default value, key:{}, value:{}", k, defaultValue);
            return defaultValue;
        }
    }

    public static String getValue(String k) {
        return configMap.get(k);
    }

    public static String getStringValue(String k, String defaultValue) {
        if (configMap.containsKey(k)) {
            return configMap.get(k).trim();
        } else {
            log.info("config use default value, key:{}, value:{}", k, defaultValue);
            return defaultValue;
        }
    }

    public static boolean getBooleanValue(String k, boolean defaultValue) {
        if (configMap.containsKey(k)) {
            return Boolean.parseBoolean(configMap.get(k));
        } else {
            log.info("config use default value, key:{}, value:{}", k, defaultValue);
            return defaultValue;
        }
    }

    public static Set<String> getSetValue(String k) {
        return getSetValue(k, ",");
    }

    public static Set<String> getSetValue(String k, String separator) {
        Set<String> result = Sets.newHashSet();
        if (!configMap.containsKey(k)) {
            return result;
        }
        if (setStringMap.containsKey(k)) {
            return setStringMap.get(k);
        }
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        for (String str : res) {
            result.add(str.trim());
        }
        setStringMap.put(k, result);
        return result;
    }

    public static List<String> getListStringValue(String k) {
        return getListStringValue(k, ",");
    }

    public static List<String> getListStringValue(String k, String separator) {
        List<String> result = Lists.newArrayList();
        if (!configMap.containsKey(k)) {
            return result;
        }
        if (listStringMap.containsKey(k)) {
            return listStringMap.get(k);
        }
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        for (String str : res) {
            result.add(str.trim());
        }
        listStringMap.put(k, result);
        return result;
    }

    public static List<Integer> getListIntValue(String k) {
        return getListIntValue(k);
    }

    public static List<Integer> getListIntValue(String k, String separator) {
        List<Integer> result = Lists.newArrayList();
        if (!configMap.containsKey(k)) {
            return result;
        }
        if (listIntMap.containsKey(k)) {
            return listIntMap.get(k);
        }
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        try {
            for (String str : res) {
                result.add(Integer.valueOf(str.trim()));
            }
        } catch (NumberFormatException e) {
            log.error("List<Integer> parse error, k:{}, v:{}", k, v, e);
            return Lists.newArrayList();
        }
        listIntMap.put(k, result);
        return result;
    }

    public static Map<String, String> getMapValue(String k) {
        return getMapValue(k, ";", ",");
    }
    public static Map<String, String> getMapValue(String k, String sep1, String sep2) {
        Map<String, String> result = Maps.newHashMap();
        if(!configMap.containsKey(k)) {
            return result;
        }
        if(mapStringStringMap.containsKey(k)) {
            return mapStringStringMap.get(k);
        }
        String v = configMap.get(k);
        if(StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(sep1).trimResults().omitEmptyStrings().split(v);
        try {
            for (String str : res) {
                String[] temp = str.split(sep2);
                Preconditions.checkArgument(temp.length == 2);
                result.put(temp[0].trim(), temp[1].trim());
            }
        } catch (Exception e) {
            log.error("Map<String, String> parse error, k:{}, v:{}", k, v, e);
            return Maps.newConcurrentMap();
        }
        mapStringStringMap.put(k, result);
        return result;
    }
}
