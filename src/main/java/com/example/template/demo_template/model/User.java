package com.example.template.demo_template.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(max = 15)
    private String username;

    @NotNull
    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    @Size(max = 100)
    private String password;

    @NotNull
    @DateTimeFormat
    private Date dateCreated;
    
    @DateTimeFormat
    private Date dateModified;

    @NotNull
    @Size(max = 100)
    private String whoCreated;
    
    @Size(max = 100)
    private String whoModified;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(name = "user_roles",
            // joinColumns = @JoinColumn(name = "user_id"),
            // inverseJoinColumns = @JoinColumn(name = "role_id"))
    // private Set<Role> roles = new HashSet<>();

    
}