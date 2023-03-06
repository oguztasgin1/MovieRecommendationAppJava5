package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepositroy extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findByUsernameAndPassword(String username, String password);
    Optional<UserProfile> findOptionalByAuthid(Long id);
}
