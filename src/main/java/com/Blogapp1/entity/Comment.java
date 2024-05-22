package com.Blogapp1.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
@Table(name="comments")

public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name", unique=true,nullable=false)
    private String name;
    private String message;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

}
