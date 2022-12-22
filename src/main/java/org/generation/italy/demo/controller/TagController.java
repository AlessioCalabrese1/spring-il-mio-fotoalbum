package org.generation.italy.demo.controller;

import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.serv.TagServ;
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
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagServ tagServ;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("tags", tagServ.all());
		model.addAttribute("size", tagServ.all().size());
		
		return "Tag/Index-Tag";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Tag newTag = new Tag();
		
		model.addAttribute("tag", newTag);
		
		return "Tag/Create-Tag";
	}
	
	@PostMapping("/store")
	public String store(@Valid Tag tag, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/tag/create";
		}
		
		tagServ.save(tag);
		
		return "redirect:/tag";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("tag", tagServ.findByid(id));
		
		return "Tag/Edit-Tag";
	}
	
	@PostMapping("/update")
	public String update(@Valid Tag tag, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/tag/create";
		}
		
		tagServ.save(tag);
		
		return "redirect:/tag";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Tag tag = tagServ.findByid(id).get();
		
		for (Picture pic : tag.getPictures()) {
			pic.getTags().remove(tag);
		}
		
		tagServ.deleteById(id);
		
		return "redirect:/tag";
	}
	
	
	
	
}
