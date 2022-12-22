package org.generation.italy.demo.controller;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.serv.CategoryServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryServ categoryServ;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("categories", categoryServ.all());
		model.addAttribute("size", categoryServ.all().size());
		
		return "Category/Index-Category";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("category",  categoryServ.findById(id));
		
		return "Category/Show-Category";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Category newCategory = new Category();
		model.addAttribute("category", newCategory);
		
		return "Category/Create-Category";
	}
	
	@PostMapping("/store")
	public String store(@Valid Category category, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/category/create";
		}
		
		System.err.println(category + "sono nello store di category");
		
		categoryServ.save(category);
		
		return "redirect:/category";
	}
	
	@GetMapping("/edit/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("category", categoryServ.findById(id).get());
		
		return "Category/Edit-Category";
	}
	
	@PostMapping("/update")
	public String update(@Valid Category category, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/category/edit/" + category.getId();
		}
		
		categoryServ.save(category);
		
		return "redirect:/category";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Category cat = categoryServ.findById(id).get();
		
		for (Picture pic : cat.getPictures()) {
			pic.getCategories().remove(cat);
		}
		
		categoryServ.deleteById(id);
		
		return "redirect:/category";
	}
}














