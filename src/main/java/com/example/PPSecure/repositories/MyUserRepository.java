package com.example.PPSecure.repositories;

import com.example.PPSecure.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String name);

    @Query("SELECT u FROM MyUser u JOIN FETCH u.roles WHERE u.id = :id")
    Optional<MyUser> findByIdWithRoles(@Param("id") long id);
    @Query ("SELECT u FROM MyUser u JOIN FETCH u.roles WHERE u.id = :id")
    public Optional<MyUser> findById(@Param ("id")long id);
}
