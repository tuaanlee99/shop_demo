package com.example.shop.model.user;

import com.example.shop.model.product.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @NotBlank(message = "usename is not blank")
    @Column(name = "username")
    private String username;
    @NotNull(message = "password it nhat 6 ky tu")
    @Size(min=6)
    @Column(name = "password")
    private String password;
    @NotNull(message = "fullname is not null")
    @Column(name = "fullname")
    private String fullname;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "birthday")
    private Date birthday;
    @Email(message = "email is not va")
    @Column(name = "email")
    private String email;
    @Pattern(regexp = "^[0-9]{9,10}$")
    @Column(name = "phone")
    private String phone;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
