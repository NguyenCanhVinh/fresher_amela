package vn.amela.fresher.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.amela.fresher.entity.Task;
import vn.amela.fresher.repository.TaskRepository;
import vn.amela.fresher.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> findByTitleContaining(String title, Pageable page) {
        return taskRepository.findByTitleContaining(title, page);
    }

    @Autowired
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
