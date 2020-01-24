package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;
    private Task task;
    private TaskDto taskDto;

    @Before
    public void init() {
        task = new Task(
                1L,
                "title",
                "content"
        );
        taskDto = new TaskDto(
                1L,
                "titleDto",
                "contentDto"
        );
    }

    @Test
    public void testMaptoTaskDto() {
        //Given
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), mappedTaskDto.getId());
        assertEquals(task.getTitle(), mappedTaskDto.getTitle());
        assertEquals(task.getContent(), mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTask() {
        //Given
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), mappedTask.getId());
        assertEquals(taskDto.getTitle(), mappedTask.getTitle());
        assertEquals(taskDto.getContent(), mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), taskDtos.size());
        assertEquals(tasks.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(tasks.get(0).getTitle(), taskDtos.get(0).getTitle());
        assertEquals(tasks.get(0).getContent(), taskDtos.get(0).getContent());
    }
}
