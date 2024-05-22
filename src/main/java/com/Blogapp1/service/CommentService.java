package com.Blogapp1.service;

import com.Blogapp1.payload.CommentDto;
import com.Blogapp1.payload.PostWithCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComments(CommentDto dto,long id);
    List<CommentDto> getAllComments(long id);
    PostWithCommentDto getAllPostsWithComments(long id);
}
