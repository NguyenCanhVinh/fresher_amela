package vn.amela.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.amela.domail.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();
    Page<Task> findByTitleContaining(String title, Pageable pageable);

    Page<Task> findAll( Pageable pageable);
}
