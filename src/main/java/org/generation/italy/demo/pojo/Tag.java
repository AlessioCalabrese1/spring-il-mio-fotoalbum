package org.generation.italy.demo.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il nome NON può essere vuoto!")
	@Column(unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "tags", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<Picture> pictures;
	
	public Tag() { }
	public Tag(String _name) {
		setName(_name);
	}
	public Tag(String _name, Picture... _pictures) {
		this(_name);
		setPictures(_pictures);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Picture[] _pictures) {
		this.pictures = new HashSet<>(Arrays.asList(_pictures));
	}
	
	@Override
	public String toString() {
		return "( " + getId() + " )" + " - " + getName() + "\n";
	}
}
