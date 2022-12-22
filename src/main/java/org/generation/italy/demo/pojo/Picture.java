package org.generation.italy.demo.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il TITOLO NON può essere vuoto!")
	@Size(min = 2, max = 30)
	private String title;
	
	@Size(max = 100)
	private String description;
	
	@NotBlank(message = "La foto DEVE essere inserita!")
	@URL(message = "L'URL NON è valido")
	@Lob
	private String imgURL;
	
	@NotNull
	private boolean valid;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	private Set<Tag> tags;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	private Set<Category> categories;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Comment> comments;
	
	public Picture() { }
	public Picture(String _title, String _imgUrl, Boolean _valid) {
		setTitle(_title);
		setImgURL(_imgUrl);
		setValid(_valid);
	}
	public Picture(String _title, String _imgUrl, Boolean _valid, Tag... _tags) {
		this(_title, _imgUrl, _valid);
		setTags(_tags);
	}
	public Picture(String _title, String _imgUrl, Boolean _valid, Category... _categories) {
		this(_title, _imgUrl, _valid);
		setCategories(_categories);
	}
	public Picture(String _title, String _imgUrl, Boolean _valid, Set<Category> _categories, Set<Tag> _tags) {
		this(_title, _imgUrl, _valid);
		setTags(_tags);
		setCategories(_categories);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	
	public void setTags(Tag[] _tags) {
		this.tags = new HashSet<>(Arrays.asList(_tags));
	}

	public void setCategories(Category[] _categories) {
		this.categories = new HashSet<>(Arrays.asList(_categories));
	}
	
	
	
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> list) {
		this.comments = list;
	}
	@Override
	public String toString() {
		return "( " + getId() + " )" + " - " + getTitle() + " - " + getCategories() + " - " + getTags() + "\n";
	}
	
}










