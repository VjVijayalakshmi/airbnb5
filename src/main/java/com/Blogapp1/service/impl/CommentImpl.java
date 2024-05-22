package com.Blogapp1.service.impl;

import com.Blogapp1.entity.Comment;
import com.Blogapp1.entity.Post;
import com.Blogapp1.exception.ResourceNotFound;
import com.Blogapp1.payload.CommentDto;
import com.Blogapp1.payload.PostDto;
import com.Blogapp1.payload.PostWithCommentDto;
import com.Blogapp1.repository.CommentRepository;
import com.Blogapp1.repository.PostRepository;
import com.Blogapp1.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentImpl implements CommentService {

    private CommentRepository cr;
    private ModelMapper mp;
    private PostRepository pr;



    @Override
    public CommentDto createComments(CommentDto dto, long id) {
        Optional<Post> byId = pr.findById(id);
        Post post = byId.get();

        Comment comment1 = mapToEntity(dto);
        comment1.setPost(post);
        Comment saved = cr.save(comment1);
        CommentDto commentDto = mapToDto(saved);
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllComments(long id) {

        List<Comment> comments = cr.findByPostId(id);
        List<CommentDto> dto = comments.stream().map(e -> mapToDto(e)).collect(Collectors.toList());



        return dto;
    }

    @Override
    public PostWithCommentDto getAllPostsWithComments(long id) {
        Post post = pr.findById(id).orElseThrow(() -> new ResourceNotFound("post not found with the id:"+id));
        PostDto dt=new PostDto();
         dt.setId(post.getId());
         dt.setTitle(post.getTitle());
         dt.setDescription(post.getDescription());
         dt.setContent(post.getContent());
        List<Comment> comment1 = cr.findByPostId(id);
        List<CommentDto> collect = comment1.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        PostWithCommentDto comment=new PostWithCommentDto();
        comment.setPost(dt);
        comment.setComment(collect);
        return comment;
    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = mp.map(dto, Comment.class);
        return comment;

    }
    CommentDto mapToDto(Comment c){
       return mp.map(c,CommentDto.class);

    }
}
