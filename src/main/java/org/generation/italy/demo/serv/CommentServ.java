package org.generation.italy.demo.serv;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServ {
	
	@Autowired
	private CommentRepo commentRepo;
	
	public void save(Comment comment) {
		commentRepo.save(comment);
	}
	
	public List<Comment> findCommentsByPictureId(int id){
		return commentRepo.findByPictureId(id);
	}
}
