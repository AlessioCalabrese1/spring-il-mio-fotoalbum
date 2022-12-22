package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServ {
	@Autowired
	private TagRepo tagRepo;
	
	public List<Tag> all(){
		return tagRepo.findAll();
	}
	
	public void save(Tag tag) {
		tagRepo.save(tag);
	}
	
	public void deleteById(int id) {
		tagRepo.deleteById(id);
	}
	
	public Optional<Tag> findByid(int id) {
		return tagRepo.findById(id);
	}
	
	public List<Tag> findByName(String tagName){
		return tagRepo.findByNameContaining(tagName);
	}
}
