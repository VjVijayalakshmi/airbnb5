package com.Blogapp1.payload;

import com.Blogapp1.entity.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PostWithCommentDto {
    private PostDto post;
    private List<CommentDto> comment=new ArrayList<>();
}
