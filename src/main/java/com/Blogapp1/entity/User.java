package com.Blogapp1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="user_role",
    joinColumns = @JoinColumn(name="user_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
    private Set<Role> roles;




 }







