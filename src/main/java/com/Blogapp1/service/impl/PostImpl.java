package com.Blogapp1.service.impl;

import com.Blogapp1.entity.Post;

import com.Blogapp1.payload.PostDto;
import com.Blogapp1.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostImpl implements PostService {

    private PostRepository pr;
    private ModelMapper modelMapper;


    public PostImpl(PostRepository pr, ModelMapper modelMapper) {
        this.pr = pr;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPostDetails(PostDto dto) {
        Post p = mapToEntity(dto);

        Post saved = pr.save(p);
        PostDto dto1 = mapToPostDto(saved);
        return dto1;

    }

    @Override
    public void deletePost(long id){


        pr.deleteById(id);
    }


    Post mapToEntity(PostDto dto) {

        Post p = modelMapper.map(dto, Post.class);
        return p;


    }

    PostDto mapToPostDto(Post p) {
        PostDto dto = modelMapper.map(p, PostDto.class);

        return dto;
    }

    @Override
    public List<PostDto> fetchAllData(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);


        Page<Post> all = pr.findAll(pageRequest);
        List<PostDto> dtos = all.stream().map(e -> mapToPostDto(e)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public PostDto getPostsById(long id) {
        Optional<Post> byId = pr.findById(id);
        Post post = byId.get();
       return mapToPostDto(post);


    }


}














