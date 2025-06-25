package com.suiteonix.schoolpaz.mail;

import java.util.Map;

/**
 * Service for sending emails with template processing capabilities.
 */
public interface MailService {

    /**
     * Send an email with a plain text body.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param text    plain text content
     */
    void sendSimpleEmail(String to, String subject, String text);

    /**
     * Send an email with HTML content processed from a Thymeleaf template string.
     *
     * @param to             recipient email address
     * @param subject        email subject
     * @param templateString Thymeleaf template as a string
     * @param variables      variables to be used in the template
     */
    void sendThymeleafEmail(String to, String subject, String templateString, Map<String, Object> variables);

    /**
     * Send an email with HTML content processed from a Thymeleaf template file.
     *
     * @param to           recipient email address
     * @param subject      email subject
     * @param templateName name of the Thymeleaf template file (without extension)
     * @param variables    variables to be used in the template
     */
    void sendThymeleafTemplateEmail(String to, String subject, String templateName, Map<String, Object> variables);

    /**
     * Send an email with HTML content processed from a Mustache template string.
     *
     * @param to             recipient email address
     * @param subject        email subject
     * @param templateString Mustache template as a string
     * @param variables      variables to be used in the template
     */
    void sendMustacheEmail(String to, String subject, String templateString, Map<String, Object> variables);

    /**
     * Send an email with HTML content processed from a Mustache template file.
     *
     * @param to           recipient email address
     * @param subject      email subject
     * @param templateName name of the Mustache template file (without extension)
     * @param variables    variables to be used in the template
     */
    void sendMustacheTemplateEmail(String to, String subject, String templateName, Map<String, Object> variables);
}