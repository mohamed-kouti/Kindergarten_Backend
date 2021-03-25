package tn.esprit.spring.service.implementations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.IUserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	static String role;
	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
		
		role = userRepository.getRole(user.getEmail());
		System.out.println(getAuthorities(user));
		

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + role.toUpperCase());
		return authorities;

	}
}
