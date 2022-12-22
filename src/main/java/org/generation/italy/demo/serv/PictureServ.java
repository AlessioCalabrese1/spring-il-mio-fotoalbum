package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.repo.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServ {
	@Autowired
	private PictureRepo pictureRepo;
	
	public List<Picture> all(){
		return pictureRepo.findAll();
	}
	
	public void save(Picture picture) {
		pictureRepo.save(picture);
	}
	
	public void deleteById(int id) {
		pictureRepo.deleteById(id);
	}
	
	public Optional<Picture> finById(int id){
		return pictureRepo.findById(id);
	}
	
	public List<Picture> search(String title, String tagName){
		return pictureRepo.findByTitleContainingOrTagsNameContaining(title, tagName);
	}
	
	public List<Picture> apiAll(){
		return pictureRepo.findByValidTrue();
	}
	
	public List<Picture> apiSearch(String title, String tagName){
		return pictureRepo.findByTitleContainingAndValidTrueOrTagsNameContainingAndValidTrue(title, tagName);
	}
}
