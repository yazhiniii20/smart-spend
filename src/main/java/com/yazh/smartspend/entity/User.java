package com.yazh.smartspend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;
    private String role;
    @OneToMany(mappedBy = "user")//The relationship is managed by the user field in Expense.
    @JsonIgnore//Prevents infinte loop and massive JSON responses
    private List<Expense> expenses;
}
