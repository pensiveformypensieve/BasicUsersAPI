package com.horcrux.ravenclaw.repository;

import com.horcrux.ravenclaw.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findFirstByUserIdAndActiveStatus(String userId, String activeStatus);

    @Query("SELECT u FROM Users AS u WHERE u.userId =:userId")
    Users getUserByUserId(@Param("userId") String userId);

    Users findByActiveStatus(String activeStatus);

    Users findByUserId(String userId);
}
