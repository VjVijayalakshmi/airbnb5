package com.Blogapp1.entity;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="xxzyy")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="title",unique=true)
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post",orphanRemoval = true,cascade =CascadeType.ALL)
    private List<Comment> comment=new ArrayList<>();



}


