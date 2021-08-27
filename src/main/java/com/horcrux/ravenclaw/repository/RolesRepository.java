package com.horcrux.ravenclaw.repository;

import com.horcrux.ravenclaw.domain.Roles;
import com.horcrux.ravenclaw.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    List<Roles> findRoleByUserId(String userId);

    @Query("SELECT r.role FROM Roles AS r WHERE r.userId =:userId")
    List<String> getRolesByUserId(@Param("userId") String userId);

}
