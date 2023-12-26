package com.techtitans.smartbudget.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users", schema = "public")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String user_id;

    @Length(max = 255,message = "Try again! Your username is too long")
    @NotEmpty(message = "Try again! Your username cannot be empty")
    @Column(name = "username")
    private String username;

    @Email(message = "This is not a valid email")
    @NotEmpty(message = "Try again! Email cannot be empty")
    @Column(name = "email", unique = true)
    private String email;

    @Length(max = 255, message = "Password is too long")
    @NotEmpty(message = "Try again! Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Try again! Password must meet the requirements")
    @Column(name = "password")
    private String password;

    @Column (name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_created;

    @Column (name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_login;






}