package com.devsuperior.movieflix.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private MovieRepository repository;
	
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
				Optional<Movie> obj = repository.findByIdWithGenre(id);
				Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
				return new MovieDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre (Long genreId, Pageable pageable){
		Page<Movie> page = (genreId == 0) ? repository.findAllOrderByName(pageable) : repository.findByGenreId(genreId, pageable);
		Page<MovieDTO> pageMovie = page.map(x ->new MovieDTO(x));
		return pageMovie;
	}
}
