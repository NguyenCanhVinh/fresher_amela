package vn.amela.fresher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @NotEmpty
    @Length(min = 2)
    private String title;

    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "id")
    private  TaskStatus taskStatus;
}
