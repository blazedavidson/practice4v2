package ru.practice4v2.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="USERS")
public class SQLUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_seq")
    @SequenceGenerator(name="users_seq", sequenceName="SEQ_USER",allocationSize=5)
    @Column(name="id", updatable=true, nullable=false)
    private long id;
    @Column(name="username", nullable = false, updatable=true, unique = true)
    private String userName;
    @Column(name="surname", nullable = false)
    private String lastName;
    @Column(name="first", nullable = false)
    private String firstName;
    @Column(name="patronymic", nullable = false)
    private String patronymic;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.MERGE)
    private List<SQLLogins> logins;

}
