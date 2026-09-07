package com.SaralSewa.SaralSewa.shared.core.service;



import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;


    public void sendLoginOtpEmail(String toEmail, String firstName, String otpCode, int expiryMinutes) {
        sendOtpEmail(toEmail, firstName, otpCode, expiryMinutes,
                "Your Saral Sewa Login Code",
                "otp-email.ftl");
    }

    public void sendForgotPasswordOtpEmail(String toEmail, String firstName, String otpCode, int expiryMinutes) {
        sendOtpEmail(toEmail, firstName, otpCode, expiryMinutes,
                "Reset Your Saral Sewa Password",
                "forgot-password-email.ftl");
    }


    private void sendOtpEmail(String toEmail, String firstName, String otpCode, int expiryMinutes,
                              String subject, String templateName) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("firstName", firstName);
            model.put("otpCode", otpCode);
            model.put("expiryMinutes", expiryMinutes);

            String htmlBody = renderTemplate(templateName, model);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            javaMailSender.send(message);
            log.info("OTP email sent to {} [{}]", toEmail, templateName);
        } catch (Exception e) {
            log.error("Failed to send OTP email to {}: {}", toEmail, e.getMessage());
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    private String renderTemplate(String templateName, Map<String, Object> model) {
        try {
            if (freemarkerConfig != null) {
                Template template = freemarkerConfig.getTemplate(templateName);
                StringWriter writer = new StringWriter();
                template.process(model, writer);
                return writer.toString();
            }
        } catch (Exception ex) {
            log.warn("FreeMarker template rendering failed for [{}], using built-in HTML fallback: {}", templateName, ex.getMessage());
        }

        String name = String.valueOf(model.getOrDefault("firstName", "Valued User"));
        String code = String.valueOf(model.getOrDefault("otpCode", ""));
        String exp = String.valueOf(model.getOrDefault("expiryMinutes", "5"));

        return "<div style='font-family:Arial,sans-serif;max-width:500px;margin:auto;padding:20px;border:1px solid #e2e8f0;border-radius:10px;'>"
                + "<h2 style='color:#2563eb;text-align:center;'>⚡ SaralSewa Verification</h2>"
                + "<p>Hello <b>" + name + "</b>,</p>"
                + "<p>Your One-Time Password (OTP) verification code is:</p>"
                + "<div style='text-align:center;padding:15px;background:#eff6ff;border-radius:8px;font-size:28px;letter-spacing:6px;font-weight:bold;color:#1e40af;margin:20px 0;'>"
                + code + "</div>"
                + "<p style='font-size:13px;color:#64748b;'>This code expires in " + exp + " minutes. If you did not request this, please ignore.</p>"
                + "</div>";
    }
}