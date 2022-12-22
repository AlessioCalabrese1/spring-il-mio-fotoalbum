package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.serv.CommentServ;
import org.generation.italy.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/comment")
@CrossOrigin("*")
public class CommentApiController {
	
	@Autowired
	private CommentServ commentServ;
	
	@Autowired
	private PictureServ picServ;
	
	@PostMapping("/create/{id}")
	public Comment createComment(@PathVariable("id") int id, @Valid @RequestBody Comment comment) {
		
		Picture pic = picServ.finById(id).get();
		
		comment.setPicture(pic);
		
		System.err.println(comment);
		
		commentServ.save(comment);
		return comment;
	}
	
}
