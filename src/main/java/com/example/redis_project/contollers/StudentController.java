package com.example.redis_project.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.redis_project.models.Student;
import com.example.redis_project.repo.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @PostMapping
    public Student save(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping
    public List<Object> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return repository.delete(id);
    }
}