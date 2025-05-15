package com.example.usermanagement.service;

import com.example.usermanagement.model.AppUser;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public AppUser updateUser(Long id, AppUser updatedUser){
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            AppUser existingUser = optionalUser.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        }else {
            throw new RuntimeException("User not found with id "+id);
        }
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
