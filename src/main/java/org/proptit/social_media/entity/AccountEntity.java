package org.proptit.social_media.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;
}