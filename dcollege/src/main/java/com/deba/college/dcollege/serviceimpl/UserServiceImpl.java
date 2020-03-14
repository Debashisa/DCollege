package com.deba.college.dcollege.serviceimpl;

import com.deba.college.dcollege.entity.Role;
import com.deba.college.dcollege.entity.User;
import com.deba.college.dcollege.repo.UserRepo;
import com.deba.college.dcollege.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmailId(String email) {
        return userRepo.findByEmailId(email);
    }

    @Override
    public User save(User user) {
        User newUser=new User();
        Role newRole=new Role();
        newUser.setName(user.getName());
        newUser.setUserName(user.getUserName());
        newUser.setEmailId(user.getEmailId());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setActive(true);

        //newRole.setRoleName("ROLE_USER");
        newRole.setRoleName("ROLE_"+user.getAdminLevel().toUpperCase());
        newUser.setRoles(Arrays.asList(newRole));
        return userRepo.save(newUser);
    }

    @Override
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=findByEmailId(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid UserName or Password");
        }
        log.info("Inside loadUserByName ...");
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
                true,true,true,
                true,mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
