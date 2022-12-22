package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServ {
	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> all(){
		return categoryRepo.findAll();
	}
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
	
	public void deleteById(int id) {
		categoryRepo.deleteById(id);
	}
	
	public Optional<Category> findById(int id){
		return categoryRepo.findById(id);
	}
}
