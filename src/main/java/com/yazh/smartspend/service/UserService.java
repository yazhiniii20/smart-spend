package com.yazh.smartspend.service;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

//business logics reside here
@Service 
public class UserService {
   @Autowired //Dependency Injection
   private UserRepository userRepository;

   public User saveUser(User user){
    return userRepository.save(user);
   }
   public List<User> getAllUsers(){
    return userRepository.findAll();
   }
   public User getUserById(Long id){
      return userRepository.findById(id).orElse(null);
   }
   public User updateUser(Long id,User updatedUser){
      User existingUser = userRepository.findById(id).orElse(null);
      if(existingUser != null){
         existingUser.setEmail(updatedUser.getEmail());
         existingUser.setName(updatedUser.getName());
         existingUser.setPassword(updatedUser.getPassword());
         existingUser.setRole(updatedUser.getRole());
         return userRepository.save(existingUser);
      }
      return null;
   }
   public void deleteUser(Long id){
      userRepository.deleteById(id);
   }  
}
