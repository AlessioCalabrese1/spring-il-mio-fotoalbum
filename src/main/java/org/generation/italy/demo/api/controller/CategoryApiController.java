package org.generation.italy.demo.api.controller;

import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/category")
@CrossOrigin("*")
public class CategoryApiController {
	@Autowired
	private	PictureServ picServ;
	
	@GetMapping("/by/picture/{id}")
	public Set<Category> getCategoryByPicId(@PathVariable("id") int id){
		
		return picServ.finById(id).get().getCategories();
	}
}
