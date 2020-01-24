package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService simpleEmailService;
    private static final String SUBJECT = "Tasks: New Trello Card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        sendTheMailWithCard(newCard);
        return newCard;
    }

    protected void sendTheMailWithCard(final CreatedTrelloCardDto createdTrelloCardDto) {
        ofNullable(createdTrelloCardDto).ifPresent(card -> simpleEmailService
                .send(new Mail(
                        adminConfig.getAdminMail(),
                        null,
                        SUBJECT,
                        "New card: " + createdTrelloCardDto.getName() + " has been created on your Trello account"
                )));
    }
}
