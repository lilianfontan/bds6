package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("SELECT obj FROM Movie obj INNER JOIN obj.genre WHERE "
			+ "obj.id = :id")
	Optional<Movie> findByIdWithGenre (Long id);
	
	Optional<Movie> findByGenreId(Long id);

}
