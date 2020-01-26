package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;

    @Test
    public void testFethTrelloBoardsFromTrelloService() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto(
                "1",
                "name",
                new ArrayList<>()
        );
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("name", trelloBoardDtoList.get(0).getName());
        assertEquals(0, trelloBoardDtoList.get(0).getLists().size());
    }

    @Test
    public void testCreateTrelloCardFromTrelloService() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "name",
                "description",
                "top",
                "listId"
        );
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        TrelloService spy = spy(trelloService);
        doNothing().when(spy).sendTheMailWithCard(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto newCard = spy.createTrelloCard(trelloCardDto);
        //Then
        assertEquals(createdTrelloCardDto.getName(), newCard.getName());
        assertEquals(createdTrelloCardDto.getId(), newCard.getId());
        assertEquals(createdTrelloCardDto.getShortUrl(), newCard.getShortUrl());
    }
}
