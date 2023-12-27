package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.api.model.Users;
import com.techtitans.smartbudget.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Users create(Users user) {
        return usersRepository.save(user);
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Optional<Users> getById(int userId) {
        return usersRepository.findById(userId);
    }

    public void update(int userId, Users updatedUser) {
        Optional<Users> existingUserOptional = usersRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();

            // Update fields here
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword_hash(updatedUser.getPassword_hash());
            existingUser.setDate_created(updatedUser.getDate_created());
            existingUser.setLast_login(updatedUser.getLast_login());

            usersRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    public void delete(int userId) {
        usersRepository.deleteById(userId);
        // Handle user not found case
    }
}
