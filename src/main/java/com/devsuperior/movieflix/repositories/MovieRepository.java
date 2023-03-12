package com.devsuperior.movieflix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("SELECT obj FROM Movie obj INNER JOIN obj.genre WHERE "
			+ "obj.id = :id "
			+ "ORDER BY obj.title ASC ")
	Optional<Movie> findByIdWithGenre (Long id);
	
	Page<Movie> findByGenreId(Long id, Pageable pageable);
	
	@Query("SELECT obj FROM Movie obj "
			+ "ORDER BY obj.title ASC")
	Page<Movie> findAllOrderByName(Pageable pageable);
	
	

}
