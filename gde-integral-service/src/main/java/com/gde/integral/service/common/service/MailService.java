package com.gde.integral.service.common.service;

import java.util.List;

/**
 * 邮件服务接口
 *
 * @author ~
 * @date 2019/9/19
 */
public interface MailService {

    /**
     * 发送纯文本邮件
     *
     * @param toList  收件人们
     * @param ccList 抄送人们
     * @param title   标题
     * @param content 内容
     */
    void sendTextMail(List<String> toList, List<String> ccList, String title, String content);

    /**
     * 发送html邮件
     *
     * @param toList  收件人们
     * @param ccList 抄送人们
     * @param title 标题
     * @param content 内容
     */
    void sendHtmlMail(List<String> toList, List<String> ccList, String title, String content);

}
