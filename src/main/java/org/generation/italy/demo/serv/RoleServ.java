package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServ {
	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> all(){
		return roleRepo.findAll();
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public void deleteById(int id) {
		roleRepo.deleteById(id);
	}
	
	public Optional<Role> findById(int id){
		return roleRepo.findById(id);
	}
}
