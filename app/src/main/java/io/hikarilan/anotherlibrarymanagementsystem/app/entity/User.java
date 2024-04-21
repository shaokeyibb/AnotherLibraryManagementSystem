package io.hikarilan.anotherlibrarymanagementsystem.app.entity;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_username", columnList = "username")
})
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "username", nullable = false)
    @Setter
    private String username;

    @Column(name = "password", nullable = false)
    @Setter
    private String password;

    @Column(name = "role", nullable = false)
    @Setter
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<BorrowRecord> borrowRecords;

}
