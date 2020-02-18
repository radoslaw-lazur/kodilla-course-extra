package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyDetails companyDetails;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private Context context = new Context();

    public String buildTrelloCardEmail(String message) {
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:5000/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", "Best regards: " + adminConfig.getAdminName());
        context.setVariable("details", "Company Details:");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("app_name", "Company name: " + companyDetails.getAppName());
        context.setVariable("app_description", "Company description: " + companyDetails.getAppDescription());
        context.setVariable("company_mail", "Company e-mail: " + companyDetails.getMail());
        context.setVariable("company_mobile", "Company mobile: " + companyDetails.getMobile());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
