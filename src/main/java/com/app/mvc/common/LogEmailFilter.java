package com.app.mvc.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.app.mvc.config.GlobalConfig;
import com.app.mvc.config.GlobalConfigKey;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 基于logback的报警邮件过滤器，该过滤器主要提供如下功能：
 * （1）等级过滤，目前只接受error的日志
 * （2）内容过滤，可以通过正则表达式匹配对应的内容从而打倒过滤效果
 * （3）日志内容过滤的动态配置功能，该功能主要依赖配置库。
 * 配置表中保存的内容是：
 * logback.filter.msg="filter1";"filter2";..."filterN"
 */
public class LogEmailFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (!event.getLevel().equals(Level.ERROR)) {
            return FilterReply.DENY;
        }

        // 是否开通邮件通知
        boolean isOpenEmail = GlobalConfig.getBooleanValue(GlobalConfigKey.LOGBACK_EMAIL_OPEN, false);
        if (!isOpenEmail) {
            return FilterReply.DENY;
        }

        // 是否过滤指定的logger
        String filterString = GlobalConfig.getStringValue(GlobalConfigKey.LOGBACK_FILTER_MSG, "");
        if (StringUtils.isBlank(filterString)) {
            return FilterReply.ACCEPT;
        }

        String message = event.getMessage();
        List<String> list = Splitter.on(";").splitToList(filterString);
        for (String filterMsg : list) {
            if (filterMsg.length() > 2) {
                // 每项均为“”包裹
                filterMsg = filterMsg.substring(1, filterMsg.length() - 1);
                if (message.contains(filterMsg)) {
                    return FilterReply.DENY;
                }
            }
        }

        return FilterReply.ACCEPT;
    }

}
