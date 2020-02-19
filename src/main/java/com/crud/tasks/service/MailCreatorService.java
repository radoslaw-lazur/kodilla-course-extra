package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyDetails companyDetails;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private Context contextTrello;
    private List<String> functionality;

    public String buildTrelloCardEmail(String message) {
        contextTrello = new Context();
        contextTrello.setVariable("message", message);
        makeListForIteration();
        makeDataTrelloContext();
        return templateEngine.process("mail/created-trello-card-mail", contextTrello);
    }

    public String buildScheduledTrelloInfoMail(String message) {
        contextTrello = new Context();
        contextTrello.setVariable("message", message);
        makeDataTrelloContext();
        return templateEngine.process("mail/scheduled-trello-cards-mail", contextTrello);
    }

    private void makeDataTrelloContext() {
        contextTrello.setVariable("tasks_url", "https://radoslaw-lazur.github.io/");
        contextTrello.setVariable("button", "Visit website");
        contextTrello.setVariable("goodbye", "Best regards: " + adminConfig.getAdminName());
        contextTrello.setVariable("details", "Company Details:");
        contextTrello.setVariable("admin_name", adminConfig.getAdminName());
        contextTrello.setVariable("app_name", "Company name: " + companyDetails.getAppName());
        contextTrello.setVariable("app_description", "Company description: " + companyDetails.getAppDescription());
        contextTrello.setVariable("company_mail", "Company e-mail: " + companyDetails.getMail());
        contextTrello.setVariable("company_mobile", "Company mobile: " + companyDetails.getMobile());
        contextTrello.setVariable("show_button", true);
        contextTrello.setVariable("show_button_in_scheduled_mail", true);
        contextTrello.setVariable("is_friend", false);
        contextTrello.setVariable("is_friend_in_scheduled_mail", false);
        contextTrello.setVariable("admin_config", adminConfig);
        contextTrello.setVariable("application_functionality", functionality);
    }

    private void makeListForIteration() {
        functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
    }
}
