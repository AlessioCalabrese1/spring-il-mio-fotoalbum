package org.generation.italy.demo.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "La USERNAME NON può essere vuoto!")
	@Column(unique = true)
	private String username;
	
	@NotNull
	@NotEmpty(message= "La PASSWORD NON può essere vuota!")
	@Column(length = 14)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public User() { }
	public User(String _username, String _password, Role _role) {
		setUsername(_username);
		setPassword(_password);
		addRole(_role);
	}
	public User(String _username, String _password, Role... _roles) {
		setUsername(_username);
		setPassword(_password);
		setRoles(_roles);
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Role[] _roles) {
		this.roles = new HashSet<>(Arrays.asList(_roles));
	}
	
	public void addRole(Role role) {
		if (getRoles() == null) {
			roles = new HashSet<>();
		}
		roles.add(role);
	}


	@Override
	public String toString() {
		return "( " + getId() + " )" + " - " + getUsername() + " - " + getRoles() + "\n";
	}
}
