package tn.esprit.spring.service.implementations;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;


import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private User user;
	@Autowired
	static IUserRepository userRepository;


	public AuthenticatedUser(User user) {
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String r=userRepository.getRole(user.getEmail());
		r=r.toUpperCase();
		System.out.println(r);
				
				Collection<GrantedAuthority> authorities = AuthorityUtils
						.createAuthorityList("ROLE_"+r);
				return authorities;
	}
}
