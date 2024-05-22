package com.Blogapp1.controller;

import com.Blogapp1.payload.PostDto;
import com.Blogapp1.service.impl.PostService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {
    private PostService ps;

    public PostController(PostService ps) {

        this.ps = ps;
    }

    @PostMapping
    public ResponseEntity<?> createPostsDetails(@Valid @RequestBody PostDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto1 = ps.createPostDetails(dto);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);


    }
    //http://localhost:8080/api/post/2

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {

       ps.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);


    }

    //http://localhost:8080/api/post/pageNo=0&pageSize&sortBy=description&sortDir=desc
    @GetMapping
   public ResponseEntity<List<PostDto>> fetchAllRecords(
           @RequestParam(name="pageNo",defaultValue = "0",required=false)int pageNo,
           @RequestParam(name="pageSize",defaultValue ="5",required=false)int pageSize,
           @RequestParam(name="sortBy",defaultValue = "id",required=false)String sortBy,
           @RequestParam(name="sortDir",defaultValue = "asc",required=false)String sortDir
    ){
        List<PostDto> postDtos = ps.fetchAllData(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
 }
 //http://localhost:8080/api/post/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostsByID(@PathVariable long id){
        PostDto postsById = ps.getPostsById(id);
        return new ResponseEntity<>(postsById,HttpStatus.OK);
    }



    }
    
           
                        


           


