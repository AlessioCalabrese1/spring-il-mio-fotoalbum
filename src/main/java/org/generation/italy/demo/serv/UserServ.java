package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.repo.UserRepo;
import org.generation.italy.demo.security.DataBaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServ implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	public List<User> all(){
		
		return userRepo.findAll();
	}
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}
	
	public Optional<User> finById(int id){
		return userRepo.findById(id);
	}
	
	public Optional<User> finByUsername(String username){
		return userRepo.findByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		
		if(user.isEmpty()){
			throw new UsernameNotFoundException("User NOT found!");
		}
		
		return new DataBaseUserDetails(user.get());
	}

}
