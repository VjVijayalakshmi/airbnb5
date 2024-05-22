package com.Blogapp1.service.impl;


import com.Blogapp1.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPostDetails(PostDto dto);

    void  deletePost(long id);

List<PostDto> fetchAllData(int pageNo, int pageSize, String sortBy, String sortDir);
PostDto getPostsById(long id);
}
