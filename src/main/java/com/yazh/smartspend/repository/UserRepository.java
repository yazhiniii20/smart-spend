package com.yazh.smartspend.repository;
import com.yazh.smartspend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
