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
	User user=new User();
	
	@Autowired
	static IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 user = userRepository.findByEmail(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
		System.out.println(getAuthorities(user));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
		
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
