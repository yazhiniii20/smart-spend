package com.yazh.smartspend.service;
import com.yazh.smartspend.dto.UserRequestDto;
import com.yazh.smartspend.dto.UserResponseDto;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yazh.smartspend.dto.UserRequestDto;
import java.util.*;

//business logics reside here
@Service 
public class UserService {
   @Autowired //Dependency Injection
   private UserRepository userRepository;
   private UserResponseDto mapToDto(User user) {
      return new UserResponseDto(
              user.getId(),
              user.getName(),
              user.getEmail(),
              user.getRole()
      );
  }

public UserResponseDto saveUser(UserRequestDto userRequestDto) {
    User user = new User();
    user.setName(userRequestDto.getName());
    user.setEmail(userRequestDto.getEmail());
    user.setPassword(userRequestDto.getPassword());
    user.setRole(userRequestDto.getRole());
    User savedUser = userRepository.save(user);
    return mapToDto(savedUser);
}

   public List<UserResponseDto> getAllUsers(){
     List<User> users = userRepository.findAll();
     List<UserResponseDto> response = new ArrayList<>();
     for(User user:users){
      response.add(mapToDto(user));
     }
     return response;
   }

   public UserResponseDto getUserById(Long id){
      User user = userRepository.findById(id).orElse(null);
      if(user != null){
         return mapToDto(user);
      }
      return null;
   }

   public UserResponseDto updateUser(Long id,User updatedUser){
      User existingUser = userRepository.findById(id).orElse(null);
      if(existingUser != null){
         existingUser.setEmail(updatedUser.getEmail());
         existingUser.setName(updatedUser.getName());
         existingUser.setPassword(updatedUser.getPassword());
         existingUser.setRole(updatedUser.getRole());
         User savedUser = userRepository.save(existingUser);
         return mapToDto(savedUser);
      }
      return null;
   }

   public void deleteUser(Long id){
      userRepository.deleteById(id);
   }  
}
