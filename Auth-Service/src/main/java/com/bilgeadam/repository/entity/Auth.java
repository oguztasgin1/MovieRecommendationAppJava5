package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String email;
    String password;
    String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    ERole role=ERole.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EStatus status=EStatus.PENDING;
}
