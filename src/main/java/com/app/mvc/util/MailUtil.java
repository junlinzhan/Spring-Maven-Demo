package com.app.mvc.util;

import com.app.mvc.beans.Mail;
import com.app.mvc.configuration.DatabaseConfig;
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

        String from = DatabaseConfig.getStringValue("mail.send.from", "kanwangzjm@126.com");
        String host = DatabaseConfig.getStringValue("mail.send.smtp", "smtp.126.com");
        String pass = DatabaseConfig.getStringValue("mail.send.password", "880103");
        String nickname = DatabaseConfig.getStringValue("mail.send.nickname", "alert");

        // 发送email
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName(host);
            // 字符编码集的设置
            email.setCharset("UTF-8");
            // 收件人的邮箱
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            // 发送人的邮箱
            email.setFrom(from, nickname);
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(from, pass);
            // 要发送的邮件主题
            email.setSubject(mail.getSubject());
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(mail.getMessage());
            // 发送
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.info("{} 发送邮件到 {} 失败", from, StringUtils.join(mail.getReceivers(), ","));
            return false;
        }
    }

}

