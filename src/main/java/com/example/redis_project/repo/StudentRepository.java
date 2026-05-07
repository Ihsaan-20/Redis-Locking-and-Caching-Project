package com.example.redis_project.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.redis_project.models.Student;

import java.util.List;

@Repository
public class StudentRepository {

    public static final String HASH_KEY = "Student";

    private final RedisTemplate<String, Object> template;

    public StudentRepository(
            @Qualifier("redisTemplate")
            RedisTemplate<String, Object> template) {

        this.template = template;
    }

    // Save Student
    public Student save(Student student) {
        template.opsForHash().put(HASH_KEY, student.getId(), student);
        return student;
    }

    // Get All Students
    public List<Object> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    // Find by ID
    public Student findById(String id) {
        return (Student) template.opsForHash().get(HASH_KEY, id);
    }

    // Delete
    public String delete(String id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "Student removed!";
    }
}