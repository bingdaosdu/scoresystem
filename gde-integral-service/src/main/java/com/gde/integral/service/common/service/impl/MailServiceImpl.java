package com.gde.integral.service.common.service.impl;

import com.gde.integral.service.common.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 邮件服务实现类
 *
 * @author ~
 * @date 2019/9/19
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送纯文本邮件
     *
     * @param toList  收件人们
     * @param ccList 抄送人们
     * @param title   标题
     * @param content 内容
     */
    @Override
    public void sendTextMail(List<String> toList, List<String> ccList, String title, String content) {
        String[] toArr = parseToStringArr(toList);
        String[] ccArr = parseToStringArr(ccList);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toArr);
        message.setCc(ccArr);
        message.setSubject(title);
        message.setText(content);

        try {
            javaMailSender.send(message);
            logger.info("Text邮件已经发送。");
        } catch (MailException e) {
            logger.error("发送Text邮件时发生异常！", e);
        }
    }

    /**
     * 发送html邮件
     *
     * @param toList  收件人们
     * @param ccList 抄送人们
     * @param title 标题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(List<String> toList, List<String> ccList, String title, String content) {

        String[] toArr = parseToStringArr(toList);
        String[] ccArr = parseToStringArr(ccList);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toArr);
            helper.setCc(ccArr);
            helper.setSubject(title);
            helper.setText(content, true);

            javaMailSender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MailException | MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    private String[] parseToStringArr(List<String> strList) {
        String[] strArr = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            strArr[i] = strList.get(i);
        }
        return strArr;
    }

}
