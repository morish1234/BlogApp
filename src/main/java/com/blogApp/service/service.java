package com.blogApp.service;

import com.blogApp.entity.Post;
import com.blogApp.payload.DtoPost;
import com.blogApp.payload.ListDto;

import java.util.List;

public interface service {
    Post getPost(long id);

    public DtoPost savepost(DtoPost dtoPost);

     ListDto getallPost(int pageNo, int pageSize, String SortBy, String SortDir);

    void deletepost(long id);

    public DtoPost updatePost(Post post);
}
