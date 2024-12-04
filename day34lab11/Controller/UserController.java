package com.example.day34lab11.Controller;

import com.example.day34lab11.ApiResponse.ApiResponse;
import com.example.day34lab11.Model.User;
import com.example.day34lab11.Service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @GetMapping("/check/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        userService.checkUserNameAndPassword(username, password);
        return ResponseEntity.status(200).body(new ApiResponse("username and password are valid"));
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity findUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(userService.findUserByUsername(username));
    }

    @GetMapping("/find-by-registration/{date}")
    public ResponseEntity findUsersByRegistrationDateIsBefore(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(userService.findUsersByRegistrationDateIsBefore(date));
    }

}
