package org.java.spring.auth.db.serv;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public UserRepo userRepo;
	
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	public User findById(int id) {
		
		return userRepo.findById(id).get();
	}
	public void save(User user) {
		
		userRepo.save(user);
	}
}
