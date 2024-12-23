package ex1;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String userId;

    private String password;

    @Column(unique = true)
    private String email;

}
