package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Integer>{
	public List<Picture> findByTitleContainingOrTagsNameContaining(String title, String tagName);
	
	public List<Picture> findByValidTrue();
	
	public List<Picture> findByTitleContainingAndValidTrueOrTagsNameContainingAndValidTrue(String title, String tagName);
}
