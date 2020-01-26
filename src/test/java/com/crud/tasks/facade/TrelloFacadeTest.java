package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    private List<TrelloListDto> trelloLists;
    private List<TrelloBoardDto> trelloBoards;
    private List<TrelloList> mappedTrelloLists;
    private List<TrelloBoard> mapperTrelloBoards;
    private List<TrelloBoardDto> trelloBoardDtos;
    private TrelloCard trelloCard;
    private TrelloCardDto trelloCardDto;
    private CreatedTrelloCardDto createdTrelloCardDto;

    @Before
    public void init() {
        trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
        mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));
        mapperTrelloBoards = new ArrayList<>();
        mapperTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mapperTrelloBoards);
        trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );
        createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );
        when(trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard))).thenReturn(createdTrelloCardDto);
    }

    @Test
    public void shouldFetchEmptyList() {
        //Given
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mapperTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mapperTrelloBoards)).thenReturn(mapperTrelloBoards);
        //When
        trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("test", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("test_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldCreateCardFromFacade() {
        //Given
        trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //When
        CreatedTrelloCardDto newCard = trelloFacade.createCard(trelloCardDto);
        //Then
        Assert.assertEquals("1", newCard.getId());
        Assert.assertEquals("Test task", newCard.getName());
        Assert.assertEquals("http://test.com", newCard.getShortUrl());
    }
}
