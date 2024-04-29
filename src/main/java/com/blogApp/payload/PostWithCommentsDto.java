package com.blogApp.payload;

import lombok.Data;

import java.util.List;
@Data
public class PostWithCommentsDto {
    private DtoPost postDto;
    private List<CommentDto> commentDtos;
    private boolean isEmpty;
}
