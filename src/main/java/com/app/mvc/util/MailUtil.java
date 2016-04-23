package com.app.mvc.util;

import com.app.mvc.beans.Mail;
import com.app.mvc.config.GlobalConfig;
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

        String from = GlobalConfig.getStringValue("mail.send.from", "");
        String host = GlobalConfig.getStringValue("mail.send.smtp", "");
        String pass = GlobalConfig.getStringValue("mail.send.password", "");
        String nickname = GlobalConfig.getStringValue("mail.send.nickname", "");

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
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

