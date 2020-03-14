package com.deba.college.dcollege.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ROLE")
public class Role {

    @Column(name = "ROLE_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<User> users;


}
