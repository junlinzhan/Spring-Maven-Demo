package com.app.mvc.configuration;

import com.app.mvc.beans.JsonMapper;
import com.app.mvc.common.SpringHelper;
import com.app.mvc.configuration.service.ConfigurationService;
import com.app.mvc.domain.Configuration;
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
public class DatabaseConfig {

    private static Map<String, String> configMap = Maps.newConcurrentMap();

    public synchronized static void loadAllConfig() {
        log.info("load all config");
        List<Configuration> configurationList = SpringHelper.popBean(ConfigurationService.class).getAll();
        if (CollectionUtils.isEmpty(configurationList)) {
            log.info("no config");
        }
        Map<String, String> tempMap = Maps.newHashMap();
        for (Configuration configuration : configurationList) {
            tempMap.put(configuration.getK(), configuration.getV());
        }
        configMap = tempMap;
        log.info("config: {}", JsonMapper.obj2String(configMap));
    }

    public static int getIntValue(String k, int defaultValue) {
        try {
            if (configMap.containsKey(k)) {
                return Integer.valueOf(configMap.get(k));
            } else {
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            log.error("int parse error, k:{}", k, e);
            return defaultValue;
        }
    }

    public static long getLongValue(String k, long defaultValue) {
        try {
            if (configMap.containsKey(k)) {
                return Long.valueOf(configMap.get(k));
            } else {
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            log.error("long parse error, k:{}", k, e);
            return defaultValue;
        }
    }

    public static String getValue(String k) {
        return configMap.get(k);
    }

    public static String getStringValue(String k, String defaultValue) {
        if (configMap.containsKey(k)) {
            return configMap.get(k);
        } else {
            return defaultValue;
        }
    }

    public static boolean getBooleanValue(String k, boolean defaultValue) {
        if (configMap.containsKey(k)) {
            return Boolean.parseBoolean(configMap.get(k));
        } else {
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
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        for (String str : res) {
            result.add(str);
        }
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
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        for (String str : res) {
            result.add(str);
        }
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
        String v = configMap.get(k);
        if (StringUtils.isBlank(v)) {
            return result;
        }
        Iterable<String> res = Splitter.on(separator).trimResults().omitEmptyStrings().split(v);
        try {
            for (String str : res) {
                result.add(Integer.valueOf(str));
            }
        } catch (NumberFormatException e) {
            log.error("integer list parse error, k:{}", k, e);
            return Lists.newArrayList();
        }
        return result;
    }
}
