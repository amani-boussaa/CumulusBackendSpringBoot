package com.example.cumulusspringboot.repositories;

<<<<<<<< HEAD:src/main/java/com/example/cumulusspringboot/repositories/BlogRepository.java
import com.example.cumulusspringboot.entities.Blog;
========
>>>>>>>> origin/aziz:src/main/java/com/example/cumulusspringboot/repositories/UserRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cumulusspringboot.entities.User;


@Repository
<<<<<<<< HEAD:src/main/java/com/example/cumulusspringboot/repositories/BlogRepository.java
public interface BlogRepository extends JpaRepository<Blog, Long> {
========
public interface UserRepository extends JpaRepository<User, Long> {
>>>>>>>> origin/aziz:src/main/java/com/example/cumulusspringboot/repositories/UserRepository.java
}
