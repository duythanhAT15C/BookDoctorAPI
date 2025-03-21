package com.lab.Assignment03.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.lab.Assignment03.Entity.Role;
import com.lab.Assignment03.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users users;
    
    public MyUserDetails(Users users) {
        this.users = users;
    }
    public String getFullName() {
    	return users.getName();
    }
    public int getId() {
    	return users.getId();
    }
    public Users getUser() {
    	return users;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = users.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(role != null){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println(authorities);
        return authorities;
	}

	@Override
    public String getPassword() {
        return users.getPassword();
    }
 
    @Override
    public String getUsername() {
        return users.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

}
