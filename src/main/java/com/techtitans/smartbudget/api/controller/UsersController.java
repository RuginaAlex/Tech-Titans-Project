package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.api.model.Users;
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

    @GetMapping("/users") // Schimbat la plural pentru a aduce toți utilizatorii
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}") // Adăugat placeholder pentru userId
    public ResponseEntity<Users> getUserById(@PathVariable String userId) { // Corectat pentru a include @PathVariable
        return usersService.getById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}") // Adăugat placeholder pentru userId
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody Users user) {
        usersService.update(userId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}") // Corectat pentru a include @PathVariable cu numele corect
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        usersService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
