package com.blogApp.controller;

import com.blogApp.payload.CommentDto;
import com.blogApp.payload.PostWithCommentsDto;
import com.blogApp.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@NoArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
@Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http:/localhost:8097/api/comments/1
    @PostMapping("/{id}")
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto, @PathVariable long id){
        CommentDto comments = commentService.createComments(commentDto, id);
        return new ResponseEntity<>(comments, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> postWithComments(@PathVariable long id){
        PostWithCommentsDto postWithComment = commentService.getPostWithComment(id);
        return  new ResponseEntity<>(postWithComment,HttpStatus.OK);
    }

}
