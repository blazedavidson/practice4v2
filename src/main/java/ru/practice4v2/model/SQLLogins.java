package ru.practice4v2.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOGINS")

public class SQLLogins {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "SEQ_USER, allocationSize=5")
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "access_date")
    private Timestamp accessDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private SQLUsers user;
    @Column(name = "application")
    private String application;

}
