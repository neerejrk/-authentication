package com.authentication.repository;

import com.authentication.entity.UserFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFlagRepository extends JpaRepository<UserFlag, Integer> {
    @Query("SELECT * FROM UserFlag WHERE id = ?1 and type = ?2")
    List<UserFlag> findByIdAndType(String id, String type);
}
