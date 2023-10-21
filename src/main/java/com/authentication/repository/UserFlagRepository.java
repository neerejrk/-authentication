package com.authentication.repository;

import com.authentication.entity.UserFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFlagRepository extends JpaRepository<UserFlag, Integer> {

    UserFlag findUserFlagByIdAndType(Integer id, String type);

}
