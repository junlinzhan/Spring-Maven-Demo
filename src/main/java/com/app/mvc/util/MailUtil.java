package com.app.mvc.util;

import com.app.mvc.beans.Mail;
import com.app.mvc.config.GlobalConfig;
import com.app.mvc.config.GlobalConfigKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by jimin on 15/11/21.
 */

@Slf4j
public class MailUtil {

    public static boolean send(Mail mail) {

        String from = GlobalConfig.getStringValue(GlobalConfigKey.MAIL_SEND_FROM, "");
        int port = GlobalConfig.getIntValue(GlobalConfigKey.MAIL_SEND_PORT, 25);
        String host = GlobalConfig.getStringValue(GlobalConfigKey.MAIL_SEND_SMTP, "");
        String pass = GlobalConfig.getStringValue(GlobalConfigKey.MAIL_SEND_PASSWORD, "");
        String nickname = GlobalConfig.getStringValue(GlobalConfigKey.MAIL_SEND_NICKNAME, "");

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers(), ",") + "失败", e);
            return false;
        }
    }

}

