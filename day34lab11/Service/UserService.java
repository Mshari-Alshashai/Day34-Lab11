package com.example.day34lab11.Service;

import com.example.day34lab11.ApiResponse.ApiException;
import com.example.day34lab11.Model.User;
import com.example.day34lab11.Repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);

    }

    public void updateUser(Integer id,User user) {
        if (userRepository.findUserById(id) == null) throw new ApiException("User not found");
        User oldUser = userRepository.findUserById(id);

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        if (userRepository.findUserById(id) == null) throw new ApiException("User not found");
        userRepository.deleteById(id);
    }

    public void checkUserNameAndPassword(String username, String password) {
        if (userRepository.checkUsernameAndPassword(username,password)==null) throw new ApiException("Username and password are not correct");
    }

    public User findUserByUsername(String username) {
        if (userRepository.findUserByUsername(username)==null) throw new ApiException("User not found");
        return userRepository.findUserByUsername(username);
    }

    public List<User> findUsersByRegistrationDateIsBefore(LocalDate date) {
        if (userRepository.findUsersByRegistrationDateIsBefore(date).isEmpty()) throw new ApiException("Users not found");
        return userRepository.findUsersByRegistrationDateIsBefore(date);
    }


}
