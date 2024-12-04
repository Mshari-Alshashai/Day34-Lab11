package com.example.day34lab11.Repository;

import com.example.day34lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.username=?1 and u.password=?2")
    User checkUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

    List<User> findUsersByRegistrationDateIsBefore(LocalDate date);

}
