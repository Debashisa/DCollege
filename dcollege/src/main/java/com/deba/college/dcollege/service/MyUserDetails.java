//package com.deba.college.dcollege.service;
//
//import com.deba.college.dcollege.entity.Role;
//import com.deba.college.dcollege.entity.User;
//import com.deba.college.dcollege.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Set;
//@Service
//public class MyUserDetails implements UserDetailsService {
//    @Autowired
//    UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user= userRepo.findByUserName(username);
//        ArrayList<GrantedAuthority> roles=getAuthorities(user.getRoles());
//        return getUserDetails(user,roles);
//    }
//
//    public UserDetails getUserDetails(User user,ArrayList<GrantedAuthority> roles){
//        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),true,
//                true,true,true,roles);
//    }
//
//    public ArrayList<GrantedAuthority> getAuthorities(Set<Role> roles){
//
//        ArrayList<GrantedAuthority> userRoles=new ArrayList<>();
//        for(Role role:roles){
//            userRoles.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return userRoles;
//
//    }
//}
