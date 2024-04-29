package com.blogApp.repository;

import com.blogApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment>   findByPostId(long id);
}
