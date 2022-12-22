package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{
	public List<Comment> findByPictureId(int id);
}
