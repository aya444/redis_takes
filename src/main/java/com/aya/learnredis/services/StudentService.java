package com.aya.learnredis.services;

import com.aya.learnredis.entity.Student;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, String> hashOperations;

    @PostConstruct // This is called after dependency injection but before the bean is put into service.
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public String createStudent(Student student) {
        String key = "STUDENT:" + student.getId();
        hashOperations.put(key, "id", student.getId());
        hashOperations.put(key, "name", student.getName());
        hashOperations.put(key, "age", String.valueOf(student.getAge()));
        return "Student Created in redis";
    }

    public List<Student> getAllStudents() {
        Set<String> keys = redisTemplate.keys("STUDENT:*");
        List<Student> students = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                Map<String, String> studentMap = hashOperations.entries(key);
                if (!studentMap.isEmpty()) {
                    students.add(new Student(
                            studentMap.get("id"),
                            studentMap.get("name"),
                            Integer.parseInt(studentMap.get("age"))
                    ));
                }
            }
        }
        return students;
    }

    @Cacheable(value = "studentCache", key = "#id") // caches the return value under the key
    public Student getStudentById(Integer id) {
        String key = "STUDENT:" + id;
        Map<String, String> studentMap = hashOperations.entries(key);

        if (studentMap.isEmpty()) {
            return null;
        }

        return new Student(
                studentMap.get("id"),
                studentMap.get("name"),
                Integer.parseInt(studentMap.get("age"))
        );
    }

    @CachePut(value = "studentCache", key = "#student.id") // Updates the values in the cache with a new value.
    public String updateStudent(Student student) {
        String key = "STUDENT:" + student.getId();
        if (redisTemplate.hasKey(key)) {
            hashOperations.put(key, "name", student.getName());
            hashOperations.put(key, "age", String.valueOf(student.getAge()));
            return "Student information updated successfully!";
        } else {
            return "Student not found!";
        }
    }

    @CacheEvict(value = "studentCache", key = "#id") // clears the cache
    public String deleteStudent(Integer id) {
        String key = "STUDENT:" + id;
        redisTemplate.delete(key);
        return "Student deleted from redis";
    }
}


