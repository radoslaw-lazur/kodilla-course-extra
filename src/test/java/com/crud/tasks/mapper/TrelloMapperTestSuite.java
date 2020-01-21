package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;
    private TrelloCardDto trelloCardDto;
    private TrelloCard trelloCard;
    private TrelloListDto trelloListDto;
    private TrelloList trelloList;
    private TrelloBoardDto trelloBoardDto;
    private TrelloBoard trelloBoard;

    @Before
    public void init() {
         trelloCardDto = new TrelloCardDto(
                "test_card_dto",
                "Description",
                "top",
                "test_id"
        );
         trelloCard = new TrelloCard(
                "test card",
                "Description",
                "bottom",
                "test id"
        );
        trelloListDto = new TrelloListDto(
                "test_id",
                "test_name",
                true
        );
        trelloList = new TrelloList(
                "test id",
                "test name",
                true
        );
        trelloBoardDto = new TrelloBoardDto(
                "test_name",
                "test_id",
                new ArrayList<>()
        );
        trelloBoard = new TrelloBoard(
                "test id",
                "test name",
                new ArrayList<>()
        );
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        //When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), mappedTrelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), mappedTrelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), mappedTrelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), mappedTrelloCardDto.getListId());
    }
    @Test
    public void testMapToTrelloCard() {
        //Given
        //When
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), mappedTrelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), mappedTrelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), mappedTrelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), mappedTrelloCard.getListId());
    }
    @Test
    public void mapToTrelloListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloLists.size(), trelloListDtos.size());
        assertEquals(trelloLists.get(0).getId(), trelloListDtos.get(0).getId());
        assertEquals(trelloLists.get(0).getName(), trelloListDtos.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed() && trelloListDtos.get(0).isClosed());
    }
    @Test
    public void mapToTrelloList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(trelloListDtos.size(), trelloLists.size());
        assertEquals(trelloListDtos.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(trelloListDtos.get(0).getName(), trelloLists.get(0).getName());
        assertTrue(trelloListDtos.get(0).isClosed() && trelloLists.get(0).isClosed());
    }
    @Test
    public void mapToTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        trelloBoardDtos.get(0).getLists().add(trelloListDto);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardDtos);
        //Then
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getLists().size(), trelloBoards.get(0).getLists().size());
        assertTrue(trelloBoards.get(0).getLists().get(0).isClosed());
    }
    @Test
    public void mapToTrelloBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        trelloBoards.get(0).getLists().add(trelloList);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().size(), trelloBoardDtos.get(0).getLists().size());
        assertTrue(trelloBoardDtos.get(0).getLists().get(0).isClosed());
    }
}
