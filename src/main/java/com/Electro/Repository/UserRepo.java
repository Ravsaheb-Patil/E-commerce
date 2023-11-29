package com.Electro.Repository;

import com.Electro.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface  UserRepo extends JpaRepository<User,String> {

    Optional<User> findByUserEmail(String email);

    List<User> findByUserNameContaining(String keyword);

    Optional<User> findByUserEmailAndUserPassword(String email,String password);
}
