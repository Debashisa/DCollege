package com.deba.college.dcollege.repo;

import com.deba.college.dcollege.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmailId(String email);
}
