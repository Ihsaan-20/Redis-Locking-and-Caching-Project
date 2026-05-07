package com.example.redis_project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String id;
    private String name;
    private String rollNumber;
    private String grade;
}
