package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloRepositoryFromDbServiceTestSuite {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskRepository taskRepository;
    private Task task;

    @Before
    public void createTaskForTesting() {
        task = new Task("title", "content");
    }

    @Test
    public void testSaveTaskAndGetTaskById() {
        //Given
        dbService.saveTask(task);
        Long taskId = task.getId();
        //When
        Task serviceTask = dbService.getTaskById(taskId);
        //Then
        assertEquals(task.getId(), serviceTask.getId());
        assertEquals(task.getTitle(), serviceTask.getTitle());
        assertEquals(task.getContent(), serviceTask.getContent());
    }

    @Test
    public void testGetAllTasks() {
        //Given
        dbService.saveTask(task);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertEquals(1, taskList.size());
        assertEquals(task.getTitle(), taskList.get(0).getTitle());
    }

    @After
    public void cleanUp() {
        taskRepository.deleteAll();
    }
}

