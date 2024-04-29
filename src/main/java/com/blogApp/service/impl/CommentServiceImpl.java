package com.blogApp.service.impl;

import com.blogApp.entity.Comment;
import com.blogApp.entity.Post;
import com.blogApp.exception.TheRecordDoesNotExit;
import com.blogApp.payload.CommentDto;
import com.blogApp.payload.DtoPost;
import com.blogApp.payload.PostWithCommentsDto;
import com.blogApp.repository.CommentRepository;
import com.blogApp.repository.PostRepository;
import com.blogApp.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
  private   CommentRepository commentRepository;
  private PostRepository postRepository;
  private ModelMapper modelMapper;

    @Override
    public CommentDto createComments(CommentDto commentDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new TheRecordDoesNotExit("Resource Not Found")
        );
        DtoPost dtoPost = maptoDto(post);
        Comment comment = mapToCommentEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        CommentDto commentDto1 = mapToCommentDto(save);
        return commentDto1;
    }

    @Override
    public PostWithCommentsDto getPostWithComment(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new TheRecordDoesNotExit("resource not found")
        );
        DtoPost dtoPost = maptoDto(post);
        List<Comment> comments= commentRepository.findByPostId(id);

        List<CommentDto> collect = comments.stream().map(c -> mapToCommentDto(c)).collect(Collectors.toList());
               PostWithCommentsDto postWithCommentsDto=new PostWithCommentsDto();
               postWithCommentsDto.setPostDto(dtoPost);
               postWithCommentsDto.setCommentDtos(collect);
        if (comments.isEmpty()) {
            postWithCommentsDto.setEmpty(true); // Set a flag indicating the post has no comments
        } else {
            postWithCommentsDto.setEmpty(false); // Set a flag indicating the post has comments
        }

        if (postWithCommentsDto.isEmpty()) {
            System.out.println("This post has no comments.");
        }
        return postWithCommentsDto;
    }

    public  Post maptoEntity(DtoPost dtoPost){
        Post post = modelMapper.map(dtoPost, Post.class);
        return post;

    }
    public  DtoPost maptoDto(Post post){
        DtoPost postDto = modelMapper.map(post, DtoPost.class);
        return postDto;
    }
    public Comment mapToCommentEntity(CommentDto commentDto){
        Comment map = modelMapper.map(commentDto, Comment.class);
        return map;
    }
    public CommentDto mapToCommentDto(Comment comment){
        CommentDto map = modelMapper.map(comment, CommentDto.class);
        return map;
    }

}
