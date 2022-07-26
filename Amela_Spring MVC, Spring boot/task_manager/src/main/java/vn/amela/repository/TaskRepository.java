package vn.amela.repository;

        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import vn.amela.domail.Task;

        import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

        List<Task> findByTitleContaining(String title);

        Page<Task> findByTitleContaining(String title, Pageable pageable);

        Object findAll(String anyString);
}
