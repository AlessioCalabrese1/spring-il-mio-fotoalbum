package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.serv.CommentServ;
import org.generation.italy.demo.serv.PictureServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/picture")
@CrossOrigin("*")
public class PictureApiController {
	
	@Autowired
	private PictureServ pictureServ;
	
	@Autowired
	private CommentServ commentServ;
	
	@GetMapping
	public List<Picture> getAll(){
		
		List<Picture> pictures = pictureServ.apiAll();
		
		for (Picture picture : pictures) {
			picture.setComments(commentServ.findCommentsByPictureId(picture.getId()));
		}
		
		return pictures;
	}
	
	@GetMapping("/search/{query}")
	public List<Picture> search(@PathVariable("query") String query){
		System.err.println(query);
		return pictureServ.apiSearch(query, query);
	}
	
}
