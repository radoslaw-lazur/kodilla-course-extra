package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TrelloValidator {


    public void validateCard(final TrelloCard trelloCard) {
        if (trelloCard.getName().contains("test")) {
            log.info("Someone is testing my app");
        } else {
            log.info("Seems my app is used in proper way");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        log.info("Starting filtering boards...");
        List<TrelloBoard> filteredTrelloBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        log.info("Boards have been filtered. Current list size: " + filteredTrelloBoards.size());
        return filteredTrelloBoards;
    }
}
