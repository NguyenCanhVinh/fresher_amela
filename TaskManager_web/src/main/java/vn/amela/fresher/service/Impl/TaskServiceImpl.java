package vn.amela.fresher.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.amela.fresher.entity.Task;
import vn.amela.fresher.entity.TaskStatus;
import vn.amela.fresher.mapper.TaskMapper;
import vn.amela.fresher.repository.TaskRepository;
import vn.amela.fresher.repository.TaskStatusRepository;
import vn.amela.fresher.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskMapper taskMapper;

    public Page<Task> findByTitleContaining(String title, Pageable page) {
        return taskRepository.findByTitleContaining(title, page);
    }

    @Autowired
    public List<TaskStatus> findAllTasks(){
        return  taskStatusRepository.findAll();
    }

    @Autowired
    public List<Task> findAll() {
        return taskMapper.findAll();
    }
}
