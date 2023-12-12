package org.java.spring.auth.db.serv;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	public UserRepo userRepo;
	
	// Restituisce una lista di tutti gli utenti nel repository.
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	// Trova un utente per ID.
	public User findById(int id) {
		return userRepo.findById(id).get();
	}
	
	// Salva un utente nel repository.
	public void save(User user) {
		userRepo.save(user);
	}
	
	// Implementazione del metodo di caricamento dell'utente basato sul nome utente.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return user;
	}
}
