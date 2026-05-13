package com.yazh.smartspend.service;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   @Autowired
   private UserRepository userRepository;
   public User saveUser(User user){
    return userRepository.save(user);
   }  
}
