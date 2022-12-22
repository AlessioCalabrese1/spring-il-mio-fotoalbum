package org.generation.italy.demo.controller;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.serv.CommentServ;
import org.generation.italy.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private PictureServ picServ;
	
	@Autowired
	private CommentServ commentServ;
	
	@PostMapping("/store/{id}")
	public String createComment(@PathVariable("id") int id, @Valid @RequestBody Comment comment) {
		
		Picture pic = picServ.finById(id).get();
		
		comment.setPicture(pic);
		
		System.err.println(comment);
		
		commentServ.save(comment);
		return "Picture/Show-Picture";
	}
	
}
