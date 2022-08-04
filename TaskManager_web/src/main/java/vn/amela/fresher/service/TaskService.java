package vn.amela.fresher.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.amela.fresher.entity.Task;
import vn.amela.fresher.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    Page<Task> findByTitleContaining(String title, Pageable page);

    List<Task> findAll();

    List<TaskStatus> findAllTasks();
}
