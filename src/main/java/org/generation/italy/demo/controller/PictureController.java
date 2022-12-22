package org.generation.italy.demo.controller;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.serv.CategoryServ;
import org.generation.italy.demo.serv.CommentServ;
import org.generation.italy.demo.serv.PictureServ;
import org.generation.italy.demo.serv.TagServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PictureController {
	
	@Autowired
	private PictureServ pictureServe;
	
	@Autowired
	private TagServ tagServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private CommentServ commentServ;
	
	@GetMapping
	public String index(Model model, @RequestParam(name = "query", required = false) String query) {
		
		if(query != null && !query.isBlank()) {
			model.addAttribute("pictures", pictureServe.search(query, query));
			
		}else {
			model.addAttribute("pictures", pictureServe.all());
		}
		
		model.addAttribute("size", pictureServe.all().size());
		
		System.err.println(pictureServe.all());
		
		return "Picture/Index-Picture";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Picture pic = pictureServe.finById(id).get();
		
		for (Comment comment : commentServ.findCommentsByPictureId(id)) {
			pic.getComments().add(comment);
		}
		
		model.addAttribute("picture", pic);
		return "Picture/Show-Picture";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Picture newPicture = new Picture();
		model.addAttribute("picture", newPicture);
		model.addAttribute("categories", categoryServ.all());
		model.addAttribute("tags", tagServ.all());
		
		return "Picture/Create-Picture";
	}
	
	@PostMapping("/store")
	public String store(@Valid Picture picture, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/create";
		}
		
		pictureServe.save(picture);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("picture", pictureServe.finById(id).get());
		model.addAttribute("categories", categoryServ.all());
		model.addAttribute("tags", tagServ.all());
		
		System.err.println(pictureServe.finById(id).get());
		
		return "Picture/Edit-Picture";
	}
	
	@PostMapping("/update")
	public String update(@Valid Picture picture, BindingResult bindingResults, RedirectAttributes redirectAttributes) {
		
		if (bindingResults.hasErrors()) {
			System.err.println("Error-----------------------------------------");
			System.err.println(bindingResults.getAllErrors());
			System.err.println("----------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResults.getAllErrors());
			
			return "redirect:/create";
		}
		
		pictureServe.save(picture);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		try {
			pictureServe.deleteById(id);
		} catch (Exception e) {
			System.err.println("Error-----------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("----------------------------------------------");
		}
		
		return "redirect:/";
	}
	
	
	
	
	
}
