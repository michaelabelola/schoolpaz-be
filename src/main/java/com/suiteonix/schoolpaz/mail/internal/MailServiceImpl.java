package com.suiteonix.schoolpaz.mail.internal;

import com.samskivert.mustache.Mustache;
import com.suiteonix.schoolpaz.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final Mustache.Compiler mustacheCompiler;

    @Value("${spring.mail.username:noreply@schoolpaz.com}")
    private String fromEmail;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailSender = mailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        this.mustacheCompiler = Mustache.compiler().escapeHTML(false);
    }

    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        logger.info("Sending simple email to: {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        logger.info("Simple email sent successfully");
    }

    @Override
    public void sendThymeleafEmail(String to, String subject, String templateString, Map<String, Object> variables) {
        logger.info("Sending Thymeleaf email to: {}", to);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            Context context = new Context();
            if (variables != null) {
                variables.forEach(context::setVariable);
            }

            String htmlContent = thymeleafTemplateEngine.process(templateString, context);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("Thymeleaf email sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send Thymeleaf email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendThymeleafTemplateEmail(String to, String subject, String templateName, Map<String, Object> variables) {
        logger.info("Sending Thymeleaf template email to: {}", to);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            Context context = new Context();
            if (variables != null) {
                variables.forEach(context::setVariable);
            }

            String htmlContent = thymeleafTemplateEngine.process(templateName, context);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("Thymeleaf template email sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send Thymeleaf template email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendMustacheEmail(String to, String subject, String templateString, Map<String, Object> variables) {
        logger.info("Sending Mustache email to: {}", to);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            String htmlContent = mustacheCompiler.compile(new StringReader(templateString)).execute(variables);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("Mustache email sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send Mustache email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendMustacheTemplateEmail(String to, String subject, String templateName, Map<String, Object> variables) {
        logger.info("Sending Mustache template email to: {}", to);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            String templatePath = "templates/mail/" + templateName + ".mustache";
            ClassPathResource resource = new ClassPathResource(templatePath);

            if (!resource.exists()) {
                throw new RuntimeException("Template not found: " + templatePath);
            }

            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            String htmlContent = mustacheCompiler.compile(reader).execute(variables);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            logger.info("Mustache template email sent successfully");
        } catch (MessagingException | IOException e) {
            logger.error("Failed to send Mustache template email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }
}