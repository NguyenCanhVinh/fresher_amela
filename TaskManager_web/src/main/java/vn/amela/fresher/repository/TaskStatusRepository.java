package vn.amela.fresher.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.fresher.entity.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long> {

}
