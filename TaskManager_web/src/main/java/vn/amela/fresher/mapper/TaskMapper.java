package vn.amela.fresher.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.amela.fresher.entity.Task;

import java.util.List;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    List<Task> findByTitleContaining(@Param("title") String title, @Param("task_id") Long task_id);

}
