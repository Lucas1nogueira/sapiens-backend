package com.sapiens.sapiens.domain.student;

import com.sapiens.sapiens.domain.user.User;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    
    private String matriculation;
    private int age;
    private String sex;

}