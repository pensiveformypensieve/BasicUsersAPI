package com.horcrux.ravenclaw.repository;

import com.horcrux.ravenclaw.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Users findFirstByUserId(String userId);

}
