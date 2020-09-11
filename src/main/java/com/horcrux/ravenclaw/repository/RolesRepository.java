package com.horcrux.ravenclaw.repository;

import com.horcrux.ravenclaw.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    List<String> findRolesByUserId(String userId);

}
