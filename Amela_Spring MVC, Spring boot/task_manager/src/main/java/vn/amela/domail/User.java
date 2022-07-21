package vn.amela.domail;

import javax.persistence.*;

@Table
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String username;
}
