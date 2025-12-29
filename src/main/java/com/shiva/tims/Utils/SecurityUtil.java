package com.shiva.tims.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {
	
	private SecurityUtil() {}
	
	public static CustomUserDetails getCurrentUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !authentication.isAuthenticated() || 
				authentication.getPrincipal().equals("anonymousUser")) {
			throw new IllegalStateException("No Authenticated User Found");
		}
		return (CustomUserDetails) authentication.getPrincipal();
	}
	
	public static String getCurrentUserId() {
		return getCurrentUser().getId();
	}
	
	public static String getCurrentUsername() {
		return getCurrentUser().getUsername();
	}
	
	public static boolean hasRole(String role) {
		return getCurrentUser().getAuthorities().stream()
				.anyMatch(a-> a.getAuthority().equals("ROLE_" + role));
	}

}
