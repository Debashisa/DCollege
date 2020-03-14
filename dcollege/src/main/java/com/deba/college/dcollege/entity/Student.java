//package com.deba.college.dcollege.entity;
//
//import lombok.*;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "STUDENT")
//public class Student {
//
//
//    @Column(unique = true, name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    private Integer id;
//
//    @Column(length = 20)
//    @Length(min = 1, message = "Name must be provided")
//    private String name;
//
//    @Column(length = 20)
//    @Length(min = 1, message = "User name must be provided")
//    private String userName;
//
//    @Column(length = 200)
//    @Length(min = 1, message = "Password must be provided")
//    private String password;
//
//    @Column(length = 50)
//    @Length(min = 1, message = "Email Id must be provided")
//    private String emailId;
//
//    private boolean active;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    private Collection<Role> roles;
//
//    private  String adminLevel;
//
//
//}
