package com.lab.Assignment03.Config;

import com.lab.Assignment03.Entity.Role;
import com.lab.Assignment03.Entity.Users;
import com.lab.Assignment03.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username);
        System.out.println(username);
        if (users == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        Role role = users.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(role != null){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), authorities);

//        return new MyUserDetails(users);
    }

}
