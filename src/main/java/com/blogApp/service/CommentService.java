package com.blogApp.service;

import com.blogApp.payload.CommentDto;
import com.blogApp.payload.PostWithCommentsDto;

public interface CommentService {
    CommentDto createComments(CommentDto commentDto, long id);

    PostWithCommentsDto getPostWithComment(long id);
}
