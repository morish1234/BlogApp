package com.blogApp.service.impl;


import com.blogApp.entity.Post;
import com.blogApp.exception.TheRecordDoesNotExit;
import com.blogApp.payload.DtoPost;
import com.blogApp.payload.ListDto;
import com.blogApp.repository.PostRepository;
import com.blogApp.service.service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements service {

    private PostRepository postRepository;
        private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public DtoPost savepost(DtoPost dtoPost) {
        Post post = maptoEntity(dtoPost);

        Post save = postRepository.save(post);
       DtoPost dto= maptoDto(save);
        return dto;
    }

    @Override
    public ListDto getallPost(int pageNo, int pageSize, String sortBy, String sortDir) {
              Sort sort=sortBy.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> allData = postRepository.findAll(pageRequest);
        List<Post> posts = allData.getContent();

        List<DtoPost> allDto = posts.stream().map(c -> maptoDto(c)).collect(Collectors.toList());
         ListDto listDto=new ListDto();
         listDto.setList(allDto);
         listDto.setTotalPage(allData.getTotalPages());
         listDto.setTotalElements((int)allData.getTotalElements());
         listDto.setLastPage(allData.isLast());
         listDto.setFirstPage(allData.isFirst());
         listDto.setPageNumber(allData.getNumber());
        return  listDto;

    }

    @Override
    public void deletepost(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()->new TheRecordDoesNotExit("record does not exist"));
        postRepository.deleteById(id);
    }



    Post maptoEntity(DtoPost dtoPost){
        Post post=modelMapper.map(dtoPost,Post.class);
        return post;
    }
    DtoPost maptoDto(Post post){
        DtoPost dto=modelMapper.map(post,DtoPost.class);
        return dto;
    }
    @Override
    public Post getPost(long id) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new TheRecordDoesNotExit("Resource Not Found")
        );

        return post1;
    }
    @Override
    public DtoPost updatePost(Post post) {
        Post save = postRepository.save(post);
        DtoPost dto=modelMapper.map(save,DtoPost.class);
        return dto;
    }


}
