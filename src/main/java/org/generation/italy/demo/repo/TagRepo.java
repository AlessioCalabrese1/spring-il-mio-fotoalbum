package org.generation.italy.demo.repo;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer>{
	public List<Tag> findByNameContaining(String tagName);
}
