package com.authentication.repository;

import com.authentication.entity.UserFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFlagRepository extends JpaRepository<UserFlag, Integer> {
    //@Query("SELECT userflag FROM UserFlag userflag WHERE userflag.id = ?1 and userflag.type = ?2")
    UserFlag findUserFlagByIdAndType(Integer id, String type);
}
