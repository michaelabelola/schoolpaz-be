package com.suiteonix.schoolpaz.mail.internal;

import com.suiteonix.schoolpaz.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
 class MailController {

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/simple")
    public ResponseEntity<String> sendSimpleEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {
        logger.info("Received request to send simple email to: {}", to);
        try {
            mailService.sendSimpleEmail(to, subject, text);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending simple email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/thymeleaf")
    public ResponseEntity<String> sendThymeleafEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("templateString") String templateString,
            @RequestBody Map<String, Object> variables) {
        logger.info("Received request to send Thymeleaf email to: {}", to);
        try {
            mailService.sendThymeleafEmail(to, subject, templateString, variables);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending Thymeleaf email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/thymeleaf/template")
    public ResponseEntity<String> sendThymeleafTemplateEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("templateName") String templateName,
            @RequestBody Map<String, Object> variables) {
        logger.info("Received request to send Thymeleaf template email to: {}", to);
        try {
            mailService.sendThymeleafTemplateEmail(to, subject, templateName, variables);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending Thymeleaf template email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/mustache")
    public ResponseEntity<String> sendMustacheEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("templateString") String templateString,
            @RequestBody Map<String, Object> variables) {
        logger.info("Received request to send Mustache email to: {}", to);
        try {
            mailService.sendMustacheEmail(to, subject, templateString, variables);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending Mustache email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/mustache/template")
    public ResponseEntity<String> sendMustacheTemplateEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("templateName") String templateName,
            @RequestBody Map<String, Object> variables) {
        logger.info("Received request to send Mustache template email to: {}", to);
        try {
            mailService.sendMustacheTemplateEmail(to, subject, templateName, variables);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending Mustache template email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @GetMapping("/test/welcome")
    public ResponseEntity<String> testWelcomeEmail(@RequestParam("to") String to) {
        logger.info("Testing welcome email to: {}", to);
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", "Test User");
            variables.put("username", "testuser");
            variables.put("email", to);
            variables.put("loginUrl", "http://localhost:8080/login");

            mailService.sendThymeleafTemplateEmail(to, "Welcome to SchoolPaz", "mail/welcome", variables);
            return ResponseEntity.ok("Welcome email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending test welcome email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    @GetMapping("/test/notification")
    public ResponseEntity<String> testNotificationEmail(@RequestParam("to") String to) {
        logger.info("Testing notification email to: {}", to);
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", "Test User");
            variables.put("email", to);
            variables.put("message", "This is a test notification from SchoolPaz.");
            variables.put("year", "2023");
            
            Map<String, Object> item1 = new HashMap<>();
            item1.put("title", "New Assignment");
            item1.put("description", "You have a new assignment due next week.");
            item1.put("hasLink", true);
            item1.put("link", "http://localhost:8080/assignments/123");
            
            Map<String, Object> item2 = new HashMap<>();
            item2.put("title", "Upcoming Event");
            item2.put("description", "School sports day is scheduled for next month.");
            item2.put("hasLink", false);
            
            variables.put("items", new Object[]{item1, item2});

            mailService.sendMustacheTemplateEmail(to, "SchoolPaz Notification", "notification", variables);
            return ResponseEntity.ok("Notification email sent successfully");
        } catch (Exception e) {
            logger.error("Error sending test notification email to: {}", to, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending email: " + e.getMessage());
        }
    }
}