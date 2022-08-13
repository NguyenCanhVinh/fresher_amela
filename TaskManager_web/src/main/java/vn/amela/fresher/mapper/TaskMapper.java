package vn.amela.fresher.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.amela.fresher.entity.Task;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    List<Task> findByTitleContaining(@Param("title") String title, @Param("task_id") Long task_id);

    void save(@Param("task") Task task);

    void update(@Param("task") Task task);

    void delete(@Param("task_id") Long task_id);

}
