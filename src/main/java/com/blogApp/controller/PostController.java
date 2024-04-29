package com.blogApp.controller;

import com.blogApp.entity.Post;
import com.blogApp.payload.DtoPost;
import com.blogApp.payload.ListDto;
import com.blogApp.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/myBlog")
@Controller
public class PostController {
    private service services;

    public PostController(service services) {
        this.services = services;
    }
@PostMapping
    public ResponseEntity<DtoPost> createdPost (@RequestBody DtoPost dtoPost){
        DtoPost dto= services.savepost(dtoPost);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    // http:/localhost:8097/api/myBlog?pageNo=0&pageSize=5&pagedDir=asc&sortBy=title
     @GetMapping()
    public ResponseEntity<?> getAllPost(@RequestParam(name ="pageNo",defaultValue = "0",required = false)int pageNo,
                                        @RequestParam(name = "pageSize",defaultValue = "2",required = false)int pageSize,
                                        @RequestParam(name = "sortDir",defaultValue ="acs",required = false)String sortDir,
                                        @RequestParam(name = "SortBy",defaultValue = "title",required = false)String SortBy
                                        ){
         ListDto listDto = services.getallPost(pageNo, pageSize, SortBy, SortBy);
         return new ResponseEntity<>(listDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        services.deletepost(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
@PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable long id,@RequestBody DtoPost dtoPost){
    Post post = services.getPost(id);
    DtoPost dtoPost1 = services.updatePost(post);
    return new ResponseEntity<>(dtoPost1,HttpStatus.OK);
}
}
