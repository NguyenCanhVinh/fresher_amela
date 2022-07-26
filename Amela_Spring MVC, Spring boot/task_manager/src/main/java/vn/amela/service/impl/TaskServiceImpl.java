package vn.amela.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.amela.domail.Task;
import vn.amela.repository.TaskRepository;
import vn.amela.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    @Override
    public Page<Task> findByTitleContaining(String title, Pageable pageable) {
        return taskRepository.findByTitleContaining(title, pageable);
    }
   @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
