package org.generation.italy.demo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Picture;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.serv.CategoryServ;
import org.generation.italy.demo.serv.PictureServ;
import org.generation.italy.demo.serv.RoleServ;
import org.generation.italy.demo.serv.TagServ;
import org.generation.italy.demo.serv.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbum implements CommandLineRunner{
	
	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private TagServ tagServ;
	
	@Autowired
	private PictureServ pictureServ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbum.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		setRoleUser();
		
		Set<Category> categories = setCategory();
		Set<Tag> tags = setTag();
		
		setPicture(categories, tags);
	}
	
	
	
	public void setRoleUser() {
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleServ.save(admin);
		roleServ.save(user);
		
		User adminUser = new User("Admin", "{noop}Admin123", admin);
		User userUser = new User("User", "{noop}User1234", user);
		User userAdmin = new User("User-Admin", "{noop}Alex4316", admin, user);
		
		userServ.save(adminUser);
		userServ.save(userUser);
		userServ.save(userAdmin);
	}
	
	public Set<Category> setCategory() {
		Category c1 = new Category("Categoria 1");
		Category c2 = new Category("Categoria 2");
		Category c3 = new Category("Categoria 3");
		
		categoryServ.save(c1);
		categoryServ.save(c2);
		categoryServ.save(c3);
		
		Set<Category> categories = new HashSet<>();
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		return categories;
	}
	
	public Set<Tag> setTag(){
		
		Tag t1 = new Tag("Tag 1");
		Tag t2 = new Tag("Tag 2");
		Tag t3 = new Tag("Tag 3");
		
		tagServ.save(t1);
		tagServ.save(t2);
		tagServ.save(t3);
		
		Set<Tag> tags = new HashSet<>();
		tags.add(t1);
		tags.add(t2);
		tags.add(t3);
		return tags;
	}
	
	public void setPicture(Set<Category> categories, Set<Tag> tags) {
		
		List<Category> categoriesList = new LinkedList<>(categories);
		List<Tag> tagsList = new LinkedList<>(tags);
		
		Picture p1 = new Picture("Picture 1", 
				"https://media.istockphoto.com/id/1213516345/it/foto/pazzo-dallaspetto-bianco-e-nero-bordo-collie-cane-dire-guardando-intensamente-su-sfondo-giallo.jpg?s=612x612&w=0&k=20&c=m2je0WoVVxVJ0tQj99NxnoR0yN8SmJRezz3GCJ2ONQE=",
				true
				);
		Picture p2 = new Picture("Picture 2", 
				"https://www.icrewplay.com/wp-content/uploads/2018/05/igry-art-rycar-artorias.jpg",
				true, 
				categoriesList.get(0),
				categoriesList.get(1)
				);
		Picture p3 = new Picture("Picture 3", 
				"https://static.wikia.nocookie.net/eldenring/images/1/1f/Godfrey.png/revision/latest?cb=20220206155142&path-prefix=it",
				true,
				tagsList.get(0),
				tagsList.get(2));
		Picture p4 = new Picture("Picture 4",
				"https://i.pinimg.com/originals/98/73/11/987311ea5621470fa6aeadfe78c5f1e2.jpg", 
				false,
				categories,
				tags);
		
		Picture p5 = new Picture("Picture 5",
				"https://media.istockphoto.com/id/1213516345/it/foto/pazzo-dallaspetto-bianco-e-nero-bordo-collie-cane-dire-guardando-intensamente-su-sfondo-giallo.jpg?s=612x612&w=0&k=20&c=m2je0WoVVxVJ0tQj99NxnoR0yN8SmJRezz3GCJ2ONQE=", 
				false,
				categories,
				tags);
		
		pictureServ.save(p1);
		pictureServ.save(p2);
		pictureServ.save(p3);
		pictureServ.save(p4);
		pictureServ.save(p5);
		
		return ;
	}
}
















