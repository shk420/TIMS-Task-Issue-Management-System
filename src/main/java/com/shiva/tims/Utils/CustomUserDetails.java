package com.shiva.tims.Utils;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shiva.tims.models.User;

public class CustomUserDetails implements UserDetails {
	
	private final String id;
	private final String username;
	private final String password;
	private final String role;
	
	public CustomUserDetails (User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPasswordHash();
		this.role =user.getRole().name();
	}
	
	public String getId() {
		return id;
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		return List.of(new SimpleGrantedAuthority("ROLE_" + role));
		
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override public boolean isAccountNonExpired() {return true;}
	@Override public boolean isAccountNonLocked() {return true;}
	@Override public boolean isCredentialsNonExpired() {return true;}
	@Override public boolean isEnabled() {return true;}


}
