package vn.amela.service.impl;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.amela.domail.Task;
import vn.amela.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

   @InjectMocks
    TaskServiceImpl taskService;

   private  Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    @Test
    void findAll()  {
        task= new Task();
        task.setTitle("Task1");
        task.setDescription("Noi dung 1");
        task.setId(1L);

        when(taskRepository.findAll()).thenReturn((List<Task>) task);
        Assertions.assertEquals(taskService.findAll(), task);
    }

    @Test
    void findByTitleContaining() {
    }

    @Test
    void testFindAll() {

    }
    @Test
    void FindAll_ReturnsAPagedListOfTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(
                new Task(1L, "Fake task 1","Description1"),
                new Task(2L, "Fake task 2","Description2"),
                new Task(3L, "Fake task 3","Description3"),
                new Task(4L, "Fake task 4","Description4")
        ));

        Pageable pageRequest = PageRequest.of(0, 4);
        List<Task> tasks = taskService.findAll(pageRequest).getContent();

      //  assertThat(tasks.size(), equalTo(4));

        verify(taskRepository).findAll();
    }


}