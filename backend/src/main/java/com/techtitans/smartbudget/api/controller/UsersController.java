package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.model.Users;
import com.techtitans.smartbudget.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/user")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = usersService.create(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable int userId) {
        return usersService.getById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}") 
    public ResponseEntity<Void> updateUser(@PathVariable int userId, @RequestBody Users user) {
        usersService.update(userId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        usersService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
