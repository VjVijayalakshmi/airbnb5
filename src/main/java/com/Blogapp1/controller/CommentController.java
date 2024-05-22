package com.Blogapp1.controller;

import com.Blogapp1.payload.CommentDto;
import com.Blogapp1.payload.PostWithCommentDto;
import com.Blogapp1.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService cs;
//http://localhost:8080/api/comment
    @PostMapping("/{id}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto,@PathVariable long id){
        CommentDto dto1 = cs.createComments(dto, id);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);

    }
    //http://localhist:8080/api/comment/1
//    @GetMapping("/{id}")
//    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable long id){
//        List<CommentDto> allComments = cs.getAllComments(id);
//        return new ResponseEntity<>(allComments,HttpStatus.OK);
//
//    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostWithCommentDto> getAllPostsWithCommentsById(@PathVariable long postId){
        PostWithCommentDto allPostsWithComments = cs.getAllPostsWithComments(postId);
        return new ResponseEntity<>(allPostsWithComments,HttpStatus.OK);
    }

}
