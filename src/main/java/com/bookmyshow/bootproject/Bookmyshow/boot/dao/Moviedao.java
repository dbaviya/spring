package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Movierepo;
@Repository
public class Moviedao {
	@Autowired
		Movierepo mRepo;
		
		public Movies saveMovie(Movies movie)
		{
			return mRepo.save(movie);
		}
		
		public Movies findMovie(int movieid)
		{
			Optional<Movies> opMovie=mRepo.findById(movieid);
			if(opMovie.isPresent())
			{
				return opMovie.get();
			}
			return null;
		}
		
		public Movies deleteMovie(int movieid)
		{
			Movies m=findMovie(movieid);
			mRepo.delete(m);
			return m;
		
		}
		
		public Movies updateMovie(Movies movie, int movieid)
		{
			Movies exm=findMovie(movieid);
			
			if(exm!=null)
			{
				movie.setMovieid(movieid);
				return mRepo.save(movie);
			}
			return null;
		}
		
		public List<Movies> findAllMovie()
		{
			List<Movies> movie=mRepo.findAll();
			return movie;
		}
		public Movies findmovietitle(String movietitle)
		{
			return mRepo.findBymovietitle(movietitle);
		}
		
		
	}

